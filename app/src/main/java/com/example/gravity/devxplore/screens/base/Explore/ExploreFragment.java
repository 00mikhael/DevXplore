package com.example.gravity.devxplore.screens.base.Explore;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gravity.devxplore.Injection;
import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.adapters.UsersAdapter;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.utilities.GridSpacingItemDecoration;
import com.example.gravity.devxplore.utilities.Util;

import java.util.List;

/**
 * Created by gravity on 7/2/17.
 */

public class ExploreFragment extends Fragment implements ExploreContract.XploreView, View.OnClickListener, UsersAdapter.UserAdapterListener {

    @NonNull
    public static String TAG = "ExploreFragment";

    private ExplorePresenter mExplorePresenter;
    private ExploreContract.Presenter mPresenter;
    private EditText locationEditText;
    private List<User> mUsers;
    private RecyclerView mRecyclerView;
    InputMethodManager imm;

    @NonNull
    public static ExploreFragment newInstance() {
        return new ExploreFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.setLocation("lagos");
        mPresenter.setLanguage("java");
        mPresenter.start();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        TextView findtext = (TextView) view.findViewById(R.id.find_button);
        findtext.setOnClickListener(this);
        locationEditText = (EditText) view.findViewById(R.id.location_edit_text);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_recycler_view);
        mExplorePresenter = new ExplorePresenter(Injection.provideDataManager(getActivity().getApplicationContext()), this);
        setupView();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void setupView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(Util.dpToPx(getActivity())));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void setPresenter(@NonNull ExploreContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoadingIndicator(boolean isLoading) {

    }

    @Override
    public void showUsers(List<User> users) {
        this.mUsers = users;
        UsersAdapter adapter = new UsersAdapter(getActivity(), users, R.layout.list_item_user_grid, this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onCardClicked(int position) {
        Toast.makeText(getActivity(), "Card Clicked", Toast.LENGTH_SHORT).show();
        User user = mUsers.get(position);
        String username = user.getLogin();
        mPresenter.openUserDetails(getActivity(), username);
    }

    @Override
    public void onCardLongClicked(int position) {

    }

    @Override
    public void onCardFavouriteClicked(int position) {
        Toast.makeText(getActivity(), "Favourite Clicked", Toast.LENGTH_SHORT).show();
        User user = mUsers.get(position);
        boolean favourite = user.isFavourite();
        favourite = !favourite;
        mPresenter.updateFavourite(user, favourite);
    }

    @Override
    public void onClick(@NonNull View v) {
        int clickedView = v.getId();
        switch (clickedView) {
            case R.id.find_button:
                imm.hideSoftInputFromWindow(locationEditText.getWindowToken(), 0);
                String mLocation = locationEditText.getText().toString();
                String mLanguage = "java";
                mPresenter.setLocation(mLocation);
                mPresenter.setLanguage(mLanguage);
                mPresenter.start();
                break;
            default:
                break;

        }

    }
}
