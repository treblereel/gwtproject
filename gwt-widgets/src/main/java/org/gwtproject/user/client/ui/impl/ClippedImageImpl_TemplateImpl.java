package org.gwtproject.user.client.ui.impl;

/**
 * This class is generated from org.gwtproject.user.client.ui.impl.ClippedImageImpl.Template, do not edit manually
 */
public class ClippedImageImpl_TemplateImpl implements org.gwtproject.user.client.ui.impl.ClippedImageImpl.Template {
  
  /**
   * @Template("<img src='{0}' style='{1}' border='0'>")
   */
  public org.gwtproject.safehtml.shared.SafeHtml image(
        org.gwtproject.safehtml.shared.SafeUri arg0, 
        org.gwtproject.safecss.shared.SafeStyles arg1) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<img src='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0.asString()));
    sb.append("' style='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg1.asString()));
    sb.append("' border='0'>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}

}
