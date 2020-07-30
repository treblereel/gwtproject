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
package org.gwtproject.user.history.client;

import static elemental2.dom.DomGlobal.document;
import static elemental2.dom.DomGlobal.setTimeout;
import static elemental2.dom.DomGlobal.window;

import com.google.gwt.junit.DoNotRunWith;
import com.google.gwt.junit.Platform;
import com.google.gwt.junit.client.GWTTestCase;
import elemental2.dom.DomGlobal;
import elemental2.dom.DomGlobal.SetTimeoutCallbackFn;
import elemental2.dom.Element;
import elemental2.dom.HTMLAnchorElement;
import elemental2.dom.HTMLInputElement;
import java.util.ArrayList;
import jsinterop.base.Js;
import org.gwtproject.event.logical.shared.ValueChangeEvent;
import org.gwtproject.event.logical.shared.ValueChangeHandler;
import org.gwtproject.event.shared.HandlerRegistration;

/**
 * Tests for the history system.
 *
 * <p>TODO: find a way to test unescaping of the initial hash value.
 */
public class HistoryTest extends GWTTestCase {

  private static String getCurrentLocationHash() {
    String hash = window.location.hash;
    if (hash.isEmpty()) {
      fail("can not read history token");
    }
    return hash.substring(1);
  }

  private HandlerRegistration handlerRegistration;
  private Double timer;

  @Override
  public String getModuleName() {
    return "org.gwtproject.user.history.History";
  }

  // TODO(dankurka): Fix up HTML unit hash change handling
  @DoNotRunWith(Platform.HtmlUnitUnknown)
  public void testClickLink() {
    HTMLAnchorElement anchorElement = (HTMLAnchorElement) document.createElement("a");
    anchorElement.href = "#href1";
    document.body.appendChild(anchorElement);

    try {
      History.newItem("something_as_base");

      addHistoryListenerImpl(
          event -> {
            assertEquals("href1", event.getValue());
            finishTest();
          });

      delayTestFinish(5000);

      // Element is missing click, so unchecked-casting to HTMLInputElement which has it
      // https://github.com/google/elemental2/issues/86
      Js.<HTMLInputElement>uncheckedCast(anchorElement).click();

    } finally {
      document.body.removeChild(anchorElement);
    }
  }

  /* Tests against issue #572: Double unescaping of history tokens. */
  public void testDoubleEscaping() {
    final String escToken = "%24%24%24";

    delayTestFinish(5000);
    addHistoryListenerImpl(
        event -> {
          assertEquals(escToken, event.getValue());
          finishTest();
        });
    History.newItem(escToken);
  }

  /*
   * Tests against issue #879: Ensure that empty history tokens do not add
   * additional characters after the '#' symbol in the URL.
   */
  public void testEmptyHistoryTokens() {
    delayTestFinish(5000);

    addHistoryListenerImpl(
        event -> {
          String historyToken = event.getValue();
          if (historyToken == null) {
            fail("historyToken should not be null");
          }

          if (historyToken.equals("foobar")) {
            History.newItem("");
          } else {
            assertEquals("", historyToken);
            finishTest();
          }
        });

    // We must first start out with a non-blank history token. Adding a blank
    // history token in the initial state will not cause an onHistoryChanged
    // event to fire.
    History.newItem("foobar");
  }

  /** Verify that no events are issued via newItem if there were not reqeuested. */
  public void testNoEvents() {
    delayTestFinish(5000);

    addHistoryListenerImpl(event -> fail("onHistoryChanged should not have been called"));

    History.newItem("testNoEvents", false);

    timer = setTimeout((Object... p0) -> finishTest(), 500);
  }

  /*
   * Ensure that non-url-safe strings (such as those containing spaces) are
   * encoded/decoded correctly, and that programmatic 'back' works.
   */
  @DoNotRunWith(Platform.HtmlUnitUnknown)
  public void testHistory() {
    /*
     * Sentinel token which should only be seen if tokens are lost during the
     * rest of the test. Without this, History.back() might send the browser too
     * far back, i.e. back to before the web app containing our test module.
     */
    History.newItem("if-you-see-this-then-history-went-back-too-far");

    final String historyToken1 = "token1";
    final String historyToken2 = "token 2";
    delayTestFinish(10000);

    addHistoryListenerImpl(
        new ValueChangeHandler<String>() {

          private int state = 0;

          @Override
          public void onValueChange(ValueChangeEvent<String> event) {
            String historyToken = event.getValue();
            switch (state) {
              case 0:
                {
                  if (!historyToken.equals(historyToken1)) {
                    fail("Expecting token '" + historyToken1 + "', but got: " + historyToken);
                  }

                  state = 1;
                  History.newItem(historyToken2);
                  break;
                }

              case 1:
                {
                  if (!historyToken.equals(historyToken2)) {
                    fail("Expecting token '" + historyToken2 + "', but got: " + historyToken);
                  }

                  state = 2;
                  History.back();
                  break;
                }

              case 2:
                {
                  if (!historyToken.equals(historyToken1)) {
                    fail("Expecting token '" + historyToken1 + "', but got: " + historyToken);
                  }
                  finishTest();
                  break;
                }
            }
          }
        });

    History.newItem(historyToken1);
  }

  /**
   * Verify that {@link ValueChangeHandler#onValueChange(ValueChangeEvent)} is only called once per
   * {@link History#newItem(String)}.
   */
  public void testHistoryChangedCount() {
    delayTestFinish(5000);
    final SetTimeoutCallbackFn callback =
        new SetTimeoutCallbackFn() {
          private int count = 0;

          @Override
          public void onInvoke(Object... p0) {
            if (count++ == 0) {
              // verify that duplicates don't issue another event
              History.newItem("testHistoryChangedCount");
              timer = setTimeout(this, 500);
            } else {
              finishTest();
            }
          }
        };
    timer = setTimeout(callback);
    addHistoryListenerImpl(
        new ValueChangeHandler<String>() {
          private int count = 0;

          @Override
          public void onValueChange(ValueChangeEvent<String> event) {
            if (count++ != 0) {
              fail("onHistoryChanged called multiple times");
            }
            // wait 500ms to see if we get called multiple times
            timer = setTimeout(callback, 500);
          }
        });

    History.newItem("testHistoryChangedCount");
  }

  @DoNotRunWith(Platform.HtmlUnitUnknown)
  public void testReplaceItem() {
    /*
     * Sentinel token which should only be seen if tokens are lost during the rest of the test.
     * Without this, History.back() might send the browser too far back, i.e. back to before the web
     * app containing our test module.
     */
    History.newItem("if-you-see-this-then-history-went-back-too-far");

    final String historyToken1 = "token1";
    final String historyToken2 = "token 2";
    final String historyToken3 = "token3";

    delayTestFinish(10000);

    addHistoryListenerImpl(
        new ValueChangeHandler<String>() {

          private int state = 0;

          @Override
          public void onValueChange(ValueChangeEvent<String> event) {
            String historyToken = event.getValue();
            switch (state) {
              case 0:
                {
                  if (!historyToken.equals(historyToken1)) {
                    fail("Expecting token '" + historyToken1 + "', but got: " + historyToken);
                  }

                  state = 1;
                  History.newItem(historyToken2);
                  break;
                }

              case 1:
                {
                  if (!historyToken.equals(historyToken2)) {
                    fail("Expecting token '" + historyToken2 + "', but got: " + historyToken);
                  }

                  state = 2;
                  History.replaceItem(historyToken3, true);
                  break;
                }

              case 2:
                {
                  if (!historyToken.equals(historyToken3)) {
                    fail("Expecting token '" + historyToken3 + "', but got: " + historyToken);
                  }
                  state = 3;
                  History.back();
                  break;
                }

              case 3:
                {
                  if (!historyToken.equals(historyToken1)) {
                    fail("Expecting token '" + historyToken1 + "', but got: " + historyToken);
                  }
                  finishTest();
                }
            }
          }
        });

    History.newItem(historyToken1);
  }

  /*
   * HtmlUnit tends to fire events after adding a new item with fireEvent set
   * to false. This makes 'testEmptyHistoryTokens' randomly fail after running
   * this test.
   */
  @DoNotRunWith(Platform.HtmlUnitBug)
  public void testReplaceItemNoEvent() {
    /*
     * Sentinel token which should only be seen if tokens are lost during the rest of the test.
     * Without this, History.back() might send the browser too far back, i.e. back to before the web
     * app containing our test module.
     */
    History.newItem("if-you-see-this-then-history-went-back-too-far");

    final String historyToken1 = "token1";
    final String historyToken2 = "token 2";
    final String historyToken2_encoded = "token%202";

    History.newItem(historyToken1);

    addHistoryListenerImpl(event -> fail("No event expected"));

    History.replaceItem(historyToken2, false);
    assertEquals(historyToken2, History.getToken());

    delayTestFinish(500);

    timer =
        setTimeout(
            (Object... p0) -> {
              // Make sure that we have updated the URL properly.
              assertEquals(historyToken2_encoded, getCurrentLocationHash());
              finishTest();
            },
            200);
  }

  public void testTokenEscaping() {
    final String shouldBeEncoded = "% ^[]|\"<>{}\\";
    final String shouldBeEncodedAs = "%25%20%5E%5B%5D%7C%22%3C%3E%7B%7D%5C";

    delayTestFinish(5000);
    addHistoryListenerImpl(
        event -> {
          assertEquals(shouldBeEncodedAs, getCurrentLocationHash());
          assertEquals(shouldBeEncoded, event.getValue());
          finishTest();
        });
    History.newItem(shouldBeEncoded);
  }

  /**
   * Test to make sure that there is no double unescaping of hash values. See
   * https://bugzilla.mozilla.org/show_bug.cgi?id=483304
   */
  @DoNotRunWith(Platform.HtmlUnitUnknown)
  public void testNoDoubleTokenUnEscaping() {
    final String shouldBeEncoded = "abc%20abc";

    delayTestFinish(5000);

    History.newItem(shouldBeEncoded);
    History.newItem("someOtherToken");
    History.back();
    // allow browser to update the url
    timer =
        setTimeout(
            (Object... p0) -> {
              // make sure that value in url actually matches the original token
              assertEquals(shouldBeEncoded, History.getToken());
              finishTest();
            },
            200);
  }

  /*
   * HtmlUnit reports:
   *   expected=abc;,/?:@&=+$-_.!~*()ABC123foo
   *   actual  =abc;,/?:@&=%20$-_.!~*()ABC123foo
   */
  @DoNotRunWith(Platform.HtmlUnitBug)
  public void testTokenNonescaping() {
    final String shouldNotChange = "abc;,/?:@&=+$-_.!~*()ABC123foo";

    delayTestFinish(5000);
    addHistoryListenerImpl(
        event -> {
          assertEquals(shouldNotChange, event.getValue());
          finishTest();
        });

    History.newItem(shouldNotChange);
  }

  /*
   * Test against issue #2500. IE6 has a bug that causes it to not report any
   * part of the current fragment after a '?' when read from location.hash; make
   * sure that on affected browsers, we're not relying on this.
   */
  public void testTokenWithQuestionmark() {
    delayTestFinish(5000);
    final String token = "foo?bar";

    addHistoryListenerImpl(
        event -> {
          String historyToken = event.getValue();
          if (historyToken == null) {
            fail("historyToken should not be null");
          }
          assertEquals(token, historyToken);
          finishTest();
        });

    History.newItem(token);
  }

  /**
   * Test that using an empty history token works properly. There have been problems (see issue
   * 2905) with this in the past on Safari.
   *
   * <p>Seems like a HtmlUnit bug. Need more investigation.
   */
  @DoNotRunWith(Platform.HtmlUnitBug)
  public void testEmptyHistoryToken() {
    final ArrayList<Object> counter = new ArrayList<>();

    addHistoryListenerImpl(
        event -> {
          counter.add(new Object());
          assertFalse("Browser is borked by empty history token", isBorked());
        });

    History.newItem("x");
    History.newItem("");

    assertEquals("Expected two history events", 2, counter.size());
  }

  // Used by testEmptyHistoryToken() to catch a bizarre failure mode on Safari.
  private static boolean isBorked() {
    Element e = document.createElement("div");
    e.innerHTML = "string";
    return e.innerHTML.length() == 0;
  }

  @Override
  protected void gwtTearDown() throws Exception {
    if (handlerRegistration != null) {
      handlerRegistration.removeHandler();
      handlerRegistration = null;
    }
    if (timer != null) {
      DomGlobal.clearTimeout(timer);
      timer = null;
    }
  }

  private void addHistoryListenerImpl(ValueChangeHandler<String> handler) {
    this.handlerRegistration = History.addValueChangeHandler(handler);
  }
}
