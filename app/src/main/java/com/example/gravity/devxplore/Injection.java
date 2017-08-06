package com.example.gravity.devxplore;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.gravity.devxplore.data.DMHelper;
import com.example.gravity.devxplore.data.DataManager;
import com.example.gravity.devxplore.data.network.NetworkManager;

/**
 * Created by gravity on 8/2/17.
 */

public class Injection {
    public static DataManager provideDataManager(@NonNull Context context) {
        return DataManager.getInstance(NetworkManager.getInstance());
    }
}
