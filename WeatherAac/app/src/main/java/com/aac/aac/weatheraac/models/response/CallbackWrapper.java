package com.aac.aac.weatheraac.models.response;

import com.aac.aac.weatheraac.main.base.BaseView;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public abstract class CallbackWrapper<T extends BaseResponse> extends DisposableObserver<T> {

    private WeakReference<BaseView> weakReference;

    public CallbackWrapper(BaseView baseView) {
        this.weakReference = new WeakReference<>(baseView);
    }

    protected abstract void onSuccess(T t);

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        BaseView view = weakReference.get();
        if (e instanceof HttpException) {
            ResponseBody responseBody = ((HttpException)e).response().errorBody();
            view.onUnknownError(getErrorMessage(responseBody));
        } else if (e instanceof SocketTimeoutException) {
            view.onTimeout();
        } else if (e instanceof IOException) {
            view.onNetworkError();
        } else {
            view.onUnknownError(e.getMessage());
        }
    }

    @Override
    public void onComplete() {
        // nothing
    }

    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
