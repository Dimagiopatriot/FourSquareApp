package com.testapp.testapp.model.entity;

import com.google.gson.annotations.SerializedName;
import com.testapp.testapp.Constants;
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

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void zoomOutWidth(){
        width = SizeChanger.zoomOut(width, Constants.UI.SCALE);
    }

    public void zoomOutHeight(){
        height = SizeChanger.zoomOut(height,Constants.UI.SCALE);
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