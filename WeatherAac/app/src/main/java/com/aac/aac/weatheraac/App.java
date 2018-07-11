package com.aac.aac.weatheraac;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.aac.aac.weatheraac.di.AppComponent;
import com.aac.aac.weatheraac.di.AppModule;
import com.aac.aac.weatheraac.di.DaggerAppComponent;
import com.aac.aac.weatheraac.di.LobbyModule;
import com.aac.aac.weatheraac.di.NetModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class App extends Application implements Application.ActivityLifecycleCallbacks {
    private AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BuildConfig.BASE_URL))
                .lobbyModule(new LobbyModule())
                .build();

        // Init logger
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG; // Only show logs in DEBUG mode.
            }
        });
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }


    /**
     * Get AppComponent object.
     *
     * @param context The {@link Context}
     * @return The instance of {@link AppComponent}
     */
    public static AppComponent getAppComponent(Context context) {
        return ((App) context.getApplicationContext()).appComponent;
    }
}
