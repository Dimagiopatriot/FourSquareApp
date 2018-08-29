package com.testapp.testapp

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.testapp.testapp.kotlin.model.entity.Venue
import com.testapp.testapp.kotlin.presenter.VenuePresenter
import com.testapp.testapp.kotlin.presenter.adapter.CommonRecyclerViewAdapter
import com.testapp.testapp.kotlin.presenter.adapter.VenueAdapter
import com.testapp.testapp.kotlin.view.AdapterView
import com.testapp.testapp.kotlin.view.CustomListView

/**
 * Created by dmitriysmishnyi on 25.07.18.
 * */
class MainActivity : AppCompatActivity(), LocationListener {

    private val requestPermissionsRequestCode = 34
    private val requestCheckSettings = 1

    private var lastLocation: Location? = null
    private var fusedLocationClient: FusedLocationProviderClient? = null
    private var googleApiClient: GoogleApiClient? = null
    private var locationRequest: LocationRequest? = null

    private var venuesLoading: ProgressBar? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar_actionbar)
        setSupportActionBar(toolbar)

        venuesLoading = findViewById(R.id.venuesLoading)
        recyclerView = findViewById(R.id.searchVenuesRecyclerView)
        initializeGoogleApiClient()
        connectGoogleApiClient()
    }

    override fun onStop() {
        super.onStop()
        googleApiClient?.disconnect()
    }

    private fun initializeGoogleApiClient() {
        googleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(object : GoogleApiClient.ConnectionCallbacks {
                    override fun onConnected(p0: Bundle?) {
                        val builder = settingsLocationRequest()
                        callbackPendingResult(builder)
                    }

                    override fun onConnectionSuspended(p0: Int) = Toast.makeText(this@MainActivity,
                            "Connection is suspended!", Toast.LENGTH_SHORT)
                            .show()
                })
                .addOnConnectionFailedListener { connectionResult -> onConnectionFailed(connectionResult) }
                .addApi(LocationServices.API)
                .build()
    }

    private fun connectGoogleApiClient() {
        if (googleApiClient != null) {
            googleApiClient?.connect()
        } else {
            Toast.makeText(this, "Google API Client not Connected!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkGoogleApiClientConnection() {
        if (!googleApiClient!!.isConnected)
            googleApiClient?.connect()
    }

    private fun settingsLocationRequest(): LocationSettingsRequest.Builder {
        val timeoutIntervalInMilliseconds: Long = 10000
        val timeoutFastestIntervalInMilliseconds: Long = 1000

        locationRequest = LocationRequest()
        locationRequest?.interval = timeoutIntervalInMilliseconds
        locationRequest?.fastestInterval = timeoutFastestIntervalInMilliseconds
        locationRequest?.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        return LocationSettingsRequest.Builder().addLocationRequest(locationRequest!!)
    }

    private fun callbackPendingResult(builder: LocationSettingsRequest.Builder) {
        val result = LocationServices.getSettingsClient(this).checkLocationSettings(builder.build())
        result.addOnCompleteListener {
            try {
                if (!checkPermissions()) {
                    requestPermissions()
                } else {
                    checkGoogleApiClientConnection()
                    getLastLocation()
                }
            } catch (apiException: ApiException) {
                when (apiException.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED ->
                        // Location settings are not satisfied. But could be fixed by showing the
                        // user a dialog.
                        try {
                            // Cast to a resolvable exception.
                            val resolvable = apiException as ResolvableApiException
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            resolvable.startResolutionForResult(this, requestCheckSettings)
                        } catch (e: IntentSender.SendIntentException) {
                            // Ignore the error.
                        }
                }
            }
        }
    }

    override fun onLocationChanged(location: Location?) {
        lastLocation = location
    }

    private fun onConnectionFailed(connectionResult: ConnectionResult) {
        Toast.makeText(this@MainActivity, "Connection Failed!", Toast.LENGTH_SHORT).show()

        if (connectionResult.hasResolution()) {
            try {

            } catch (exception: IntentSender.SendIntentException) {
                exception.printStackTrace()
            }
        } else {
            Log.i("Current Location", "Location services connection failed with code " + connectionResult.errorCode)
        }
    }

    private fun showSnackBar(stringId: Int, actionStringId: Int, listener: View.OnClickListener) = Snackbar
            .make(findViewById(android.R.id.content), getString(stringId), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(actionStringId), listener).show()

    private fun checkPermissions(): Boolean {
        val fineLocationPermissionState = ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)
        val coarseLocationPermissionState = ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION)

        return fineLocationPermissionState == PackageManager.PERMISSION_GRANTED && coarseLocationPermissionState == PackageManager.PERMISSION_GRANTED
    }

    private fun startLocationPermissionRequest() = ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION),
            requestPermissionsRequestCode)

    private fun requestPermissions() {
        val shouldProvideRationaleFineLocation = ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_FINE_LOCATION)
        val shouldProvideRationaleCoarseLocation = ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_COARSE_LOCATION)

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationaleFineLocation || shouldProvideRationaleCoarseLocation) {
            showSnackBar(R.string.text_warn, android.R.string.ok, View.OnClickListener { startLocationPermissionRequest() })
        } else {
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            startLocationPermissionRequest()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient!!.lastLocation
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful && task.result != null) {
                        lastLocation = task.result
                    }
                }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val item = menu!!.findItem(R.id.action_search)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val adapter: CommonRecyclerViewAdapter<Venue, VenueAdapter.Holder> = VenueAdapter()
                val customListView: CustomListView<Venue> = AdapterView(applicationContext, venuesLoading, adapter, recyclerView)

                val presenter = VenuePresenter(customListView, lastLocation, query)
                presenter.getResponse()
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            requestCheckSettings -> when (resultCode) {
                Activity.RESULT_OK -> getLastLocation()
                Activity.RESULT_CANCELED -> Toast.makeText(this, "Location Service not Enabled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == requestPermissionsRequestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation()
            } else {
                // Permission denied.

                // Notify the user via a SnackBar that they have rejected a core permission for the
                // app, which makes the Activity useless. In a real app, core permissions would
                // typically be best requested during a welcome-screen flow.

                // Additionally, it is important to remember that a permission might have been
                // rejected without asking the user for permission (device policy or "Never ask
                // again" prompts). Therefore, a user interface affordance is typically implemented
                // when permissions are denied. Otherwise, your app could appear unresponsive to
                // touches or interactions which have required permissions.

                showSnackBar(R.string.text_warn, R.string.settings, View.OnClickListener {
                    // Build intent that displays the App settings screen.
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    intent.data = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                })
            }
        }
    }
}
