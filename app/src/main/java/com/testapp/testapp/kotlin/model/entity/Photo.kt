package com.testapp.testapp.kotlin.model.entity

import com.google.gson.annotations.SerializedName
import com.testapp.testapp.kotlin.UI
import com.testapp.testapp.kotlin.model.utils.SizeChanger

data class Photo(@SerializedName("prefix") val urlPrefix: String,
                 @SerializedName("suffix") val urlSuffix: String,
                 @SerializedName("width") var width: Int,
                 @SerializedName("height") var height: Int) {

    fun zoomOutWidth() {
        width = SizeChanger.zoomOut(width, UI.SCALE)
    }

    fun zoomInHeight() {
        height = SizeChanger.zoomIn(height, UI.SCALE)
    }

    override fun toString(): String = urlPrefix + width + "x" + height + urlSuffix
}