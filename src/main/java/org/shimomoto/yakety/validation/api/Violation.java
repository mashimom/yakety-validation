package org.shimomoto.yakety.validation.api;

import lombok.NonNull;

public interface Violation {
	@NonNull String getViolationCode();
}
