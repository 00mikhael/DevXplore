package com.example.gravity.devxplore.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.gravity.devxplore.Injection;
import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.screens.main.favourites.FavouritesFragment;
import com.example.gravity.devxplore.screens.main.profile.ProfileFragment;
import com.example.gravity.devxplore.screens.main.users.UserXploreContract;
import com.example.gravity.devxplore.screens.main.users.UserXploreFragment;
import com.example.gravity.devxplore.screens.main.users.UserXplorePresenter;
import com.example.gravity.devxplore.utils.Util;

import info.hoang8f.android.segmented.SegmentedGroup;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private CollapsingToolbarLayout mCollapsingToolbar;
    private BottomNavigationView mBottomNavigationView1;
    private BottomNavigationView mBottomNavigationView2;
    private UserXplorePresenter mUserXplorePresenter;
    private UserXploreContract.Presenter mPresenter;
    private AppBarLayout mHomeAppBar;
    private SegmentedGroup segment;
    private Toolbar mToolbar;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_fragment_container);

        mBottomNavigationView1 = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        fm = getSupportFragmentManager();

        UserXploreFragment fragment1 = (UserXploreFragment) fm.findFragmentById(R.id.fragment_container);
        if (fragment1 == null) {
            fragment1 = UserXploreFragment.newInstance();
            Util.addFragmentToActivity(fm, fragment1, R.id.fragment_container);
        }

        mUserXplorePresenter = new UserXplorePresenter(Injection.provideDataManager(this), fragment1);

        /*setupView();*/

        /*segment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                segment.animate();
                changeFragment(v);
            }
        });*/

        mBottomNavigationView1.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        boolean isHome = false;
                        Fragment fragment = null;

                        switch (item.getItemId()) {
                            case R.id.menu_home:
                                fragment = UserXploreFragment.newInstance();
                                isHome = true;
                                break;
                            case R.id.menu_fav:
                                fragment = FavouritesFragment.newInstance();
                                break;
                            case R.id.menu_profile:
                                fragment = ProfileFragment.newInstance();
                                break;
                        }

                        Util.replaceFragmentInActivity(fm, fragment, R.id.fragment_container);
                        return true;
                    }
                });
    }

    /*@Override
    protected void onStart() {
        super.onStart();

        HomeFragment fragment1 =  (HomeFragment) fm.findFragmentById(R.id.fragment_container1);
        if (fragment1 == null) {
            fragment1 = HomeFragment.newInstance();
            Util.addFragmentToActivity(fm, fragment1, R.id.fragment_container1);
        }

        mUserXplorePresenter = new UserXplorePresenter(Injection.provideDataManager(this), fragment1);
    }*/

    /*private void setupView() {
        fm = getSupportFragmentManager();
        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.home_collapsing_toolbar);
        mBottomNavigationView1 = (BottomNavigationView) findViewById(R.id.bottom_navigation1);
        mHomeAppBar = (AppBarLayout) findViewById(R.id.home_app_bar);
        mToolbar = (Toolbar) findViewById(R.id.home_toolbar);
        segment = (SegmentedGroup) findViewById(R.id.segmented_group);
        Util.initCollapsingToolbar(this, mCollapsingToolbar, mHomeAppBar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("");
        segment.setTintColor(R.color.colorAccent, R.color.colorPrimary);
        mBottomNavigationView2 = (BottomNavigationView) findViewById(R.id.bottom_navigation2);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   /* @Override
    public void setPresenter(UserXploreContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoadingIndicator(boolean isLoading) {

    }

    @Override
    public void showRepos(List<Repository> repositories) {

    }

    @Override
    public void showUserDetailsUi(String user) {

    }

    @Override
    public void loadUsers() {
        mPresenter.start();
    }

    private void changeFragment(View v) {
        int clickedView = v.getId();
        Fragment fragment = null;
        switch (clickedView) {
            case R.id.home:
                // TODO: 8/3/17
                fragment = HomeFragment.newInstance();
                break;
            case R.id.stats:
                //// TODO: 8/3/17
                fragment = StatsFragment.newInstance();
                break;
        }
        Util.replaceFragmentInActivity(fm, fragment, R.id.fragment_container1);
    }*/

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void onClick(View v) {

    }
}