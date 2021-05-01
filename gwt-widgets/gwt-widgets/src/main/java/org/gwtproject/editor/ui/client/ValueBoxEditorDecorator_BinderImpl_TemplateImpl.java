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
package org.gwtproject.editor.ui.client;

/**
 * This class is generated from
 * org.gwtproject.editor.ui.client.ValueBoxEditorDecorator_BinderImpl.Template, do not edit manually
 */
public class ValueBoxEditorDecorator_BinderImpl_TemplateImpl
    implements org.gwtproject.editor.ui.client.ValueBoxEditorDecorator_BinderImpl.Template {

  /** @Template("<div class='{0}' id='{1}'></div> <span id='{2}'></span>") */
  public org.gwtproject.safehtml.shared.SafeHtml html1(
      java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<div class='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
    sb.append("' id='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg1));
    sb.append("'></div> <span id='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg2));
    sb.append("'></span>");
    return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(
        sb.toString());
  }
}
