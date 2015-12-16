package com.jugmontpellier.entity;

import java.util.List;

public class Speaker extends com.jugmontpellier.jooq.tables.pojos.Speaker {

    private String lastTweet;

    private List<Talk> talks;

    public String getLastTweet() {
        return lastTweet;
    }

    public void setLastTweet(String lastTweet) {
        this.lastTweet = lastTweet;
    }

    public List<Talk> getTalks() {
        return talks;
    }

    public void setTalks(List<Talk> talks) {
        this.talks = talks;
    }
}
