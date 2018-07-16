package com.aac.aac.weatheraac.models.response;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class BaseResponse {

    @SerializedName("cod")
    private String cod;

    @SerializedName("message")
    private String message;

    public boolean hasError() {
        return cod != null;
    }

    public String getCod() {
        return cod;
    }

    public String getMessage() {
        return message;
    }
}
