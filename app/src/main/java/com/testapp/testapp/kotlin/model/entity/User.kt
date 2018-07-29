package com.testapp.testapp.kotlin.model.entity

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("firstName") val userFirstName: String,
                @SerializedName("photo") val userPhoto: Photo)