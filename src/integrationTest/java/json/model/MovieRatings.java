package json.model;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public enum MovieRatings implements Predicate<String>, HasExternalValue {
	G("G"),
	PG("PG"),
	N_A("N/A"),
	NOT_RATED("Not rated");

	@Getter
	public final String externalValue;

	MovieRatings(final String value) {
		this.externalValue = value;
	}

	public static boolean contains(final @Nullable String value) {
		return Arrays.stream(MovieRatings.values())
						.anyMatch(mr -> mr.test(value));
	}

	@Override
	public boolean test(final @Nullable String s) {
		return externalValue.equalsIgnoreCase(s);
	}

	public static Optional<MovieRatings> parse(final @Nullable String value) {
		return Arrays.stream(MovieRatings.values())
				.filter(mr -> mr.test(value))
				.findFirst();
	}
}
