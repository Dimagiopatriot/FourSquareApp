package com.testapp.testapp.kotlin.model.rest

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.testapp.testapp.kotlin.Network
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

class RestApiManager private constructor() {

    private object Holder {
        val INSTANCE = RestApiManager()
    }

    companion object {
        val instance: RestApiManager by lazy { Holder.INSTANCE }
    }

    private var retrofit: Retrofit? = null
    private var venueApi: VenueApi? = null

    init {
        val gson: Gson = GsonBuilder().create()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .baseUrl(Network.BASE_URL)
                .build()
    }

    fun getVenueApi(): VenueApi? {
        if (venueApi == null) {
            venueApi = retrofit?.create(VenueApi::class.java)
        }
        return venueApi
    }
}