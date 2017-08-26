package com.example.gravity.devxplore.data.db;

import android.arch.persistence.room.RoomDatabase;

import com.example.gravity.devxplore.data.db.dao.DetailDao;
import com.example.gravity.devxplore.data.db.dao.UserDao;

/**
 * Created by gravity on 8/23/17.
 */

public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "devxplore-db";

    public abstract UserDao userDao();

    public abstract DetailDao detailDao();
}
