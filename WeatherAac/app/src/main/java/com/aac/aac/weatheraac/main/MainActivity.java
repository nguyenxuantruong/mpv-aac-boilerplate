package com.aac.aac.weatheraac.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.aac.aac.weatheraac.App;
import com.aac.aac.weatheraac.BuildConfig;
import com.aac.aac.weatheraac.R;
import com.aac.aac.weatheraac.services.ApiService;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;

    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidInjection.inject(this);


        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);

        mainViewModel.getWeatherResponse(16.032137, 108.215470)
                .observe(this, weatherResponse -> {
                    Log.d("3333", weatherResponse.getName());
                    Log.d("3333", weatherResponse.getWeatherMain().getHumidity().toString());
                    Log.d("3333", weatherResponse.getWeatherMain().getTemp().toString());
                });
    }
}
