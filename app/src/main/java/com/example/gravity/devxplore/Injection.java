package com.example.gravity.devxplore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.gravity.devxplore.data.source.DataRepository;
import com.example.gravity.devxplore.data.source.local.LocalDataSource;
import com.example.gravity.devxplore.data.source.remote.RemoteDataSource;

/**
 * Created by gravity on 8/2/17.
 */

@SuppressWarnings("ALL")
public class Injection {
    @Nullable
    public static DataRepository provideDataManager(@NonNull Context context) {
        return DataRepository.getInstance(RemoteDataSource.getInstance(), LocalDataSource.getInstance());
    }
}
