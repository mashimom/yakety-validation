package json.model;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.function.Predicate;

public enum MovieRatings implements Predicate<String> {
	G("G"),
	PG("PG"),
	N_A("N/A"),
	NOT_RATED("Not rated");

	public final String VALUE;

	MovieRatings(final String value) {
		this.VALUE = value;
	}

	public static boolean contains(final @Nullable String value) {
		return Arrays.stream(MovieRatings.values())
						.anyMatch(mr -> mr.test(value));
	}

	@Override
	public boolean test(final @Nullable String s) {
		return VALUE.equalsIgnoreCase(s);
	}
}
