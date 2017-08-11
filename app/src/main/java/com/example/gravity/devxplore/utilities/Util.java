package com.example.gravity.devxplore.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AlphaAnimation;

/**
 * Created by gravity on 7/29/17.
 */

@SuppressWarnings("ALL")
public class Util {

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private static boolean mIsDetailsVisible = true;

    public static int dpToPx(@NonNull Context context) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, r.getDisplayMetrics()));
    }


    public static DisplayMetrics getDisplayMetrics(@NonNull Context context) {
        Resources resources = context.getResources();
        return resources.getDisplayMetrics();
    }

    public static float convertDpToPixel(float dp, @NonNull Context context) {
        return dp * (getDisplayMetrics(context).densityDpi / 160f);
    }

    public static int convertDpToPixelSize(float dp, @NonNull Context context) {
        float pixels = convertDpToPixel(dp, context);
        final int res = (int) (pixels + 0.5f);
        if (res != 0) {
            return res;
        } else if (pixels == 0) {
            return 0;
        } else if (pixels > 0) {
            return 1;
        }
        return -1;
    }




    public static boolean initCollapsingToolbar(@NonNull final Activity activity, @NonNull final CollapsingToolbarLayout collapsingToolbarLayout,
                                                @NonNull AppBarLayout appBarLayout) {
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(@NonNull AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(activity.getTitle());
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }
            }
        });
        return true;
    }

    public static void startAlphaAnimation(@NonNull View v, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration((long) Util.ALPHA_ANIMATIONS_DURATION);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    public static void handleAlphaOnView(@NonNull View v, float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if(mIsDetailsVisible) {
                startAlphaAnimation(v, View.INVISIBLE);
                mIsDetailsVisible = false;
            }

        } else {

            if (!mIsDetailsVisible) {
                startAlphaAnimation(v, View.VISIBLE);
                mIsDetailsVisible = true;
            }
        }
    }

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(com.example.gravity.devxplore.R.id.fragment_container, fragment);
        transaction.commit();
    }
    public static void replaceFragmentInActivity(@NonNull FragmentManager fragmentManager,
                                                 @NonNull Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(com.example.gravity.devxplore.R.id.fragment_container, fragment);
        transaction.commit();
    }
}
