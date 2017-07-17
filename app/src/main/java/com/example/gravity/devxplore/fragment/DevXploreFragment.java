package com.example.gravity.devxplore.fragment;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.adapter.DevelopersAdapter;
import com.example.gravity.devxplore.adapter.RepoAdapter;
import com.example.gravity.devxplore.model.Developer;
import com.example.gravity.devxplore.model.DevelopersResponse;
import com.example.gravity.devxplore.model.RepositoriesResponse;
import com.example.gravity.devxplore.model.Repository;
import com.example.gravity.devxplore.network.ApiClient;
import com.example.gravity.devxplore.network.ApiInterface;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gravity on 7/2/17.
 */

public class DevXploreFragment extends Fragment {


    CollapsingToolbarLayout collapsingToolbar;
    AppBarLayout homeAppBar;
    Toolbar toolbar;

    public static DevXploreFragment newInstance() {
        return new DevXploreFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dev_xplore, container, false);
        collapsingToolbar =  view.findViewById(R.id.home_collapsing_toolbar);

        /*getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);*/


        toolbar = view.findViewById(R.id.home_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("");


        homeAppBar = view.findViewById(R.id.home_app_bar);

        initCollapsingToolbar();


        final RecyclerView recyclerView = view.findViewById(R.id.home_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        final TabLayout homeTabLayout = view.findViewById(R.id.home_tab_layout);

        final RecyclerViewPager recyclerViewPager = (RecyclerViewPager) view.findViewById(R.id.recycler_viewpager);

        LinearLayoutManager layout = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPager.setLayoutManager(layout);


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        /*Map<String, String> data = new HashMap<>();
        data.put("location", "lagos");
        data.put("language", "java");*/
        Call<DevelopersResponse> developersResponseCall = apiService.getDevelopers("location:umuahia+location:abia+location:aba+language:java");
        developersResponseCall.enqueue(new Callback<DevelopersResponse>() {
            @Override
            public void onResponse(@NonNull Call<DevelopersResponse> call, @NonNull Response<DevelopersResponse> response) {
                List<Developer> developers = response.body().getDevItems();
                recyclerView.setAdapter(new DevelopersAdapter(getActivity(), developers, R.layout.list_item_dev));
            }

            @Override
            public void onFailure(@NonNull Call<DevelopersResponse> call,@NonNull Throwable t) {

            }
        });

        Call<RepositoriesResponse> repositoriesResponseCall = apiService.getRepositories();
        repositoriesResponseCall.enqueue(new Callback<RepositoriesResponse>() {
            @Override
            public void onResponse( @NonNull Call<RepositoriesResponse> call,@NonNull Response<RepositoriesResponse> response) {
                List<Repository> repositories = response.body().getRepoItems();
                RepoAdapter repoAdapter = new RepoAdapter(getActivity(), repositories);
                recyclerViewPager.setAdapter(repoAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<RepositoriesResponse> call,@NonNull Throwable t) {

            }
        });

        return view;
    }

    private void initCollapsingToolbar() {
        homeAppBar.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        homeAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle("");
                    isShow = false;
                }
            }
        });
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
