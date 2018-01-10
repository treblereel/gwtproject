package org.gwtproject.xhr;

import com.google.gwt.junit.tools.GWTTestSuite;
import org.gwtproject.xhr.client.XMLHttpRequestTest;
import junit.framework.Test;

public class XhrTestSuite {

    public static Test suite() {
        GWTTestSuite suite = new GWTTestSuite("Xhr client test");
        suite.addTestSuite(XMLHttpRequestTest.class);
        return suite;
    }
}
