package org.shimomoto.yakety.validation;

import lombok.*;
import org.shimomoto.yakety.validation.api.Validation;
import org.shimomoto.yakety.validation.api.Violation;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode
class BaseNonNullValidation<T> implements Validation<T>, Violation {
	@NonNull String violationCode;

	@Override
	public Optional<Violation> inViolation(final T t) {
		return this.test(t) ? Optional.of(this) : Optional.empty();
	}

	@Override
	public boolean test(final T t) {
		return Objects.isNull(t);
	}
}
