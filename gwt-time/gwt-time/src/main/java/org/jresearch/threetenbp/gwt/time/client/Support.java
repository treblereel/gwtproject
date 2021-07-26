package org.jresearch.threetenbp.gwt.time.client;

import elemental2.core.JsDate;
import elemental2.dom.DomGlobal;
import java.time.zone.ZoneRulesProvider;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import org.gwtproject.core.client.ScriptInjector;
import org.jresearch.threetenbp.gwt.time.client.locale.LocaleWrapper;
import org.jresearch.threetenbp.gwt.time.client.zone.GwtZoneRuleProvider;

public class Support {

  public static final String support =
      "if (window.performance.now) {\r\n    getTimestamp = function() { return window.performance.timing.navigationStart + window.performance.now(); };\r\n} else {\r\n    getTimestamp = function() { return new Date().getTime(); };\r\n}\r\n\r\nif (typeof Intl == 'object' && typeof Intl.DateTimeFormat == 'function'  && typeof Intl.DateTimeFormat().resolvedOptions == 'function') {\r\n	getTimezone = function() { return Intl.DateTimeFormat().resolvedOptions().timeZone; };\r\n} else {\r\n	getTimezone = function() { return new Date().toTimeString().slice(9, 17); };\r\n}\r\n\r\nif (typeof Intl == 'object' && typeof Intl.DateTimeFormat == 'function'  && typeof Intl.DateTimeFormat.supportedLocalesOf == 'function') {\r\n	supportedLocalesOfDateTimeFormat = function(locales) {\r\n		return Intl.DateTimeFormat.supportedLocalesOf(locales, { localeMatcher: 'lookup' });\r\n	};\r\n} else {\r\n	supportedLocalesOfDateTimeFormat = function(locales) {\r\n		return [\"ROOT\", \"US\"];\r\n	};\r\n}\r\n\r\nif (typeof Intl == 'object' && typeof Intl.NumberFormat == 'function'  && typeof Intl.NumberFormat.supportedLocalesOf == 'function') {\r\n	supportedLocalesOfNumberFormat = function(locales) {\r\n		return Intl.NumberFormat.supportedLocalesOf(locales, { localeMatcher: 'lookup' });\r\n	};\r\n} else {\r\n	supportedLocalesOfNumberFormat = function(locales) {\r\n		return [\"ROOT\", \"US\"];\r\n	};\r\n}\r\n\r\nif (typeof Intl == 'object' && typeof Intl.DateTimeFormat == 'function'  && typeof Intl.DateTimeFormat().formatToParts == 'function') {\r\n	displayTimeZone = function(daylight, timeZone, style, locale) {\r\n		try {\r\n			const region = new Intl.DateTimeFormat(locale, { timeZone: timeZone, timeZoneName: style });\r\n			const month = daylight ? 6 : 12;\r\n			const date = new Date(Date.UTC(2012, month, 20, 6, 0, 0));\r\n			return region.formatToParts(date).find(checkTimeZone).value;\r\n		} catch (e) {\r\n			console.error(\"Error while call displayTimeZone with daylight %s, timeZone %s, style %s, locale %s: %s\", daylight, timeZone, style, locale, e);\r\n			return timeZone;\r\n		}\r\n	};\r\n} else {\r\n	displayTimeZone = function(daylight, timeZone, style, locale) {\r\n		return timeZone;\r\n	};\r\n}\r\n\r\nif (typeof Intl == 'object' && typeof Intl.DateTimeFormat == 'function'  && typeof Intl.DateTimeFormat().formatToParts == 'function') {\r\n	displayMonths = function(style, standalone, locale) {\r\n		try {\r\n			var result = [];\r\n			const region = standalone ? new Intl.DateTimeFormat(locale, { month: style }) : new Intl.DateTimeFormat(locale, { month: style, day: 'numeric' });\r\n			for (i = 0; i < 12; i++) {\r\n				const date = new Date(Date.UTC(2020, i, 4, 6, 0, 0));\r\n				result.push(region.formatToParts(date).find(checkMonth).value);\r\n			}\r\n			return result;\r\n		} catch (e) {\r\n			console.error(\"Error while call displayMonths with style %s, locale %s: %s\", style, locale, e);\r\n			return [];\r\n		}\r\n	};\r\n} else {\r\n	displayMonths = function(style, locale) {\r\n		switch (style) {\r\n		case \"narrow\":\r\n			return [\"J\", \"F\", \"M\", \"A\", \"M\", \"J\", \"J\", \"A\", \"S\", \"O\", \"N\", \"D\"];\r\n		case \"short\":\r\n			return [\"Jan\", \"Feb\", \"Mar\", \"Apr\", \"May\", \"Jun\", \"Jul\", \"Aug\", \"Sep\", \"Oct\", \"Nov\", \"Dec\"];\r\n		default:\r\n			return [\"January\", \"February\", \"March\", \"April\", \"May\", \"June\", \"July\", \"August\", \"September\", \"October\", \"November\", \"December\"];\r\n		}\r\n	};\r\n}\r\n\r\nif (typeof Intl == 'object' && typeof Intl.DateTimeFormat == 'function'  && typeof Intl.DateTimeFormat().formatToParts == 'function') {\r\n	displayWeekdays = function(style, standalone, locale) {\r\n		try {\r\n			var result = [];\r\n			const region = standalone ? new Intl.DateTimeFormat(locale, { weekday: style }) : new Intl.DateTimeFormat(locale, { weekday: style, day: 'numeric' });\r\n			for (i = 1; i <= 7; i++) {\r\n				const date = new Date(Date.UTC(2020, 5, i, 6, 0, 0));\r\n				result.push(region.formatToParts(date).find(checkWeekday).value);\r\n			}\r\n			return result;\r\n		} catch (e) {\r\n			console.error(\"Error while call displayMonths with style %s, locale %s: %s\", style, locale, e);\r\n			return [];\r\n		}\r\n	};\r\n} else {\r\n	displayWeekdays = function(style, locale) {\r\n		switch (style) {\r\n		case \"narrow\":\r\n			return [\"M\", \"T\", \"W\", \"T\", \"F\", \"S\", \"S\"];\r\n		case \"short\":\r\n			return [\"Mon\", \"Tue\", \"Wed\", \"Thu\", \"Fri\", \"Sat\", \"Sun\"];\r\n		default:\r\n			return [\"Monday\", \"Tuesday\", \"Wednesday\", \"Thursday\", \"Friday\", \"Saturday\", \"Sunday\"];\r\n		}\r\n	};\r\n}\r\n\r\nif (typeof Intl == 'object' && typeof Intl.DateTimeFormat == 'function'  && typeof Intl.DateTimeFormat().formatToParts == 'function') {\r\n	displayEras = function(style, locale) {\r\n		try {\r\n			var result = [];\r\n			const region = new Intl.DateTimeFormat(locale, { era: style });\r\n			var date = new Date(-99999999999999);\r\n			result.push(region.formatToParts(date).find(checkEra).value);\r\n			date = new Date(99999999999999);\r\n			result.push(region.formatToParts(date).find(checkEra).value);\r\n			return result;\r\n		} catch (e) {\r\n			console.error(\"Error while call displayMonths with style %s, locale %s: %s\", style, locale, e);\r\n			return [];\r\n		}\r\n	};\r\n} else {\r\n	displayEras = function(style, locale) {\r\n		switch (style) {\r\n		case \"narrow\":\r\n			return [\"B\", \"A\"];\r\n		case \"short\":\r\n			return [\"BC\", \"AD\"];\r\n		default:\r\n			return [\"Before Christ\", \"Anno Domini\"];\r\n		}\r\n	};\r\n}\r\n\r\nif (typeof Intl == 'object' && typeof Intl.DateTimeFormat == 'function'  && typeof Intl.DateTimeFormat().formatToParts == 'function') {\r\n	displayAmpm = function(style, locale) {\r\n		try {\r\n			var result = [];\r\n			const region = new Intl.DateTimeFormat(locale, { dayPeriod: style, hour: 'numeric', hour12: 'false' });\r\n			var date = new Date(Date.UTC(2020, 5, 4, 6, 0, 0));\r\n			result.push(region.formatToParts(date).find(checkAmpm).value);\r\n			date = new Date(Date.UTC(2020, 5, 4, 16, 0, 0));\r\n			result.push(region.formatToParts(date).find(checkAmpm).value);\r\n			return result;\r\n		} catch (e) {\r\n			console.error(\"Error while call displayMonths with style %s, locale %s: %s\", style, locale, e);\r\n			return [];\r\n		}\r\n	};\r\n} else {\r\n	displayAmpm = function(style, locale) {\r\n		return [\"AM\", \"PM\"]\r\n	};\r\n}\r\n\r\nif (typeof Intl == 'object' && typeof Intl.NumberFormat == 'function'  && typeof Intl.NumberFormat().formatToParts == 'function') {\r\n	displayNumber = function(locale) {\r\n		var result = new Object();\r\n		try {\r\n			const numFormat = new Intl.NumberFormat(locale, { signDisplay:'always' });\r\n			result.zeroDigit = numFormat.formatToParts(0).find(checkZero).value;\r\n			//FF does not support signDisplay return +\r\n			var res = numFormat.formatToParts(1).find(checkPlus)\r\n			result.positiveSign = res === undefined ? '+' : res.value ;\r\n			result.negativeSign = numFormat.formatToParts(-1).find(checkMinus).value;\r\n			result.decimalSeparator = numFormat.formatToParts(1.5).find(checkPoint).value;\r\n		} catch (e) {\r\n			console.error(\"Error while call displayNumber, locale %s: %s\", locale, e);\r\n			result.zeroDigit = '0';\r\n			result.positiveSign = '+';\r\n			result.negativeSign = '-';\r\n			result.decimalSeparator = '.';\r\n		}\r\n		return result;\r\n	};\r\n} else {\r\n	displayNumber = function(locale) {\r\n		var result = new Object();\r\n		result.zeroDigit = '0';\r\n		result.positiveSign = '+';\r\n		result.negativeSign = '-';\r\n		result.decimalSeparator = '.';\r\n		return result;\r\n	};\r\n}\r\n\r\nfunction sleep(milliseconds) {\r\n	var start = getTimestamp();\r\n	var current = getTimestamp();\r\n	while(current - start < milliseconds) {\r\n		current = getTimestamp();\r\n	}\r\n}\r\n\r\nfunction displayTimeZoneModern(daylight, timeZone, style, locale) {\r\n	const region = new Intl.DateTimeFormat(locale, { timeZone: timeZone, timeZoneName: style });\r\n	const month = daylight ? 6 : 12;\r\n	const date = new Date(Date.UTC(2012, month, 20, 6, 0, 0));\r\n	return region.formatToParts(date).find(checkType).value;\r\n}\r\n\r\nfunction checkZero(part) {\r\n	  return part.type === \"integer\";\r\n}\r\n\r\nfunction checkPlus(part) {\r\n	  return part.type === \"plusSign\";\r\n}\r\n\r\nfunction checkMinus(part) {\r\n	  return part.type === \"minusSign\";\r\n}\r\n\r\nfunction checkPoint(part) {\r\n	  return part.type === \"decimal\";\r\n}\r\n\r\nfunction checkTimeZone(part) {\r\n	  return part.type === \"timeZoneName\";\r\n}\r\n\r\nfunction checkMonth(part) {\r\n	  return part.type === \"month\";\r\n}\r\n\r\nfunction checkWeekday(part) {\r\n	  return part.type === \"weekday\";\r\n}\r\n\r\nfunction checkEra(part) {\r\n	  return part.type === \"era\";\r\n}\r\n\r\nfunction checkAmpm(part) {\r\n	  return part.type === \"dayPeriod\";\r\n}\r\n\r\nfunction displayTimeZoneLegacy(daylight, timeZone, style, locale) {\r\n	const month = daylight ? 6 : 12;\r\n	const date = new Date(Date.UTC(2012, month, 20, 6, 0, 0));\r\n	return date.toLocaleDateString(locale, { timeZone: timeZone, timeZoneName: style }).slice(11);\r\n}";

  private static final Map<String, GwtZoneRuleProvider> gwtZoneRuleProviders = new HashMap<>();

  private static boolean commonInitialized = false;

  public static void init() {
    if (!commonInitialized) {
      DomGlobal.console.debug("common initialization");
      ScriptInjector.fromString(support).setWindow(ScriptInjector.TOP_WINDOW).inject();
      commonInitialized = true;
    }
  }

  public static void initTzData() {
    DomGlobal.console.debug("initTzData called");
    if (!commonInitialized) {
      init();
    }
    if (!isTzTnitialized()) {
      gwtZoneRuleProviders.values().forEach(GwtZoneRuleProvider::initialize);
    }
  }

  public static void registerGwtZoneRuleProvider(GwtZoneRuleProvider gwtZoneRuleProvider) {
    if (!gwtZoneRuleProviders.containsKey(gwtZoneRuleProvider.getProviderId())) {
      DomGlobal.console.debug(
          "Register GWT zone rule provider: {}", gwtZoneRuleProvider.getProviderId());
      gwtZoneRuleProviders.put(gwtZoneRuleProvider.getProviderId(), gwtZoneRuleProvider);
      ZoneRulesProvider.refresh();
    }
  }

  public static boolean isTzTnitialized() {
    return !gwtZoneRuleProviders.isEmpty()
        && gwtZoneRuleProviders.values().stream().allMatch(GwtZoneRuleProvider::isInitialized);
  }

  public static float getTimestamp() {
    return SupportJs.getTimestamp();
  }

  public static int getMinutesOffset() {
    return -(new JsDate().getTimezoneOffset());
  }

  @Nonnull
  public static String getTimezone() {
    return SupportJs.getTimezone();
  }

  public static void sleep(int milliseconds) {
    SupportJs.sleep(milliseconds);
  }

  /**
   * @param style - "short", "long"
   * @param locale - language tag
   */
  @Nonnull
  public static String displayTimeZone(
      boolean daylight, String timeZone, String style, String locale) {
    return SupportJs.displayTimeZone(daylight, timeZone, style, locale);
  }

  /**
   * @param style - "2-digit", "numeric", "narrow", "short", "long"
   * @param locale - language tag
   */
  @Nonnull
  public static String[] displayMonths(String style, boolean standalone, String locale) {
    return SupportJs.displayMonths(style, standalone, locale);
  }

  /**
   * @param style - "narrow", "short", "long"
   * @param locale - language tag
   */
  @Nonnull
  public static String[] displayWeekdays(String style, boolean standalone, String locale) {
    return SupportJs.displayWeekdays(style, standalone, locale);
  }

  /**
   * @param style - "narrow", "short", "long"
   * @param locale - language tag
   */
  @Nonnull
  public static String[] displayEras(String style, String locale) {
    return SupportJs.displayEras(style, locale);
  }

  /**
   * @param style - "narrow", "short", "long"
   * @param locale - language tag
   */
  @Nonnull
  public static String[] displayAmpm(String style, String locale) {
    return SupportJs.displayAmpm(style, locale);
  }

  @Nonnull
  public static DecimalProperty displayNumber(String locale) {
    return SupportJs.displayNumber(locale);
  }

  @Nonnull
  public static Locale[] supportedLocalesOfDateTimeFormat(Locale[] locales) {
    String[] a =
        Stream.of(locales)
            .map(l -> LocaleWrapper.getInstance(l).toLanguageTag())
            .filter(l -> !"und".equalsIgnoreCase(l))
            .toArray(String[]::new);
    String[] supportedLocales = SupportJs.supportedLocalesOfDateTimeFormat(a);
    return Stream.of(supportedLocales)
        .map(Support::jsRootToJava)
        .map(l -> LocaleWrapper.getInstance(l))
        .toArray(Locale[]::new);
  }

  @Nonnull
  public static Locale[] supportedLocalesOfNumberFormat(Locale[] locales) {
    String[] a =
        Stream.of(locales)
            .map(l -> LocaleWrapper.getInstance(l).toLanguageTag())
            .filter(l -> !"und".equalsIgnoreCase(l))
            .toArray(String[]::new);
    String[] supportedLocales = SupportJs.supportedLocalesOfNumberFormat(a);
    return Stream.of(supportedLocales)
        .map(Support::jsRootToJava)
        .map(l -> LocaleWrapper.getInstance(l))
        .toArray(Locale[]::new);
  }

  public static String jsRootToJava(String tag) {
    return "root".equalsIgnoreCase(tag) ? "" : tag;
  }
}
