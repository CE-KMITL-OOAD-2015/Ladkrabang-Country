package com.awakenguys.kmitl.ladkrabangcountry.model;

import java.util.ArrayList;
import org.springframework.data.annotation.Id;

public class User {
    @Id private String id;
    private String fb_id;
    private String name;
    private boolean banned;
    private  int level;
    public final int ADMIN = 0;
    public final int MEMBER = 1;
    ArrayList<String> rateReview = new ArrayList<String>();
    public User(String fb_id, String name, boolean banned, int level, ArrayList<String> rateReview){
        this.fb_id = fb_id;
        this.name = name;
        this.banned = banned;
        this.level = level;
        this.rateReview = rateReview;
    }

    public String getFb_id() {
        return fb_id;
    }

    public void setFb_id(String fb_id) {
        this.fb_id = fb_id;
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

    public ArrayList<String> getRateReview() {
        return rateReview;
    }

    public void setRateReview(ArrayList<String> rateReview) {
        this.rateReview = rateReview;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", fb_id='" + fb_id + '\'' +
                ", name='" + name + '\'' +
                ", banned=" + banned +
                ", level=" + level +
                ", rateReview=" + rateReview +
                '}';
    }
}
