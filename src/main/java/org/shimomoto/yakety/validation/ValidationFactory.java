package org.shimomoto.yakety.validation;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.shimomoto.yakety.validation.api.Validation;

import java.util.function.Function;
import java.util.function.Predicate;

import static org.shimomoto.yakety.validation.util.PredicateUtils.derivedPredicate;

@UtilityClass
public class ValidationFactory {

	public static final String VIOLATION_CODE_CANNOT_BE_BLANK_OR_NULL = "Violation code cannot be blank or null";

	public static <T> Validation<T> nonNull(final @NonNull String violationCode) {
		if (violationCode.isBlank()) {
			throw new IllegalArgumentException(VIOLATION_CODE_CANNOT_BE_BLANK_OR_NULL);
		}
		return new BaseNonNullValidation<>(violationCode);
	}

	public static <T, F> Validation<T> nonNullAggregateField(final @NonNull Function<T, F> getter,
	                                                         final @NonNull String violationCode) {
		if (violationCode.isBlank()) {
			throw new IllegalArgumentException(VIOLATION_CODE_CANNOT_BE_BLANK_OR_NULL);
		}
		return new BaseNonNullFieldValidation<>(getter, violationCode);
	}

	public static <T> Validation<T> simple(final @NonNull Predicate<T> predicate,
	                                       final @NonNull String violationCode) {
		if (violationCode.isBlank()) {
			throw new IllegalArgumentException(VIOLATION_CODE_CANNOT_BE_BLANK_OR_NULL);
		}
		return new BaseValidation<>(predicate, BaseViolation.build(violationCode));
	}

	public static <T, F> Validation<T> forAggregateField(final @NonNull Function<T, F> getter,
	                                                     final @NonNull Predicate<F> predicate,
	                                                     final @NonNull String violationCode) {
		if (violationCode.isBlank()) {
			throw new IllegalArgumentException(VIOLATION_CODE_CANNOT_BE_BLANK_OR_NULL);
		}
		return new BaseValidation<>(
						derivedPredicate(predicate, getter),
						BaseViolation.build(violationCode));
	}
}
