package org.gwtproject.user.client.ui;

/**
 * This class is generated from org.gwtproject.user.client.ui.FormPanel.IFrameTemplate, do not edit manually
 */
public class FormPanel_IFrameTemplateImpl implements org.gwtproject.user.client.ui.FormPanel.IFrameTemplate {
  
  /**
   * @Template("<iframe src=\"about:blank\" name='{0}' tabindex='-1' style='position:absolute;width:0;height:0;border:0'>")
   */
  public org.gwtproject.safehtml.shared.SafeHtml get(
        java.lang.String arg0) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<iframe src=\"about:blank\" name='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
    sb.append("' tabindex='-1' style='position:absolute;width:0;height:0;border:0'>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}

}
