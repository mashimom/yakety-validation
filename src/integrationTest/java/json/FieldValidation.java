package json;

import com.fasterxml.jackson.databind.JsonNode;
import json.model.MovieRatings;
import json.predicates.DatePredicates;
import json.predicates.IntegralPredicates;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.shimomoto.yakety.validation.api.Validation;
import org.shimomoto.yakety.validation.api.Violation;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public enum FieldValidation implements Violation, Validation<JsonNode> {
	ID_EMPTY("id", s -> s != null && !s.isBlank()),
	TITLE_EMPTY("title", s -> s != null && !s.isBlank()),
	RELEASE_DATE_EMPTY("releaseDate", s -> s != null && !s.isBlank()),
	RELEASE_DATE_MALFORMED("releaseDate", DatePredicates::isIso8601DateWellFormed),
	RELEASE_DATE_INVALID("releaseDate", DatePredicates::isIso8601DateValid),
	RUN_TIME_EMPTY("runTime", s -> s != null && !s.isBlank()),
	RUN_TIME_NA_OR_UNSIGNED_INTEGRAL("runTime", ((Predicate<String>) "NA"::equals).or(IntegralPredicates::isUnsignedIntegral)),
	RATING_EMPTY("rating", s -> s != null && !s.isBlank()),
	RATING_OUT_OF_RANGE("rating", MovieRatings::contains);

	final String fieldName;
	final Predicate<String> predicate;
	final Function<JsonNode, String> getter;

	FieldValidation(final String fieldName, final Predicate<String> predicate, final Function<JsonNode, String> getter) {
		this.fieldName = fieldName;
		this.predicate = predicate;
		this.getter = getter;
	}

	FieldValidation(final String fieldName, final Predicate<String> predicate) {
		this(fieldName, predicate, (JsonNode node) -> node.get(fieldName).asText());
	}

	@Override
	public Optional<Violation> inViolation(@Nullable final JsonNode node) {
		return predicate.test(getter.apply(node)) ? Optional.empty() : Optional.of(this);
	}

	@Override
	public @NotNull String getViolationCode() {
		return this.toString();
	}

	@Override
	public boolean test(final JsonNode jsonNode) {
		return predicate.test(jsonNode.get(fieldName).asText());
	}
}
