package org.shimomoto.yakety.validation.util

import spock.lang.IgnoreRest
import spock.lang.Shared
import spock.lang.Specification

import java.util.function.Function
import java.util.function.Predicate

class PredicateUtilsSpec extends Specification {

    class TruePredicate<T> implements Predicate<T> {
        @Override
        boolean test(T t) {
            return true
        }
    }

    class FalsePredicate<T> implements Predicate<T> {
        @Override
        boolean test(T t) {
            return false
        }
    }

    @Shared
    def truePred = new TruePredicate()
    @Shared
    def falsePred = new FalsePredicate()

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

    def "logic gates"() {
        expect:
        and == (a && b)
        nand == (!(a && b))
        or == (a || b)
        nor == (!(a || b))
        xor == ((a && !b) || (!a && b))
        xnor == ((!a && !b) || (a && b))

        where:
        _ | a     | b     | and   | nand  | or    | nor   | xor   | xnor
        0 | false | false | false | true  | false | true  | false | true
        1 | false | true  | false | true  | true  | false | true  | false
        2 | true  | false | false | true  | true  | false | true  | false
        3 | true  | true  | true  | false | true  | false | false | true
    }

    def "predicate logic gates"() {
        expect:
        and == PredicateUtils.<String> and(p1, p2).test("")
        nand == PredicateUtils.<String> nand(p1, p2).test("")
        or == PredicateUtils.<String> or(p1, p2).test("")
        nor == PredicateUtils.<String> nor(p1, p2).test("")
        xor == PredicateUtils.<String> xor(p1, p2).test("")
        xnor == PredicateUtils.<String> xnor(p1, p2).test("")

        where:
        _ | p1        | p2        | and   | nand  | or    | nor   | xor   | xnor
        0 | (falsePred) | (falsePred) | false | true  | false | true  | false | true
        1 | (falsePred) | (truePred)  | false | true  | true  | false | true  | false
        2 | (truePred)  | (falsePred) | false | true  | true  | false | true  | false
        3 | (truePred)  | (truePred)  | true  | false | true  | false | false | true
    }
}
