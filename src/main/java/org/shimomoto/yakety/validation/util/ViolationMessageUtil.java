package org.shimomoto.yakety.validation.util;

import lombok.experimental.UtilityClass;
import org.shimomoto.yakety.validation.api.Violation;

import java.util.function.Function;

@UtilityClass
public class ViolationMessageUtil {

	public static <V extends Violation, T, I> Function<T, String> basicRepresentation(final V violation, final Function<T, I> idGetter) {
		return (T t) -> String.format("Violation code: %s - on '%s'", violation.getViolationCode(), idGetter.apply(t));
	}
}
