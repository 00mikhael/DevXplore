package com.example.gravity.devxplore.screens.settings.Sponsor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gravity.devxplore.R;

/**
 * Created by gravity on 8/20/17.
 */

public class SponsorFragment extends Fragment {
    @NonNull
    public static SponsorFragment newInstance() {
        SponsorFragment sponsorFragment = new SponsorFragment();
        return sponsorFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        return view;
    }
}
