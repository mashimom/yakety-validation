package org.shimomoto.yakety.validation

import spock.lang.Specification

class BaseSetIdViolationSpec extends Specification {
	def "SANITY - constructor works"() {
		given:
		def si = Mock(Set)

		when:
		BaseSetIdViolation bsiv = new BaseSetIdViolation(si, 'some string')

		then:
		bsiv.setIds == si
		bsiv.violationCode == 'some string'
	}

	def "SANITY - constructor fails"() {
		when:
		new BaseSetIdViolation(setids, id)

		then:
		thrown IllegalArgumentException

		where:
		_ | setids     | id
//		0 | [].toSet() | ' \t\n'
		1 | null       | 'valid'
		2 | [].toSet() | null
		3 | null       | null
	}
}
