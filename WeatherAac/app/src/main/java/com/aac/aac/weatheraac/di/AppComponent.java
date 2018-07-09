package com.aac.aac.weatheraac.di;

import com.aac.aac.weatheraac.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class, LobbyModule.class, RepositoryModule.class})
public interface AppComponent {

    void inject(MainActivity activity);
}
