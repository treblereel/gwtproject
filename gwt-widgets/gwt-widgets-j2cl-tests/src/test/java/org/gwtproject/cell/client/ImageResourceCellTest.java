/*
 * Copyright 2010 Google Inc.
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

import com.google.j2cl.junit.apt.J2clTestInput;
import org.gwtproject.resources.client.ImageResource;
import org.gwtproject.user.client.ui.ImageResourceRenderer;
import org.gwtproject.user.client.ui.ImageTestBundle;
import org.gwtproject.user.client.ui.ImageTestBundleImpl;

/**
 * Tests for {@link org.gwtproject.cell.client.ImageResourceCell}.
 */
@J2clTestInput(ImageResourceCellTest.class)
public class ImageResourceCellTest extends CellTestBase<ImageResource> {

  private ImageTestBundle images;

  @Override
  protected Cell<ImageResource> createCell() {
    return new ImageResourceCell();
  }

  @Override
  protected ImageResource createCellValue() {
    return getImages().prettyPiccy();
  }

  @Override
  protected boolean dependsOnSelection() {
    return false;
  }

  @Override
  protected String[] getConsumedEvents() {
    return null;
  }

  @Override
  protected String getExpectedInnerHtml() {
    return new ImageResourceRenderer().render(getImages().prettyPiccy()).asString();
  }

  @Override
  protected String getExpectedInnerHtmlNull() {
    return "";
  }

  private ImageTestBundle getImages() {
    if (images == null) {
      images = new ImageTestBundleImpl();
    }
    return images;
  }

  @Override
  public String getModuleName() {
    return "";
  }
}
