package com.testapp.testapp.kotlin.model.entity

import com.google.gson.annotations.SerializedName
import com.testapp.testapp.Constants
import com.testapp.testapp.model.utils.SizeChanger

data class Photo(@SerializedName("prefix") val urlPrefix: String,
                 @SerializedName("suffix") val urlSuffix: String,
                 @SerializedName("width") var width: Int,
                 @SerializedName("height") var height: Int) {

    fun zoomOutWidth() {
        width = SizeChanger.zoomOut(width, Constants.UI.SCALE)
    }

    fun zoomInHeight() {
        height = SizeChanger.zoomIn(height, Constants.UI.SCALE)
    }

    override fun toString(): String = urlPrefix + width + "x" + height + urlSuffix
}