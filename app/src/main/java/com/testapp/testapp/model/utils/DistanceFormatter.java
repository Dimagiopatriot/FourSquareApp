package com.testapp.testapp.model.utils;

import java.util.Locale;

/**
 * Created by troll on 05.11.2017.
 */

public class DistanceFormatter {

    public static String fromMetersToKilometersFormat(int distanceInMeters){
        int precise = 10;
        double distanceInKilometers = (double) distanceInMeters / Constants.KILOMETER_IN_METERS;
        distanceInKilometers = distanceInKilometers * precise;
        int roundedDistance = (int) Math.round(distanceInKilometers);
        distanceInKilometers = (double) roundedDistance / precise;
        return String.format(Locale.US,"%.1f", distanceInKilometers);
    }
}
