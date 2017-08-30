package com.example.gravity.devxplore.view.ui.details.starred;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
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
import com.example.gravity.devxplore.data.model.Repository;
import com.example.gravity.devxplore.utilities.DividerItemDecoration;
import com.example.gravity.devxplore.view.adapters.RepoAdapter;

import java.util.List;

/**
 * Created by gravity on 7/15/17.
 */

@SuppressWarnings("ALL")
public class StarredFragment extends Fragment implements LifecycleRegistryOwner, RepoAdapter.RepoAdapterListener {

    private static final String USERNAME = "username";

    private RecyclerView mRecyclerView;
    private StarredViewModel mViewModel;
    private RepoAdapter mAdapter;
    private List<Repository> mUserStarredRepos;
    private String username;

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @NonNull
    public static StarredFragment newInstance(String username) {
        StarredFragment starredFragment = new StarredFragment();
        Bundle args = new Bundle();
        args.putString(USERNAME, username);
        starredFragment.setArguments(args);
        return starredFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_starred, container, false);
        mViewModel = ViewModelProviders.of(this).get(StarredViewModel.class);
        username = getArguments().getString(USERNAME);
        mViewModel.setCurrentUser(username);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.starred_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        mAdapter = new RepoAdapter(getActivity(), this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        mViewModel.getUserStarred().observe(this, new Observer<List<Repository>>() {
            @Override
            public void onChanged(@Nullable List<Repository> userStarred) {
                showStarred(userStarred);
            }
        });
        return view;
    }

    public void showStarred(List<Repository> userStarred) {
        this.mUserStarredRepos = userStarred;
        mAdapter.setList(userStarred);
    }

    @Override
    public void onCardClicked(int position) {
        Repository repo = mUserStarredRepos.get(position);
        String url = repo.getRepoUrl();
        openInBrowser(url);
    }

    @Override
    public void onCardLongClicked(int position) {

    }

    private void openInBrowser(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
