package org.gwtproject.user.client.ui;

import org.gwtproject.resources.client.ResourcePrototype;

public class NotificationMole_BinderImpl_GenBundleImpl implements org.gwtproject.user.client.ui.NotificationMole_BinderImpl_GenBundle {
  private static NotificationMole_BinderImpl_GenBundleImpl _instance0 = new NotificationMole_BinderImpl_GenBundleImpl();
  private void styleInitializer() {
    style = new org.gwtproject.user.client.ui.NotificationMole_BinderImpl_GenCss_style() {
      public String getName() {
        return "style";
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
        return com.google.gwt.i18n.client.LocaleInfo.getCurrentLocale().isRTL() ? (".JGPSNOB-c-a{position:absolute;height:0;text-align:center;width:100%}.JGPSNOB-c-b{margin-right:auto;margin-left:auto;border-right:1px solid #96a2b5;border-left:1px solid #96a2b5;border-bottom:1px solid #96a2b5;background-color:#e5edf9;padding:5px;overflow:hidden;display:inline-block}.JGPSNOB-c-c{font-family:Helvetica;font-size:1em}") : (".JGPSNOB-c-a{position:absolute;height:0;text-align:center;width:100%}.JGPSNOB-c-b{margin-left:auto;margin-right:auto;border-left:1px solid #96a2b5;border-right:1px solid #96a2b5;border-bottom:1px solid #96a2b5;background-color:#e5edf9;padding:5px;overflow:hidden;display:inline-block}.JGPSNOB-c-c{font-family:Helvetica;font-size:1em}");
      }
      public java.lang.String container() {
        return "JGPSNOB-c-a";
      }
      public java.lang.String centered() {
        return "JGPSNOB-c-b";
      }
      public java.lang.String notificationText() {
        return "JGPSNOB-c-c";
      }
    }
    ;
  }
  private static class styleInitializer {
    static {
      _instance0.styleInitializer();
    }
    static org.gwtproject.user.client.ui.NotificationMole_BinderImpl_GenCss_style get() {
      return style;
    }
  }
  public org.gwtproject.user.client.ui.NotificationMole_BinderImpl_GenCss_style style() {
    return styleInitializer.get();
  }
  private static java.util.HashMap<String, ResourcePrototype> resourceMap;
  private static org.gwtproject.user.client.ui.NotificationMole_BinderImpl_GenCss_style style;
  
  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      style(), 
    };
  }
  public ResourcePrototype getResource(String name) {
      if (resourceMap == null) {
        resourceMap = new java.util.HashMap<String, ResourcePrototype>();
        resourceMap.put("style", style());
      }
      return resourceMap.get(name);
  }
}
