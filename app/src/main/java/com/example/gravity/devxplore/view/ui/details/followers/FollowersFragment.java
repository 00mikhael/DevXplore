package com.example.gravity.devxplore.view.ui.details.followers;

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
import com.example.gravity.devxplore.view.adapters.UsersAdapter;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.view.ui.details.DetailsContract;
import com.example.gravity.devxplore.view.ui.details.DetailsPresenter;
import com.example.gravity.devxplore.utilities.DividerItemDecoration;

import java.util.List;

/**
 * Created by gravity on 7/15/17.
 */

@SuppressWarnings("ALL")
public class FollowersFragment extends Fragment implements DetailsContract.DetailView5, UsersAdapter.UserAdapterListener {

    public final static String USERNAME = "";

    private RecyclerView mRecyclerView;
    private DetailsContract.Presenter mPresenter;
    private List<User> mFollowers;
    @Nullable
    private String username;

    @NonNull
    public static FollowersFragment newInstance(String username) {
        FollowersFragment followersFragment = new FollowersFragment();
        Bundle args = new Bundle();
        args.putString(USERNAME, username);
        followersFragment.setArguments(args);
        return followersFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadFollowers(username);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_followers, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.followers_recycler_view);
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
    public void showFollowers(List<User> followers) {
        this.mFollowers = followers;
        mRecyclerView.setAdapter(new UsersAdapter(getActivity(), followers, R.layout.list_item_user_linear, this));
    }

    @Override
    public void onCardClicked(int position) {
        User user = mFollowers.get(position);
        String username = user.getLogin();
        mPresenter.openUserDetails(getActivity(), username);
    }

    @Override
    public void onCardLongClicked(int position) {

    }

    @Override
    public void onCardFavouriteClicked(int position) {
        User follower = mFollowers.get(position);
        boolean favourite = follower.isFavourite();
        favourite = !favourite;
        mPresenter.updateFavourite(follower, favourite);
    }
}
