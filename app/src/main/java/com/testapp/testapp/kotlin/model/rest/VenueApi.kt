package com.testapp.testapp.kotlin.model.rest

import com.testapp.testapp.kotlin.model.entity.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface VenueApi {

    @GET("venues/search")
    fun searchVenues(@QueryMap params: Map<String, String>) : Call<Response<ResponseSearchVenues>>

    @GET("venues/{venueId}")
    fun venueDetails(@Path("venueId") id: String, @QueryMap params: Map<String, String>) : Call<Response<ResponseVenue>>

    @GET("venues/{venueId}/photos")
    fun venuePhotos(@Path("venueId") id: String, @QueryMap params: Map<String, String>) : Call<Response<ResponsePhotos>>

    @GET("venues/{venueId}/tips")
    fun venueTips(@Path("venueId") id: String, @QueryMap params: Map<String, String>) : Call<Response<ResponseTips>>
}