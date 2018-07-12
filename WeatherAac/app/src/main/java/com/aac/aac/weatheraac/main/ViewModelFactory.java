package com.aac.aac.weatheraac.main;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.aac.aac.weatheraac.services.ApiService;
import com.aac.aac.weatheraac.services.LocalService;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private ApiService apiService;
    private LocalService localService;

    public ViewModelFactory(ApiService apiService, LocalService localService) {
        this.apiService = apiService;
        this.localService = localService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(apiService, localService);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
