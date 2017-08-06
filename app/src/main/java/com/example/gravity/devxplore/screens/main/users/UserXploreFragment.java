package com.example.gravity.devxplore.screens.main.users;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.gravity.devxplore.Injection;
import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.data.model.Repository;
import com.example.gravity.devxplore.utils.Util;

import java.util.List;

import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * Created by gravity on 7/2/17.
 */

public class UserXploreFragment extends Fragment implements UserXploreContract.XploreView1, View.OnClickListener {

    private UserXplorePresenter mUserXplorePresenter;
    FragmentManager fm;
    CollapsingToolbarLayout mCollapsingToolbar;
    UserXploreContract.Presenter mPresenter;
    SegmentedGroup segment;
    AppBarLayout mHomeAppBar;
    Toolbar mToolbar;
    boolean viewCalled = false;

    @Override
    public void onStart() {
        super.onStart();
    }

    public UserXploreFragment() {
    }

    public static UserXploreFragment newInstance() {
        return new UserXploreFragment();
    }

    @Override
    public void onResume() {
        super.onResume();

        Toast.makeText(getActivity(), "onResume called!!", Toast.LENGTH_SHORT).show();

        HomeFragment fragment1 =  (HomeFragment) fm.findFragmentById(R.id.fragment_container1);
        if (fragment1 == null) {
            fragment1 = HomeFragment.newInstance();
            Util.addFragmentToActivity(fm, fragment1, R.id.fragment_container1);
        }

        mUserXplorePresenter = new UserXplorePresenter(Injection.provideDataManager(getActivity()), fragment1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dev_xplore, container, false);

        fm = getChildFragmentManager();
        mCollapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.home_collapsing_toolbar);
        mHomeAppBar = (AppBarLayout) view.findViewById(R.id.home_app_bar);
        mToolbar = (Toolbar) view.findViewById(R.id.home_toolbar);
        segment = (SegmentedGroup) view.findViewById(R.id.segmented_group);
        Util.initCollapsingToolbar(getActivity(), mCollapsingToolbar, mHomeAppBar);
        segment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                HomeFragment fragment1 = null;
                StatsFragment fragment2 = null;
                boolean isHome = false;

                switch (checkedId) {
                    case R.id.home:
                        fragment1 = HomeFragment.newInstance();
                        isHome = true;
                        break;
                    case R.id.stats:
                        fragment2 = StatsFragment.newInstance();
                        break;
                }
                if (isHome) {
                    Util.replaceFragmentInActivity(fm, fragment1, R.id.fragment_container1);
                    mUserXplorePresenter = new UserXplorePresenter(Injection.provideDataManager(getActivity()), fragment1);
                }
                else {
                    Util.replaceFragmentInActivity(fm, fragment2, R.id.fragment_container1);
                    mUserXplorePresenter = new UserXplorePresenter(Injection.provideDataManager(getActivity()), fragment2);
                }
            }
        });

        setupView();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //mPresenter.loadUsers(true);
        //viewCalled = true;
        //mPresenter.loadSlideRepos(true);
    }

    private void setupView() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
        segment.animate();
        segment.setTintColor(R.color.colorAccent, R.color.colorPrimary);
    }

    @Override
    public void setPresenter(@NonNull UserXploreContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showRepos(List<Repository> repositories) {

    }

    @Override
    public void showLoadingIndicator(boolean isLoading) {

    }

    @Override
    public void showUserDetailsUi(String user) {

    }

    @Override
    public void loadUsers() {
        mPresenter.start();
    }

    @Override
    public void onClick(View v) {

    }
}
