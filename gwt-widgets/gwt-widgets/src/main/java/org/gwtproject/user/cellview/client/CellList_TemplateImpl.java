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
package org.gwtproject.user.cellview.client;

/**
 * This class is generated from org.gwtproject.user.cellview.client.CellList.Template, do not edit
 * manually
 */
public class CellList_TemplateImpl
    implements org.gwtproject.user.cellview.client.CellList.Template {

  /** @Template("<div __idx=\"{0}\" class=\"{1}\" style=\"outline:none;\" >{2}</div>") */
  public org.gwtproject.safehtml.shared.SafeHtml div(
      int arg0, java.lang.String arg1, org.gwtproject.safehtml.shared.SafeHtml arg2) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<div __idx=\"");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(String.valueOf(arg0)));
    sb.append("\" class=\"");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg1));
    sb.append("\" style=\"outline:none;\" >");
    sb.append(arg2.asString());
    sb.append("</div>");
    return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(
        sb.toString());
  }
}
