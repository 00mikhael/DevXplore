package com.example.gravity.devxplore.view.ui.Notification;

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

public class NotificationFragment extends Fragment {
    @NonNull
    public static NotificationFragment newInstance() {
        NotificationFragment notificationFragment = new NotificationFragment();
        return notificationFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        return view;
    }
}
