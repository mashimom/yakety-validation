package simple.api;

import java.util.List;
import java.util.UUID;

public interface ComicsCharacter {
	UUID getId();

	String getName();

	List<String> getAliases();

	List<String> getPowers();

	int getDurability();

	int getEnergy();

	int getFightingSkills();

	int getIntelligence();

	int getSpeed();

	int getStrength();
}
