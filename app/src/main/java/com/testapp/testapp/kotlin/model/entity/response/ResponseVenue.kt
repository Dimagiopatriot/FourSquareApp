package com.testapp.testapp.kotlin.model.entity.response

import com.google.gson.annotations.SerializedName
import com.testapp.testapp.kotlin.model.entity.Venue

data class ResponseVenue(@SerializedName("venue") val venue: Venue)