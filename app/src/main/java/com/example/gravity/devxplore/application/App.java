package com.example.gravity.devxplore.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by gravity on 7/29/17.
 */

@SuppressWarnings("ALL")
public class App extends Application {
    private static App sInstance;

    public static int currentThemePosition;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static App getInstance() {
        if (sInstance == null) {
            sInstance = new App();
        }
        return sInstance;
    }

    public static Context getContext() {
        return getInstance().getApplicationContext();
    }
}
