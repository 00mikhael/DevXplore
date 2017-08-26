package com.example.gravity.devxplore.view.ui.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gravity.devxplore.R;

/**
 * Created by gravity on 7/2/17.
 */

@SuppressWarnings("ALL")
public class ProfileFragment extends Fragment implements ProfileContract.ProfileView {

    ProfileContract.Presenter mPresenter;

    @NonNull
    public static ProfileFragment newInstance() {
        ProfileFragment profileFragment = new ProfileFragment();
        return profileFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
