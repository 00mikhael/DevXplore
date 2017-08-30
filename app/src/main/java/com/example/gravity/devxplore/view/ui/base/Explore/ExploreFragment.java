package com.example.gravity.devxplore.view.ui.base.Explore;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.utilities.BasicUtil;
import com.example.gravity.devxplore.utilities.GridSpacingItemDecoration;
import com.example.gravity.devxplore.view.adapters.UsersAdapter;
import com.example.gravity.devxplore.view.ui.base.BaseViewModel;
import com.example.gravity.devxplore.view.ui.details.DetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gravity on 7/2/17.
 */

public class ExploreFragment extends Fragment implements LifecycleRegistryOwner,
        UsersAdapter.UserAdapterListener, View.OnClickListener {

    private static String TAG = "ExploreFragment";

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    private BaseViewModel mViewModel;
    private GridLayoutManager mLayoutManager;
    private UsersAdapter mAdapter;
    private EditText mLocationInput;
    private TextView mFindButton;
    private EditText mLanguageInput;
    private List<User> mUsers;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private ArrayList<String> mLanguages = new ArrayList<>();

    @NonNull
    public static ExploreFragment newInstance() {
        return new ExploreFragment();
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mLanguages.add("java");
        mViewModel = ViewModelProviders.of(this).get(BaseViewModel.class);
        mFindButton = (TextView) view.findViewById(R.id.find_button);
        mLocationInput = (EditText) view.findViewById(R.id.location_edit_text);
        mLanguageInput = (EditText) view.findViewById(R.id.language_edit_text);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_recycler_view);
        mProgressBar = (ProgressBar) view.findViewById(R.id.home_progress_bar);
        mProgressBar.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.refresh();
        mProgressBar.setVisibility(View.VISIBLE);
        setupView();
    }

    private void setupView() {
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(BasicUtil.dpToPx(getActivity())));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new UsersAdapter(getActivity(), R.layout.list_item_user_grid, this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mFindButton.setOnClickListener(this);
        mViewModel.getSearchResult().observe(this, users -> {
            mProgressBar.setVisibility(View.GONE);
            ExploreFragment.this.mUsers = users;
            mAdapter.setList(users);
        });
    }

    @Override
    public void onCardClicked(int position, View v) {
        Toast.makeText(getActivity(), "Card Clicked", Toast.LENGTH_SHORT).show();
        User user = mUsers.get(position);
        String username = user.getLogin();
        Intent intent = DetailsActivity.createIntent(getActivity(), username);
        startActivity(intent);
    }

    @Override
    public void onCardLongClicked(int position) {

    }

    @Override
    public void onCardFavouriteClicked(int position) {
        Toast.makeText(getActivity(), "Favourite Clicked", Toast.LENGTH_SHORT).show();
        User user = mUsers.get(position);
        String username = user.getLogin();
        boolean favourite = user.isFavourite();
        favourite = !favourite;
        /*mViewModel.setFavourite(username, favourite);*/
    }

    private void hideSoftInputPanel(View v) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    private void showSoftInputPanel(View view) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }
    }

    private void search(String query) {
        mViewModel.setSearchQuery(query);
    }

    @Override
    public void onClick(View view) {
        int clicked = view.getId();
        switch (clicked) {
            case R.id.find_button:
                hideSoftInputPanel(mLocationInput);
                hideSoftInputPanel(mLanguageInput);

                String location = mLocationInput.getText().toString().toLowerCase().trim();
                String language = mLanguageInput.getText().toString().toLowerCase().trim();
                Log.e(TAG, location+" "+language);

                String query = "location:" + location + "+language:" + language;
                search(query);
                Toast.makeText(getActivity(), query, Toast.LENGTH_SHORT).show();
                break;
            default:
        }
    }

}
