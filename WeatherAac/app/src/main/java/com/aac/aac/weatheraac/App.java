package com.aac.aac.weatheraac;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.aac.aac.weatheraac.di.DaggerAppComponent;
import com.aac.aac.weatheraac.services.DataMigration;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application implements Application.ActivityLifecycleCallbacks, HasActivityInjector {
    private static final String REALM_NAME = "weather.realm";

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder().application(this).build().inject(this);

        // config Realm database
        Realm.init(this);
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder();
        if (BuildConfig.BUILD_TYPE.equalsIgnoreCase("debug") || BuildConfig.FLAVOR.equalsIgnoreCase("dev")) {
            builder.deleteRealmIfMigrationNeeded();
        }
        builder.name(REALM_NAME)
                .schemaVersion(DataMigration.REALM_SCHEMA_VERSION)
                .migration(new DataMigration());
        RealmConfiguration realmConfiguration = builder.build();
        Realm.setDefaultConfiguration(realmConfiguration);
        Realm.getInstance(realmConfiguration);

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

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
