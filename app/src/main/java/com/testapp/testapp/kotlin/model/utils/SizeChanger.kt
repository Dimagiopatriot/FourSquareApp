package com.testapp.testapp.kotlin.model.utils

class SizeChanger {

    companion object {
        fun zoomOut(oldSize: Int, scale: Int): Int = Math.round(oldSize.toDouble() / scale).toInt()

        fun zoomIn(oldSize: Int, scale: Int): Int = Math.round((oldSize * scale).toDouble()).toInt()
    }
}