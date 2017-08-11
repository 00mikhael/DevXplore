package com.example.gravity.devxplore.screens.details;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.gravity.devxplore.data.DataManager;
import com.example.gravity.devxplore.data.model.Repository;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.model.UserDetails;
import com.example.gravity.devxplore.data.model.UserEvents;
import com.example.gravity.devxplore.data.network.NetworkManager;

import java.util.List;

/**
 * Created by gravity on 8/5/17.
 */

@SuppressWarnings("ALL")
public class DetailsPresenter implements DetailsContract.Presenter {

    final DataManager mDataManager;
    DetailsContract.MainDetailView mMainDetailView;
    DetailsContract.DetailView1 mDetailView1;
    DetailsContract.DetailView2 mDetailView2;
    DetailsContract.DetailView3 mDetailView3;
    DetailsContract.DetailView4 mDetailView4;
    DetailsContract.DetailView5 mDetailView5;

    public DetailsPresenter(@NonNull DataManager dataManager, @NonNull DetailsContract.MainDetailView mainDetailView) {
        this.mDataManager = dataManager;
        this.mMainDetailView = mainDetailView;

        mainDetailView.setPresenter(this);
    }

    public DetailsPresenter(@NonNull DataManager dataManager, @NonNull DetailsContract.DetailView1 detailView1) {
        this.mDataManager = dataManager;
        this.mDetailView1 = detailView1;

        detailView1.setPresenter(this);
    }

    public DetailsPresenter(@NonNull DataManager dataManager, @NonNull DetailsContract.DetailView2 detailView2) {
        this.mDataManager = dataManager;
        this.mDetailView2 = detailView2;

        detailView2.setPresenter(this);
    }

    public DetailsPresenter(@NonNull DataManager dataManager, @NonNull DetailsContract.DetailView3 detailView3) {
        this.mDataManager = dataManager;
        this.mDetailView3 = detailView3;

        detailView3.setPresenter(this);
    }

    public DetailsPresenter(@NonNull DataManager dataManager, @NonNull DetailsContract.DetailView4 detailView4) {
        this.mDataManager = dataManager;
        this.mDetailView4 = detailView4;

        detailView4.setPresenter(this);
    }

    public DetailsPresenter(@NonNull DataManager dataManager, @NonNull DetailsContract.DetailView5 detailView5) {
        this.mDataManager = dataManager;
        this.mDetailView5 = detailView5;

        detailView5.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadUserDetails(@NonNull String username) {
        mDataManager.getUser(username, new NetworkManager.GetUserCallback() {
            @Override
            public void onSuccess(UserDetails user) {
                mMainDetailView.showUserDetails(user);
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }

    @Override
    public void loadOverview(@NonNull String username) {
        mDataManager.getUserEvents(username, new NetworkManager.GetUserEventsCallback() {
            @Override
            public void onSuccess(List<UserEvents> events) {
                mDetailView1.showOverview(events);
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }

    @Override
    public void loadRepos(@NonNull String username) {
        mDataManager.getUserRepos(username, new NetworkManager.GetUserReposCallback() {
            @Override
            public void onSuccess(List<Repository> userRepos) {
                mDetailView2.showRepos(userRepos);
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }

    @Override
    public void loadStarredRepos(@NonNull String username) {
        mDataManager.getUserStarredRepos(username, new NetworkManager.GetUserStarredCallback() {
            @Override
            public void onSuccess(List<Repository> userStarredRepos) {
                mDetailView3.showStarred(userStarredRepos);
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }

    @Override
    public void loadFollowing(@NonNull String username) {
        mDataManager.getUserFollowing(username, new NetworkManager.GetUserFollowingCallback() {
            @Override
            public void onSuccess(List<User> following) {
                mDetailView4.showFollowing(following);
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }

    @Override
    public void loadFollowers(@NonNull String username) {
        mDataManager.getUserFollowers(username, new NetworkManager.GetUserFollowersCallback() {
            @Override
            public void onSuccess(List<User> followers) {
                mDetailView5.showFollowers(followers);
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }

    @Override
    public void updateFavourite(User user, boolean isFavourite) {

    }

    @Override
    public void openUserDetails(@NonNull Context context, @NonNull String username) {
        context.startActivity(DetailsActivity.createIntent(context, username));
    }
}
