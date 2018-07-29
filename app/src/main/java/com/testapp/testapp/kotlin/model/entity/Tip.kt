package com.testapp.testapp.kotlin.model.entity

import com.google.gson.annotations.SerializedName

data class Tip(@SerializedName("text") val tipText: String,
               @SerializedName("photo") val photo: Photo,
               @SerializedName("likes") val tipLikes: Likes,
               @SerializedName("user") val user: User)

data class Likes(@SerializedName("count") val count: Int)