package com.aac.aac.weatheraac.main.base;

import com.aac.aac.weatheraac.models.ResponseError;

public class DataWrapper<T> {
    private T data;
    private ResponseError error;

    public DataWrapper(T data, ResponseError error) {
        this.data = data;
        this.error = error;
    }

    public boolean hasError() {
        return error != null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseError getError() {
        return error;
    }

    public void setError(ResponseError error) {
        this.error = error;
    }
}
