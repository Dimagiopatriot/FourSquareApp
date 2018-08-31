package com.testapp.testapp

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.testapp.testapp.kotlin.model.entity.Venue
import com.testapp.testapp.kotlin.view.CustomView

import com.testapp.testapp.kotlin.System
import com.testapp.testapp.kotlin.model.entity.Tip
import com.testapp.testapp.kotlin.presenter.PhotosPresenter
import com.testapp.testapp.kotlin.presenter.TipsPresenter
import com.testapp.testapp.kotlin.presenter.VenueDetailsPresenter
import com.testapp.testapp.kotlin.presenter.adapter.TipAdapter
import com.testapp.testapp.kotlin.view.AdapterView
import com.testapp.testapp.kotlin.view.CustomListView
import com.testapp.testapp.kotlin.view.PhotosView

/**
 * Created by dmitriysmishnyi on 29.08.18.
 */
class KotlinVenueDetailsActivity : AppCompatActivity(), CustomView<Venue?>, OnMapReadyCallback {

    private var venueId: String? = null
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_venue_details)
        setUpActivityTitle()
        initMap()

        venueId = intent.getStringExtra(System.INTENT_VENUE_ID)
        val venueDetailsPresenter = VenueDetailsPresenter(this, venueId)
        venueDetailsPresenter.getResponse()

        getPhotosFromServer()
        getTipsFromServer()
    }

    private fun setUpActivityTitle() {
        val title = intent.getStringExtra(System.INTENT_TITLE)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title
    }

    private fun initMap() {
        val mapFragment = fragmentManager.findFragmentById(R.id.mapFragment) as MapFragment
        mapFragment.getMapAsync(this)
    }

    private fun getPhotosFromServer() {
        val photoView = PhotosView(this)
        val photoPresenter = PhotosPresenter(photoView, venueId!!)

        photoPresenter.getResponse()
    }

    private fun getTipsFromServer() {
        val tipsLoading = findViewById<ProgressBar>(R.id.tipsLoading)
        val recyclerView = findViewById<RecyclerView>(R.id.tipsRecyclerView)
        val tipAdapter = TipAdapter()
        val tipsView: CustomListView<Tip> = AdapterView(this, tipsLoading, tipAdapter, recyclerView)
        val tipPresenter = TipsPresenter(tipsView, venueId!!)
        tipPresenter.getResponse()
    }

    override fun onStartRequest() {
        progressDialog = ProgressDialog(this)
        progressDialog?.setTitle("Loading...")
        progressDialog?.setMessage("Please, wait. Venue is loading...")
        progressDialog?.show()
    }

    override fun onFailureRequest() {
        closeProgressDialog()
        Toast.makeText(this, "Can`t load venue", Toast.LENGTH_SHORT).show()
    }

    override fun onEndRequest() {
        closeProgressDialog()
    }

    override fun onSuccessResponse(data: Venue?) {
        val rating = findViewById<TextView>(R.id.venueRatingDetails)
        val name = findViewById<TextView>(R.id.venueNameDetails)
        val category = findViewById<TextView>(R.id.venueCategoryDetails)
        val description = findViewById<TextView>(R.id.venueDescriptionDetails)
        val address = findViewById<TextView>(R.id.venueAddressDetails)
        val price = findViewById<TextView>(R.id.venuePriceDetails)

        if (data?.rating != 0.0) {
            rating?.text = data?.rating.toString()
            rating?.setBackgroundColor(Color.parseColor("#${data?.ratingColor}"))
        } else {
            rating.visibility = View.GONE
        }

        name?.text = data?.name
        category?.text = data?.primaryCategory?.name
        description?.text = data?.description
        address?.text = data?.location?.address
        price?.text = data?.price.toString()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.venu_details_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val defaultValue = 0.0
        val latitude = intent.getDoubleExtra(System.INTENT_LATITUDE, defaultValue)
        val longitude = intent.getDoubleExtra(System.INTENT_LONGITUDE, defaultValue)
        val zoom = 16

        val positionX = 0.0f
        val positionY = 1.0f
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(
                LatLng(latitude, longitude), zoom.toFloat()))
        googleMap?.addMarker(MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker)).anchor(positionX, positionY) // Anchors the marker on the bottom left
                .position(LatLng(latitude, longitude)))
        googleMap?.uiSettings?.isMapToolbarEnabled = false
    }

    private fun closeProgressDialog() {
        if (progressDialog?.isShowing == true) {
            progressDialog?.dismiss()
        }
    }

}