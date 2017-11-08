package com.testapp.testapp.model.entity.response;

import com.google.gson.annotations.SerializedName;
import com.testapp.testapp.model.entity.Venue;

/**
 * Created by troll on 04.11.2017.
 */

public class ResponseVenue {

    @SerializedName("venue")
    private Venue venue;

    public Venue getVenue() {
        return venue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponseVenue that = (ResponseVenue) o;

        return venue != null ? venue.equals(that.venue) : that.venue == null;
    }

    @Override
    public int hashCode() {
        return venue != null ? venue.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ResponseVenue{" +
                "venue=" + venue +
                '}';
    }
}
