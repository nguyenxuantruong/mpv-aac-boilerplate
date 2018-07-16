package com.aac.aac.weatheraac.main;


import com.aac.aac.weatheraac.models.WeatherResponse;

import io.reactivex.Flowable;

public interface WeatherRepository {

    Flowable<WeatherResponse> getWeatherData(int id);
}
