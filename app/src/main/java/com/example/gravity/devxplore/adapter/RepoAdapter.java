package com.example.gravity.devxplore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.model.Repository;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by gravity on 7/12/17.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder>{

    private Context context;
    private List<Repository> repositories;

    public RepoAdapter(Context context, List<Repository> repositories) {
        this.context = context;
        this.repositories = repositories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_item_repo, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context)
                .load(repositories.get(position).getOwner().getAvatarUrl())
                .into(holder.repoOwner);
        holder.ownerName.setText(repositories.get(position).getOwner().getLogin());
        holder.repoName.setText(repositories.get(position).getRepoName());
        holder.repoLanguage.setText(repositories.get(position).getLanguage());
        if (!repositories.get(position).getDescription().equals("")) {
            holder.repoDescription.setText(repositories.get(position).getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        private CircleImageView repoOwner;
        private TextView repoName, repoDescription, repoLanguage, ownerName;

        public ViewHolder(View itemView) {
            super(itemView);
            repoOwner = itemView.findViewById(R.id.repo_owner);
            repoName = itemView.findViewById(R.id.repo_name);
            repoDescription = itemView.findViewById(R.id.repo_description);
            repoLanguage = itemView.findViewById(R.id.repo_language);
            ownerName = itemView.findViewById(R.id.owner_name);
        }
    }
}

