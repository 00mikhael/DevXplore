package com.example.gravity.devxplore.view.ui.details;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.data.model.UserDetails;
import com.example.gravity.devxplore.utilities.BasicUtil;
import com.example.gravity.devxplore.utilities.ThemeUtil;
import com.example.gravity.devxplore.view.adapters.FragmentPager;
import com.example.gravity.devxplore.view.ui.details.followers.FollowersFragment;
import com.example.gravity.devxplore.view.ui.details.following.FollowingFragment;
import com.example.gravity.devxplore.view.ui.details.overview.OverviewFragment;
import com.example.gravity.devxplore.view.ui.details.repos.ReposFragment;
import com.example.gravity.devxplore.view.ui.details.starred.StarredFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by gravity on 7/2/17.
 */

@SuppressWarnings("ALL")
public class DetailsActivity extends AppCompatActivity implements LifecycleRegistryOwner, View.OnClickListener, AppBarLayout.OnOffsetChangedListener {
    private final static String USERNAME = "username";
    private final static String TAG = "DETAILS";

    private AppBarLayout mAppBarLayout;
    private CircleImageView mAvatarImageView;
    private TextView mCollapsedUserName, mFullName, mUsername;
    private SmartTabLayout mTabLayout;
    private LinearLayout mDetailsContainer;
    private ViewPager mViewPager;
    private Toolbar mToolBar;
    private FloatingActionButton mFab;
    private NestedScrollView mNestedScrollView;
    private UserDetails mUser;
    private LinearLayout mContainerFollowing, mContainerFollowers, mContainerRepos;
    private TextView mFollowingCount, mFollowersCount, mReposCount;
    private ImageView mGithubLink;
    private String username;
    private ProgressBar mProgressBar;
    private FragmentPager mAdapter;
    private DetailsViewModel mViewModel;

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @NonNull
    public static Intent createIntent(Context context, String username) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(USERNAME, username);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtil.onActivityCreateSetTheme(this);
        setContentView(R.layout.fragment_details);

        mViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        username = getIntent().getStringExtra(USERNAME);
        mViewModel.setCurrentUser(username);

        findViews();
        setUpViews();

        mProgressBar.setVisibility(View.VISIBLE);

        mViewModel.getUserDetails().observe(this, new Observer<UserDetails>() {
            @Override
            public void onChanged(@Nullable UserDetails user) {
                mProgressBar.setVisibility(View.INVISIBLE);
                showUserDetails(user);
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        mAdapter = new FragmentPager(fragmentManager);
        mAdapter.addFragment(OverviewFragment.newInstance(username), "Overview");
        mAdapter.addFragment(ReposFragment.newInstance(username), "Repos");
        mAdapter.addFragment(StarredFragment.newInstance(username), "Starred");
        mAdapter.addFragment(FollowingFragment.newInstance(username), "Following");
        mAdapter.addFragment(FollowersFragment.newInstance(username), "Followers");

        mViewPager.setAdapter(mAdapter);
        mTabLayout.setViewPager(mViewPager);

    }

    private void findViews() {
        mAppBarLayout = (AppBarLayout) findViewById(R.id.details_app_bar);
        mAvatarImageView = (CircleImageView) findViewById(R.id.details_profile_image);
        mCollapsedUserName = (TextView) findViewById(R.id.details_username_collapsed);
        mFullName = (TextView) findViewById(R.id.details_fullname);
        mUsername = (TextView) findViewById(R.id.details_username);
        mTabLayout = (SmartTabLayout) findViewById(R.id.details_tab);
        mViewPager = (ViewPager) findViewById(R.id.details_viewpager);
        mToolBar = (Toolbar) findViewById(R.id.details_toolbar);
        mDetailsContainer = (LinearLayout) findViewById(R.id.details_container);
        mFab = (FloatingActionButton) findViewById(R.id.details_fab);
        mNestedScrollView = (NestedScrollView) findViewById(R.id.details_nested_scrollview);
        mContainerFollowing = (LinearLayout) findViewById(R.id.details_following_container);
        mContainerFollowers = (LinearLayout) findViewById(R.id.details_followers_container);
        mContainerRepos = (LinearLayout) findViewById(R.id.details_repos_container);
        mFollowingCount = (TextView) findViewById(R.id.following_count);
        mFollowersCount = (TextView) findViewById(R.id.followers_count);
        mReposCount = (TextView) findViewById(R.id.repos_count);
        mGithubLink = (ImageView) findViewById(R.id.details_github_link);
        mProgressBar = (ProgressBar) findViewById(R.id.details_progress_bar);
    }

    private void setUpViews() {
        setSupportActionBar(mToolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }
        mNestedScrollView.setFillViewport(true);
        mAppBarLayout.addOnOffsetChangedListener(this);
        mFab.setOnClickListener(this);
        mContainerFollowers.setOnClickListener(this);
        mContainerFollowing.setOnClickListener(this);
        mContainerRepos.setOnClickListener(this);
        mGithubLink.setOnClickListener(this);
    }

    public void showUserDetails(@NonNull UserDetails user) {
        this.mUser = user;
        if (user.getLogin() == null) {
            Toast.makeText(this, "No Username", Toast.LENGTH_SHORT).show();
        }else {
            mUsername.setText(user.getLogin());
            mCollapsedUserName.setText(user.getLogin());
        }

        if (user.getName() == null) {
            mFullName.setTypeface(mFullName.getTypeface(), Typeface.ITALIC);
            mFullName.setText("Name Unavailable");
        } else {
            mFullName.setText(user.getName());
        }

        Glide.with(getApplicationContext())
                .load(user.getAvatarUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .thumbnail(0.5f)
                .into(mAvatarImageView);

        int followers = user.getFollowers();
        int following = user.getFollowing();
        int repos = user.getRepos();

        mFollowersCount.setText(""+followers+"");
        mFollowingCount.setText(""+following+"");
        mReposCount.setText(""+repos+"");
    }

    @Override
    public void onClick(View v) {
        int clickedView = v.getId();
        switch (clickedView) {
            case R.id.details_fab:
                Intent intent = createShareForecastIntent();
                startActivity(intent);
                break;
            case R.id.details_followers_container:
                mTabLayout.getTabAt(4).setSelected(true);
                break;
            case R.id.details_following_container:
                mTabLayout.getTabAt(3).setSelected(true);
                break;
            case R.id.details_repos_container:
                mTabLayout.getTabAt(1).setSelected(true);
                break;
            case R.id.details_github_link:
                String url = mUser.getHtmlUrl();
                openInBrowser(url);
                break;
        }

    }

    public void showLoadingIndicator(boolean isLoading) {

    }

    private Intent createShareForecastIntent() {
        String username = mUser.getLogin();
        String link = mUser.getHtmlUrl();
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("Checkout this awesome developer @" + username + ", " + link)
                .getIntent();
        return  shareIntent;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        BasicUtil.handleAlphaOnTitle(percentage, mDetailsContainer);
        BasicUtil.handleToolbarTitleVisibility(percentage, mCollapsedUserName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_favourite) {
            applyFavourite(item);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void applyFavourite(MenuItem item) {
        boolean favourite = mUser.isFavourite();
        if (!favourite) {
            item.setIcon(R.drawable.ic_heart_selected);
            mUser.setFavourite(true);
        } else {
            item.setIcon(R.drawable.ic_heart_outline);
            mUser.setFavourite(false);
        }
    }

    private void openInBrowser(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}
