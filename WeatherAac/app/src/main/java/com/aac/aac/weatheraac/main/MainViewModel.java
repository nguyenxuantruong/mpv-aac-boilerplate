package com.aac.aac.weatheraac.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.aac.aac.weatheraac.main.base.DataWrapper;
import com.aac.aac.weatheraac.models.ResponseError;
import com.aac.aac.weatheraac.models.WeatherResponse;
import com.aac.aac.weatheraac.services.ApiService;
import com.aac.aac.weatheraac.services.LocalService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    private final CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<DataWrapper<WeatherResponse>> weatherResponseMutableLiveData = new MutableLiveData<>();

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
                    if (response.hasError()) {
                        weatherResponseMutableLiveData.postValue(new DataWrapper<>(null, new ResponseError(ResponseError.Type.IN_APP_ERROR, response.getMessage())));
                    } else {
                        weatherResponseMutableLiveData.postValue(new DataWrapper<>(response, null));
                    }
                }, throwable -> {
                    weatherResponseMutableLiveData.postValue(new DataWrapper<>(null, new ResponseError(ResponseError.Type.IN_APP_ERROR, throwable.getMessage())));
                }));
    }

    MutableLiveData<DataWrapper<WeatherResponse>> getWeatherResponse(int id) {
        loadWeatherData(id);
        return weatherResponseMutableLiveData;
    }
}
