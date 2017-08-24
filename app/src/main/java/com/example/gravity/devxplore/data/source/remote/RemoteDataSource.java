package com.example.gravity.devxplore.data.source.remote;

import android.support.annotation.NonNull;

import com.example.gravity.devxplore.data.model.Repository;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.model.UserDetails;
import com.example.gravity.devxplore.data.model.UserResponse;
import com.example.gravity.devxplore.data.source.DataCallbacks;
import com.example.gravity.devxplore.data.source.remote.apis.ApiClient;
import com.example.gravity.devxplore.data.source.remote.apis.SearchService;
import com.example.gravity.devxplore.data.source.remote.apis.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gravity on 7/29/17.
 */

@SuppressWarnings("ALL")
public class RemoteDataSource implements SourceRemote, DataCallbacks {

    private static RemoteDataSource INSTANCE;
    private SearchService mSearchService;

    private static final int SERVICE_LATENCY_IN_MILLIS = 5000;


    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }

    private RemoteDataSource() {}

    @Override
    public void loadUsers(@NonNull String location, @NonNull String language, @NonNull final GetUserListCallback callback) {
        mSearchService = ApiClient.getClient().create(SearchService.class);
        Call<UserResponse> usersResponse = mSearchService.getUsersResponse(("location:"+location+"+language:"+language));
        usersResponse.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                List<User> users = response.body().getDevItems();
                callback.onDataLoaded(users);
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void loadUser(@NonNull String username, @NonNull final GetUserCallback callback) {
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<UserDetails> userDetailsCall = userService.getUser(username);
        userDetailsCall.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(@NonNull Call<UserDetails> call, @NonNull Response<UserDetails> response) {
                UserDetails user = response.body();
                callback.onDataLoaded(user);
            }

            @Override
            public void onFailure(@NonNull Call<UserDetails> call, @NonNull Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void loadPopularRepos(@NonNull String username, final GetUserReposCallback callback) {
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<List<Repository>> userPopularRepos = userService.getPopularRepos(username);
        userPopularRepos.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(@NonNull Call<List<Repository>> call, @NonNull Response<List<Repository>> response) {
                List<Repository> popularRepos = response.body();
                callback.onDataLoaded(popularRepos);
            }

            @Override
            public void onFailure(@NonNull Call<List<Repository>> call, @NonNull Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void loadUserRepos(@NonNull String username, @NonNull final GetUserReposCallback callback) {
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<List<Repository>> userRepos = userService.getUserRepos(username);
        userRepos.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(@NonNull Call<List<Repository>> call, @NonNull Response<List<Repository>> response) {
                List<Repository> userRepos = response.body();
                callback.onDataLoaded(userRepos);
            }

            @Override
            public void onFailure(@NonNull Call<List<Repository>> call, @NonNull Throwable t) {
                callback.onDataNotAvailable();
            }
        });

    }

    @Override
    public void loadUserStarredRepos(@NonNull String username, @NonNull final GetUserStarredCallback callback) {
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<List<Repository>> userStarred = userService.getUserStarred(username);
        userStarred.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(@NonNull Call<List<Repository>> call, @NonNull Response<List<Repository>> response) {
                List<Repository> userStarredRepos = response.body();
                callback.onDataLoaded(userStarredRepos);
            }

            @Override
            public void onFailure(@NonNull Call<List<Repository>> call, @NonNull Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void loadUserFollowers(@NonNull String username, @NonNull final GetUserFollowersCallback callback) {
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<List<User>> userFollowers = userService.getUserFollowers(username);
        userFollowers.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                List<User> followers = response.body();
                callback.onDataLoaded(followers);
            }

            @Override
                public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void loadUserFollowing(@NonNull String username, @NonNull final GetUserFollowingCallback callback) {
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<List<User>> userFollowing = userService.getUserFollowing(username);
        userFollowing.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                List<User> following = response.body();
                callback.onDataLoaded(following);
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }
}
