package com.example.gravity.devxplore.network;

import com.example.gravity.devxplore.model.DevelopersResponse;
import com.example.gravity.devxplore.model.RepositoriesResponse;
import com.example.gravity.devxplore.model.SingleUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gravity on 7/4/17.
 */

public interface ApiInterface {
    @GET("search/users")
    Call<DevelopersResponse> getDevelopers(@Query(value = "q", encoded = true) String query);

    @GET("/search/repositories?q=language:java+created:>2017-07-06+stars:>200")
    Call<RepositoriesResponse> getRepositories();

    @GET("/users/moyheen")
    Call<SingleUser> getUser();
}
