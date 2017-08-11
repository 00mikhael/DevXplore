package com.example.gravity.devxplore.data;

import android.support.annotation.NonNull;

import com.example.gravity.devxplore.data.network.NetworkManager;

/**
 * Created by gravity on 7/29/17.
 */

@SuppressWarnings("ALL")
public interface DMHelper {


    void loadUsers(@NonNull String location, @NonNull String language, NetworkManager.GetUserListCallback callback);

    void getUser(@NonNull String username, NetworkManager.GetUserCallback callback);

    void getUserEvents(@NonNull String username, NetworkManager.GetUserEventsCallback callback);

    void getUserRepos(@NonNull String username, NetworkManager.GetUserReposCallback callback);

    void getUserStarredRepos(@NonNull String username, NetworkManager.GetUserStarredCallback callback);

    void getUserFollowers(@NonNull String username, NetworkManager.GetUserFollowersCallback callback);

    void getUserFollowing(@NonNull String  username, NetworkManager.GetUserFollowingCallback callback);


}
