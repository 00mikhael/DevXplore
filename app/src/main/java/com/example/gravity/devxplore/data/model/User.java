package com.example.gravity.devxplore.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gravity on 7/4/17.
 */

public class User {
    @SerializedName("login")
    private final String login;
    @SerializedName("id")
    private final int id;
    @SerializedName("avatar_url")
    private final String avatarUrl;
    private boolean isFavourite = false;

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public User(String login, int id, String avatarUrl, boolean isFavourite) {
        this.login = login;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.isFavourite = isFavourite;
    }
}
