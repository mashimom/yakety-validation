package org.shimomoto.yakety.validation.util

import spock.lang.Specification

import java.util.function.Function

class AggregateSetUtilsSpec extends Specification {
    private idFn = { s -> s[0] } //Fist char in string
    private fieldFn = { s -> s[-1] }//last char in string

    def "getDuplicates fails on null"() {
        when:
        AggregateSetUtils.getDuplicates(null)

        then:
        thrown IllegalArgumentException
    }

    def "getDuplicates works on unique items"() {
        given:
        def unique = ['a', 'b', 'c']

        expect:
        AggregateSetUtils.getDuplicates(unique) == [].toSet()
    }

    def "getDuplicates works on duped items"() {
        given:
        def dupes = ['c', 'a', 'b', 'c']

        expect:
        AggregateSetUtils.getDuplicates(dupes) == ['c'].toSet()
    }

    def "getDuplicateIds fails on null"() {
        when:
        AggregateSetUtils.getDuplicateIds(null, idFn)

        then:
        thrown IllegalArgumentException
    }

    def "getDuplicateIds fails on null id function"() {
        when:
        AggregateSetUtils.getDuplicateIds([], null)

        then:
        thrown IllegalArgumentException
    }

    def "getDuplicateIds works on unique items"() {
        given:
        def unique = ['Abba', 'Black Sabbath', 'Camera Obscura']

        expect:
        AggregateSetUtils.getDuplicateIds(unique, idFn) == [].toSet()
    }

    def "getDuplicateIds works on duped items"() {
        given:
        def dupes = ['Cibelle', 'Abba', 'Black Sabbath', 'Camera Obscura']

        expect:
        AggregateSetUtils.getDuplicateIds(dupes, idFn) == ['C'].toSet()
    }

    def "getIdsOfDuplicateField fails on null"() {
        when:
        AggregateSetUtils.getIdsOfDuplicateField(null, idFn, fieldFn)

        then:
        thrown IllegalArgumentException
    }

    def "getIdsOfDuplicateField fails on null id function"() {
        when:
        AggregateSetUtils.getIdsOfDuplicateField([], null, fieldFn)

        then:
        thrown IllegalArgumentException
    }

    def "getIdsOfDuplicateField fail on null field getter"() {
        when:
        AggregateSetUtils.getIdsOfDuplicateField([], idFn, null)

        then:
        thrown IllegalArgumentException
    }

    def "getIdsOfDuplicateField works on unique items"() {
        given:
        def unique = ['Abba', 'Black Sabbath', 'Cranberries']

        expect:
        AggregateSetUtils.getIdsOfDuplicateField(unique, idFn, fieldFn) == [].toSet()
    }

    def "getIdsOfDuplicateField works on duped items"() {
        given:
        def dupes = ['Abba', 'Black Sabbath', 'Camera Obscura']

        expect:
        AggregateSetUtils.getIdsOfDuplicateField(dupes, idFn, fieldFn) == ['A', 'C'].toSet()
    }
}
