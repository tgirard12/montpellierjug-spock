package com.jugmontpellier;

import com.jugmontpellier.entity.Speaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional(readOnly = true)
public class SpeakerServiceImpl implements SpeakerService {

    SpeakerRepository speakerRepository;

    TwitterService twitterService;

    @Autowired
    public SpeakerServiceImpl(SpeakerRepository speakerRepository, TwitterService twitterService) {
        this.speakerRepository = speakerRepository;
        this.twitterService = twitterService;
    }

    @Override
    public List<Speaker> getSpeakers() {
        List<Speaker> speakers = speakerRepository.findAll();
        for (Speaker speaker : speakers)
            speaker.setLastTweet(twitterService.getLastTweet(speaker));

        return speakers;
    }

    @Override
    public List<Speaker> getSpeakerByIdAndTalks(String idSpeaker) {
        List<Speaker> speakers = speakerRepository.findSpeakerByIdAndTalks(idSpeaker);
        for (Speaker speaker : speakers)
            speaker.setLastTweet(twitterService.getLastTweet(speaker));

        return speakers;
    }

    @Override
    @Transactional(readOnly = false)
    public Speaker createSpeaker(Speaker speaker) {

        if (StringUtils.isEmpty(speaker.getName_()))
            throw new RuntimeException("Speaker Name Null");
        if (StringUtils.isEmpty(speaker.getMail()))
            throw new RuntimeException("Speaker email Null");

        if (speakerRepository.isMailAllReadyExist(speaker.getMail()))
            throw new RuntimeException("Email allready in database");

        speaker.setIdSpeaker(null);
        return speakerRepository.insertSpeaker(speaker);
    }

}
