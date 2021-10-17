package org.shimomoto.yakety.validation

import java.util.function.Function
import java.util.function.Predicate

import spock.lang.Specification

class ValidationFactorySpec extends Specification {

	def "nonNull builds correctly"() {
		given:
		def code = 'some identifier'
		when:
		def result = ValidationFactory.nonNull(code)

		then:
		result instanceof BaseNonNullValidation
		result.@violationCode == code
	}

	def "nonNull refuses blank or null identifier"() {
		when:
		ValidationFactory.nonNull(code)

		then:
		thrown IllegalArgumentException

		where:
		_ | code
		0 | ' \t\n'
		1 | null
	}

	def "nonNullAggregateField builds correctly"() {
		given:
		def code = 'some identifier'
		def fn = Mock(Function)

		when:
		def result = ValidationFactory.nonNullAggregateField(fn, code)

		then:
		result instanceof BaseNonNullFieldValidation
		result.@violationCode == code
		result.@getter == fn
	}

	def "nonNullAggregateField refuses blank identifier or nulls"() {
		when:
		ValidationFactory.nonNullAggregateField(fn, code)

		then:
		thrown IllegalArgumentException

		where:
		_ | fn             | code
		0 | Mock(Function) | ' \t\n'
		1 | Mock(Function) | null
		2 | null           | 'valid'
		3 | null           | null
	}

	def "simple builds correctly"() {
		given:
		def code = 'some identifier'
		def p = Mock(Predicate)

		when:
		def result = ValidationFactory.simple(p, code)

		then:
		result instanceof BaseValidation
		result.@pred == p
		result.@violation instanceof BaseViolation
		result.@violation.@violationCode == code
	}

	def "simple refuses blank identifier or nulls"() {
		when:
		ValidationFactory.simple(p, code)

		then:
		thrown IllegalArgumentException

		where:
		_ | p               | code
		0 | Mock(Predicate) | ' \t\n'
		1 | Mock(Predicate) | null
		2 | null            | 'valid'
		3 | null            | null
	}

	def "forAggregateField builds correctly"() {
		given:
		def code = 'some identifier'
		def fn = Mock(Function)
		def p = Mock(Predicate)
		def aggr = Mock(Object)
		def field = Mock(Object)

		when:
		def result = ValidationFactory.forAggregateField(fn, p, code)

		then:
		result instanceof BaseValidation
		result.@pred instanceof Predicate
		result.@violation instanceof BaseViolation
		result.@violation.@violationCode == code

		when: 'executed, predicate is composed correctly'
		result.inViolation(aggr)
		then: 'interactions'
		1 * fn.apply(aggr) >> field
		1 * p.test(field) >> true
		0 * _
	}

	def "forAggregateField refuses nulls or blank identifier"() {
		when:
		ValidationFactory.forAggregateField(fn, p, code)

		then:
		thrown IllegalArgumentException

		where:
		_ | fn             | p               | code
		0 | Mock(Function) | Mock(Predicate) | ' \t\n'
		1 | Mock(Function) | Mock(Predicate) | null
		2 | Mock(Function) | null            | 'valid'
		3 | null           | Mock(Predicate) | 'valid'
		4 | null           | null            | null
	}
}
