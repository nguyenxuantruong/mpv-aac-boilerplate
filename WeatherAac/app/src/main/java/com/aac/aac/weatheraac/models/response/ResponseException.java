package com.aac.aac.weatheraac.models.response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

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

    public static class ResponseError {

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

        public ResponseError(Type type, final int errorCode, final String message) {
            this.type = type;
            this.errorCode = errorCode;
            this.message = message;
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

        public static ResponseError getResponseError(Throwable throwable) {

            if (throwable instanceof HttpException) {
                Response response = ((HttpException) throwable).response();
                ResponseBody responseBody = response.errorBody();
                return new ResponseError(com.aac.aac.weatheraac.models.ResponseError.Type.SERVER_ERROR, response.code(), getErrorMessage(responseBody));
            } else if (throwable instanceof SocketTimeoutException) {
                return new ResponseError(com.aac.aac.weatheraac.models.ResponseError.Type.SERVER_ERROR, com.aac.aac.weatheraac.models.ResponseError.ERR_SOCKET_TIME_OUT, "Opps, something went wrong..");
            } else if (throwable instanceof ResponseException) {
                return ((ResponseException) throwable).getError();
            } else {
                return new ResponseError(com.aac.aac.weatheraac.models.ResponseError.Type.UNKNOWN_ERROR, com.aac.aac.weatheraac.models.ResponseError.ERR_UNKNOWN, "Unknown!");
            }
        }

        private static String getErrorMessage(ResponseBody responseBody) {
            try {
                JSONObject jsonObject = new JSONObject(responseBody.string());
                return jsonObject.getString("errorMessage");

            } catch (JSONException | IOException e) {
                return e.getMessage();
            }
        }
    }
}
