package com.aac.aac.weatheraac.main;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.aac.aac.weatheraac.services.ApiService;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private ApiService apiService;

    public ViewModelFactory(ApiService apiService) {
        this.apiService = apiService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(apiService);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
