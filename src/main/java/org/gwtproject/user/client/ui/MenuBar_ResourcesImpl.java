package org.gwtproject.user.client.ui;

import org.gwtproject.resources.client.ResourcePrototype;

public class MenuBar_ResourcesImpl implements MenuBar.Resources {
  private static MenuBar_ResourcesImpl _instance0 = new MenuBar_ResourcesImpl();
  private void menuBarSubMenuIconInitializer() {
    menuBarSubMenuIcon = new org.gwtproject.resources.client.impl.ImageResourcePrototype(
      "menuBarSubMenuIcon",
      org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage),
      0, 0, 5, 9, false, false
    );
  }
  private static class menuBarSubMenuIconInitializer {
    static {
      _instance0.menuBarSubMenuIconInitializer();
    }
    static org.gwtproject.resources.client.ImageResource get() {
      return menuBarSubMenuIcon;
    }
  }
  public org.gwtproject.resources.client.ImageResource menuBarSubMenuIcon() {
    return menuBarSubMenuIconInitializer.get();
  }
  private static java.util.HashMap<String, ResourcePrototype> resourceMap;
  private static final String externalImage = "data:image/gif;base64,R0lGODlhBQAJAIAAAAAAAAAAACH5BAEAAAEALAAAAAAFAAkAAAIMRB5gp9v2YlJsJRQKADs=";
  private static org.gwtproject.resources.client.ImageResource menuBarSubMenuIcon;
  
  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      menuBarSubMenuIcon(), 
    };
  }
  public ResourcePrototype getResource(String name) {
      if (resourceMap == null) {
        resourceMap = new java.util.HashMap<String, ResourcePrototype>();
        resourceMap.put("menuBarSubMenuIcon", menuBarSubMenuIcon());
      }
      return resourceMap.get(name);
  }
}
