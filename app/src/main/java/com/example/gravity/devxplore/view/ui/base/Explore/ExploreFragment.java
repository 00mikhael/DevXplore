package com.example.gravity.devxplore.view.ui.base.Explore;

import android.app.ActivityOptions;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.data.model.User;
import com.example.gravity.devxplore.utilities.BasicUtil;
import com.example.gravity.devxplore.utilities.GridSpacingItemDecoration;
import com.example.gravity.devxplore.view.adapters.CustomAdapter;
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

    private String mLocation;
    private String mLanguage;

    private BaseViewModel mViewModel;
    private GridLayoutManager mLayoutManager;
    private UsersAdapter mAdapter;
    private CustomAdapter mSpinnerAdapter;
    private EditText mLocationInput;
    private TextView mFindButton;
    private Spinner mSpinner;
    private EditText mLanguageInput;
    private List<User> mUsers;
    private RecyclerView mRecyclerView;
    private ArrayList<String> mLanguages = new ArrayList<>();

    @NonNull
    public static ExploreFragment newInstance() {
        return new ExploreFragment();
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }


    private void setLocation(String location) {
        this.mLocation = mLocation;
    }

    private void setLanguage(String language) {
        this.mLanguage = mLanguage;
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
        mSpinner = (Spinner) view.findViewById(R.id.language_spinner);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_recycler_view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.refresh();
        setupView();
    }

    private void setupView() {
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(BasicUtil.dpToPx(getActivity())));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new UsersAdapter(getActivity(), R.layout.list_item_user_grid, this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mSpinnerAdapter = new CustomAdapter(getActivity(), mLanguages);
        mSpinner.setAdapter(mSpinnerAdapter);
        mFindButton.setOnClickListener(this);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i) != null) {
                    String language = adapterView.getItemAtPosition(i).toString();
                    if (language != null) {
                        setLanguage(language);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mViewModel.getSearchResult().observe(this, users -> {
            this.mUsers = users;
            mAdapter.setList(users);
        });
    }

    private void showLoadingIndicator(boolean isLoading) {

    }

    @Override
    public void onCardClicked(int position, View v) {
        Toast.makeText(getActivity(), "Card Clicked", Toast.LENGTH_SHORT).show();
        User user = mUsers.get(position);
        String username = user.getLogin();
        Intent intent = DetailsActivity.createIntent(getActivity(), username);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String transitionName = getString(R.string.transition);
            ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(getActivity(), v, transitionName);
            startActivity(intent, transitionActivityOptions.toBundle());
        }else {
            startActivity(intent);
        }
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
        mViewModel.setFavourite(username, favourite);
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

                mSpinnerAdapter.addLanguage(language);
                String query = "location:" + location + "+language:" + language;
                search(query);
                Toast.makeText(getActivity(), query, Toast.LENGTH_SHORT).show();
                break;
            default:
        }
    }

}
