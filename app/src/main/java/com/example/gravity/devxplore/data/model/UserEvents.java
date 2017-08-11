package com.example.gravity.devxplore.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gravity on 7/15/17.
 */

public class UserEvents {

    @SerializedName("id")
    private final int eventId;

    @SerializedName("type")
    private final String eventType;

    @SerializedName("actor")
    private final Actor eventActor;

    @SerializedName("repo")
    private final Repo eventRepo;

    @SerializedName("payload")
    private final PayLoad eventPayload;

    @SerializedName("created_at")
    private final String eventTime;

    @SerializedName("public")
    private boolean isPublic;

    public boolean isPublic() {
        return isPublic;
    }

    public String getEventTime() {
        return eventTime;
    }

    public PayLoad getEventPayload() {
        return eventPayload;
    }

    public Repo getEventRepo() {
        return eventRepo;
    }

    public Actor getEventActor() {
        return eventActor;
    }

    public String getEventType() {
        return eventType;
    }

    public int getEventId() {
        return eventId;
    }

    public class Actor {
        @SerializedName("login")
        private final String actorLogin;
        @SerializedName("url")
        private final String actorUrl;
        @SerializedName("avartar_url")
        private final String actorAvatarUrl;

        public String getActorAvatarUrl() {
            return actorAvatarUrl;
        }

        public String getActorUrl() {
            return actorUrl;
        }

        public String getActorLogin() {
            return actorLogin;
        }


        public Actor(String actorLogin, String actorUrl, String actorAvatarUrl) {
            this.actorLogin = actorLogin;
            this.actorUrl = actorUrl;
            this.actorAvatarUrl = actorAvatarUrl;
        }
    }

    public class Repo {
        @SerializedName("name")
        private final String eventRepoName;

        @SerializedName("url")
        private final String eventRepoUrl;

        public String getEventRepoName() {
            return eventRepoName;
        }
        public String getEventRepoUrl() {
            return eventRepoUrl;
        }


        public Repo(String eventRepoName, String eventRepoUrl) {
            this.eventRepoName = eventRepoName;
            this.eventRepoUrl = eventRepoUrl;
        }
    }

    public class PayLoad {
        @SerializedName("action")
        private final String eventAction;
        @SerializedName("forkee")
        private final Forkee forkedToRepo;
        @SerializedName("ref_type")
        private final String eventRefType;
        @SerializedName("member")
        private final Member addedMember;
        @SerializedName("issue")
        private final Issue eventIssue;
        @SerializedName("ref")
        private final String eventRef;
        @SerializedName("pull_request")
        private final PullRequest eventPullRequest;
        @SerializedName("release")
        private final PullRequest eventRelease;

        public PullRequest getEventRelease() {
            return eventRelease;
        }

        public PullRequest getEventPullRequest() {
            return eventPullRequest;
        }

        public String getEventRef() {
            return eventRef;
        }

        public Member getAddedMember() {
            return addedMember;
        }

        public Issue getEventIssue() {
            return eventIssue;
        }

        public String getEventRefType() {
            return eventRefType;
        }

        public Forkee getForkedToRepo() {
            return forkedToRepo;
        }

        public String getEventAction() {
            return eventAction;
        }


        public PayLoad(String eventAction, Forkee forkedToRepo, String eventRefType, Member addedMember, Issue eventIssue, String eventRef, PullRequest eventPullRequest, PullRequest eventRelease) {
            this.eventAction = eventAction;
            this.forkedToRepo = forkedToRepo;
            this.eventRefType = eventRefType;
            this.addedMember = addedMember;
            this.eventIssue = eventIssue;
            this.eventRef = eventRef;
            this.eventPullRequest = eventPullRequest;
            this.eventRelease = eventRelease;
        }
    }

    public class Release {
        @SerializedName("tag_name")
        private final String  releaseTag;

        public String getReleaseTag() {
            return releaseTag;
        }


        public Release(String releaseTag) {
            this.releaseTag = releaseTag;
        }
    }

    public class PullRequest {
        @SerializedName("html_url")
        private final String pullRequestHtmlUrl;
        @SerializedName("number")
        private final int pullRequestNumber;

        @SerializedName("merged")
        private final boolean isMerged;

        public boolean isMerged() {
            return isMerged;
        }

        public String getPullRequestHtmlUrl() {
            return pullRequestHtmlUrl;
        }

        public int getPullRequestNumber() {
            return pullRequestNumber;
        }


        public PullRequest(String pullRequestHtmlUrl, int pullRequestNumber, boolean isMerged) {
            this.pullRequestHtmlUrl = pullRequestHtmlUrl;
            this.pullRequestNumber = pullRequestNumber;
            this.isMerged = isMerged;
        }
    }

    public class Issue {
        @SerializedName("number")
        private final int issueNumber;
        @SerializedName("html_url")
        private final String issueHtmlUrl;

        public int getIssueNumber() {
            return issueNumber;
        }

        public String getIssueHtmlUrl() {
            return issueHtmlUrl;
        }


        public Issue(int issueNumber, String issueHtmlUrl) {
            this.issueNumber = issueNumber;
            this.issueHtmlUrl = issueHtmlUrl;
        }
    }

    public class Member {
        @SerializedName("login")
        private final String memberLogin;

        public String getMemberLogin() {
            return memberLogin;
        }


        public Member(String memberLogin) {
            this.memberLogin = memberLogin;
        }
    }

    public class Forkee {
        @SerializedName("full_name")
        private final String forkedRepoName;
        @SerializedName("html_url")
        private final String forkedRepoUrl;

        public String getForkedRepoUrl() {
            return forkedRepoUrl;
        }

        public String getForkedRepoName() {
            return forkedRepoName;
        }


        public Forkee(String forkedRepoName, String forkedRepoUrl) {
            this.forkedRepoName = forkedRepoName;
            this.forkedRepoUrl = forkedRepoUrl;
        }
    }

    public UserEvents (int eventId, String eventType, Actor eventActor,
                       Repo eventRepo, PayLoad eventPayload, String eventTime) {

        this.eventId = eventId;
        this.eventType = eventType;
        this.eventActor = eventActor;
        this.eventRepo = eventRepo;
        this.eventPayload = eventPayload;
        this.eventTime = eventTime;

    }










}
