package java.util;

import com.google.gwt.i18n.client.LocaleInfo;

@SuppressWarnings("nls")
public final class Locale {

	/** Constant from JDK */
	public static final Locale ENGLISH = new Locale("en");
	public static final Locale FRENCH = new Locale("fr");
	public static final Locale GERMAN = new Locale("de");
	public static final Locale ITALIAN = new Locale("it");
	public static final Locale JAPANESE = new Locale("ja");
	public static final Locale KOREAN = new Locale("ko");
	public static final Locale CHINESE = new Locale("zh");
	public static final Locale SIMPLIFIED_CHINESE = new Locale("zh", "CN");
	public static final Locale TRADITIONAL_CHINESE = new Locale("zh", "TW");
	public static final Locale FRANCE = new Locale("fr", "FR");
	public static final Locale GERMANY = new Locale("de", "DE");
	public static final Locale ITALY = new Locale("it", "IT");
	public static final Locale JAPAN = new Locale("ja", "JP");
	public static final Locale KOREA = new Locale("ko", "KR");
	public static final Locale UK = new Locale("en", "GB");
	public static final Locale US = new Locale("en", "US");
	public static final Locale CANADA = new Locale("en", "CA");
	public static final Locale CANADA_FRENCH = new Locale("fr", "CA");
	public static final Locale ROOT = new Locale("");
	public static final Locale CHINA = SIMPLIFIED_CHINESE;
	public static final Locale PRC = SIMPLIFIED_CHINESE;
	public static final Locale TAIWAN = TRADITIONAL_CHINESE;

	private static Locale defaultLocale = ROOT;

	private final String language;
	private final String region;
	private final String variant;
	private final String script;
	private String languageTag;

	static {
		String localeName = LocaleInfo.getCurrentLocale().getLocaleName();
		if (!"default".equalsIgnoreCase(localeName)) {
			String[] split = localeName.split("_", 3);
			if (split.length == 1) {
				defaultLocale = new Locale(localeName);
			} else if (split.length == 2) {
				defaultLocale = new Locale(split[0], split[1]);
			} else if (split.length == 3) {
				defaultLocale = new Locale(split[0], split[1], split[2]);
			}
		}
	}

	public Locale(String language, String region, String script, String variant) {
		if (language == null || script == null || region == null || variant == null) {
			throw new NullPointerException();
		}
		this.language = language;
		this.region = region;
		this.variant = variant;
		this.script = script;
	}

	public Locale(String language, String region, String script) {
		this(language, region, script, "");
	}

	public Locale(String language, String region) {
		this(language, region, "", "");
	}

	public Locale(String language) {
		this(language, "", "", "");
	}

	public static Locale getDefault() {
		return defaultLocale;
	}

	public static void setDefault(Locale locale) {
		defaultLocale = locale;
	}

	public static Locale[] getAvailableLocales() {
		return org.jresearch.threetenbp.gwt.client.cldr.LocaleInfo.LOCALES;
	}

	public String getLanguage() {
		return language;
	}

	public String getScript() {
		return script;
	}

	public String getCountry() {
		return region;
	}

	public String getVariant() {
		return variant;
	}

	public boolean hasExtensions() {
		return false;
	}

	public Locale stripExtensions() {
		return this;
	}

	public String getExtension(char key) {
		return null;
	}

	public Set<Character> getExtensionKeys() {
		return Collections.emptySet();
	}

	public Set<String> getUnicodeLocaleAttributes() {
		return Collections.emptySet();
	}

	public String getUnicodeLocaleType(String key) {
		return null;
	}

	public Set<String> getUnicodeLocaleKeys() {
		return Collections.emptySet();
	}

	@Override
	public final String toString() {
		return to('_');
	}

	public String toLanguageTag() {
		if (languageTag == null) {
			languageTag = to('-');
		}
		return languageTag;
	}

	public String to(char delimeter) {
		StringBuilder result = new StringBuilder();

		if (!language.isEmpty()) {
			result.append(language);
		}

		if (!script.isEmpty()) {
			result.append(delimeter);
			result.append(script);
		}

		if (!region.isEmpty()) {
			result.append(delimeter);
			result.append(region);
		}

		if (!variant.isEmpty()) {
			result.append(delimeter);
			result.append(variant);
		}
		return result.toString();
	}

	public final String getDisplayLanguage() {
		return getDisplayLanguage(defaultLocale);
	}

	public String getDisplayLanguage(Locale inLocale) {
		return language;
	}

	public String getDisplayScript() {
		return getDisplayScript(defaultLocale);
	}

	public String getDisplayScript(Locale inLocale) {
		return script;
	}

	public final String getDisplayCountry() {
		return getDisplayCountry(defaultLocale);
	}

	public String getDisplayCountry(Locale inLocale) {
		return region;
	}

	public final String getDisplayVariant() {
		return getDisplayVariant(defaultLocale);
	}

	public String getDisplayVariant(Locale inLocale) {
		return variant;
	}

	public final String getDisplayName() {
		return getDisplayName(defaultLocale);
	}

	public String getDisplayName(Locale inLocale) {
		return toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((language == null) ? 0 : language.toLowerCase().hashCode());
		result = prime * result + ((region == null) ? 0 : region.toLowerCase().hashCode());
		result = prime * result + ((script == null) ? 0 : script.toLowerCase().hashCode());
		result = prime * result + ((variant == null) ? 0 : variant.toLowerCase().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locale other = (Locale) obj;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equalsIgnoreCase(other.language))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equalsIgnoreCase(other.region))
			return false;
		if (script == null) {
			if (other.script != null)
				return false;
		} else if (!script.equalsIgnoreCase(other.script))
			return false;
		if (variant == null) {
			if (other.variant != null)
				return false;
		} else if (!variant.equalsIgnoreCase(other.variant))
			return false;
		return true;
	}

}
