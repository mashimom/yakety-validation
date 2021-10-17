package json

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification

class FieldValidationIT extends Specification {
	ObjectMapper objectMapper = new ObjectMapper()

	def "ID_EMPTY accepts"() {
		given:
		def movie = Movies.loadList()[1]

		expect:
		FieldValidation.ID_EMPTY.inViolation(movie).isEmpty()
	}

	def "ID_EMPTY rejects"() {
		given:
		JsonNode badNode = objectMapper.createObjectNode().put("id", "")

		expect:
		FieldValidation.ID_EMPTY.inViolation(badNode) == Optional.of(FieldValidation.ID_EMPTY)
	}

	def "RATING_EMPTY accepts"() {
		given:
		def movie = Movies.loadList()[1]

		expect:
		FieldValidation.RATING_EMPTY.inViolation(movie).isEmpty()
	}

	def "RATING_EMPTY rejects"() {
		given:
		JsonNode badNode = objectMapper.createObjectNode().put("rating", "")

		expect:
		FieldValidation.RATING_EMPTY.inViolation(badNode) == Optional.of(FieldValidation.RATING_EMPTY)
	}

	def "RATING_OUT_OF_RANGE accepts"() {
		given:
		def movie = Movies.loadList()[1]

		expect:
		FieldValidation.RATING_OUT_OF_RANGE.inViolation(movie).isEmpty()
	}

	def "RATING_OUT_OF_RANGE rejects"() {
		given:
		JsonNode badNode = objectMapper.createObjectNode().put("rating", "R")

		expect:
		FieldValidation.RATING_OUT_OF_RANGE.inViolation(badNode) == Optional.of(FieldValidation.RATING_OUT_OF_RANGE)
	}
}
