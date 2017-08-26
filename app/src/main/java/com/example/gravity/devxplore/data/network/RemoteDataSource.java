package com.example.gravity.devxplore.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.Resource;
import com.example.gravity.devxplore.data.model.Repository;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.model.UserDetails;
import com.example.gravity.devxplore.data.model.UserResponse;
import com.example.gravity.devxplore.data.network.github.ApiClient;
import com.example.gravity.devxplore.data.network.github.SearchService;
import com.example.gravity.devxplore.data.network.github.UserService;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.data;

/**
 * Created by gravity on 7/29/17.
 */

@SuppressWarnings("ALL")
public class RemoteDataSource implements SourceRemote {

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
    public LiveData<List<User>> loadUsers(@NonNull String location, @NonNull String language) {
        mSearchService = ApiClient.getClient().create(SearchService.class);
        Call<LiveData<UserResponse>> usersResponse = mSearchService.loadUserResponse(("location:"+location+"+language:"+language));
        usersResponse.enqueue(new Callback<LiveData<UserResponse>>() {
            @Override
            public void onResponse(@NonNull Call<LiveData<UserResponse>> call, @NonNull Response<LiveData<UserResponse>> response) {
                LiveData<List<User>> users = response.body().getUserItems();

            }

            @Override
            public void onFailure(@NonNull Call<LiveData<UserResponse>> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public LiveData<User> loadUser(@NonNull String username) {
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<LiveData<UserDetails>> userDetailsCall = userService.loadUser(username);
        userDetailsCall.enqueue(new Callback<LiveData<UserDetails>>() {
            @Override
            public void onResponse(@NonNull Call<LiveData<UserDetails>> call, @NonNull Response<LiveData<UserDetails>> response) {
                LiveData<UserDetails> user = response.body();

            }

            @Override
            public void onFailure(@NonNull Call<LiveData<UserDetails>> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public LiveData<List<Repository>> loadPopularRepos(@NonNull String username) {
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<List<List<Repository>>> userPopularRepos = userService.loadPopularRepos(username);
        userPopularRepos.enqueue(new Callback<LiveData<List<Repository>>>() {
            @Override
            public void onResponse(@NonNull Call<List<Repository>> call, @NonNull Response<List<Repository>> response) {
                List<Repository> popularRepos = response.body();
                boolean is = response.isSuccessful();
                int code = response.code();
                int body = response.code();
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
        Call<List<Repository>> userRepos = userService.loadUserRepos(username);
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
        Call<List<Repository>> userStarred = userService.loadUserStarred(username);
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
        Call<List<User>> userFollowers = userService.loadUserFollowers(username);
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
        Call<List<User>> userFollowing = userService.loadUserFollowing(username);
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
