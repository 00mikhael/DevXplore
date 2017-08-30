package com.example.gravity.devxplore.view.ui.base.favourites;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.view.adapters.FavouriteAdapter;
import com.example.gravity.devxplore.view.ui.base.BaseViewModel;
import com.example.gravity.devxplore.view.ui.details.DetailsActivity;

import java.util.List;

/**
 * Created by gravity on 8/5/17.
 */

@SuppressWarnings("ALL")
public class FavouritesFragment extends Fragment implements LifecycleRegistryOwner, FavouriteAdapter.FavouriteAdapterListener {

    private RecyclerView mRecyclerView;
    private FavouriteAdapter mAdapter;
    private List<User> mUsers;
    private BaseViewModel mViewModel;

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @NonNull
    public static FavouritesFragment newInstance() {
        FavouritesFragment favouritesFragment = new FavouritesFragment();
        return favouritesFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        mViewModel = ViewModelProviders.of(this).get(BaseViewModel.class);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.favourites_recycler_view);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new FavouriteAdapter(getActivity(), R.layout.list_item_user_linear, this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getFavouriteResult().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> favourites) {
                if (favourites != null) {
                    mAdapter.setList(favourites);
                }
            }
        });
    }

    @Override
    public void onCardClicked(int position) {
        Toast.makeText(getActivity(), "Card Clicked", Toast.LENGTH_SHORT).show();
        User user = mUsers.get(position);
        String username = user.getLogin();
        Intent intent = DetailsActivity.createIntent(getActivity(), username);
        startActivity(intent);
    }

    @Override
    public void onCardLongClicked(int position) {

    }

    @Override
    public void onCardFavouriteClicked(int position) {
        Toast.makeText(getActivity(), "Favourite Clicked", Toast.LENGTH_SHORT).show();
        User user = mUsers.get(position);
        String username = user.getLogin();
        boolean favourite = user.isFavourite();
        favourite = !favourite;
        mViewModel.setFavourite(username, favourite);
    }
}
