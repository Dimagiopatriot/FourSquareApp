package com.testapp.testapp.kotlin.model.entity

import com.google.gson.annotations.SerializedName

data class Category(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("primary") val primary: Boolean,
        @SerializedName("icon") val icon: Icon)


data class Icon(@SerializedName("prefix") private val urlPrefix : String,
                @SerializedName("suffix") private val urlSuffix: String)