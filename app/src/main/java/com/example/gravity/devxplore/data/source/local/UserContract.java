package com.example.gravity.devxplore.data.source.local;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by gravity on 8/23/17.
 */

public  final class UserContract {
    private UserContract() {}

    public static final String SQL_CREATE_USER_TABLE = "CREATE TABLE " + UserEntry.TABLE_NAME + "("
            + UserEntry._ID + " INTEGER NOT NULL, "
            + UserEntry.COLUMN_USER_NAME + " TEXT NOT NULL, "
            + UserEntry.COLUMN_IS_FAVOURITE + " INTEGER NOT NULL, "
            + UserEntry.COLUMN_AVATAR_URL + " TEXT NOT NULL);";

    public static final String SQL_CREATE_USERDETAILS_TABLE = "CREATE TABLE " + UserDetailEntry.TABLE_NAME + "("
            + UserDetailEntry._ID + " INTEGER NOT NULL, "
            + UserDetailEntry.COLUMN_USER_NAME + " TEXT NOT NULL, "
            + UserDetailEntry.COLUMN_FULL_NAME + " TEXT, "
            + UserDetailEntry.COLUMN_FOLLOWERS_COUNT + " INTEGER NOT NULL, "
            + UserDetailEntry.COLUMN_FOLLOWING_COUNT + " INTEGER NOT NULL, "
            + UserDetailEntry.COLUMN_REPOS_COUNT + " INTEGER NOT NULL, "
            + UserDetailEntry.COLUMN_USER_LOCATION + " TEXT, "
            + UserDetailEntry.COLUMN_USER_BIO + " TEXT, "
            + UserDetailEntry.COLUMN_IS_FAVOURITE + " INTEGER NOT NULL, "
            + UserDetailEntry.COLUMN_AVATAR_URL + " TEXT NOT NULL, "
            + UserDetailEntry.COLUMN_HTML_URL + " TEXT NOT NULL);";

    public static final String CONTENT_AUTHORITY = "com.example.gravity.users";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_USERS = "users";

    public static final String PATH_USERDETAILS = "userDetails";


    public static final class UserEntry implements BaseColumns {

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USERS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USERS;

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_USERS);

        public final static String TABLE_NAME = "users";

        public final static String _ID = "id";
        public final static  String COLUMN_USER_NAME = "userName";
        public final static String COLUMN_IS_FAVOURITE = "isFavourite";
        public final static String COLUMN_AVATAR_URL = "avatarUrl";

    }

    public static final class UserDetailEntry implements BaseColumns {

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USERDETAILS;

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_USERDETAILS);

        public final static String TABLE_NAME = "userDetails";

        public final static String _ID = "id";
        public final static  String COLUMN_USER_NAME = "userName";
        public final static  String COLUMN_FULL_NAME = "fullName";
        public final static String COLUMN_AVATAR_URL = "avatarUrl";
        public final static String COLUMN_HTML_URL = "htmlUrl";
        public final static String COLUMN_FOLLOWERS_COUNT = "followersCount";
        public final static String COLUMN_FOLLOWING_COUNT = "followingCount";
        public final static String COLUMN_REPOS_COUNT = "reposCount";
        public final static String COLUMN_USER_LOCATION = "location";
        public final static String COLUMN_USER_BIO = "bio";
        public final static String COLUMN_IS_FAVOURITE = "isFavourite";
    }

}
