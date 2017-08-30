package com.example.gravity.devxplore.view.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.data.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gravity on 8/20/17.
 */

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.DeveloperViewHolder> {
    private  Context mContext;
    private List<User> mFavourites;
    private  int mRowLayout;
    private  FavouriteAdapterListener mListener;

    public FavouriteAdapter(Context mContext, int mRowLayout,
                            FavouriteAdapterListener listener, RecyclerView recyclerView) {
        this.mContext = mContext;
        this.mFavourites = new ArrayList<>();
        this.mRowLayout = mRowLayout;
        this.mListener = listener;
    }

    public void setList(List<User> favouritesList) {
        mFavourites.addAll(favouritesList);
        notifyDataSetChanged();
    }

    private void clearList() {
        mFavourites.clear();
        notifyDataSetChanged();
    }

    public class DeveloperViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        private final ImageView mUserProfilePic;
        private final TextView mUserName;
        private final ImageView mFavouriteIcon;
        private final LinearLayout mContainer;

        public DeveloperViewHolder(@NonNull View view) {
            super(view);
            mUserProfilePic = (ImageView) view.findViewById(R.id.user_profile_image);
            mUserName = (TextView) view.findViewById(R.id.username);
            mFavouriteIcon = (ImageView) view.findViewById(R.id.user_favourite);
            mContainer = (LinearLayout) view.findViewById(R.id.user_container);
            view.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(@NonNull View v) {
            mListener.onCardLongClicked(getAdapterPosition());
            v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            return true;
        }

    }

    @NonNull
    @Override
    public FavouriteAdapter.DeveloperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mRowLayout, parent, false);
        return new DeveloperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DeveloperViewHolder holder, final int position) {
        if (mFavourites != null) {
            holder.mUserName.setText(mFavourites.get(position).getLogin());
            Glide.with(mContext)
                    .load(mFavourites.get(position).getAvatarUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(getRandomMaterialColor())
                    .crossFade()
                    .thumbnail(0.5f)
                    .into(holder.mUserProfilePic);
            applyClickEvents(holder, position);
            applyFavourite(holder);
        }
    }


    private void applyClickEvents(@NonNull final DeveloperViewHolder holder, final int position) {

        holder.mContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(@NonNull View v) {
                mListener.onCardLongClicked(position);
                v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                return true;
            }
        });

        holder.mFavouriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFavourite(position);
                mListener.onCardFavouriteClicked(position);
            }
        });
        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCardClicked(position);
            }
        });
    }

    private void applyFavourite(@NonNull DeveloperViewHolder holder) {
        holder.mFavouriteIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_heart_selected));
        holder.mFavouriteIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.red));
    }


    private void changeFavourite(int position) {
        mFavourites.remove(position);
        notifyDataSetChanged();
    }

    private int getRandomMaterialColor() {
        int returnColor = Color.GRAY;
        int arrayId = mContext.getResources().getIdentifier("mdcolor", "array", mContext.getPackageName());

        if (arrayId != 0) {
            TypedArray colors = mContext.getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.GRAY);
            colors.recycle();
        }
        return returnColor;
    }

    @Override
    public int getItemCount() {
        return mFavourites.size();
    }

    public interface FavouriteAdapterListener {
        void onCardClicked(int position);

        void onCardLongClicked(int position);

        void onCardFavouriteClicked(int position);
    }

}
