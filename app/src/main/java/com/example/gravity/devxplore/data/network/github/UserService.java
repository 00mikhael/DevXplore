package com.example.gravity.devxplore.data.network.github;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.gravity.devxplore.data.model.Repository;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.model.UserDetails;
import com.example.gravity.devxplore.data.model.UserEvents;

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
    Call<LiveData<UserDetails>> loadUser(@Path("login") String login);

    @NonNull
    @GET("/users/{login}/followers")
    Call<LiveData<List<User>>> loadUserFollowers(@Path("login") String login);

    @NonNull
    @GET("/users/{login}/following")
    Call<LiveData<List<User>>> loadUserFollowing(@Path("login") String login);

    @NonNull
    @GET("/users/{login}/starred")
    Call<LiveData<List<Repository>>> loadUserStarred(@Path("login") String login);

    @NonNull
    @GET("/users/{login}/subscriptions")
    Call<LiveData<List<Repository>>> loadUserSubscriptions(@Path("login") String login);

    @NonNull
    @GET("/users/{login}/repos")
    Call<LiveData<List<Repository>>> loadUserRepos(@Path("login") String login);

    @NonNull
    @GET("/users/{login}/events")
    Call<LiveData<List<UserEvents>>> loadUserEvents(@Path("login") String login);

    @NonNull
    @GET("/users/{login}/repos")
    Call<LiveData<List<Repository>>> loadPopularRepos(@Path("login") String login);

    @NonNull
    @GET("/users/{login}/received_events")
    Call<LiveData<List<UserEvents>>> loadUserReceivedEvents(@Path("login") String login);
}
