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
// Template file: org/gwtproject/user/client/ui/NativeHorizontalScrollbar.ui.xml
// .ui.xml template last modified: 1544462803403
package org.gwtproject.user.client.ui;

public class NativeHorizontalScrollbar_NativeHorizontalScrollbarUiBinderImpl
    implements org.gwtproject.uibinder.client.UiBinder<
            org.gwtproject.dom.client.Element,
            org.gwtproject.user.client.ui.NativeHorizontalScrollbar>,
        org.gwtproject.user.client.ui.NativeHorizontalScrollbar.NativeHorizontalScrollbarUiBinder {

  interface Template extends org.gwtproject.safehtml.client.SafeHtmlTemplates {
    org.gwtproject.safehtml.shared.SafeHtml html1(
        String arg0, String arg1, String arg2, String arg3, String arg4);
  }

  Template template =
      new NativeHorizontalScrollbar_NativeHorizontalScrollbarUiBinderImpl_TemplateImpl();

  public org.gwtproject.dom.client.Element createAndBindUi(
      final org.gwtproject.user.client.ui.NativeHorizontalScrollbar owner) {

    return new Widgets(owner).get_f_div1();
  }

  /** Encapsulates the access to all inner widgets */
  class Widgets {
    private final org.gwtproject.user.client.ui.NativeHorizontalScrollbar owner;

    public Widgets(final org.gwtproject.user.client.ui.NativeHorizontalScrollbar owner) {
      this.owner = owner;
      build_style(); // generated css resource must be always created. Type: GENERATED_CSS.
      // Precedence: 1
      build_domId0(); // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 2
      build_domId1(); // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 2
    }

    org.gwtproject.safehtml.shared.SafeHtml template_html1() {
      return template.html1(
          "" + get_style().viewport() + "",
          "" + get_style().scrollable() + "",
          get_domId0(),
          "" + get_style().content() + "",
          get_domId1());
    }

    /**
     * Getter for clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay called 1 times.
     * Type: GENERATED_BUNDLE. Build precedence: 1.
     */
    private org.gwtproject.user.client.ui
            .NativeHorizontalScrollbar_NativeHorizontalScrollbarUiBinderImpl_GenBundle
        get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      return build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay();
    }

    private org.gwtproject.user.client.ui
            .NativeHorizontalScrollbar_NativeHorizontalScrollbarUiBinderImpl_GenBundle
        build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      // Creation section.
      final org.gwtproject.user.client.ui
              .NativeHorizontalScrollbar_NativeHorizontalScrollbarUiBinderImpl_GenBundle
          clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay =
              new org.gwtproject.user.client.ui
                  .NativeHorizontalScrollbar_NativeHorizontalScrollbarUiBinderImpl_GenBundleImpl();
      // Setup section.

      return clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay;
    }

    /** Getter for style called 3 times. Type: GENERATED_CSS. Build precedence: 1. */
    private org.gwtproject.user.client.ui
            .NativeHorizontalScrollbar_NativeHorizontalScrollbarUiBinderImpl_GenCss_style
        style;

    private org.gwtproject.user.client.ui
            .NativeHorizontalScrollbar_NativeHorizontalScrollbarUiBinderImpl_GenCss_style
        get_style() {
      return style;
    }

    private org.gwtproject.user.client.ui
            .NativeHorizontalScrollbar_NativeHorizontalScrollbarUiBinderImpl_GenCss_style
        build_style() {
      // Creation section.
      style = get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay().style();
      // Setup section.
      style.ensureInjected();

      return style;
    }

    /** Getter for f_div1 called 1 times. Type: DEFAULT. Build precedence: 1. */
    private org.gwtproject.dom.client.Element get_f_div1() {
      return build_f_div1();
    }

    private org.gwtproject.dom.client.Element build_f_div1() {
      // Creation section.
      final org.gwtproject.dom.client.Element f_div1 =
          (org.gwtproject.dom.client.Element)
              org.gwtproject.uibinder.client.UiBinderUtil.fromHtml(template_html1().asString());
      // Setup section.

      {
        // Attach section.
        org.gwtproject.uibinder.client.UiBinderUtil.TempAttachment __attachRecord__ =
            org.gwtproject.uibinder.client.UiBinderUtil.attachToDom(f_div1);

        get_scrollable();
        get_contentDiv();

        // Detach section.
        __attachRecord__.detach();
      }

      return f_div1;
    }

    /** Getter for scrollable called 1 times. Type: DEFAULT. Build precedence: 2. */
    private org.gwtproject.dom.client.Element get_scrollable() {
      return build_scrollable();
    }

    private org.gwtproject.dom.client.Element build_scrollable() {
      // Creation section.
      final org.gwtproject.dom.client.Element scrollable =
          new org.gwtproject.uibinder.client.LazyDomElement(get_domId0()).get().cast();
      // Setup section.

      this.owner.scrollable = scrollable.cast();

      return scrollable;
    }

    /** Getter for domId0 called 2 times. Type: DOM_ID_HOLDER. Build precedence: 2. */
    private java.lang.String domId0;

    private java.lang.String get_domId0() {
      return domId0;
    }

    private java.lang.String build_domId0() {
      // Creation section.
      domId0 = org.gwtproject.dom.client.Document.get().createUniqueId();
      // Setup section.

      return domId0;
    }

    /** Getter for contentDiv called 1 times. Type: DEFAULT. Build precedence: 2. */
    private org.gwtproject.dom.client.Element get_contentDiv() {
      return build_contentDiv();
    }

    private org.gwtproject.dom.client.Element build_contentDiv() {
      // Creation section.
      final org.gwtproject.dom.client.Element contentDiv =
          new org.gwtproject.uibinder.client.LazyDomElement(get_domId1()).get().cast();
      // Setup section.

      this.owner.contentDiv = contentDiv.cast();

      return contentDiv;
    }

    /** Getter for domId1 called 2 times. Type: DOM_ID_HOLDER. Build precedence: 2. */
    private java.lang.String domId1;

    private java.lang.String get_domId1() {
      return domId1;
    }

    private java.lang.String build_domId1() {
      // Creation section.
      domId1 = org.gwtproject.dom.client.Document.get().createUniqueId();
      // Setup section.

      return domId1;
    }
  }
}
