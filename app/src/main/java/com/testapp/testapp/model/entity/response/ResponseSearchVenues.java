package com.testapp.testapp.model.entity.response;

import com.google.gson.annotations.SerializedName;
import com.testapp.testapp.model.entity.Venue;

import java.util.List;

/**
 * Created by troll on 04.11.2017.
 */

public class ResponseSearchVenues {

    @SerializedName("venues")
    private List<Venue> venues;

    public List<Venue> getVenues() {
        return venues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponseSearchVenues that = (ResponseSearchVenues) o;

        return venues != null ? venues.equals(that.venues) : that.venues == null;
    }

    @Override
    public int hashCode() {
        return venues != null ? venues.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ResponseSearchVenues{" +
                "venues=" + venues +
                '}';
    }
}
