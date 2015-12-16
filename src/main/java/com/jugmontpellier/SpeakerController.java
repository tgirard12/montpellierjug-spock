package com.jugmontpellier;

import com.jugmontpellier.entity.Speaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpeakerController {

    SpeakerService speakerService;

    @Autowired
    public SpeakerController(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    @RequestMapping(
            value = "/speakers",
            method = RequestMethod.GET)
    public List<Speaker> getSpeakers() {
        return speakerService.getSpeakers();

    }

    @RequestMapping(
            value = "/speaker",
            method = RequestMethod.GET)
    public List<Speaker> getSpeakerByIdAndTalks(@RequestParam("idSpeaker") String idSpeaker) {
        return speakerService.getSpeakerByIdAndTalks(idSpeaker);
    }

    @RequestMapping(
            value = "/speaker",
            method = RequestMethod.POST)
    public Speaker createSPeaker(@RequestBody Speaker speaker) {
        return speakerService.createSpeaker(speaker);

    }

}
