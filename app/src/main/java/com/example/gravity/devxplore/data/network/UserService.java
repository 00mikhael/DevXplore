package com.example.gravity.devxplore.data.network;

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
    Call<UserDetails> getUser(@Path("login") String login);

    @NonNull
    @GET("/users/{login}/followers")
    Call<List<User>> getUserFollowers(@Path("login") String login);

    @NonNull
    @GET("/users/{login}/following")
    Call<List<User>> getUserFollowing(@Path("login") String login);

    /*@GET("/users/{login}/gists")
    Call<UserDetails> getUserGists(@Path("login") String login);*/

    @NonNull
    @GET("/users/{login}/starred")
    Call<List<Repository>> getUserStarred(@Path("login") String login);

    @NonNull
    @GET("/users/{login}/subscriptions")
    Call<List<Repository>> getUserSubscriptions(@Path("login") String login);

    @NonNull
    @GET("/users/{login}/repos")
    Call<List<Repository>> getUserRepos(@Path("login") String login);

    @NonNull
    @GET("/users/{login}/events")
    Call<List<UserEvents>> getUserEvents(@Path("login") String login);

    @NonNull
    @GET("/users/{login}/received_events")
    Call<List<UserEvents>> getUserReceivedEvents(@Path("login") String login);
}
