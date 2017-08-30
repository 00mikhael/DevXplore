package com.example.gravity.devxplore.data.network.github;

import android.support.annotation.NonNull;

import com.example.gravity.devxplore.data.model.Repository;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.model.UserDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by gravity on 7/18/17.
 */

@SuppressWarnings("ALL")
public interface UserService {

    @NonNull
    @GET("/users/{login}")
    Call<UserDetails> loadUser(@Path("login") String login);

    @NonNull
    @GET("/users/{login}/followers")
    Call<List<User>> loadUserFollowers(@Path("login") String login);

    @NonNull
    @GET("/users/{login}/following")
    Call<List<User>> loadUserFollowing(@Path("login") String login);

    @NonNull
    @GET("/users/{login}/starred")
    Call<List<Repository>> loadUserStarred(@Path("login") String login);

    @NonNull
    @GET("/users/{login}/subscriptions")
    Call<List<Repository>> loadUserSubscriptions(@Path("login") String login);

    @NonNull
    @GET("/users/{login}/repos")
    Call<List<Repository>> loadUserRepos(@Path("login") String login);

    @NonNull
    @GET("/users/{login}/repos")
    Call<List<Repository>> loadPopularRepos(@Path("login") String login);
}
