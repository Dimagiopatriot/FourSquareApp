package com.testapp.testapp.kotlin.model.entity

import com.google.gson.annotations.SerializedName

data class Price(@SerializedName("tier") val tier: Int,
                 @SerializedName("message") val message: String,
                 @SerializedName("currency") val currency: String) {

    override fun toString(): String = "$message $currency"
}