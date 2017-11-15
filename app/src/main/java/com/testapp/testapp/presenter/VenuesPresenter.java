package com.testapp.testapp.presenter;

import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;

import com.testapp.testapp.model.entity.Venue;
import com.testapp.testapp.model.entity.response.Response;
import com.testapp.testapp.model.entity.response.ResponseSearchVenues;
import com.testapp.testapp.model.rest.RestApiManager;
import com.testapp.testapp.presenter.utils.RequestParametersHolder;
import com.testapp.testapp.view.CustomListView;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by troll on 06.11.2017.
 */

public class VenuesPresenter implements Callback<Response<ResponseSearchVenues>>, Presenter {

    private CustomListView<Venue> view;

    private Location lastKnownLocation;
    private String query;

    public VenuesPresenter(CustomListView<Venue> view, Location lastKnownLocation, String query) {
        this.view = view;
        this.lastKnownLocation = lastKnownLocation;
        this.query = query;
    }

    @Override
    public void getResponse() {
        RestApiManager restApiManager = RestApiManager.getInstance();
        Call<Response<ResponseSearchVenues>> venuesResponseCall = restApiManager
                .getVenueApi()
                .searchVenues(checkLastKnownLocation(query));

        view.onStartRequest();
        venuesResponseCall.enqueue(this);
    }

    private Map<String, String> checkLastKnownLocation(String query) {
        if (lastKnownLocation == null) {
            return RequestParametersHolder.getInstance().getSearchVenuesParamsWithoutLocation(query);
        } else {
            return RequestParametersHolder.getInstance().getSearchVenuesParamsWithLocation(lastKnownLocation.getLongitude(),
                    lastKnownLocation.getLatitude(), query);
        }
    }

    @Override
    public void onResponse(@NonNull Call<Response<ResponseSearchVenues>> call, @NonNull retrofit2.Response<Response<ResponseSearchVenues>> response) {
        Response<ResponseSearchVenues> commonResponse = response.body();
        if (commonResponse != null) {
            ResponseSearchVenues responseSearchVenues = commonResponse.getResponse();
            List<Venue> responseVenues = responseSearchVenues.getVenues();
            setPrimaryCategoryForVenues(responseVenues);
            view.onSuccessResponse(responseVenues);
        }
        view.onEndRequest();
    }

    private void setPrimaryCategoryForVenues(@NonNull List<Venue> venues) {
        for (Venue venue : venues) {
            venue.setPrimaryCategory();
        }
    }

    @Override
    public void onFailure(@NonNull Call<Response<ResponseSearchVenues>> call, @NonNull Throwable t) {
        view.onFailureRequest();
        Log.e("error response", t.getMessage());
    }
}
