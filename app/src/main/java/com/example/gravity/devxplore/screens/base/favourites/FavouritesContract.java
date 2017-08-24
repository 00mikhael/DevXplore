package com.example.gravity.devxplore.screens.base.favourites;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.screens.base.BasePresenter;
import com.example.gravity.devxplore.screens.base.BaseView;

import java.util.List;

/**
 * Created by gravity on 8/5/17.
 */

@SuppressWarnings("ALL")
public interface FavouritesContract {

    interface Presenter extends BasePresenter {
        void loadFavourites();

        void removeFavourite(User user, boolean isFavourite);

        void openFavDetails(@NonNull Context context, @NonNull String username);
    }

    interface FavouriteView extends BaseView<Presenter> {
        void showFavourite(List<User> favourites);
    }
}
