package org.shimomoto.yakety.validation

import spock.lang.Specification

class BaseNonNullValidationSpec extends Specification {
	def "SANITY - constructor works"() {
		when:
		BaseNonNullValidation<String> validation = new BaseNonNullValidation<>('some string')

		then:
		validation.violationCode == 'some string'
	}

	//TODO: fix constructor
	def "SANITY - constructor works but shouldn't"() {
		when:
		BaseNonNullValidation<String> validation = new BaseNonNullValidation<>('')

		then:
		validation.violationCode == ''
	}

	//TODO: fix constructor
	def "SANITY - constructor works but shouldn't also"() {
		when:
		BaseNonNullValidation<String> validation = new BaseNonNullValidation<>(' \t\n')

		then:
		validation.violationCode == ' \t\n'
	}

	def "SANITY - constructor fails"() {
		when:
		new BaseNonNullValidation<>(null)

		then:
		thrown IllegalArgumentException
	}

	def "Validation accepts"() {
		given:
		BaseNonNullValidation<String> validation = new BaseNonNullValidation<>('some string')

		when:
		def result = validation.inViolation("lala")

		then:
		result.isEmpty()
		and:
		!validation.test("lala")
	}

	def "Validation refuses"() {
		given:
		BaseNonNullValidation<String> validation = new BaseNonNullValidation<>('some string')

		when:
		def result = validation.inViolation(null)

		then:
		result.isPresent()
		result.get().violationCode == 'some string'
		and:
		validation.test(null)
	}
}
