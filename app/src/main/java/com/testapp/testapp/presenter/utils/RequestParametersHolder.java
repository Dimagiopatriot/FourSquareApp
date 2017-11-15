package com.testapp.testapp.presenter.utils;

import java.util.HashMap;
import java.util.Map;

import static com.testapp.testapp.Constants.Network.*;

/**
 * Created by troll on 05.11.2017.
 */

public class RequestParametersHolder {

    private Map<String, String> requestParameters;

    RequestParametersHolder(){
        requestParameters = new HashMap<>();
        requestParameters.put(DATE_VERSION_FIELD, DateFormatter.getCurrentDateInApiVersionFormat());
        requestParameters.put(CLIENT_ID_FIELD, CLIENT_ID_VALUE);
        requestParameters.put(CLIENT_SECRET_FIELD, CLIENT_SECRET_VALUE);
    }

    private static class Holder{
        static final RequestParametersHolder INSTANCE = new RequestParametersHolder();
    }

    public static RequestParametersHolder getInstance(){
        return Holder.INSTANCE;
    }

    public Map<String, String> getCommonRequestParams(){
        return requestParameters;
    }

    public Map<String, String> getSearchVenuesParamsWithLocation(double longitude, double latitude, String query){
        Map<String, String> searchRequestParams = new HashMap<>();
        searchRequestParams.putAll(requestParameters);
        searchRequestParams.put(LONGITUDE_AND_LATITUDE_FIELD, String.valueOf(latitude) + "," + String.valueOf(longitude));
        searchRequestParams.put(SEARCH_QUERY_FIELD, query);
        searchRequestParams.put(API_INTENT_FIELD, "checkin");
        return searchRequestParams;
    }

    public Map<String, String> getSearchVenuesParamsWithoutLocation(String query){
        Map<String, String> searchRequestParams = new HashMap<>();
        searchRequestParams.putAll(requestParameters);
        searchRequestParams.put(SEARCH_QUERY_FIELD, query);
        searchRequestParams.put(API_INTENT_FIELD, "global");
        return searchRequestParams;
    }

    public Map<String, String> getVenuePhotosParams(){
        Map<String, String> venuePhotosRequestParams = new HashMap<>();
        venuePhotosRequestParams.putAll(requestParameters);
        venuePhotosRequestParams.put(LIMIT_FIELD, String.valueOf(3));
        return venuePhotosRequestParams;
    }
}
