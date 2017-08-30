package com.example.gravity.devxplore.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gravity on 7/14/17.
 */
@Entity(tableName = "details", foreignKeys = {
        @ForeignKey(entity = User.class,
                    parentColumns = "is_favourite",
                    childColumns = "is_favourite",
                    onDelete = ForeignKey.CASCADE,
                    onUpdate = ForeignKey.CASCADE)},
        indices = {@Index(value = {"is_favourite"}, unique = true)
})
public class UserDetails {
    @PrimaryKey
    @ColumnInfo(name = "login")
    @SerializedName("login")
    private String login;
    @SerializedName("id")
    private int id;
    @ColumnInfo(name = "fullname")
    @SerializedName("name")
    private String name;

    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url")
    private String avatarUrl;

    @ColumnInfo(name = "html_url")
    @SerializedName("html_url")
    private String htmlUrl;

    @ColumnInfo(name = "repos_count")
    @SerializedName("public_repos")
    private int repos;

    @ColumnInfo(name = "followers_count")
    @SerializedName("followers")
    private int followers;

    @ColumnInfo(name = "following_count")
    @SerializedName("following")
    private int following;

    @ColumnInfo(name = "location")
    @SerializedName("location")
    private String location;

    @ColumnInfo(name = "bio")
    @SerializedName("bio")
    private String bio;

    @ColumnInfo(name = "is_favourite")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
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

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public void setRepos(int repos) {
        this.repos = repos;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public UserDetails(String login,int id, String name, String avatarUrl, String htmlUrl, int repos, int followers, int following, String location, String bio, boolean isFavourite) {
        this.login = login;
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.htmlUrl = htmlUrl;
        this.repos = repos;
        this.followers = followers;
        this.following = following;
        this.location = location;
        this.bio = bio;
        this.isFavourite = isFavourite;
    }
}
