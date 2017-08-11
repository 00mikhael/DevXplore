package com.example.gravity.devxplore.screens.details.overview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gravity.devxplore.Injection;
import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.adapters.EventAdapter;
import com.example.gravity.devxplore.data.model.UserEvents;
import com.example.gravity.devxplore.screens.details.DetailsContract;
import com.example.gravity.devxplore.screens.details.DetailsPresenter;
import com.example.gravity.devxplore.utilities.DividerItemDecoration;

import java.util.List;

/**
 * Created by gravity on 7/15/17.
 */

@SuppressWarnings("ALL")
public class OverviewFragment extends Fragment implements DetailsContract.DetailView1 {

    public final static String USERNAME = "";

    private DetailsContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    @Nullable
    private String username;

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
        assert username != null;
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

        DetailsPresenter mUserDetailsPresenter = new DetailsPresenter(Injection.provideDataManager(getActivity().getApplicationContext()), this);

        username = getArguments().getString(USERNAME);

        return view;
    }

    @Override
    public void setPresenter(DetailsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showOverview(List<UserEvents> userEvents) {
        List<UserEvents> mEvents = userEvents;
        mRecyclerView.setAdapter(new EventAdapter(getActivity(), userEvents));
        Toast.makeText(getActivity(), "Got OverView too", Toast.LENGTH_SHORT).show();
        Log.e("UserOverView", "OverView Called");
    }
}
