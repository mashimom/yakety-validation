package org.shimomoto.yakety.validation

import java.util.function.Predicate

import org.shimomoto.yakety.validation.api.Violation
import spock.lang.Specification

class BaseValidationSpec extends Specification {

	def "SANITY - constructor errors"() {
		when:
		new BaseValidation<>(p, v)

		then:
		thrown IllegalArgumentException

		where:
		_ | p               | v
		0 | null            | Mock(Violation)
		1 | Mock(Predicate) | null
		2 | null            | null
	}

	def "SANITY - constructor"() {
		given:
		def p = Mock(Predicate)
		def v = Mock(Violation)

		when:
		def validation = new BaseValidation<>(p, v)

		then:
		validation instanceof BaseValidation
		validation.pred == p
		validation.violation == v
	}

	def "BaseValidation finds a violation"() {
		given:
		Predicate<String> p = Mock(Predicate)
		Violation v = Mock(Violation)
		BaseValidation<String> validation = new BaseValidation<>(p, v)

		when:
		def result = validation.inViolation("some string")

		then:
		result.isPresent()
		result.get() == v
		and:
		1 * p.test("some string") >> true
	}

	def "BaseValidation finds no violation"() {
		given:
		Predicate<String> p = Mock(Predicate)
		Violation v = Mock(Violation)
		BaseValidation<String> validation = new BaseValidation<>(p, v)

		when:
		def result = validation.inViolation("some string")

		then:
		result.isEmpty()
		and:
		1 * p.test("some string") >> false
	}
}
