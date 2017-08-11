package com.example.gravity.devxplore.screens.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.screens.base.Home.HomeFragment;
import com.example.gravity.devxplore.screens.base.favourites.FavouritesFragment;
import com.example.gravity.devxplore.screens.base.statistics.StatsFragment;
import com.example.gravity.devxplore.utilities.Util;

public class BaseActivity extends AppCompatActivity {

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container_base);

        BottomNavigationView mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        fm = getSupportFragmentManager();
        HomeFragment fragment = (HomeFragment) fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = HomeFragment.newInstance();
            Util.addFragmentToActivity(fm, fragment);
        }

        mBottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment fragment = null;

                        switch (item.getItemId()) {
                            case R.id.nav_home:
                                fragment = HomeFragment.newInstance();
                                break;
                            case R.id.nav_fav:
                                fragment = FavouritesFragment.newInstance();
                                break;
                            case R.id.nav_stats:
                                fragment = StatsFragment.newInstance();
                                break;
                        }
                        assert fragment != null;
                        Util.replaceFragmentInActivity(fm, fragment);
                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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
}