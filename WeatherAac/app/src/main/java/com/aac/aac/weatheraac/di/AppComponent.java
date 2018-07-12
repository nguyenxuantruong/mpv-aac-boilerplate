package com.aac.aac.weatheraac.di;

import android.app.Application;

import com.aac.aac.weatheraac.App;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, NetModule.class, LobbyModule.class, RepositoryModule.class, ActivityBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(App app);
}
