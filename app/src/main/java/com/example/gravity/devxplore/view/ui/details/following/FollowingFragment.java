package com.example.gravity.devxplore.view.ui.details.following;

import android.app.ActivityOptions;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.utilities.DividerItemDecoration;
import com.example.gravity.devxplore.view.adapters.UsersAdapter;
import com.example.gravity.devxplore.view.ui.details.DetailsActivity;

import java.util.List;

/**
 * Created by gravity on 7/15/17.
 */

@SuppressWarnings("ALL")
public class FollowingFragment extends Fragment implements LifecycleRegistryOwner, UsersAdapter.UserAdapterListener {

    private static final String USERNAME = "username";

    private RecyclerView mRecyclerView;
    private FollowingViewModel mViewModel;
    private UsersAdapter mAdapter;
    private List<User> mFollowing;
    private String username;

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @NonNull
    public static FollowingFragment newInstance(String username) {
        FollowingFragment followingFragment = new FollowingFragment();
        Bundle args = new Bundle();
        args.putString(USERNAME, username);
        followingFragment.setArguments(args);
        return followingFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_following, container, false);
        mViewModel = ViewModelProviders.of(this).get(FollowingViewModel.class);
        username = getArguments().getString(USERNAME);
        mViewModel.setCurrentUser(username);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.following_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        mAdapter = new UsersAdapter(getActivity(), R.layout.list_item_user_linear, this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        mViewModel.getUserFollowing().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> following) {
                showFollowing(following);
            }
        });
        return view;
    }

    public void showFollowing(List<User> following) {
        this.mFollowing = following;
        mAdapter.setList(following);
    }

    @Override
    public void onCardClicked(int position, View v) {
        User user = mFollowing.get(position);
        String username = user.getLogin();
        Intent intent = DetailsActivity.createIntent(getActivity(), username);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String transitionName = getString(R.string.transition);
            ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(getActivity(), v, transitionName);
            startActivity(intent, transitionActivityOptions.toBundle());
        }else {
            startActivity(intent);
        }
    }

    @Override
    public void onCardLongClicked(int position) {

    }

    @Override
    public void onCardFavouriteClicked(int position) {
        User user = mFollowing.get(position);
        boolean favourite = user.isFavourite();
        String username = user.getLogin();
        favourite = !favourite;
        mViewModel.setFavourite(username, favourite);
    }
}