package com.example.gravity.devxplore.data.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gravity on 7/12/17.
 */

public class RepositoriesResponse {
    @SerializedName("items")
    private final List<Repository> mRepoItems;

    @NonNull
    public List<Repository> getRepoItems() {
        return mRepoItems;
    }


    public RepositoriesResponse(List<Repository> mRepoItems) {
        this.mRepoItems = mRepoItems;
    }
}
