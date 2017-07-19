package com.example.gravity.devxplore.network;

import com.example.gravity.devxplore.model.Repository;
import com.example.gravity.devxplore.model.User;
import com.example.gravity.devxplore.model.UserDetails;
import com.example.gravity.devxplore.model.UserEvents;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by gravity on 7/18/17.
 */

public interface UserService {

    @GET("/users/{login}")
    Call<UserDetails> getUser(@Path("login") String login);

    @GET("/users/{login}/followers")
    Call<User> getUserFollowers(@Path("login") String login);

    @GET("/users/{login}/following")
    Call<User> getUserFollowing(@Path("login") String login);

    /*@GET("/users/{login}/gists")
    Call<UserDetails> getUserGists(@Path("login") String login);*/

    @GET("/users/{login}/starred")
    Call<Repository> getUserStarred(@Path("login") String login);

    @GET("/users/{login}/subscriptions")
    Call<Repository > getUserSubscriptions(@Path("login") String login);

    @GET("/users/{login}/repos")
    Call<Repository> getUserRepos(@Path("login") String login);

    @GET("/users/{login}/events")
    Call<UserEvents> getUserEvents(@Path("login") String login);

    @GET("/users/{login}/received_events")
    Call<UserEvents> getUserReceivedEvents(@Path("login") String login);
}
