package org.shimomoto.yakety.validation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.shimomoto.yakety.validation.api.Violation;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
class BaseViolation implements Violation {
	@NonNull
	private final String violationCode;

	static Violation build(final String violationCode) {
		if (violationCode == null || violationCode.isBlank()) {
			throw new IllegalArgumentException("Identifier must be an non-blank String");
		}
		return new BaseViolation(violationCode);
	}
}
