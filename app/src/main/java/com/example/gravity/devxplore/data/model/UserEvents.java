package com.example.gravity.devxplore.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gravity on 7/15/17.
 */

public class UserEvents {

    @SerializedName("id")
    private int eventId;

    @SerializedName("type")
    private String eventType;

    @SerializedName("actor")
    private Actor eventActor;

    @SerializedName("repo")
    private Repo eventRepo;

    @SerializedName("payload")
    private PayLoad eventPayload;

    @SerializedName("created_at")
    private String eventTime;

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
        private String actorLogin;
        @SerializedName("url")
        private String actorUrl;
        @SerializedName("avartar_url")
        private String actorAvatarUrl;

        public String getActorAvatarUrl() {
            return actorAvatarUrl;
        }

        public String getActorUrl() {
            return actorUrl;
        }

        public String getActorLogin() {
            return actorLogin;
        }
    }

    public class Repo {
        @SerializedName("name")
        private String eventRepoName;

        @SerializedName("url")
        private String eventRepoUrl;

        public String getEventRepoName() {
            return eventRepoName;
        }
        public String getEventRepoUrl() {
            return eventRepoUrl;
        }
    }

    public class PayLoad {
        @SerializedName("action")
        private String eventAction;
        @SerializedName("forkee")
        private Forkee forkedToRepo;
        @SerializedName("ref_type")
        private String eventRefType;
        @SerializedName("member")
        private Member addedMember;
        @SerializedName("issue")
        private Issue eventIssue;
        @SerializedName("ref")
        private String pushRef;
        @SerializedName("pull_request")
        private PullRequest eventPullRequest;

        public PullRequest getEventPullRequest() {
            return eventPullRequest;
        }

        public String getPushRef() {
            return pushRef;
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
    }

    public class PullRequest {
        @SerializedName("html_url")
        private String pullRequestHtmlUrl;
        @SerializedName("number")
        private int pullRequestNumber;

        public String getPullRequestHtmlUrl() {
            return pullRequestHtmlUrl;
        }

        public int getPullRequestNumber() {
            return pullRequestNumber;
        }
    }

    public class Issue {
        @SerializedName("number")
        private int issueNumber;
        @SerializedName("html_url")
        private String issueHtmlUrl;

        public int getIssueNumber() {
            return issueNumber;
        }

        public String getIssueHtmlUrl() {
            return issueHtmlUrl;
        }
    }

    public class Member {
        @SerializedName("login")
        private String memberLogin;

        public String getMemberLogin() {
            return memberLogin;
        }
    }

    public class Forkee {
        @SerializedName("full_name")
        private String forkedRepoName;
        @SerializedName("html_url")
        private String forkedRepoUrl;

        public String getForkedRepoUrl() {
            return forkedRepoUrl;
        }

        public String getForkedRepoName() {
            return forkedRepoName;
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
