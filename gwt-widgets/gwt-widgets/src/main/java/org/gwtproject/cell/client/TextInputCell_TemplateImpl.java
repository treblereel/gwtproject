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
package org.gwtproject.cell.client;

/**
 * This class is generated from org.gwtproject.cell.client.TextInputCell.Template, do not edit
 * manually
 */
public class TextInputCell_TemplateImpl
    implements org.gwtproject.cell.client.TextInputCell.Template {

  /** @Template("<input type=\"text\" value=\"{0}\" tabindex=\"-1\"></input>") */
  public org.gwtproject.safehtml.shared.SafeHtml input(java.lang.String arg0) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<input type=\"text\" value=\"");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
    sb.append("\" tabindex=\"-1\"></input>");
    return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(
        sb.toString());
  }
}
