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
package org.gwtproject.resources.client;
/*
 * Copyright 2010 Google Inc.
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

/** Generally useful styles and resources used throughout GWT widgets and cells. */
public class CommonResources {

  /** The {@link ClientBundle} of resources. */
  interface Bundle extends ClientBundle {

    Bundle INSTANCE = new CommonResources_BundleImpl();

    @Source("inline-block.gss")
    InlineBlockStyle inlineBlockStyle();
  }

  /** Cross-browser implementation of the "display: inline-block" CSS property. */
  interface InlineBlockStyle extends CssResource {

    /** The inline block style. */
    String inlineBlock();
  }

  /** Lazily loaded singleton. */
  private static Bundle instance;

  /**
   * Ensure that the shared {@link Bundle} is created and return it.
   *
   * @return the {@link Bundle} of resources
   */
  private static Bundle ensureResources() {
    if (instance == null) {
      instance = new CommonResources_BundleImpl();
    }
    return instance;
  }

  /** Get the style class name that simulates a "display: inline-block" effect across browsers. */
  public static String getInlineBlockStyle() {
    InlineBlockStyle style = ensureResources().inlineBlockStyle();
    style.ensureInjected();
    return style.inlineBlock();
  }
}
