package configuration.marvel;

import configuration.marvel.api.IMarvelUniverseColumn;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class MarvelUniverseIndexColumn implements IMarvelUniverseColumn {
	public static final IMarvelUniverseColumn INDEX = new MarvelUniverseIndexColumn();

	private MarvelUniverseIndexColumn() {
	}

	int order = -1;
	String name = "_";

	@Override
	public String toString() {
		return name;
	}
}
