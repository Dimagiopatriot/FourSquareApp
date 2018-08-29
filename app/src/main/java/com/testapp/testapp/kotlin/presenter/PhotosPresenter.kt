package com.testapp.testapp.kotlin.presenter

import com.testapp.testapp.kotlin.model.entity.Photo
import com.testapp.testapp.kotlin.model.entity.response.Response
import com.testapp.testapp.kotlin.model.entity.response.ResponsePhotos
import com.testapp.testapp.kotlin.model.rest.RestApiManager
import com.testapp.testapp.kotlin.presenter.utils.RequestParametersHolder
import com.testapp.testapp.kotlin.view.CustomListView
import retrofit2.Call
import retrofit2.Callback

class PhotosPresenter(val viewForList: CustomListView<Photo>, val venueId: String) : Presenter, Callback<Response<ResponsePhotos>> {

    override fun getResponse() {
        val restApiManager = RestApiManager.instance
        val responseCall = restApiManager.getVenueApi()
                ?.venuePhotos(venueId, RequestParametersHolder.instance.getVenuePhotosParams())
        responseCall?.enqueue(this)
    }

    override fun onFailure(call: Call<Response<ResponsePhotos>>?, t: Throwable?) {
        viewForList.onFailureRequest()
    }

    override fun onResponse(call: Call<Response<ResponsePhotos>>?, response: retrofit2.Response<Response<ResponsePhotos>>?) {
        val commonResponse = response?.body()
        val responsePhotos = commonResponse?.response
        viewForList.onSuccessResponse(responsePhotos?.photoWrapper!!.items)
    }
}