// Template file: org/gwtproject/editor/ui/client/ValueBoxEditorDecorator.ui.xml
// .ui.xml template last modified: 1544462803400
package org.gwtproject.editor.ui.client;


public class ValueBoxEditorDecorator_BinderImpl implements org.gwtproject.uibinder.client.UiBinder<org.gwtproject.user.client.ui.Widget, org.gwtproject.editor.ui.client.ValueBoxEditorDecorator<?>>, org.gwtproject.editor.ui.client.ValueBoxEditorDecorator.Binder {

  interface Template extends org.gwtproject.safehtml.client.SafeHtmlTemplates {

    org.gwtproject.safehtml.shared.SafeHtml html1(String arg0, String arg1, String arg2);
     
  }

  Template template = new ValueBoxEditorDecorator_BinderImpl_TemplateImpl();


  public org.gwtproject.user.client.ui.Widget createAndBindUi(final org.gwtproject.editor.ui.client.ValueBoxEditorDecorator owner) {


    return new Widgets(owner).get_f_HTMLPanel1();
  }

  /**
   * Encapsulates the access to all inner widgets
   */
  class Widgets {
    private final org.gwtproject.editor.ui.client.ValueBoxEditorDecorator owner;


    public Widgets(final org.gwtproject.editor.ui.client.ValueBoxEditorDecorator owner) {
      this.owner = owner;
      build_style();  // generated css resource must be always created. Type: GENERATED_CSS. Precedence: 1
      build_domId0();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 2
      build_domId1();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 2
      build_domId1Element();  // more than one getter call detected. Type: DEFAULT, precedence: 2
    }

    org.gwtproject.safehtml.shared.SafeHtml template_html1() {
      return template.html1("" + get_style().errorLabel() + "", get_domId0(), get_domId1());
    }

    /**
     * Getter for clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay called 1 times. Type: GENERATED_BUNDLE. Build precedence: 1.
     */
    private org.gwtproject.editor.ui.client.ValueBoxEditorDecorator_BinderImpl_GenBundle get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      return build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay();
    }
    private org.gwtproject.editor.ui.client.ValueBoxEditorDecorator_BinderImpl_GenBundle build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      // Creation section.
      final org.gwtproject.editor.ui.client.ValueBoxEditorDecorator_BinderImpl_GenBundle clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay = new org.gwtproject.editor.ui.client.ValueBoxEditorDecorator_BinderImpl_GenBundleImpl();
      // Setup section.

      return clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay;
    }

    /**
     * Getter for style called 2 times. Type: GENERATED_CSS. Build precedence: 1.
     */
    private org.gwtproject.editor.ui.client.ValueBoxEditorDecorator_BinderImpl_GenCss_style style;
    private org.gwtproject.editor.ui.client.ValueBoxEditorDecorator_BinderImpl_GenCss_style get_style() {
      return style;
    }
    private org.gwtproject.editor.ui.client.ValueBoxEditorDecorator_BinderImpl_GenCss_style build_style() {
      // Creation section.
      style = get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay().style();
      // Setup section.
      style.ensureInjected();

      return style;
    }

    /**
     * Getter for f_HTMLPanel1 called 1 times. Type: DEFAULT. Build precedence: 1.
     */
    private org.gwtproject.user.client.ui.HTMLPanel get_f_HTMLPanel1() {
      return build_f_HTMLPanel1();
    }
    private org.gwtproject.user.client.ui.HTMLPanel build_f_HTMLPanel1() {
      // Creation section.
      final org.gwtproject.user.client.ui.HTMLPanel f_HTMLPanel1 = new org.gwtproject.user.client.ui.HTMLPanel(template_html1().asString());
      // Setup section.

      {
        // Attach section.
        org.gwtproject.uibinder.client.UiBinderUtil.TempAttachment __attachRecord__ = org.gwtproject.uibinder.client.UiBinderUtil.attachToDom(f_HTMLPanel1.getElement());

        get_errorLabel();
        get_domId1Element().get();

        // Detach section.
        __attachRecord__.detach();
      }
      f_HTMLPanel1.addAndReplaceElement(get_contents(), get_domId1Element().get());

      return f_HTMLPanel1;
    }

    /**
     * Getter for errorLabel called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private org.gwtproject.dom.client.Element get_errorLabel() {
      return build_errorLabel();
    }
    private org.gwtproject.dom.client.Element build_errorLabel() {
      // Creation section.
      final org.gwtproject.dom.client.Element errorLabel = new org.gwtproject.uibinder.client.LazyDomElement(get_domId0()).get().cast();
      // Setup section.

      this.owner.errorLabel = errorLabel.cast();

      return errorLabel;
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

    /**
     * Getter for contents called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private org.gwtproject.user.client.ui.SimplePanel get_contents() {
      return build_contents();
    }
    private org.gwtproject.user.client.ui.SimplePanel build_contents() {
      // Creation section.
      final org.gwtproject.user.client.ui.SimplePanel contents = new org.gwtproject.user.client.ui.SimplePanel();
      // Setup section.
      contents.setStylePrimaryName("" + get_style().contents() + "");

      this.owner.contents = contents;

      return contents;
    }

    /**
     * Getter for domId1Element called 2 times. Type: DEFAULT. Build precedence: 2.
     */
    private org.gwtproject.uibinder.client.LazyDomElement domId1Element;
    private org.gwtproject.uibinder.client.LazyDomElement get_domId1Element() {
      return domId1Element;
    }
    private org.gwtproject.uibinder.client.LazyDomElement build_domId1Element() {
      // Creation section.
      domId1Element = new org.gwtproject.uibinder.client.LazyDomElement<org.gwtproject.dom.client.Element>(get_domId1());
      // Setup section.

      return domId1Element;
    }
  }
}
