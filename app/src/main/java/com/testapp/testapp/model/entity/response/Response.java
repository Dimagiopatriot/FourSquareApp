package com.testapp.testapp.model.entity.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by troll on 04.11.2017.
 */

public class Response<T>  {

    @SerializedName("meta")
    private Meta responseMetaData;
    @SerializedName("response")
    private T response;

    public Meta getResponseMetaData() {
        return responseMetaData;
    }

    public void setResponseMetaData(Meta responseMetaData) {
        this.responseMetaData = responseMetaData;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response<?> response1 = (Response<?>) o;

        if (responseMetaData != null ? !responseMetaData.equals(response1.responseMetaData) : response1.responseMetaData != null)
            return false;
        return response != null ? response.equals(response1.response) : response1.response == null;
    }

    @Override
    public int hashCode() {
        int result = responseMetaData != null ? responseMetaData.hashCode() : 0;
        result = 31 * result + (response != null ? response.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "responseMetaData=" + responseMetaData +
                ", response=" + response +
                '}';
    }

    class Meta {
        @SerializedName("code")
        private int responseCode;

        public int getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(int responseCode) {
            this.responseCode = responseCode;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Meta meta = (Meta) o;

            return responseCode == meta.responseCode;
        }

        @Override
        public int hashCode() {
            return responseCode;
        }

        @Override
        public String toString() {
            return "Meta{" +
                    "responseCode=" + responseCode +
                    '}';
        }
    }
}
