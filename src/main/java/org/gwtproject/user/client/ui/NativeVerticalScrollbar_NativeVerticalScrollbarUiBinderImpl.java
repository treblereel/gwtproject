// Template file: org/gwtproject/user/client/ui/NativeVerticalScrollbar.ui.xml
// .ui.xml template last modified: 1544462803403
package org.gwtproject.user.client.ui;


@javax.annotation.Generated(value="org.gwtproject.uibinder.processor.UiBinderProcessor", date="2019-06-22T17:50:32.234958")
public class NativeVerticalScrollbar_NativeVerticalScrollbarUiBinderImpl implements org.gwtproject.uibinder.client.UiBinder<org.gwtproject.dom.client.Element, org.gwtproject.user.client.ui.NativeVerticalScrollbar>, org.gwtproject.user.client.ui.NativeVerticalScrollbar.NativeVerticalScrollbarUiBinder {

  interface Template extends org.gwtproject.safehtml.client.SafeHtmlTemplates {
    org.gwtproject.safehtml.shared.SafeHtml html1(String arg0, String arg1, String arg2, String arg3);
     
  }

  Template template = new NativeVerticalScrollbar_NativeVerticalScrollbarUiBinderImpl_TemplateImpl();


  public org.gwtproject.dom.client.Element createAndBindUi(final org.gwtproject.user.client.ui.NativeVerticalScrollbar owner) {


    return new Widgets(owner).get_f_div1();
  }

  /**
   * Encapsulates the access to all inner widgets
   */
  class Widgets {
    private final org.gwtproject.user.client.ui.NativeVerticalScrollbar owner;


    public Widgets(final org.gwtproject.user.client.ui.NativeVerticalScrollbar owner) {
      this.owner = owner;
      build_res();  // more than one getter call detected. Type: IMPORTED, precedence: 1
      build_domId0();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 2
      build_domId1();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 2
    }

    org.gwtproject.safehtml.shared.SafeHtml template_html1() {
      return template.html1("" + get_res().nativeVerticalScrollbarUi().viewport() + "", "" + get_res().nativeVerticalScrollbarUi().scrollable() + "", get_domId0(), get_domId1());
    }

    /**
     * Getter for clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay called 0 times. Type: GENERATED_BUNDLE. Build precedence: 1.
     */
    private org.gwtproject.user.client.ui.NativeVerticalScrollbar_NativeVerticalScrollbarUiBinderImpl_GenBundle get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      return build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay();
    }
    private org.gwtproject.user.client.ui.NativeVerticalScrollbar_NativeVerticalScrollbarUiBinderImpl_GenBundle build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      // Creation section.
      final org.gwtproject.user.client.ui.NativeVerticalScrollbar_NativeVerticalScrollbarUiBinderImpl_GenBundle clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay = new org.gwtproject.user.client.ui.NativeVerticalScrollbar_NativeVerticalScrollbarUiBinderImpl_GenBundleImpl();
      // Setup section.

      return clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay;
    }

    /**
     * Getter for res called 2 times. Type: IMPORTED. Build precedence: 1.
     */
    private org.gwtproject.user.client.ui.NativeVerticalScrollbar.UiBinderBundle res;
    private org.gwtproject.user.client.ui.NativeVerticalScrollbar.UiBinderBundle get_res() {
      return res;
    }
    private org.gwtproject.user.client.ui.NativeVerticalScrollbar.UiBinderBundle build_res() {
      // Creation section.
      res = new org.gwtproject.user.client.ui.NativeVerticalScrollbar_UiBinderBundleImpl();
      // Setup section.

      return res;
    }

    /**
     * Getter for f_div1 called 1 times. Type: DEFAULT. Build precedence: 1.
     */
    private org.gwtproject.dom.client.Element get_f_div1() {
      return build_f_div1();
    }
    private org.gwtproject.dom.client.Element build_f_div1() {
      // Creation section.
      final org.gwtproject.dom.client.Element f_div1 = (org.gwtproject.dom.client.Element) org.gwtproject.uibinder.client.UiBinderUtil.fromHtml(template_html1().asString());
      // Setup section.

      {
        // Attach section.
        org.gwtproject.uibinder.client.UiBinderUtil.TempAttachment __attachRecord__ = org.gwtproject.uibinder.client.UiBinderUtil.attachToDom(f_div1);

        get_scrollable();
        get_contentDiv();

        // Detach section.
        __attachRecord__.detach();
      }

      return f_div1;
    }

    /**
     * Getter for scrollable called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private org.gwtproject.dom.client.Element get_scrollable() {
      return build_scrollable();
    }
    private org.gwtproject.dom.client.Element build_scrollable() {
      // Creation section.
      final org.gwtproject.dom.client.Element scrollable = new org.gwtproject.uibinder.client.LazyDomElement(get_domId0()).get().cast();
      // Setup section.

      this.owner.scrollable = scrollable.cast();

      return scrollable;
    }

    /**
     * Getter for domId0 called 2 times. Type: DOM_ID_HOLDER. Build precedence: 2.
     */
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

    /**
     * Getter for contentDiv called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private org.gwtproject.dom.client.Element get_contentDiv() {
      return build_contentDiv();
    }
    private org.gwtproject.dom.client.Element build_contentDiv() {
      // Creation section.
      final org.gwtproject.dom.client.Element contentDiv = new org.gwtproject.uibinder.client.LazyDomElement(get_domId1()).get().cast();
      // Setup section.

      this.owner.contentDiv = contentDiv.cast();

      return contentDiv;
    }

    /**
     * Getter for domId1 called 2 times. Type: DOM_ID_HOLDER. Build precedence: 2.
     */
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
