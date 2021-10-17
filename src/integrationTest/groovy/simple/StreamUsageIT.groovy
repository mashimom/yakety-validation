package simple

import static simple.Characters.*
import simple.api.ComicsCharacter
import spock.lang.Specification

class StreamUsageIT extends Specification {

	BaseComicsCharacter TASKMASTER = BaseComicsCharacter.builder()
					.id(UUID.randomUUID())
					.name('Anthony “Tony” Masters')
					.aliases(['Taksmaster', 'Captain America', 'Contingency T', 'Tony Masters'])
					.powers([null]) //null value
					.durability(2)
					.energy(1)
					.fightingSkills(7)
					.intelligence(4)
					.speed(2)
					.strength(3)
					.build()

	BaseComicsCharacter SPIDER_WOMAN = BaseComicsCharacter.builder()
					.id(UUID.randomUUID())
					.name('') //moved to aliases
					.aliases(['Jessica Drew', 'Spider-Woman', 'Arachne', 'Ariadne Hyde', 'Dark Angel', 'Dark Angel of San Francisco'])
					.powers(['Superhuman Strength', 'Superhuman Durability', 'Superhuman Speed', 'Superhuman Reflexes',
					         'Wallcrawling', 'Venom Blasts', 'Heightened Senses'])
					.durability(4)
					.energy(5)
					.fightingSkills(4)
					.intelligence(3)
					.speed(3)
					.strength(5)
					.build()

	BaseComicsCharacter GHOST_RIDER = BaseComicsCharacter.builder()
					.id(UUID.randomUUID())
					.name('Johnny Blaze')
					.aliases(['Ghost Rider', 'Johnathon "Johnny" Blaze', 'Brimstone Biker', 'Spirit of Vengeance',
					          'Stuntmaster', 'Ghost Biker', 'Frank Ryder'])
					.powers(['Superior Marksmanship', 'Superhuman Strength', 'Healing Factor', 'Pyrokinesis'])
					.durability(5)
					.energy(4)
					.fightingSkills(2)
					.intelligence(2)
					.speed(3)
					.strength(44) //typo
					.build()

	BaseComicsCharacter GHOST_RIDER_ROBBIE = BaseComicsCharacter.builder()
					.id(UUID.randomUUID())
					.name('Robbie Reyes')
					.aliases(['Ghost Rider', 'Roberto "Robbie" Reyes'])
					.powers(['Pyrokinesis', 'Healing Factor', 'Teleportation'])
					.durability(5)
					.energy(3)
					.fightingSkills(4)
					.intelligence(2)
					.speed(-3) //negated value
					.strength(4)
					.build()

	def validCharacters = [HULK, COLOSSUS, CABLE, SCARLET_WITCH, VISION]
	def invalidCharacters = [TASKMASTER, SPIDER_WOMAN, GHOST_RIDER, GHOST_RIDER_ROBBIE]

	def "All simple validations on valid set"() {
		expect:
		ValidationUsage.validate(validCharacters).isEmpty()
	}

	def "All simple validations on invalid set"() {
		when:
		def result = ValidationUsage.validate(invalidCharacters)

		then:
		!result.isEmpty()
		result == [(TASKMASTER.id)        : [SimpleValidation.POWERS_NON_NULL],
		           (SPIDER_WOMAN.id)      : [SimpleValidation.NAME_NON_EMPTY],
		           (GHOST_RIDER.id)       : [SimpleValidation.STRENGTH_IN_RANGE],
		           (GHOST_RIDER_ROBBIE.id): [SimpleValidation.SPEED_IN_RANGE]]
	}

	def "All simple validations on mixed set"() {
		given:
		List<ComicsCharacter> mixedCharacters = [*validCharacters, *invalidCharacters]
		when:
		Collections.shuffle(mixedCharacters, new Random(42))
		def result = ValidationUsage.validate(mixedCharacters)

		then:
		!result.isEmpty()
		result == [(TASKMASTER.id)        : [SimpleValidation.POWERS_NON_NULL],
		           (SPIDER_WOMAN.id)      : [SimpleValidation.NAME_NON_EMPTY],
		           (GHOST_RIDER.id)       : [SimpleValidation.STRENGTH_IN_RANGE],
		           (GHOST_RIDER_ROBBIE.id): [SimpleValidation.SPEED_IN_RANGE]]
	}

	def "setValidations accept when all valid"() {
		expect:
		ValidationUsage.validateSet(validCharacters).isEmpty()
	}

	def "setValidations reject"() {
		given:
		ComicsCharacter RED_HULK = BaseComicsCharacter.builder()
						.id(HULK.id) //id repeated
						.name("Thaddeus E. Ross")
						.aliases(List.of("Red Hulk", 'General Ross', '"Thunderbolt" Ross', 'Red Hulk', 'Rulk',
										'Red', 'Red man', 'Redeemer', 'Zzzax'))
						.powers(List.of("Healing Factor", "Superhuman Durability", "Superhuman Strength",
										"Size and Shape Alteration",))
						.durability(7)
						.energy(2)
						.fightingSkills(5)
						.intelligence(5)
						.speed(4)
						.strength(7)
						.build();
		List<ComicsCharacter> mixedCharacters = [*validCharacters, *invalidCharacters, RED_HULK]
		when:
		Collections.shuffle(mixedCharacters, new Random(42))
		def result = ValidationUsage.validateSet(mixedCharacters)

		then:
		result == [SetValidation.SET_ID_UNIQUENESS]
	}
}
