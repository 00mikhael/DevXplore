package com.example.gravity.devxplore.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.example.gravity.devxplore.data.db.AppDatabase.DATABASE_NAME;

/**
 * Created by gravity on 8/25/17.
 */

public class DatabaseCreator {

    private static final String TAG = "DatabaseCreator";

    private static DatabaseCreator sInstance;

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    private AppDatabase mDatabase;

    private final AtomicBoolean mIntializing = new AtomicBoolean(true);

    private static final Object LOCK = new Object();

    public synchronized static DatabaseCreator getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new DatabaseCreator();
                }
            }
        }
        return sInstance;
    }

    public LiveData<Boolean> isDatabaseCreated() {
        return mIsDatabaseCreated;
    }

    @Nullable
    public AppDatabase getDatabase() {
        return mDatabase;
    }

    public void createDatabase(final Context context) {
        Log.d(TAG, "Creating DB from " + Thread.currentThread().getName());

        if (!mIntializing.compareAndSet(true, false)) {
            return;
        }
        mIsDatabaseCreated.setValue(false);
        new AsyncTask<Context, Void, Void>() {
            @Override
            protected Void doInBackground(Context... contexts) {
                Log.d(TAG, "Starting bg job " + Thread.currentThread().getName());

                Context context = contexts[0].getApplicationContext();

                context.deleteDatabase(DATABASE_NAME);

                AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, DATABASE_NAME).build();

                addDelay();

                mDatabase = db;

                return null;
            }

            @Override
            protected void onPostExecute(Void ignored) {
                super.onPostExecute(ignored);
                mIsDatabaseCreated.setValue(true);
            }
        }.execute(context.getApplicationContext());
    }

    private void addDelay() {
        try {
            Thread.sleep(4000);
        }catch (InterruptedException ignored) {

        }
    }
}
