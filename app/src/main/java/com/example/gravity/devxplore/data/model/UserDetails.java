package com.example.gravity.devxplore.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gravity on 7/14/17.
 */

public class UserDetails {

    @SerializedName("name")
    private String name;
    @SerializedName("login")
    private String login;
    @SerializedName("id")
    private int id;
    @SerializedName("public_repos")
    private int repos;
    @SerializedName("followers")
    private int followers;
    @SerializedName("following")
    private int following;
    @SerializedName("public_gists")
    private int gists;
    @SerializedName("location")
    private String location;
    @SerializedName("bio")
    private String bio;
    @SerializedName("blog")
    private String blog;
    @SerializedName("company")
    private String company;


    public String getLocation() {
        return location;
    }

    public String getBio() {
        return bio;
    }

    public String getBlog() {
        return blog;
    }

    public String getCompany() {
        return company;
    }

    public String getName() {
        return name;

    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public int getRepos() {
        return repos;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public int getGists() {
        return gists;
    }
}
