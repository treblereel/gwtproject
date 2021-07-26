package org.jresearch.threetenbp.gwt.time.client;

import elemental2.core.ArrayBuffer;
import elemental2.core.Uint8Array;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "window")
public class SupportJs {

  public static native float getTimestamp();

  public static native String getTimezone();

  public static native void sleep(int milliseconds);

  /**
   * @param style "short", "long"
   * @param locale language tag
   */
  public static native String displayTimeZone(
      boolean daylight, String timeZone, String style, String locale);

  /**
   * @param style "2 digit", "numeric", "narrow", "short", "long"
   * @param locale language tag
   */
  public static native String[] displayMonths(String style, boolean standalone, String locale);

  /**
   * @param style "narrow", "short", "long"
   * @param locale language tag
   */
  public static native String[] displayWeekdays(String style, boolean standalone, String locale);

  /**
   * @param style "narrow", "short", "long"
   * @param locale language tag
   */
  public static native String[] displayEras(String style, String locale);

  /**
   * @param style "narrow", "short", "long"
   * @param locale language tag
   */
  public static native String[] displayAmpm(String style, String locale);

  public static native DecimalProperty displayNumber(String locale);

  public static native String[] supportedLocalesOfDateTimeFormat(String[] locales);

  public static native String[] supportedLocalesOfNumberFormat(String[] locales);

  @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Base64Binary")
  public static class Base64Binary {

    public static native ArrayBuffer decodeArrayBuffer(String base64);

    public static native Uint8Array decode(String base64);
  }
}
