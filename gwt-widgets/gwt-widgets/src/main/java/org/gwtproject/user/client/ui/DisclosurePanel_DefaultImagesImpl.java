/*
 * Copyright © 2019 The GWT Project Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gwtproject.user.client.ui;

import org.gwtproject.resources.client.ResourcePrototype;

public class DisclosurePanel_DefaultImagesImpl
    implements org.gwtproject.user.client.ui.DisclosurePanel.DefaultImages {
  private static DisclosurePanel_DefaultImagesImpl _instance0 =
      new DisclosurePanel_DefaultImagesImpl();

  private void disclosurePanelClosedInitializer() {
    disclosurePanelClosed =
        new org.gwtproject.resources.client.impl.ImageResourcePrototype(
            "disclosurePanelClosed",
            org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage),
            0,
            0,
            16,
            16,
            false,
            false);
  }

  private static class disclosurePanelClosedInitializer {
    static {
      _instance0.disclosurePanelClosedInitializer();
    }

    static org.gwtproject.resources.client.ImageResource get() {
      return disclosurePanelClosed;
    }
  }

  public org.gwtproject.resources.client.ImageResource disclosurePanelClosed() {
    return disclosurePanelClosedInitializer.get();
  }

  private void disclosurePanelOpenInitializer() {
    disclosurePanelOpen =
        new org.gwtproject.resources.client.impl.ImageResourcePrototype(
            "disclosurePanelOpen",
            org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage0),
            0,
            0,
            16,
            16,
            false,
            false);
  }

  private static class disclosurePanelOpenInitializer {
    static {
      _instance0.disclosurePanelOpenInitializer();
    }

    static org.gwtproject.resources.client.ImageResource get() {
      return disclosurePanelOpen;
    }
  }

  public org.gwtproject.resources.client.ImageResource disclosurePanelOpen() {
    return disclosurePanelOpenInitializer.get();
  }

  private static java.util.HashMap<String, ResourcePrototype> resourceMap;
  private static final String externalImage =
      "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAnklEQVR4XmNgoDZITk4WQhcjCaSlpZ1JTU3tBdL86HJEAaDGS0D8H4hfpaenJ6PLEwRIBsDweSC2QVeHE2AxAIaXArEcunoMgMcAEP4GDJ+6wsJCTnR9cEDAABjeGBoayoyuFwwIGPCcYMDiMOAnKGqzsrJ40NVjACwGbAQmLhV0dTgBkgFXgc71QJcnCIAaDwOdm48zkAgBvFE0KAEAjDyRV4CcerkAAAAASUVORK5CYII=";
  private static final String externalImage0 =
      "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAoElEQVR4XmNgGD6gsLCQMy0t7TAQXyICn0lOThZCN4MhNTU1Hyj5nxAGqutF1wsGoaGhzEAFV9E1oOFXQMyPrhcO0tPTPbBogmOgfDK6HgwAVLgRXSMUn0dXixUAA0gFqPgnFgNs0NXiBKCAQtO8FF0NXpCVlcUD1PQcqvkbEMuhqyEIQAEGMgDomjp0OaIB0ICNoESGLk40AKUNdLHBBQAIRpFXGETgdQAAAABJRU5ErkJggg==";
  private static org.gwtproject.resources.client.ImageResource disclosurePanelClosed;
  private static org.gwtproject.resources.client.ImageResource disclosurePanelOpen;

  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      disclosurePanelClosed(), disclosurePanelOpen(),
    };
  }

  public ResourcePrototype getResource(String name) {
    if (resourceMap == null) {
      resourceMap = new java.util.HashMap<String, ResourcePrototype>();
      resourceMap.put("disclosurePanelClosed", disclosurePanelClosed());
      resourceMap.put("disclosurePanelOpen", disclosurePanelOpen());
    }
    return resourceMap.get(name);
  }
}
