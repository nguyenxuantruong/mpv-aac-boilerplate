package com.aac.aac.weatheraac.services;

import com.aac.aac.weatheraac.BuildConfig;
import com.aac.aac.weatheraac.models.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("data/2.5/weather?appid=" + BuildConfig.API_ID + "&")
    Observable<WeatherResponse> getWeatherData(@Query("lat") double lat,
                                               @Query("lon") double lon);
}
