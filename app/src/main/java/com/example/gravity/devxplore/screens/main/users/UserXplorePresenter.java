package com.example.gravity.devxplore.screens.main.users;

import android.support.annotation.NonNull;

import com.example.gravity.devxplore.data.DataManager;
import com.example.gravity.devxplore.data.model.Repository;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.network.NetworkManager;

import java.util.List;

/**
 * Created by gravity on 8/5/17.
 */

public class UserXplorePresenter implements UserXploreContract.Presenter {

    DataManager mDataManager;
    UserXploreContract.XploreView1 mXploreView1;
    UserXploreContract.XploreView2 mXploreView2;


    public UserXplorePresenter() {}

    public UserXplorePresenter(@NonNull DataManager dataManager,@NonNull UserXploreContract.XploreView1 xploreView1) {
        this.mDataManager = dataManager;
        this.mXploreView1 = xploreView1;

        mXploreView1.setPresenter(this);
    }

    public UserXplorePresenter(@NonNull DataManager dataManager, @NonNull UserXploreContract.XploreView2 xploreView2) {
        this.mDataManager = dataManager;
        this.mXploreView2 = xploreView2;

        xploreView2.setPresenter(this);
    }

    @Override
    public void start() {
        loadUsers();
    }

    @Override
    public void loadUsers() {
        mDataManager.loadUsers(new NetworkManager.GetUserListCallback() {
            @Override
            public void onSuccess(List<User> users) {
                mXploreView2.showUsers(users);
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }

    @Override
    public void openUserDetails(@NonNull User viewUser) {

    }

    @Override
    public void loadSlideRepos(boolean updating) {

    }

    @Override
    public void setSlideRepos(List<Repository> repos) {

    }
}
