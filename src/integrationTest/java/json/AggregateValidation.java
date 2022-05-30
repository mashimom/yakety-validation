package json;

import com.fasterxml.jackson.databind.JsonNode;
import json.model.AggregateUtils;
import json.model.MovieRatings;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.shimomoto.yakety.validation.ValidationFactory;
import org.shimomoto.yakety.validation.api.Validation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
import java.util.function.Predicate;

@UtilityClass
public class AggregateValidation {

	public static final EnumSet<MovieRatings> ALL_UNRATED_RATINGS = EnumSet.of(MovieRatings.N_A, MovieRatings.NOT_RATED);
	private static final String IS_UNRATED_AND_RELEASED = "IS_UNRATED_AND_RELEASED";
	private static final String IS_UNRATED = "IS_UNRATED";

	public static @NotNull Validation<JsonNode> getReleasedAfter(@NotNull final LocalDate dateLimit) {
		return ValidationFactory.forAggregateField(
						AggregateUtils::unsafeParseRelease,
						dateLimit::isAfter,
						"IS_RELEASED_AFTER_LIMIT"+dateLimit.format(DateTimeFormatter.ISO_LOCAL_DATE));
	}

	public static @NotNull Validation<JsonNode> getUnrated() {
		return ValidationFactory.forAggregateField(
				AggregateUtils::unsafeParseRating,
				ALL_UNRATED_RATINGS::contains,
				IS_UNRATED);
	}

	public static @NotNull Validation<JsonNode> getUnratedAndReleasedAfter(@NotNull final LocalDate dateLimit) {
		final Predicate<LocalDate> predicate = ((Predicate<LocalDate>) dateLimit::isAfter).negate();
		return ValidationFactory.forAggregateField(
						AggregateUtils::unsafeParseRelease,
						predicate,
						IS_UNRATED_AND_RELEASED);
	}
}
