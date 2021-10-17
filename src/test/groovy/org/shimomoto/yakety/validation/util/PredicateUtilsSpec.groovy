package org.shimomoto.yakety.validation.util

import java.util.function.Function
import java.util.function.Predicate

import spock.lang.Specification

class PredicateUtilsSpec extends Specification {

	def "logic gates"() {
		expect:
		and.equals(a && b)
		nand.equals(!(a && b))
		or.equals(a || b)
		nor.equals(!(a || b))
		xor.equals((a && !b) || (!a && b))
		xnor.equals((!a && !b) || (a && b))

		where:
		_ | a     | b     | and   | nand  | or    | nor   | xor   | xnor
		0 | false | false | false | true  | false | true  | false | true
		1 | false | true  | false | true  | true  | false | true  | false
		2 | true  | false | false | true  | true  | false | true  | false
		3 | true  | true  | true  | false | true  | false | false | true
	}

	def "predicate logic gates"() {
		given:
		def p1 = { a }
		def p2 = { b }
		expect:
		and.equals(PredicateUtils.and(p1, p2).test(""))
		nand.equals(PredicateUtils.nand(p1, p2).test(""))
		or.equals(PredicateUtils.or(p1, p2).test(""))
		nor.equals(PredicateUtils.nor(p1, p2).test(""))
		xor.equals(PredicateUtils.xor(p1, p2).test(""))
		xnor.equals(PredicateUtils.xnor(p1, p2).test(""))

		where:
		_ | a     | b     | and   | nand  | or    | nor   | xor   | xnor
		0 | false | false | false | true  | false | true  | false | true
		1 | false | true  | false | true  | true  | false | true  | false
		2 | true  | false | false | true  | true  | false | true  | false
		3 | true  | true  | true  | false | true  | false | false | true
	}

	def "Derived predicate works"() {
		given:
		Function<String, Integer> fn = (String s) -> Integer.valueOf(s.length())
		Predicate<Integer> p = (Integer i) -> i == 0
		when:
		def dp = PredicateUtils.derivedPredicate(p, fn)

		then:
		dp.test("")
		!dp.test("abc")
	}
}
