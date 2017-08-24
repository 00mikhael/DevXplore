package com.example.gravity.devxplore.data.source.local;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.model.UserDetails;
import com.example.gravity.devxplore.data.source.DataCallbacks;
import com.example.gravity.devxplore.data.source.local.UserContract.UserEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gravity on 8/23/17.
 */

public class LocalDataSource implements SourceLocal, DataCallbacks {
    private static LocalDataSource INSTANCE;

    public static LocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource();
        }
        return INSTANCE;
    }

    private LocalDataSource() {}

    private UserContentProvider mContentProvider = UserContentProvider.getInstance(this);

    @Override
    public void loadAllUsers(@NonNull GetUserListCallback callback) {
        List<User> users = new ArrayList<>();
        String[] projection = {
                UserEntry._ID,
                UserEntry.COLUMN_USER_NAME,
                UserEntry.COLUMN_IS_FAVOURITE,
                UserEntry.COLUMN_AVATAR_URL
        };
        Cursor cursor = mContentProvider.query(UserEntry.CONTENT_URI, projection, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int Id = cursor.getInt(cursor.getColumnIndexOrThrow(UserEntry._ID));
                String username = cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_USER_NAME));
                boolean isFavourite = cursor.getInt(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_IS_FAVOURITE)) == 1;
                String avatarUrl = cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.COLUMN_AVATAR_URL));

                User user = new User(Id, username ,avatarUrl ,isFavourite);
                users.add(user);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        if (users.isEmpty()) {
            callback.onDataNotAvailable();
        }else {
            callback.onDataLoaded(users);
        }
    }

    @Override
    public void loadUserDetails(@NonNull String username, @NonNull GetUserCallback callback) {

    }

    @Override
    public void saveAllUsers(@NonNull List<User> users) {

    }

    @Override
    public void saveUser(@NonNull String username, @NonNull String avatarUrl, boolean isFavourite) {

    }

    @Override
    public void updateFavUser(@NonNull String username, boolean isFavourite) {

    }

    @Override
    public void deleteAllUsers() {

    }

    @Override
    public void saveUserDetails(@NonNull UserDetails userDetails) {

    }

    @Override
    public void updateFavUserDetails(@NonNull String username, boolean isFavourite) {

    }

    @Override
    public void deleteAllUserDetails() {

    }

    @Override
    public void loadAllFavourites(@NonNull GetUserListCallback callback) {

    }
}
