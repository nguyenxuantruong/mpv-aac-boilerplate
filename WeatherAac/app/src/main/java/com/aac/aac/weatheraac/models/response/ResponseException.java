package com.aac.aac.weatheraac.models.response;

import com.aac.aac.weatheraac.models.ResponseError;

public class ResponseException extends RuntimeException {

    private int responseCode;
    private String message;

    public ResponseException(int responseCode, String message) {
        this(responseCode);
        this.message = message;
    }

    public ResponseException(int responseCode) {
        this.responseCode = responseCode;
    }

    public ResponseError getError() {
        return new ResponseError(ResponseError.Type.IN_APP_ERROR, responseCode, message);
    }

    public int getResponseCode() {
        return responseCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
