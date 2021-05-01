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

import org.gwtproject.i18n.shared.cldr.LocaleInfo;
import org.gwtproject.resources.client.ResourcePrototype;

public class CellTree_ResourcesImpl implements CellTree.Resources {
  private static CellTree_ResourcesImpl _instance0 = new CellTree_ResourcesImpl();

  private void cellTreeClosedItemInitializer() {
    cellTreeClosedItem =
        new org.gwtproject.resources.client.impl.ImageResourcePrototype(
            "cellTreeClosedItem",
            org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage),
            0,
            0,
            15,
            15,
            false,
            false);
  }

  private static class cellTreeClosedItemInitializer {
    static {
      _instance0.cellTreeClosedItemInitializer();
    }

    static org.gwtproject.resources.client.ImageResource get() {
      return cellTreeClosedItem;
    }
  }

  public org.gwtproject.resources.client.ImageResource cellTreeClosedItem() {
    return cellTreeClosedItemInitializer.get();
  }

  private void cellTreeLoadingInitializer() {
    cellTreeLoading =
        new org.gwtproject.resources.client.impl.ImageResourcePrototype(
            "cellTreeLoading",
            org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage0),
            0,
            0,
            16,
            16,
            true,
            false);
  }

  private static class cellTreeLoadingInitializer {
    static {
      _instance0.cellTreeLoadingInitializer();
    }

    static org.gwtproject.resources.client.ImageResource get() {
      return cellTreeLoading;
    }
  }

  public org.gwtproject.resources.client.ImageResource cellTreeLoading() {
    return cellTreeLoadingInitializer.get();
  }

  private void cellTreeOpenItemInitializer() {
    cellTreeOpenItem =
        new org.gwtproject.resources.client.impl.ImageResourcePrototype(
            "cellTreeOpenItem",
            org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage1),
            0,
            0,
            15,
            15,
            false,
            false);
  }

  private static class cellTreeOpenItemInitializer {
    static {
      _instance0.cellTreeOpenItemInitializer();
    }

    static org.gwtproject.resources.client.ImageResource get() {
      return cellTreeOpenItem;
    }
  }

  public org.gwtproject.resources.client.ImageResource cellTreeOpenItem() {
    return cellTreeOpenItemInitializer.get();
  }

  private void cellTreeSelectedBackgroundInitializer() {
    cellTreeSelectedBackground =
        new org.gwtproject.resources.client.impl.ImageResourcePrototype(
            "cellTreeSelectedBackground",
            org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage2),
            0,
            0,
            82,
            26,
            false,
            false);
  }

  private static class cellTreeSelectedBackgroundInitializer {
    static {
      _instance0.cellTreeSelectedBackgroundInitializer();
    }

    static org.gwtproject.resources.client.ImageResource get() {
      return cellTreeSelectedBackground;
    }
  }

  public org.gwtproject.resources.client.ImageResource cellTreeSelectedBackground() {
    return cellTreeSelectedBackgroundInitializer.get();
  }

  private void cellTreeStyleInitializer() {
    cellTreeStyle =
        new CellTree.Style() {
          public String getName() {
            return "cellTreeStyle";
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
            return LocaleInfo.getCurrentLocale().isRTL()
                ? (".MU1SOAB-f-a{padding-right:16px;font-style:italic}.MU1SOAB-f-b{padding-top:4px;padding-bottom:4px;cursor:hand;cursor:pointer;zoom:1}.MU1SOAB-f-d{zoom:1}.MU1SOAB-f-e{padding-right:3px;padding-left:3px;outline:none}.MU1SOAB-f-j{font-weight:bold;color:#4b4a4a;margin-top:20px;padding:3px 10px 3px 13px!important}.MU1SOAB-f-l{border-bottom:1px solid #6f7277;padding-bottom:1px}.MU1SOAB-f-f{background-color:#ffc;outline:none}.MU1SOAB-f-h{background:url("
                    + (CellTree_ResourcesImpl.this
                        .cellTreeSelectedBackground()
                        .getSafeUri()
                        .asString())
                    + ") "
                    + ("-"
                        + CellTree_ResourcesImpl.this.cellTreeSelectedBackground().getLeft()
                        + "px")
                    + " "
                    + ("-"
                        + CellTree_ResourcesImpl.this.cellTreeSelectedBackground().getTop()
                        + "px")
                    + "  repeat-x;background-color:#628cd5;color:white;height:auto;overflow:visible}.MU1SOAB-f-i{padding-right:16px;outline:none}")
                : (".MU1SOAB-f-a{padding-left:16px;font-style:italic}.MU1SOAB-f-b{padding-top:4px;padding-bottom:4px;cursor:hand;cursor:pointer;zoom:1}.MU1SOAB-f-d{zoom:1}.MU1SOAB-f-e{padding-left:3px;padding-right:3px;outline:none}.MU1SOAB-f-j{font-weight:bold;color:#4b4a4a;margin-top:20px;padding:3px 13px 3px 10px!important}.MU1SOAB-f-l{border-bottom:1px solid #6f7277;padding-bottom:1px}.MU1SOAB-f-f{background-color:#ffc;outline:none}.MU1SOAB-f-h{background:url("
                    + (CellTree_ResourcesImpl.this
                        .cellTreeSelectedBackground()
                        .getSafeUri()
                        .asString())
                    + ") "
                    + ("-"
                        + CellTree_ResourcesImpl.this.cellTreeSelectedBackground().getLeft()
                        + "px")
                    + " "
                    + ("-"
                        + CellTree_ResourcesImpl.this.cellTreeSelectedBackground().getTop()
                        + "px")
                    + "  repeat-x;background-color:#628cd5;color:white;height:auto;overflow:visible}.MU1SOAB-f-i{padding-left:16px;outline:none}");
          }

          public String cellTreeEmptyMessage() {
            return "MU1SOAB-f-a";
          }

          public String cellTreeItem() {
            return "MU1SOAB-f-b";
          }

          public String cellTreeItemImage() {
            return "MU1SOAB-f-c";
          }

          public String cellTreeItemImageValue() {
            return "MU1SOAB-f-d";
          }

          public String cellTreeItemValue() {
            return "MU1SOAB-f-e";
          }

          public String cellTreeKeyboardSelectedItem() {
            return "MU1SOAB-f-f";
          }

          public String cellTreeOpenItem() {
            return "MU1SOAB-f-g";
          }

          public String cellTreeSelectedItem() {
            return "MU1SOAB-f-h";
          }

          public String cellTreeShowMoreButton() {
            return "MU1SOAB-f-i";
          }

          public String cellTreeTopItem() {
            return "MU1SOAB-f-j";
          }

          public String cellTreeTopItemImage() {
            return "MU1SOAB-f-k";
          }

          public String cellTreeTopItemImageValue() {
            return "MU1SOAB-f-l";
          }

          public String cellTreeWidget() {
            return "MU1SOAB-f-m";
          }
        };
  }

  private static class cellTreeStyleInitializer {
    static {
      _instance0.cellTreeStyleInitializer();
    }

    static CellTree.Style get() {
      return cellTreeStyle;
    }
  }

  public CellTree.Style cellTreeStyle() {
    return cellTreeStyleInitializer.get();
  }

  private static java.util.HashMap<String, ResourcePrototype> resourceMap;
  private static final String externalImage =
      "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA8AAAAPCAYAAAA71pVKAAAAwElEQVR4XmNgGPogr6KzIbeiM7q+vp4NXY4gAGq8klfZ+Se/onNLflV3DLo8XpBX1XkYaPt/KP4MNGhXTkVXKLo6rCC3svNkXmXXf6AmCIYY8gHooiN55R3+6OpRAFDjKYTmrv9ATWAMFqvo/AoUX46uBw5yK0Ca4YrhtudXdr0E0jOzq3vk0fXAAcjZMNsgNnbeB4bD7PyyTnV0tRggr7zzPFTTXaCmqQVlnZroanACoMYVQOdNz69oN0WXG0YAAC38dueW7qlRAAAAAElFTkSuQmCC";
  private static final String externalImage0 =
      "data:image/gif;base64,R0lGODlhEAAQAPIAAP///255itve4pObqG55iqWstre9xcDFzCH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==";
  private static final String externalImage1 =
      "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA8AAAAPCAYAAAA71pVKAAAAwklEQVR4XmNgGPogt6JzRV555/ncys6TuRVdp/Iqu06B2HlVnYeBclfyKjob0PXAAVByOlDRfxDOqwThLgiG8P8AxaPR9cBBfkW7KVDRXYhmkEaoIUB+fkXnlvr6ejZ0PSgA6MSpYM3IBlR0fs6v6o5BV4sBCso6NYEa7oM0IQzp3IWuDicA2j4bpAmq+UNORVcouhqcIL+sUz2/suslyNlAA46gyxMEQBtnAvHXvPIOf3Q5giC7ukce6Nfl6OLDAAAA2QV25/ovWEQAAAAASUVORK5CYII=";
  private static final String externalImage2 =
      "data:image/png;base64,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";
  private static org.gwtproject.resources.client.ImageResource cellTreeClosedItem;
  private static org.gwtproject.resources.client.ImageResource cellTreeLoading;
  private static org.gwtproject.resources.client.ImageResource cellTreeOpenItem;
  private static org.gwtproject.resources.client.ImageResource cellTreeSelectedBackground;
  private static CellTree.Style cellTreeStyle;

  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      cellTreeClosedItem(),
      cellTreeLoading(),
      cellTreeOpenItem(),
      cellTreeSelectedBackground(),
      cellTreeStyle(),
    };
  }

  public ResourcePrototype getResource(String name) {
    if (resourceMap == null) {
      resourceMap = new java.util.HashMap<String, ResourcePrototype>();
      resourceMap.put("cellTreeClosedItem", cellTreeClosedItem());
      resourceMap.put("cellTreeLoading", cellTreeLoading());
      resourceMap.put("cellTreeOpenItem", cellTreeOpenItem());
      resourceMap.put("cellTreeSelectedBackground", cellTreeSelectedBackground());
      resourceMap.put("cellTreeStyle", cellTreeStyle());
    }
    return resourceMap.get(name);
  }
}
