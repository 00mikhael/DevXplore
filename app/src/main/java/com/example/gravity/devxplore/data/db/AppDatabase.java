package com.example.gravity.devxplore.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.gravity.devxplore.data.db.dao.UserDao;
import com.example.gravity.devxplore.data.model.Favourites;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.data.model.UserDetails;

/**
 * Created by gravity on 8/23/17.
 */
@Database(entities = {User.class, UserDetails.class, Favourites.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    static final String DATABASE_NAME = "devxplore.db";

    public abstract UserDao userDao();


    public static AppDatabase sInstance;

    // Get a database instance
    public static synchronized AppDatabase getDatabaseInstance(Context context) {
        if (sInstance == null) {
            sInstance = create(context);
        }
        return sInstance;
    }

    // Create the database
    static AppDatabase create(Context context) {
        RoomDatabase.Builder<AppDatabase> builder = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class,
                DATABASE_NAME);
        return builder.build();
    }

}
