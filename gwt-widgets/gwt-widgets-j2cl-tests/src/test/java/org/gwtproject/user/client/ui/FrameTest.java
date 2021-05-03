/*
 * Copyright © 2019 The GWT Project Authors
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
package org.gwtproject.user.client.ui;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.j2cl.junit.apt.J2clTestInput;
import org.gwtproject.event.dom.client.LoadEvent;
import org.gwtproject.event.dom.client.LoadHandler;
import org.gwtproject.user.client.DOM;
import org.gwtproject.user.client.Event;

@J2clTestInput(FrameTest.class)
public class FrameTest extends GWTTestCase {

  private static final int FRAME_LOAD_DELAY = 3000;

  @Override
  public String getModuleName() {
    return "org.gwtproject.user.UserTest";
  }

  public void testOnLoadEventFiresWithBrowerEvent() {
    delayTestFinish(FRAME_LOAD_DELAY);

    org.gwtproject.user.client.ui.Frame frame =
        new org.gwtproject.user.client.ui.Frame() {
          @Override
          public void onBrowserEvent(Event event) {
            if (DOM.eventGetType(event) == Event.ONLOAD) {
              finishTest();
            }
            super.onBrowserEvent(event);
          }
        };

    frame.sinkEvents(Event.ONLOAD);
    org.gwtproject.user.client.ui.RootPanel.get().add(frame);
    frame.setUrl("iframetest.html");
  }

  public void testOnLoadEventFiresWithLoadHandler() {
    delayTestFinish(FRAME_LOAD_DELAY);

    org.gwtproject.user.client.ui.Frame frame = new org.gwtproject.user.client.ui.Frame();
    frame.addLoadHandler(
        new LoadHandler() {
          @Override
          public void onLoad(LoadEvent event) {
            finishTest();
          }
        });

    org.gwtproject.user.client.ui.RootPanel.get().add(frame);
    frame.setUrl("iframetest.html");
  }

  public void testOnLoadEventFiresWithDomLoadHandler() {
    delayTestFinish(FRAME_LOAD_DELAY);

    org.gwtproject.user.client.ui.Frame frame =
        new Frame() {
          {
            addDomHandler(
                new LoadHandler() {
                  @Override
                  public void onLoad(LoadEvent event) {
                    finishTest();
                  }
                },
                LoadEvent.getType());
          }
        };

    RootPanel.get().add(frame);
    frame.setUrl("iframetest.html");
  }
}
