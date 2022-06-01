package json.model

import spock.lang.Specification

class MovieRatingsIT extends Specification {

    def "sanity check"() {
        given:
        List<MovieRatings> domain = MovieRatings.values().toList()

        expect:
        domain.collect { it.getExternalValue() }.toSet().size() == domain.size()
    }
}
