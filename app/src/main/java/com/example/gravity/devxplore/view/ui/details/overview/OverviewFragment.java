package com.example.gravity.devxplore.view.ui.details.overview;

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
import com.example.gravity.devxplore.view.adapters.PopularReposAdapter;
import com.example.gravity.devxplore.data.model.Repository;
import com.example.gravity.devxplore.view.ui.details.DetailsContract;
import com.example.gravity.devxplore.view.ui.details.DetailsPresenter;
import com.example.gravity.devxplore.utilities.DividerItemDecoration;

import java.util.List;

/**
 * Created by gravity on 7/15/17.
 */

@SuppressWarnings("ALL")
public class OverviewFragment extends Fragment implements DetailsContract.DetailView1, PopularReposAdapter.RepoAdapterListener {

    public final static String USERNAME = "";
    public final static String BIO = "";
    public final static String TAG = "OVERVIEWFRAG";

    private DetailsContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    @Nullable
    private String username;
    /*private String userbio;*/

    @NonNull
    public static OverviewFragment newInstance(String username) {
        OverviewFragment overviewFragment = new OverviewFragment();
        Bundle args = new Bundle();
        args.putString(USERNAME, username);
        overviewFragment.setArguments(args);
        return overviewFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadOverview(username);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.overview_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        /*TextView bioText = (TextView) view.findViewById(R.id.bio);*/

        DetailsPresenter mUserDetailsPresenter = new DetailsPresenter(Injection.provideDataManager(getActivity().getApplicationContext()), this);

        username = getArguments().getString(USERNAME);
        /*userbio = getArguments().getString(BIO);
        if (userbio == null) {
            bioText.setText("No bio");
        }else {
            bioText.setText(userbio);
        }*/

        return view;
    }

    @Override
    public void setPresenter(DetailsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showOverview(List<Repository> popularRepos) {
        List<Repository> mPopularRepos = popularRepos;
        mRecyclerView.setAdapter(new PopularReposAdapter(getActivity(), popularRepos,this));
    }

    @Override
    public void onCardClicked(int position) {

    }

    @Override
    public void onCardLongClicked(int position) {

    }
}
