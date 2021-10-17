package org.shimomoto.yakety.validation.api;

import java.util.Optional;
import java.util.function.Predicate;

public interface Validation<T> extends Predicate<T> {

	Optional<Violation> inViolation(T t);
}
