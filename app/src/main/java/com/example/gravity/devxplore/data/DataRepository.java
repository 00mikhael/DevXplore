package com.example.gravity.devxplore.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.gravity.devxplore.application.AppExecutors;
import com.example.gravity.devxplore.data.db.AppDatabase;
import com.example.gravity.devxplore.data.db.dao.UserDao;
import com.example.gravity.devxplore.data.model.Repository;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.model.UserDetails;
import com.example.gravity.devxplore.data.model.UserSearchResponse;
import com.example.gravity.devxplore.data.network.github.ApiClient;
import com.example.gravity.devxplore.data.network.github.SearchService;
import com.example.gravity.devxplore.data.network.github.UserService;
import com.example.gravity.devxplore.utilities.Objects;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gravity on 7/29/17.
 */

@SuppressWarnings("ALL")
@Singleton
public class DataRepository implements DataInterface {

    private static final String TAG = "DataRepo";

    private static DataRepository sInstance;

    private final AppDatabase db;

    private AppExecutors appExecutors;

    private final UserDao userDao;

    public static DataInterface getInstance(AppDatabase db, UserDao userDao, AppExecutors appExecutors) {
        if (sInstance == null) {
            sInstance = new DataRepository(db, userDao, appExecutors);
        }
        return sInstance;
    }

    private DataRepository(AppDatabase db, UserDao userDao, AppExecutors appExecutors) {
        this.db = db;
        this.userDao = userDao;
        this.appExecutors = appExecutors;
    }

    List<User> savedList = new ArrayList<>();
    @Override
    public LiveData<List<User>> getUsers() {
        final MutableLiveData<List<User>> liveData = new MutableLiveData<>();
        appExecutors.diskIO().execute(()->{
            savedList = userDao.loadAllUsers();
        });
        liveData.setValue(savedList);
        return liveData;
    }

    @Override
    public LiveData<List<User>> getUsers(@NonNull String query) {
        final MutableLiveData<List<User>> liveData = new MutableLiveData<>();
        SearchService searchService = ApiClient.getClient().create(SearchService.class);
        Call<UserSearchResponse> call = searchService.loadUserResponse(query);
        call.enqueue(new Callback<UserSearchResponse>() {
            @Override
            public void onResponse(Call<UserSearchResponse> call, Response<UserSearchResponse> response) {
                /*DataResponse<List<User>> userlist = new DataResponse<List<User>>(processResponse(response.body()));
                Log.e(TAG, userlist.getDataList().toString());*/
                populate();
                Log.e(TAG, response.code()+"");
                liveData.setValue(processResponse(response.body()));
            }

            @Override
            public void onFailure(Call<UserSearchResponse> call, Throwable t) {
               /* liveData.setValue(new DataResponse(t));*/
            }
        });
        return liveData;
    }

    @Override
    public LiveData<UserDetails> getUser(@NonNull String username) {
        Log.e(TAG, username);
        final MutableLiveData<UserDetails> liveData = new MutableLiveData<>();
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<UserDetails> call = userService.loadUser(username);
        call.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                Log.e(TAG, response.code()+"");
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
            }
        });
        return liveData;
    }

    @Override
    public LiveData<List<Repository>> getPopularRepos(@NonNull String username) {
        final MutableLiveData<List<Repository>> liveData = new MutableLiveData<>();
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<List<Repository>> call = userService.loadPopularRepos(username);
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
            }
        });
        return liveData;
    }

    @Override
    public LiveData<List<Repository>> getUserRepos(@NonNull String username) {
        final MutableLiveData<List<Repository>> liveData = new MutableLiveData<>();
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<List<Repository>> call = userService.loadUserRepos(username);
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
            }
        });
        return liveData;
    }

    @Override
    public LiveData<List<Repository>> getUserStarredRepos(@NonNull String username) {
        final MutableLiveData<List<Repository>> liveData = new MutableLiveData<>();
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<List<Repository>> call = userService.loadUserStarred(username);
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
            }
        });
        return liveData;
    }

    @Override
    public LiveData<List<User>> getUserFollowers(@NonNull String username) {
        final MutableLiveData<List<User>> liveData = new MutableLiveData<>();
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<List<User>> call = userService.loadUserFollowers(username);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
        });
        return liveData;
    }

    @Override
    public LiveData<List<User>> getUserFollowing(@NonNull String username) {
        final MutableLiveData<List<User>> liveData = new MutableLiveData<>();
        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<List<User>> call = userService.loadUserFollowing(username);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
        });
        return liveData;
    }

    @Override
    public void setFavourite(@NonNull String username, boolean isFavourite) {
        appExecutors.diskIO().execute(() -> {
            try{
                db.beginTransaction();
                db.userDao().updateUser(username, isFavourite);
                if (isFavourite == false) {
                    db.userDao().deleteFavourite(username);
                }else {
                    db.userDao().insertFavourite(db.userDao().loadUser(username));
                }
                db.setTransactionSuccessful();
            }finally {
                db.endTransaction();
            }

        });

    }

    List<User> favourites = new ArrayList<>();
    @Override
    public LiveData<List<User>> getFavourites() {
        final MutableLiveData<List<User>> liveData = new MutableLiveData<>();
        favourites.clear();
        appExecutors.diskIO().execute(() -> {
            try {
                db.beginTransaction();
                if (db.userDao().loadAllFavourites() != null) {
                    favourites = userDao.loadAllFavourites();
                }
                db.setTransactionSuccessful();
            }finally {
                db.endTransaction();
            }

        });
        liveData.setValue(favourites);
        return liveData;
    }

    public void saveDetails(List<User> userList) {
        UserService userService = ApiClient.getClient().create(UserService.class);
        List<UserDetails> userDetailsList = new ArrayList<>();
        for (User user : userList) {
            Call<UserDetails> userDetails = userService.loadUser(user.getLogin());
            userDetails.enqueue(new Callback<UserDetails>() {
                @Override
                public void onResponse(@NonNull Call<UserDetails> call, @NonNull Response<UserDetails> response) {
                    userDetailsList.add(response.body());
                }

                @Override
                public void onFailure(@NonNull Call<UserDetails> call, @NonNull Throwable t) {
                }
            });
        }
        try {
            db.beginTransaction();
            db.userDao().deleteAllDetails();
            db.userDao().insertAllDetails(userDetailsList);
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    public void markFavourite(List<User> oldList, List<User> newList) {
        for (User oldUser : oldList) {
            for (User newUser : newList) {
                if (Objects.equals(oldUser, newUser)) {
                    boolean favourite = oldUser.isFavourite();
                    newUser.setFavourite(favourite);
                }
            }
        }
    }
    public List<User> processResponse(UserSearchResponse response) {
        List<User> users = response.getItems();
        appExecutors.diskIO().execute(() -> {
            saveSearch(users);
        });
        return users;
    }

    int count = 0;

    public void saveSearch(List<User> users) {
        count = 0;
        List<User> oldList = null;
        List<User> newList = users;
        try {
            db.beginTransaction();
            oldList = db.userDao().loadAllUsers();
            if (oldList == null) {
                for (User user : newList) {
                    ++count;
                    db.userDao().insertUser(user);
                    Log.e(TAG, "INSERTING USER AT POSITION " + count);
                }
                for (User user : newList) {
                    if (user.isFavourite()) {
                        db.userDao().insertFavourite(user);
                    }
                }
                db.setTransactionSuccessful();
                return;
            }
            markFavourite(oldList, newList);
            db.userDao().deleteAllUsers();
            for (User user : newList) {
                ++count;
                db.userDao().insertUser(user);
                Log.e(TAG, "INSERTING USER AT POSITION " + count);
            }
            for (User user : newList) {
                if (user.isFavourite()) {
                    db.userDao().insertFavourite(user);
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
    public void populate() {
        User user1 = new User("Emeka", 1, "url", true);
        User user2 = new User("Ifeanyi", 2, "url", false);
        appExecutors.diskIO().execute(()->{
            try {
                db.beginTransaction();
                db.userDao().insertUser(user1);
                db.userDao().insertUser(user2);
                db.setTransactionSuccessful();
            }finally {
                db.endTransaction();
            }
        });
    }
}
