package com.aac.aac.weatheraac.main;


import com.aac.aac.weatheraac.models.Weather;

import io.reactivex.Flowable;

public interface WeatherRepository {

    Flowable<Weather> getWeatherData(int id);
}
