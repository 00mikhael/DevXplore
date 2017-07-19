package com.example.gravity.devxplore.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gravity.devxplore.R;

/**
 * Created by gravity on 7/2/17.
 */

public class RepoXploreFragment extends Fragment {

    public static RepoXploreFragment newInstance() {
        RepoXploreFragment repoXploreFragment = new RepoXploreFragment();
        return repoXploreFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_xplore, container, false);
        return view;
    }
}
