package com.example.gravity.devxplore.utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by gravity on 8/8/17.
 */

@SuppressWarnings("ALL")
public class AvatarBehaviourUtils {
    private final static float EXPAND_AVATAR_SIZE_DP = 80f;
    private final static float COLLAPSED_AVATAR_SIZE_DP = 32f;

    private AppBarStateChangeListener mAppBarStateChangeListener;

    @NonNull
    private final int[] mAvatarPoint = new int[2];
    @NonNull
    private final int[] mSpacePoint = new int[2];
    @NonNull
    private final int[] mToolbarTextPoint =
            new int[2];
    @NonNull
    private final int[] mTitleTextViewPoint = new int[2];

    public void translationView(@NonNull Context context, @NonNull CircleImageView avatar,
                                @NonNull TextView titleText, @NonNull TextView toolbarText, float offset, float textSize) {
        float xOffset = -(mAvatarPoint[0] - mSpacePoint[0]) * offset;
        float yOffset = -(mAvatarPoint[1] - mSpacePoint[1]) * offset;
        float xTitleOffset = -(mTitleTextViewPoint[0] - mToolbarTextPoint[0]) * offset;
        float yTitleOffset = -(mTitleTextViewPoint[1] - mToolbarTextPoint[1]) * offset;
        int newSize = Util.convertDpToPixelSize(
                EXPAND_AVATAR_SIZE_DP - (EXPAND_AVATAR_SIZE_DP - COLLAPSED_AVATAR_SIZE_DP) * offset, context);
        float newTextSize =
                textSize - (textSize - toolbarText.getTextSize()) * offset;
        avatar.getLayoutParams().width = newSize;
        avatar.getLayoutParams().height = newSize;
        avatar.setTranslationX(xOffset);
        avatar.setTranslationY(yOffset);
        titleText.setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSize);
        titleText.setTranslationX(xTitleOffset);
        titleText.setTranslationY(yTitleOffset);
    }

    public void clearAnim(@NonNull CircleImageView avatar, @NonNull TextView titleText, float textSize) {
        avatar.setTranslationX(0);
        avatar.setTranslationY(0);
        titleText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        titleText.setTranslationX(0);
        titleText.setTranslationY(0);
    }

    public void resetPoints(@NonNull final Context context, @NonNull final CircleImageView avatar, @NonNull final TextView titleText,
                            @NonNull final TextView toolbarText, @NonNull android.support.v4.widget.Space space, final float offset, final float textSize) {
        clearAnim(avatar, titleText, textSize);

        int avatarSize = Util.convertDpToPixelSize(EXPAND_AVATAR_SIZE_DP, context);
        avatar.getLocationOnScreen(mAvatarPoint);
        mAvatarPoint[0] -= (avatarSize - avatar.getWidth()) / 2;
        space.getLocationOnScreen(mSpacePoint);
        toolbarText.getLocationOnScreen(mToolbarTextPoint);
        mToolbarTextPoint[0] += Util.convertDpToPixelSize(16, context);
        titleText.post(new Runnable() {

            @Override
            public void run() {
                titleText.getLocationOnScreen(mTitleTextViewPoint);
                translationView(context, avatar, titleText, toolbarText, offset, textSize);
            }
        });
    }
}
