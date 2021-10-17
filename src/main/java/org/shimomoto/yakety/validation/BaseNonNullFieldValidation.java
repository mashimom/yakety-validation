package org.shimomoto.yakety.validation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.shimomoto.yakety.validation.api.Validation;
import org.shimomoto.yakety.validation.api.Violation;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class BaseNonNullFieldValidation<T> implements Validation<T>, Violation {
	@NonNull
	private final Function<T, ?> getter;
	@Getter
	@NonNull
	private final String violationCode;

	@Override
	public Optional<Violation> inViolation(final T t) {
		return this.test(t) ? Optional.of(this) : Optional.empty();
	}

	@Override
	public boolean test(final T t) {
		return Objects.isNull(getter.apply(t));
	}
}
