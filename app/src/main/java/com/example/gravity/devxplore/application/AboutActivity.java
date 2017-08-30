package com.example.gravity.devxplore.application;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.utilities.ThemeUtil;

import mehdi.sakout.aboutpage.AboutPage;

/**
 * Created by gravity on 8/20/17.
 */

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtil.onActivityCreateSetTheme(this);
        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.background)
                .addGroup("Connect with us")
                .addEmail("mikalil525@gmail.com")
                .addWebsite("http://zirogravity.github.io/")
                .addFacebook("M.C.Nwankwo")
                .addTwitter("gravi__ty")
                .addYoutube("mikalil")
                .addGitHub("zirogravity")
                .create();
        setContentView(aboutPage);
    }
}
