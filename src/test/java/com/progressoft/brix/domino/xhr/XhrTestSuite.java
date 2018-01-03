package com.progressoft.brix.domino.xhr;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.progressoft.brix.domino.xhr.client.XMLHttpRequestTest;
import junit.framework.Test;

public class XhrTestSuite {

    public static Test suite() {
        GWTTestSuite suite = new GWTTestSuite("Xhr client test");
        suite.addTestSuite(XMLHttpRequestTest.class);
        return suite;
    }
}
