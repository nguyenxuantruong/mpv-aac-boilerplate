package com.aac.aac.weatheraac.di;

import com.aac.aac.weatheraac.main.ViewModelFactory;
import com.aac.aac.weatheraac.services.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LobbyModule {

    @Singleton
    @Provides
    ViewModelFactory provideViewModelFactory(ApiService apiService) {
        return new ViewModelFactory(apiService);
    }
}
