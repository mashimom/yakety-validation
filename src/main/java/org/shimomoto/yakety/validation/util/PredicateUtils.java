package org.shimomoto.yakety.validation.util;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.function.Function;
import java.util.function.Predicate;

@UtilityClass
public class PredicateUtils {

	public static <T, F> Predicate<@NonNull T> derivedPredicate(final Predicate<F> predicate, final Function<@NonNull T, F> getter) {
		return t -> predicate.test(getter.apply(t));
	}

	public static <T> Predicate<T> and(final Predicate<T> p1, final Predicate<T> p2) {
		return p1.and(p2);
	}

	public static <T> Predicate<T> or(final Predicate<T> p1, final Predicate<T> p2) {
		return p1.or(p2);
	}

	public static <T> Predicate<T> nand(final Predicate<T> p1, final Predicate<T> p2) {
		return (p1.and(p2)).negate();
	}

	public static <T> Predicate<T> nor(final Predicate<T> p1, final Predicate<T> p2) {
		return (p1.or(p2)).negate();
	}

	public static <T> Predicate<T> xor(final Predicate<T> p1, final Predicate<T> p2) {
		return (p1.and(p2.negate()))
						.or(p1.negate().and(p2));
	}

	public static <T> Predicate<T> xnor(final Predicate<T> p1, final Predicate<T> p2) {
		return (p1.and(p2))
						.or(p1.negate().and(p2.negate()));
	}
}
