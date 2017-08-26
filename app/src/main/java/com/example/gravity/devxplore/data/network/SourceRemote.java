package com.example.gravity.devxplore.data.network;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.gravity.devxplore.data.model.Repository;
import com.example.gravity.devxplore.data.model.User;

import java.util.List;

/**
 * Created by gravity on 7/29/17.
 */

@SuppressWarnings("ALL")
public interface SourceRemote {

    LiveData<List<User>> loadUsers(@NonNull String location, @NonNull String language);

    LiveData<User> loadUser(@NonNull String username);

    LiveData<List<Repository>> loadPopularRepos(@NonNull String username);

    LiveData<List<Repository>> loadUserRepos(@NonNull String username);

    LiveData<List<Repository>> loadUserStarredRepos(@NonNull String username);

    LiveData<List<User>> loadUserFollowers(@NonNull String username);

    LiveData<List<User>> loadUserFollowing(@NonNull String username);
}
