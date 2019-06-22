package org.gwtproject.cell.client;

import org.gwtproject.resources.client.ResourcePrototype;

public class ButtonCellBase_DefaultAppearance_ResourcesImpl implements ButtonCellBase.DefaultAppearance.Resources {
  private static ButtonCellBase_DefaultAppearance_ResourcesImpl _instance0 = new ButtonCellBase_DefaultAppearance_ResourcesImpl();
  private void buttonCellBaseBackgroundInitializer() {
    buttonCellBaseBackground = new org.gwtproject.resources.client.impl.ImageResourcePrototype(
      "buttonCellBaseBackground",
      org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage),
      0, 0, 32, 31, false, false
    );
  }
  private static class buttonCellBaseBackgroundInitializer {
    static {
      _instance0.buttonCellBaseBackgroundInitializer();
    }
    static org.gwtproject.resources.client.ImageResource get() {
      return buttonCellBaseBackground;
    }
  }
  public org.gwtproject.resources.client.ImageResource buttonCellBaseBackground() {
    return buttonCellBaseBackgroundInitializer.get();
  }
  private void buttonCellBaseStyleInitializer() {
    buttonCellBaseStyle = new ButtonCellBase.DefaultAppearance.Style() {
      public String getName() {
        return "buttonCellBaseStyle";
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
        return com.google.gwt.i18n.client.LocaleInfo.getCurrentLocale().isRTL() ? (".MU1SOAB-m-a{overflow:hidden;background:url(" + (ButtonCellBase_DefaultAppearance_ResourcesImpl.this.buttonCellBaseBackground().getSafeUri().asString()) + ") " + ("-" + ButtonCellBase_DefaultAppearance_ResourcesImpl.this.buttonCellBaseBackground().getLeft() + "px") + " " + ("-" + ButtonCellBase_DefaultAppearance_ResourcesImpl.this.buttonCellBaseBackground().getTop() + "px") + "  repeat-x;height:auto;background-color:#f1f1f1;margin:0;padding:5px 7px;text-decoration:none;cursor:pointer;cursor:hand;font-size:small;border:1px solid #bbb;border-bottom:1px solid #a0a0a0;border-radius:5px;-moz-border-radius:5px}.MU1SOAB-m-f{background:#666;color:white}.MU1SOAB-m-e{background:#940000;color:white}.MU1SOAB-m-a:hover{border-color:#939393}.MU1SOAB-m-a[disabled]{cursor:default;color:#888}.MU1SOAB-m-a[disabled]:hover{border-color:#bbb #bbb #a0a0a0}.MU1SOAB-m-g{border:1px inset #ccc}.MU1SOAB-m-b{border-top-right-radius:0;border-bottom-right-radius:0;-moz-border-radius-topright:0;-moz-border-radius-bottomright:0}.MU1SOAB-m-c{border-left:none;border-top-left-radius:0;border-bottom-left-radius:0;-moz-border-radius-topleft:0;-moz-border-radius-bottomleft:0}") : (".MU1SOAB-m-a{overflow:hidden;background:url(" + (ButtonCellBase_DefaultAppearance_ResourcesImpl.this.buttonCellBaseBackground().getSafeUri().asString()) + ") " + ("-" + ButtonCellBase_DefaultAppearance_ResourcesImpl.this.buttonCellBaseBackground().getLeft() + "px") + " " + ("-" + ButtonCellBase_DefaultAppearance_ResourcesImpl.this.buttonCellBaseBackground().getTop() + "px") + "  repeat-x;height:auto;background-color:#f1f1f1;margin:0;padding:5px 7px;text-decoration:none;cursor:pointer;cursor:hand;font-size:small;border:1px solid #bbb;border-bottom:1px solid #a0a0a0;border-radius:5px;-moz-border-radius:5px}.MU1SOAB-m-f{background:#666;color:white}.MU1SOAB-m-e{background:#940000;color:white}.MU1SOAB-m-a:hover{border-color:#939393}.MU1SOAB-m-a[disabled]{cursor:default;color:#888}.MU1SOAB-m-a[disabled]:hover{border-color:#bbb #bbb #a0a0a0}.MU1SOAB-m-g{border:1px inset #ccc}.MU1SOAB-m-b{border-top-left-radius:0;border-bottom-left-radius:0;-moz-border-radius-topleft:0;-moz-border-radius-bottomleft:0}.MU1SOAB-m-c{border-right:none;border-top-right-radius:0;border-bottom-right-radius:0;-moz-border-radius-topright:0;-moz-border-radius-bottomright:0}");
      }
      public String buttonCellBase() {
        return "MU1SOAB-m-a";
      }
      public String buttonCellBaseCollapseLeft() {
        return "MU1SOAB-m-b";
      }
      public String buttonCellBaseCollapseRight() {
        return "MU1SOAB-m-c";
      }
      public String buttonCellBaseDefault() {
        return "MU1SOAB-m-d";
      }
      public String buttonCellBaseNegative() {
        return "MU1SOAB-m-e";
      }
      public String buttonCellBasePrimary() {
        return "MU1SOAB-m-f";
      }
      public String buttonCellBasePushing() {
        return "MU1SOAB-m-g";
      }
    }
    ;
  }
  private static class buttonCellBaseStyleInitializer {
    static {
      _instance0.buttonCellBaseStyleInitializer();
    }
    static ButtonCellBase.DefaultAppearance.Style get() {
      return buttonCellBaseStyle;
    }
  }
  public ButtonCellBase.DefaultAppearance.Style buttonCellBaseStyle() {
    return buttonCellBaseStyleInitializer.get();
  }
  private static java.util.HashMap<String, ResourcePrototype> resourceMap;
  private static final String externalImage = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAfCAYAAACGVs+MAAAAL0lEQVR4Xu3OMQ0AAAzDsPJnWwQbjD6OlN+5cWl7ywEAAAAAAAAAAAAAAAAA5oAHQZjT07gbmRUAAAAASUVORK5CYII=";
  private static org.gwtproject.resources.client.ImageResource buttonCellBaseBackground;
  private static ButtonCellBase.DefaultAppearance.Style buttonCellBaseStyle;
  
  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      buttonCellBaseBackground(), 
      buttonCellBaseStyle(), 
    };
  }
  public ResourcePrototype getResource(String name) {
      if (resourceMap == null) {
        resourceMap = new java.util.HashMap<String, ResourcePrototype>();
        resourceMap.put("buttonCellBaseBackground", buttonCellBaseBackground());
        resourceMap.put("buttonCellBaseStyle", buttonCellBaseStyle());
      }
      return resourceMap.get(name);
  }
}
