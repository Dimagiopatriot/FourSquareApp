package com.testapp.testapp.presenter;

import android.support.annotation.NonNull;

import com.testapp.testapp.model.entity.Photo;
import com.testapp.testapp.model.entity.response.Response;
import com.testapp.testapp.model.entity.response.ResponsePhotos;
import com.testapp.testapp.model.rest.RestApiManager;
import com.testapp.testapp.presenter.utils.RequestParametersHolder;
import com.testapp.testapp.view.CustomListView;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by troll on 07.11.2017.
 */

public class PhotosPresenter implements Presenter, Callback<Response<ResponsePhotos>> {

    private CustomListView<Photo> viewForList;
    private String venueId;

    public PhotosPresenter(CustomListView<Photo> viewForList, String venueId) {
        this.viewForList = viewForList;
        this.venueId = venueId;
    }

    @Override
    public void getResponse() {
        Call<Response<ResponsePhotos>> responseCall = new RestApiManager()
                .getVenueApi()
                .venuePhotos(venueId, RequestParametersHolder.getInstance().getVenuePhotosParams());
        responseCall.enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<Response<ResponsePhotos>> call, @NonNull retrofit2.Response<Response<ResponsePhotos>> response) {
        Response<ResponsePhotos> commonResponse = response.body();
        if (commonResponse != null){
            ResponsePhotos responsePhotos = commonResponse.getResponse();
            viewForList.onSuccessResponse(responsePhotos.getPhotoWrapper().getItems());
        }
    }

    @Override
    public void onFailure(@NonNull Call<Response<ResponsePhotos>> call, @NonNull Throwable t) {
        viewForList.onFailureRequest();
    }
}
