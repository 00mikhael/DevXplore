package com.example.gravity.devxplore.data.source.remote.apis;

import android.support.annotation.Nullable;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gravity on 7/2/17.
 */

public class ApiClient {

    private static final String BASE_URL = "https://api.github.com";
    @Nullable
    private static Retrofit retrofit = null;

    @Nullable
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
