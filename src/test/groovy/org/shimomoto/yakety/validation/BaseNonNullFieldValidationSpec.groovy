package org.shimomoto.yakety.validation

import spock.lang.Specification

class BaseNonNullFieldValidationSpec extends Specification {

	static class SupportingPOGO {
		String aProperty

		SupportingPOGO(String aProperty) {
			this.aProperty = aProperty
		}

		String getAProperty() {
			return aProperty
		}
	}

	def "SANITY - support class"() {
		expect:
		new SupportingPOGO('lala') != null
		new SupportingPOGO(null) != null
	}

	SupportingPOGO valid = new SupportingPOGO('lala')
	SupportingPOGO invalid = new SupportingPOGO(null)

	def "SANITY - constructor works"() {
		when:
		BaseNonNullFieldValidation<SupportingPOGO> validation =
						new BaseNonNullFieldValidation<>({ SupportingPOGO o -> o.aProperty }, 'some string')

		then:
		validation.violationCode == 'some string'
	}

	//TODO: fix constructor
	def "SANITY - constructor works but shouldn't"() {
		when:
		BaseNonNullFieldValidation<SupportingPOGO> validation =
						new BaseNonNullFieldValidation<>({ SupportingPOGO o -> o.aProperty }, '')

		then:
		validation.violationCode == ''
	}

	//TODO: fix constructor
	def "SANITY - constructor works but shouldn't also"() {
		when:
		BaseNonNullFieldValidation<SupportingPOGO> validation =
						new BaseNonNullFieldValidation<>({ SupportingPOGO o -> o.aProperty }, ' \t\n')

		then:
		validation.violationCode == ' \t\n'
	}

	def "SANITY - constructor fails on null identifier"() {
		when:
		new BaseNonNullFieldValidation<>({ SupportingPOGO o -> o.aProperty }, null)

		then:
		thrown IllegalArgumentException
	}

	def "SANITY - constructor fails on null getter"() {
		when:
		new BaseNonNullFieldValidation<>(null, 'some string')

		then:
		thrown IllegalArgumentException
	}

	def "Validation accepts"() {
		given:
		BaseNonNullFieldValidation<SupportingPOGO> validation =
						new BaseNonNullFieldValidation<>({ SupportingPOGO o -> o.aProperty }, 'some string')

		when:
		def result = validation.inViolation(valid)

		then:
		result.isEmpty()
		and:
		!validation.test(valid)
	}

	def "Validation refuses"() {
		given:
		BaseNonNullFieldValidation<SupportingPOGO> validation =
						new BaseNonNullFieldValidation<>({ SupportingPOGO o -> o.aProperty }, 'some string')

		when:
		def result = validation.inViolation(invalid)

		then:
		result.isPresent()
		result.get().violationCode == 'some string'
		and:
		validation.test(invalid)
	}
}
