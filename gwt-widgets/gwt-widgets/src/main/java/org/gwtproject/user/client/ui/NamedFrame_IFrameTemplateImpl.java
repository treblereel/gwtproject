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
 * This class is generated from org.gwtproject.user.client.ui.NamedFrame.IFrameTemplate, do not edit
 * manually
 */
public class NamedFrame_IFrameTemplateImpl
    implements org.gwtproject.user.client.ui.NamedFrame.IFrameTemplate {

  /** @Template("<iframe src=\"about:blank\" name='{0}'>") */
  public org.gwtproject.safehtml.shared.SafeHtml get(java.lang.String arg0) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<iframe src=\"about:blank\" name='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
    sb.append("'>");
    return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(
        sb.toString());
  }
}
