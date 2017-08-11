package com.example.gravity.devxplore.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gravity on 7/12/17.
 */

public class Repository {

    @SerializedName("name")
    private final String repoName;
    @SerializedName("full_name")
    private final String repoFullName;
    @SerializedName("description")
    private final String description;
    @SerializedName("language")
    private final String language;
    @SerializedName("owner")
    private final Owner owner;
    @SerializedName("updated_at")
    private final String repoUpdatedAt;
    @SerializedName("stargazers_count")
    private final int repoStars;
    @SerializedName("watchers_count")
    private final int repoWatchers;
    @SerializedName("forks_count")
    private final int repoForks;
    @SerializedName("html_url")
    private final String  repoUrl;

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

    public String  getRepoUrl() {
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
        private final String login;

        @SerializedName("avatar_url")
        private final String avatarUrl;

        public String getLogin() {
            return login;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public Owner(String login, String avatarUrl) {
            this.login = login;
            this.avatarUrl = avatarUrl;
        }
    }

    public Repository(String repoName, String repoFullName, String description, String language,
                      Owner owner, String repoUpdatedAt, int repoStars, int repoWatchers, int repoForks, String repoUrl) {
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
