package com.aac.aac.weatheraac.di;

import com.aac.aac.weatheraac.main.ViewModelFactory;
import com.aac.aac.weatheraac.services.ApiService;
import com.aac.aac.weatheraac.services.LocalService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LobbyModule {

    @Singleton
    @Provides
    ViewModelFactory provideViewModelFactory(ApiService apiService, LocalService localService) {
        return new ViewModelFactory(apiService, localService);
    }
}
