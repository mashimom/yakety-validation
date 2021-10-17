package json.predicates;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

@UtilityClass
public class DatePredicates {

	public static final Pattern ISO_8601_DATE = Pattern.compile("/^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/");

	public static boolean isIso8601DateWellFormed(final String value) {
		return ISO_8601_DATE.asMatchPredicate().test(value);
	}

	public static boolean isIso8601DateValid(final String value) {
		try {
			LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
			return true;
		} catch (final DateTimeParseException e) {
			//should never have thrown anyway
		}
		return false;
	}
}
