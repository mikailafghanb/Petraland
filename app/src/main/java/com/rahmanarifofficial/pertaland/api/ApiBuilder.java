package com.rahmanarifofficial.pertaland.api;

import com.rahmanarifofficial.pertaland.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBuilder {
    private static Retrofit mapsRetrofit = null;
    private static Retrofit mlRetrofit = null;

    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS).build();

    public static Retrofit getMapsClient() {
        if (mapsRetrofit == null) {
            mapsRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL).client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mapsRetrofit;
    }

    public static Retrofit getRekomendasiClient() {
        if (mlRetrofit == null) {
            mlRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_API_URL).client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mlRetrofit;
    }
}
