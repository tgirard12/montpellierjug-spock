package com.jugmontpellier;

import com.jugmontpellier.entity.Speaker;
import com.jugmontpellier.entity.Talk;
import org.springframework.stereotype.Component;

@Component
public class TwitterServiceImpl implements TwitterService {


    @Override
    public String getLastTweet(Speaker speaker) {

        Talk talk = null;
        if (speaker.getTalks() != null)
            talk = speaker.getTalks().stream()
                    .findFirst()
                    .orElse(null);

        if (talk == null)
            return "";

        return "Ce soir, session " + talk.getTitle() + " au @montpellierjug !!";
    }
}
