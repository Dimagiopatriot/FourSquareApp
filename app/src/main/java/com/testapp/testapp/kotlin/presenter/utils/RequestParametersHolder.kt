package com.testapp.testapp.kotlin.presenter.utils

import com.testapp.testapp.Constants.Network.*

class RequestParametersHolder private constructor(){

    private object Holder {
        val INSTANCE = RequestParametersHolder()
    }

    companion object {
        val instance: RequestParametersHolder by lazy { Holder.INSTANCE }
    }

    val requestParameters: MutableMap<String, String> = HashMap()

    init {
        requestParameters[DATE_VERSION_FIELD] = DateFormatter.getCurrentDateInApiVersionFormat()
        requestParameters[CLIENT_ID_FIELD] = CLIENT_ID_VALUE
        requestParameters[CLIENT_SECRET_FIELD] = CLIENT_SECRET_VALUE
    }

    fun getSearchVenuesParamsWithLocation(longitude: Double, latitude: Double, query: String): MutableMap<String, String> {
        val searchRequestParams: MutableMap<String, String> = HashMap()
        searchRequestParams.putAll(requestParameters)
        searchRequestParams[LONGITUDE_AND_LATITUDE_FIELD] = "$latitude,$longitude"
        searchRequestParams[SEARCH_QUERY_FIELD] = query
        searchRequestParams[API_INTENT_FIELD] = "checkin"
        return searchRequestParams
    }

    fun getSearchVenuesParamsWithoutLocation(query: String) : MutableMap<String, String> {
        val searchRequestParams: MutableMap<String, String> = HashMap()
        searchRequestParams.putAll(requestParameters)
        searchRequestParams[SEARCH_QUERY_FIELD] = query
        searchRequestParams[API_INTENT_FIELD] = "global"
        return searchRequestParams
    }

    fun getVenuePhotosParams(): MutableMap<String, String> {
        val venuePhotosRequestParams: MutableMap<String, String> = HashMap()
        venuePhotosRequestParams.putAll(requestParameters)
        venuePhotosRequestParams[LIMIT_FIELD] = "3"
        return venuePhotosRequestParams
    }
}