package com.testapp.testapp.kotlin.model.entity

import com.google.gson.annotations.SerializedName

data class Wrapper<T>(@SerializedName("items") val items: List<T>)