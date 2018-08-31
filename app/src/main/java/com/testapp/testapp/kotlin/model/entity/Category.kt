package com.testapp.testapp.kotlin.model.entity

import com.google.gson.annotations.SerializedName
import com.testapp.testapp.kotlin.UI

data class Category(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("primary") val primary: Boolean,
        @SerializedName("icon") val icon: Icon)


data class Icon(@SerializedName("prefix") private val urlPrefix: String,
                @SerializedName("suffix") private val urlSuffix: String) {
    override fun toString(): String {
        return "$urlPrefix${UI.ICON_SIZE}$urlSuffix"
    }
}