package com.aac.aac.weatheraac.di;

import android.content.Context;
import com.aac.aac.weatheraac.BuildConfig;
import com.aac.aac.weatheraac.services.ApiService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    @Provides
    @Singleton
    Cache provideHttpCache(Context context) {
        final int cacheSize = 10 * 1024 * 1024;     // 10 MB
        return new Cache(context.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).create();
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkhttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(Cache cache, OkHttpClient.Builder builder) {
        builder.cache(cache)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);

        if (BuildConfig.BUILD_TYPE.equalsIgnoreCase("debug") || BuildConfig.FLAVOR.equalsIgnoreCase("dev")) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    /**
     * Provide the {@link ApiService}
     *
     * @param retrofit The {@link Retrofit} instance
     * @return The instance of {@link ApiService}
     */
    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
