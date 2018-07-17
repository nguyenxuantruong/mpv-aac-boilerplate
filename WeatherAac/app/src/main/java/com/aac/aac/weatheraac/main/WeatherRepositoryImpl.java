package com.aac.aac.weatheraac.main;

import com.aac.aac.weatheraac.models.Weather;
import com.aac.aac.weatheraac.models.response.ResponseException;
import com.aac.aac.weatheraac.models.response.WeatherResponse;
import com.aac.aac.weatheraac.services.ApiService;
import com.aac.aac.weatheraac.services.LocalService;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class WeatherRepositoryImpl implements WeatherRepository {

    private ApiService apiService;
    private LocalService localService;

    public WeatherRepositoryImpl(ApiService apiService, LocalService localService) {
        this.apiService = apiService;
        this.localService = localService;
    }

    @Override
    public Flowable<Weather> getWeatherData(int id) {

        // weather from remote
        Flowable<Weather> weatherRemote = apiService.getWeatherData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(response -> {
                    if (response.hasError()) {
                        throw new ResponseException(response.getCod(), response.getMessage());
                    }
                    Weather weather = convertResponseToWeather(response);
                    localService.saveCityWeather(weather);
                    return weather;
                });

        // weather in local
        Weather weather = localService.getCityWeather(id);

        // emit data
        if (weather == null) {
            return weatherRemote;
        } else {
            Flowable<Weather> weatherLocal = Flowable.just(weather);
            return Flowable.mergeDelayError(weatherLocal, weatherRemote);
        }
    }

    private Weather convertResponseToWeather(WeatherResponse response) {
        Weather weather = new Weather();
        weather.setId(response.getId());
        weather.setName(response.getName());
        weather.setWeatherMain(response.getWeatherMain());
        return weather;
    }
}
