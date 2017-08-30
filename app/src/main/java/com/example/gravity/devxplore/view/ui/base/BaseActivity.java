package com.example.gravity.devxplore.view.ui.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.application.App;
import com.example.gravity.devxplore.application.SettingsPrefActivity;
import com.example.gravity.devxplore.utilities.BasicUtil;
import com.example.gravity.devxplore.utilities.ThemeUtil;
import com.example.gravity.devxplore.view.ui.authentication.AuthActivity;
import com.example.gravity.devxplore.view.ui.base.Explore.ExploreFragment;
import com.example.gravity.devxplore.view.ui.base.favourites.FavouritesFragment;
import com.example.gravity.devxplore.view.ui.base.notification.NotificationFragment;
import com.example.gravity.devxplore.view.ui.base.statistics.StatsFragment;
import com.example.gravity.devxplore.view.ui.details.DetailsActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class BaseActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    private FragmentManager mFragmentManager;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private BottomNavigationView mBottomNavigationView;
    private CircleImageView mUserProfileProfile;
    private TextView mFullName, mUsername;

    private String[] FragmentTitles;
    private int navItemIndex = 0;

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @NonNull
    public static Intent createIntent(Context context) {
        return new Intent(context, BaseActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtil.onActivityCreateSetTheme(this);
        setContentView(R.layout.fragment_container_base);
        findViews();
        setUpViews();

        ExploreFragment fragment = (ExploreFragment) mFragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = ExploreFragment.newInstance();
            BasicUtil.addFragmentToActivity(mFragmentManager, fragment);
        }
    }

    private void findViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_drawer);
        View mNavHeader = mNavigationView.getHeaderView(0);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mFullName = (TextView) mNavHeader.findViewById(R.id.nav_fullname);
        mUsername = (TextView) mNavHeader.findViewById(R.id.nav_username);
        mUserProfileProfile = (CircleImageView) mNavHeader.findViewById(R.id.nav_profile_image);
    }

    private void setUpViews() {
        mFragmentManager = getSupportFragmentManager();
        setSupportActionBar(mToolbar);
        FragmentTitles = getResources().getStringArray(R.array.fragment_titles);
        Spinner mThemeSpinner = (Spinner) mNavigationView.getMenu().findItem(R.id.nav_themes).getActionView();
        mThemeSpinner.setSelection(App.currentThemePosition);
        App.currentThemePosition = mThemeSpinner.getSelectedItemPosition();
        mThemeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (App.currentThemePosition != position) {
                    ThemeUtil.changeToTheme(BaseActivity.this, position);
                }
                App.currentThemePosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        setToolbarTitle();
        selectNavMenu();
        loadNavHeader();
        setUpBottomNavigationView();
        setUpDrawerNavigationView();
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(FragmentTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        mNavigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void loadNavHeader() {
        mFullName.setText(R.string.name);
        mUsername.setText(R.string.owner_login);

        Glide.with(this).load(getString(R.string.owner_image))
                .crossFade()
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mUserProfileProfile);

        mNavigationView.getMenu().getItem(navItemIndex).setActionView(R.layout.selected_item_dot);
    }

    private void setUpBottomNavigationView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener
                (item -> {
                    Fragment fragment;
                    switch (item.getItemId()) {
                        case R.id.nav_explore:
                            navItemIndex = 0;
                            fragment = ExploreFragment.newInstance();
                            break;
                        case R.id.nav_fav:
                            navItemIndex = 1;
                            fragment = FavouritesFragment.newInstance();
                            break;
                        case R.id.nav_stats:
                            navItemIndex = 2;
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
                });
    }

    private void setUpDrawerNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(item -> {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nav_explore:
                    fragment = ExploreFragment.newInstance();
                    navItemIndex = 0;
                    break;
                case R.id.nav_profile:
                    startActivity(DetailsActivity.createIntent(this, getResources().getString(R.string.owner_login)));
                    return true;
                case R.id.nav_notifications:
                    fragment = NotificationFragment.newInstance();
                    navItemIndex = 5;
                    break;
                case R.id.nav_settings:
                    startActivity(new Intent(BaseActivity.this, SettingsPrefActivity.class));
                    mDrawerLayout.closeDrawers();
                    return true;
                case R.id.nav_sponsor:
                    String url = getResources().getString(R.string.andela);
                    openInBrowser(url);
                    return true;
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

    private void loadSelectedFragment(Fragment fragment) {
        selectNavMenu();
        setToolbarTitle();
        BasicUtil.replaceFragmentInActivity(mFragmentManager, fragment);
        mDrawerLayout.closeDrawers();
        invalidateOptionsMenu();
    }

    private void openInBrowser(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
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