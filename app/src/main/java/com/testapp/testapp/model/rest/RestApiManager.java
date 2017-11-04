package com.testapp.testapp.model.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.testapp.testapp.model.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by troll on 03.11.2017.
 */

public class RestApiManager {

    private Retrofit retrofit;

    public RestApiManager(){
        Gson gson = new GsonBuilder().create();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Constants.BASE_URL)
                .build();
    }
}
