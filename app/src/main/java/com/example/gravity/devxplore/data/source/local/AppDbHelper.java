package com.example.gravity.devxplore.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gravity on 8/23/17.
 */

public class AppDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "devxplore.db";

    private static final int DATABASE_VERSION = 1;

    public AppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserContract.SQL_CREATE_USER_TABLE);
        db.execSQL(UserContract.SQL_CREATE_USERDETAILS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
