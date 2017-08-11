package com.example.gravity.devxplore.data.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.gravity.devxplore.data.DMHelper;
import com.example.gravity.devxplore.data.model.Repository;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.model.UserDetails;
import com.example.gravity.devxplore.data.model.UserEvents;
import com.example.gravity.devxplore.data.model.UserResponse;
import com.example.gravity.devxplore.screens.base.Home.HomeContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gravity on 7/29/17.
 */

@SuppressWarnings("ALL")
public class NetworkManager implements NMHelper {

    private static final String TAG = "GOOOOOOAAAAAAL";

    private static NetworkManager INSTANCE;

    SearchService searchService;

    DMHelper dataManager;
    private static final int SERVICE_LATENCY_IN_MILLIS = 5000;

    private SearchService isearchService = ApiClient.getClient().create(SearchService.class);
    private HomeContract.Presenter mUsersXPlorePresenter;

    public static NetworkManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NetworkManager();
        }
        return INSTANCE;
    }

    private NetworkManager() {

    }

    private NetworkManager(@NonNull DMHelper dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void loadUsers(@NonNull String location, @NonNull String language, @NonNull final GetUserListCallback callback) {
        searchService = ApiClient.getClient().create(SearchService.class);
        Call<UserResponse> developersResponseCall = searchService.getUsersResponse(("location:"+location+"+language:"+language));
        developersResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                List<User> users = response.body().getDevItems();
                Log.d(TAG, "onResponse: " + users.toString());
                callback.onSuccess(users);
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                callback.onError(t);
            }
        });
    }

    @Override
    public void getUser(@NonNull String username, @NonNull final GetUserCallback callback) {
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<UserDetails> userDetailsCall = userService.getUser(username);
        userDetailsCall.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(@NonNull Call<UserDetails> call, @NonNull Response<UserDetails> response) {
                UserDetails user = response.body();
                callback.onSuccess(user);
            }

            @Override
            public void onFailure(@NonNull Call<UserDetails> call, @NonNull Throwable t) {
                callback.onError(t);
            }
        });
    }

    @Override
    public void getUserEvents(@NonNull String username, @NonNull final GetUserEventsCallback callback) {
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<List<UserEvents>> userEventsCall = userService.getUserEvents(username);
        userEventsCall.enqueue(new Callback<List<UserEvents>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserEvents>> call, @NonNull Response<List<UserEvents>> response) {
                List<UserEvents> events = response.body();
                callback.onSuccess(events);
            }

            @Override
            public void onFailure(@NonNull Call<List<UserEvents>> call, @NonNull Throwable t) {
                callback.onError(t);
            }
        });
    }

    @Override
    public void getUserRepos(@NonNull String username, @NonNull final GetUserReposCallback callback) {
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<List<Repository>> userRepos = userService.getUserRepos(username);
        userRepos.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(@NonNull Call<List<Repository>> call, @NonNull Response<List<Repository>> response) {
                List<Repository> userRepos = response.body();
                callback.onSuccess(userRepos);
            }

            @Override
            public void onFailure(@NonNull Call<List<Repository>> call, @NonNull Throwable t) {
                callback.onError(t);
            }
        });

    }

    @Override
    public void getUserStarredRepos(@NonNull String username, @NonNull final GetUserStarredCallback callback) {
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<List<Repository>> userStarred = userService.getUserStarred(username);
        userStarred.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(@NonNull Call<List<Repository>> call, @NonNull Response<List<Repository>> response) {
                List<Repository> userStarredRepos = response.body();
                callback.onSuccess(userStarredRepos);
            }

            @Override
            public void onFailure(@NonNull Call<List<Repository>> call, @NonNull Throwable t) {
                callback.onError(t);
            }
        });
    }

    @Override
    public void getUserFollowers(@NonNull String username, @NonNull final GetUserFollowersCallback callback) {
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<List<User>> userFollowers = userService.getUserFollowers(username);
        userFollowers.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                List<User> followers = response.body();
                callback.onSuccess(followers);
            }

            @Override
                public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                callback.onError(t);
            }
        });
    }

    @Override
    public void getUserFollowing(@NonNull String username, @NonNull final GetUserFollowingCallback callback) {
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<List<User>> userFollowing = userService.getUserFollowing(username);
        userFollowing.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                List<User> following = response.body();
                callback.onSuccess(following);
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                callback.onError(t);
            }
        });
    }

    public interface GetUserListCallback{
        void onSuccess(List<User> users);

        void onError(Throwable t);
    }

    public interface GetUserCallback{
        void onSuccess(UserDetails user);

        void onError(Throwable t);
    }

    public interface GetUserEventsCallback{
        void onSuccess(List<UserEvents> events);

        void onError(Throwable t);
    }

    public interface GetUserReposCallback{
        void onSuccess(List<Repository> userRepos);

        void onError(Throwable t);
    }

    public interface GetUserStarredCallback{
        void onSuccess(List<Repository> userStarredRepos);

        void onError(Throwable t);
    }

    public interface GetUserFollowingCallback{
        void onSuccess(List<User> following);

        void onError(Throwable t);
    }

    public interface GetUserFollowersCallback{
        void onSuccess(List<User> followers);

        void onError(Throwable t);
    }
}
