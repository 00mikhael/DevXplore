package com.example.gravity.devxplore.screens.base.favourites;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.gravity.devxplore.data.source.DataRepository;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.screens.details.DetailsActivity;

import java.util.List;

/**
 * Created by gravity on 8/5/17.
 */

@SuppressWarnings("ALL")
public class FavouritesPresenter implements FavouritesContract.Presenter {

    private DataRepository mDataRepositoryManager;
    private FavouritesContract.FavouriteView mFavouriteView;
    private List<User> mUsers;

    public FavouritesPresenter(@NonNull DataRepository dataRepositoryManager, @NonNull FavouritesContract.FavouriteView favouriteView) {
        this.mDataRepositoryManager = dataRepositoryManager;
        this.mFavouriteView = favouriteView;

        favouriteView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadFavourites() {
        mUsers = mDataRepositoryManager.getFavourites();
        mFavouriteView.showFavourite(mUsers);
    }

    @Override
    public void removeFavourite(@NonNull User user, boolean isFavourite) {
        user.setFavourite(isFavourite);
    }

    @Override
    public void openFavDetails(@NonNull Context context, @NonNull String username) {
        context.startActivity(DetailsActivity.createIntent(context, username));
    }
}
