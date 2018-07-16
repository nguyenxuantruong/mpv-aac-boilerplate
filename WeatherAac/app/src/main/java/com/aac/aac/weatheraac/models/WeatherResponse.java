package com.aac.aac.weatheraac.models;

import com.aac.aac.weatheraac.models.response.BaseResponse;
import com.google.gson.annotations.SerializedName;
import com.orhanobut.logger.Logger;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WeatherResponse extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("main")
    private WeatherMain weatherMain;

    @SerializedName("cod")
    private int cod;

    @SerializedName("message")
    private String message;

    public boolean hasError() {
        Logger.d(cod);
        return cod / 100 != 2;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public WeatherMain getWeatherMain() {
        return weatherMain;
    }

    public int getCod() {
        return cod;
    }

    public String getMessage() {
        return message;
    }
}
