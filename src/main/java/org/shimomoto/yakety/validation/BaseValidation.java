package org.shimomoto.yakety.validation;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.shimomoto.yakety.validation.api.Validation;
import org.shimomoto.yakety.validation.api.Violation;

import java.util.Optional;
import java.util.function.Predicate;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class BaseValidation<T> implements Validation<T> {
	@NonNull
	private final Predicate<T> pred;
	@NonNull
	private final Violation violation;

	@Override
	public Optional<Violation> inViolation(final T t) {
		return this.test(t) ? Optional.of(violation) : Optional.empty();
	}

	@Override
	public boolean test(final T t) {
		return pred.test(t);
	}
}
