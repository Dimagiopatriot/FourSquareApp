package com.testapp.testapp.kotlin.model.utils

import com.testapp.testapp.Constants
import java.util.*

class DistanceFormatter {

    companion object {
        fun fromMetersToKilometersFormat(distanceInMeters: Int): String {
            val precise = 10
            var distanceInKilometers: Double = distanceInMeters.toDouble() / Constants.UI.KILOMETER_IN_METERS
            distanceInKilometers *= precise
            val roundedDistance: Int = Math.round(distanceInKilometers).toInt()
            distanceInKilometers = roundedDistance.toDouble() / precise
            return String.format(Locale.US, "%.1f", distanceInKilometers)
        }
    }
}