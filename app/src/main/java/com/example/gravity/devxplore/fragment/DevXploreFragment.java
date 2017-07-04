package com.example.gravity.devxplore.fragment;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.adapter.DevelopersAdapter;
import com.example.gravity.devxplore.model.Developer;
import com.example.gravity.devxplore.model.DevelopersResponse;
import com.example.gravity.devxplore.network.ApiClient;
import com.example.gravity.devxplore.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gravity on 7/2/17.
 */

public class DevXploreFragment extends Fragment {

    public static DevXploreFragment newInstance() {
        DevXploreFragment devXploreFragment = new DevXploreFragment();
        return devXploreFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dev_xplore, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.home_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        initCollapsingToolbar();

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.home_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<DevelopersResponse> developersResponseCall = apiService.getDevelopers("lagos","java");
        developersResponseCall.enqueue(new Callback<DevelopersResponse>() {
            @Override
            public void onResponse(Call<DevelopersResponse> call, Response<DevelopersResponse> response) {
                List<Developer> developers = response.body().getItems();
                recyclerView.setAdapter(new DevelopersAdapter(getContext(), developers, R.layout.list_item_dev));
            }

            @Override
            public void onFailure(Call<DevelopersResponse> call, Throwable t) {

            }
        });

        return view;
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) getView().findViewById(R.id.home_collapsing_toolbar);
        collapsingToolbar.setTitle("");
        AppBarLayout homeAppBar = (AppBarLayout) getView().findViewById(R.id.home_app_bar);
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
