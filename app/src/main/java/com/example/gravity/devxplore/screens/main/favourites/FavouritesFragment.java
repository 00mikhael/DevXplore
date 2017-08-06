package com.example.gravity.devxplore.screens.main.favourites;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gravity.devxplore.R;

/**
 * Created by gravity on 8/5/17.
 */

public class FavouritesFragment extends Fragment implements FavouritesContract.FavouriteView {

    FavouritesContract.Presenter mPresenter;

    public static FavouritesFragment newInstance() {
        FavouritesFragment favouritesFragment = new FavouritesFragment();
        return favouritesFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        return view;
    }

    @Override
    public void setPresenter(FavouritesContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
