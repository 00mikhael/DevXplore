package com.example.gravity.devxplore.data;

import android.support.annotation.NonNull;

import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.network.NMHelper;
import com.example.gravity.devxplore.data.network.NetworkManager;
import com.example.gravity.devxplore.screens.main.users.UserXplorePresenter;

import java.util.List;

/**
 * Created by gravity on 7/29/17.
 */

public class DataManager implements DMHelper {

    private static DataManager INSTANCE = null;

    private final NMHelper mNetworkManager;

    private UserXplorePresenter mUserXplorePresenter;


    private DataManager(@NonNull NMHelper mNetworkManager) {
        this.mNetworkManager = mNetworkManager;
    }

    public static DataManager getInstance(NMHelper mNetworkManager) {
        if (INSTANCE == null) {
            INSTANCE = new DataManager(mNetworkManager);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void loadUsers(final NetworkManager.GetUserListCallback callback) {
        mNetworkManager.loadUsers(new NetworkManager.GetUserListCallback() {
            @Override
            public void onSuccess(List<User> users) {
                callback.onSuccess(users);
            }

            @Override
            public void onError(Throwable t) {
                callback.onError(t);
            }
        });
    }

    @Override
    public void getUser(@NonNull String username) {

    }

    @Override
    public void loadRepos() {
        mNetworkManager.loadRepos();
    }
}
