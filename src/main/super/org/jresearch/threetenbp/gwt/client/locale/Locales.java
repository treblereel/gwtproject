package org.jresearch.threetenbp.gwt.client.locale;

import java.util.Locale;

public class Locales {

	private Locales() {
		// prevent instantiation
	}

	public static Locale create(String language, String region, String script, String variant) {
		return new Locale(language, region, script, variant);
	}

}
