// Template file: org/gwtproject/user/client/ui/NotificationMole.ui.xml
// .ui.xml template last modified: 1544462803403
package org.gwtproject.user.client.ui;


@javax.annotation.Generated(value="org.gwtproject.uibinder.processor.UiBinderProcessor", date="2019-06-22T17:50:32.251474")
public class NotificationMole_BinderImpl implements org.gwtproject.uibinder.client.UiBinder<org.gwtproject.user.client.ui.HTMLPanel, org.gwtproject.user.client.ui.NotificationMole>, org.gwtproject.user.client.ui.NotificationMole.Binder {

  interface Template extends org.gwtproject.safehtml.client.SafeHtmlTemplates {
    org.gwtproject.safehtml.shared.SafeHtml html1(String arg0, String arg1, String arg2, String arg3, String arg4);
     
  }

  Template template = new NotificationMole_BinderImpl_TemplateImpl();


  public org.gwtproject.user.client.ui.HTMLPanel createAndBindUi(final org.gwtproject.user.client.ui.NotificationMole owner) {


    return new Widgets(owner).get_f_HTMLPanel1();
  }

  /**
   * Encapsulates the access to all inner widgets
   */
  class Widgets {
    private final org.gwtproject.user.client.ui.NotificationMole owner;


    public Widgets(final org.gwtproject.user.client.ui.NotificationMole owner) {
      this.owner = owner;
      build_style();  // generated css resource must be always created. Type: GENERATED_CSS. Precedence: 1
      build_domId0();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 2
      build_domId1();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 2
      build_domId2();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 2
    }

    org.gwtproject.safehtml.shared.SafeHtml template_html1() {
      return template.html1("" + get_style().centered() + "", get_domId0(), get_domId1(), "" + get_style().notificationText() + "", get_domId2());
    }

    /**
     * Getter for clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay called 1 times. Type: GENERATED_BUNDLE. Build precedence: 1.
     */
    private org.gwtproject.user.client.ui.NotificationMole_BinderImpl_GenBundle get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      return build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay();
    }
    private org.gwtproject.user.client.ui.NotificationMole_BinderImpl_GenBundle build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      // Creation section.
      final org.gwtproject.user.client.ui.NotificationMole_BinderImpl_GenBundle clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay = new org.gwtproject.user.client.ui.NotificationMole_BinderImpl_GenBundleImpl();
      // Setup section.

      return clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay;
    }

    /**
     * Getter for style called 3 times. Type: GENERATED_CSS. Build precedence: 1.
     */
    private org.gwtproject.user.client.ui.NotificationMole_BinderImpl_GenCss_style style;
    private org.gwtproject.user.client.ui.NotificationMole_BinderImpl_GenCss_style get_style() {
      return style;
    }
    private org.gwtproject.user.client.ui.NotificationMole_BinderImpl_GenCss_style build_style() {
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
      f_HTMLPanel1.setStyleName("" + get_style().container() + "");

      {
        // Attach section.
        org.gwtproject.uibinder.client.UiBinderUtil.TempAttachment __attachRecord__ = org.gwtproject.uibinder.client.UiBinderUtil.attachToDom(f_HTMLPanel1.getElement());

        get_borderElement();
        get_heightMeasure();
        get_notificationText();

        // Detach section.
        __attachRecord__.detach();
      }

      return f_HTMLPanel1;
    }

    /**
     * Getter for borderElement called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private org.gwtproject.dom.client.Element get_borderElement() {
      return build_borderElement();
    }
    private org.gwtproject.dom.client.Element build_borderElement() {
      // Creation section.
      final org.gwtproject.dom.client.Element borderElement = new org.gwtproject.uibinder.client.LazyDomElement(get_domId0()).get().cast();
      // Setup section.

      this.owner.borderElement = borderElement.cast();

      return borderElement;
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
     * Getter for heightMeasure called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private org.gwtproject.dom.client.Element get_heightMeasure() {
      return build_heightMeasure();
    }
    private org.gwtproject.dom.client.Element build_heightMeasure() {
      // Creation section.
      final org.gwtproject.dom.client.Element heightMeasure = new org.gwtproject.uibinder.client.LazyDomElement(get_domId1()).get().cast();
      // Setup section.

      this.owner.heightMeasure = heightMeasure.cast();

      return heightMeasure;
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
     * Getter for notificationText called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private org.gwtproject.dom.client.Element get_notificationText() {
      return build_notificationText();
    }
    private org.gwtproject.dom.client.Element build_notificationText() {
      // Creation section.
      final org.gwtproject.dom.client.Element notificationText = new org.gwtproject.uibinder.client.LazyDomElement(get_domId2()).get().cast();
      // Setup section.

      this.owner.notificationText = notificationText.cast();

      return notificationText;
    }

    /**
     * Getter for domId2 called 2 times. Type: DOM_ID_HOLDER. Build precedence: 2.
     */
    private java.lang.String domId2;
    private java.lang.String get_domId2() {
      return domId2;
    }
    private java.lang.String build_domId2() {
      // Creation section.
      domId2 = org.gwtproject.dom.client.Document.get().createUniqueId();
      // Setup section.

      return domId2;
    }
  }
}
