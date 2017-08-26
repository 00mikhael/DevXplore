package com.example.gravity.devxplore;

import android.app.Application;
import android.content.Context;

/**
 * Created by gravity on 7/29/17.
 */

@SuppressWarnings("ALL")
public class MyApp extends Application {
    private static MyApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MyApp getInstance() {
        if (sInstance == null) {
            sInstance = new MyApp();
        }
        return sInstance;
    }

    public static Context getContext() {
        return getInstance().getApplicationContext();
    }
}
