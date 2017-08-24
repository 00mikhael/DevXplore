package com.example.gravity.devxplore.data.source.remote;

import android.support.annotation.NonNull;

import com.example.gravity.devxplore.data.source.DataCallbacks;

/**
 * Created by gravity on 7/29/17.
 */

@SuppressWarnings("ALL")
public interface SourceRemote {

    void loadUsers(@NonNull String location, @NonNull String language, DataCallbacks.GetUserListCallback callback);

    void loadUser(@NonNull String username, DataCallbacks.GetUserCallback callback);

    void loadPopularRepos(@NonNull String username, DataCallbacks.GetUserReposCallback callback);

    void loadUserRepos(@NonNull String username, DataCallbacks.GetUserReposCallback callback);

    void loadUserStarredRepos(@NonNull String username, DataCallbacks.GetUserStarredCallback callback);

    void loadUserFollowers(@NonNull String username, DataCallbacks.GetUserFollowersCallback callback);

    void loadUserFollowing(@NonNull String username, DataCallbacks.GetUserFollowingCallback callback);

}
