package com.aac.aac.weatheraac.di;

import com.aac.aac.weatheraac.main.WeatherRepository;
import com.aac.aac.weatheraac.main.WeatherRepositoryImpl;
import com.aac.aac.weatheraac.services.ApiService;
import com.aac.aac.weatheraac.services.LocalService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository(ApiService apiService, LocalService localService) {
        return new WeatherRepositoryImpl(apiService, localService);
    }
}
