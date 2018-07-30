package com.testapp.testapp.kotlin.presenter

import com.testapp.testapp.kotlin.model.entity.Tip
import com.testapp.testapp.kotlin.model.entity.response.Response
import com.testapp.testapp.kotlin.model.entity.response.ResponseTips
import com.testapp.testapp.kotlin.model.rest.RestApiManager
import com.testapp.testapp.kotlin.presenter.utils.RequestParametersHolder
import com.testapp.testapp.view.CustomListView

import retrofit2.Call
import retrofit2.Callback

class TipsPresenter(private val viewForList: CustomListView<Tip>, val venueId: String) : Presenter, Callback<Response<ResponseTips>> {
    override fun onFailure(call: Call<Response<ResponseTips>>?, t: Throwable?) {
        viewForList.onFailureRequest()
    }

    override fun onResponse(call: Call<Response<ResponseTips>>?, response: retrofit2.Response<Response<ResponseTips>>?) {
        val commonResponse = response?.body()
        val responseTips = commonResponse?.response
        viewForList.onSuccessResponse(responseTips?.tipsWrapper?.items)
        viewForList.onEndRequest()
    }

    override fun getResponse() {
        val restApiManager = RestApiManager.instance
        val responseCall = restApiManager.getVenueApi()
                ?.venueTips(venueId, RequestParametersHolder.instance.requestParameters)
        viewForList.onStartRequest()
        responseCall?.enqueue(this)
    }
}