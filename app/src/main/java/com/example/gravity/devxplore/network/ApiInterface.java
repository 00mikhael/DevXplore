package com.example.gravity.devxplore.network;

import com.example.gravity.devxplore.model.DevelopersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by gravity on 7/4/17.
 */

public interface ApiInterface {
    @GET("/search/users?q=location:{location}+language:{language}")
    Call<DevelopersResponse> getDevelopers(@Path("location") String location, @Path("language") String language);
}
