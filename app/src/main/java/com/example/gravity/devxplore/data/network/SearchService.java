package com.example.gravity.devxplore.data.network;

import android.support.annotation.NonNull;

import com.example.gravity.devxplore.data.model.UserResponse;
import com.example.gravity.devxplore.data.model.RepositoriesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gravity on 7/4/17.
 */

@SuppressWarnings("ALL")
public interface SearchService {
    @NonNull
    @GET("/search/users")
    Call<UserResponse> getUsersResponse(@Query(value = "q", encoded = true) String query1);

    @NonNull
    @GET("/search/repositories")
    Call<RepositoriesResponse> getRepositoriesResponse(@Query(value = "q", encoded = true) String query);
}
