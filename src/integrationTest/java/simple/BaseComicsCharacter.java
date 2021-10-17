package simple;

import lombok.Builder;
import lombok.Value;
import simple.api.ComicsCharacter;

import java.util.List;
import java.util.UUID;

@Builder(toBuilder = true)
@Value
public class BaseComicsCharacter implements ComicsCharacter {
	UUID id;
	String name;
	List<String> aliases;
	List<String> powers;
	int durability;
	int energy;
	int fightingSkills;
	int intelligence;
	int speed;
	int strength;
}
