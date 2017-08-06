package com.example.gravity.devxplore.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.data.model.User;

import java.util.List;

/**
 * Created by gravity on 7/4/17.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.DeveloperViewHolder> {

    private Context mContext;
    private List<User> mUsers;
    private int mRowLayout;

    public UsersAdapter(Context mContext, List<User> mUsers, int mRowLayout) {
        this.mContext = mContext;
        this.mUsers = mUsers;
        this.mRowLayout = mRowLayout;
    }

    public static class DeveloperViewHolder extends RecyclerView.ViewHolder {
        private ImageView mUserProfilePic;
        private TextView mUserName;

        public DeveloperViewHolder(View view) {
            super(view);
            mUserProfilePic = (ImageView) view.findViewById(R.id.user_profile_image);
            mUserName = (TextView) view.findViewById(R.id.username);
        }
    }

    @Override
    public UsersAdapter.DeveloperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mRowLayout, parent, false);
        return new DeveloperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DeveloperViewHolder holder, final int position) {
        holder.mUserName.setText(mUsers.get(position).getLogin());
        Glide.with(mContext)
                .load(mUsers.get(position).getAvatarUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .thumbnail(0.5f)
                .into(holder.mUserProfilePic);
        /*holder.mUserContextMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showContextMenu(holder.mUserContextMenu);
            }
        });*/
    }

    /*private void showContextMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(mContext, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.context_menu, popupMenu.getMenu());
        popupMenu.show();

    }*/

    @Override
    public int getItemCount() {
        if (mUsers.size() > 10) {
            return 10;
        }
        return mUsers.size();
    }
}
