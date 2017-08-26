package com.example.gravity.devxplore.view.ui.details.repos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gravity.devxplore.Injection;
import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.view.adapters.RepoAdapter;
import com.example.gravity.devxplore.data.model.Repository;
import com.example.gravity.devxplore.view.ui.details.DetailsContract;
import com.example.gravity.devxplore.view.ui.details.DetailsPresenter;
import com.example.gravity.devxplore.utilities.DividerItemDecoration;

import java.util.List;

/**
 * Created by gravity on 7/15/17.
 */

@SuppressWarnings("ALL")
public class ReposFragment extends Fragment implements DetailsContract.DetailView2, RepoAdapter.RepoAdapterListener{

    private final static String USERNAME = "";

    private DetailsContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    @Nullable
    private String username;
    private RepoAdapter.RepoAdapterListener mListener;

    @NonNull
    public static ReposFragment newInstance(String username) {
        ReposFragment reposFragment = new ReposFragment();
        Bundle args = new Bundle();
        args.putString(USERNAME, username);
        reposFragment.setArguments(args);
        return reposFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        assert username != null;
        mPresenter.loadRepos(username);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repos, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.repos_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        DetailsPresenter mUserDetailsPresenter = new DetailsPresenter(Injection.provideDataManager(getActivity().getApplicationContext()), this);

        username = getArguments().getString(USERNAME);

        return view;
    }

    @Override
    public void setPresenter(DetailsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showRepos(List<Repository> repositories) {
        List<Repository> mRepositories = repositories;
        mRecyclerView.setAdapter(new RepoAdapter(getActivity(), repositories, this));
    }

    @Override
    public void onCardClicked(int position) {

    }

    @Override
    public void onCardLongClicked(int position) {

    }
}
