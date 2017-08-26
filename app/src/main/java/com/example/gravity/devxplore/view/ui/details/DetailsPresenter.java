package com.example.gravity.devxplore.view.ui.details;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.gravity.devxplore.data.model.Repository;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.model.UserDetails;
import com.example.gravity.devxplore.data.DataRepository;

import java.util.List;

/**
 * Created by gravity on 8/5/17.
 */

@SuppressWarnings("ALL")
public class DetailsPresenter implements DetailsContract.Presenter {

    public final static String TAG = "OverviewPresenter";

    final DataRepository mDataRepository;
    DetailsContract.MainDetailView mMainDetailView;
    DetailsContract.DetailView1 mDetailView1;
    DetailsContract.DetailView2 mDetailView2;
    DetailsContract.DetailView3 mDetailView3;
    DetailsContract.DetailView4 mDetailView4;
    DetailsContract.DetailView5 mDetailView5;

    public DetailsPresenter(@NonNull DataRepository dataRepositoryManager, @NonNull DetailsContract.MainDetailView mainDetailView) {
        this.mDataRepository = dataRepositoryManager;
        this.mMainDetailView = mainDetailView;

        mainDetailView.setPresenter(this);
    }

    public DetailsPresenter(@NonNull DataRepository dataRepositoryManager, @NonNull DetailsContract.DetailView1 detailView1) {
        this.mDataRepository = dataRepositoryManager;
        this.mDetailView1 = detailView1;

        detailView1.setPresenter(this);
    }

    public DetailsPresenter(@NonNull DataRepository dataRepositoryManager, @NonNull DetailsContract.DetailView2 detailView2) {
        this.mDataRepository = dataRepositoryManager;
        this.mDetailView2 = detailView2;

        detailView2.setPresenter(this);
    }

    public DetailsPresenter(@NonNull DataRepository dataRepositoryManager, @NonNull DetailsContract.DetailView3 detailView3) {
        this.mDataRepository = dataRepositoryManager;
        this.mDetailView3 = detailView3;

        detailView3.setPresenter(this);
    }

    public DetailsPresenter(@NonNull DataRepository dataRepositoryManager, @NonNull DetailsContract.DetailView4 detailView4) {
        this.mDataRepository = dataRepositoryManager;
        this.mDetailView4 = detailView4;

        detailView4.setPresenter(this);
    }

    public DetailsPresenter(@NonNull DataRepository dataRepositoryManager, @NonNull DetailsContract.DetailView5 detailView5) {
        this.mDataRepository = dataRepositoryManager;
        this.mDetailView5 = detailView5;

        detailView5.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadUserDetails(@NonNull String username) {
        mDataRepository.getUser(username, new DataCallbacks.GetUserCallback() {
            @Override
            public void onDataLoaded(UserDetails user) {
                mMainDetailView.showUserDetails(user);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void loadOverview(@NonNull String username) {
        Log.e(TAG, username);
        mDataRepository.getPopularRepos(username, new DataCallbacks.GetUserReposCallback() {
            @Override
            public void onDataLoaded(List<Repository> popularRepos) {
                mDetailView1.showOverview(popularRepos);

            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }



    @Override
    public void loadRepos(@NonNull String username) {
        mDataRepository.getUserRepos(username, new DataCallbacks.GetUserReposCallback() {
            @Override
            public void onDataLoaded(List<Repository> userRepos) {
                mDetailView2.showRepos(userRepos);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void loadStarredRepos(@NonNull String username) {
        mDataRepository.getUserStarredRepos(username, new DataCallbacks.GetUserStarredCallback() {
            @Override
            public void onDataLoaded(List<Repository> userStarredRepos) {
                mDetailView3.showStarred(userStarredRepos);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void loadFollowing(@NonNull String username) {
        mDataRepository.getUserFollowing(username, new DataCallbacks.GetUserFollowingCallback() {
            @Override
            public void onDataLoaded(List<User> following) {
                mDetailView4.showFollowing(following);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void loadFollowers(@NonNull String username) {
        mDataRepository.getUserFollowers(username, new DataCallbacks.GetUserFollowersCallback() {
            @Override
            public void onDataLoaded(List<User> followers) {
                mDetailView5.showFollowers(followers);
            }

            @Override
            public void onDataNotAvailable() {

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
