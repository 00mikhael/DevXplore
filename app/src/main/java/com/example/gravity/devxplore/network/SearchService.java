package com.example.gravity.devxplore.network;

import com.example.gravity.devxplore.model.UserResponse;
import com.example.gravity.devxplore.model.RepositoriesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gravity on 7/4/17.
 */

public interface SearchService {
    @GET("search/users")
    Call<UserResponse> getUsersResponse(@Query(value = "q", encoded = true) String query);

    @GET("/search/repositories")
    Call<RepositoriesResponse> getRepositoriesResponse(@Query(value = "q", encoded = true) String query);
}
