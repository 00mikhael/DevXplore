package com.example.gravity.devxplore.utilities;

import android.arch.lifecycle.LiveData;

/**
 * Created by gravity on 8/27/17.
 */

public class AbsentLiveData extends LiveData {
    private AbsentLiveData() {
        postValue(null);
    }
    public static <T> LiveData<T> create() {
        //noinspection unchecked
        return new AbsentLiveData();
    }
}
