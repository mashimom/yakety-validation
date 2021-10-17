package json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Value
@Slf4j
public class Movies {

	public static final String PIXAR_JSON_RESOURCE = "/json/pixar.json";
	JsonNode movies;

	public Movies() {
		movies = load();
		log.info("Loaded movies");
	}

	static JsonNode load() {
		final ObjectMapper objectMapper = new ObjectMapper();
		try (final InputStream inputStream = Movies.class.getResourceAsStream(PIXAR_JSON_RESOURCE)) {
			return objectMapper.readTree(inputStream);
		} catch (final IOException e) {
			log.error("Unable to load file {}", PIXAR_JSON_RESOURCE);
		}
		return objectMapper.nullNode();
	}

	static ArrayNode loadList() {
		//noinspection unchecked
		return (ArrayNode) load().get("movies");
	}
}
