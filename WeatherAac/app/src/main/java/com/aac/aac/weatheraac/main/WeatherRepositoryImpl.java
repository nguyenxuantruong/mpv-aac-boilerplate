package com.aac.aac.weatheraac.main;

import com.aac.aac.weatheraac.models.WeatherResponse;
import com.aac.aac.weatheraac.services.ApiService;
import com.aac.aac.weatheraac.services.LocalService;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class WeatherRepositoryImpl implements WeatherRepository {

    private ApiService apiService;
    private LocalService localService;

    public WeatherRepositoryImpl(ApiService apiService, LocalService localService) {
        this.apiService = apiService;
        this.localService = localService;
    }

    @Override
    public Observable<WeatherResponse> getWeatherData(int id) {

        // weather from remote
        Observable<WeatherResponse> weatherRemote = apiService.getWeatherData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(response -> {
                    localService.saveCityWeather(response);
                    return response;
                });

        // weather in local
        WeatherResponse weather = localService.getCityWeather(id);

        // emit data
        Observable<WeatherResponse> weatherLocal = Observable.just(weather).filter(localWeather -> localWeather != null);

        return Observable.mergeDelayError(weatherLocal, weatherRemote);
    }
}
