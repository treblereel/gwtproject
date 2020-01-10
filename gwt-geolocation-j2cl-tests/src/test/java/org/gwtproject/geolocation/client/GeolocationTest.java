package org.gwtproject.geolocation.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.junit.Test;

/** Tests for {@link Geolocation}. */
@J2clTestInput(GeolocationTest.class)
public class GeolocationTest {

  protected Geolocation geolocation;

  {
    geolocation = Geolocation.getIfSupported();
  }

  @Test
  public void testNullIfUnsupported() {
    if (!Geolocation.isSupported()) {
      assertNull(geolocation);
    } else {
      assertNotNull(geolocation);
    }
  }
}
