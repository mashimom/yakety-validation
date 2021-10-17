package json.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@UtilityClass
public class AggregateUtils {

	public static Optional<LocalDate> parseRelease(final JsonNode movie) {
		return Optional.ofNullable(movie)
						.map(jn -> jn.get("releaseDate"))
						.map(JsonNode::asText)
						.map(AggregateUtils::parseLocalDate);
	}

	@Nullable
	public static LocalDate unsafeParseRelease(final JsonNode movie) {
		return parseRelease(movie)
						.orElse(null);
	}

	@Nullable
	private static LocalDate parseLocalDate(final @NotNull String s) {
		try {
			return LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE);
		} catch (final DateTimeParseException e) {
			//should have never thrown anyway
		}
		return null;
	}
}
