package com.example.gravity.devxplore.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
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

import java.util.List;
import java.util.Random;

/**
 * Created by gravity on 7/4/17.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.DeveloperViewHolder> {

    private final Context mContext;
    private final List<User> mUsers;
    private final int mRowLayout;
    private final UserAdapterListener mListener;

    public UsersAdapter(Context mContext, List<User> mUsers, int mRowLayout, UserAdapterListener listener) {
        this.mContext = mContext;
        this.mUsers = mUsers;
        this.mRowLayout = mRowLayout;
        this.mListener = listener;
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
    public UsersAdapter.DeveloperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mRowLayout, parent, false);
        return new DeveloperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DeveloperViewHolder holder, final int position) {
        holder.mUserName.setText(mUsers.get(position).getLogin());
        Glide.with(mContext)
                .load(mUsers.get(position).getAvatarUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.color.colorAccent)
                .crossFade()
                .thumbnail(0.5f)
                .into(holder.mUserProfilePic);
        applyClickEvents(holder, position);
        applyFavourite(holder, position);
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
                changeFavourite(holder, position);
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

    private void changeFavourite(@NonNull DeveloperViewHolder holder, int position) {
        boolean favourite = mUsers.get(position).isFavourite();
        if (!favourite) {
            holder.mFavouriteIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_heart_selected));
            holder.mFavouriteIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.colorAccent));
        } else {
            holder.mFavouriteIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_heart_outline));
            holder.mFavouriteIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.icon_color_Light));
        }
    }

    private void applyFavourite(@NonNull DeveloperViewHolder holder, int position) {
        boolean favourite = mUsers.get(position).isFavourite();
        if (favourite) {
            holder.mFavouriteIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_heart_selected));
            holder.mFavouriteIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.colorAccent));
        } else {
            holder.mFavouriteIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_heart_outline));
            holder.mFavouriteIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.icon_color_Light));
        }
    }

    private int getRandomMaterialColor() {
        int[] colors = mContext.getResources().getIntArray(R.array.holderColors);
        int randomColor = colors[new Random().nextInt(colors.length)];
        return randomColor;
    }

    private int obtainColor() {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = mContext.getTheme();
        theme.resolveAttribute(R.attr.icon_color, typedValue, true);
        @ColorInt int iconColor = typedValue.data;
        return iconColor;
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public interface UserAdapterListener {
        void onCardClicked(int position);

        void onCardLongClicked(int position);

        void onCardFavouriteClicked(int position);
    }

}
