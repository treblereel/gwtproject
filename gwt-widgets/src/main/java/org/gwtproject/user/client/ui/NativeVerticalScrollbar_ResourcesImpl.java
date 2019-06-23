package org.gwtproject.user.client.ui;

import org.gwtproject.resources.client.ResourcePrototype;

public class NativeVerticalScrollbar_ResourcesImpl implements NativeVerticalScrollbar.Resources {
  private static NativeVerticalScrollbar_ResourcesImpl _instance0 = new NativeVerticalScrollbar_ResourcesImpl();
  private void nativeVerticalScrollbarStyleInitializer() {
    nativeVerticalScrollbarStyle = new NativeVerticalScrollbar.Style() {
      public String getName() {
        return "nativeVerticalScrollbarStyle";
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
      public String nativeVerticalScrollbar() {
        return "MU1SOAB-k-a";
      }
    }
    ;
  }
  private static class nativeVerticalScrollbarStyleInitializer {
    static {
      _instance0.nativeVerticalScrollbarStyleInitializer();
    }
    static NativeVerticalScrollbar.Style get() {
      return nativeVerticalScrollbarStyle;
    }
  }
  public NativeVerticalScrollbar.Style nativeVerticalScrollbarStyle() {
    return nativeVerticalScrollbarStyleInitializer.get();
  }
  private static java.util.HashMap<String, ResourcePrototype> resourceMap;
  private static NativeVerticalScrollbar.Style nativeVerticalScrollbarStyle;
  
  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      nativeVerticalScrollbarStyle(), 
    };
  }
  public ResourcePrototype getResource(String name) {
      if (resourceMap == null) {
        resourceMap = new java.util.HashMap<String, ResourcePrototype>();
        resourceMap.put("nativeVerticalScrollbarStyle", nativeVerticalScrollbarStyle());
      }
      return resourceMap.get(name);
  }
}
