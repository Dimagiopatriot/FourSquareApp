package com.testapp.testapp;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.testapp.testapp.model.entity.Venue;
import com.testapp.testapp.presenter.Presenter;
import com.testapp.testapp.presenter.VenuesPresenter;
import com.testapp.testapp.presenter.adapter.CommonRecyclerViewAdapter;
import com.testapp.testapp.presenter.adapter.VenueAdapter;
import com.testapp.testapp.view.AdapterView;
import com.testapp.testapp.view.CustomListView;
import com.testapp.testapp.view.utils.PlayServicesUtils;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private final int REQUEST_CHECK_SETTINGS = 1;

    GoogleApiClient googleApiClient;
    Location lastKnownLocation;
    LocationRequest locationRequest;

    ProgressBar venuesLoading;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.testapp.testapp.R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);

        venuesLoading = (ProgressBar) findViewById(R.id.venuesLoading);
        recyclerView = (RecyclerView) findViewById(R.id.searchVenuesRecyclerView);
        initializeGoogleAdiClient();
        connectGoogleApiClient();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Verifies the proper version of Google Play Services exists on the device.
        PlayServicesUtils.checkGooglePlayServices(this);
    }

    private void initializeGoogleAdiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void connectGoogleApiClient() {
        if (googleApiClient != null) {
            googleApiClient.connect();
        } else {
            Toast.makeText(this, "Google API Client not Connected!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        LocationSettingsRequest.Builder builder = settingsLocationRequest();
        callbackPendingResult(builder);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "Connection is suspended!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Connection Failed!", Toast.LENGTH_SHORT).show();
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, 90000);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.i("Current Location", "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        lastKnownLocation = location;
    }

    private LocationSettingsRequest.Builder settingsLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(10000); // 10 seconds
        locationRequest.setFastestInterval(1000); // 1 second
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
    }

    private void callbackPendingResult(LocationSettingsRequest.Builder builder) {
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(googleApiClient,
                        builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {

            @Override
            public void onResult(@NonNull LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can
                        // initialize location requests here.
                        getLocation();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied, but this can be fixed
                        // by showing the user a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(MainActivity.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way
                        // to fix the settings so we won't show the dialog.
                        break;
                }
            }

        });
    }

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        } else {
            /*Getting the location after aquiring location service*/
            lastKnownLocation = LocationServices.FusedLocationApi.getLastLocation(
                    googleApiClient);

            if (lastKnownLocation == null) {
                /*if there is no last known location. Which means the device has no data for the location currently.
                * So we will get the current location.
                * For this we'll implement Location Listener and override onLocationChanged*/
                Log.i("Current Location", "No data for location found");
                checkGoogleApiClientConnection();

                LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, MainActivity.this);
            }
        }
    }

    private void checkGoogleApiClientConnection() {
        if (!googleApiClient.isConnected())
            googleApiClient.connect();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        // All required changes were successfully made
                        getLocation();
                        break;
                    case Activity.RESULT_CANCELED:
                        // The user was asked to change settings, but chose not to do
                        Toast.makeText(this, "Location Service not Enabled", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                CommonRecyclerViewAdapter<Venue, VenueAdapter.Holder> venueAdapter = new VenueAdapter();
                CustomListView<Venue> customListView = new AdapterView<>(getApplicationContext(), venuesLoading, venueAdapter, recyclerView);

                Presenter presenter = new VenuesPresenter(customListView, lastKnownLocation, query);
                presenter.getResponse();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
}