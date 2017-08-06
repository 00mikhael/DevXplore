package com.example.gravity.devxplore.screens.userdetail;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.data.model.UserDetails;
import com.example.gravity.devxplore.data.network.ApiClient;
import com.example.gravity.devxplore.data.network.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by gravity on 7/2/17.
 */

public class UserDetailFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener {

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;

    private LinearLayout titleContainer;
    private TextView title;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public static UserDetailFragment newInstance() {
        UserDetailFragment userDetailFragment = new UserDetailFragment();
        return userDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dev_details, container, false);

        toolbar = (Toolbar) view.findViewById(R.id.dev_details_toolbar);
        title = (TextView) view.findViewById(R.id.dev_details_collapsed_username);
        titleContainer = (LinearLayout) view.findViewById(R.id.dev_details_title_container);
        appBarLayout = (AppBarLayout) view.findViewById(R.id.dev_details_appbar);
        tabLayout = (TabLayout) view.findViewById(R.id.user_tab);

        final TextView username = (TextView) view.findViewById(R.id.dev_details_username);

        appBarLayout.addOnOffsetChangedListener(this);

        toolbar.inflateMenu(R.menu.main);
        startAlphaAnimation(title, 0, View.INVISIBLE);

        UserService userService = ApiClient.getClient().create(UserService.class);
        Call<UserDetails> userDetailsCall = userService.getUser("zirogravity");
        userDetailsCall.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                UserDetails userDetails = response.body();
                username.setText(userDetails.getLogin());
            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {

            }
        });

        return view;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if(!mIsTheTitleVisible) {
                startAlphaAnimation(title, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(title, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if(mIsTheTitleContainerVisible) {
                startAlphaAnimation(titleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(titleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    /*private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new UserEventsFragment(), "EVENTS");
        adapter.addFragment(new UserFollowersFragment(), "FOLLOWERS");
        adapter.addFragment(new UserFollowingFragment(), "FOLLOWING");
        adapter.addFragment(new UserReposFragment(), "REPOS");
        adapter.addFragment(new UserStarredFragment(), "STARRED");
        viewPager.setAdapter(adapter);
    }*/

    /*private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
            *//*return fragmentTitleList.get(position);*//*
        }

        private int mCurrentPosition = -1; // Keep track of the current position

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);

            if (!(container instanceof WrappingViewPager)) {
                return; // Do nothing if it's not a compatible ViewPager
            }

            if (position != mCurrentPosition) { // If the position has changed, tell WrappingViewPager
                Fragment fragment = (Fragment) object;
                WrappingViewPager pager = (WrappingViewPager) container;
                if (fragment != null && fragment.getView() != null) {
                    mCurrentPosition = position;
                    pager.onPageChanged(fragment.getView());
                }
            }
        }
    }*/
}

