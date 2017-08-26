package com.example.gravity.devxplore.data.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.gravity.devxplore.data.model.UserDetails;

import java.util.List;

/**
 * Created by gravity on 8/25/17.
 */

public interface DetailDao {
    @Query("SELECT * FROM details WHERE login = :login")
    LiveData<UserDetails> loadDetail(String login);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDetail(UserDetails... user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllDetails(List<UserDetails> users);

    @Update
    void updateDetail(UserDetails user);

    @Query("DELETE FROM details")
    void deleteAll();
}
