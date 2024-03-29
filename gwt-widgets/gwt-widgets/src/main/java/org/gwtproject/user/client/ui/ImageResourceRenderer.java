/*
 * Copyright 2011 Google Inc.
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
package org.gwtproject.user.client.ui;

import org.gwtproject.resources.client.ImageResource;
import org.gwtproject.resources.client.impl.ImageResourcePrototype;
import org.gwtproject.safehtml.client.SafeHtmlTemplates;
import org.gwtproject.safehtml.shared.SafeHtml;
import org.gwtproject.safehtml.shared.SafeUri;
import org.gwtproject.text.shared.AbstractSafeHtmlRenderer;

/** Given an {@link ImageResource}, renders an element to show it. */
public class ImageResourceRenderer extends AbstractSafeHtmlRenderer<ImageResource> {

  interface Template extends SafeHtmlTemplates {
    SafeHtml image(SafeUri imageUri, int width, int height);
  }

  private static final Template TEMPLATE = new ImageResourceRenderer_TemplateImpl();

  @Override
  public SafeHtml render(ImageResource image) {
    if (image instanceof ImageResourcePrototype.Bundle) {
      return AbstractImagePrototype.create(image).getSafeHtml();
    } else {
      return TEMPLATE.image(image.getSafeUri(), image.getWidth(), image.getHeight());
    }
  }
}
