package java.util;

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
	private String languageTag;

	public Locale(String language, String region, String variant) {
		if (language == null || region == null || variant == null) {
			throw new NullPointerException();
		}
		this.language = language;
		this.region = region;
		this.variant = variant;
	}

	public Locale(String language, String region) {
		this(language, region, "");
	}

	public Locale(String language) {
		this(language, "", "");
	}

	public static Locale getDefault() {
		// GWT TODO init from browser
		return defaultLocale;
	}

	public static void setDefault(Locale locale) {
		defaultLocale = locale;
	}

	public static Locale[] getAvailableLocales() {
		return new Locale[0];
	}

	public String getLanguage() {
		return language;
	}

	public String getScript() {
		return "";
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
		boolean lang = !language.isEmpty();
		boolean reg = !region.isEmpty();
		boolean var = !variant.isEmpty();

		StringBuilder result = new StringBuilder(language);
		if (reg || (lang && var)) {
			result.append('_').append(region);
		}
		if (var && (lang || reg)) {
			result.append('_').append(variant);
		}
		return result.toString();
	}

	public String toLanguageTag() {
		if (languageTag == null) {
			StringBuilder result = new StringBuilder();

			if (!language.isEmpty()) {
				result.append(language);
			}

			if (!region.isEmpty()) {
				result.append('-');
				result.append(region);
			}

			if (!variant.isEmpty()) {
				result.append('-');
				result.append(variant);
			}
			languageTag = result.toString();
		}
		return languageTag;
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
		return "";
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

}
