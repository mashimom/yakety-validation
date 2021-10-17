package org.shimomoto.yakety.validation

import spock.lang.Specification

class BaseViolationSpec extends Specification {

	def "Builder works"() {
		expect:
		BaseViolation.build(s).getViolationCode() == s

		where:
		_ | s
		0 | 'lala'
		1 | '123'
		2 | '&$%#'
		3 | '\t\n3  '
	}

	def "Builder fails"() {
		when:
		BaseViolation.build(i)
		then:
		thrown IllegalArgumentException

		where:
		i << [null, "", " \t", "\n\t"]
	}

}
