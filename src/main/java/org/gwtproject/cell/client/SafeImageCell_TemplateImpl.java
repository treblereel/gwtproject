package org.gwtproject.cell.client;

/**
 * This class is generated from org.gwtproject.cell.client.SafeImageCell.Template, do not edit manually
 */
public class SafeImageCell_TemplateImpl implements org.gwtproject.cell.client.SafeImageCell.Template {
  
  /**
   * @Template("<img src=\"{0}\"/>")
   */
  public org.gwtproject.safehtml.shared.SafeHtml img(
        org.gwtproject.safehtml.shared.SafeUri arg0) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<img src=\"");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0.asString()));
    sb.append("\"/>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}

}
