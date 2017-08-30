package com.example.gravity.devxplore.view.ui.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.gravity.devxplore.application.AppExecutors;
import com.example.gravity.devxplore.data.DataInterface;
import com.example.gravity.devxplore.data.DataRepository;
import com.example.gravity.devxplore.data.db.AppDatabase;
import com.example.gravity.devxplore.data.db.dao.UserDao;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.utilities.AbsentLiveData;

import java.util.List;
import java.util.Locale;

/**
 * Created by gravity on 8/27/17.
 */

public class BaseViewModel extends AndroidViewModel {
    private static final String TAG = "BaseViewModel";

    private static final String DEFAULT_SEARCH_QUERY = "location:lagos+language:java";

    private MutableLiveData<String> searchQuery = new MutableLiveData<>();

    private MediatorLiveData<List<User>> savedList = new MediatorLiveData<>();

    private LiveData<List<User>> searchResult = new MediatorLiveData<>();

    private MediatorLiveData<List<User>> favouriteResult = new MediatorLiveData<>();

    private MediatorLiveData<List<User>> statsResult = new MediatorLiveData<>();

    private DataInterface mDataRepository;

    public BaseViewModel(Application application) {
        super(application);
        AppExecutors appExecutors = AppExecutors.getInstance();
        AppDatabase mDb = AppDatabase.getDatabaseInstance(this.getApplication());
        UserDao mUserDao = mDb.userDao();
        mDataRepository = DataRepository.getInstance(mDb, mUserDao, appExecutors);
        setSearchQuery(DEFAULT_SEARCH_QUERY);
        searchResult = Transformations.switchMap(searchQuery, query -> {
            if (query == null || query.trim().length() == 0) {
                return AbsentLiveData.create();
            } else {
                return mDataRepository.getUsers(query);
            }
        });
        favouriteResult.addSource(mDataRepository.getFavourites(), new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> favourites) {
                favouriteResult.setValue(favourites);
            }
        });
        Log.e(TAG, searchResult.toString());

    }

    public LiveData<List<User>> getSavedList() {
        savedList.addSource(mDataRepository.getUsers(), new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                savedList.setValue(users);
            }
        });
        return savedList;
    }


    public LiveData<List<User>> getSearchResult() {
        return searchResult;
    }

    public MediatorLiveData<List<User>> getFavouriteResult() {
        return favouriteResult;
    }

    public MediatorLiveData<List<User>> getStatsResult() {
        return statsResult;
    }

    public void setSearchQuery(@NonNull String originalInput) {
        String input = originalInput.toLowerCase(Locale.getDefault()).trim();
        searchQuery.setValue(input);
    }

    public void setFavourite(String username, boolean isFavourite) {
        mDataRepository.setFavourite(username, isFavourite);
    }

    public void refresh() {
        if (searchQuery.getValue() != null) {
            searchQuery.setValue(searchQuery.getValue());
        }
    }
}
