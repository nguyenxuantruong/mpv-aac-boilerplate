package com.aac.aac.weatheraac.services;

import com.aac.aac.weatheraac.models.Weather;
import io.realm.Realm;

public class LocalServiceImpl implements LocalService {

    @Override
    public void saveCityWeather(Weather weatherResponse) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm -> {

                // get weather in local by id
                Weather weather = realm.where(Weather.class)
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
    public Weather getCityWeather(int id) {
        try (Realm realm = Realm.getDefaultInstance()) {
            Weather weather = realm.where(Weather.class)
                    .equalTo("id", id)
                    .findFirst();
            if (weather == null)
                return null;
            return realm.copyFromRealm(weather);
        }
    }
}
