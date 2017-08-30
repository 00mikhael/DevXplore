package com.example.gravity.devxplore.view.ui.details.overview;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.data.model.Repository;
import com.example.gravity.devxplore.utilities.DividerItemDecoration;
import com.example.gravity.devxplore.view.adapters.PopularReposAdapter;

import java.util.List;

/**
 * Created by gravity on 7/15/17.
 */

@SuppressWarnings("ALL")
public class OverviewFragment extends Fragment implements LifecycleRegistryOwner, PopularReposAdapter.RepoAdapterListener {

    private static final String TAG = "OVERVIEW";
    private static final String USERNAME = "username";

    private RecyclerView mRecyclerView;
    private OverviewViewModel mViewModel;
    private PopularReposAdapter mAdapter;
    private List<Repository> mUserPopRepos;
    private String username;

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @NonNull
    public static OverviewFragment newInstance(String username) {
        OverviewFragment overviewFragment = new OverviewFragment();
        Bundle args = new Bundle();
        args.putString(USERNAME, username);
        overviewFragment.setArguments(args);
        return overviewFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        mViewModel = ViewModelProviders.of(this).get(OverviewViewModel.class);
        Toast.makeText(getActivity(), "oncreateview called", Toast.LENGTH_SHORT).show();
        username = getArguments().getString(USERNAME);
        mViewModel.setCurrentUser(username);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.overview_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        mAdapter = new PopularReposAdapter(getActivity(), this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        mViewModel.getUserPopRepos().observe(this, new Observer<List<Repository>>() {
            @Override
            public void onChanged(@Nullable List<Repository> popRepos) {
                Log.e(TAG, popRepos.toString());
                Toast.makeText(getActivity(), "showrepos called", Toast.LENGTH_SHORT).show();
                showPopRepos(popRepos);
            }
        });
        return view;
    }

    public void showPopRepos(List<Repository> popRepos) {
        this.mUserPopRepos = popRepos;
        mAdapter.setList(popRepos);
    }

    @Override
    public void onCardClicked(int position) {
        Repository repo = mUserPopRepos.get(position);
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
