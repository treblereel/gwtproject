package org.gwtproject.user.client.ui;

/**
 * This class is generated from org.gwtproject.user.client.ui.ImageResourceRenderer.Template, do not edit manually
 */
public class ImageResourceRenderer_TemplateImpl implements org.gwtproject.user.client.ui.ImageResourceRenderer.Template {
  
  /**
   * @Template("<img src='{0}' border='0' width='{1}' height='{2}'>")
   */
  public org.gwtproject.safehtml.shared.SafeHtml image(
        org.gwtproject.safehtml.shared.SafeUri arg0, 
        int arg1, 
        int arg2) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<img src='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0.asString()));
    sb.append("' border='0' width='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(String.valueOf(arg1)));
    sb.append("' height='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(String.valueOf(arg2)));
    sb.append("'>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}

}
