package org.gwtproject.user.window.client;

import static org.gwtproject.user.window.client.CookieTest.TEST_EXPIRES_DELAY;
import static org.gwtproject.user.window.client.CookieTest.TEST_EXPIRES_TIMEOUT;
import static org.gwtproject.user.window.client.CookieTest.getClientTime;
import static org.gwtproject.user.window.client.CookieTest.setup_testExpires;
import static org.gwtproject.user.window.client.CookieTest.verify_testExpires;
import static org.junit.Assert.assertEquals;

import elemental2.dom.DomGlobal;
import elemental2.promise.Promise;
import java.util.Date;
import org.junit.Test;

public class CookieJ2clTest {

  public void testCookiesWithTheSameName() {
    if ("htmlunit".equals(System.getProperty("test.webdriver", "htmlunit"))) {
      // HTMLUnit doesn't match browsers in terms of the order of cookies.
      return;
    }

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
        "/gwt-window-j2cl-tests/test-org.gwtproject.user.window.client.CookieJ2clTest.html",
        false);
    Cookies.setCookie(
        "token",
        "middle",
        expires,
        null,
        "/gwt-window-j2cl-tests/test-org.gwtproject.user.window.client.CookieJ2clTest.html",
        false);
    assertEquals("longest", Cookies.getCookie("token"));
  }

  @Test(timeout = TEST_EXPIRES_TIMEOUT)
  public Promise<Void> testExpires() {
    int uniqueId = setup_testExpires();
    // Wait until the cookie expires before checking it
    return new Promise<>(
        ((resolve, reject) -> {
          DomGlobal.setTimeout(
              args -> {
                try {
                  verify_testExpires(uniqueId);

                  resolve.onInvoke((Void) null);
                } catch (Throwable throwable) {
                  reject.onInvoke(throwable);
                }
              },
              TEST_EXPIRES_DELAY);
        }));
  }
}
