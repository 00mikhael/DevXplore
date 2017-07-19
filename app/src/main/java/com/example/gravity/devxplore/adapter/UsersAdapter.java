package com.example.gravity.devxplore.adapter;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.model.User;

import java.util.List;

/**
 * Created by gravity on 7/4/17.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.DeveloperViewHolder> {

    private Context context;
    private List<User> users;
    private int rowLayout;

    public static class DeveloperViewHolder extends RecyclerView.ViewHolder {
        private ImageView devProfileImage;
        private ImageView devContextMenu;
        private TextView devUsername;

        public DeveloperViewHolder(View view) {
            super(view);
            devProfileImage = view.findViewById(R.id.dev_profile_image);
            devUsername = view.findViewById(R.id.dev_username);
            devContextMenu = view.findViewById(R.id.dev_context_menu);
        }
    }

    public UsersAdapter(Context context, List<User> users, int rowLayout) {
        this.context = context;
        this.users = users;
        this.rowLayout = rowLayout;
    }

    @Override
    public UsersAdapter.DeveloperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new DeveloperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DeveloperViewHolder holder, final int position) {
        holder.devUsername.setText(users.get(position).getLogin());
        Glide.with(context)
                .load(users.get(position).getAvatarUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .thumbnail(0.5f)
                .into(holder.devProfileImage);
        holder.devContextMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showContextMenu(holder.devContextMenu);
            }
        });
    }

    private void showContextMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.context_menu, popupMenu.getMenu());
        popupMenu.show();

    }

    class DevContextMenuClickListener implements PopupMenu.OnMenuItemClickListener {
        public DevContextMenuClickListener() {
        }
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.context_action_open:
                    //TODO: oPEN POSITION
                    return true;
                case R.id.context_action_share:

                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        if (users.size() > 10) {
            return 10;
        }
        return users.size();
    }
}
