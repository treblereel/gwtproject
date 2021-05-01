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
 * This class is generated from
 * org.gwtproject.cell.client.ButtonCellBase.DefaultAppearance.Template, do not edit manually
 */
public class ButtonCellBase_DefaultAppearance_TemplateImpl
    implements org.gwtproject.cell.client.ButtonCellBase.DefaultAppearance.Template {

  /** @Template("<div class=\"{0}\" style=\"{1}position:relative;zoom:0;\">{2}{3}</div>") */
  public org.gwtproject.safehtml.shared.SafeHtml iconContentLayout(
      java.lang.String arg0,
      org.gwtproject.safecss.shared.SafeStyles arg1,
      org.gwtproject.safehtml.shared.SafeHtml arg2,
      org.gwtproject.safehtml.shared.SafeHtml arg3) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<div class=\"");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
    sb.append("\" style=\"");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg1.asString()));
    sb.append("position:relative;zoom:0;\">");
    sb.append(arg2.asString());
    sb.append(arg3.asString());
    sb.append("</div>");
    return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(
        sb.toString());
  }

  /** @Template("<div style=\"{0}position:absolute;top:50%;line-height:0px;\">{1}</div>") */
  public org.gwtproject.safehtml.shared.SafeHtml iconWrapper(
      org.gwtproject.safecss.shared.SafeStyles arg0, org.gwtproject.safehtml.shared.SafeHtml arg1) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<div style=\"");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0.asString()));
    sb.append("position:absolute;top:50%;line-height:0px;\">");
    sb.append(arg1.asString());
    sb.append("</div>");
    return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(
        sb.toString());
  }
}
