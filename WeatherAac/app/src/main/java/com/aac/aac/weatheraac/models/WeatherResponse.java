package com.aac.aac.weatheraac.models;

import com.google.gson.annotations.SerializedName;

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
