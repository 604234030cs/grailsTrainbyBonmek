package box

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class FixtureServiceSpec extends Specification implements ServiceUnitTest<FixtureService>{

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
