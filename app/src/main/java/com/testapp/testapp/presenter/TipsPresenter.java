package com.testapp.testapp.presenter;

import android.support.annotation.NonNull;

import com.testapp.testapp.model.entity.Tip;
import com.testapp.testapp.model.entity.response.Response;
import com.testapp.testapp.model.entity.response.ResponseTips;
import com.testapp.testapp.model.rest.RestApiManager;
import com.testapp.testapp.presenter.utils.RequestParametersHolder;
import com.testapp.testapp.view.CustomListView;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by troll on 06.11.2017.
 */

public class TipsPresenter implements Presenter, Callback<Response<ResponseTips>> {

    private CustomListView<Tip> viewForList;

    private String venueId;

    public TipsPresenter(CustomListView<Tip> viewForList, String venueId) {
        this.viewForList = viewForList;
        this.venueId = venueId;
    }

    @Override
    public void getResponse() {
        RestApiManager restApiManager = RestApiManager.getInstance();
        Call<Response<ResponseTips>> responseCall = restApiManager
                .getVenueApi()
                .venueTips(venueId, RequestParametersHolder.getInstance().getCommonRequestParams());
        viewForList.onStartRequest();
        responseCall.enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<Response<ResponseTips>> call, @NonNull retrofit2.Response<Response<ResponseTips>> response) {
        Response<ResponseTips> commonResponse = response.body();
        if (commonResponse != null) {
            ResponseTips responseTips = commonResponse.getResponse();
            viewForList.onSuccessResponse(responseTips.getTipsWrapper().getItems());
        }
        viewForList.onEndRequest();
    }

    @Override
    public void onFailure(@NonNull Call<Response<ResponseTips>> call, @NonNull Throwable t) {
        viewForList.onFailureRequest();
    }
}
