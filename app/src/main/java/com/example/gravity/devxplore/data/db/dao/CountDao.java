package com.example.gravity.devxplore.data.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

/**
 * Created by gravity on 8/25/17.
 */

public interface CountDao {
    @Query("SELECT * FROM count")
    LiveData<Integer> loadCount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCount(int... count);

    @Query("DELETE FROM count")
    void deleteCount();
}
