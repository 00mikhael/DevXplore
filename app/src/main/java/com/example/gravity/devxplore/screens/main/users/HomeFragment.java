package com.example.gravity.devxplore.screens.main.users;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.adapters.UsersAdapter;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.utils.GridSpacingItemDecoration;
import com.example.gravity.devxplore.utils.Util;

import java.util.List;

/**
 * Created by gravity on 8/3/17.
 */

public class HomeFragment extends Fragment implements UserXploreContract.XploreView2, View.OnClickListener {

    RecyclerView mRecyclerView;
    UserXploreContract.Presenter mPresenter;

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, Util.dpToPx(getActivity(), 10), true));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setPresenter(UserXploreContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void openUserDetails(@NonNull User viewUser) {

    }

    @Override
    public void showUsers(List<User> users) {
        mRecyclerView.setAdapter(new UsersAdapter(getContext(), users, R.layout.list_item_dev_grid));
    }

    @Override
    public void onClick(View v) {

    }
}