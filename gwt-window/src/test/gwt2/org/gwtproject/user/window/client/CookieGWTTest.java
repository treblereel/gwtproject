/*
 * Copyright 2020 The GWT Project Authors
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
package org.gwtproject.user.window.client;

import com.google.gwt.junit.DoNotRunWith;
import com.google.gwt.junit.Platform;
import com.google.gwt.user.client.Timer;
import java.util.Date;

public class CookieGWTTest extends CookieTest {

  // HTMLUnit doesn't match browsers in terms of the order of cookies.
  @DoNotRunWith(Platform.HtmlUnitUnknown)
  public void testCookiesWithTheSameName() {
    // Make the cookie expire in one minute, so that they don't hang around
    // past the end of this test.
    Date expires = new Date(getClientTime() + (60 * 1000));

    // Given multiple cookies with the same name, we should pick the cookie with the longest
    // path.
    Cookies.setCookie("token", "root", expires, null, "/", false);
    Cookies.setCookie(
        "token",
        "longest",
        expires,
        null,
        "/org.gwtproject.user.window.Window.JUnit/junit.html",
        false);
    Cookies.setCookie(
        "token", "middle", expires, null, "/org.gwtproject.user.window.Window.JUnit/", false);
    assertEquals("longest", Cookies.getCookie("token"));
  }

  public void testExpires() {
    int uniqueId = setup_testExpires();
    delayTestFinish(TEST_EXPIRES_TIMEOUT);
    // Wait until the cookie expires before checking it
    Timer timer =
        new Timer() {
          @Override
          public void run() {
            verify_testExpires(uniqueId);

            // Finish the test
            finishTest();
          }
        };
    timer.schedule(TEST_EXPIRES_DELAY);
  }
}
