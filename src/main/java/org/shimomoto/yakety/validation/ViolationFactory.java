package org.shimomoto.yakety.validation;

import lombok.experimental.UtilityClass;
import org.shimomoto.yakety.validation.api.Violation;

@UtilityClass
public class ViolationFactory {

	public static Violation simple(final String violationCode) {
		return BaseViolation.build(violationCode);
	}
}
