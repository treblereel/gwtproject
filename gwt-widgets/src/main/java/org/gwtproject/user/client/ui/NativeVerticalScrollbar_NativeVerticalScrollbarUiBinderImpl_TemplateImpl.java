package org.gwtproject.user.client.ui;

/**
 * This class is generated from org.gwtproject.user.client.ui.NativeVerticalScrollbar_NativeVerticalScrollbarUiBinderImpl.Template, do not edit manually
 */
public class NativeVerticalScrollbar_NativeVerticalScrollbarUiBinderImpl_TemplateImpl implements org.gwtproject.user.client.ui.NativeVerticalScrollbar_NativeVerticalScrollbarUiBinderImpl.Template {
  
  /**
   * @Template("<div class='{0}'><div class='{1}' id='{2}'> <div id='{3}'></div> </div></div>")
   */
  public org.gwtproject.safehtml.shared.SafeHtml html1(
        java.lang.String arg0, 
        java.lang.String arg1, 
        java.lang.String arg2, 
        java.lang.String arg3) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<div class='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
    sb.append("'><div class='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg1));
    sb.append("' id='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg2));
    sb.append("'> <div id='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg3));
    sb.append("'></div> </div></div>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}

}
