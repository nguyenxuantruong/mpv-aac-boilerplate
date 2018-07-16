package com.aac.aac.weatheraac.models;

public class ResponseError {

    public static final int ERR_SOCKET_TIME_OUT = 0x011;
    public static final int ERR_UNKNOWN = 0x012;
    public static final int ERR_NO_INTERNET_CONNECTION= 0x014;

    public enum Type {
        SERVER_ERROR, // Have error from server. Http status is not success.
        IN_APP_ERROR, // Have error in app. Http status is success but responseCode is not success.
        UNKNOWN_ERROR // Unknown error.
    }

    private Type type;
    private int errorCode;
    private String message;

    public ResponseError(Type type, final String message) {
        this.type = type;
        this.errorCode = errorCode;
        this.message = message;
    }

    public ResponseError(Type type) {
        this.type = type;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    /**
     * Check the error is in app error type.
     *
     * @return True if this error is in app error type.
     */
    public boolean isInAppError() {
        return this.type == Type.IN_APP_ERROR;
    }

    /**
     * Check the error is no internet connection error.
     *
     * @return True if this error is no internet connection error.
     */
    public boolean isNoInternetConnection() {
        return this.type == Type.IN_APP_ERROR && this.errorCode == ERR_NO_INTERNET_CONNECTION;
    }
}

