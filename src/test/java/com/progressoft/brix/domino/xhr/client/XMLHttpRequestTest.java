package com.progressoft.brix.domino.xhr.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Timer;

import java.util.logging.Logger;

public class XMLHttpRequestTest extends GWTTestCase {

    private static final Logger LOGGER = Logger.getLogger(XMLHttpRequestTest.class.getCanonicalName());
    private static final int UNSENT = 0;
    private static final int OPENED = 1;
    private static final int DONE = 4;
    private XMLHttpRequest xmlHttpRequest;

    @Override
    public String getModuleName() {
        return "com.progressoft.brix.domino.xhr.XMLHttpRequestTest";
    }

    @FunctionalInterface
    private interface Task {
        void run();
    }

    private void runAsyncTask(Task task) {
        Timer timer = new Timer() {
            @Override
            public void run() {
                task.run();
            }
        };
        delayTestFinish(1000);
        timer.schedule(100);
    }

    private String getTestPath() {
        return GWT.getModuleBaseURL() + "testRequest";
    }

    @Override
    protected void gwtSetUp() {
        xmlHttpRequest = XMLHttpRequest.create();
    }

    public void testCreateXmlHttpRequest_shouldNotBeNull() {
        assertNotNull(xmlHttpRequest);
        assertEquals(UNSENT, xmlHttpRequest.getReadyState());
    }

    public void testOpenXmlHttpRequest_shouldHaveReadyStateOfOPENED() {
        xmlHttpRequest.setOnReadyStateChange(xhr -> {
            assertEquals(OPENED, xhr.getReadyState());
            finishTest();
        });
        runAsyncTask(() -> xmlHttpRequest.open("GET", "url"));
    }

    public void testOpenWithUserXmlHttpRequest_shouldHaveReadyStateOfOPENED() {
        xmlHttpRequest.setOnReadyStateChange(xhr -> {
            assertEquals(OPENED, xhr.getReadyState());
            finishTest();
        });
        runAsyncTask(() -> xmlHttpRequest.open("GET", getTestPath(), "user"));
    }

    public void testOpenWithUserAndPasswordXmlHttpRequest() {
        xmlHttpRequest.setOnReadyStateChange(xhr -> {
            assertEquals(OPENED, xhr.getReadyState());
            finishTest();
        });
        runAsyncTask(() -> xmlHttpRequest.open("GET", "url", "user", "password"));
    }

    public void testSetRequestHeader() {
        xmlHttpRequest.setOnReadyStateChange(xhr -> {
            if (xhr.getReadyState() == DONE) {
                assertEquals("value", xhr.getResponseHeader("header"));
                finishTest();
            }
        });
        xmlHttpRequest.open("GET", getTestPath());
        xmlHttpRequest.setRequestHeader("header", "value");
        runAsyncTask(xmlHttpRequest::send);
    }

    public void testAllResponseHeaders() {
        xmlHttpRequest.setOnReadyStateChange(xhr -> {
            if (xhr.getReadyState() == DONE) {
                LOGGER.info(xmlHttpRequest.getAllResponseHeaders());
                finishTest();
            }
        });
        xmlHttpRequest.open("GET", getTestPath());
        runAsyncTask(xmlHttpRequest::send);
    }
}
