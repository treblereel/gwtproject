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

/**
 * This class is generated from org.gwtproject.user.client.ui.ImageResourceRenderer.Template, do not
 * edit manually
 */
public class ImageResourceRenderer_TemplateImpl
    implements org.gwtproject.user.client.ui.ImageResourceRenderer.Template {

  /** @Template("<img src='{0}' border='0' width='{1}' height='{2}'>") */
  public org.gwtproject.safehtml.shared.SafeHtml image(
      org.gwtproject.safehtml.shared.SafeUri arg0, int arg1, int arg2) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<img src='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0.asString()));
    sb.append("' border='0' width='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(String.valueOf(arg1)));
    sb.append("' height='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(String.valueOf(arg2)));
    sb.append("'>");
    return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(
        sb.toString());
  }
}
