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
 * This class is generated from org.gwtproject.cell.client.ImageCell.Template, do not edit manually
 */
public class ImageCell_TemplateImpl implements org.gwtproject.cell.client.ImageCell.Template {

  /** @Template("<img src=\"{0}\"/>") */
  public org.gwtproject.safehtml.shared.SafeHtml img(java.lang.String arg0) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<img src=\"");
    sb.append(
        org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(
            org.gwtproject.safehtml.shared.UriUtils.sanitizeUri(arg0)));
    sb.append("\"/>");
    return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(
        sb.toString());
  }
}
