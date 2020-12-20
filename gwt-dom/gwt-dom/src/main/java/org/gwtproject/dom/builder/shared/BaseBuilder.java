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

/** Builds an base element. */
public interface BaseBuilder extends ElementBuilderBase<BaseBuilder> {

  /**
   * The base URI See the href attribute definition in HTML 4.01.
   *
   * @see <a
   *     href="http://www.w3.org/TR/1999/REC-html401-19991224/struct/links.html#adef-href-BASE">W3C
   *     HTML Specification</a>
   */
  BaseBuilder href(String href);

  /**
   * The default target frame.
   *
   * @see <a
   *     href="http://www.w3.org/TR/1999/REC-html401-19991224/present/frames.html#adef-target">W3C
   *     HTML Specification</a>
   */
  BaseBuilder target(String target);
}
