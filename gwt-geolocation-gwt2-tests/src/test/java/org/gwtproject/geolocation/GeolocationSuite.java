package org.gwtproject.geolocation;

import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import org.gwtproject.geolocation.client.GeolocationTest;

/** Suite for all Geolocation tests. */
public class GeolocationSuite {
  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Geolocation Tests");

    suite.addTestSuite(GeolocationTest.class);

    return suite;
  }
}
