package org.jresearch.threetenbp.gwt.time.client.locale;

import elemental2.dom.DomGlobal;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import jsinterop.base.Js;

@SuppressWarnings("nls")
public final class LocaleWrapper {

  /** Constant from JDK */
  public static final LocaleWrapper ENGLISH = new LocaleWrapper("en");

  public static final LocaleWrapper FRENCH = new LocaleWrapper("fr");
  public static final LocaleWrapper GERMAN = new LocaleWrapper("de");
  public static final LocaleWrapper ITALIAN = new LocaleWrapper("it");
  public static final LocaleWrapper JAPANESE = new LocaleWrapper("ja");
  public static final LocaleWrapper KOREAN = new LocaleWrapper("ko");
  public static final LocaleWrapper CHINESE = new LocaleWrapper("zh");
  public static final LocaleWrapper SIMPLIFIED_CHINESE = new LocaleWrapper("zh", "CN");
  public static final LocaleWrapper TRADITIONAL_CHINESE = new LocaleWrapper("zh", "TW");
  public static final LocaleWrapper FRANCE = new LocaleWrapper("fr", "FR");
  public static final LocaleWrapper GERMANY = new LocaleWrapper("de", "DE");
  public static final LocaleWrapper ITALY = new LocaleWrapper("it", "IT");
  public static final LocaleWrapper JAPAN = new LocaleWrapper("ja", "JP");
  public static final LocaleWrapper KOREA = new LocaleWrapper("ko", "KR");
  public static final LocaleWrapper UK = new LocaleWrapper("en", "GB");
  public static final LocaleWrapper US = new LocaleWrapper("en", "US");
  public static final LocaleWrapper CANADA = new LocaleWrapper("en", "CA");
  public static final LocaleWrapper CANADA_FRENCH = new LocaleWrapper("fr", "CA");
  public static final LocaleWrapper ROOT = new LocaleWrapper("");
  public static final LocaleWrapper CHINA = SIMPLIFIED_CHINESE;
  public static final LocaleWrapper PRC = SIMPLIFIED_CHINESE;
  public static final LocaleWrapper TAIWAN = TRADITIONAL_CHINESE;

  public static LocaleWrapper defaultLocale = ROOT;

  static {
    String localeName = getCurrentLocale();
    defaultLocale = getInstance(localeName);
  }

  private final String language;
  private final String region;
  private final String variant;
  private final String script;
  private String languageTag;

  public LocaleWrapper(String language, String region, String script) {
    this(language, region, script, "");
  }

  public LocaleWrapper(String language, String region, String script, String variant) {
    if (language == null || script == null || region == null || variant == null) {
      throw new NullPointerException();
    }
    this.language = language;
    this.region = region;
    this.variant = variant;
    this.script = script;
  }

  public LocaleWrapper(String language, String region) {
    this(language, region, "", "");
  }

  public LocaleWrapper(String language) {
    this(language, "", "", "");
  }

  public static LocaleWrapper getInstance(Locale locale) {
    return getInstance(locale.toString());
  }

  public static LocaleWrapper getInstance(String localeName) {
    if (!"default".equalsIgnoreCase(localeName)) {
      String[] split = localeName.split("_", 3);
      if (split.length == 1) {
        defaultLocale = new LocaleWrapper(localeName);
      } else if (split.length == 2) {
        defaultLocale = new LocaleWrapper(split[0], split[1]);
      } else if (split.length == 3) {
        defaultLocale = new LocaleWrapper(split[0], split[1], split[2]);
      }
    }
    return defaultLocale;
  }

  @jsinterop.annotations.JsMethod
  private static native String getCurrentLocale() /* {
 		var currentLocale =  @com.google.gwt.i18n.client.LocaleInfo::getCurrentLocale()();
 		return currentLocale.@com.google.gwt.i18n.client.LocaleInfo::getLocaleName()();
 	} */;

  public static Locale getDefault() {
    return Js.uncheckedCast(defaultLocale);
  }

  public static void setDefault(Locale locale) {
    defaultLocale = Js.uncheckedCast(locale);
  }

  public static java.util.Locale[] getAvailableLocales() {
    // return org.jresearch.threetenbp.gwt.time.client.cldr.LocaleInfo.LOCALES;
    // return null;
    throw new Error(LocaleWrapper.class.getCanonicalName());
  }

  public static Locale forLanguageTag(String br) {
    return Js.uncheckedCast(getInstance(br));
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
    return Js.uncheckedCast(this);
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

  public String toLanguageTag() {
    if (languageTag == null) {
      languageTag = to(' ');
    }

    DomGlobal.console.log("toLanguageTag " + languageTag);
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
    return getDisplayLanguage(Js.uncheckedCast(defaultLocale));
  }

  public String getDisplayLanguage(Locale inLocale) {
    return language;
  }

  public String getDisplayScript() {
    return getDisplayScript(Js.uncheckedCast(defaultLocale));
  }

  public String getDisplayScript(Locale inLocale) {
    return script;
  }

  public final String getDisplayCountry() {
    return getDisplayCountry(Js.uncheckedCast(defaultLocale));
  }

  public String getDisplayCountry(Locale inLocale) {
    return region;
  }

  public final String getDisplayVariant() {
    return getDisplayVariant(Js.uncheckedCast(defaultLocale));
  }

  public String getDisplayVariant(Locale inLocale) {
    return variant;
  }

  public final String getDisplayName() {
    return getDisplayName(Js.uncheckedCast(defaultLocale));
  }

  public String getDisplayName(Locale inLocale) {
    return toString();
  }

  @Override
  public final String toString() {
    return to('_');
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
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    LocaleWrapper other = (LocaleWrapper) obj;
    if (language == null) {
      if (other.language != null) return false;
    } else if (!language.equalsIgnoreCase(other.language)) return false;
    if (region == null) {
      if (other.region != null) return false;
    } else if (!region.equalsIgnoreCase(other.region)) return false;
    if (script == null) {
      if (other.script != null) return false;
    } else if (!script.equalsIgnoreCase(other.script)) return false;
    if (variant == null) {
      if (other.variant != null) return false;
    } else if (!variant.equalsIgnoreCase(other.variant)) return false;
    return true;
  }

  public java.util.Locale getLocale() {
    return Js.uncheckedCast(this);
  }
}
