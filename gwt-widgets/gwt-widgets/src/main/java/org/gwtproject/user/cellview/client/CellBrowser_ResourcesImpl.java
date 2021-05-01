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
package org.gwtproject.user.cellview.client;

import org.gwtproject.resources.client.ResourcePrototype;

public class CellBrowser_ResourcesImpl implements CellBrowser.Resources {
  private static CellBrowser_ResourcesImpl _instance0 = new CellBrowser_ResourcesImpl();

  private void cellBrowserClosedInitializer() {
    cellBrowserClosed =
        new org.gwtproject.resources.client.impl.ImageResourcePrototype(
            "cellBrowserClosed",
            org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage),
            0,
            0,
            16,
            16,
            false,
            false);
  }

  private static class cellBrowserClosedInitializer {
    static {
      _instance0.cellBrowserClosedInitializer();
    }

    static org.gwtproject.resources.client.ImageResource get() {
      return cellBrowserClosed;
    }
  }

  public org.gwtproject.resources.client.ImageResource cellBrowserClosed() {
    return cellBrowserClosedInitializer.get();
  }

  private void cellBrowserOpenInitializer() {
    cellBrowserOpen =
        new org.gwtproject.resources.client.impl.ImageResourcePrototype(
            "cellBrowserOpen",
            org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage0),
            0,
            0,
            16,
            16,
            false,
            false);
  }

  private static class cellBrowserOpenInitializer {
    static {
      _instance0.cellBrowserOpenInitializer();
    }

    static org.gwtproject.resources.client.ImageResource get() {
      return cellBrowserOpen;
    }
  }

  public org.gwtproject.resources.client.ImageResource cellBrowserOpen() {
    return cellBrowserOpenInitializer.get();
  }

  private void cellBrowserOpenBackgroundInitializer() {
    cellBrowserOpenBackground =
        new org.gwtproject.resources.client.impl.ImageResourcePrototype(
            "cellBrowserOpenBackground",
            org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage1),
            0,
            0,
            82,
            26,
            false,
            false);
  }

  private static class cellBrowserOpenBackgroundInitializer {
    static {
      _instance0.cellBrowserOpenBackgroundInitializer();
    }

    static org.gwtproject.resources.client.ImageResource get() {
      return cellBrowserOpenBackground;
    }
  }

  public org.gwtproject.resources.client.ImageResource cellBrowserOpenBackground() {
    return cellBrowserOpenBackgroundInitializer.get();
  }

  private void cellBrowserSelectedBackgroundInitializer() {
    cellBrowserSelectedBackground =
        new org.gwtproject.resources.client.impl.ImageResourcePrototype(
            "cellBrowserSelectedBackground",
            org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage2),
            0,
            0,
            82,
            26,
            false,
            false);
  }

  private static class cellBrowserSelectedBackgroundInitializer {
    static {
      _instance0.cellBrowserSelectedBackgroundInitializer();
    }

    static org.gwtproject.resources.client.ImageResource get() {
      return cellBrowserSelectedBackground;
    }
  }

  public org.gwtproject.resources.client.ImageResource cellBrowserSelectedBackground() {
    return cellBrowserSelectedBackgroundInitializer.get();
  }

  private void cellBrowserStyleInitializer() {
    cellBrowserStyle =
        new CellBrowser.Style() {
          public String getName() {
            return "cellBrowserStyle";
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
            return (".MU1SOAB-h-b,.MU1SOAB-h-e{padding:8px;zoom:1}.MU1SOAB-h-d{background:#ffc}.MU1SOAB-h-g{background:url("
                + (CellBrowser_ResourcesImpl.this
                    .cellBrowserSelectedBackground()
                    .getSafeUri()
                    .asString())
                + ") "
                + ("-"
                    + CellBrowser_ResourcesImpl.this.cellBrowserSelectedBackground().getLeft()
                    + "px")
                + " "
                + ("-"
                    + CellBrowser_ResourcesImpl.this.cellBrowserSelectedBackground().getTop()
                    + "px")
                + "  repeat;background-color:#628cd5;background-repeat:repeat-x;color:white;height:auto;overflow:hidden}.MU1SOAB-h-f{background:url("
                + (CellBrowser_ResourcesImpl.this
                    .cellBrowserOpenBackground()
                    .getSafeUri()
                    .asString())
                + ") "
                + ("-"
                    + CellBrowser_ResourcesImpl.this.cellBrowserOpenBackground().getLeft()
                    + "px")
                + " "
                + ("-" + CellBrowser_ResourcesImpl.this.cellBrowserOpenBackground().getTop() + "px")
                + "  repeat;background-color:#7b7b7b;background-repeat:repeat-x;color:white;height:auto;overflow:hidden}");
          }

          public String cellBrowserColumn() {
            return "MU1SOAB-h-a";
          }

          public String cellBrowserEvenItem() {
            return "MU1SOAB-h-b";
          }

          public String cellBrowserFirstColumn() {
            return "MU1SOAB-h-c";
          }

          public String cellBrowserKeyboardSelectedItem() {
            return "MU1SOAB-h-d";
          }

          public String cellBrowserOddItem() {
            return "MU1SOAB-h-e";
          }

          public String cellBrowserOpenItem() {
            return "MU1SOAB-h-f";
          }

          public String cellBrowserSelectedItem() {
            return "MU1SOAB-h-g";
          }

          public String cellBrowserWidget() {
            return "MU1SOAB-h-h";
          }
        };
  }

  private static class cellBrowserStyleInitializer {
    static {
      _instance0.cellBrowserStyleInitializer();
    }

    static CellBrowser.Style get() {
      return cellBrowserStyle;
    }
  }

  public CellBrowser.Style cellBrowserStyle() {
    return cellBrowserStyleInitializer.get();
  }

  private static java.util.HashMap<String, ResourcePrototype> resourceMap;
  private static final String externalImage =
      "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAWElEQVR4XmNgGAUYICws7H92Ts5/dHGigYODw38HR8f/ZBsE0oyMYQZNmDCBOMNAmhxBmoEuAdNQ7OTsTJxBMA1wzVAvgfhEGYCukWQvwAwgOxDJ1jiCAQD3hlZxsqImSwAAAABJRU5ErkJggg==";
  private static final String externalImage0 =
      "data:image/gif;base64,R0lGODlhEAAQAOMIAEBAQEBBQUFBQUFCQ1NVVXd3d9PT0////wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAEAAAgALAAAAAAQABAAAAQ2EMlJq704W3OOnkfBedohBMSYHQAQoKp1vIErDARJzadwojpQ4JfqXEyAYrASimWcn6h0WokAADs=";
  private static final String externalImage1 =
      "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFIAAAAaCAYAAAAkJwuaAAAAo0lEQVR4Xu3QQQqCYABE4bm3gZBQ4MKFYFAgJCQYGAkGLoSEhATpP9PUHablLL53gIcQPjQdQgg0HdZ1pemwLG+aDvP8oukwTU+aDuM40nQYhoGmQ98/aDp0946mQ3traTo014amQ32paTpU54qmQ1mWNB2OxxNNh0NR0HTI85ymQ5ZlNB3SNKXpsNvtaTok24SmQxzHNB02UUTTIfrFdB75J1/N6jFfDUlx6QAAAABJRU5ErkJggg==";
  private static final String externalImage2 =
      "data:image/png;base64,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";
  private static org.gwtproject.resources.client.ImageResource cellBrowserClosed;
  private static org.gwtproject.resources.client.ImageResource cellBrowserOpen;
  private static org.gwtproject.resources.client.ImageResource cellBrowserOpenBackground;
  private static org.gwtproject.resources.client.ImageResource cellBrowserSelectedBackground;
  private static CellBrowser.Style cellBrowserStyle;

  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      cellBrowserClosed(),
      cellBrowserOpen(),
      cellBrowserOpenBackground(),
      cellBrowserSelectedBackground(),
      cellBrowserStyle(),
    };
  }

  public ResourcePrototype getResource(String name) {
    if (resourceMap == null) {
      resourceMap = new java.util.HashMap<String, ResourcePrototype>();
      resourceMap.put("cellBrowserClosed", cellBrowserClosed());
      resourceMap.put("cellBrowserOpen", cellBrowserOpen());
      resourceMap.put("cellBrowserOpenBackground", cellBrowserOpenBackground());
      resourceMap.put("cellBrowserSelectedBackground", cellBrowserSelectedBackground());
      resourceMap.put("cellBrowserStyle", cellBrowserStyle());
    }
    return resourceMap.get(name);
  }
}
