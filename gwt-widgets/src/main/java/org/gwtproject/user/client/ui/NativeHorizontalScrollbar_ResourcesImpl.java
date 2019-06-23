package org.gwtproject.user.client.ui;

import org.gwtproject.resources.client.ResourcePrototype;

public class NativeHorizontalScrollbar_ResourcesImpl implements NativeHorizontalScrollbar.Resources {
  private static NativeHorizontalScrollbar_ResourcesImpl _instance0 = new NativeHorizontalScrollbar_ResourcesImpl();
  private void nativeHorizontalScrollbarStyleInitializer() {
    nativeHorizontalScrollbarStyle = new NativeHorizontalScrollbar.Style() {
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
        return ("");
      }
      public String nativeHorizontalScrollbar() {
        return "MU1SOAB-i-a";
      }
    }
    ;
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
