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

public class Tree_ResourcesImpl implements Tree.Resources {
  private static Tree_ResourcesImpl _instance0 = new Tree_ResourcesImpl();

  private void treeClosedInitializer() {
    treeClosed =
        new org.gwtproject.resources.client.impl.ImageResourcePrototype(
            "treeClosed",
            org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage),
            0,
            0,
            16,
            16,
            false,
            false);
  }

  private static class treeClosedInitializer {
    static {
      _instance0.treeClosedInitializer();
    }

    static org.gwtproject.resources.client.ImageResource get() {
      return treeClosed;
    }
  }

  public org.gwtproject.resources.client.ImageResource treeClosed() {
    return treeClosedInitializer.get();
  }

  private void treeLeafInitializer() {
    treeLeaf =
        new org.gwtproject.resources.client.impl.ImageResourcePrototype(
            "treeLeaf",
            org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage0),
            0,
            0,
            16,
            16,
            false,
            false);
  }

  private static class treeLeafInitializer {
    static {
      _instance0.treeLeafInitializer();
    }

    static org.gwtproject.resources.client.ImageResource get() {
      return treeLeaf;
    }
  }

  public org.gwtproject.resources.client.ImageResource treeLeaf() {
    return treeLeafInitializer.get();
  }

  private void treeOpenInitializer() {
    treeOpen =
        new org.gwtproject.resources.client.impl.ImageResourcePrototype(
            "treeOpen",
            org.gwtproject.safehtml.shared.UriUtils.fromTrustedString(externalImage1),
            0,
            0,
            16,
            16,
            false,
            false);
  }

  private static class treeOpenInitializer {
    static {
      _instance0.treeOpenInitializer();
    }

    static org.gwtproject.resources.client.ImageResource get() {
      return treeOpen;
    }
  }

  public org.gwtproject.resources.client.ImageResource treeOpen() {
    return treeOpenInitializer.get();
  }

  private static java.util.HashMap<String, ResourcePrototype> resourceMap;
  private static final String externalImage =
      "data:image/gif;base64,R0lGODlhEAAQAIQaAFhorldnrquz1mFxsvz9/vr6/M3Q2ZGbw5mixvb3+Gp5t2Nys77F4GRzs9ze4mt6uGV1s8/R2VZnrl5usFdortPV2/P09+3u8eXm6lZnrf///wAAzP///////////////yH5BAEAAB8ALAAAAAAQABAAAAVE4CeOZGmeaKquo5K974MuTKHdhDCcgOVvvoTkRLkYN8bL0ETBbJ5PTIaIqW6q0lPAYcVOTRNEpEI2HCYoCOzVYLnf7hAAOw==";
  private static final String externalImage0 =
      "data:image/gif;base64,R0lGODlhEAAQAJEAAP///wAAAP///wAAACH5BAEAAAIALAAAAAAQABAAAAIOlI+py+0Po5y02ouzPgUAOw==";
  private static final String externalImage1 =
      "data:image/gif;base64,R0lGODlhEAAQAIQaAFhorldnrquz1mFxsvz9/vr6/M3Q2ZGbw5mixvb3+Gp5t2Nys77F4GRzs9ze4mt6uGV1s8/R2VZnrl5usFdortPV2/P09+3u8eXm6lZnrf///wAAzP///////////////yH5BAEAAB8ALAAAAAAQABAAAAVD4CeOZGmeaKquo5K974MuTKHdhDCcgOVfvoTkRLkYj5ehiYLZOJ2YDBFDvVCjp4CjepWaJohIZWw4TFAQ2KvBarvbIQA7";
  private static org.gwtproject.resources.client.ImageResource treeClosed;
  private static org.gwtproject.resources.client.ImageResource treeLeaf;
  private static org.gwtproject.resources.client.ImageResource treeOpen;

  public ResourcePrototype[] getResources() {
    return new ResourcePrototype[] {
      treeClosed(), treeLeaf(), treeOpen(),
    };
  }

  public ResourcePrototype getResource(String name) {
    if (resourceMap == null) {
      resourceMap = new java.util.HashMap<String, ResourcePrototype>();
      resourceMap.put("treeClosed", treeClosed());
      resourceMap.put("treeLeaf", treeLeaf());
      resourceMap.put("treeOpen", treeOpen());
    }
    return resourceMap.get(name);
  }
}
