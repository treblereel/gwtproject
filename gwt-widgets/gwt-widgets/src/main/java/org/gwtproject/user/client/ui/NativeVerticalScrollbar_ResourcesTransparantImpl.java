package org.gwtproject.user.client.ui;

import org.gwtproject.resources.client.ResourcePrototype;

public class NativeVerticalScrollbar_ResourcesTransparantImpl implements org.gwtproject.user.client.ui.NativeVerticalScrollbar.ResourcesTransparant {
  private static NativeVerticalScrollbar_ResourcesTransparantImpl _instance0 = new NativeVerticalScrollbar_ResourcesTransparantImpl();
  private void nativeVerticalScrollbarStyleInitializer() {
    nativeVerticalScrollbarStyle = new org.gwtproject.user.client.ui.NativeVerticalScrollbar.Style() {
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
        return (".EYYW6LD-a-a{opacity:.7;filter:alpha(opacity=70);-webkit-transition:opacity 350ms;-moz-transition:opacity 350ms;-o-transition:opacity 350ms;transition:opacity 350ms}.EYYW6LD-a-a:hover{opacity:1;filter:alpha(opacity=100)}");
      }
      public java.lang.String nativeVerticalScrollbar() {
        return "EYYW6LD-a-a";
      }
    }
    ;
  }
  private static class nativeVerticalScrollbarStyleInitializer {
    static {
      _instance0.nativeVerticalScrollbarStyleInitializer();
    }
    static org.gwtproject.user.client.ui.NativeVerticalScrollbar.Style get() {
      return nativeVerticalScrollbarStyle;
    }
  }
  public org.gwtproject.user.client.ui.NativeVerticalScrollbar.Style nativeVerticalScrollbarStyle() {
    return nativeVerticalScrollbarStyleInitializer.get();
  }
  private static java.util.HashMap<String, ResourcePrototype> resourceMap;
  private static org.gwtproject.user.client.ui.NativeVerticalScrollbar.Style nativeVerticalScrollbarStyle;
  
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
