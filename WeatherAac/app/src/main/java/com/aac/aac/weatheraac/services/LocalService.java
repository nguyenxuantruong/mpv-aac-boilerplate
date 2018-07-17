package com.aac.aac.weatheraac.services;

import com.aac.aac.weatheraac.models.Weather;

public interface LocalService {
    void saveCityWeather(Weather weather);
    Weather getCityWeather(int id);
}
