/*
 * Copyright 2018 The GWT Project Authors
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
package org.gwtproject.layout.client;


import elemental2.dom.DomGlobal;
import junit.framework.Assert;
import org.gwtproject.layout.client.helper.WindowHelper;
import org.gwtproject.regexp.shared.MatchResult;
import org.gwtproject.regexp.shared.RegExp;

/**
 * Calculates the sizes for Window extras such as border, menu, tool bar, and stores the original
 * sizes to restore at the end of test.
 */
public final class ResizeHelper {

  private static int extraWidth;
  private static int extraHeight;

  static {
    // FF4 on win can start in 'almost' fullscreen when the window title bar
    // is hidden but accounted incorrectly, so, move the window and resize to
    // smaller size first, to take it out of 'full screen mode'.
    WindowHelper.moveTo(10, 10);
    WindowHelper.resizeTo(700, 500);

    extraWidth = 700 - DomGlobal.document.documentElement.clientWidth;
    extraHeight = 500 - DomGlobal.document.documentElement.clientHeight;
  }

  public static void resizeBy(int width, int height) {
    WindowHelper.resizeBy(width, height);
  }

  public static void resizeTo(int width, int height) {
    WindowHelper.resizeTo(width, height);
  }

  public static void assertSize(int width, int height) {
    Assert.assertEquals(width, DomGlobal.document.documentElement.clientWidth + extraWidth);
    Assert.assertEquals(height, DomGlobal.document.documentElement.clientHeight + extraHeight);
  }

  public static boolean isResizeSupported() {
    String userAgent = DomGlobal.window.navigator.userAgent;
    if (userAgent.contains("Chrome")) {
      return false; // All versions of Chrome are upsupported
    }

    if (userAgent.contains("Firefox")) {
      RegExp versionRegExp = RegExp.compile("Firefox[\\/\\s](\\d+)\\.\\d+", "ig");
      MatchResult result = versionRegExp.exec(userAgent);
      if (result != null) {
        int version = Integer.parseInt(result.getGroup(1));
        return version < 7; // Resize is unsupported for Firefox 7 and newer.
      }
    }
    return true;
  }
}

