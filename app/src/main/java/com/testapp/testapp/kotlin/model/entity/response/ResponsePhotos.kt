package com.testapp.testapp.kotlin.model.entity.response

import com.google.gson.annotations.SerializedName
import com.testapp.testapp.kotlin.model.entity.Photo
import com.testapp.testapp.kotlin.model.entity.Wrapper

data class ResponsePhotos(@SerializedName("photos") val photoWrapper: Wrapper<Photo>)