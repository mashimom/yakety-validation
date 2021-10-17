package org.shimomoto.yakety.validation.util;

import com.codepoetics.protonpack.maps.MapStream;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.shimomoto.yakety.validation.api.Validation;
import org.shimomoto.yakety.validation.api.Violation;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@UtilityClass
public class ValidationUtil {

	public static <T> List<Violation> validateOne(
					final @NonNull T subject,
					final @NonNull List<Validation<T>> validations) {

		return validations.stream()
						.map(v -> v.inViolation(subject))
						.flatMap(Optional::stream)
						.collect(Collectors.toList());
	}

	public static <T, I> Map<I, List<Violation>> validateMany(
					final @NonNull List<T> subjects,
					final @NonNull Function<T, I> getIdFunction,
					final @NonNull List<Validation<T>> validations) {

		return MapStream.of(subjects.stream()
										.collect(Collectors.toMap(
														getIdFunction,
														s -> ValidationUtil.validateOne(s, validations))))
						.filter(e -> !e.getValue().isEmpty())
						.collect();
	}
}
