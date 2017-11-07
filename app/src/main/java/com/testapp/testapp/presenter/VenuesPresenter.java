package com.testapp.testapp.presenter;

import android.location.Location;
import android.support.annotation.NonNull;

import com.testapp.testapp.model.entity.response.Response;
import com.testapp.testapp.model.entity.response.ResponseSearchVenues;
import com.testapp.testapp.model.rest.RestApiManager;
import com.testapp.testapp.presenter.utils.RequestParametersHolder;
import com.testapp.testapp.view.CustomListView;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by troll on 06.11.2017.
 */

public class VenuesPresenter implements Callback<Response<ResponseSearchVenues>>, Presenter {

    private CustomListView view;

    private Location lastKnownLocation;
    private String query;

    public VenuesPresenter(CustomListView view, Location lastKnownLocation, String query) {
        this.view = view;
        this.lastKnownLocation = lastKnownLocation;
        this.query = query;
    }

    @Override
    public void getResponse() {
        Call<Response<ResponseSearchVenues>> venuesResponseCall = new RestApiManager()
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
            view.onSuccessResponse(responseSearchVenues.getVenues());
        }
        view.onEndRequest();
    }

    @Override
    public void onFailure(@NonNull Call<Response<ResponseSearchVenues>> call, @NonNull Throwable t) {
        view.onFailureRequest();
    }
}
