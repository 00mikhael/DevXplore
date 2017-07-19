package com.example.gravity.devxplore.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gravity on 7/4/17.
 */

public class User {
    @SerializedName("login")
    private String login;
    @SerializedName("id")
    private int id;
    @SerializedName("avatar_url")
    private String avatarUrl;

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public User(String login, int id, String avatarUrl,
                String url, String eventsUrl, String reposUrl) {
        this.login = login;
        this.id = id;
        this.avatarUrl = avatarUrl;
    }
}
