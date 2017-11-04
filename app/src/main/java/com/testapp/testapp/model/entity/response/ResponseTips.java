package com.testapp.testapp.model.entity.response;

import com.google.gson.annotations.SerializedName;
import com.testapp.testapp.model.entity.Tip;
import com.testapp.testapp.model.entity.Wrapper;

/**
 * Created by troll on 04.11.2017.
 */

public class ResponseTips {

    @SerializedName("tips")
    private Wrapper<Tip> tipsWrapper;

    public Wrapper<Tip> getTipsWrapper() {
        return tipsWrapper;
    }

    public void setTipsWrapper(Wrapper<Tip> tipsWrapper) {
        this.tipsWrapper = tipsWrapper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponseTips that = (ResponseTips) o;

        return tipsWrapper != null ? tipsWrapper.equals(that.tipsWrapper) : that.tipsWrapper == null;
    }

    @Override
    public int hashCode() {
        return tipsWrapper != null ? tipsWrapper.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ResponseTips{" +
                "tipsWrapper=" + tipsWrapper +
                '}';
    }
}
