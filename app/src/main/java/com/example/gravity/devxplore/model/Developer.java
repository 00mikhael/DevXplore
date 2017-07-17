package com.example.gravity.devxplore.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gravity on 7/4/17.
 */

public class Developer {
    @SerializedName("login")
    private String login;
    @SerializedName("id")
    private int id;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("url")
    private String url;
    @SerializedName("events_url")
    private String eventsUrl;
    @SerializedName("repos_url")
    private String reposUrl;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public Developer(String login, int id, String avatarUrl,
                     String url, String eventsUrl, String reposUrl) {
        this.login = login;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.url = url;
        this.eventsUrl = eventsUrl;
        this.reposUrl = reposUrl;
    }
}
