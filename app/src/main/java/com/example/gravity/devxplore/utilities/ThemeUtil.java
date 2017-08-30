package com.example.gravity.devxplore.utilities;

import android.app.Activity;
import android.content.Intent;

import com.example.gravity.devxplore.R;

/**
 * Created by gravity on 8/29/17.
 */

public class ThemeUtil {
    private static int sTheme;

    public final static int APP_THEME_DARK = 0;

    public final static int APP_THEME_LIGHT = 1;

    public static void changeToTheme(Activity activity, int theme) {

        sTheme = theme;

        activity.finish();

        activity.startActivity(new Intent(activity, activity.getClass()));

        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public static void onActivityCreateSetTheme(Activity activity) {
        switch (sTheme) {
            case APP_THEME_DARK:
                activity.setTheme(R.style.AppTheme_Dark);
                break;
            case APP_THEME_LIGHT:
                activity.setTheme(R.style.AppTheme_Light);
                break;
            default:
                activity.setTheme(R.style.AppTheme_Dark);
                break;
        }

    }
}
