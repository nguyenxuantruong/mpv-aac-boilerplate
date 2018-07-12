package com.aac.aac.weatheraac.di;

import android.app.Application;
import android.content.Context;

import com.aac.aac.weatheraac.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }
}
