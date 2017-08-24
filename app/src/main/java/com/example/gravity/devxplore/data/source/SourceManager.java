package com.example.gravity.devxplore.data.source;

import android.support.annotation.NonNull;

/**
 * Created by gravity on 8/23/17.
 */

public interface SourceManager {
    void getUsers(DataCallbacks.GetUserListCallback callback);

    void getUsers(@NonNull String location, @NonNull String language, DataCallbacks.GetUserListCallback callback);

    void getUser(@NonNull String username, DataCallbacks.GetUserCallback callback);

    void getPopularRepos(@NonNull String username, DataCallbacks.GetUserReposCallback callback);

    void getUserRepos(@NonNull String username, DataCallbacks.GetUserReposCallback callback);

    void getUserStarredRepos(@NonNull String username, DataCallbacks.GetUserStarredCallback callback);

    void getUserFollowers(@NonNull String username, DataCallbacks.GetUserFollowersCallback callback);

    void getUserFollowing(@NonNull String username, DataCallbacks.GetUserFollowingCallback callback);

    void updateFavourite(@NonNull String username, boolean isFavourite);

    boolean checkNetwork();

    void getFavourites(@NonNull DataCallbacks.GetUserListCallback callback);
}
