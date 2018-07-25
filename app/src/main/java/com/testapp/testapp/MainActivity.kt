package com.testapp.testapp

import android.content.IntentSender
import android.location.Location
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*

import android.Manifest.permission.*
import android.content.pm.PackageManager

/**
 * Created by dmitriysmishnyi on 25.07.18.
 */
class MainActivity : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private val REQUEST_CHECK_SETTINGS = 1


    private var googleApiClient: GoogleApiClient? = null
    private var lastKnownLocation: Location? = null
    private var locationRequest: LocationRequest? = null

    private var venuesLoading: ProgressBar? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)

    }

    private fun initializeGoogleApi() {
        googleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    override fun onConnected(p0: Bundle?) {
        val builder = settingsLocationRequest()
        builder.setNeedBle(true)
        locationSettingsResponse(builder)
    }

    private fun settingsLocationRequest(): LocationSettingsRequest.Builder {
        val timeoutIntervalInMilliseconds: Long = 10000
        val timeoutFastestIntervalInMilliseconds: Long = 1000
        locationRequest = LocationRequest()
        locationRequest!!.interval = timeoutIntervalInMilliseconds
        locationRequest!!.fastestInterval = timeoutFastestIntervalInMilliseconds
        locationRequest!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        return LocationSettingsRequest.Builder().addLocationRequest(locationRequest!!)
    }

    private fun locationSettingsResponse(builder: LocationSettingsRequest.Builder) {
        val result = LocationServices.getSettingsClient(this).checkLocationSettings(builder.build())
        result.addOnCompleteListener({ task ->
            try {
                getLocation()
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
                            resolvable.startResolutionForResult(this, REQUEST_CHECK_SETTINGS)
                        } catch (e: IntentSender.SendIntentException) {
                            // Ignore the error.
                        }
                }
            }
        })
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        } else {
            //TODO implement logic
        }
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLocationChanged(p0: Location?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}