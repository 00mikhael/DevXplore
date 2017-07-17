package com.example.gravity.devxplore.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gravity on 7/15/17.
 */

public class UserEvents {

    @SerializedName("id")
    private String eventId;

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

    public String getEventId() {
        return eventId;
    }

    public class Actor {
        @SerializedName("login")
        private String actorLogin;

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

        public String getEventAction() {
            return eventAction;
        }
    }
}
