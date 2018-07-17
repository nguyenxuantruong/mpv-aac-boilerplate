package com.aac.aac.weatheraac.main.base;

import com.aac.aac.weatheraac.models.response.ResponseException;

public class DataWrapper<T> {
    private T data;
    private ResponseException.ResponseError error;

    public DataWrapper(T data, ResponseException.ResponseError error) {
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

    public ResponseException.ResponseError getError() {
        return error;
    }

    public void setError(ResponseException.ResponseError error) {
        this.error = error;
    }
}
