package com.aac.aac.weatheraac.di;

import com.aac.aac.weatheraac.main.ViewModelFactory;
import com.aac.aac.weatheraac.main.WeatherRepository;
import com.aac.aac.weatheraac.main.WeatherRepositoryImpl;
import com.aac.aac.weatheraac.services.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository(ApiService apiService) {
        return new WeatherRepositoryImpl(apiService);
    }
}
