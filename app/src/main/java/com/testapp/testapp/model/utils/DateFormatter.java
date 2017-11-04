package com.testapp.testapp.model.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by troll on 05.11.2017.
 */

public class DateFormatter {

    /**
     * Transform date to format for Foursquare 'v' parameter in GET requests
     * */
    public static String getCurrentDateInApiVersionFormat(){
        Date currentDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(currentDate);
    }
}
