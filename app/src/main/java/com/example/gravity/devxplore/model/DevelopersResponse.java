package com.example.gravity.devxplore.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gravity on 7/4/17.
 */

public class DevelopersResponse {
    @SerializedName("total_count")
    private int totalCount;
    @SerializedName("items")
    private List<Developer> items;

    public int getTotalCount() {
        return totalCount;
    }

    public List<Developer> getItems() {
        return items;
    }
}
