package json.model

import spock.lang.Specification

class MovieRatingsIT extends Specification {

    def "sanity check"() {
        given:
        def domain = EnumSet.of(MovieRatings)

        expect:
        domain*.externalValue.size() == domain.size()
    }
}
