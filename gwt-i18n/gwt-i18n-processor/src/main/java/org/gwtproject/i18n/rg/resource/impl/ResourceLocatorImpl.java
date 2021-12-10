/*
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.gwtproject.i18n.rg.resource.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.gwtproject.i18n.ext.ResourceOracle;
import org.gwtproject.i18n.ext.TreeLogger;

public class ResourceLocatorImpl {
  private static final int CLASS_LOADER_LOAD_REPORT_LIMIT = 10;
  private static int classLoaderLoadCount;

  public ResourceLocatorImpl() {}

  public static void resetClassLoaderLoadWarningCount() {
    classLoaderLoadCount = 0;
  }

  public static InputStream tryFindResourceAsStream(
      TreeLogger logger, ResourceOracle resourceOracle, String resourceName) {
    URL url = tryFindResourceUrl(logger, resourceOracle, resourceName);
    if (url == null) {
      return null;
    } else {
      try {
        return url.openStream();
      } catch (IOException var5) {
        return null;
      }
    }
  }

  public static URL tryFindResourceUrl(
      TreeLogger logger, ResourceOracle resourceOracle, String resourceName) {
    URL resource = resourceOracle.findResource(resourceName);
    if (resource != null) {
      return resource;
    }
    logger.log(TreeLogger.ERROR, "Unable to find Resource '" + resourceName + "'");
    throw new Error();
  }
}
