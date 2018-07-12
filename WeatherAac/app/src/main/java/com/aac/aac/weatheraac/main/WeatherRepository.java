package com.aac.aac.weatheraac.main;


import com.aac.aac.weatheraac.models.WeatherResponse;

import io.reactivex.Observable;

public interface WeatherRepository {

    Observable<WeatherResponse> getWeatherData(int id);
}
