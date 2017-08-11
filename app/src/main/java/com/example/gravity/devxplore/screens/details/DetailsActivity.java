package com.example.gravity.devxplore.screens.details;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.Space;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.gravity.devxplore.Injection;
import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.adapters.FragmentPager;
import com.example.gravity.devxplore.data.model.UserDetails;
import com.example.gravity.devxplore.screens.details.followers.FollowersFragment;
import com.example.gravity.devxplore.screens.details.following.FollowingFragment;
import com.example.gravity.devxplore.screens.details.overview.OverviewFragment;
import com.example.gravity.devxplore.screens.details.repos.ReposFragment;
import com.example.gravity.devxplore.screens.details.starred.StarredFragment;
import com.example.gravity.devxplore.utilities.AppBarStateChangeListener;
import com.example.gravity.devxplore.utilities.Util;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by gravity on 7/2/17.
 */

@SuppressWarnings("ALL")
public class DetailsActivity extends AppCompatActivity implements DetailsContract.MainDetailView, View.OnClickListener, AppBarLayout.OnOffsetChangedListener {
    private final static float EXPAND_AVATAR_SIZE_DP = 80f;
    private final static float COLLAPSED_AVATAR_SIZE_DP = 32f;
    private static final float PERCENTAGE_TO_HIDE_APPBAR_DETAILS = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;

    private boolean mIsDetailsVisible = true;

    public final static String USERNAME = "";

    private DetailsContract.Presenter mPresenter;
    private AppBarLayout mAppBarLayout;
    private CircleImageView mAvatarImageView;
    private TextView mCollapsedFullName, mFullName, mUsername, mUserDescription;
    private SmartTabLayout mTabLayout;
    private ViewPager mViewPager;
    private Space mSpace;
    private Toolbar mToolBar;
    private NestedScrollView mNestedScrollView;
    private LinearLayout mContactContainer;

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
    private float mTitleTextSize;

    @NonNull
    public static Intent createIntent(Context context, String username) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(USERNAME, username);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_details);

        findViews();
        setUpViews();

        mNestedScrollView.setFillViewport(true);

        DetailsPresenter mUserDetailsPresenter = new DetailsPresenter(Injection.provideDataManager(getApplicationContext()), this);
        String username = getIntent().getStringExtra(USERNAME);
        mPresenter.loadUserDetails(username);

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentPager mAdapter = new FragmentPager(fragmentManager);
        mAdapter.addFragment(OverviewFragment.newInstance(username), "Overview");
        mAdapter.addFragment(ReposFragment.newInstance(username), "Repos");
        mAdapter.addFragment(StarredFragment.newInstance(username), "Starred");
        mAdapter.addFragment(FollowingFragment.newInstance(username), "Following");
        mAdapter.addFragment(FollowersFragment.newInstance(username), "Followers");

        mViewPager.setAdapter(mAdapter);
        mTabLayout.setViewPager(mViewPager);

        mAppBarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    public void showUserDetails(@NonNull UserDetails user) {

        if (user.getLogin() == null) {
            Toast.makeText(this, "Nullity", Toast.LENGTH_SHORT).show();
        }else {
            mUsername.setText(user.getLogin());
        }
        if (user.getName() == null) {
            mFullName.setTypeface(mFullName.getTypeface(), Typeface.ITALIC);
            mFullName.setText("Name Unavailable");
        } else {
            mFullName.setText(user.getName());
        }
        if (user.getBio() == null) {
            mUserDescription.setTypeface(mUserDescription.getTypeface(), Typeface.ITALIC);
            mUserDescription.setText("Bio Unavailable");
        } else {
            mUserDescription.setText(user.getBio());
        }
        Glide.with(getApplicationContext())
                .load(user.getAvatarUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .thumbnail(0.5f)
                .into(mAvatarImageView);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setPresenter(DetailsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoadingIndicator(boolean isLoading) {

    }

    private void findViews() {
        mAppBarLayout = (AppBarLayout) findViewById(R.id.profile_app_bar);
        mAvatarImageView = (CircleImageView) findViewById(R.id.profile_profile_image);
        mCollapsedFullName = (TextView) findViewById(R.id.profile_fullname_collapsed);
        mFullName = (TextView) findViewById(R.id.profile_fullname);
        mUsername = (TextView) findViewById(R.id.profile_username);
        mUserDescription = (TextView) findViewById(R.id.profile_bio);
        mSpace = (Space) findViewById(R.id.space);
        mTabLayout = (SmartTabLayout) findViewById(R.id.profile_tab);
        mViewPager = (ViewPager) findViewById(R.id.profile_viewpager);
        mToolBar = (Toolbar) findViewById(R.id.profile_toolbar);
        mContactContainer = (LinearLayout) findViewById(R.id.profile_contact_container);
        mNestedScrollView = (NestedScrollView) findViewById(R.id.profile_nested_scrollview);
    }

    private void setUpViews() {
        mTitleTextSize = mFullName.getTextSize();
        setUpToolbar();
        setUpAmazingAvatar();
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setUpAmazingAvatar() {
        mAppBarStateChangeListener = new AppBarStateChangeListener() {

            @Override
            public void onStateChanged(AppBarLayout appBarLayout,
                                       AppBarStateChangeListener.State state) {
            }

            @Override
            public void onOffsetChanged(AppBarStateChangeListener.State state, float offset) {
                translationView(offset);
            }
        };
        mAppBarLayout.addOnOffsetChangedListener(mAppBarStateChangeListener);
    }

    private void translationView(float offset) {
        float xOffset = -(mAvatarPoint[0] - mSpacePoint[0]) * offset;
        float yOffset = -(mAvatarPoint[1] - mSpacePoint[1]) * offset;
        float xTitleOffset = -(mTitleTextViewPoint[0] - mToolbarTextPoint[0]) * offset;
        float yTitleOffset = -(mTitleTextViewPoint[1] - mToolbarTextPoint[1]) * offset;
        int newSize = Util.convertDpToPixelSize(
                EXPAND_AVATAR_SIZE_DP - (EXPAND_AVATAR_SIZE_DP - COLLAPSED_AVATAR_SIZE_DP) * offset, this);
        float newTextSize =
                mTitleTextSize - (mTitleTextSize - mCollapsedFullName.getTextSize()) * offset;
        mAvatarImageView.getLayoutParams().width = newSize;
        mAvatarImageView.getLayoutParams().height = newSize;
        mAvatarImageView.setTranslationX(xOffset);
        mAvatarImageView.setTranslationY(yOffset);
        mFullName.setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSize);
        mFullName.setTranslationX(xTitleOffset);
        mFullName.setTranslationY(yTitleOffset);
    }

    private void clearAnim() {
        mAvatarImageView.setTranslationX(0);
        mAvatarImageView.setTranslationY(0);
        mFullName.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTitleTextSize);
        mFullName.setTranslationX(0);
        mFullName.setTranslationY(0);
    }

    private void resetPoints() {
        clearAnim();

        int avatarSize = Util.convertDpToPixelSize(EXPAND_AVATAR_SIZE_DP, this);
        mAvatarImageView.getLocationOnScreen(mAvatarPoint);
        mAvatarPoint[0] -= (avatarSize - mAvatarImageView.getWidth()) / 2;
        mSpace.getLocationOnScreen(mSpacePoint);
        mCollapsedFullName.getLocationOnScreen(mToolbarTextPoint);
        mToolbarTextPoint[0] += Util.convertDpToPixelSize(16, this);
        mFullName.post(new Runnable() {

            @Override
            public void run() {
                mFullName.getLocationOnScreen(mTitleTextViewPoint);
                translationView(mAppBarStateChangeListener.getCurrentOffset());
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!hasFocus) {
            return;
        }
        resetPoints();
    }

    @Override
    public void onOffsetChanged(@NonNull AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        Util.handleAlphaOnView(mUsername, percentage);
        Util.handleAlphaOnView(mUserDescription, percentage);
        Util.handleAlphaOnView(mContactContainer, percentage);
    }
}
