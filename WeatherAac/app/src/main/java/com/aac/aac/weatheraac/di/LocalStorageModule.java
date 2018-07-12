package com.aac.aac.weatheraac.di;

import com.aac.aac.weatheraac.services.LocalService;
import com.aac.aac.weatheraac.services.LocalServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalStorageModule {

    @Singleton
    @Provides
    LocalService provideLocalService() {
        return new LocalServiceImpl();
    }
}
