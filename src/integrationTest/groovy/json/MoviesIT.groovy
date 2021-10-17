package json

import spock.lang.Specification

class MoviesIT extends Specification {


	def "data loaded"() {
		when:
		def result = new Movies()

		then:
		result.movies != null
		!result.movies.isEmpty()
		result.movies.get('movies').size() == 27
		result.movies.get('movies').get(23).get('title').asText() == 'Luca'
	}
}
