package org.gwtproject.user.cellview.client;

import org.gwtproject.i18n.shared.cldr.LocaleInfo;
import org.gwtproject.resources.client.ResourcePrototype;

public class DataGrid_ResourcesImpl implements DataGrid.Resources {
  private static DataGrid_ResourcesImpl _instance0 = new DataGrid_ResourcesImpl();
  private void dataGridLoadingInitializer() {
    dataGridLoading = new org.gwtproject.resources.client.impl.ImageResourcePrototype(
      "dataGridLoading",
      org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage),
      0, 0, 43, 11, true, false
    );
  }
  private static class dataGridLoadingInitializer {
    static {
      _instance0.dataGridLoadingInitializer();
    }
    static org.gwtproject.resources.client.ImageResource get() {
      return dataGridLoading;
    }
  }
  public org.gwtproject.resources.client.ImageResource dataGridLoading() {
    return dataGridLoadingInitializer.get();
  }
  private void dataGridSortAscendingInitializer() {
    dataGridSortAscending = new org.gwtproject.resources.client.impl.ImageResourcePrototype(
      "dataGridSortAscending",
      org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage0),
      0, 0, 11, 7, false, false
    );
  }
  private static class dataGridSortAscendingInitializer {
    static {
      _instance0.dataGridSortAscendingInitializer();
    }
    static org.gwtproject.resources.client.ImageResource get() {
      return dataGridSortAscending;
    }
  }
  public org.gwtproject.resources.client.ImageResource dataGridSortAscending() {
    return dataGridSortAscendingInitializer.get();
  }
  private void dataGridSortDescendingInitializer() {
    dataGridSortDescending = new org.gwtproject.resources.client.impl.ImageResourcePrototype(
      "dataGridSortDescending",
      org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage1),
      0, 0, 11, 7, false, false
    );
  }
  private static class dataGridSortDescendingInitializer {
    static {
      _instance0.dataGridSortDescendingInitializer();
    }
    static org.gwtproject.resources.client.ImageResource get() {
      return dataGridSortDescending;
    }
  }
  public org.gwtproject.resources.client.ImageResource dataGridSortDescending() {
    return dataGridSortDescendingInitializer.get();
  }
  private void dataGridStyleInitializer() {
    dataGridStyle = new DataGrid.Style() {
      public String getName() {
        return "dataGridStyle";
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
        return LocaleInfo.getCurrentLocale().isRTL() ? (".MU1SOAB-a-g{border-top:2px solid #6f7277;padding:3px 15px;text-align:right;color:#4b4a4a;text-shadow:#ddf 1px 1px 0;overflow:hidden;white-space:nowrap}.MU1SOAB-a-h{border-bottom:2px solid #6f7277;padding:3px 15px;text-align:right;color:#4b4a4a;text-shadow:#ddf 1px 1px 0;overflow:hidden;white-space:nowrap}.MU1SOAB-a-a{padding:2px 15px;overflow:hidden}.MU1SOAB-a-u{cursor:pointer;cursor:hand}.MU1SOAB-a-u:hover{color:#6c6b6b}.MU1SOAB-a-b{background:#fff}.MU1SOAB-a-c{border:2px solid #fff}.MU1SOAB-a-q{background:#f3f7fb}.MU1SOAB-a-r{border:2px solid #f3f7fb}.MU1SOAB-a-i{background:#eee}.MU1SOAB-a-j{border:2px solid #eee}.MU1SOAB-a-l{background:#ffc}.MU1SOAB-a-m{border:2px solid #ffc}.MU1SOAB-a-s{background:#628cd5;color:white;height:auto;overflow:auto}.MU1SOAB-a-t{border:2px solid #628cd5}.MU1SOAB-a-k{border:2px solid #d7dde8}") : (".MU1SOAB-a-g{border-top:2px solid #6f7277;padding:3px 15px;text-align:left;color:#4b4a4a;text-shadow:#ddf 1px 1px 0;overflow:hidden;white-space:nowrap}.MU1SOAB-a-h{border-bottom:2px solid #6f7277;padding:3px 15px;text-align:left;color:#4b4a4a;text-shadow:#ddf 1px 1px 0;overflow:hidden;white-space:nowrap}.MU1SOAB-a-a{padding:2px 15px;overflow:hidden}.MU1SOAB-a-u{cursor:pointer;cursor:hand}.MU1SOAB-a-u:hover{color:#6c6b6b}.MU1SOAB-a-b{background:#fff}.MU1SOAB-a-c{border:2px solid #fff}.MU1SOAB-a-q{background:#f3f7fb}.MU1SOAB-a-r{border:2px solid #f3f7fb}.MU1SOAB-a-i{background:#eee}.MU1SOAB-a-j{border:2px solid #eee}.MU1SOAB-a-l{background:#ffc}.MU1SOAB-a-m{border:2px solid #ffc}.MU1SOAB-a-s{background:#628cd5;color:white;height:auto;overflow:auto}.MU1SOAB-a-t{border:2px solid #628cd5}.MU1SOAB-a-k{border:2px solid #d7dde8}");
      }
      public String dataGridCell() {
        return "MU1SOAB-a-a";
      }
      public String dataGridEvenRow() {
        return "MU1SOAB-a-b";
      }
      public String dataGridEvenRowCell() {
        return "MU1SOAB-a-c";
      }
      public String dataGridFirstColumn() {
        return "MU1SOAB-a-d";
      }
      public String dataGridFirstColumnFooter() {
        return "MU1SOAB-a-e";
      }
      public String dataGridFirstColumnHeader() {
        return "MU1SOAB-a-f";
      }
      public String dataGridFooter() {
        return "MU1SOAB-a-g";
      }
      public String dataGridHeader() {
        return "MU1SOAB-a-h";
      }
      public String dataGridHoveredRow() {
        return "MU1SOAB-a-i";
      }
      public String dataGridHoveredRowCell() {
        return "MU1SOAB-a-j";
      }
      public String dataGridKeyboardSelectedCell() {
        return "MU1SOAB-a-k";
      }
      public String dataGridKeyboardSelectedRow() {
        return "MU1SOAB-a-l";
      }
      public String dataGridKeyboardSelectedRowCell() {
        return "MU1SOAB-a-m";
      }
      public String dataGridLastColumn() {
        return "MU1SOAB-a-n";
      }
      public String dataGridLastColumnFooter() {
        return "MU1SOAB-a-o";
      }
      public String dataGridLastColumnHeader() {
        return "MU1SOAB-a-p";
      }
      public String dataGridOddRow() {
        return "MU1SOAB-a-q";
      }
      public String dataGridOddRowCell() {
        return "MU1SOAB-a-r";
      }
      public String dataGridSelectedRow() {
        return "MU1SOAB-a-s";
      }
      public String dataGridSelectedRowCell() {
        return "MU1SOAB-a-t";
      }
      public String dataGridSortableHeader() {
        return "MU1SOAB-a-u";
      }
      public String dataGridSortedHeaderAscending() {
        return "MU1SOAB-a-v";
      }
      public String dataGridSortedHeaderDescending() {
        return "MU1SOAB-a-w";
      }
      public String dataGridWidget() {
        return "MU1SOAB-a-x";
      }
    }
    ;
  }
  private static class dataGridStyleInitializer {
    static {
      _instance0.dataGridStyleInitializer();
    }
    static DataGrid.Style get() {
      return dataGridStyle;
    }
  }
  public DataGrid.Style dataGridStyle() {
    return dataGridStyleInitializer.get();
  }
  private static java.util.HashMap<String, ResourcePrototype> resourceMap;
  private static final String externalImage = "data:image/gif;base64,R0lGODlhKwALAPEAAP///0tKSqampktKSiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAKwALAAACMoSOCMuW2diD88UKG95W88uF4DaGWFmhZid93pq+pwxnLUnXh8ou+sSz+T64oCAyTBUAACH5BAkKAAAALAAAAAArAAsAAAI9xI4IyyAPYWOxmoTHrHzzmGHe94xkmJifyqFKQ0pwLLgHa82xrekkDrIBZRQab1jyfY7KTtPimixiUsevAAAh+QQJCgAAACwAAAAAKwALAAACPYSOCMswD2FjqZpqW9xv4g8KE7d54XmMpNSgqLoOpgvC60xjNonnyc7p+VKamKw1zDCMR8rp8pksYlKorgAAIfkECQoAAAAsAAAAACsACwAAAkCEjgjLltnYmJS6Bxt+sfq5ZUyoNJ9HHlEqdCfFrqn7DrE2m7Wdj/2y45FkQ13t5itKdshFExC8YCLOEBX6AhQAADsAAAAAAAAAAAA=";
  private static final String externalImage0 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAsAAAAHCAYAAADebrddAAAAjUlEQVR4XmNgwALyKrumFRf3iKCLY4D8yq4qoOL/eRWd29HlUEBeeYdNXmXnfzCuAON6dDVgALI2t6LzMUhRLkghRNO/vKpOR3S1DLmVnTsgCrqgGGp6Zeez/KpWcbhCoO4mJEls+EBCfT0HQ15pl2pueedZoNUXQDRQ4jKQfTkPhCvB+HxuRdd1oM0hAPwcZIjP6ejiAAAAAElFTkSuQmCC";
  private static final String externalImage1 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAsAAAAHCAYAAADebrddAAAAj0lEQVR4XmPIrewMya3oup5X2XkeiC/nVXRezgViEDu3vPMskH0BROeVdqkyJNTXcwAlDgDxfwxcAaWrOpsYYCC/qlUcKPgMLlnZBcWd/4E274ArhAGgbkeg5D+wApCmCjD9uLi4RwRdLRgAFdQjTAfi8g4bdDUoAKh4O8gJ+ZVdVehyGABkLVDxNHRxEAAAkCpkiGG1rZcAAAAASUVORK5CYII=";
  private static org.gwtproject.resources.client.ImageResource dataGridLoading;
  private static org.gwtproject.resources.client.ImageResource dataGridSortAscending;
  private static org.gwtproject.resources.client.ImageResource dataGridSortDescending;
  private static DataGrid.Style dataGridStyle;
  
  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      dataGridLoading(), 
      dataGridSortAscending(), 
      dataGridSortDescending(), 
      dataGridStyle(), 
    };
  }
  public ResourcePrototype getResource(String name) {
      if (resourceMap == null) {
        resourceMap = new java.util.HashMap<String, ResourcePrototype>();
        resourceMap.put("dataGridLoading", dataGridLoading());
        resourceMap.put("dataGridSortAscending", dataGridSortAscending());
        resourceMap.put("dataGridSortDescending", dataGridSortDescending());
        resourceMap.put("dataGridStyle", dataGridStyle());
      }
      return resourceMap.get(name);
  }
}
