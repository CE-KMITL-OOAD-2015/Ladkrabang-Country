package com.awakenguys.kmitl.ladkrabangcountry.model;

import java.util.ArrayList;



public class User {
    private String id;
    private String fbId;
    private String name;
    private  int level;
    private boolean banned;
    ArrayList<String> ratedReviews = new ArrayList<String>();

    public User() {
    }

    public User(String fbId, String name, int level) {
        this.fbId = fbId;
        this.name = name;
        this.level = level;
        banned = false;
    }

    public User(String fbId, String name,  int level, boolean banned) {
        this.fbId = fbId;
        this.name = name;
        this.level = level;
        this.banned = banned;
    }

    public User(String id, String fbId, String name,  int level, boolean banned, ArrayList<String> ratedReviews) {
        this.id = id;
        this.fbId = fbId;
        this.name = name;
        this.level = level;
        this.banned = banned;
        this.ratedReviews = ratedReviews;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<String> getRatedReviews() {
        return ratedReviews;
    }

    public void setRatedReviews(ArrayList<String> ratedReviews) {
        this.ratedReviews = ratedReviews;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", fbId='" + fbId + '\'' +
                ", name='" + name + '\'' +
                ", banned=" + banned +
                ", level=" + level +
                ", ratedReviews=" + ratedReviews +
                '}';
    }
}
