package com.example.gravity.devxplore.data.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.gravity.devxplore.data.model.User;

import java.util.List;

/**
 * Created by gravity on 8/25/17.
 */

public interface UserDao {

    @Query("SELECT * FROM users WHERE login = :login")
    LiveData<User> loadUser(String login);

    @Query("SELECT * FROM users")
    LiveData<List<User>> loadAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User... user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllUsers(List<User> users);

    @Update
    void updateUser(User... user);

    @Update
    void updateAllUser(List<User> users);

    @Query("DELETE FROM users")
    void deleteAll();
}
