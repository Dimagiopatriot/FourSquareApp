package com.testapp.testapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.testapp.testapp.kotlin.model.entity.Venue
import com.testapp.testapp.kotlin.view.CustomView

import com.testapp.testapp.kotlin.System
import com.testapp.testapp.kotlin.presenter.PhotosPresenter
import com.testapp.testapp.kotlin.view.PhotosView

/**
 * Created by dmitriysmishnyi on 29.08.18.
 */
class KotlinVenueDetailsActivity : AppCompatActivity(), CustomView<Venue>, OnMapReadyCallback {

    private var venueId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_venue_details)
        setUpActivityTitle()
        initMap()

        venueId = intent.getStringExtra(System.INTENT_VENUE_ID)
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
    override fun onStartRequest() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailureRequest() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onEndRequest() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccessResponse(data: Venue) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMapReady(p0: GoogleMap?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}