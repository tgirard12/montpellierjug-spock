package com.jugmontpellier;

import com.jugmontpellier.entity.Speaker;

import java.util.List;

public interface SpeakerService {
    List<Speaker> getSpeakers();

    Speaker createSpeaker(Speaker speaker);

    List<Speaker> getSpeakerByIdAndTalks(String idSpeaker);
}
