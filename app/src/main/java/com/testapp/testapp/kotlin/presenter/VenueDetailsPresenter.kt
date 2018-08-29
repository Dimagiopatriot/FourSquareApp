package com.testapp.testapp.kotlin.presenter


import com.testapp.testapp.kotlin.model.entity.Venue
import com.testapp.testapp.kotlin.model.entity.response.Response
import com.testapp.testapp.kotlin.model.entity.response.ResponseVenue
import com.testapp.testapp.kotlin.model.rest.RestApiManager
import com.testapp.testapp.kotlin.presenter.utils.RequestParametersHolder
import com.testapp.testapp.kotlin.view.CustomView
import retrofit2.Call
import retrofit2.Callback

class VenueDetailsPresenter(val view: CustomView<Venue?>, val venueId: String): Presenter, Callback<Response<ResponseVenue>> {
    override fun getResponse() {
        val restApiManager = RestApiManager.instance
        val responseCall = restApiManager.getVenueApi()
                ?.venueDetails(venueId, RequestParametersHolder.instance.requestParameters)
        responseCall?.enqueue(this)
        view.onStartRequest()
    }

    override fun onFailure(call: Call<Response<ResponseVenue>>?, t: Throwable?) {
        view.onFailureRequest()
    }

    override fun onResponse(call: Call<Response<ResponseVenue>>?, response: retrofit2.Response<Response<ResponseVenue>>?) {
        val commonResponse = response?.body()
        val responseVenue = commonResponse?.response?.venue
        responseVenue?.setPrimaryCategory()
        view.onSuccessResponse(responseVenue)
        view.onEndRequest()
    }

}