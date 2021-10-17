package simple;

import org.shimomoto.yakety.validation.api.Validation;
import org.shimomoto.yakety.validation.api.Violation;
import org.shimomoto.yakety.validation.util.ValidationUtil;
import simple.api.ComicsCharacter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ValidationUsage {
	public static List<Validation<ComicsCharacter>> simpleValidations() {
		return Arrays.stream(SimpleValidation.values()).collect(Collectors.toList());
	}

	public static List<Validation<List<ComicsCharacter>>> setValidations() {
		return List.of(SetValidation.SET_ID_UNIQUENESS);
	}

	public static Map<UUID, List<Violation>> validate(final List<ComicsCharacter> ccList) {
		return ValidationUtil.validateMany(ccList, ComicsCharacter::getId, simpleValidations());
	}

	public static List<Violation> validateSet(final List<ComicsCharacter> ccList) {
		return ValidationUtil.validateOne(ccList, setValidations());
	}
}
