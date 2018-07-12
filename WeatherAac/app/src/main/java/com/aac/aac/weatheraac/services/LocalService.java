package com.aac.aac.weatheraac.services;

import com.aac.aac.weatheraac.models.WeatherResponse;

public interface LocalService {
    void saveCityWeather(WeatherResponse weatherResponse);
    WeatherResponse getCityWeather(int id);
}
