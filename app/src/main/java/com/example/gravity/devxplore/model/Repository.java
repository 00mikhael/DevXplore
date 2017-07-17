package com.example.gravity.devxplore.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gravity on 7/12/17.
 */

public class Repository {

    @SerializedName("full_name")
    private String repoName;
    @SerializedName("description")
    private String description;
    @SerializedName("language")
    private String language;
    @SerializedName("owner")
    private Owner owner;

    public Owner getOwner() {
        return owner;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Repository(String repoName, String description, String language, Owner owner) {
        this.repoName = repoName;
        this.description = description;
        this.language = language;
        this.owner = owner;
    }

    public class Owner {
        @SerializedName("login")
        private String login;

        @SerializedName("avatar_url")
        private String avatarUrl;

        public String getLogin() {
            return login;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }
    }
}
