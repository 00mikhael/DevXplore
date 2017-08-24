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
    @SerializedName("html_url")
    private final String htmlUrl;
    @SerializedName("id")
    private final int id;
    @SerializedName("public_repos")
    private final int repos;
    @SerializedName("followers")
    private final int followers;
    @SerializedName("following")
    private final int following;
    @SerializedName("location")
    private final String location;
    @SerializedName("bio")
    private final String bio;
    @SerializedName("blog")
    private boolean isFavourite = false;

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getLocation() {
        return location;
    }

    public String getBio() {
        return bio;
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

    public UserDetails(int id, String name, String login, String avatarUrl, String htmlUrl, int repos, int followers, int following, String location, String bio, boolean isFavourite) {
        this.name = name;
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.htmlUrl = htmlUrl;
        this.id = id;
        this.repos = repos;
        this.followers = followers;
        this.following = following;
        this.location = location;
        this.bio = bio;
        this.isFavourite = isFavourite;
    }
}
