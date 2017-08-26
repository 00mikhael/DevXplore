package com.example.gravity.devxplore.data.network.github;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.gravity.devxplore.data.model.RepositoriesResponse;
import com.example.gravity.devxplore.data.model.UserResponse;

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
    Call<LiveData<UserResponse>> loadUserResponse(@Query(value = "q", encoded = true) String query);

    @NonNull
    @GET("/search/repositories")
    Call<LiveData<RepositoriesResponse>> loadReposResponse(@Query(value = "q", encoded = true) String query);
}
