package com.example.gravity.devxplore.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gravity on 7/12/17.
 */

public class Repository {

    @SerializedName("name")
    private String repoName;
    @SerializedName("full_name")
    private String repoFullName;
    @SerializedName("description")
    private String description;
    @SerializedName("language")
    private String language;
    @SerializedName("owner")
    private Owner owner;
    @SerializedName("updated_at")
    private String repoUpdatedAt;
    @SerializedName("stargazers_count")
    private int repoStars;
    @SerializedName("watchers_count")
    private int repoWatchers;
    @SerializedName("forks_count")
    private int repoForks;
    @SerializedName("html_url")
    private int repoUrl;

    public String getRepoName() {
        return repoName;
    }

    public String getRepoUpdatedAt() {
        return repoUpdatedAt;
    }

    public int getRepoStars() {
        return repoStars;
    }

    public int getRepoWatchers() {
        return repoWatchers;
    }

    public int getRepoForks() {
        return repoForks;
    }

    public int getRepoUrl() {
        return repoUrl;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getRepoFullName() {
        return repoFullName;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
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

    public Repository(String repoName, String repoFullName, String description, String language,
                      Owner owner, String repoUpdatedAt, int repoStars, int repoWatchers, int repoForks, int repoUrl) {
        this.repoName = repoName;
        this.repoFullName = repoFullName;
        this.description = description;
        this.language = language;
        this.owner = owner;
        this.repoUpdatedAt = repoUpdatedAt;
        this.repoStars = repoStars;
        this.repoWatchers = repoWatchers;
        this.repoForks = repoForks;
        this.repoUrl = repoUrl;
    }
}
