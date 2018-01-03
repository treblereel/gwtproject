package com.progressoft.brix.domino.xhr.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Timer;

import java.util.logging.Logger;

public class XMLHttpRequestTest extends GWTTestCase {

    private static final Logger LOGGER = Logger.getLogger(XMLHttpRequestTest.class.getCanonicalName());
    private static final int UNSENT = 0;

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

    public void testCreateXmlHttpRequest() {
        XMLHttpRequest xmlHttpRequest = XMLHttpRequest.create();

        assertNotNull(xmlHttpRequest);
        assertEquals(UNSENT, xmlHttpRequest.getReadyState());
    }

    public void testOpenXmlHttpRequest() {
        XMLHttpRequest xmlHttpRequest = XMLHttpRequest.create();
        xmlHttpRequest.setOnReadyStateChange(xhr -> {
            assertEquals(1, xhr.getReadyState());
            finishTest();
        });

        runAsyncTask(() -> xmlHttpRequest.open("GET", "url"));
    }

    public void testOpenWithUserXmlHttpRequest() {
        XMLHttpRequest xmlHttpRequest = XMLHttpRequest.create();
        xmlHttpRequest.setOnReadyStateChange(xhr -> {
            assertEquals(1, xhr.getReadyState());
            finishTest();
        });

        runAsyncTask(() -> xmlHttpRequest.open("GET", "url", "user"));
    }

    public void testOpenWithUserAndPasswordXmlHttpRequest() {
        XMLHttpRequest xmlHttpRequest = XMLHttpRequest.create();
        xmlHttpRequest.setOnReadyStateChange(xhr -> {
            assertEquals(1, xhr.getReadyState());
            finishTest();
        });

        runAsyncTask(() -> xmlHttpRequest.open("GET", "url", "user", "password"));
    }

    public void testAbortXmlHttpRequest() {
        XMLHttpRequest xmlHttpRequest = XMLHttpRequest.create();
        xmlHttpRequest.open("GET", getTestPath());
        xmlHttpRequest.send();
    }

    private String getTestPath() {
        return GWT.getModuleBaseURL() + "testRequest";
    }

}
