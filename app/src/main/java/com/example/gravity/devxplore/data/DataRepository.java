package com.example.gravity.devxplore.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.model.UserDetails;
import com.example.gravity.devxplore.data.network.SourceRemote;

import java.util.List;

/**
 * Created by gravity on 7/29/17.
 */

@SuppressWarnings("ALL")
public class DataRepository implements SourceManager, DataCallbacks {

    @Nullable
    private static DataRepository INSTANCE = null;

    private SourceRemote mRemoteDataSource;
    private SourceLocal mLocalDataSource;

    private List<User> mUser;

    private DataRepository(@NonNull SourceRemote mRemoteDataSource,
                            @NonNull SourceLocal mLocalDataSource) {
        this.mRemoteDataSource = mRemoteDataSource;
        this.mLocalDataSource = mLocalDataSource;
    }

    @Nullable
    public static DataRepository getInstance(@NonNull SourceRemote mRemoteDataStore,
                                             @NonNull SourceLocal mLocalDataSource) {

        if (INSTANCE == null) {
            INSTANCE = new DataRepository(mRemoteDataStore, mLocalDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getUsers(final GetUserListCallback callback) {
        mLocalDataSource.loadAllUsers(new GetUserListCallback() {
            @Override
            public void onDataLoaded(List<User> users) {
                callback.onDataLoaded(users);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getUsers(@NonNull String location, @NonNull String language, final GetUserListCallback callback) {
        if (!checkNetwork()) {
            getUsers(callback);
            return;
        }
        mRemoteDataSource.loadUsers(location, language, new GetUserListCallback() {
            @Override
            public void onDataLoaded(List<User> users) {
                mLocalDataSource.saveAllUsers(users);
                callback.onDataLoaded(users);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getUser(@NonNull String username, final GetUserCallback callback) {
        if (!checkNetwork()) {
            mLocalDataSource.loadUserDetails(username, new GetUserCallback() {
                @Override
                public void onDataLoaded(UserDetails user) {
                    callback.onDataLoaded(user);
                }

                @Override
                public void onDataNotAvailable() {
                    callback.onDataNotAvailable();
                }
            });
            return;
        }
        mRemoteDataSource.loadUser(username, new GetUserCallback() {
            @Override
            public void onDataLoaded(UserDetails user) {
                mLocalDataSource.saveUserDetails(user);
                callback.onDataLoaded(user);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getPopularRepos(@NonNull String username, GetUserReposCallback callback) {
        if (!checkNetwork()) {
            return;
        }
        mRemoteDataSource.loadPopularRepos(username, callback);
    }

    @Override
    public void getUserRepos(@NonNull String username, GetUserReposCallback callback) {
        if (!checkNetwork()) {
            return;
        }
        mRemoteDataSource.loadUserRepos(username, callback);
    }

    @Override
    public void getUserStarredRepos(@NonNull String username, GetUserStarredCallback callback) {
        if (!checkNetwork()) {
            return;
        }
        mRemoteDataSource.loadUserStarredRepos(username, callback);
    }

    @Override
    public void getUserFollowers(@NonNull String username, GetUserFollowersCallback callback) {
        if (!checkNetwork()) {
            return;
        }
        mRemoteDataSource.loadUserFollowers(username, callback);
    }

    @Override
    public void getUserFollowing(@NonNull String username, GetUserFollowingCallback callback) {
        if (!checkNetwork()) {
            return;
        }
        mRemoteDataSource.loadUserFollowing(username, callback);
    }

    @Override
    public void getFavourites(@NonNull final GetUserListCallback callback) {
        mLocalDataSource.loadAllFavourites(new GetUserListCallback() {
            @Override
            public void onDataLoaded(List<User> favourites) {
                callback.onDataLoaded(favourites);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public boolean checkNetwork() {
        return false;
    }

    @Override
    public void updateFavourite(@NonNull String username, boolean isFavourite) {
        mLocalDataSource.updateFavUser(username, isFavourite);
        mLocalDataSource.updateFavUserDetails(username, isFavourite);
    }
}
