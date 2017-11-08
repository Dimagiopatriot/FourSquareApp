package com.testapp.testapp;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.testapp.testapp.model.entity.Photo;
import com.testapp.testapp.model.entity.Tip;
import com.testapp.testapp.model.entity.Venue;
import com.testapp.testapp.model.utils.Constants;
import com.testapp.testapp.presenter.PhotosPresenter;
import com.testapp.testapp.presenter.Presenter;
import com.testapp.testapp.presenter.TipsPresenter;
import com.testapp.testapp.presenter.VenueDetailsPresenter;
import com.testapp.testapp.presenter.adapter.CommonRecyclerViewAdapter;
import com.testapp.testapp.presenter.adapter.TipAdapter;
import com.testapp.testapp.view.AdapterView;
import com.testapp.testapp.view.CustomItemView;
import com.testapp.testapp.view.CustomListView;
import com.testapp.testapp.view.PhotosView;

public class VenueDetailsActivity extends AppCompatActivity implements CustomItemView<Venue>, OnMapReadyCallback {

    private String venueId;
    private ProgressDialog progressDialog;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_details);
        setUpActivityTitle();
        initMap();

        venueId = getIntent().getStringExtra(Constants.INTENT_VENUE_ID);
        Presenter venueDetailsPresenter = new VenueDetailsPresenter(this, venueId);
        venueDetailsPresenter.getResponse();

        getPhotosFromServer();
        getTipsFromServer();
    }

    private void initMap() {
        MapFragment mapFragment = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment));
        mapFragment.getMapAsync(this);
    }

    private void setUpActivityTitle() {
        String title = getIntent().getStringExtra(Constants.INTENT_TITLE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(title);
        }
    }

    private void getPhotosFromServer() {
        CustomListView<Photo> photoView = new PhotosView(this);

        Presenter photoPresenter = new PhotosPresenter(photoView, venueId);

        photoPresenter.getResponse();
    }

    private void getTipsFromServer() {
        ProgressBar tipsLoading = (ProgressBar) findViewById(R.id.tipsLoading);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.tipsRecyclerView);
        CommonRecyclerViewAdapter<Tip, TipAdapter.Holder> tipAdapter = new TipAdapter();

        CustomListView<Tip> tipsView = new AdapterView<>(this, tipsLoading, tipAdapter, recyclerView);

        Presenter tipsPresenter = new TipsPresenter(tipsView, venueId);

        tipsPresenter.getResponse();
    }

    @Override
    public void onStartRequest() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please, wait. Venue is loading...");
        progressDialog.show();
    }

    @Override
    public void onFailureRequest() {
        closeProgressDialog();
        Toast.makeText(this, "Can`t load venue", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEndRequest() {
        closeProgressDialog();
    }

    @Override
    public void onSuccessResponse(Venue venue) {
        TextView rating = (TextView) findViewById(R.id.venueRatingDetails);
        TextView name = (TextView) findViewById(R.id.venueNameDetails);
        TextView category = (TextView) findViewById(R.id.venueCategoryDetails);
        TextView description = (TextView) findViewById(R.id.venueDescriptionDetails);
        TextView address = (TextView) findViewById(R.id.venueAddressDetails);
        TextView price = (TextView) findViewById(R.id.venuePriceDetails);

        if (venue.getRating() != 0.) {
            rating.setText(String.valueOf(venue.getRating()));
            rating.setBackgroundColor(Color.parseColor("#" + venue.getRatingColor()));
        } else {
            rating.setVisibility(View.GONE);
        }
        name.setText(venue.getName());
        category.setText(venue.getPrimaryCategory().getName());
        description.setText(venue.getDescription());
        address.setText(venue.getLocation().getAddress());
        if (venue.getPrice() != null)
            price.setText(venue.getPrice().toString());
    }

    private void closeProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.venu_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        double latitude = getIntent().getDoubleExtra(Constants.INTENT_LATITUDE, 0.);
        double longitude = getIntent().getDoubleExtra(Constants.INTENT_LONGITUDE, 0.);
        int zoom = 16;

        map = googleMap;
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(latitude, longitude), zoom));
        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker)).anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(latitude, longitude)));
        map.getUiSettings().setMapToolbarEnabled(false);
    }
}
