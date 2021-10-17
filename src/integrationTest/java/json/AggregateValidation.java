package json;

import com.fasterxml.jackson.databind.JsonNode;
import json.model.AggregateUtils;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.shimomoto.yakety.validation.ValidationFactory;
import org.shimomoto.yakety.validation.api.Validation;

import java.time.LocalDate;
import java.util.function.Predicate;

@UtilityClass
public class AggregateValidation {

	private static final String IS_UNRATED_AND_RELEASED = "IS_UNRATED_AND_RELEASED";

	public static @NotNull Validation<JsonNode> getReleasedAfter(@NotNull final LocalDate dateLimit) {
		return ValidationFactory.forAggregateField(
						AggregateUtils::unsafeParseRelease,
						dateLimit::isAfter,
						IS_UNRATED_AND_RELEASED);
	}

	public static @NotNull Validation<JsonNode> getUnratedAndReleasedAfter(@NotNull final LocalDate dateLimit) {
		final Predicate<LocalDate> predicate = ((Predicate<LocalDate>) dateLimit::isEqual).or(dateLimit::isBefore);
		return ValidationFactory.forAggregateField(
						AggregateUtils::unsafeParseRelease,
						predicate,
						IS_UNRATED_AND_RELEASED);
	}
}
