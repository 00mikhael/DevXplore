package com.example.gravity.devxplore.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.data.model.Repository;

import java.util.List;

/**
 * Created by gravity on 7/4/17.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {

    private final Context mContext;
    private final List<Repository> mRepositories;
    private final int mRowLayout;
    private final RepoAdapterListener mListener;

    public RepoAdapter(Context context, List<Repository> repositories, RepoAdapterListener listener) {
        this.mContext = context;
        this.mRepositories = repositories;
        this.mRowLayout = R.layout.list_item_repo;
        this.mListener = listener;
    }

    public class RepoViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        private final TextView mRepoName;
        private final TextView mRepoLanguge;
        private final TextView mRepoDescription;
        private final TextView mRepoStarCount;
        private final TextView mRepoWatchCount;
        private final TextView mRepoForkCount;
        private final View mRepoColor;
        private final CardView mCardView;

        public RepoViewHolder(@NonNull View view) {
            super(view);
            mRepoName = (TextView) view.findViewById(R.id.repo_name);
            mRepoLanguge = (TextView) view.findViewById(R.id.repo_language);
            mRepoDescription = (TextView) view.findViewById(R.id.repo_description);
            mRepoStarCount = (TextView) view.findViewById(R.id.repo_star_count);
            mRepoWatchCount = (TextView) view.findViewById(R.id.repo_watch_count);
            mRepoForkCount  = (TextView)  view.findViewById(R.id.repo_fork_count);
            mRepoColor = view.findViewById(R.id.repo_color);
            mCardView = (CardView) view.findViewById(R.id.repo_card);
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
    public RepoAdapter.RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mRowLayout, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RepoViewHolder holder, final int position) {
        holder.mRepoName.setText(mRepositories.get(position).getRepoFullName());
        holder.mRepoLanguge.setText(mRepositories.get(position).getLanguage());
        if (mRepositories.get(position).getDescription() == null) {
            holder.mRepoDescription.setTypeface(holder.mRepoDescription.getTypeface(), Typeface.ITALIC);
            holder.mRepoDescription.setText("no description");
        }else {
            holder.mRepoDescription.setText(mRepositories.get(position).getDescription());
        }
        holder.mRepoStarCount.setText(""+mRepositories.get(position).getRepoStars()+"");
        holder.mRepoWatchCount.setText(""+mRepositories.get(position).getRepoWatchers()+"");
        holder.mRepoForkCount.setText(""+mRepositories.get(position).getRepoForks()+"");

        applyClickEvents(holder, position);
    }

    private void applyClickEvents(@NonNull final RepoViewHolder holder, final int position) {
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onCardClicked(position);
            }
        });

        holder.mCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(@NonNull View v) {
                mListener.onCardLongClicked(position);
                v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRepositories.size();
    }

    public interface RepoAdapterListener {
        void onCardClicked(int position);

        void onCardLongClicked(int position);

    }
}
