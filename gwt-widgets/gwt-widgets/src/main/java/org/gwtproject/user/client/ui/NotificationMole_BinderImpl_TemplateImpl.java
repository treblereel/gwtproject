package org.gwtproject.user.client.ui;

/**
 * This class is generated from org.gwtproject.user.client.ui.NotificationMole_BinderImpl.Template, do not edit manually
 */
public class NotificationMole_BinderImpl_TemplateImpl implements org.gwtproject.user.client.ui.NotificationMole_BinderImpl.Template {
  
  /**
   * @Template("<div class='{0}' id='{1}' style='display:none'> <div id='{2}'> <span class='{3}' id='{4}'></span> </div> </div>")
   */
  public org.gwtproject.safehtml.shared.SafeHtml html1(
        java.lang.String arg0, 
        java.lang.String arg1, 
        java.lang.String arg2, 
        java.lang.String arg3, 
        java.lang.String arg4) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<div class='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
    sb.append("' id='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg1));
    sb.append("' style='display:none'> <div id='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg2));
    sb.append("'> <span class='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg3));
    sb.append("' id='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg4));
    sb.append("'></span> </div> </div>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}

}
