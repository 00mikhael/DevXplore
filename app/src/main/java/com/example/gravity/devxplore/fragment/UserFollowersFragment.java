package com.example.gravity.devxplore.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gravity.devxplore.R;

/**
 * Created by gravity on 7/15/17.
 */

public class UserFollowersFragment extends Fragment {

    public static UserFollowersFragment newInstance() {
        UserFollowersFragment userFollowersFragment = new UserFollowersFragment();
        return userFollowersFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_followers, container, false);
        return view;
    }
}
