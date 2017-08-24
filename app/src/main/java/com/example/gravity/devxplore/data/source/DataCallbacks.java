package com.example.gravity.devxplore.data.source;

import com.example.gravity.devxplore.data.model.Repository;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.model.UserDetails;

import java.util.List;

/**
 * Created by gravity on 8/23/17.
 */

public interface DataCallbacks {
    interface GetUserListCallback {
        void onDataLoaded(List<User> users);

        void onDataNotAvailable();
    }

    interface GetUserCallback {
        void onDataLoaded(UserDetails user);

        void onDataNotAvailable();
    }

    interface GetUserReposCallback {
        void onDataLoaded(List<Repository> userRepos);

        void onDataNotAvailable();
    }

    interface GetUserStarredCallback {
        void onDataLoaded(List<Repository> userStarredRepos);

        void onDataNotAvailable();
    }

    interface GetUserFollowingCallback {
        void onDataLoaded(List<User> following);

        void onDataNotAvailable();
    }

    interface GetUserFollowersCallback {
        void onDataLoaded(List<User> followers);

        void onDataNotAvailable();
    }
}
