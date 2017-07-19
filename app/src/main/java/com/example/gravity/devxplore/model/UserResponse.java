package com.example.gravity.devxplore.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gravity on 7/4/17.
 */

public class UserResponse {
    @SerializedName("items")
    private List<User> devItems;

    @NonNull
    public List<User> getDevItems() {
        return devItems;
    }
}
