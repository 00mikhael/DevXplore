package com.example.gravity.devxplore.data.network;

import android.support.annotation.NonNull;

/**
 * Created by gravity on 8/2/17.
 */

public interface NMHelper {
    void loadUsers(NetworkManager.GetUserListCallback callback);

    void loadRepos();

    void getUser(@NonNull String username);
}
