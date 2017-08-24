package com.example.gravity.devxplore.data.source.local;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.gravity.devxplore.data.source.local.UserContract.UserDetailEntry;
import com.example.gravity.devxplore.data.source.local.UserContract.UserEntry;

/**
 * Created by gravity on 8/23/17.
 */

public class UserContentProvider extends ContentProvider {

    private static final String LOG_TAG = UserContentProvider.class.getSimpleName();

    private static final int USERS = 1000;
    private static final int USER_ID = 1001;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(UserContract.CONTENT_AUTHORITY, UserContract.PATH_USERS, USERS);

        sUriMatcher.addURI(UserContract.CONTENT_AUTHORITY, UserContract.PATH_USERS + "/#", USER_ID);

        /*sUriMatcher.addURI(UserContract.CONTENT_AUTHORITY, UserContract.PATH_USERDETAILS + "*//*", USER_NAME);*/

        /*sUriMatcher.addURI(UserContract.CONTENT_AUTHORITY, UserContract.PATH_USERPOPREPOS, USER_POP);

        sUriMatcher.addURI(UserContract.CONTENT_AUTHORITY, UserContract.PATH_USERSTARRED, USER_STARRED);

        sUriMatcher.addURI(UserContract.CONTENT_AUTHORITY, UserContract.PATH_USERREPOS, USER_REPOS);

        sUriMatcher.addURI(UserContract.CONTENT_AUTHORITY, UserContract.PATH_USERFOLLOWING, USER_FOLLOWING);

        sUriMatcher.addURI(UserContract.CONTENT_AUTHORITY, UserContract.PATH_USERFOLLOWERS, USER_FOLLOWERS);*/

    }

    private static UserContentProvider INSTANCE;
    private SourceLocal mLocalDataSource;
    private AppDbHelper mDbHelper;

    public static UserContentProvider getInstance(@NonNull SourceLocal mLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new UserContentProvider(mLocalDataSource);
        }
        return INSTANCE;
    }

    private UserContentProvider(@NonNull SourceLocal mLocalDataSource) {
        this.mLocalDataSource = mLocalDataSource;
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new AppDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        // Get readable database
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case USERS:
                cursor = database.query(UserEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case USER_ID:
                selection = UserEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                cursor = database.query(UserDetailEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case USERS:
                return insertUser(uri, contentValues);
            case USER_ID:
                return insertUserDetails(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertUser(Uri uri, ContentValues values) {

        String username = values.getAsString(UserEntry.COLUMN_USER_NAME);
        if (username == null) {
            throw new IllegalArgumentException("User requires a username");
        }

        String avatarUrl = values.getAsString(UserEntry.COLUMN_AVATAR_URL);
        if (avatarUrl == null) {
            throw new IllegalArgumentException("User requires a avatarUrl");
        }

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        long id = db.insert(UserEntry.TABLE_NAME, null, values);

        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        // Once we know the ID of the new row in the table,
        // return the new URI with the ID appended to the end of it
        return ContentUris.withAppendedId(uri, id);
    }

    private Uri insertUserDetails(Uri uri, ContentValues values) {
        int Id = Integer.parseInt(values.getAsString(UserDetailEntry._ID));
        if (Id < 1) {
            throw new IllegalArgumentException("User requires a valid id");
        }

        String username = values.getAsString(UserDetailEntry.COLUMN_USER_NAME);
        if (username == null) {
            throw new IllegalArgumentException("User requires a username");
        }

        String avatarUrl = values.getAsString(UserDetailEntry.COLUMN_AVATAR_URL);
        if (avatarUrl == null) {
            throw new IllegalArgumentException("User requires a avatarUrl");
        }

        String htmlUrl = values.getAsString(UserDetailEntry.COLUMN_HTML_URL);
        if (htmlUrl == null) {
            throw new IllegalArgumentException("User requires a html");
        }

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        long id = db.insert(UserDetailEntry.TABLE_NAME, null, values);

        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        // Once we know the ID of the new row in the table,
        // return the new URI with the ID appended to the end of it
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case USERS:
                return updateUser(uri, contentValues, selection, selectionArgs);
            case USER_ID:
                selection = UserDetailEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updateUserDetails(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int updateUser(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // If there are no values to update, then don't try to update the database
        if (values.size() == 0) {
            return 0;
        }

        // Otherwise, get writeable database to update the data
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Perform the update on the database and get the number of rows affected
        int rowsUpdated = database.update(UserEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    private int updateUserDetails(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // If there are no values to update, then don't try to update the database
        if (values.size() == 0) {
            return 0;
        }

        // Otherwise, get writeable database to update the data
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Perform the update on the database and get the number of rows affected
        int rowsUpdated = database.update(UserDetailEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Get writeable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Track the number of rows that were deleted
        int usersDeleted;
        int usersD_Deleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case USERS:
                // Delete all rows that match the selection and selection args
                usersDeleted = database.delete(UserEntry.TABLE_NAME, selection, selectionArgs);
                usersD_Deleted = database.delete(UserDetailEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        // If 1 or more rows were deleted, then notify all listeners that the data at the
        // given URI has changed
        if (usersDeleted != 0 || usersD_Deleted != 0 ) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        // Return the number of rows deleted
        return usersDeleted + usersD_Deleted;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case USERS:
                return UserEntry.CONTENT_LIST_TYPE;
            case USER_ID:
                return UserDetailEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }
}
