package org.gwtproject.cell.client;

/**
 * This class is generated from org.gwtproject.cell.client.SelectionCell.Template, do not edit manually
 */
public class SelectionCell_TemplateImpl implements org.gwtproject.cell.client.SelectionCell.Template {
  
  /**
   * @Template("<option value=\"{0}\">{0}</option>")
   */
  public org.gwtproject.safehtml.shared.SafeHtml deselected(
        java.lang.String arg0) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<option value=\"");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
    sb.append("\">");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
    sb.append("</option>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}


/**
 * @Template("<option value=\"{0}\" selected=\"selected\">{0}</option>")
 */
public org.gwtproject.safehtml.shared.SafeHtml selected(
      java.lang.String arg0) {
  StringBuilder sb = new java.lang.StringBuilder();
  sb.append("<option value=\"");
  sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
  sb.append("\" selected=\"selected\">");
  sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
  sb.append("</option>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}

}
