package com.aac.aac.weatheraac.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aac.aac.weatheraac.R;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidInjection.inject(this);


        MainViewModel mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);

        mainViewModel.getWeatherResponse(2172797)
                .observe(this, dataWrapper -> {
                    if (dataWrapper != null && dataWrapper.hasError()) {
                        // TODO: 7/16/18 handle error on UI
                        Logger.d(dataWrapper.getError().getMessage());
                        Logger.d(dataWrapper.getError().getErrorCode());
                    } else {
                        // TODO: 7/12/18  update UI
                        Logger.d(dataWrapper.getData().getName());
                    }
                });
    }
}
