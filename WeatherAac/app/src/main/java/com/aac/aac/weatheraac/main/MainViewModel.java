package com.aac.aac.weatheraac.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.aac.aac.weatheraac.models.WeatherResponse;
import com.aac.aac.weatheraac.services.ApiService;
import com.aac.aac.weatheraac.services.LocalService;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    private final CompositeDisposable disposable = new CompositeDisposable();
    private final MutableLiveData<WeatherResponse> weatherResponse = new MutableLiveData<>();
    private MutableLiveData<WeatherResponse> weatherResponseMutableLiveData = new MutableLiveData<>();

    private WeatherRepository weatherRepository;

    public MainViewModel(ApiService apiService, LocalService localService) {
        weatherRepository = new WeatherRepositoryImpl(apiService, localService);

    }

    @Override
    protected void onCleared() {
        disposable.clear();
    }

    private void loadWeatherData(int id) {

        // FIXME: 7/12/18 I am using interval operator to verify LiveData on UI
//        Observable.interval(5, TimeUnit.SECONDS)
//                .flatMap(n -> weatherRepository.getWeatherData(id))
//                .observeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableObserver<WeatherResponse>() {
//                    @Override
//                    public void onNext(WeatherResponse weatherResponse) {
//                        weatherResponseMutableLiveData.postValue(weatherResponse);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        disposable.add(weatherRepository.getWeatherData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    weatherResponseMutableLiveData.postValue(response);
                }, Throwable::printStackTrace));
    }

    MutableLiveData<WeatherResponse> getWeatherResponse(int id) {
        loadWeatherData(id);
        return weatherResponseMutableLiveData;
    }
}
