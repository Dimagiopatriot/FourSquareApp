package com.testapp.testapp.kotlin.model.entity

import com.google.gson.annotations.SerializedName

data class Location(@SerializedName("address") val address: String,
                    @SerializedName("formattedAddress") val formattedAddress: List<String>,
                    @SerializedName("lat") val latitude: Double,
                    @SerializedName("lng") val longitude: Double,
                    @SerializedName("distance") val distance: Int)