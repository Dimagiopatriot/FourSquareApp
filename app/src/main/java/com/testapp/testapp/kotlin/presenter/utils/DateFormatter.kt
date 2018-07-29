package com.testapp.testapp.kotlin.presenter.utils

import java.text.SimpleDateFormat
import java.util.*

class DateFormatter {

    companion object {
        /**
         * Transform date to format for Foursquare 'v' parameter in GET requests
         * */
        fun getCurrentDateInApiVersionFormat(): String {
            val currentDate = Date()
            val simpleDateFormat = SimpleDateFormat("yyyyMMdd")
            return simpleDateFormat.format(currentDate)
        }
    }
}