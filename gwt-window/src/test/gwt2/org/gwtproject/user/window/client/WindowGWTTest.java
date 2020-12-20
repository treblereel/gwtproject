/*
 * Copyright © 2020 The GWT Project Authors
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

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.junit.DoNotRunWith;
import com.google.gwt.junit.Platform;
import org.gwtproject.event.shared.HandlerRegistration;

public class WindowGWTTest extends WindowTest {

  @DoNotRunWith(Platform.HtmlUnitBug)
  public void testGetClientSize() {
    GetClientSizeTestData testData = setup_testGetClientSize();
    if (testData == null) {
      return;
    }

    delayTestFinish(TEST_GET_CLIENT_SIZE_TIMEOUT);
    Scheduler.get()
        .scheduleDeferred(
            () -> {
              verify_testGetClientSize(testData);
              finishTest();
            });
  }

  public void testResizing() {
    TestResizeHandler handler = new TestResizeHandler();
    HandlerRegistration handlerRegistration = setup_testResizing(handler);
    if (handlerRegistration == null) {
      return;
    }

    delayTestFinish(TEST_RESIZING_TIMEOUT);
    Scheduler.get()
        .scheduleFixedDelay(
            () -> {
              boolean done = verify_testResizing(handler, handlerRegistration);
              if (done) {
                finishTest();
              }
              return done;
            },
            TEST_RESIZING_DELAY);
  }

  @DoNotRunWith(Platform.HtmlUnitBug)
  public void testScrolling() {
    doTestScrolling();
  }
}
