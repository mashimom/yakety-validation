package simple

import java.util.stream.Collectors

import static simple.Characters.HULK
import static simple.SimpleValidation.*
import spock.lang.Specification


class SimpleIT extends Specification {

	def "ID_NON_NULL works"() {
		BaseComicsCharacter badHulk = ((BaseComicsCharacter) HULK).toBuilder()
						.id(null)
						.build()
		expect:
		ID_NON_NULL.inViolation(badHulk) == Optional.of(ID_NON_NULL)

		ID_NON_NULL.inViolation(HULK).isEmpty()

		ID_NON_NULL.violationCode == 'ID_NON_NULL'
	}

	def "NAME_NON_NULL works"() {
		BaseComicsCharacter badHulk = ((BaseComicsCharacter) HULK).toBuilder()
						.name(null)
						.build()
		expect:
		NAME_NON_NULL.inViolation(badHulk) == Optional.of(NAME_NON_NULL)

		NAME_NON_NULL.inViolation(HULK).isEmpty()

		NAME_NON_NULL.violationCode == 'NAME_NON_NULL'
	}

	def "NAME_NON_EMPTY works"() {
		BaseComicsCharacter badHulk = ((BaseComicsCharacter) HULK).toBuilder()
						.name(" \t  \n")
						.build()
		expect:
		NAME_NON_EMPTY.inViolation(badHulk) == Optional.of(NAME_NON_EMPTY)

		NAME_NON_EMPTY.inViolation(HULK).isEmpty()

		NAME_NON_EMPTY.violationCode == 'NAME_NON_EMPTY'
	}

	def "NON_EMPTY_ALIASES works"() {
		BaseComicsCharacter badHulk = ((BaseComicsCharacter) HULK).toBuilder()
						.aliases(Collections.emptyList())
						.build()
		expect:
		NON_EMPTY_ALIASES.inViolation(badHulk) == Optional.of(NON_EMPTY_ALIASES)

		NON_EMPTY_ALIASES.inViolation(HULK).isEmpty()

		NON_EMPTY_ALIASES.violationCode == 'NON_EMPTY_ALIASES'
	}

	def "ALIAS_NON_NULL works"() {
		BaseComicsCharacter badHulk = ((BaseComicsCharacter) HULK).toBuilder()
						.aliases(["GREEN", "ANGRY", null, "BIG"].stream().collect(Collectors.toList()))
						.build()
		expect:
		ALIAS_NON_NULL.inViolation(badHulk) == Optional.of(ALIAS_NON_NULL)

		ALIAS_NON_NULL.inViolation(HULK).isEmpty()

		ALIAS_NON_NULL.violationCode == 'ALIAS_NON_NULL'
	}

	def "ALIAS_NON_EMPTY works"() {
		BaseComicsCharacter badHulk = ((BaseComicsCharacter) HULK).toBuilder()
						.aliases(List.of("GREEN", "ANGRY", " \n  \t \r", "BIG"))
						.build()
		expect:
		ALIAS_NON_EMPTY.inViolation(badHulk) == Optional.of(ALIAS_NON_EMPTY)

		ALIAS_NON_EMPTY.inViolation(HULK).isEmpty()

		ALIAS_NON_EMPTY.violationCode == 'ALIAS_NON_EMPTY'
	}

	def "POWERS_NON_NULL works"() {
		BaseComicsCharacter badHulk = ((BaseComicsCharacter) HULK).toBuilder()
						.powers(["GREEN", "ANGRY", null, "BIG"].stream().collect(Collectors.toList()))
						.build()
		expect:
		POWERS_NON_NULL.inViolation(badHulk) == Optional.of(POWERS_NON_NULL)

		POWERS_NON_NULL.inViolation(HULK).isEmpty()

		POWERS_NON_NULL.violationCode == 'POWERS_NON_NULL'
	}

	def "POWERS_NON_EMPTY works"() {
		BaseComicsCharacter badHulk = ((BaseComicsCharacter) HULK).toBuilder()
						.powers(List.of("GREEN", "ANGRY", " \n  \t \r", "BIG"))
						.build()
		expect:
		POWERS_NON_EMPTY.inViolation(badHulk) == Optional.of(POWERS_NON_EMPTY)

		POWERS_NON_EMPTY.inViolation(HULK).isEmpty()

		POWERS_NON_EMPTY.violationCode == 'POWERS_NON_EMPTY'
	}

	def "DURABILITY_IN_RANGE accepts"() {
		expect:
		DURABILITY_IN_RANGE.inViolation(HULK).isEmpty()

		DURABILITY_IN_RANGE.violationCode == 'DURABILITY_IN_RANGE'
	}

	def "DURABILITY_IN_RANGE rejects"() {
		given:
		BaseComicsCharacter badHulk = ((BaseComicsCharacter) HULK).toBuilder()
						.durability(v)
						.build();
		expect:
		DURABILITY_IN_RANGE.inViolation(badHulk) == Optional.of(DURABILITY_IN_RANGE)

		where:
		_ | v
		0 | -1
		1 | 8
	}

	def "ENERGY_IN_RANGE accepts"() {
		expect:
		ENERGY_IN_RANGE.inViolation(HULK).isEmpty()

		ENERGY_IN_RANGE.violationCode == 'ENERGY_IN_RANGE'
	}

	def "ENERGY_IN_RANGE rejects"() {
		given:
		BaseComicsCharacter badHulk = ((BaseComicsCharacter) HULK).toBuilder()
						.energy(v)
						.build();
		expect:
		ENERGY_IN_RANGE.inViolation(badHulk) == Optional.of(ENERGY_IN_RANGE)

		where:
		_ | v
		0 | -1
		1 | 8
	}

	def "FIGHT_IN_RANGE accepts"() {
		expect:
		FIGHT_IN_RANGE.inViolation(HULK).isEmpty()

		FIGHT_IN_RANGE.violationCode == 'FIGHT_IN_RANGE'
	}

	def "FIGHT_IN_RANGE rejects"() {
		given:
		BaseComicsCharacter badHulk = ((BaseComicsCharacter) HULK).toBuilder()
						.fightingSkills(v)
						.build();
		expect:
		FIGHT_IN_RANGE.inViolation(badHulk) == Optional.of(FIGHT_IN_RANGE)

		where:
		_ | v
		0 | -1
		1 | 8
	}

	def "INTELLIGENCE_IN_RANGE accepts"() {
		expect:
		INTELLIGENCE_IN_RANGE.inViolation(HULK).isEmpty()

		INTELLIGENCE_IN_RANGE.violationCode == 'INTELLIGENCE_IN_RANGE'
	}

	def "INTELLIGENCE_IN_RANGE rejects"() {
		given:
		BaseComicsCharacter badHulk = ((BaseComicsCharacter) HULK).toBuilder()
						.intelligence(v)
						.build();
		expect:
		INTELLIGENCE_IN_RANGE.inViolation(badHulk) == Optional.of(INTELLIGENCE_IN_RANGE)

		where:
		_ | v
		0 | -1
		1 | 8
	}

	def "SPEED_IN_RANGE accepts"() {
		expect:
		SPEED_IN_RANGE.inViolation(HULK).isEmpty()

		SPEED_IN_RANGE.violationCode == 'SPEED_IN_RANGE'
	}

	def "SPEED_IN_RANGE rejects"() {
		given:
		BaseComicsCharacter badHulk = ((BaseComicsCharacter) HULK).toBuilder()
						.speed(v)
						.build();
		expect:
		SPEED_IN_RANGE.inViolation(badHulk) == Optional.of(SPEED_IN_RANGE)

		where:
		_ | v
		0 | -1
		1 | 8
	}

	def "STRENGTH_IN_RANGE accepts"() {
		expect:
		STRENGTH_IN_RANGE.inViolation(HULK).isEmpty()

		STRENGTH_IN_RANGE.violationCode == 'STRENGTH_IN_RANGE'
	}

	def "STRENGTH_IN_RANGE rejects"() {
		given:
		BaseComicsCharacter badHulk = ((BaseComicsCharacter) HULK).toBuilder()
						.strength(v)
						.build();
		expect:
		STRENGTH_IN_RANGE.inViolation(badHulk) == Optional.of(STRENGTH_IN_RANGE)

		where:
		_ | v
		0 | -1
		1 | 8
	}
}
