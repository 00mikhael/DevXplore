package com.example.gravity.devxplore.view.ui.details;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.example.gravity.devxplore.application.AppExecutors;
import com.example.gravity.devxplore.data.DataInterface;
import com.example.gravity.devxplore.data.DataRepository;
import com.example.gravity.devxplore.data.db.AppDatabase;
import com.example.gravity.devxplore.data.db.dao.UserDao;
import com.example.gravity.devxplore.data.model.Repository;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.model.UserDetails;

import java.util.List;

/**
 * Created by gravity on 8/29/17.
 */

public class DetailsViewModel extends AndroidViewModel {

    private static final String TAG = "DetailsViewModel";

    private MutableLiveData<String> mCurrentUser = new MutableLiveData<>();

    private LiveData<UserDetails> userDetails = new MediatorLiveData<>();

    private LiveData<List<Repository>> userPopRepos = new MediatorLiveData<>();

    private LiveData<List<Repository>> userStarredRepos = new MediatorLiveData<>();

    private LiveData<List<Repository>> userRepos = new MediatorLiveData<>();

    private LiveData<List<User>> userFollowers = new MediatorLiveData<>();

    private LiveData<List<User>> userFollowing = new MediatorLiveData<>();

    private DataInterface mDataRepository;

    public DetailsViewModel(Application application) {
        super(application);
        AppExecutors appExecutors = AppExecutors.getInstance();
        AppDatabase mDb = AppDatabase.getDatabaseInstance(this.getApplication());
        UserDao mUserDao = mDb.userDao();
        mDataRepository = DataRepository.getInstance(mDb, mUserDao,appExecutors);
        userDetails = Transformations.switchMap(mCurrentUser, current -> mDataRepository.getUser(current));
        userPopRepos = Transformations.switchMap(mCurrentUser, current -> mDataRepository.getPopularRepos(current));
        userStarredRepos = Transformations.switchMap(mCurrentUser, current -> mDataRepository.getUserStarredRepos(current));
        userRepos = Transformations.switchMap(mCurrentUser, current -> mDataRepository.getUserRepos(current));
        userFollowers = Transformations.switchMap(mCurrentUser, current -> mDataRepository.getUserFollowers(current));
        userFollowing = Transformations.switchMap(mCurrentUser, current -> mDataRepository.getUserFollowing(current));
    }

    public void setCurrentUser(@NonNull String username) {
        mCurrentUser.setValue(username);
    }

    public LiveData<UserDetails> getUserDetails() {
        return userDetails;
    }

    public LiveData<List<Repository>> getUserPopRepos() {
        return userPopRepos;
    }

    public LiveData<List<Repository>> getUserStarredRepos() {
        return userStarredRepos;
    }

    public LiveData<List<Repository>> getUserRepos() {
        return userRepos;
    }

    public LiveData<List<User>> getUserFollowers() {
        return userFollowers;
    }

    public LiveData<List<User>> getUserFollowing() {
        return userFollowing;
    }

    public void setFavourite(String username, boolean isFavourite) {
        mDataRepository.setFavourite(username, isFavourite);
    }
}
