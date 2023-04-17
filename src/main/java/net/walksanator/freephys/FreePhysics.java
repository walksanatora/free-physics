package net.walksanator.freephys;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class FreePhysics implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("freephys");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Congrats on the free physics mod 🙃");
	}

	public static Map<String, String> getParamsFromUrl(String url) throws Exception {
		Map<String, String> params = new HashMap<>();
		URL parsedUrl = new URL(url);
		String query = parsedUrl.getQuery();
		String[] pairs = query.split("&");
		for (String pair : pairs) {
			String[] parts = pair.split("=");
			String key = URLDecoder.decode(parts[0], "UTF-8");
			String value = URLDecoder.decode(parts[1], "UTF-8");
			params.put(key, value);
		}
		return params;
	}
}
