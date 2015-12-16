package com.jugmontpellier

import com.jugmontpellier.jooq.tables.daos.SpeakerDao
import groovy.json.JsonSlurper
import org.jooq.Configuration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.Rollback
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringApplicationConfiguration(classes = [SpockDemoApplication.class])
@WebIntegrationTest('server.port:9000')
class IntegrationTest extends Specification {

    @Autowired
    WebApplicationContext wac

    @Autowired
    Configuration configuration

    MockMvc mockMvc
    private SpeakerDao speakerDao

    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build()
        this.speakerDao = new SpeakerDao(configuration)
    }

    def "getSpeakers by Arnaud "() {
        //region setup:
        setup:
        def jsonResponse = """
[ {
  "idSpeaker" : "b0aef670-be1c-4793-97f7-e2e2d7fb205d",
  "name_" : "Arnaud Castelltort",
  "mail" : "arnaud_castelltort@gmail.com",
  "twitter" : "@Arnaud",
  "lastTweet" : "Ce soir, session MicroBenchmarking: a survival guide on JVM au @montpellierjug !!",
  "talks" : [ {
    "idTalk" : "f831cea7-2e21-4b57-906e-ee4d2deda6bb",
    "title" : "MicroBenchmarking: a survival guide on JVM",
    "description" : "What is MicroBenchmarking ? What are the problems with the dynamic compilation language, the usecase of the JVM The Heisenberg principle Statistics theory as a rigorous data analysis approach Scala Meter Sum-Up Survival Guide",
    "date_" : 1450294800000,
    "speakerFk" : "b0aef670-be1c-4793-97f7-e2e2d7fb205d"
  }, {
    "idTalk" : "4f96748e-36d7-4934-9549-4de525b2021d",
    "title" : "Parser combinator in Scala",
    "description" : "",
    "date_" : 1450296000000,
    "speakerFk" : "b0aef670-be1c-4793-97f7-e2e2d7fb205d"
  } ]
} ]
"""
        //endregion

        //region expect:
        expect:
        this.mockMvc.perform(get("/speaker?idSpeaker=b0aef670-be1c-4793-97f7-e2e2d7fb205d")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(jsonResponse));
        //endregion
    }

    @Transactional
    @Rollback
    def "Insert New Speaker in Database"() {
        //region setup:
        setup:
        def jsonRequest = """
{
  "name_" : "New Speaker",
  "mail" : "new_speaker@gmail.com",
  "twitter" : "@Speaker"
}
"""
        //endregion

        //region when:
        when:
        this.mockMvc.perform(post("/speaker")
                .content(jsonRequest)
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
        //endregion

        //region then:
        then:
        def speakers = speakerDao.fetchByMail('new_speaker@gmail.com')
        speakers.size() == 1

        def speaker = speakers[0]
        speaker.idSpeaker != null
        speaker.name_ == 'New Speaker'
        speaker.mail == 'new_speaker@gmail.com'
        speaker.twitter == '@Speaker'
        //endregion
    }

    @Transactional
    @Rollback
    def "Insert New Speaker json Response"() {
        //region setup:
        setup:
        def jsonRequest = """
{
  "name_" : "New Speaker",
  "mail" : "new_speaker@gmail.com",
  "twitter" : "@Speaker"
}
"""
        //endregion

        //region when:
        when:
        def jsonResponse = this.mockMvc.perform(post("/speaker")
                .content(jsonRequest)
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().response.getContentAsString()
        // endregion

        //region then:
        then:
        def jsonSlurper = new JsonSlurper().parseText(jsonResponse)
        jsonSlurper.idSpeaker != null
        jsonSlurper.name_ == 'New Speaker'
        jsonSlurper.mail == 'new_speaker@gmail.com'
        jsonSlurper.twitter == '@Speaker'
        // endregion
    }


}
