package simple;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import simple.api.ComicsCharacter;

import java.util.List;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
@UtilityClass
public class Characters {
	public static final ComicsCharacter HULK = BaseComicsCharacter.builder()
					.id(UUID.fromString("586f3ca5-0f5e-4727-bce1-71291a73738a"))
					.name("Bruce Banner")
					.aliases(List.of("Hulk", "Robert Bruce Banner", "Breaker of Worlds", "Patient #133", "Green King",
									"Green Scar", "Holku", "Sakaarson", "Green One", "Eye of Rage", "World Breaker", "Harkanon", "Haarg",
									"Once-Savage", "Two-Minds", "Captain Universe", "Professor", "War", "Maestro", "Joe Fixit",
									"Mr. Fixit", "Annihilator", "Mechano", "Bruce Barnes", "Bruce Smith", "Glenn Summers",
									"Ross Oppenheimer", "Fred Pottsworth", "Bob Danner", "Bruce Ross", "Bruce Jones", "Bruce Roberts",
									"Mr. Bergen", "Bruce Franklin", "Bruce Green", "Bruce Bancroft", "Bruce Baxter", "Bruce Davidson",
									"David Bannon", "Robert Baker", "Bruce Bixby", "David Banner", "David Bixby", "Two-Hands",
									"Greenskin", "Mr. Green", "Jade Jaws", "Green Goliath", "Jade Giant", "Gray Goliath", "Mighty Bob",
									"Anti-Hulk", "Friday", "Green Golem", "Golem"))
					.powers(List.of("Healing Factor", "Superhuman Durability", "Superhuman Strength",
									"Size and Shape Alteration", "Genius Intelligence"))
					.durability(7)
					.energy(5)
					.fightingSkills(4)
					.intelligence(6)
					.speed(3)
					.strength(7)
					.build();
	public static final ComicsCharacter COLOSSUS = BaseComicsCharacter.builder()
					.id(UUID.fromString("1f94d1c5-d0f8-489e-95e3-5ff11c054678"))
					.name("Piotr Nikolaievitch Rasputin")
					.aliases(List.of("Colossus", "Peter Rasputin", "Peter Nicholas", "the Proletarian"))
					.powers(List.of("Turns into organic steel", "Superhuman Durability", "Superhuman Strength"))
					.durability(7)
					.energy(5)
					.fightingSkills(5)
					.intelligence(4)
					.speed(4)
					.strength(7)
					.build();
	public static final ComicsCharacter CABLE = BaseComicsCharacter.builder()
					.id(UUID.fromString("bb3bb471-4173-4425-9e4f-592eed3daebd"))
					.name("Nathan Summers")
					.aliases(List.of("Cable", "Soldier X", "X-32", "Nathan Winters", "Nathan Dayspring Summers",
									"Son of Tomorrow", "Man of Tomorrow", "Traveler", "Chosen One",
									"Nathan Dayspring Askani'son"))
					.powers(List.of("Telekinesis", "Telepathy", "Force Field", "Teleportation"))
					.durability(4)
					.energy(4)
					.fightingSkills(6)
					.intelligence(3)
					.speed(7)
					.strength(4)
					.build();
	public static final ComicsCharacter SCARLET_WITCH = BaseComicsCharacter.builder()
					.id(UUID.fromString("23adee20-cb04-4313-934c-ad1a86bade60"))
					.name("Wanda Maximoff")
					.aliases(List.of("Scarlet Witch", "Gypsy Witch", "Wanda Frank", "Wanda Magnus",
									"Ana Maximoff"))
					.powers(List.of("llumination", "Magic (Chaos Magic)", "Matter Transmutation", "Nexus Being",
									"Powers of Life and Death", "Probability Alteration", "Psychometry", "Precognition",
									"Magic", "Mind Control", "Healing Factor", "Reality Warping", "Control of Elements", "Psionics",
									"Communing with Deceased Spirits"))
					.durability(2)
					.energy(6)
					.fightingSkills(3)
					.intelligence(3)
					.speed(2)
					.strength(2)
					.build();
	public static final ComicsCharacter VISION = BaseComicsCharacter.builder()
					.id(UUID.fromString("a05fe77a-7fb4-4881-8039-9029159366f3"))
					.name("Victor Shade")
					.aliases(List.of("Vision", "Vizh", "Viz", "Vision of Tomorrow", "Human Torch",
									"Manikin the Ghost of Stone", "Alex Lipton", "Simon Williams", "Jim Hammond",
									"Firebug"))
					.powers(List.of("Density Shifting", "Intangibility", "Photokinesis",
									"Remote Interfacing (with other computer systems)", "Synthezoid", "Regeneration",
									"Heightened Senses", "Flight", "Superhuman Strength", "Superhuman Durability",
									"Superhuman Speed", "Superhuman Reflexes", "Superhuman Agility"))
					.durability(6)
					.energy(6)
					.fightingSkills(3)
					.intelligence(4)
					.speed(3)
					.strength(5)
					.build();
}
