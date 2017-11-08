package com.testapp.testapp.model.rest;

import com.testapp.testapp.model.entity.response.Response;
import com.testapp.testapp.model.entity.response.ResponsePhotos;
import com.testapp.testapp.model.entity.response.ResponseTips;
import com.testapp.testapp.model.entity.response.ResponseVenue;
import com.testapp.testapp.model.entity.response.ResponseSearchVenues;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by troll on 03.11.2017.
 */

public interface VenueApi {

    @GET("venues/search")
    Call<Response<ResponseSearchVenues>> searchVenues(@QueryMap Map<String, String> params);

    @GET("venues/{venueId}")
    Call<Response<ResponseVenue>> venueDetails(@Path("venueId") String id, @QueryMap Map<String, String> params);

    @GET("venues/{venueId}/photos")
    Call<Response<ResponsePhotos>> venuePhotos(@Path("venueId") String id, @QueryMap Map<String, String> params);

    @GET("venues/{venueId}/tips")
    Call<Response<ResponseTips>> venueTips(@Path("venueId") String id, @QueryMap Map<String, String> params);
}
