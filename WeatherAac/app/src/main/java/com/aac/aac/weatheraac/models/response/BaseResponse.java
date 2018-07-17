package com.aac.aac.weatheraac.models.response;

import com.google.gson.annotations.SerializedName;
import com.orhanobut.logger.Logger;

public class BaseResponse {

    @SerializedName("cod")
    private int cod;

    @SerializedName("message")
    private String message;

    public boolean hasError() {
        return cod / 100 != 2;
    }
    public int getCod() {
        return cod;
    }

    public String getMessage() {
        return message;
    }
}
