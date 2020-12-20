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
package org.gwtproject.dom.builder.shared;

import org.gwtproject.safehtml.shared.SafeHtml;

/** Builds an style element. */
public interface StyleBuilder extends ElementBuilderBase<StyleBuilder> {

  String UNSUPPORTED_HTML =
      "Style elements do not support setting inner html or inner text.  Use cssText() instead.";

  /** Sets the CSS text. */
  StyleBuilder cssText(String cssText);

  /** Disable the style sheet. */
  StyleBuilder disabled();

  /**
   * Throws {@link UnsupportedOperationException}.
   *
   * <p>Style elements do not support html. Use {@link #cssText(String)} instead.
   *
   * @throws UnsupportedOperationException
   */
  @Override
  StyleBuilder html(SafeHtml html);

  /**
   * Designed for use with one or more target media.
   *
   * @see <a
   *     href="http://www.w3.org/TR/1999/REC-html401-19991224/present/styles.html#adef-media">W3C
   *     HTML Specification</a>
   */
  StyleBuilder media(String media);

  /**
   * Throws {@link UnsupportedOperationException}.
   *
   * <p>Style elements do not support text. Use {@link #cssText(String)} instead.
   *
   * @throws UnsupportedOperationException
   */
  @Override
  StyleBuilder text(String text);

  /**
   * The content type of the style sheet language.
   *
   * @see <a
   *     href="http://www.w3.org/TR/1999/REC-html401-19991224/present/styles.html#adef-type-STYLE">W3C
   *     HTML Specification</a>
   */
  StyleBuilder type(String type);
}
