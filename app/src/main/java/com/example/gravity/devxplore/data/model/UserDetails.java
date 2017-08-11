package com.example.gravity.devxplore.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gravity on 7/14/17.
 */

public class UserDetails {

    @SerializedName("name")
    private final String name;
    @SerializedName("login")
    private final String login;
    @SerializedName("avatar_url")
    private final String avatarUrl;
    @SerializedName("id")
    private final int id;
    @SerializedName("public_repos")
    private final int repos;
    @SerializedName("followers")
    private final int followers;
    @SerializedName("following")
    private final int following;
    @SerializedName("public_gists")
    private final int gists;
    @SerializedName("location")
    private final String location;
    @SerializedName("bio")
    private final String bio;
    @SerializedName("blog")
    private final String blog;
    @SerializedName("company")
    private final String company;

    public String getAvatarUrl() {
        return avatarUrl;
    }

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

    public UserDetails(String name, String login, String avatarUrl, int id, int repos, int followers, int following, int gists, String location, String bio, String blog, String company) {
        this.name = name;
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.id = id;
        this.repos = repos;
        this.followers = followers;
        this.following = following;
        this.gists = gists;
        this.location = location;
        this.bio = bio;
        this.blog = blog;
        this.company = company;
    }
}
