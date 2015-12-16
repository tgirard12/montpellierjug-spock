package com.jugmontpellier

import com.jugmontpellier.entity.Speaker
import spock.lang.Specification
import spock.lang.Unroll

class SpeakerServiceImplTest extends Specification {

    def speakerRepositoryMock = Mock(SpeakerRepository)
    def twitterServiceMock = Mock(TwitterService)
    def speakerService = new SpeakerServiceImpl(speakerRepositoryMock, twitterServiceMock)

    def setup() {

    }

    def cleanup() {

    }

    def "getSpeekers() retreive last speaker Tweet"() {
        //region setup:
        setup:
        def speakers = [
                new Speaker(
                        idSpeaker: UUID.fromString('f70634a9-70f8-4792-bbd9-dd652362fe8'),
                        name_: 'speaker1',
                        twitter: '@speaker1'),
                new Speaker(
                        idSpeaker: UUID.fromString('0953d8c8-4ea8-4859-adfa-3194c450ee16'),
                        name_: 'speaker2',
                        twitter: '@speaker2')]
        speakerRepositoryMock.findAll() >> { return speakers }
        //endregion

        //region when:
        when:
        speakerService.getSpeakers()
        //endregion

        //region then:
        then:
        speakers.size() * twitterServiceMock.getLastTweet(_)
        //endregion
    }

    @Unroll
    /**
     * - Name and email required
     * - Email unique
     */
    def "test createSpeaker precondition"() {
        setup:
        speakerRepositoryMock.isMailAllReadyExist('speakerEXIST@mail.com') >> { return true }

        //region expect:
        expect:
        def ex = getException {
            speakerService.createSpeaker(new Speaker(name_: name, mail: mail, twitter: twitter))
        }
        if (ex != null) ex.class == exception
        ex?.message == message
        //endregion

        //region where:
        where:
        name           | mail                    | twitter         || exception | message
        'speaker1'     | 'speaker1@mail.com'     | '@speaker1'     || null | null
        'speaker1'     | 'speaker1@mail.com'     | null            || null | null

        //region Error
        null           | 'speaker1@mail.com'     | '@speaker2'     || RuntimeException | 'Speaker Name Null'
        'speaker2'     | null                    | '@speaker2'     || RuntimeException | 'Speaker email Null'
        'speakerEXIST' | 'speakerEXIST@mail.com' | '@speakerEXIST' || RuntimeException | 'Email allready in database'
        //endregion
        //endregion
    }

    def getException(Closure closure) {
        try {
            closure.run()
            return null
        } catch (Exception ex) {
            return ex
        }
    }
}
