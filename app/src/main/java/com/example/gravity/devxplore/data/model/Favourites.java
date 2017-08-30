package com.example.gravity.devxplore.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by gravity on 8/28/17.
 */

@Entity(tableName = "favourites", foreignKeys = {
        @ForeignKey(entity = User.class,
                parentColumns = "is_favourite",
                childColumns = "is_favourite",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE)},
        indices = {@Index(value = {"is_favourite"}, unique = true)
        })
public class Favourites {
    @PrimaryKey
    @ColumnInfo(name = "login")
    private String login;

    private int id;

    @ColumnInfo(name = "avatar_url")
    private String avatarUrl;

    @ColumnInfo(name = "is_favourite")
    private boolean isFavourite = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Favourites(String login,int id, String avatarUrl, boolean isFavourite) {
        this.login = login;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.isFavourite = isFavourite;
    }
}
