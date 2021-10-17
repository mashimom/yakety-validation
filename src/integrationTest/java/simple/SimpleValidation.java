package simple;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.shimomoto.yakety.validation.api.Validation;
import org.shimomoto.yakety.validation.api.Violation;
import simple.api.ComicsCharacter;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public enum SimpleValidation implements Violation, Validation<ComicsCharacter> {
	ID_NON_NULL(cc -> Objects.nonNull(cc.getId())),
	NAME_NON_NULL(cc -> Objects.nonNull(cc.getName())),
	NAME_NON_EMPTY(cc -> !cc.getName().isBlank()),
	NON_EMPTY_ALIASES(cc -> !cc.getAliases().isEmpty()),
	ALIAS_NON_NULL(cc -> cc.getAliases().stream().noneMatch(Objects::isNull)),
	ALIAS_NON_EMPTY(cc -> cc.getAliases().stream()
					.filter(Objects::nonNull)
					.map(String::trim)
					.noneMatch(String::isEmpty)),
	POWERS_NON_NULL(cc -> cc.getPowers().stream()
					.noneMatch(Objects::isNull)),
	POWERS_NON_EMPTY(cc -> cc.getPowers().stream()
					.filter(Objects::nonNull)
					.map(String::trim)
					.noneMatch(String::isEmpty)),
	DURABILITY_IN_RANGE(cc -> cc.getDurability() >= 0 && cc.getDurability() <= 7),
	ENERGY_IN_RANGE(cc -> cc.getEnergy() >= 0 && cc.getEnergy() <= 7),
	FIGHT_IN_RANGE(cc -> cc.getFightingSkills() >= 0 && cc.getFightingSkills() <= 7),
	INTELLIGENCE_IN_RANGE(cc -> cc.getIntelligence() >= 0 && cc.getIntelligence() <= 7),
	SPEED_IN_RANGE(cc -> cc.getSpeed() >= 0 && cc.getSpeed() <= 7),
	STRENGTH_IN_RANGE(cc -> cc.getStrength() >= 0 && cc.getStrength() <= 7);

	final Predicate<ComicsCharacter> predicate;

	SimpleValidation(final Predicate<ComicsCharacter> predicate) {
		this.predicate = predicate;
	}

	@Override
	public Optional<Violation> inViolation(@Nullable final ComicsCharacter comicsCharacter) {
		return predicate.test(comicsCharacter) ? Optional.empty() : Optional.of(this);
	}

	@Override
	public @NotNull String getViolationCode() {
		return this.toString();
	}

	@Override
	public boolean test(final ComicsCharacter comicsCharacter) {
		return predicate.test(comicsCharacter);
	}
}
