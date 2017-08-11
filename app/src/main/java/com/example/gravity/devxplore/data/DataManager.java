package com.example.gravity.devxplore.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.gravity.devxplore.data.model.Repository;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.model.UserDetails;
import com.example.gravity.devxplore.data.model.UserEvents;
import com.example.gravity.devxplore.data.network.NMHelper;
import com.example.gravity.devxplore.data.network.NetworkManager;
import com.example.gravity.devxplore.screens.base.Home.HomePresenter;

import java.util.List;

/**
 * Created by gravity on 7/29/17.
 */

@SuppressWarnings("ALL")
public class DataManager implements DMHelper {

    @Nullable
    private static DataManager INSTANCE = null;

    @NonNull
    private final NMHelper mNetworkManager;

    private HomePresenter mHomePresenter;


    private DataManager(@NonNull NMHelper mNetworkManager) {
        this.mNetworkManager = mNetworkManager;
    }

    @Nullable
    public static DataManager getInstance(@NonNull NMHelper mNetworkManager) {
        if (INSTANCE == null) {
            INSTANCE = new DataManager(mNetworkManager);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void loadUsers(@NonNull String location, @NonNull String language, @NonNull final NetworkManager.GetUserListCallback callback) {
        mNetworkManager.loadUsers(location, language, new NetworkManager.GetUserListCallback() {
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
    public void getUser(@NonNull String username, @NonNull final NetworkManager.GetUserCallback callback) {
        mNetworkManager.getUser(username, new NetworkManager.GetUserCallback() {
            @Override
            public void onSuccess(UserDetails user) {
                callback.onSuccess(user);
            }

            @Override
            public void onError(Throwable t) {
                callback.onError(t);
            }
        });
    }

    @Override
    public void getUserEvents(@NonNull String username, @NonNull final NetworkManager.GetUserEventsCallback callback) {
        mNetworkManager.getUserEvents(username, new NetworkManager.GetUserEventsCallback() {
            @Override
            public void onSuccess(List<UserEvents> events) {
                callback.onSuccess(events);
            }

            @Override
            public void onError(Throwable t) {
                callback.onError(t);
            }
        });
    }

    @Override
    public void getUserRepos(@NonNull String username, @NonNull final NetworkManager.GetUserReposCallback callback) {
        mNetworkManager.getUserRepos(username, new NetworkManager.GetUserReposCallback() {
            @Override
            public void onSuccess(List<Repository> userRepos) {
                callback.onSuccess(userRepos);
            }

            @Override
            public void onError(Throwable t) {
                callback.onError(t);
            }
        });
    }

    @Override
    public void getUserStarredRepos(@NonNull String username, @NonNull final NetworkManager.GetUserStarredCallback callback) {
        mNetworkManager.getUserStarredRepos(username, new NetworkManager.GetUserStarredCallback() {
            @Override
            public void onSuccess(List<Repository> userStarredRepos) {
                callback.onSuccess(userStarredRepos);
            }

            @Override
            public void onError(Throwable t) {
                callback.onError(t);
            }
        });
    }

    @Override
    public void getUserFollowers(@NonNull String username, @NonNull final NetworkManager.GetUserFollowersCallback callback) {
        mNetworkManager.getUserFollowers(username, new NetworkManager.GetUserFollowersCallback() {
            @Override
            public void onSuccess(List<User> followers) {
                callback.onSuccess(followers);
            }

            @Override
            public void onError(Throwable t) {
                callback.onError(t);
            }
        });
    }

    @Override
    public void getUserFollowing(@NonNull String username, @NonNull final NetworkManager.GetUserFollowingCallback callback) {
        mNetworkManager.getUserFollowing(username, new NetworkManager.GetUserFollowingCallback() {
            @Override
            public void onSuccess(List<User> following) {
                callback.onSuccess(following);
            }

            @Override
            public void onError(Throwable t) {
                callback.onError(t);
            }
        });
    }
}
