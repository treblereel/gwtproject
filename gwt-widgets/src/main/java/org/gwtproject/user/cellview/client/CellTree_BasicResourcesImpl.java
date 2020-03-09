package org.gwtproject.user.cellview.client;

import org.gwtproject.i18n.shared.cldr.LocaleInfo;
import org.gwtproject.resources.client.ResourcePrototype;

public class CellTree_BasicResourcesImpl implements CellTree.BasicResources {
  private static CellTree_BasicResourcesImpl _instance0 = new CellTree_BasicResourcesImpl();
  private void cellTreeSelectedBackgroundInitializer() {
    cellTreeSelectedBackground = new org.gwtproject.resources.client.impl.ImageResourcePrototype(
      "cellTreeSelectedBackground",
      org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage),
      0, 0, 82, 26, false, false
    );
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
  private void cellTreeClosedItemInitializer() {
    cellTreeClosedItem = new org.gwtproject.resources.client.impl.ImageResourcePrototype(
      "cellTreeClosedItem",
      org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage0),
      0, 0, 15, 15, false, false
    );
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
    cellTreeLoading = new org.gwtproject.resources.client.impl.ImageResourcePrototype(
      "cellTreeLoading",
      org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage1),
      0, 0, 16, 16, true, false
    );
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
    cellTreeOpenItem = new org.gwtproject.resources.client.impl.ImageResourcePrototype(
      "cellTreeOpenItem",
      org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage2),
      0, 0, 15, 15, false, false
    );
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
  private void cellTreeStyleInitializer() {
    cellTreeStyle = new CellTree.BasicStyle() {
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
        return LocaleInfo.getCurrentLocale().isRTL() ? (".MU1SOAB-e-a{padding-right:16px;font-style:italic}.MU1SOAB-e-b{padding-top:4px;padding-bottom:4px;cursor:hand;cursor:pointer;zoom:1}.MU1SOAB-e-d{zoom:1}.MU1SOAB-e-e{padding-right:3px;padding-left:3px;outline:none}.MU1SOAB-e-f{background-color:#ffc;outline:none}.MU1SOAB-e-h{background:url(" + (CellTree_BasicResourcesImpl.this.cellTreeSelectedBackground().getSafeUri().asString()) + ") " + ("-" + CellTree_BasicResourcesImpl.this.cellTreeSelectedBackground().getLeft() + "px") + " " + ("-" + CellTree_BasicResourcesImpl.this.cellTreeSelectedBackground().getTop() + "px") + "  repeat-x;background-color:#628cd5;color:white;height:auto;overflow:visible}.MU1SOAB-e-i{padding-right:16px;outline:none}") : (".MU1SOAB-e-a{padding-left:16px;font-style:italic}.MU1SOAB-e-b{padding-top:4px;padding-bottom:4px;cursor:hand;cursor:pointer;zoom:1}.MU1SOAB-e-d{zoom:1}.MU1SOAB-e-e{padding-left:3px;padding-right:3px;outline:none}.MU1SOAB-e-f{background-color:#ffc;outline:none}.MU1SOAB-e-h{background:url(" + (CellTree_BasicResourcesImpl.this.cellTreeSelectedBackground().getSafeUri().asString()) + ") " + ("-" + CellTree_BasicResourcesImpl.this.cellTreeSelectedBackground().getLeft() + "px") + " " + ("-" + CellTree_BasicResourcesImpl.this.cellTreeSelectedBackground().getTop() + "px") + "  repeat-x;background-color:#628cd5;color:white;height:auto;overflow:visible}.MU1SOAB-e-i{padding-left:16px;outline:none}");
      }
      public String cellTreeEmptyMessage() {
        return "MU1SOAB-e-a";
      }
      public String cellTreeItem() {
        return "MU1SOAB-e-b";
      }
      public String cellTreeItemImage() {
        return "MU1SOAB-e-c";
      }
      public String cellTreeItemImageValue() {
        return "MU1SOAB-e-d";
      }
      public String cellTreeItemValue() {
        return "MU1SOAB-e-e";
      }
      public String cellTreeKeyboardSelectedItem() {
        return "MU1SOAB-e-f";
      }
      public String cellTreeOpenItem() {
        return "MU1SOAB-e-g";
      }
      public String cellTreeSelectedItem() {
        return "MU1SOAB-e-h";
      }
      public String cellTreeShowMoreButton() {
        return "MU1SOAB-e-i";
      }
      public String cellTreeTopItem() {
        return "MU1SOAB-e-j";
      }
      public String cellTreeTopItemImage() {
        return "MU1SOAB-e-k";
      }
      public String cellTreeTopItemImageValue() {
        return "MU1SOAB-e-l";
      }
      public String cellTreeWidget() {
        return "MU1SOAB-e-m";
      }
    }
    ;
  }
  private static class cellTreeStyleInitializer {
    static {
      _instance0.cellTreeStyleInitializer();
    }
    static CellTree.BasicStyle get() {
      return cellTreeStyle;
    }
  }
  public CellTree.BasicStyle cellTreeStyle() {
    return cellTreeStyleInitializer.get();
  }
  private static java.util.HashMap<String, ResourcePrototype> resourceMap;
  private static final String externalImage = "data:image/png;base64,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";
  private static final String externalImage0 = "data:image/gif;base64,R0lGODlhDwAPAIQaAFhorldnrquz1mFxsvz9/vr6/M3Q2ZGbw5mixvb3+Gp5t2Nys77F4GRzs9ze4mt6uGV1s8/R2VZnrl5usFdortPV2/P09+3u8eXm6lZnrf///wAAzP///////////////yH5BAEAAB8ALAAAAAAPAA8AAAVD4CeOZGmeaEoqWds+58IUWk0IgwlY/MYnEhPlQtwQL8ESBbNpNjEZIWa6mUJNAQfVGi1NEJGK2HCYnCCuVkPFbrtHIQA7";
  private static final String externalImage1 = "data:image/gif;base64,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";
  private static final String externalImage2 = "data:image/gif;base64,R0lGODlhDwAPAIQaAFhorldnrquz1mFxsvz9/vr6/M3Q2ZGbw5mixvb3+Gp5t2Nys77F4GRzs9ze4mt6uGV1s8/R2VZnrl5usFdortPV2/P09+3u8eXm6lZnrf///wAAzP///////////////yH5BAEAAB8ALAAAAAAPAA8AAAVC4CeOZGmeaEoqWds+58IUWk0IgwlY/MUnEhPlQixegiUKZsNkYjJCjPQifZoCjmkVWpogIpWw4TA5QVythmrNbo9CADs=";
  private static org.gwtproject.resources.client.ImageResource cellTreeSelectedBackground;
  private static org.gwtproject.resources.client.ImageResource cellTreeClosedItem;
  private static org.gwtproject.resources.client.ImageResource cellTreeLoading;
  private static org.gwtproject.resources.client.ImageResource cellTreeOpenItem;
  private static CellTree.BasicStyle cellTreeStyle;
  
  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      cellTreeSelectedBackground(), 
      cellTreeClosedItem(), 
      cellTreeLoading(), 
      cellTreeOpenItem(), 
      cellTreeStyle(), 
    };
  }
  public ResourcePrototype getResource(String name) {
      if (resourceMap == null) {
        resourceMap = new java.util.HashMap<String, ResourcePrototype>();
        resourceMap.put("cellTreeSelectedBackground", cellTreeSelectedBackground());
        resourceMap.put("cellTreeClosedItem", cellTreeClosedItem());
        resourceMap.put("cellTreeLoading", cellTreeLoading());
        resourceMap.put("cellTreeOpenItem", cellTreeOpenItem());
        resourceMap.put("cellTreeStyle", cellTreeStyle());
      }
      return resourceMap.get(name);
  }
}
