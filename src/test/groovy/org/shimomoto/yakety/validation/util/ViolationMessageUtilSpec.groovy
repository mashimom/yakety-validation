package org.shimomoto.yakety.validation.util

import java.util.function.Function

import org.shimomoto.yakety.validation.api.Violation
import spock.lang.Specification

class ViolationMessageUtilSpec extends Specification {

	def "basicRepresentation works"() {
		given:
		Violation v = Mock(Violation)
		Function fn = Mock(Function)
		def someObject = Mock(Object)

		when:
		def resultingFn = ViolationMessageUtil.basicRepresentation(v, fn)

		then:
		resultingFn != null
		resultingFn instanceof Function

		when:
		def result = resultingFn.apply(someObject)
		then:
		result == "Violation code: VIOLATION - on 'MYID'"
		and: 'interactions'
		1 * fn.apply(someObject) >> 'MYID'
		1 * v.getViolationCode() >> 'VIOLATION'
	}
}
