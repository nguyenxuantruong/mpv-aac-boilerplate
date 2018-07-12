package com.aac.aac.weatheraac.services;

import com.aac.aac.weatheraac.models.WeatherResponse;

import io.realm.Realm;

public class LocalServiceImpl implements LocalService {

    @Override
    public void saveCityWeather(WeatherResponse weatherResponse) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm -> {

                // get weather in local by id
                WeatherResponse weather = realm.where(WeatherResponse.class)
                        .equalTo("id", weatherResponse.getId())
                        .findFirst();
                if (weather != null) {
                    weather.deleteFromRealm();
                }

                realm.insertOrUpdate(weatherResponse);
            });
        }
    }

    @Override
    public WeatherResponse getCityWeather(int id) {
        try (Realm realm = Realm.getDefaultInstance()) {
            WeatherResponse weather = realm.where(WeatherResponse.class)
                    .equalTo("id", id)
                    .findFirst();
            if (weather == null)
                return null;
            return realm.copyFromRealm(weather);
        }
    }
}
