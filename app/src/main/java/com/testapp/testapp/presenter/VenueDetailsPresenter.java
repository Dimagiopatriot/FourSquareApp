package com.testapp.testapp.presenter;

import android.support.annotation.NonNull;

import com.testapp.testapp.model.entity.Venue;
import com.testapp.testapp.model.entity.response.Response;
import com.testapp.testapp.model.entity.response.ResponseVenue;
import com.testapp.testapp.model.rest.RestApiManager;
import com.testapp.testapp.presenter.utils.RequestParametersHolder;
import com.testapp.testapp.view.CustomItemView;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by troll on 07.11.2017.
 */

public class VenueDetailsPresenter implements Presenter, Callback<Response<ResponseVenue>> {

    private CustomItemView<Venue> view;
    private String venueId;

    public VenueDetailsPresenter(CustomItemView<Venue> view, String venueId) {
        this.view = view;
        this.venueId = venueId;
    }

    @Override
    public void getResponse() {
        Call<Response<ResponseVenue>> call = new RestApiManager()
                .getVenueApi()
                .venueDetails(venueId, RequestParametersHolder.getInstance().getCommonRequestParams());
        call.enqueue(this);
        view.onStartRequest();
    }

    @Override
    public void onResponse(@NonNull Call<Response<ResponseVenue>> call, @NonNull retrofit2.Response<Response<ResponseVenue>> response) {
        Response<ResponseVenue> commonResponse = response.body();
        if (commonResponse != null) {
            Venue responseVenue = commonResponse.getResponse().getVenue();
            responseVenue.setPrimaryCategory();
            view.onSuccessResponse(responseVenue);
        }
        view.onEndRequest();
    }

    @Override
    public void onFailure(@NonNull Call<Response<ResponseVenue>> call, @NonNull Throwable t) {
        view.onFailureRequest();
    }
}
