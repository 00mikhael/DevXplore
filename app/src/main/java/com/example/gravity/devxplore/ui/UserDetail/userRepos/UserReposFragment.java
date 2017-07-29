package com.example.gravity.devxplore.ui.UserDetail.userRepos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gravity.devxplore.R;

/**
 * Created by gravity on 7/15/17.
 */

public class UserReposFragment extends Fragment {

    public static UserReposFragment newInstance() {
        UserReposFragment userReposFragment = new UserReposFragment();
        return userReposFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_repos, container, false);
        return view;
    }
}
