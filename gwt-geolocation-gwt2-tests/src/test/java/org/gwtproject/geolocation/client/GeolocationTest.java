package org.gwtproject.geolocation.client;

import com.google.gwt.junit.client.GWTTestCase;

/** Tests for {@link Geolocation}. */
public class GeolocationTest extends GWTTestCase {

  protected Geolocation geolocation;

  @Override
  public String getModuleName() {
    return "org.gwtproject.geolocation.GeolocationTestModule";
  }

  @Override
  protected void gwtSetUp() throws Exception {
    geolocation = Geolocation.getIfSupported();
  }

  @Override
  protected void gwtTearDown() throws Exception {
    geolocation = null;
  }

  public void testNullIfUnsupported() {
    if (!Geolocation.isSupported()) {
      assertNull(geolocation);
    } else {
      assertNotNull(geolocation);
    }
  }
}
