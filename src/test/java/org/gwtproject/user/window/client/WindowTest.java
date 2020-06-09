/*
 * Copyright 2008 The GWT Project Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.gwtproject.user.window.client;

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.dom.client.Element;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.gwtproject.event.logical.shared.ResizeEvent;
import org.gwtproject.event.logical.shared.ResizeHandler;
import org.gwtproject.event.shared.HandlerRegistration;
import org.gwtproject.http.client.URL;
import org.gwtproject.http.client.UrlBuilder;
import org.gwtproject.user.window.client.Window.Location;
import org.gwtproject.user.window.client.Window.Navigator;

/** Test Case for {@link Window}. */
public class WindowTest extends GWTTestCase {

  private static native String getNodeName(Element elem) /*-{
    return (elem.nodeName || "").toLowerCase();
  }-*/;

  /** Removes all elements in the body, except scripts and iframes. */
  static void clearBodyContent() {
    Element bodyElem = RootPanel.getBodyElement();

    List<Element> toRemove = new ArrayList<Element>();
    for (int i = 0, n = DOM.getChildCount(bodyElem); i < n; ++i) {
      Element elem = DOM.getChild(bodyElem, i);
      String nodeName = getNodeName(elem);
      if (!"script".equals(nodeName) && !"iframe".equals(nodeName)) {
        toRemove.add(elem);
      }
    }

    for (int i = 0, n = toRemove.size(); i < n; ++i) {
      bodyElem.removeChild(toRemove.get(i));
    }
  }

  @Override
  public String getModuleName() {
    return "org.gwtproject.user.window.Window";
  }

  public void testLocation() {
    // testing reload, replace, and assign seemed to hang our junit harness.
    // Therefore only testing subset of Location that is testable.

    // Use History to get the #hash part of the url into a known state (if the
    // url has somehow been set to http://host/#, location.hash returns the
    // empty string, but location.href includes the trailing hash).
    History.newItem("foo bar");

    // As we have no control over these values we cannot assert much about them.
    String hash = Window.Location.getHash();
    String host = Window.Location.getHost();
    String hostName = Window.Location.getHostName();
    String href = Window.Location.getHref();
    assertNull(Window.Location.getParameter("fuzzy bunny"));
    String path = Window.Location.getPath();
    String port = Window.Location.getPort();
    String protocol = Window.Location.getProtocol();
    String query = Window.Location.getQueryString();

    // Check that the sum is equal to its parts.
    assertEquals(host, hostName + ":" + port);
    assertEquals(href, protocol + "//" + host + path + query + hash);
  }

  public void testLocationCreateUrlBuilder() {
    History.newItem("theHash");
    String expected = Location.getHref();

    // Build the string with the builder.
    UrlBuilder builder = Location.createUrlBuilder();
    String actual = builder.buildString();

    // Check the hash.
    {
      String[] expectedParts = expected.split("#");
      String[] actualParts = actual.split("#");
      assertEquals(2, actualParts.length);
      assertEquals(expectedParts[1], actualParts[1]);
      expected = expectedParts[0];
      actual = actualParts[0];
    }

    // Check the query parameters.
    {
      String[] expectedParts = expected.split("[?]");
      String[] actualParts = actual.split("[?]");
      if (expectedParts.length > 1) {
        assertEquals(2, actualParts.length);
        String[] expectedPairs = expectedParts[1].split("&");
        String[] actualPairs = actualParts[1].split("&");
        assertEquals(expectedPairs.length, actualPairs.length);
        for (String actualPair : actualPairs) {
          String[] kv = actualPair.split("=");
          assertEquals(
              Location.getParameter(kv[0]),
              // has a URL encoded ':' in a parameter
              kv.length > 1 ? URL.decodeQueryString(kv[1]) : "");
        }
      }
      expected = expectedParts[0];
      actual = actualParts[0];
    }

    // Check everything but the query params and hash/
    assertEquals(expected, actual);
  }

  public void testLocationParsing() {
    Map<String, List<String>> map;

    // typical case
    map = Window.Location.buildListParamMap("?fuzzy=bunnies&foo=bar&num=42");
    assertEquals(3, map.size());
    assertEquals("bar", map.get("foo").get(0));
    assertEquals("bunnies", map.get("fuzzy").get(0));

    // multiple values for the same parameter
    map = Window.Location.buildListParamMap("?fuzzy=bunnies&foo=bar&num=42&foo=baz");
    assertEquals(3, map.size());
    assertEquals("bar", map.get("foo").get(0));
    assertEquals("baz", map.get("foo").get(1));

    // no query parameters.
    map = Window.Location.buildListParamMap("");
    assertEquals(0, map.size());

    // blank keys should be ignored, but blank values are OK. Also,
    // keys can contain whitespace. (but the browser may give whitespace
    // back as escaped).
    map = Window.Location.buildListParamMap("?&& &a&b=&c=c&d=d=d&=e&f=2&f=1&");
    assertEquals(6, map.size());
    assertEquals("", map.get(" ").get(0));
    assertEquals("", map.get("a").get(0));
    assertEquals("", map.get("b").get(0));
    assertEquals("c", map.get("c").get(0));
    assertEquals("d=d", map.get("d").get(0));
    assertEquals("2", map.get("f").get(0));
    assertEquals("1", map.get("f").get(1));

    // Values escaped with hex codes should work too.
    map = Window.Location.buildListParamMap("?foo=bar%20baz%3aqux");
    assertEquals("bar baz:qux", map.get("foo").get(0));

    // Malformed URI sequence should not make the app break (issue #7741)
    map = Window.Location.buildListParamMap("?foo=%E4&bar=%3Aqux");
    assertEquals("%E4", map.get("foo").get(0));
    assertEquals(":qux", map.get("bar").get(0));
  }

  public void testNavigator() {
    assertNotNull(Navigator.getAppCodeName());
    assertNotNull(Navigator.getAppName());
    assertNotNull(Navigator.getAppVersion());
    assertNotNull(Navigator.getPlatform());
    assertNotNull(Navigator.getUserAgent());
    assertTrue(Navigator.isCookieEnabled());
    // We don't care if Java is enabled, but need to make sure this call does
    // not throw. The try/catch block keeps the compiled code from being
    // optimized away.
    try {
      Navigator.isJavaEnabled();
    } catch (JavaScriptException e) {
      throw e;
    }
  }

  static final int TEST_GET_CLIENT_SIZE_TIMEOUT = 1000;

  static final class GetClientSizeTestData {
    private int oldClientHeight;
    private int oldClientWidth;
    private Widget largeDom;
  }

  /**
   * Tests the ability of the Window to get the client size correctly with and without visible
   * scroll bars.
   *
   * <p>Caller must ensure this is not running in HtmlUnit, as it failed in all modes due to
   * HtmlUnit bug:
   * https://sourceforge.net/tracker/?func=detail&aid=2944261&group_id=47038&atid=448266
   *
   * @return null if the test should stop.
   */
  static GetClientSizeTestData setup_testGetClientSize() {

    // NOTE: We must clear the DOM here so that previous tests do not pollute
    // our results.
    clearBodyContent();

    GetClientSizeTestData testData = new GetClientSizeTestData();

    // Get the dimensions without any scroll bars
    Window.enableScrolling(false);
    testData.oldClientHeight = Window.getClientHeight();
    testData.oldClientWidth = Window.getClientWidth();
    assertTrue(
        "Expect positive oldClientHeight. " + "This will fail in WebKit if run headless",
        testData.oldClientHeight > 0);
    assertTrue(testData.oldClientWidth > 0);

    // Firefox hides scrollbar if clientHeight < 49 even when it should show.
    // If we are in this case, simply return.
    if (testData.oldClientHeight < 49 && Window.Navigator.getUserAgent().contains("Firefox")) {
      return null;
    }

    // Compare to the dimensions with scroll bars
    Window.enableScrolling(true);
    testData.largeDom = new Label();
    testData.largeDom.setPixelSize(testData.oldClientWidth + 100, testData.oldClientHeight + 100);
    RootPanel.get().add(testData.largeDom);

    return testData;
  }

  static void verify_testGetClientSize(GetClientSizeTestData testData) {
    int newClientHeight = Window.getClientHeight();
    int newClientWidth = Window.getClientWidth();
    assertTrue(newClientHeight < testData.oldClientHeight);
    assertTrue(newClientWidth < testData.oldClientWidth);
    RootPanel.get().remove(testData.largeDom);
  }

  static final class TestResizeHandler implements ResizeHandler {
    private int height;
    private int width;
    private boolean called;

    @Override
    public void onResize(ResizeEvent event) {
      width = event.getWidth();
      height = event.getHeight();
      called = true;
    }

    public int getHeight() {
      return height;
    }

    public int getWidth() {
      return width;
    }

    public boolean isCalled() {
      return called;
    }
  }

  static final int TEST_RESIZING_TIMEOUT = 1000;
  static final int TEST_RESIZING_DELAY = 10;

  /**
   * Tests the ability of resize the Window and catch resize events.
   *
   * @return null if the test should stop.
   */
  static HandlerRegistration setup_testResizing(TestResizeHandler handler) {
    // There is nothing to test if the browser doesn't support resize
    if (!ResizeHelper.isResizeSupported()) {
      return null;
    }

    clearBodyContent();

    final int width = 600;
    final int height = 500;

    final HandlerRegistration handlerRegistration = Window.addResizeHandler(handler);

    ResizeHelper.resizeTo(width, height);
    ResizeHelper.assertSize(width, height);
    ResizeHelper.resizeBy(10, 20);
    ResizeHelper.assertSize(width + 10, height + 20);

    return handlerRegistration;
  }

  static boolean verify_testResizing(
      TestResizeHandler handler, HandlerRegistration handlerRegistration) {
    if (!handler.isCalled()) {
      return true; // we still didn't receive the callback, let's wait more
    }
    assertEquals(Window.getClientWidth(), handler.getWidth());
    assertEquals(Window.getClientHeight(), handler.getHeight());
    handlerRegistration.removeHandler();
    return false;
  }

  /**
   * Tests the ability of scroll the Window and catch scroll events. * *
   *
   * <p>Caller must ensure this is not running in HtmlUnit, as it failed in all modes due to
   * HtmlUnit bug:
   * https://sourceforge.net/tracker/?func=detail&aid=2897457&group_id=47038&atid=448266
   *
   * <p>TODO(flin): it is marked fixed, but is still not fixed.
   */
  protected static void doTestScrolling() {
    // Force scroll bars to appear
    Window.enableScrolling(true);
    int clientHeight = Window.getClientHeight();
    int clientWidth = Window.getClientWidth();
    final Label largeDOM = new Label();
    largeDOM.setPixelSize(clientWidth + 500, clientHeight + 500);
    RootPanel.get().add(largeDOM);

    // Listener for scroll events
    Window.scrollTo(100, 200);
    assertEquals(100, Window.getScrollLeft());
    assertEquals(200, Window.getScrollTop());
    Window.scrollTo(0, 0);
    assertEquals(0, Window.getScrollLeft());
    assertEquals(0, Window.getScrollTop());

    // Cleanup the window
    RootPanel.get().remove(largeDOM);
  }
}
