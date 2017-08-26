package com.example.gravity.devxplore.view.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gravity.devxplore.R;
import com.example.gravity.devxplore.data.model.UserEvents;

import java.util.List;

/**
 * Created by gravity on 7/4/17.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private final List<UserEvents> mEvents;
    private final int mRowLayout;

    public EventAdapter(Context context, List<UserEvents> events) {
        Context mContext = context;
        this.mEvents = events;
        this.mRowLayout = R.layout.list_item_event;
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        private final TextView mEvent;
        private final TextView mEventTime;
        private final ImageView mEventImage;

        public EventViewHolder(@NonNull View view) {
            super(view);
            mEvent = (TextView) view.findViewById(R.id.event);
            mEventTime = (TextView) view.findViewById(R.id.event_time);
            mEventImage = (ImageView) view.findViewById(R.id.event_image);
        }
    }

    @NonNull
    @Override
    public EventAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mRowLayout, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EventViewHolder holder, final int position) {
        int eventType = getItemViewType(position);
        String eventText;

        SpannableString userEvent;

        String actor, newMember, action, tag, repo, forkee;
        int issueNumber;

        switch (eventType) {
            case 1: // CreateEvent
                if (mEvents.get(position).getEventPayload().getEventRefType().equals("repository")) {

                    actor = mEvents.get(position).getEventActor().getActorLogin();
                    action = "created repo";
                    repo = mEvents.get(position).getEventRepo().getEventRepoName();

                    eventText = actor+" "+action+" "+repo;

                    userEvent = new SpannableString(eventText);

                    userEvent.setSpan(new StyleSpan(Typeface.BOLD), actor.length()+1, action.length()+(actor.length()+1), 0);

                    holder.mEvent.setText(userEvent);
                    holder.mEvent.setMovementMethod(LinkMovementMethod.getInstance());
                    holder.mEvent.setLinkTextColor(Color.TRANSPARENT);
                    holder.mEventImage.setImageResource(R.drawable.ic_repo);

                }else if (mEvents.get(position).getEventPayload().getEventRefType().equals("tag")) {

                    actor = mEvents.get(position).getEventActor().getActorLogin();
                    action = "created tag";
                    tag = mEvents.get(position).getEventPayload().getEventRef();
                    repo = mEvents.get(position).getEventRepo().getEventRepoName();

                    eventText = actor+" "+action+" "+tag+" at "+repo;

                    userEvent = new SpannableString(eventText);

                    userEvent.setSpan(new StyleSpan(Typeface.BOLD), actor.length()+1, action.length()+(actor.length()+1), 0);

                    holder.mEvent.setText(userEvent);
                    holder.mEvent.setMovementMethod(LinkMovementMethod.getInstance());
                    holder.mEvent.setLinkTextColor(Color.TRANSPARENT);
                    holder.mEventImage.setImageResource(R.drawable.ic_repo);

                }else if (mEvents.get(position).getEventPayload().getEventRefType().equals("branch")) {

                    actor = mEvents.get(position).getEventActor().getActorLogin();
                    action = "created branch";
                    tag = mEvents.get(position).getEventPayload().getEventRef();
                    repo = mEvents.get(position).getEventRepo().getEventRepoName();

                    eventText = actor+" "+action+" "+tag+" at "+repo;

                    userEvent = new SpannableString(eventText);

                    userEvent.setSpan(new StyleSpan(Typeface.BOLD), actor.length()+1, action.length()+(actor.length()+1), 0);

                    holder.mEvent.setText(userEvent);
                    holder.mEvent.setMovementMethod(LinkMovementMethod.getInstance());
                    holder.mEvent.setLinkTextColor(Color.TRANSPARENT);
                    holder.mEventImage.setImageResource(R.drawable.ic_fork_normal);

                }
                break;
            case 2: // WatchEvent

                actor = mEvents.get(position).getEventActor().getActorLogin();
                action = "starred";
                repo = mEvents.get(position).getEventRepo().getEventRepoName();

                eventText = actor+" "+action+" "+repo;

                userEvent = new SpannableString(eventText);

                userEvent.setSpan(new StyleSpan(Typeface.BOLD), actor.length()+1, action.length()+(actor.length()+1), 0);

                holder.mEvent.setText(userEvent);
                holder.mEvent.setMovementMethod(LinkMovementMethod.getInstance());
                holder.mEvent.setLinkTextColor(Color.TRANSPARENT);
                holder.mEventImage.setImageResource(R.drawable.ic_star_selected);

                break;
            case 3: // PushEvent

                actor = mEvents.get(position).getEventActor().getActorLogin();
                action = "Pushed to";
                repo = mEvents.get(position).getEventRepo().getEventRepoName();

                eventText = actor+" "+action+" "+repo;

                userEvent = new SpannableString(eventText);

                userEvent.setSpan(new StyleSpan(Typeface.BOLD), actor.length()+1, action.length()+(actor.length()+1), 0);

                holder.mEvent.setText(userEvent);
                holder.mEvent.setMovementMethod(LinkMovementMethod.getInstance());
                holder.mEvent.setLinkTextColor(Color.TRANSPARENT);
                holder.mEventImage.setImageResource(R.drawable.ic_repo);

                break;
            case 4: // ForkEvent

                actor = mEvents.get(position).getEventActor().getActorLogin();
                action = "Forked";
                repo = mEvents.get(position).getEventRepo().getEventRepoName();
                forkee = mEvents.get(position).getEventPayload().getForkedToRepo().getForkedRepoName();

                eventText = actor+" "+action+" "+repo+" to "+forkee;

                userEvent = new SpannableString(eventText);

                userEvent.setSpan(new StyleSpan(Typeface.BOLD), actor.length()+1, action.length()+(actor.length()+1), 0);

                holder.mEvent.setText(userEvent);
                holder.mEvent.setMovementMethod(LinkMovementMethod.getInstance());
                holder.mEvent.setLinkTextColor(Color.TRANSPARENT);
                holder.mEventImage.setImageResource(R.drawable.ic_repo);

                break;
            case 5: // IssuesEvent

                if (mEvents.get(position).getEventPayload().getEventAction().equals("opened")) {

                    actor = mEvents.get(position).getEventActor().getActorLogin();
                    action = "opened issue";
                    repo = mEvents.get(position).getEventRepo().getEventRepoName();
                    issueNumber = mEvents.get(position).getEventPayload().getEventIssue().getIssueNumber();

                    eventText = actor+" "+action+" "+repo+"/"+issueNumber;

                    userEvent = new SpannableString(eventText);

                    userEvent.setSpan(new StyleSpan(Typeface.BOLD), actor.length()+1, action.length()+(actor.length()+1), 0);

                    holder.mEvent.setText(userEvent);
                    holder.mEvent.setMovementMethod(LinkMovementMethod.getInstance());
                    holder.mEvent.setLinkTextColor(Color.TRANSPARENT);
                    holder.mEventImage.setImageResource(R.drawable.ic_repo);

                }else if (mEvents.get(position).getEventPayload().getEventAction().equals("closed")) {
                    actor = mEvents.get(position).getEventActor().getActorLogin();
                    action = "closed issue";
                    repo = mEvents.get(position).getEventRepo().getEventRepoName();
                    issueNumber = mEvents.get(position).getEventPayload().getEventIssue().getIssueNumber();

                    eventText = actor+" "+action+" "+repo+"/"+issueNumber;

                    userEvent = new SpannableString(eventText);

                    userEvent.setSpan(new StyleSpan(Typeface.BOLD), actor.length()+1, action.length()+(actor.length()+1), 0);

                    holder.mEvent.setText(userEvent);
                    holder.mEvent.setMovementMethod(LinkMovementMethod.getInstance());
                    holder.mEvent.setLinkTextColor(Color.TRANSPARENT);
                    holder.mEventImage.setImageResource(R.drawable.ic_repo);
                }

                break;
            case 6: // PullRequestReviewCommentEvent & IssueCommentEvent

                actor = mEvents.get(position).getEventActor().getActorLogin();
                action = "commented on";
                repo = mEvents.get(position).getEventRepo().getEventRepoName();
                issueNumber = mEvents.get(position).getEventPayload().getEventIssue().getIssueNumber();

                eventText = actor+" "+action+" "+repo+"/"+issueNumber;

                userEvent = new SpannableString(eventText);

                userEvent.setSpan(new StyleSpan(Typeface.BOLD), actor.length()+1, action.length()+(actor.length()+1), 0);

                holder.mEvent.setText(userEvent);
                holder.mEvent.setMovementMethod(LinkMovementMethod.getInstance());
                holder.mEvent.setLinkTextColor(Color.TRANSPARENT);
                holder.mEventImage.setImageResource(R.drawable.ic_repo);

                break;
            case 7: // MemberEvent

                actor = mEvents.get(position).getEventActor().getActorLogin();
                action = "added";
                repo = mEvents.get(position).getEventRepo().getEventRepoName();
                newMember = mEvents.get(position).getEventPayload().getAddedMember().getMemberLogin();

                eventText = actor+" "+action+" "+newMember+" to "+repo;

                userEvent = new SpannableString(eventText);

                userEvent.setSpan(new StyleSpan(Typeface.BOLD), actor.length()+1, action.length()+(actor.length()+1), 0);

                holder.mEvent.setText(userEvent);
                holder.mEvent.setMovementMethod(LinkMovementMethod.getInstance());
                holder.mEvent.setLinkTextColor(Color.TRANSPARENT);
                holder.mEventImage.setImageResource(R.drawable.ic_repo);

                break;
            case 8: // PullRequestEvent

                if (mEvents.get(position).getEventPayload().getEventAction().equals("closed") &&
                        mEvents.get(position).getEventPayload().getEventPullRequest().isMerged()) {

                    actor = mEvents.get(position).getEventActor().getActorLogin();
                    action = "merged pull request";
                    repo = mEvents.get(position).getEventRepo().getEventRepoName();
                    issueNumber = mEvents.get(position).getEventPayload().getEventIssue().getIssueNumber();

                    eventText = actor+" "+action+" "+repo+"/"+issueNumber;

                    userEvent = new SpannableString(eventText);

                    userEvent.setSpan(new StyleSpan(Typeface.BOLD), actor.length()+1, action.length()+(actor.length()+1), 0);

                    holder.mEvent.setText(userEvent);
                    holder.mEvent.setMovementMethod(LinkMovementMethod.getInstance());
                    holder.mEvent.setLinkTextColor(Color.TRANSPARENT);
                    holder.mEventImage.setImageResource(R.drawable.ic_repo);

                }else if (mEvents.get(position).getEventPayload().getEventAction().equals("closed") &&
                        !mEvents.get(position).getEventPayload().getEventPullRequest().isMerged()) {

                    actor = mEvents.get(position).getEventActor().getActorLogin();
                    action = "closed pull request";
                    repo = mEvents.get(position).getEventRepo().getEventRepoName();
                    issueNumber = mEvents.get(position).getEventPayload().getEventIssue().getIssueNumber();

                    eventText = actor+" "+action+" "+repo+"/"+issueNumber;

                    userEvent = new SpannableString(eventText);

                    userEvent.setSpan(new StyleSpan(Typeface.BOLD), actor.length()+1, action.length()+(actor.length()+1), 0);

                    holder.mEvent.setText(userEvent);
                    holder.mEvent.setMovementMethod(LinkMovementMethod.getInstance());
                    holder.mEvent.setLinkTextColor(Color.TRANSPARENT);
                    holder.mEventImage.setImageResource(R.drawable.ic_repo);

                }else if (mEvents.get(position).getEventPayload().getEventAction().equals("opened")) {

                    actor = mEvents.get(position).getEventActor().getActorLogin();
                    action = "opened pull request";
                    repo = mEvents.get(position).getEventRepo().getEventRepoName();
                    issueNumber = mEvents.get(position).getEventPayload().getEventIssue().getIssueNumber();

                    eventText = actor+" "+action+" "+repo+"/"+issueNumber;

                    userEvent = new SpannableString(eventText);

                    userEvent.setSpan(new StyleSpan(Typeface.BOLD), actor.length()+1, action.length()+(actor.length()+1), 0);

                    holder.mEvent.setText(userEvent);
                    holder.mEvent.setMovementMethod(LinkMovementMethod.getInstance());
                    holder.mEvent.setLinkTextColor(Color.TRANSPARENT);
                    holder.mEventImage.setImageResource(R.drawable.ic_repo);

                }

                break;
            case 9: // ReleaseEvent

                    actor = mEvents.get(position).getEventActor().getActorLogin();
                    action = "released";
                    repo = mEvents.get(position).getEventRepo().getEventRepoName();
                    tag = mEvents.get(position).getEventPayload().getEventRef();

                    eventText = actor+" "+action+" "+tag+" at "+repo;

                    userEvent = new SpannableString(eventText);

                    userEvent.setSpan(new StyleSpan(Typeface.BOLD), actor.length()+1, action.length()+(actor.length()+1), 0);

                    holder.mEvent.setText(userEvent);
                    holder.mEvent.setMovementMethod(LinkMovementMethod.getInstance());
                    holder.mEvent.setLinkTextColor(Color.TRANSPARENT);
                    holder.mEventImage.setImageResource(R.drawable.ic_repo);

                break;
            case 10: // PublicEvent

                actor = mEvents.get(position).getEventActor().getActorLogin();
                action = "made";
                repo = mEvents.get(position).getEventRepo().getEventRepoName();

                eventText = actor+" "+action+" "+repo+" public ";

                userEvent = new SpannableString(eventText);

                userEvent.setSpan(new StyleSpan(Typeface.BOLD), actor.length()+1, action.length()+(actor.length()+1), 0);

                holder.mEvent.setText(userEvent);
                holder.mEvent.setMovementMethod(LinkMovementMethod.getInstance());
                holder.mEvent.setLinkTextColor(Color.TRANSPARENT);
                holder.mEventImage.setImageResource(R.drawable.ic_repo);

                break;
            case -1: // UncaughtEventType

                action = "Uncaught Type";

                holder.mEvent.setText(action);
                holder.mEvent.setMovementMethod(LinkMovementMethod.getInstance());
                holder.mEvent.setLinkTextColor(Color.TRANSPARENT);
                holder.mEventImage.setImageResource(R.drawable.ic_repo);

                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        String eventType = mEvents.get(position).getEventType();
        int type = -1;

        switch (eventType) {
            case "CreateEvent":
                type = 1;
                break;
            case "WatchEvent":
                type = 2;
                break;
            case "PushEvent":
                type = 3;
                break;
            case "ForkEvent":
                type = 4;
                break;
            case "IssuesEvent":
                type = 5;
                break;
            case "PullRequestReviewCommentEvent":
                type = 6;
                break;
            case "IssueCommentEvent":
                type = 6;
                break;
            case "MemberEvent":
                type = 7;
                break;
            case "PullRequestEvent":
                type = 8;
                break;
            case "ReleaseEvent":
                type = 9;
                break;
            case "PublicEvent":
                type = 10;
                break;
            default:
                break;
        }
        return type;
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }
}
