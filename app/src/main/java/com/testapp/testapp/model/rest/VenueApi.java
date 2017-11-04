package com.testapp.testapp.model.rest;

import com.testapp.testapp.model.entity.Venue;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by troll on 03.11.2017.
 */

public interface VenueApi {

    @GET("/venues/search")
    List<Venue> searchVenues(@QueryMap String params);
}
