/*
 * Copyright © 2019 The GWT Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gwtproject.xhr.client;

// import com.google.gwt.core.client.GWT;
// import com.google.gwt.junit.DoNotRunWith;
// import com.google.gwt.junit.Platform;
// import com.google.gwt.junit.client.GWTTestCase;
// import com.google.gwt.user.client.Timer;
// import java.nio.charset.StandardCharsets;
// import java.util.Map;
// import java.util.logging.Logger;
// import java.util.stream.Collectors;
// import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import com.google.j2cl.junit.apt.J2clTestInput;
import elemental2.promise.Promise;
import java.util.logging.Logger;
import org.gwtproject.timer.client.Timer;
import org.junit.Before;
import org.junit.Test;

// import jsinterop.base.Js;
// import org.gwtproject.typedarrays.client.Int8ArrayNative;

@J2clTestInput(XMLHttpRequestJ2clTest.class)
public class XMLHttpRequestJ2clTest {

  private static final Logger LOGGER =
      Logger.getLogger(XMLHttpRequestJ2clTest.class.getCanonicalName());

  private static final int UNSENT = 0;
  private static final int OPENED = 1;
  private static final int DONE = 4;

  private XMLHttpRequest xmlHttpRequest;

  //  @FunctionalInterface
  //  private interface Task {
  //    void run();
  //  }
  //
  //  private void runAsyncTask(Task task) {
  //    Timer timer =
  //        new Timer() {
  //          @Override
  //          public void run() {
  //            task.run();
  //          }
  //        };
  //    delayTestFinish(5000);
  //    timer.schedule(500);
  //  }

  //  private String getTestPath() {
  //    return GWT.getModuleBaseURL() + "testRequest";
  //  }

  private String getTestPathCORS() {
    return "http://localhost:9999/testCors";
  }

  //  private Map<String, String> responseHeaders() {
  //    String allResponseHeaders = xmlHttpRequest.getAllResponseHeaders();
  //    String[] headers = allResponseHeaders.split("\n");
  //    return Stream.of(headers)
  //        .map(header -> header.split(":", 2))
  //        .collect(Collectors.toMap(header -> header[0], header -> header[1].trim()));
  //  }
  //
  //  private String arrayBufferToString(XMLHttpRequest xhr) {
  //    return new String(
  //        Js.uncheckedCast(new Int8ArrayNative(xhr.getResponseArrayBuffer())),
  //        StandardCharsets.UTF_8);
  //  }

  @Before
  public void setUp() {
    xmlHttpRequest = XMLHttpRequest.create();
  }

  @Test
  public void testCreateXmlHttpRequest_shouldNotBeNull() {
    assertNotNull(xmlHttpRequest);
    assertEquals(UNSENT, xmlHttpRequest.getReadyState());
  }

  @Test(timeout = 5000)
  public Promise<Void> testOpenXmlHttpRequest_shouldHaveReadyStateOfOPENED() {
    return new Promise<>(
        (resolve, reject) -> {
          xmlHttpRequest.setOnReadyStateChange(
              xhr -> {
                assertEquals(OPENED, xhr.getReadyState());
                resolve.onInvoke((Void) null);
              });
          Timer timer =
              new Timer() {
                @Override
                public void run() {
                  xmlHttpRequest.open("GET", "url");
                  LOGGER.info(xmlHttpRequest.getReadyState() + "");
                }
              };
          timer.scheduleRepeating(500);
        });
  }

  //  @Test(timeout = 5000)
  //  public Promise<Void> testOpenWithUserXmlHttpRequest_shouldHaveReadyStateOfOPENED() {
  //    return new Promise<>((resolve, reject) -> {
  //    xmlHttpRequest.setOnReadyStateChange(
  //        xhr -> {
  //          assertEquals(OPENED, xhr.getReadyState());
  //          resolve.onInvoke((Void) null);
  //        });
  //      Timer timer =
  //          new Timer() {
  //            @Override
  //            public void run() {
  //              xmlHttpRequest.open("GET", getTestPath(), "user")
  //            }
  //          };
  //      timer.scheduleRepeating(500);
  //    });
  //  }

  //  @Test
  //  public void testOpenWithUserAndPasswordXmlHttpRequest() {
  //    xmlHttpRequest.setOnReadyStateChange(
  //        xhr -> {
  //          assertEquals(OPENED, xhr.getReadyState());
  //          finishTest();
  //        });
  //    runAsyncTask(() -> xmlHttpRequest.open("GET", "url", "user", "password"));
  //  }

  //  public void testStatus() {
  //    xmlHttpRequest.setOnReadyStateChange(
  //        xhr -> {
  //          if (xhr.getReadyState() == DONE) {
  //            assertEquals(200, xhr.getStatus());
  //            assertEquals("OK", xhr.getStatusText());
  //            finishTest();
  //          }
  //        });
  //    xmlHttpRequest.open("GET", getTestPath());
  //    xmlHttpRequest.setRequestHeader("header", "value");
  //    runAsyncTask(xmlHttpRequest::send);
  //  }
  //
  //  public void testSetRequestHeader() {
  //    xmlHttpRequest.setOnReadyStateChange(
  //        xhr -> {
  //          if (xhr.getReadyState() == DONE) {
  //            assertEquals("value", xhr.getResponseHeader("header"));
  //            finishTest();
  //          }
  //        });
  //    xmlHttpRequest.open("GET", getTestPath());
  //    xmlHttpRequest.setRequestHeader("header", "value");
  //    runAsyncTask(xmlHttpRequest::send);
  //  }
  //
  //  public void testAllResponseHeaders() {
  //    xmlHttpRequest.setOnReadyStateChange(
  //        xhr -> {
  //          if (xhr.getReadyState() == DONE) {
  //            Map<String, String> responseHeaders = responseHeaders();
  //            assertEquals("value", responseHeaders.get("header"));
  //            assertEquals("anothervalue", responseHeaders.get("anotherheader"));
  //            finishTest();
  //          }
  //        });
  //    xmlHttpRequest.open("GET", getTestPath());
  //    xmlHttpRequest.setRequestHeader("header", "value");
  //    xmlHttpRequest.setRequestHeader("anotherheader", "anothervalue");
  //    runAsyncTask(xmlHttpRequest::send);
  //  }
  //
  //  public void testSendRequestData() {
  //    xmlHttpRequest.setOnReadyStateChange(
  //        xhr -> {
  //          if (xhr.getReadyState() == DONE) {
  //            assertEquals("test content with body [sample request data]", xhr.getResponseText());
  //            finishTest();
  //          }
  //        });
  //    xmlHttpRequest.open("POST", getTestPath());
  //    runAsyncTask(() -> xmlHttpRequest.send("sample request data"));
  //  }

  @Test(timeout = 5000)
  public Promise<Void> setOnReadyStateChange() {
    return new Promise<>(
        (resolve, reject) -> {
          xmlHttpRequest.setOnReadyStateChange(
              xhr -> {
                if (xhr.getReadyState() == DONE) {
                  assertEquals("", xhr.getResponseText());
                  resolve.onInvoke((Void) null);
                }
              });
          Timer timer =
              new Timer() {
                @Override
                public void run() {
                  xmlHttpRequest.open("GET", getTestPathCORS() + "?credentials=false");
                  xmlHttpRequest.setWithCredentials(true);
                  xmlHttpRequest.send();
                }
              };
          timer.scheduleRepeating(500);
        });
  }

  //  @DoNotRunWith(Platform.HtmlUnitBug)
  //  public void
  //      testSetWithCredentialsToTrue_withCredentialsResponseHeader_responseShouldNotBeEmpty() {
  //    xmlHttpRequest.setOnReadyStateChange(
  //        xhr -> {
  //          if (xhr.getReadyState() == DONE) {
  //            assertEquals("test content", xhr.getResponseText());
  //            finishTest();
  //          }
  //        });
  //    xmlHttpRequest.open("GET", getTestPathCORS() + "?credentials=true");
  //    xmlHttpRequest.setWithCredentials(true);
  //    runAsyncTask(xmlHttpRequest::send);
  //  }
  //
  //  @DoNotRunWith(Platform.HtmlUnitBug)
  //  public void
  //      testSetWithCredentialsToFalse_withNoCredentialsResponseHeader_responseShouldNotBeEmpty() {
  //    xmlHttpRequest.setOnReadyStateChange(
  //        xhr -> {
  //          if (xhr.getReadyState() == DONE) {
  //            assertEquals("test content", xhr.getResponseText());
  //            finishTest();
  //          }
  //        });
  //    xmlHttpRequest.open("GET", getTestPathCORS() + "?credentials=false");
  //    xmlHttpRequest.setWithCredentials(false);
  //    runAsyncTask(xmlHttpRequest::send);
  //  }
  //
  //  @DoNotRunWith(Platform.HtmlUnitBug)
  //  public void
  //      testSetWithCredentialsToFalse_withCredentialsResponseHeader_responseShouldNotBeEmpty() {
  //    xmlHttpRequest.setOnReadyStateChange(
  //        xhr -> {
  //          if (xhr.getReadyState() == DONE) {
  //            assertEquals("test content", xhr.getResponseText());
  //            finishTest();
  //          }
  //        });
  //    xmlHttpRequest.open("GET", getTestPathCORS() + "?credentials=true");
  //    xmlHttpRequest.setWithCredentials(false);
  //    runAsyncTask(xmlHttpRequest::send);
  //  }
  //
  //  @DoNotRunWith(Platform.HtmlUnitBug)
  //  public void testSetResponseTypeAsResponseType() {
  //    xmlHttpRequest.setOnReadyStateChange(
  //        xhr -> {
  //          if (xhr.getReadyState() == DONE) {
  //            assertEquals("arraybuffer", xhr.getResponseType());
  //            assertEquals(12, xhr.getResponseArrayBuffer().byteLength());
  //            assertEquals("test content", arrayBufferToString(xhr));
  //            finishTest();
  //          }
  //        });
  //    xmlHttpRequest.open("GET", getTestPathCORS());
  //    xmlHttpRequest.setResponseType(XMLHttpRequest.ResponseType.ArrayBuffer);
  //    runAsyncTask(xmlHttpRequest::send);
  //  }
  //
  //  @DoNotRunWith(Platform.HtmlUnitBug)
  //  public void testSetResponseTypeAsString() {
  //    xmlHttpRequest.setOnReadyStateChange(
  //        xhr -> {
  //          if (xhr.getReadyState() == DONE) {
  //            assertEquals("arraybuffer", xhr.getResponseType());
  //            assertEquals(12, xhr.getResponseArrayBuffer().byteLength());
  //            assertEquals("test content", arrayBufferToString(xhr));
  //            finishTest();
  //          }
  //        });
  //    xmlHttpRequest.open("GET", getTestPathCORS());
  //    xmlHttpRequest.setResponseType("arraybuffer");
  //    runAsyncTask(xmlHttpRequest::send);
  //  }
  //
  //  @DoNotRunWith(Platform.HtmlUnitBug)
  //  public void testAbortRequest() {
  //    xmlHttpRequest.setOnReadyStateChange(
  //        xhr -> {
  //          if (xhr.getReadyState() == DONE) {
  //            assertEquals(0, xhr.getStatus());
  //            finishTest();
  //          }
  //        });
  //    xmlHttpRequest.open("GET", getTestPath());
  //    runAsyncTask(
  //        () -> {
  //          xmlHttpRequest.send();
  //          xmlHttpRequest.abort();
  //        });
  //  }
  //
  //  public void testClearOnReadyStateChange() {
  //    xmlHttpRequest.setOnReadyStateChange(
  //        xhr -> {
  //          fail();
  //        });
  //    xmlHttpRequest.clearOnReadyStateChange();
  //    xmlHttpRequest.open("GET", getTestPath());
  //    runAsyncTask(xmlHttpRequest::send);
  //    finishTest();
  //  }
}
