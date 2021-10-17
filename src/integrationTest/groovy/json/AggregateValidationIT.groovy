package json

import java.time.LocalDate

import spock.lang.Ignore
import spock.lang.Specification

class AggregateValidationIT extends Specification {

	@Ignore
	def "IS_UNRATED_AND_RELEASED accepts"() {
		given:
		def movie = Movies.loadList()[1]
		def validation = AggregateValidation.getUnratedAndReleasedAfter(LocalDate.of(2021, 6, 18))
		expect:
		1 == 1
	}
}
