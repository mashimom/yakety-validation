package org.shimomoto.yakety.validation.api;

import lombok.NonNull;

import java.util.Set;

public interface SetViolation<I> extends Violation {
	@NonNull Set<I> getSetIds();
}
