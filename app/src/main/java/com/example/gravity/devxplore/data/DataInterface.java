package com.example.gravity.devxplore.data;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.gravity.devxplore.data.model.Repository;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.model.UserDetails;

import java.util.List;

/**
 * Created by gravity on 8/27/17.
 */

public interface DataInterface {
    LiveData<List<User>> getUsers();

    LiveData<List<User>> getUsers(@NonNull String query);

    LiveData<UserDetails> getUser(@NonNull String username);

    LiveData<List<Repository>> getPopularRepos(@NonNull String username);

    LiveData<List<Repository>> getUserRepos(@NonNull String username);

    LiveData<List<Repository>> getUserStarredRepos(@NonNull String username);

    LiveData<List<User>> getUserFollowers(@NonNull String username);

    LiveData<List<User>> getUserFollowing(@NonNull String username);

    void setFavourite(@NonNull String username, boolean isFavourite);

    LiveData<List<User>> getFavourites();
}
