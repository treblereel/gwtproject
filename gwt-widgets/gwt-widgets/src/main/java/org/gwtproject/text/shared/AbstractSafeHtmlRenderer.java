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
package org.gwtproject.text.shared;

import org.gwtproject.safehtml.shared.SafeHtml;
import org.gwtproject.safehtml.shared.SafeHtmlBuilder;
import org.gwtproject.safehtml.shared.SafeHtmlUtils;

public abstract class AbstractSafeHtmlRenderer<T> implements SafeHtmlRenderer<T> {

  private static final SafeHtml EMPTY_STRING = SafeHtmlUtils.fromSafeConstant("");

  public void render(T object, SafeHtmlBuilder appendable) {
    appendable.append(render(object));
  }

  protected SafeHtml toSafeHtml(Object obj) {
    return obj == null ? EMPTY_STRING : SafeHtmlUtils.fromString(String.valueOf(obj));
  }
}
