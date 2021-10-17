package simple

import static simple.Characters.*
import spock.lang.Specification

class SetValidationIT extends Specification {

	BaseComicsCharacter TASKMASTER = BaseComicsCharacter.builder()
					.id(HULK.id)
					.name('Anthony “Tony” Masters')
					.aliases(['Taksmaster', 'Captain America', 'Contingency T', 'Tony Masters'])
					.powers([])
					.durability(2)
					.energy(1)
					.fightingSkills(7)
					.intelligence(4)
					.speed(2)
					.strength(3)
					.build()

	def "SET_ID_UNIQUENESS accepts"() {
		expect:
		SetValidation.SET_ID_UNIQUENESS.inViolation([HULK, COLOSSUS, CABLE, SCARLET_WITCH, VISION]).isEmpty()

		SetValidation.SET_ID_UNIQUENESS.violationCode == 'SET_ID_UNIQUENESS'
	}

	def "SET_ID_UNIQUENESS rejects"() {
		expect:
		SetValidation.SET_ID_UNIQUENESS.inViolation([HULK, COLOSSUS, CABLE, SCARLET_WITCH, TASKMASTER, VISION]).get() ==
						SetValidation.SET_ID_UNIQUENESS
	}
}
