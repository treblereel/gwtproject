package org.gwtproject.user.client.ui;

import org.gwtproject.i18n.shared.cldr.LocaleInfo;
import org.gwtproject.resources.client.ResourcePrototype;

public class NativeVerticalScrollbar_UiBinderBundleImpl implements org.gwtproject.user.client.ui.NativeVerticalScrollbar.UiBinderBundle {
  private static NativeVerticalScrollbar_UiBinderBundleImpl _instance0 = new NativeVerticalScrollbar_UiBinderBundleImpl();
  private void nativeVerticalScrollbarUiInitializer() {
    nativeVerticalScrollbarUi = new org.gwtproject.user.client.ui.NativeVerticalScrollbar.UiStyle() {
      public String getName() {
        return "nativeVerticalScrollbarUi";
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
        return LocaleInfo.getCurrentLocale().isRTL() ? (".MU1SOAB-l-a{position:relative;overflow:hidden;direction:rtl}.MU1SOAB-l-b{position:absolute;top:0;right:0;height:100%;width:100px;overflow-y:scroll;overflow-x:hidden}") : (".MU1SOAB-l-a{position:relative;overflow:hidden;direction:ltr}.MU1SOAB-l-b{position:absolute;top:0;right:0;height:100%;width:100px;overflow-y:scroll;overflow-x:hidden}");
      }
      public String viewport() {
        return "MU1SOAB-l-a";
      }
      public String scrollable() {
        return "MU1SOAB-l-b";
      }
    }
    ;
  }
  private static class nativeVerticalScrollbarUiInitializer {
    static {
      _instance0.nativeVerticalScrollbarUiInitializer();
    }
    static org.gwtproject.user.client.ui.NativeVerticalScrollbar.UiStyle get() {
      return nativeVerticalScrollbarUi;
    }
  }
  public org.gwtproject.user.client.ui.NativeVerticalScrollbar.UiStyle nativeVerticalScrollbarUi() {
    return nativeVerticalScrollbarUiInitializer.get();
  }
  private static java.util.HashMap<String, ResourcePrototype> resourceMap;
  private static org.gwtproject.user.client.ui.NativeVerticalScrollbar.UiStyle nativeVerticalScrollbarUi;
  
  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      nativeVerticalScrollbarUi(), 
    };
  }
  public ResourcePrototype getResource(String name) {
      if (resourceMap == null) {
        resourceMap = new java.util.HashMap<String, ResourcePrototype>();
        resourceMap.put("nativeVerticalScrollbarUi", nativeVerticalScrollbarUi());
      }
      return resourceMap.get(name);
  }
}
