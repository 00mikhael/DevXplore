package com.example.gravity.devxplore.screens.details;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.gravity.devxplore.screens.base.BasePresenter;
import com.example.gravity.devxplore.screens.base.BaseView;
import com.example.gravity.devxplore.data.model.Repository;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.model.UserDetails;
import com.example.gravity.devxplore.data.model.UserEvents;

import java.util.List;

/**
 * Created by gravity on 8/6/17.
 */

@SuppressWarnings("ALL")
public interface DetailsContract {

    interface Presenter extends BasePresenter {

        void loadUserDetails(@NonNull String username);

        void openUserDetails(Context context, @NonNull String username);

        void loadOverview(@NonNull String username);

        void loadRepos(@NonNull String username);

        void loadStarredRepos(@NonNull String username);

        void loadFollowing(@NonNull String username);

        void loadFollowers(@NonNull String username);

        void updateFavourite(User user, boolean isFavourite);
    }


    interface MainDetailView extends BaseView<Presenter> {

        void showLoadingIndicator(boolean isLoading);

        void showUserDetails(UserDetails user);

    }

    interface DetailView1 extends BaseView<Presenter> {

        void showOverview(List<UserEvents> userEvents);

    }

    interface DetailView2 extends BaseView<Presenter> {

        void showRepos(List<Repository> repositories);

    }

    interface DetailView3 extends BaseView<Presenter> {

        void showStarred(List<Repository> repositories);

    }

    interface DetailView4 extends BaseView<Presenter> {

        void showFollowing(List<User> following);

    }

    interface DetailView5 extends BaseView<Presenter> {

        void showFollowers(List<User> followers);

    }

}
