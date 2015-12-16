package com.jugmontpellier;

import com.jugmontpellier.entity.Speaker;

import java.util.List;

public interface SpeakerRepository {
    boolean isMailAllReadyExist(String mail);

    Speaker insertSpeaker(Speaker speaker);

    List<Speaker> findAll();

    List<Speaker> findSpeakerByIdAndTalks(String idSpeaker);
}
