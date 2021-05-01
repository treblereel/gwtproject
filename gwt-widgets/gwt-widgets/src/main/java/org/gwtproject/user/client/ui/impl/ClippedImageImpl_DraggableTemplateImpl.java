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
package org.gwtproject.user.client.ui.impl;

/**
 * This class is generated from
 * org.gwtproject.user.client.ui.impl.ClippedImageImpl.DraggableTemplate, do not edit manually
 */
public class ClippedImageImpl_DraggableTemplateImpl
    implements org.gwtproject.user.client.ui.impl.ClippedImageImpl.DraggableTemplate {

  /** @Template("<img src='{0}' style='{1}' border='0' draggable='true'>") */
  public org.gwtproject.safehtml.shared.SafeHtml image(
      org.gwtproject.safehtml.shared.SafeUri arg0, org.gwtproject.safecss.shared.SafeStyles arg1) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<img src='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0.asString()));
    sb.append("' style='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg1.asString()));
    sb.append("' border='0' draggable='true'>");
    return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(
        sb.toString());
  }
}
