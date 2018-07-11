package com.aac.aac.weatheraac.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import android.webkit.WebResourceResponse;

import com.aac.aac.weatheraac.App;
import com.aac.aac.weatheraac.models.WeatherResponse;
import com.aac.aac.weatheraac.services.ApiService;
import com.orhanobut.logger.Logger;

import org.reactivestreams.Subscriber;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
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

    public void loadWeatherData(double lat, double lon) {
        weatherRepository.getWeatherData(lat, lon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<WeatherResponse>() {
                    @Override
                    public void onNext(WeatherResponse weatherResponse) {
                        weatherResponseMutableLiveData.postValue(weatherResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    MutableLiveData<WeatherResponse> getWeatherResponse(double lat, double lon) {
        loadWeatherData(lat, lon);
        return weatherResponseMutableLiveData;
    }
}
