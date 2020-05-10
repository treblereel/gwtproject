package org.jresearch.threetenbp.gwt.client.cldr;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class Cldrs {

	private static final Map<String, Region> INDEX = new HashMap<>(Region.values().length);

	static {
		for (Region region : Region.values()) {
			INDEX.put(region.name().toUpperCase(), region);
		}
	}

	public static Region regionOf(Locale locale) {
		return Optional.of(locale)
				.map(Locale::getCountry)
				.map(String::toUpperCase)
				.map(INDEX::get)
				.orElse(Region._001);
	}

}
