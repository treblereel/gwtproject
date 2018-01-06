
package com.progressoft.brix.domino.xhr.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Timer;

import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        delayTestFinish(2000);
        timer.schedule(500);
    }

    private String getTestPath() {
        return GWT.getModuleBaseURL() + "testRequest";
    }

    private String getTestPathCORS() {
        return "http://localhost:9999/testCors";
    }

    private Map<String, String> responseHeaders() {
        String allResponseHeaders = xmlHttpRequest.getAllResponseHeaders();
        String[] headers = allResponseHeaders.split("\n");
        return Stream.of(headers)
                .map(header -> header.split(":", 2))
                .collect(Collectors.toMap(header -> header[0], header -> header[1].trim()));
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
                Map<String, String> responseHeaders = responseHeaders();
                assertEquals("value", responseHeaders.get("header"));
                assertEquals("anotherValue", responseHeaders.get("anotherHeader"));
                finishTest();
            }
        });
        xmlHttpRequest.open("GET", getTestPath());
        xmlHttpRequest.setRequestHeader("header", "value");
        xmlHttpRequest.setRequestHeader("anotherHeader", "anotherValue");
        runAsyncTask(xmlHttpRequest::send);
    }

    public void testSetWithCredentialsToTrue_withNoCredentialsResponseHeader_responseShouldBeEmpty() {
        xmlHttpRequest.setOnReadyStateChange(xhr -> {
            if (xhr.getReadyState() == DONE) {
                assertEquals("", xhr.getResponseText());
                finishTest();
            }
        });
        xmlHttpRequest.open("GET", getTestPathCORS());
        xmlHttpRequest.setRequestHeader("server-credentials", "false");
        xmlHttpRequest.setWithCredentials(true);
        runAsyncTask(xmlHttpRequest::send);
    }

    public void testSetWithCredentialsToTrue_withCredentialsResponseHeader_responseShouldNotBeEmpty() {
        xmlHttpRequest.setOnReadyStateChange(xhr -> {
            if (xhr.getReadyState() == DONE) {
                assertEquals("test content", xhr.getResponseText());
                finishTest();
            }
        });
        xmlHttpRequest.open("GET", getTestPathCORS());
        xmlHttpRequest.setRequestHeader("server-credentials", "true");
        xmlHttpRequest.setWithCredentials(true);
        runAsyncTask(xmlHttpRequest::send);
    }

    public void testSetWithCredentialsToFalse_withNoCredentialsResponseHeader_responseShouldNotBeEmpty() {
        xmlHttpRequest.setOnReadyStateChange(xhr -> {
            if (xhr.getReadyState() == DONE) {
                assertEquals("test content", xhr.getResponseText());
                finishTest();
            }
        });
        xmlHttpRequest.open("GET", getTestPathCORS());
        xmlHttpRequest.setRequestHeader("server-credentials", "false");
        xmlHttpRequest.setWithCredentials(false);
        runAsyncTask(xmlHttpRequest::send);
    }

    public void testSetWithCredentialsToFalse_withCredentialsResponseHeader_responseShouldNotBeEmpty() {
        xmlHttpRequest.setOnReadyStateChange(xhr -> {
            if (xhr.getReadyState() == DONE) {
                assertEquals("test content", xhr.getResponseText());
                finishTest();
            }
        });
        xmlHttpRequest.open("GET", getTestPathCORS());
        xmlHttpRequest.setRequestHeader("server-credentials", "true");
        xmlHttpRequest.setWithCredentials(false);
        runAsyncTask(xmlHttpRequest::send);
    }
}
