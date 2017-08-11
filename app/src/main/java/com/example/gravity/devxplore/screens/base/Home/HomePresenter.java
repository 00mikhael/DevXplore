package com.example.gravity.devxplore.screens.base.Home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.gravity.devxplore.data.DataManager;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.network.NetworkManager;
import com.example.gravity.devxplore.screens.details.DetailsActivity;

import java.util.List;

/**
 * Created by gravity on 8/5/17.
 */

public class HomePresenter implements HomeContract.Presenter {

    @NonNull
    public static final String TAG = "HomePresenter";

    @Nullable
    private String mSearchLanguage = null;
    @Nullable
    private String mSearchLocation = null;
    private final DataManager mDataManager;
    private final HomeContract.XploreView mXploreView;

    @Nullable
    @Override
    public String getLanguage() {
        return mSearchLanguage;
    }

    @Override
    public void setLanguage(String mLanguage) {
        this.mSearchLanguage = mLanguage;
        Log.e(TAG, "NEW LANGUAGE: "+mLanguage);
    }

    @Nullable
    @Override
    public String getLocation() {
        return mSearchLocation;
    }

    @Override
    public void setLocation(String mLocation) {
        this.mSearchLocation = mLocation;
        Log.e(TAG, "NEW LOCATION: "+mLocation);
    }

    public HomePresenter(@NonNull DataManager dataManager, @NonNull HomeContract.XploreView xploreView) {
        this.mDataManager = dataManager;
        this.mXploreView = xploreView;

        mXploreView.setPresenter(this);
    }

    @Override
    public void start() {
        loadUsers(mSearchLocation, mSearchLanguage);
    }


    @Override
    public void loadUsers(@NonNull String location, @NonNull String language) {
        mDataManager.loadUsers(location, language, new NetworkManager.GetUserListCallback() {
            @Override
            public void onSuccess(List<User> users) {
                mXploreView.showUsers(users);
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }

    @Override
    public void updateFavourite(@NonNull User user, boolean isFavourite) {
        user.setFavourite(isFavourite);
    }

    @Override
    public void openUserDetails(@NonNull Context context, @NonNull String username) {
        context.startActivity(DetailsActivity.createIntent(context, username));
    }
}
