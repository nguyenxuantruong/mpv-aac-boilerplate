package com.aac.aac.weatheraac.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import android.webkit.WebResourceResponse;

import com.aac.aac.weatheraac.App;
import com.aac.aac.weatheraac.models.WeatherResponse;
import com.aac.aac.weatheraac.services.ApiService;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    private final CompositeDisposable disposable = new CompositeDisposable();
    private final MutableLiveData<WeatherResponse> weatherResponse = new MutableLiveData<>();
    private MutableLiveData<WeatherResponse> weatherResponseMutableLiveData = new MutableLiveData<>();

    ApiService apiService;

    private WeatherRepository weatherRepository;

    public MainViewModel(ApiService apiService) {
        this.apiService = apiService;
        weatherRepository = new WeatherRepositoryImpl(apiService);

    }

    @Override
    protected void onCleared() {
        disposable.clear();
    }

    public Observable<WeatherResponse> loadWeatherData(double lat, double lon) {
        return weatherRepository.getWeatherData(lat, lon)
                .doOnNext(reponse -> {
                    weatherResponseMutableLiveData.postValue(reponse);
                });
    }

    MutableLiveData<WeatherResponse> getWeatherResponse(double lat, double lon) {
        loadWeatherData(lat, lon);
        return weatherResponseMutableLiveData;
    }
}
