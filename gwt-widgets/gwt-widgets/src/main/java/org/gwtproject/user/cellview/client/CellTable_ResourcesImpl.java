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

public class CellTable_ResourcesImpl implements CellTable.Resources {
  private static CellTable_ResourcesImpl _instance0 = new CellTable_ResourcesImpl();

  private void cellTableFooterBackgroundInitializer() {
    cellTableFooterBackground =
        new org.gwtproject.resources.client.impl.ImageResourcePrototype(
            "cellTableFooterBackground",
            org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage),
            0,
            0,
            82,
            23,
            false,
            false);
  }

  private static class cellTableFooterBackgroundInitializer {
    static {
      _instance0.cellTableFooterBackgroundInitializer();
    }

    static org.gwtproject.resources.client.ImageResource get() {
      return cellTableFooterBackground;
    }
  }

  public org.gwtproject.resources.client.ImageResource cellTableFooterBackground() {
    return cellTableFooterBackgroundInitializer.get();
  }

  private void cellTableHeaderBackgroundInitializer() {
    cellTableHeaderBackground =
        new org.gwtproject.resources.client.impl.ImageResourcePrototype(
            "cellTableHeaderBackground",
            org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage0),
            0,
            0,
            82,
            23,
            false,
            false);
  }

  private static class cellTableHeaderBackgroundInitializer {
    static {
      _instance0.cellTableHeaderBackgroundInitializer();
    }

    static org.gwtproject.resources.client.ImageResource get() {
      return cellTableHeaderBackground;
    }
  }

  public org.gwtproject.resources.client.ImageResource cellTableHeaderBackground() {
    return cellTableHeaderBackgroundInitializer.get();
  }

  private void cellTableLoadingInitializer() {
    cellTableLoading =
        new org.gwtproject.resources.client.impl.ImageResourcePrototype(
            "cellTableLoading",
            org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage1),
            0,
            0,
            43,
            11,
            true,
            false);
  }

  private static class cellTableLoadingInitializer {
    static {
      _instance0.cellTableLoadingInitializer();
    }

    static org.gwtproject.resources.client.ImageResource get() {
      return cellTableLoading;
    }
  }

  public org.gwtproject.resources.client.ImageResource cellTableLoading() {
    return cellTableLoadingInitializer.get();
  }

  private void cellTableSelectedBackgroundInitializer() {
    cellTableSelectedBackground =
        new org.gwtproject.resources.client.impl.ImageResourcePrototype(
            "cellTableSelectedBackground",
            org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage2),
            0,
            0,
            82,
            26,
            false,
            false);
  }

  private static class cellTableSelectedBackgroundInitializer {
    static {
      _instance0.cellTableSelectedBackgroundInitializer();
    }

    static org.gwtproject.resources.client.ImageResource get() {
      return cellTableSelectedBackground;
    }
  }

  public org.gwtproject.resources.client.ImageResource cellTableSelectedBackground() {
    return cellTableSelectedBackgroundInitializer.get();
  }

  private void cellTableSortAscendingInitializer() {
    cellTableSortAscending =
        new org.gwtproject.resources.client.impl.ImageResourcePrototype(
            "cellTableSortAscending",
            org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage3),
            0,
            0,
            11,
            7,
            false,
            false);
  }

  private static class cellTableSortAscendingInitializer {
    static {
      _instance0.cellTableSortAscendingInitializer();
    }

    static org.gwtproject.resources.client.ImageResource get() {
      return cellTableSortAscending;
    }
  }

  public org.gwtproject.resources.client.ImageResource cellTableSortAscending() {
    return cellTableSortAscendingInitializer.get();
  }

  private void cellTableSortDescendingInitializer() {
    cellTableSortDescending =
        new org.gwtproject.resources.client.impl.ImageResourcePrototype(
            "cellTableSortDescending",
            org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage4),
            0,
            0,
            11,
            7,
            false,
            false);
  }

  private static class cellTableSortDescendingInitializer {
    static {
      _instance0.cellTableSortDescendingInitializer();
    }

    static org.gwtproject.resources.client.ImageResource get() {
      return cellTableSortDescending;
    }
  }

  public org.gwtproject.resources.client.ImageResource cellTableSortDescending() {
    return cellTableSortDescendingInitializer.get();
  }

  private void cellTableStyleInitializer() {
    cellTableStyle =
        new CellTable.Style() {
          public String getName() {
            return "cellTableStyle";
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
                ? (".MU1SOAB-d-g{border-top:2px solid #6f7277;padding:3px 15px;text-align:right;color:#4b4a4a;text-shadow:#ddf 1px 1px 0;overflow:hidden}.MU1SOAB-d-h{border-bottom:2px solid #6f7277;padding:3px 15px;text-align:right;color:#4b4a4a;text-shadow:#ddf 1px 1px 0;overflow:hidden}.MU1SOAB-d-a{padding:2px 15px;overflow:hidden}.MU1SOAB-d-v{cursor:pointer;cursor:hand}.MU1SOAB-d-v:hover{color:#6c6b6b}.MU1SOAB-d-b{background:#fff}.MU1SOAB-d-c{border:2px solid #fff}.MU1SOAB-d-r{background:#f3f7fb}.MU1SOAB-d-s{border:2px solid #f3f7fb}.MU1SOAB-d-i{background:#eee}.MU1SOAB-d-j{border:2px solid #eee}.MU1SOAB-d-l{background:#ffc}.MU1SOAB-d-m{border:2px solid #ffc}.MU1SOAB-d-t{background:#628cd5;color:white;height:auto;overflow:auto}.MU1SOAB-d-u{border:2px solid #628cd5}.MU1SOAB-d-k{border:2px solid #d7dde8}.MU1SOAB-d-q{margin:30px}")
                : (".MU1SOAB-d-g{border-top:2px solid #6f7277;padding:3px 15px;text-align:left;color:#4b4a4a;text-shadow:#ddf 1px 1px 0;overflow:hidden}.MU1SOAB-d-h{border-bottom:2px solid #6f7277;padding:3px 15px;text-align:left;color:#4b4a4a;text-shadow:#ddf 1px 1px 0;overflow:hidden}.MU1SOAB-d-a{padding:2px 15px;overflow:hidden}.MU1SOAB-d-v{cursor:pointer;cursor:hand}.MU1SOAB-d-v:hover{color:#6c6b6b}.MU1SOAB-d-b{background:#fff}.MU1SOAB-d-c{border:2px solid #fff}.MU1SOAB-d-r{background:#f3f7fb}.MU1SOAB-d-s{border:2px solid #f3f7fb}.MU1SOAB-d-i{background:#eee}.MU1SOAB-d-j{border:2px solid #eee}.MU1SOAB-d-l{background:#ffc}.MU1SOAB-d-m{border:2px solid #ffc}.MU1SOAB-d-t{background:#628cd5;color:white;height:auto;overflow:auto}.MU1SOAB-d-u{border:2px solid #628cd5}.MU1SOAB-d-k{border:2px solid #d7dde8}.MU1SOAB-d-q{margin:30px}");
          }

          public String cellTableCell() {
            return "MU1SOAB-d-a";
          }

          public String cellTableEvenRow() {
            return "MU1SOAB-d-b";
          }

          public String cellTableEvenRowCell() {
            return "MU1SOAB-d-c";
          }

          public String cellTableFirstColumn() {
            return "MU1SOAB-d-d";
          }

          public String cellTableFirstColumnFooter() {
            return "MU1SOAB-d-e";
          }

          public String cellTableFirstColumnHeader() {
            return "MU1SOAB-d-f";
          }

          public String cellTableFooter() {
            return "MU1SOAB-d-g";
          }

          public String cellTableHeader() {
            return "MU1SOAB-d-h";
          }

          public String cellTableHoveredRow() {
            return "MU1SOAB-d-i";
          }

          public String cellTableHoveredRowCell() {
            return "MU1SOAB-d-j";
          }

          public String cellTableKeyboardSelectedCell() {
            return "MU1SOAB-d-k";
          }

          public String cellTableKeyboardSelectedRow() {
            return "MU1SOAB-d-l";
          }

          public String cellTableKeyboardSelectedRowCell() {
            return "MU1SOAB-d-m";
          }

          public String cellTableLastColumn() {
            return "MU1SOAB-d-n";
          }

          public String cellTableLastColumnFooter() {
            return "MU1SOAB-d-o";
          }

          public String cellTableLastColumnHeader() {
            return "MU1SOAB-d-p";
          }

          public String cellTableLoading() {
            return "MU1SOAB-d-q";
          }

          public String cellTableOddRow() {
            return "MU1SOAB-d-r";
          }

          public String cellTableOddRowCell() {
            return "MU1SOAB-d-s";
          }

          public String cellTableSelectedRow() {
            return "MU1SOAB-d-t";
          }

          public String cellTableSelectedRowCell() {
            return "MU1SOAB-d-u";
          }

          public String cellTableSortableHeader() {
            return "MU1SOAB-d-v";
          }

          public String cellTableSortedHeaderAscending() {
            return "MU1SOAB-d-w";
          }

          public String cellTableSortedHeaderDescending() {
            return "MU1SOAB-d-x";
          }

          public String cellTableWidget() {
            return "MU1SOAB-d-y";
          }
        };
  }

  private static class cellTableStyleInitializer {
    static {
      _instance0.cellTableStyleInitializer();
    }

    static CellTable.Style get() {
      return cellTableStyle;
    }
  }

  public CellTable.Style cellTableStyle() {
    return cellTableStyleInitializer.get();
  }

  private static java.util.HashMap<String, ResourcePrototype> resourceMap;
  private static final String externalImage =
      "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFIAAAAXCAYAAACYuRhEAAAAj0lEQVR4Xu3EWwrCQBQE0d7/ekQEUUQEEQXjgxiMISI+cAW5M/los4f2swtOge4vof32NB2aYaZD/elpOlTvnqZD+co0Hc7PTNPh+Mg0HYphpsP+nmk67NpE02HbJJoOm1vQdFjXiabD6ho0HZZV0HRYXIKmw7wMmg6zsqPpMD0FTYfJMNNhfOhoOoyKoOl+PTDH5SLwRl0AAAAASUVORK5CYII=";
  private static final String externalImage0 =
      "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFIAAAAXCAYAAACYuRhEAAAAj0lEQVR4Xu3EWwrCQBQE0d7/ekQEUUQEEQXjgxiMISI+cAW5M/los4f2swtOge4vof32NB2aYaZD/elpOlTvnqZD+co0Hc7PTNPh+Mg0HYphpsP+nmk67NpE02HbJJoOm1vQdFjXiabD6ho0HZZV0HRYXIKmw7wMmg6zsqPpMD0FTYfJMNNhfOhoOoyKoOl+PTDH5SLwRl0AAAAASUVORK5CYII=";
  private static final String externalImage1 =
      "data:image/gif;base64,R0lGODlhKwALAPEAAP///0tKSqampktKSiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAKwALAAACMoSOCMuW2diD88UKG95W88uF4DaGWFmhZid93pq+pwxnLUnXh8ou+sSz+T64oCAyTBUAACH5BAkKAAAALAAAAAArAAsAAAI9xI4IyyAPYWOxmoTHrHzzmGHe94xkmJifyqFKQ0pwLLgHa82xrekkDrIBZRQab1jyfY7KTtPimixiUsevAAAh+QQJCgAAACwAAAAAKwALAAACPYSOCMswD2FjqZpqW9xv4g8KE7d54XmMpNSgqLoOpgvC60xjNonnyc7p+VKamKw1zDCMR8rp8pksYlKorgAAIfkECQoAAAAsAAAAACsACwAAAkCEjgjLltnYmJS6Bxt+sfq5ZUyoNJ9HHlEqdCfFrqn7DrE2m7Wdj/2y45FkQ13t5itKdshFExC8YCLOEBX6AhQAADsAAAAAAAAAAAA=";
  private static final String externalImage2 =
      "data:image/png;base64,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";
  private static final String externalImage3 =
      "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAsAAAAHCAYAAADebrddAAAAjUlEQVR4XmNgwALyKrumFRf3iKCLY4D8yq4qoOL/eRWd29HlUEBeeYdNXmXnfzCuAON6dDVgALI2t6LzMUhRLkghRNO/vKpOR3S1DLmVnTsgCrqgGGp6Zeez/KpWcbhCoO4mJEls+EBCfT0HQ15pl2pueedZoNUXQDRQ4jKQfTkPhCvB+HxuRdd1oM0hAPwcZIjP6ejiAAAAAElFTkSuQmCC";
  private static final String externalImage4 =
      "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAsAAAAHCAYAAADebrddAAAAj0lEQVR4XmPIrewMya3oup5X2XkeiC/nVXRezgViEDu3vPMskH0BROeVdqkyJNTXcwAlDgDxfwxcAaWrOpsYYCC/qlUcKPgMLlnZBcWd/4E274ArhAGgbkeg5D+wApCmCjD9uLi4RwRdLRgAFdQjTAfi8g4bdDUoAKh4O8gJ+ZVdVehyGABkLVDxNHRxEAAAkCpkiGG1rZcAAAAASUVORK5CYII=";
  private static org.gwtproject.resources.client.ImageResource cellTableFooterBackground;
  private static org.gwtproject.resources.client.ImageResource cellTableHeaderBackground;
  private static org.gwtproject.resources.client.ImageResource cellTableLoading;
  private static org.gwtproject.resources.client.ImageResource cellTableSelectedBackground;
  private static org.gwtproject.resources.client.ImageResource cellTableSortAscending;
  private static org.gwtproject.resources.client.ImageResource cellTableSortDescending;
  private static CellTable.Style cellTableStyle;

  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      cellTableFooterBackground(),
      cellTableHeaderBackground(),
      cellTableLoading(),
      cellTableSelectedBackground(),
      cellTableSortAscending(),
      cellTableSortDescending(),
      cellTableStyle(),
    };
  }

  public ResourcePrototype getResource(String name) {
    if (resourceMap == null) {
      resourceMap = new java.util.HashMap<String, ResourcePrototype>();
      resourceMap.put("cellTableFooterBackground", cellTableFooterBackground());
      resourceMap.put("cellTableHeaderBackground", cellTableHeaderBackground());
      resourceMap.put("cellTableLoading", cellTableLoading());
      resourceMap.put("cellTableSelectedBackground", cellTableSelectedBackground());
      resourceMap.put("cellTableSortAscending", cellTableSortAscending());
      resourceMap.put("cellTableSortDescending", cellTableSortDescending());
      resourceMap.put("cellTableStyle", cellTableStyle());
    }
    return resourceMap.get(name);
  }
}
