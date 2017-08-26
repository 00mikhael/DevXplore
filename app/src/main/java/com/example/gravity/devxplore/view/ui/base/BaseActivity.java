package com.example.gravity.devxplore.view.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.view.ui.Notification.NotificationFragment;
import com.example.gravity.devxplore.view.ui.authentication.AuthActivity;
import com.example.gravity.devxplore.view.ui.base.Explore.ExploreFragment;
import com.example.gravity.devxplore.view.ui.base.favourites.FavouritesFragment;
import com.example.gravity.devxplore.view.ui.base.statistics.StatsFragment;
import com.example.gravity.devxplore.view.ui.profile.ProfileFragment;
import com.example.gravity.devxplore.view.ui.settings.About.AboutFragment;
import com.example.gravity.devxplore.view.ui.settings.SettingsPrefActivity;
import com.example.gravity.devxplore.view.ui.settings.Sponsor.SponsorFragment;
import com.example.gravity.devxplore.view.ui.settings.Theme.ThemeDialog;
import com.example.gravity.devxplore.utilities.Util;

import de.hdodenhof.circleimageview.CircleImageView;

public class BaseActivity extends AppCompatActivity {

    private FragmentManager fm;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private BottomNavigationView mBottomNavigationView;
    private ImageView NavHeaderBg;
    private CircleImageView mUserProfileProfile;
    private TextView mFullName, mUsername;

    public static int navItemIndex = 0;
    private String[] FragmentTitles;

    private static final String TAG_HOME = "home";
    private static final String TAG_FAV = "favourite";
    private static final String TAG_STAT = "statistics";
    private static final String TAG_PROFILE = "profile";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_ABOUT = "about";
    private static final String TAG_SPONSOR = "sponsor";
    public static String CURRENT_TAG = TAG_HOME;

    private static final String urlNavHeaderBg = "https://api.androidhive.info/images/nav-menu-header-bg.jpg";
    private static final String urlProfileImg = "https://lh3.googleusercontent.com/eCtE_G34M9ygdkmOpYvCag1vBARCmZwnVS6rS5t4JLzJ6QgQSBquM0nuTsCpLhYbKljoyS-txg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container_base);
        findViews();
        setUpViews();
        setToolbarTitle();
        selectNavMenu();
        loadNavHeader();
        setUpDrawerNavigationView();
        setUpBottomNavigationView();

        ExploreFragment fragment = (ExploreFragment) fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = ExploreFragment.newInstance();
            Util.addFragmentToActivity(fm, fragment);
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(FragmentTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        mNavigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void findViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_drawer);
        View navHeader = mNavigationView.getHeaderView(0);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mFullName = (TextView) navHeader.findViewById(R.id.nav_fullname);
        mUsername = (TextView) navHeader.findViewById(R.id.nav_username);
        NavHeaderBg = (ImageView) navHeader.findViewById(R.id.nav_image_bg);
        mUserProfileProfile = (CircleImageView) navHeader.findViewById(R.id.nav_profile_image);
    }

    private void setUpViews() {
        fm = getSupportFragmentManager();
        setSupportActionBar(mToolbar);
        FragmentTitles = getResources().getStringArray(R.array.fragment_titles);
    }

    private void setUpBottomNavigationView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment fragment;
                        switch (item.getItemId()) {
                            case R.id.nav_explore:
                                navItemIndex = 0;
                                CURRENT_TAG = TAG_HOME;
                                fragment = ExploreFragment.newInstance();
                                break;
                            case R.id.nav_fav:
                                navItemIndex = 1;
                                CURRENT_TAG = TAG_FAV;
                                fragment = FavouritesFragment.newInstance();
                                break;
                            case R.id.nav_stats:
                                navItemIndex = 2;
                                CURRENT_TAG = TAG_STAT;
                                fragment = StatsFragment.newInstance();
                                break;
                            default:
                                fragment = ExploreFragment.newInstance();
                                navItemIndex = 0;
                        }

                        if (item.isChecked()) {
                            item.setChecked(false);
                        } else {
                            item.setChecked(true);
                        }
                        item.setChecked(true);

                        loadSelectedFragment(fragment);
                        return true;
                    }
                });
    }

    private void setUpDrawerNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.nav_explore:
                        fragment = ExploreFragment.newInstance();
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_profile:
                        fragment = ProfileFragment.newInstance();
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_PROFILE;
                        break;
                    case R.id.nav_notifications:
                        fragment = NotificationFragment.newInstance();
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_NOTIFICATIONS;
                        break;
                    case R.id.nav_theme:
                        startActivity(new Intent(BaseActivity.this, ThemeDialog.class));
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_settings:
                        startActivity(new Intent(BaseActivity.this, SettingsPrefActivity.class));
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_about:
                        fragment = AboutFragment.newInstance();
                        navItemIndex = 6;
                        CURRENT_TAG = TAG_ABOUT;
                        break;
                    case R.id.nav_sponsor:
                        fragment = SponsorFragment.newInstance();
                        navItemIndex = 7;
                        CURRENT_TAG = TAG_SPONSOR;
                        break;
                    case R.id.nav_logout:
                        startActivity(new Intent(BaseActivity.this, AuthActivity.class));
                        mDrawerLayout.closeDrawers();
                        return true;
                    default:
                        fragment = ExploreFragment.newInstance();
                        navItemIndex = 0;
                }

                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                item.setChecked(true);
                loadSelectedFragment(fragment);

                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void loadNavHeader() {
        mFullName.setText("M.C Nwankwo");
        mUsername.setText("zirogravity");

        Glide.with(this).load(urlNavHeaderBg)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(NavHeaderBg);

        Glide.with(this).load(urlProfileImg)
                .crossFade()
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mUserProfileProfile);

        mNavigationView.getMenu().getItem(navItemIndex).setActionView(R.layout.menu_dot);
    }

    private void loadSelectedFragment(Fragment fragment) {
        selectNavMenu();
        setToolbarTitle();
        if (fm.findFragmentByTag(CURRENT_TAG) != null) {
            mDrawerLayout.closeDrawers();
            return;
        }
        Util.replaceFragmentInActivity(fm, fragment);
        mDrawerLayout.closeDrawers();
        invalidateOptionsMenu();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            // launch settings activity
            startActivity(new Intent(BaseActivity.this, SettingsPrefActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}