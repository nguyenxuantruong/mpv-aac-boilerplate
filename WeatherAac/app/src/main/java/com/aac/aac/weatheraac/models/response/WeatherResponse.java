package com.aac.aac.weatheraac.models.response;

import com.aac.aac.weatheraac.models.WeatherMain;
import com.google.gson.annotations.SerializedName;

public class WeatherResponse extends BaseResponse {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("main")
    private WeatherMain weatherMain;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public WeatherMain getWeatherMain() {
        return weatherMain;
    }
}
