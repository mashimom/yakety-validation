package json

import java.time.LocalDate

import spock.lang.Ignore
import spock.lang.Specification

class AggregateValidationIT extends Specification {


	def "IS_UNRATED_AND_RELEASED accepts"() {
		given:
		def movie = Movies.loadList()[22]
		def validation = AggregateValidation.getUnratedAndReleasedAfter(LocalDate.of(2021, 06, 18))
		expect:
		validation.test(movie)
	}
}
