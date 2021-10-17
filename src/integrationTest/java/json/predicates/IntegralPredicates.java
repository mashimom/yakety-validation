package json.predicates;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
@UtilityClass
public class IntegralPredicates {
	public static final Pattern UNSIGNED_INTEGRAL = Pattern.compile("/d+");

	public static boolean isUnsignedIntegral(final String value) {
		return UNSIGNED_INTEGRAL.asMatchPredicate().test(value);
	}
}
