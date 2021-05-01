/*
 * Copyright 2012 Google Inc.
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
package org.gwtproject.cell.client;

import org.gwtproject.safehtml.client.SafeHtmlTemplates;
import org.gwtproject.safehtml.shared.SafeHtml;
import org.gwtproject.safehtml.shared.SafeHtmlBuilder;
import org.gwtproject.safehtml.shared.SafeUri;

/**
 * An {@link org.gwtproject.cell.client.AbstractCell} used to render an image by using a {@link
 * SafeUri}.
 *
 * <p>If the images being displayed are static or available at compile time, using {@link
 * org.gwtproject.cell.client.ImageResourceCell} will usually be more efficient.
 *
 * @see org.gwtproject.cell.client.ImageCell
 * @see org.gwtproject.cell.client.ImageResourceCell
 */
public class SafeImageCell extends org.gwtproject.cell.client.AbstractCell<SafeUri> {

  interface Template extends SafeHtmlTemplates {

    SafeImageCell.Template INSTANCE = new SafeImageCell_TemplateImpl();

    SafeHtml img(SafeUri url);
  }

  /** Construct a new SafeImageCell. */
  public SafeImageCell() {}

  @Override
  public void render(Context context, SafeUri value, SafeHtmlBuilder sb) {
    if (value != null) {
      sb.append(Template.INSTANCE.img(value));
    }
  }
}
