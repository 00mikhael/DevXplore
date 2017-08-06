package com.example.gravity.devxplore.data.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.gravity.devxplore.data.DMHelper;
import com.example.gravity.devxplore.data.model.RepositoriesResponse;
import com.example.gravity.devxplore.data.model.Repository;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.model.UserResponse;
import com.example.gravity.devxplore.screens.main.users.UserXploreContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gravity on 7/29/17.
 */

public class NetworkManager implements NMHelper {

    private static final String TAG = "GOOOOOOAAAAAAL";

    private static NetworkManager INSTANCE;

    DMHelper dataManager;
    private static final int SERVICE_LATENCY_IN_MILLIS = 5000;

    private SearchService isearchService = ApiClient.getClient().create(SearchService.class);
    private UserXploreContract.Presenter mUsersXPlorePresenter;

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
    public void loadUsers(final GetUserListCallback callback) {
        SearchService searchService = ApiClient.getClient().create(SearchService.class);
        Call<UserResponse> developersResponseCall = searchService.getUsersResponse("location:lagos+language:java");
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
    public void getUser(@NonNull String username) {

    }

    @Override
    public void loadRepos() {
        Call<RepositoriesResponse> repositoriesResponseCall = isearchService.getRepositoriesResponse("language:java+created:>2017-07-06+stars:>200");
        repositoriesResponseCall.enqueue(new Callback<RepositoriesResponse>() {
            @Override
            public void onResponse( @NonNull Call<RepositoriesResponse> call,@NonNull Response<RepositoriesResponse> response) {
                List<Repository> repositories = response.body().getRepoItems();
                mUsersXPlorePresenter.setSlideRepos(repositories);
            }

            @Override
            public void onFailure(@NonNull Call<RepositoriesResponse> call,@NonNull Throwable t) {

            }
        });
    }

    public interface GetUserListCallback{
        void onSuccess(List<User> users);

        void onError(Throwable t);
    }
}
