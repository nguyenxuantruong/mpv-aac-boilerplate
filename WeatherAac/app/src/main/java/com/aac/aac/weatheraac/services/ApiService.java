package com.aac.aac.weatheraac.services;

import com.aac.aac.weatheraac.BuildConfig;
import com.aac.aac.weatheraac.models.response.WeatherResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("data/2.5/weather?appid=" + BuildConfig.API_ID)
    Flowable<WeatherResponse> getWeatherData(@Query("id") int id);
}
