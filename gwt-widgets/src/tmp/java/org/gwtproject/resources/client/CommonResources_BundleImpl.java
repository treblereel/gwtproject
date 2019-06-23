package org.gwtproject.resources.client;

public class CommonResources_BundleImpl implements CommonResources.Bundle {
  private static CommonResources_BundleImpl _instance0 = new CommonResources_BundleImpl();
  private void inlineBlockStyleInitializer() {
    inlineBlockStyle = new CommonResources.InlineBlockStyle() {
      public String getName() {
        return "inlineBlockStyle";
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
        return (".MU1SOAB-n-a{position:relative;display:inline-block}");
      }
      public String inlineBlock() {
        return "MU1SOAB-n-a";
      }
    }
    ;
  }
  private static class inlineBlockStyleInitializer {
    static {
      _instance0.inlineBlockStyleInitializer();
    }
    static CommonResources.InlineBlockStyle get() {
      return inlineBlockStyle;
    }
  }
  public CommonResources.InlineBlockStyle inlineBlockStyle() {
    return inlineBlockStyleInitializer.get();
  }
  private static java.util.HashMap<String, ResourcePrototype> resourceMap;
  private static CommonResources.InlineBlockStyle inlineBlockStyle;
  
  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      inlineBlockStyle(), 
    };
  }
  public ResourcePrototype getResource(String name) {
      if (resourceMap == null) {
        resourceMap = new java.util.HashMap<String, ResourcePrototype>();
        resourceMap.put("inlineBlockStyle", inlineBlockStyle());
      }
      return resourceMap.get(name);
  }
}
