package com.example.gravity.devxplore.data.network.github;

import android.support.annotation.NonNull;

import com.example.gravity.devxplore.data.model.RepositoriesResponse;
import com.example.gravity.devxplore.data.model.UserDetails;
import com.example.gravity.devxplore.data.model.UserSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by gravity on 7/4/17.
 */

@SuppressWarnings("ALL")
public interface SearchService {
    @NonNull
    @GET("/search/users")
    Call<UserSearchResponse> loadUserResponse(@Query(value = "q", encoded = true) String query);

    @NonNull
    @GET("/users/{login}")
    Call<UserDetails> loadUser(@Path("login") String login);

    @NonNull
    @GET("/search/users")
    Call<UserSearchResponse> loadNextPage(@Query(value = "q", encoded = true) String query, @Query(value = "page", encoded = true) int page);

    @NonNull
    @GET("/search/repositories")
    Call<RepositoriesResponse> loadReposResponse(@Query(value = "q", encoded = true) String query);
}
