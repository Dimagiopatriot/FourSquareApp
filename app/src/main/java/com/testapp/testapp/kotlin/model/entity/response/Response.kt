package com.testapp.testapp.kotlin.model.entity.response

import com.google.gson.annotations.SerializedName

data class Response<T>(@SerializedName("meta") val meta: Meta,
                       @SerializedName("response") val response: T)

data class Meta(@SerializedName("code") val responseCode: Int)