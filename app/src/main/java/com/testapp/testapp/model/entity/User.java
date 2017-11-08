package com.testapp.testapp.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by troll on 04.11.2017.
 */

public class User {

    @SerializedName("firstName")
    private String userFirstName;
    @SerializedName("photo")
    private Photo userPhoto;

    public String getUserFirstName() {
        return userFirstName;
    }

    public Photo getUserPhoto() {
        return userPhoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userFirstName != null ? !userFirstName.equals(user.userFirstName) : user.userFirstName != null)
            return false;
        return userPhoto != null ? userPhoto.equals(user.userPhoto) : user.userPhoto == null;
    }

    @Override
    public int hashCode() {
        int result = userFirstName != null ? userFirstName.hashCode() : 0;
        result = 31 * result + (userPhoto != null ? userPhoto.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userFirstName='" + userFirstName + '\'' +
                ", userPhoto=" + userPhoto +
                '}';
    }
}
