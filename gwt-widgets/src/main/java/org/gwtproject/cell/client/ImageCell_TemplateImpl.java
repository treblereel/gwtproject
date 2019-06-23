package org.gwtproject.cell.client;

/**
 * This class is generated from org.gwtproject.cell.client.ImageCell.Template, do not edit manually
 */
public class ImageCell_TemplateImpl implements org.gwtproject.cell.client.ImageCell.Template {
  
  /**
   * @Template("<img src=\"{0}\"/>")
   */
  public org.gwtproject.safehtml.shared.SafeHtml img(
        java.lang.String arg0) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<img src=\"");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(org.gwtproject.safehtml.shared.UriUtils.sanitizeUri(arg0)));
    sb.append("\"/>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}

}
