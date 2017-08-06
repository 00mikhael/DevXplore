package com.example.gravity.devxplore.data;

import android.support.annotation.NonNull;

import com.example.gravity.devxplore.data.network.NetworkManager;

/**
 * Created by gravity on 7/29/17.
 */

public interface DMHelper {


    void loadUsers(NetworkManager.GetUserListCallback callback);

    void loadRepos();

    void getUser(@NonNull String username);
}
