package org.jresearch.threetenbp.gwt.time.client;

import com.google.gwt.junit.client.GWTTestCase;
import elemental2.core.Uint8Array;
import org.junit.Test;

@SuppressWarnings({"static-method", "nls"})
public class GwtSupportTest extends GWTTestCase {

  /** Must refer to a valid module that sources this class. */
  @Override
  public String getModuleName() {
    return "org.jresearch.threetenbp.gwt.time.Time";
  }

  @Test
  public void testGetTimestamp() {
    final float timestamp = Support.getTimestamp();
    assertTrue(timestamp > 0);
  }

  @Test
  public void testGetTimezove() {
    final String timezone = Support.getTimezone();
    assertNotNull(timezone);
  }

  @Test
  public void testDecodeArrayBuffer() {
    Uint8Array data = SupportJs.Base64Binary.decode("VGhlIHF1");
    assertNotNull(data);
    assertEquals(6, data.length);
    assertEquals(0x54, data.getAt(0));
    assertEquals(0x68, data.getAt(1));
    assertEquals(0x65, data.getAt(2));
    assertEquals(0x20, data.getAt(3));
    assertEquals(0x71, data.getAt(4));
    assertEquals(0x75, data.getAt(5));
  }

  @Test
  public void testDisplayTimeZone() {
    boolean daylight = false;
    String timeZone = "Europe/Prague";
    String style = "short";
    String locale = "de-DE";
    final String result = Support.displayTimeZone(daylight, timeZone, style, locale);
    assertNotNull(result);
    assertTrue("MEZ".equals(result) || timeZone.equals(result));
  }

  /*	@Test
  public void testSupportedLocalesOfDateTimeFormat() {
  	Locale[] test = new Locale[] { LocaleInfo.AGQ_CM, LocaleInfo.DE_DE };
  	Locale[] result = Support.supportedLocalesOfDateTimeFormat(test);
  	assertNotNull(result);
  	assertEquals(2, result.length);
  }*/

  /*	@Test
  public void testSupportedLocalesOfNumberFormat() {
  	Locale[] test = new Locale[] { LocaleInfo.AGQ_CM, LocaleInfo.DE_DE };
  	Locale[] result = Support.supportedLocalesOfNumberFormat(test);
  	assertNotNull(result);
  	assertEquals(2, result.length);
  }*/
}
