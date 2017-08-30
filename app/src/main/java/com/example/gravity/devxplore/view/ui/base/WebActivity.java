package com.example.gravity.devxplore.view.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.gravity.devxplore.utilities.ThemeUtil;

/**
 * Created by gravity on 8/28/17.
 */

public class WebActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtil.onActivityCreateSetTheme(this);
    }
}
