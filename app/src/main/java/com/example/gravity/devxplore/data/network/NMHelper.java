package com.example.gravity.devxplore.data.network;

import android.support.annotation.NonNull;

/**
 * Created by gravity on 8/2/17.
 */

@SuppressWarnings("ALL")
public interface NMHelper {
    void loadUsers(@NonNull String location, @NonNull String language, NetworkManager.GetUserListCallback callback);

    void getUser(@NonNull String username, NetworkManager.GetUserCallback callback);

    void getUserEvents(@NonNull String username, NetworkManager.GetUserEventsCallback callback);

    void getUserRepos(@NonNull String username, NetworkManager.GetUserReposCallback callback);

    void getUserStarredRepos(@NonNull String username, NetworkManager.GetUserStarredCallback callback);

    void getUserFollowers(@NonNull String username, NetworkManager.GetUserFollowersCallback callback);

    void getUserFollowing(@NonNull String  username, NetworkManager.GetUserFollowingCallback callback);
}
