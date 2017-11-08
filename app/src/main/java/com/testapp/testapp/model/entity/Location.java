package com.testapp.testapp.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by troll on 04.11.2017.
 */

public class Location {

    @SerializedName("address")
    private String address;
    @SerializedName("formattedAddress")
    private List<String> formattedAddress;
    @SerializedName("lat")
    private double latitude;
    @SerializedName("lng")
    private double longitude;
    @SerializedName("distance")
    private int distance;

    public String getAddress() {
        if (address == null) {
            return formattedAddress.get(0);
        }
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (Double.compare(location.latitude, latitude) != 0) return false;
        if (Double.compare(location.longitude, longitude) != 0) return false;
        if (distance != location.distance) return false;
        if (address != null ? !address.equals(location.address) : location.address != null)
            return false;
        return formattedAddress != null ? formattedAddress.equals(location.formattedAddress) : location.formattedAddress == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = address != null ? address.hashCode() : 0;
        result = 31 * result + (formattedAddress != null ? formattedAddress.hashCode() : 0);
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + distance;
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "address='" + address + '\'' +
                ", formattedAddress=" + formattedAddress +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", distance=" + distance +
                '}';
    }
}
