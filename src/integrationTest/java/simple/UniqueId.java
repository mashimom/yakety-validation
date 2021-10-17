package simple;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.shimomoto.yakety.validation.api.Validation;
import org.shimomoto.yakety.validation.api.Violation;
import simple.api.ComicsCharacter;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.shimomoto.yakety.validation.util.AggregateSetUtils.getDuplicateIds;

@NoArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
final class UniqueId implements Violation, Validation<List<ComicsCharacter>> {
	String violationCode = "SET_ID_UNIQUENESS";

	@Override
	public Optional<Violation> inViolation(@NotNull final List<ComicsCharacter> comicsCharacters) {
		return test(comicsCharacters) ? Optional.empty() : Optional.of(this);
	}

	@Override
	public boolean test(final List<ComicsCharacter> comicsCharacters) {
		return getDuplicateIdSet(comicsCharacters).isEmpty();
	}

	private Set<UUID> getDuplicateIdSet(final List<ComicsCharacter> comicsCharacters) {
		return getDuplicateIds(comicsCharacters, ComicsCharacter::getId);
	}
}
