package com.testapp.testapp.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by troll on 04.11.2017.
 */

public class Price {

    @SerializedName("tier")
    private int tier;
    @SerializedName("message")
    private String message;
    @SerializedName("currency")
    private String currency;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price = (Price) o;

        if (tier != price.tier) return false;
        if (message != null ? !message.equals(price.message) : price.message != null) return false;
        return currency != null ? currency.equals(price.currency) : price.currency == null;
    }

    @Override
    public int hashCode() {
        int result = tier;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return message + " " + currency;
    }
}
