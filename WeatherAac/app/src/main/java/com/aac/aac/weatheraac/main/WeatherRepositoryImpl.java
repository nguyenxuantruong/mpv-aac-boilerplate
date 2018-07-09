package com.aac.aac.weatheraac.main;

import android.arch.lifecycle.LiveData;

import com.aac.aac.weatheraac.models.WeatherResponse;
import com.aac.aac.weatheraac.services.ApiService;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class WeatherRepositoryImpl implements WeatherRepository {

    private ApiService apiService;

    public WeatherRepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<WeatherResponse> getWeatherData(double lat, double lng) {
        return apiService.getWeatherData(lat, lat)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(weatherResponse -> {
                    return weatherResponse;
                });
    }
}
