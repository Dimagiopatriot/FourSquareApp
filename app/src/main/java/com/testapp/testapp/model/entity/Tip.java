package com.testapp.testapp.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by troll on 04.11.2017.
 */

public class Tip {

    @SerializedName("text")
    private String tipText;
    @SerializedName("photo")
    private Photo photo;
    @SerializedName("likes")
    private Like tipLikes;
    @SerializedName("user")
    private User user;

    class Like {
        @SerializedName("count")
        private int count;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Like like = (Like) o;

            return count == like.count;
        }

        @Override
        public int hashCode() {
            return count;
        }

        @Override
        public String toString() {
            return "Like{" +
                    "count=" + count +
                    '}';
        }
    }
}
