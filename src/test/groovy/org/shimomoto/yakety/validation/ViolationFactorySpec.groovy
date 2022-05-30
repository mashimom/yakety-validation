package org.shimomoto.yakety.validation

import spock.lang.Specification

class ViolationFactorySpec extends Specification {

	def "simple works"() {
		when:
		def result = ViolationFactory.simple(vc)

		then:
		result.violationCode == vc

		where:
		_ | vc
		0 | 'SOME_VALUE'
		1 | '1215'
		2 | 'lapala'
		3 | 'wioiwnv.xc'
		4 | ' lala '
		5 | '\tvalid'
	}

	def "simple does not accept"() {
		when:
		ViolationFactory.simple(vc)

		then:
		thrown IllegalArgumentException

		where:
		_ | vc
		0 | null
		1 | ''
		2 | '   '
		3 | '\n'
		4 | '\t \n'
	}
}
