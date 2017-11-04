package com.testapp.testapp.model.entity.response;

import com.google.gson.annotations.SerializedName;
import com.testapp.testapp.model.entity.Photo;
import com.testapp.testapp.model.entity.Wrapper;

/**
 * Created by troll on 04.11.2017.
 */

public class ResponsePhotos {

    @SerializedName("photos")
    private Wrapper<Photo> photoWrapper;

    public Wrapper getPhotoWrapper() {
        return photoWrapper;
    }

    public void setPhotoWrapper(Wrapper<Photo> wrapper) {
        this.photoWrapper = wrapper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponsePhotos that = (ResponsePhotos) o;

        return photoWrapper != null ? photoWrapper.equals(that.photoWrapper) : that.photoWrapper == null;
    }

    @Override
    public int hashCode() {
        return photoWrapper != null ? photoWrapper.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ResponsePhotos{" +
                "photoWrapper=" + photoWrapper +
                '}';
    }
}
