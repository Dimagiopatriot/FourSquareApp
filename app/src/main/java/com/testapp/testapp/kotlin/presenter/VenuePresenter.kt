package com.testapp.testapp.kotlin.presenter

import android.location.Location
import android.util.Log
import com.testapp.testapp.kotlin.model.entity.Venue
import com.testapp.testapp.kotlin.model.entity.response.Response
import com.testapp.testapp.kotlin.model.entity.response.ResponseSearchVenues
import com.testapp.testapp.kotlin.model.rest.RestApiManager
import com.testapp.testapp.kotlin.presenter.utils.RequestParametersHolder
import com.testapp.testapp.view.CustomListView
import retrofit2.Call
import retrofit2.Callback

class VenuePresenter(private val view: CustomListView<Venue>,
                     private val lastKnownLocation: Location?,
                     private val query: String) : Callback<Response<ResponseSearchVenues>>, Presenter {



    override fun onFailure(call: Call<Response<ResponseSearchVenues>>?, t: Throwable?) {
        view.onFailureRequest()
        Log.e("error response", t?.message)
    }

    override fun onResponse(call: Call<Response<ResponseSearchVenues>>?, response: retrofit2.Response<Response<ResponseSearchVenues>>?) {
        val commonResponse = response!!.body()
        if (commonResponse != null) {
            val responseSearchVenues = commonResponse.response
            val responseVenues = responseSearchVenues.venues

            view.onSuccessResponse(responseVenues)
        }
        view.onEndRequest()
    }

    override fun getResponse() {
        val restApiManager = RestApiManager.instance
        val venuesResponseCall = restApiManager.getVenueApi()
                ?.searchVenues(checkLastKnownLocation(query))

        view.onStartRequest()
        venuesResponseCall?.enqueue(this)
    }

    private fun checkLastKnownLocation(query: String): Map<String, String> {
        return if (lastKnownLocation == null) {
            RequestParametersHolder.instance.getSearchVenuesParamsWithoutLocation(query)
        } else {
            RequestParametersHolder.instance.getSearchVenuesParamsWithLocation(lastKnownLocation.longitude,
                    lastKnownLocation.latitude, query)
        }
    }

    private fun setPrimaryCategoryForVenues(venues: List<Venue>) {
        venues.forEach { venue -> venue.setPrimaryCategory()}
    }
}