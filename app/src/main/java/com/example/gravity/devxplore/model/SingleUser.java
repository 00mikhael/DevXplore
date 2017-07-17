package com.example.gravity.devxplore.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gravity on 7/14/17.
 */

public class SingleUser {

    @SerializedName("name")
    private String name;
    @SerializedName("login")
    private String login;
    @SerializedName("id")
    private int id;
    @SerializedName("public_repos")
    private String repos;
    @SerializedName("followers")
    private String followers;
    @SerializedName("following")
    private String following;
    @SerializedName("public_gists")
    private String gists;

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getRepos() {
        return repos;
    }

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }

    public String getGists() {
        return gists;
    }
}
