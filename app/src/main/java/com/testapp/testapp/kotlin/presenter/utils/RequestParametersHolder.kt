package com.testapp.testapp.kotlin.presenter.utils

import com.testapp.testapp.kotlin.Network

class RequestParametersHolder private constructor(){

    private object Holder {
        val INSTANCE = RequestParametersHolder()
    }

    companion object {
        val instance: RequestParametersHolder by lazy { Holder.INSTANCE }
    }

    val requestParameters: MutableMap<String, String> = HashMap()

    init {
        requestParameters[Network.DATE_VERSION_FIELD] = DateFormatter.getCurrentDateInApiVersionFormat()
        requestParameters[Network.CLIENT_ID_FIELD] = Network.CLIENT_ID_VALUE
        requestParameters[Network.CLIENT_SECRET_FIELD] = Network.CLIENT_SECRET_VALUE
    }

    fun getSearchVenuesParamsWithLocation(longitude: Double, latitude: Double, query: String): MutableMap<String, String> {
        val searchRequestParams: MutableMap<String, String> = HashMap()
        searchRequestParams.putAll(requestParameters)
        searchRequestParams[Network.LONGITUDE_AND_LATITUDE_FIELD] = "$latitude,$longitude"
        searchRequestParams[Network.SEARCH_QUERY_FIELD] = query
        searchRequestParams[Network.API_INTENT_FIELD] = "checkin"
        return searchRequestParams
    }

    fun getSearchVenuesParamsWithoutLocation(query: String) : MutableMap<String, String> {
        val searchRequestParams: MutableMap<String, String> = HashMap()
        searchRequestParams.putAll(requestParameters)
        searchRequestParams[Network.SEARCH_QUERY_FIELD] = query
        searchRequestParams[Network.API_INTENT_FIELD] = "global"
        return searchRequestParams
    }

    fun getVenuePhotosParams(): MutableMap<String, String> {
        val venuePhotosRequestParams: MutableMap<String, String> = HashMap()
        venuePhotosRequestParams.putAll(requestParameters)
        venuePhotosRequestParams[Network.LIMIT_FIELD] = "3"
        return venuePhotosRequestParams
    }
}