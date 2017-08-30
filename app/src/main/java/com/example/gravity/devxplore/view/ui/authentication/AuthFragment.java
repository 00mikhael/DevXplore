package com.example.gravity.devxplore.view.ui.authentication;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.doctoror.particlesdrawable.ParticlesDrawable;
import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.view.ui.base.BaseActivity;

/**
 * Created by gravity on 7/2/17.
 */

public class AuthFragment extends Fragment {

    private ParticlesDrawable mDrawable;

    @NonNull
    public static AuthFragment newInstance() {
        return new AuthFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mDrawable = (ParticlesDrawable) ContextCompat
                    .getDrawable(getActivity(), R.drawable.particles);
        } else {
            mDrawable = new ParticlesDrawable();
        }
        FrameLayout authScreen = (FrameLayout) view.findViewById(R.id.auth_background);
        authScreen.setBackground(mDrawable);

        TextView startText = (TextView) view.findViewById(R.id.start);
        startText.setOnClickListener(view1 -> startActivity(BaseActivity.createIntent(getActivity())));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mDrawable.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        mDrawable.stop();
    }
}
