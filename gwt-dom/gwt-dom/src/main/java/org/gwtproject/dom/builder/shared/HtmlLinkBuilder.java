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

import org.gwtproject.safehtml.shared.annotations.IsTrustedResourceUri;

/** HTML-based implementation of {@link LinkBuilder}. */
public class HtmlLinkBuilder extends HtmlElementBuilderBase<LinkBuilder> implements LinkBuilder {

  HtmlLinkBuilder(HtmlBuilderImpl delegate) {
    super(delegate, true);
  }

  @Override
  public LinkBuilder disabled() {
    return trustedAttribute("disabled", "disabled");
  }

  @Override
  public LinkBuilder href(@IsTrustedResourceUri String href) {
    return trustedAttribute("href", href);
  }

  @Override
  public LinkBuilder hreflang(String hreflang) {
    return trustedAttribute("hreflang", hreflang);
  }

  @Override
  public LinkBuilder media(String media) {
    return trustedAttribute("media", media);
  }

  @Override
  public LinkBuilder rel(String rel) {
    return trustedAttribute("rel", rel);
  }

  @Override
  public LinkBuilder target(String target) {
    return trustedAttribute("target", target);
  }

  @Override
  public LinkBuilder type(String type) {
    return trustedAttribute("type", type);
  }
}
