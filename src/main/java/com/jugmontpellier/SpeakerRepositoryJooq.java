package com.jugmontpellier;

import com.jugmontpellier.entity.Speaker;
import com.jugmontpellier.entity.Talk;
import com.jugmontpellier.jooq.tables.daos.SpeakerDao;
import com.jugmontpellier.jooq.tables.records.SpeakerRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.jugmontpellier.jooq.tables.Speaker.SPEAKER;
import static com.jugmontpellier.jooq.tables.Talk.TALK;
import static org.jooq.impl.DSL.count;

@Repository
public class SpeakerRepositoryJooq implements SpeakerRepository {

    SpeakerDao speakerDao;
    DSLContext dslContext;

    @Autowired
    public SpeakerRepositoryJooq(DSLContext dslContext, Configuration configuration) {
        this.dslContext = dslContext;
        speakerDao = new SpeakerDao(configuration);
    }

    @Override
    public boolean isMailAllReadyExist(String mail) {
        return dslContext.select(count(SPEAKER.ID_SPEAKER))
                .from(SPEAKER)
                .where(SPEAKER.MAIL.eq(mail))
                .fetchOne()
                .value1() > 0;
    }

    @Override
    public Speaker insertSpeaker(Speaker speaker) {
        return dslContext.insertInto(SPEAKER,
                SPEAKER.MAIL,
                SPEAKER.NAME_,
                SPEAKER.TWITTER)
                .values(speaker.getMail(),
                        speaker.getName_(),
                        speaker.getTwitter())
                .returning()
                .fetchOne()
                .into(Speaker.class);
    }

    @Override
    public List<Speaker> findAll() {
        Map<SpeakerRecord, Result<Record>> speakerRecordResultMap = dslContext
                .select(SPEAKER.fields())
                .select(TALK.fields())
                .from(SPEAKER)
                .leftJoin(TALK).on(TALK.SPEAKER_FK.eq(SPEAKER.ID_SPEAKER))
                .fetch()
                .intoGroups(SPEAKER);
        return fetchSpeakersAndTalkFromSpeakerGroups(speakerRecordResultMap);
    }


    @Override
    public List<Speaker> findSpeakerByIdAndTalks(String idSpeaker) {
        Map<SpeakerRecord, Result<Record>> speakerRecordResultMap = dslContext
                .select(SPEAKER.fields())
                .select(TALK.fields())
                .from(SPEAKER)
                .leftJoin(TALK).on(TALK.SPEAKER_FK.eq(SPEAKER.ID_SPEAKER))
                .where(SPEAKER.ID_SPEAKER.eq(UUID.fromString(idSpeaker)))
                .fetch()
                .intoGroups(SPEAKER);
        return fetchSpeakersAndTalkFromSpeakerGroups(speakerRecordResultMap);
    }

    private ArrayList<Speaker> fetchSpeakersAndTalkFromSpeakerGroups(Map<SpeakerRecord, Result<Record>> speakerRecordResultMap) {
        ArrayList<Speaker> speakers = new ArrayList<Speaker>();
        for (SpeakerRecord speakerRecord : speakerRecordResultMap.keySet()) {

            Speaker speaker = speakerRecord.into(SPEAKER).into(Speaker.class);
            speaker.setTalks(speakerRecordResultMap.get(speakerRecord).into(TALK).into(Talk.class));
            speakers.add(speaker);
        }
        return speakers;
    }

}
