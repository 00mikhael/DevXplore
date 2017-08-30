package com.example.gravity.devxplore.data.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gravity on 7/4/17.
 */

public class UserSearchResponse {
    @SerializedName("total_count")
    private int total;
    @SerializedName("items")
    private List<User> items;
    private Integer nextPage;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<User> getItems() {
        return items;
    }

    public void setItems(List<User> items) {
        this.items = items;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    @NonNull
    public List<Integer> getUserIds() {
        List<Integer> userIds = new ArrayList<>();
        for (User user : items) {
            userIds.add(user.getId());
        }
        return userIds;
    }
}
