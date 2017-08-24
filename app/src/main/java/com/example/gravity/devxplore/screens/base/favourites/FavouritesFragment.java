package com.example.gravity.devxplore.screens.base.favourites;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.adapters.FavouriteAdapter;
import com.example.gravity.devxplore.data.model.User;

import java.util.List;

/**
 * Created by gravity on 8/5/17.
 */

@SuppressWarnings("ALL")
public class FavouritesFragment extends Fragment implements FavouritesContract.FavouriteView, FavouriteAdapter.FavouriteAdapterListener {

    private FavouritesContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private List<User> mUsers;

    @NonNull
    public static FavouritesFragment newInstance() {
        FavouritesFragment favouritesFragment = new FavouritesFragment();
        return favouritesFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        return view;
    }

    @Override
    public void setPresenter(FavouritesContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showFavourite(List<User> favourites) {
        mRecyclerView.setAdapter(new FavouriteAdapter(getActivity(), favourites, R.layout.list_item_user_linear, this));
    }

    @Override
    public void onCardClicked(int position) {
        Toast.makeText(getActivity(), "Card Clicked", Toast.LENGTH_SHORT).show();
        User user = mUsers.get(position);
        String username = user.getLogin();
        mPresenter.openFavDetails(getActivity(), username);
    }

    @Override
    public void onCardLongClicked(int position) {

    }

    @Override
    public void onCardFavouriteClicked(int position) {
        Toast.makeText(getActivity(), "Favourite Clicked", Toast.LENGTH_SHORT).show();
        User user = mUsers.get(position);
        boolean favourite = user.isFavourite();
        favourite = !favourite;
        mPresenter.removeFavourite(user, favourite);
    }
}
