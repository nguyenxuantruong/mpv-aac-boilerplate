package com.aac.aac.weatheraac.di;

import com.aac.aac.weatheraac.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract MainActivity contributeActivityInjector();
}
