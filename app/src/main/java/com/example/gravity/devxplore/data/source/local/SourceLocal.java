package com.example.gravity.devxplore.data.source.local;

import android.support.annotation.NonNull;

import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.model.UserDetails;
import com.example.gravity.devxplore.data.source.DataCallbacks;

import java.util.List;

/**
 * Created by gravity on 8/23/17.
 */

public interface SourceLocal {
    void loadAllUsers(@NonNull DataCallbacks.GetUserListCallback callback);

    void saveAllUsers(@NonNull List<User> users);

    void saveUser(@NonNull String username, @NonNull String avatarUrl, boolean isFavourite);

    void updateFavUser(@NonNull String username, boolean isFavourite);

    void deleteAllUsers();

    void loadAllFavourites(@NonNull DataCallbacks.GetUserListCallback callback);

    void loadUserDetails(@NonNull String username,@NonNull DataCallbacks.GetUserCallback callback);

    void saveUserDetails(@NonNull UserDetails userDetails);

    void updateFavUserDetails(@NonNull String username, boolean isFavourite);

    void deleteAllUserDetails();
}
