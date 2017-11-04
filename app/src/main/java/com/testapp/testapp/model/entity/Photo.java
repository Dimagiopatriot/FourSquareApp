package com.testapp.testapp.model.entity;

import com.google.gson.annotations.SerializedName;
import com.testapp.testapp.model.utils.Constants;
import com.testapp.testapp.model.utils.SizeChanger;

/**
 * Created by troll on 04.11.2017.
 */

public class Photo {

    @SerializedName("prefix")
    private String urlPrefix;
    @SerializedName("suffix")
    private String urlSuffix;
    @SerializedName("width")
    private int width;
    @SerializedName("height")
    private int height;

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public void setUrlSuffix(String urlSuffix) {
        this.urlSuffix = urlSuffix;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void zoomInWidth(){
        width = SizeChanger.zoomIn(width, Constants.SCALE);
    }

    public void zoomOutWidth(){
        width = SizeChanger.zoomOut(width, Constants.SCALE);
    }

    public void zoomInHeiht(){
        height = SizeChanger.zoomIn(height, Constants.SCALE);
    }

    public void zoomOutHeiht(){
        height = SizeChanger.zoomOut(height, Constants.SCALE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (width != photo.width) return false;
        if (height != photo.height) return false;
        if (urlPrefix != null ? !urlPrefix.equals(photo.urlPrefix) : photo.urlPrefix != null)
            return false;
        return urlSuffix != null ? urlSuffix.equals(photo.urlSuffix) : photo.urlSuffix == null;
    }

    @Override
    public int hashCode() {
        int result = urlPrefix != null ? urlPrefix.hashCode() : 0;
        result = 31 * result + (urlSuffix != null ? urlSuffix.hashCode() : 0);
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }

    @Override
    public String toString() {
        return urlPrefix + width + "x" + height + urlSuffix;
    }
}