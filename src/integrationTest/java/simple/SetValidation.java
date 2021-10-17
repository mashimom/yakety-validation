package simple;

import lombok.experimental.UtilityClass;
import org.shimomoto.yakety.validation.api.Validation;
import simple.api.ComicsCharacter;

import java.util.List;

@UtilityClass
public class SetValidation {
	public static final Validation<List<ComicsCharacter>> SET_ID_UNIQUENESS = new UniqueId();
}
