package com.progressoft.brix.domino.xhr.client;

import com.google.gwt.junit.client.GWTTestCase;

import java.util.logging.Logger;

public class XMLHttpRequestTest extends GWTTestCase {

    private static final Logger LOGGER = Logger.getLogger(XMLHttpRequestTest.class.getCanonicalName());
    private static final int UNSENT = 0;

    @Override
    public String getModuleName() {
        return "com.progressoft.brix.domino.xhr.XMLHttpRequest";
    }

    public void testCreateXmlHttpRequest() {
        XMLHttpRequest xmlHttpRequest = XMLHttpRequest.create();

        assertNotNull(xmlHttpRequest);
        assertEquals(xmlHttpRequest.getStatus(), UNSENT);
    }
}
