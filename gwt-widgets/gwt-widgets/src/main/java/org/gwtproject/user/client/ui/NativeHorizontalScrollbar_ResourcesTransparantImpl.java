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

import org.gwtproject.resources.client.ResourcePrototype;

public class NativeHorizontalScrollbar_ResourcesTransparantImpl
    implements NativeHorizontalScrollbar.ResourcesTransparant {
  private static NativeHorizontalScrollbar_ResourcesTransparantImpl _instance0 =
      new NativeHorizontalScrollbar_ResourcesTransparantImpl();

  private void nativeHorizontalScrollbarStyleInitializer() {
    nativeHorizontalScrollbarStyle =
        new NativeHorizontalScrollbar.Style() {
          public String getName() {
            return "nativeHorizontalScrollbarStyle";
          }

          private boolean injected;

          public boolean ensureInjected() {
            if (!injected) {
              injected = true;
              org.gwtproject.dom.client.StyleInjector.inject(getText());
              return true;
            }
            return false;
          }

          public String getText() {
            return (".MU1SOAB-i-a{opacity:.7;filter:alpha(opacity=70);-webkit-transition:opacity 350ms;-moz-transition:opacity 350ms;-o-transition:opacity 350ms;transition:opacity 350ms}.MU1SOAB-i-a:hover{opacity:1;filter:alpha(opacity=100)}");
          }

          public String nativeHorizontalScrollbar() {
            return "MU1SOAB-i-a";
          }
        };
  }

  private static class nativeHorizontalScrollbarStyleInitializer {
    static {
      _instance0.nativeHorizontalScrollbarStyleInitializer();
    }

    static NativeHorizontalScrollbar.Style get() {
      return nativeHorizontalScrollbarStyle;
    }
  }

  public NativeHorizontalScrollbar.Style nativeHorizontalScrollbarStyle() {
    return nativeHorizontalScrollbarStyleInitializer.get();
  }

  private static java.util.HashMap<String, ResourcePrototype> resourceMap;
  private static NativeHorizontalScrollbar.Style nativeHorizontalScrollbarStyle;

  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      nativeHorizontalScrollbarStyle(),
    };
  }

  public ResourcePrototype getResource(String name) {
    if (resourceMap == null) {
      resourceMap = new java.util.HashMap<String, ResourcePrototype>();
      resourceMap.put("nativeHorizontalScrollbarStyle", nativeHorizontalScrollbarStyle());
    }
    return resourceMap.get(name);
  }
}
