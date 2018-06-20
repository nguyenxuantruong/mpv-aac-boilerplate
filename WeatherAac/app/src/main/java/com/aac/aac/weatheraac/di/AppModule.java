package com.aac.aac.weatheraac.di;

import com.aac.aac.weatheraac.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private App application;


    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplication() {
        return this.application;
    }
}
