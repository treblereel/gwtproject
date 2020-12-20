package org.gwtproject.user.window.client;

import static org.gwtproject.user.window.client.WindowTest.TEST_GET_CLIENT_SIZE_TIMEOUT;
import static org.gwtproject.user.window.client.WindowTest.TEST_RESIZING_DELAY;
import static org.gwtproject.user.window.client.WindowTest.TEST_RESIZING_TIMEOUT;
import static org.gwtproject.user.window.client.WindowTest.doTestScrolling;
import static org.gwtproject.user.window.client.WindowTest.setup_testGetClientSize;
import static org.gwtproject.user.window.client.WindowTest.setup_testResizing;
import static org.gwtproject.user.window.client.WindowTest.verify_testGetClientSize;
import static org.gwtproject.user.window.client.WindowTest.verify_testResizing;

import elemental2.dom.DomGlobal;
import elemental2.dom.DomGlobal.SetTimeoutCallbackFn;
import elemental2.promise.Promise;
import org.gwtproject.event.shared.HandlerRegistration;
import org.gwtproject.user.window.client.WindowTest.GetClientSizeTestData;
import org.gwtproject.user.window.client.WindowTest.TestResizeHandler;
import org.junit.Test;

public class WindowJ2clTest {

  private boolean isHtmlUnit() {
    return "htmlunit".equals(System.getProperty("test.webdriver", "htmlunit"));
  }

  @Test(timeout = TEST_GET_CLIENT_SIZE_TIMEOUT)
  public Promise<Void> testGetClientSize() {
    if (isHtmlUnit()) {
      return Promise.resolve((Void) null);
    }

    GetClientSizeTestData testData = setup_testGetClientSize();
    if (testData == null) {
      return null;
    }

    return new Promise<>(
        (resolve, reject) ->
            DomGlobal.setTimeout(
                (SetTimeoutCallbackFn)
                    (Object... p0) -> {
                      try {
                        verify_testGetClientSize(testData);
                        resolve.onInvoke((Void) null);
                      } catch (Throwable throwable) {
                        reject.onInvoke(throwable);
                      }
                    },
                0));
  }

  @Test(timeout = TEST_RESIZING_TIMEOUT)
  public Promise<Void> testResizing() {
    TestResizeHandler handler = new TestResizeHandler();
    HandlerRegistration handlerRegistration = setup_testResizing(handler);
    if (handlerRegistration == null) {
      return Promise.resolve((Void) null);
    }

    return new Promise<>(
        (resolve, reject) ->
            DomGlobal.setTimeout(
                new SetTimeoutCallbackFn() {
                  @Override
                  public void onInvoke(Object... p0) {
                    try {
                      boolean done = verify_testResizing(handler, handlerRegistration);
                      if (done) {
                        resolve.onInvoke((Void) null);
                        return;
                      }
                      DomGlobal.setTimeout(this, 10);
                    } catch (Throwable throwable) {
                      reject.onInvoke(throwable);
                    }
                  }
                },
                TEST_RESIZING_DELAY));
  }

  public void testScrolling() {
    if (isHtmlUnit()) {
      return;
    }

    doTestScrolling();
  }
}
