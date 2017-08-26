package com.example.gravity.devxplore.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gravity on 7/4/17.
 */
@Entity(tableName = "users",
        indices = {@Index(value = {"login"}, unique = true)})

public class User {
    @PrimaryKey
    @ColumnInfo(name = "login")
    @SerializedName("login")
    private String login;

    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url")
    private String avatarUrl;

    @ColumnInfo(name = "is_favourite")
    private boolean isFavourite = false;


    public String getLogin() {
        return login;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public User(String login, String avatarUrl, boolean isFavourite) {
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.isFavourite = isFavourite;
    }
}
