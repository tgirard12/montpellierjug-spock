package com.jugmontpellier

import groovy.sql.Sql
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class SimpleSpockTest extends Specification {

    @Shared
    def sql = Sql.newInstance('jdbc:postgresql://localhost:5432/spock_demo', 'postgres', 'postgres', 'org.postgresql.Driver')

    def setup() {
    }

    def cleanup() {

    }

    def "Assertion"() {
        setup:
        def list = []

        when:
        list << 'Value 1'

        //region then:
        then:
        list.size() == 1
        list[0] == 'Value 1'
        //endregion
    }

    def "Exception thrown"() {
        setup:
        def map = null

        when:
        map.put('key2', 'val2')

        //region then:
        then:
        def e = thrown(NullPointerException)
        e.cause == null
        //endregion
    }

    def "Exception Not Thrown"() {
        setup:
        def map = [key: 'Value']

        when:
        map.put('key 2', 'Value 2')

        //region then:
        then:
        notThrown(NullPointerException)
        //endregion
    }

    def "Data table"() {
        expect:
        Math.max(a, b) == c

        //region where:
        where:
        a | b || c
        3 | 5 || 5
        7 | 0 || 7
        0 | 0 || 0
        //endregion
    }

    @Unroll
    def "Data table between #a and #b"() {
        expect:
        Math.max(a, b) == c

        //region where:
        where:
        a | b || c
        3 | 5 || 5
        7 | 0 || 7
        0 | 0 || 0
        //endregion
    }

    def "Interaction"() {
        setup:
        def list = Mock(ArrayList)

        when:
        list << 'Value 1'
        list << 'Value 2'

        //region then:
        then:
        1 * list.add('Value 1')
        1 * list.add('Value 2')
        //endregion
    }

    def "Interactions with wildcard"() {
        setup:
        def list = Mock(ArrayList)

        when:
        list << 'Value 1';
        list << 'Value 2';
        list << 'Value 3';

        //region then:
        then:
        (1..3) * list.add('Value 1')
        (1.._) * list.add('Value 2')
        1 * list./a.*d/('Value 3')
        _ * list.add('Value 2')
        _ * list.add(_)
        _ * list._(_)
        _ * _._(_)
        //endregion
    }

    def "Interactions in order"() {
        setup:
        def list = Mock(ArrayList)

        when:
        list << 'Value 1'
        list << 'Value 2'
        list << 'Value 3'

        //region then:
        then:
        1 * list.add('Value 1')
        then:
        1 * list.add('Value 2')
        then:
        1 * list.add('Value 3')
        //endregion
    }

    @Unroll
    def "Data pipe #name_"() {
        expect:
        mail.length() > name_.length()

        //region where:
        where:
        [name_, mail] << sql.rows("""select name_, mail from public."SPEAKER" """)
        //endregion
    }
}
