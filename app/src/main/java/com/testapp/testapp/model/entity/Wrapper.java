package com.testapp.testapp.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by troll on 04.11.2017.
 */

public class Wrapper<T> {

    @SerializedName("items")
    private List<T> items;

    public List<T> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wrapper that = (Wrapper) o;

        return items != null ? items.equals(that.items) : that.items == null;
    }

    @Override
    public int hashCode() {
        return items != null ? items.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Wrapper{" +
                "items=" + items +
                '}';
    }
}
