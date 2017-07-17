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

public class UserFollowingFragment extends Fragment {

    public static UserFollowingFragment newInstance() {
        UserFollowingFragment userFollowingFragment = new UserFollowingFragment();
        return userFollowingFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_following, container, false);
        return view;
    }
}
