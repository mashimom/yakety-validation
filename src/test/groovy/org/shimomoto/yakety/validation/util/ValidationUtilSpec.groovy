package org.shimomoto.yakety.validation.util

import java.util.function.Function

import org.shimomoto.yakety.validation.api.Validation
import org.shimomoto.yakety.validation.api.Violation
import spock.lang.Specification

class ValidationUtilSpec extends Specification {

	def "validateOne against one validation (in list) for violation list accepts"() {
		given:
		def validation = Mock(Validation)
		def subject = Mock(Object)

		when:
		def result = ValidationUtil.validateOne(subject, [validation])

		then:
		result == []
		and: 'interactions'
		1 * validation.inViolation(subject) >> Optional.empty()
		0 * _
	}

	def "validateOne against one validation (in list) for violation list rejects"() {
		given:
		Validation<String> validation = Mock(Validation)
		Violation violation = Mock(Violation)
		def subject = Mock(Object)

		when:
		def result = ValidationUtil.validateOne(subject, [validation])

		then:
		result == [violation]
		and: 'interactions'
		1 * validation.inViolation(subject) >> Optional.of(violation)
	}

	def "validateOne rejects input"() {
		when:
		ValidationUtil.validateOne(s, vl)

		then:
		thrown IllegalArgumentException

		where:
		_ | s            | vl
		0 | Mock(Object) | null
		1 | null         | []
	}

	def "ValidateMany against one validation (in list) for violationsById map accepts"() {
		given:
		def validation = Mock(Validation)
		def fn = Mock(Function)
		def subject = Mock(Object)
		def id = 42L

		when:
		def result = ValidationUtil.validateMany([subject], fn, [validation])

		then:
		result == [:]
		and: 'interactions'
		1 * validation.inViolation(subject) >> Optional.empty()
		1 * fn.apply(subject) >> id
		0 * _
	}

	def "ValidateMany against one validation (in list) for violationsById map rejects"() {
		given:
		def validation = Mock(Validation)
		def violation = Mock(Violation)
		def fn = Mock(Function)
		def subject = Mock(Object)
		def id = 42L

		when:
		def result = ValidationUtil.validateMany([subject], fn, [validation])

		then:
		result == [(42L): [violation]]
		and: 'interactions'
		1 * validation.inViolation(subject) >> Optional.of(violation)
		1 * fn.apply(subject) >> id
		0 * _
	}

	def "validateMany rejects input"() {
		when:
		ValidationUtil.validateMany(s, fn, v)

		then:
		thrown IllegalArgumentException

		where:
		_ | s              | fn             | v
		0 | null           | Mock(Function) | [Mock(Validation)]
		1 | [Mock(Object)] | null           | [Mock(Validation)]
		2 | [Mock(Object)] | Mock(Function) | null
	}
}
