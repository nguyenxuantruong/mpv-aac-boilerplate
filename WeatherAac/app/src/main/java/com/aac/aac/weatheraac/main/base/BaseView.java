package com.aac.aac.weatheraac.main.base;

public interface BaseView {
    void onUnknownError(String message);
    void onTimeout();
    void onNetworkError();
}
