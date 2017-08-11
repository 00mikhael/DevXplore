package com.example.gravity.devxplore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.gravity.devxplore.data.DataManager;
import com.example.gravity.devxplore.data.network.NetworkManager;

/**
 * Created by gravity on 8/2/17.
 */

@SuppressWarnings("ALL")
public class Injection {
    @Nullable
    public static DataManager provideDataManager(@NonNull Context context) {
        return DataManager.getInstance(NetworkManager.getInstance());
    }
}
