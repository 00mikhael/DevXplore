package com.example.gravity.devxplore.data.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gravity on 7/4/17.
 */

@Entity(tableName = "count")
public class UserResponse {
    @Ignore
    @SerializedName("items")
    private LiveData<List<User>> mUserItems;

    @ColumnInfo(name = "total_count")
    @SerializedName("total_count")
    private LiveData<Integer> mTotalCount;

    @NonNull
    public LiveData<List<User>> getUserItems() {
        return mUserItems;
    }

    @NonNull
    public LiveData<Integer> getTotalUsers() {
        return mTotalCount;
    }

    public void setUserItems(LiveData<List<User>> userItems) {
        this.mUserItems = userItems;
    }

        public void setTotalCount(LiveData<Integer> count) {
        this.mTotalCount = count;
    }

    public UserResponse(LiveData<List<User>> mUserItems, LiveData<Integer> mTotalCount) {
        this.mUserItems = mUserItems;
        this.mTotalCount = mTotalCount;
    }
}
