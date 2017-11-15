package com.testapp.testapp;

/**
 * Created by troll on 03.11.2017.
 */

public interface Constants {

    interface Network {
        String BASE_URL = "https://api.foursquare.com/v2/";
        String CLIENT_ID_VALUE = "DA2YIVUIZ5TUFRLEXNWZR210TFJRNY2CVK5PEHEZZWS2PSSS";
        String CLIENT_SECRET_VALUE = "PP4ZMJF04X3GXHSTXGYYVKRA3PUPWI00ZUG2PLZS3JPSGJZJ";

        String DATE_VERSION_FIELD = "v";
        String LONGITUDE_AND_LATITUDE_FIELD = "ll";
        String API_INTENT_FIELD = "intent";
        String SEARCH_QUERY_FIELD = "query";
        String LIMIT_FIELD = "limit";
        String CLIENT_ID_FIELD = "client_id";
        String CLIENT_SECRET_FIELD = "client_secret";
    }

    interface System {
        String INTENT_TITLE = "title";
        String INTENT_VENUE_ID = "venueId";
        String INTENT_LATITUDE = "latitude";
        String INTENT_LONGITUDE = "longitude";
    }

    interface UI {
        int ICON_SIZE = 100;
        int SCALE = 10;
        int KILOMETER_IN_METERS = 1000;
    }
}
