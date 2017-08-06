package com.example.gravity.devxplore.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.data.model.Repository;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by gravity on 7/12/17.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder>{

    private Context mContext;
    private List<Repository> mRepositories;

    public RepoAdapter(Context mContext, List<Repository> mRepositories) {
        this.mContext = mContext;
        this.mRepositories = mRepositories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.card_pager_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(mRepositories.get(position).getOwner().getAvatarUrl())
                .into(holder.mRepoOwner);
        holder.mOwnerName.setText(mRepositories.get(position).getOwner().getLogin());
        holder.mRepoName.setText(mRepositories.get(position).getRepoName());
        holder.mRepoLanguage.setText(mRepositories.get(position).getLanguage());
        if (!mRepositories.get(position).getDescription().equals("")) {
            holder.mRepoDescription.setText(mRepositories.get(position).getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return mRepositories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        private CircleImageView mRepoOwner;
        private TextView mRepoName, mRepoDescription, mRepoLanguage, mOwnerName;

        public ViewHolder(View itemView) {
            super(itemView);
            mRepoOwner = (CircleImageView) itemView.findViewById(R.id.repo_owner);
            mRepoName = (TextView) itemView.findViewById(R.id.repo_name);
            mRepoDescription = (TextView) itemView.findViewById(R.id.repo_description);
            mRepoLanguage = (TextView) itemView.findViewById(R.id.repo_language);
            mOwnerName = (TextView) itemView.findViewById(R.id.owner_name);
        }
    }
}

