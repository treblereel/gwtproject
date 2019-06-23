package org.gwtproject.cell.client;

/**
 * This class is generated from org.gwtproject.cell.client.EditTextCell.Template, do not edit manually
 */
public class EditTextCell_TemplateImpl implements org.gwtproject.cell.client.EditTextCell.Template {
  
  /**
   * @Template("<input type=\"text\" value=\"{0}\" tabindex=\"-1\"></input>")
   */
  public org.gwtproject.safehtml.shared.SafeHtml input(
        java.lang.String arg0) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<input type=\"text\" value=\"");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
    sb.append("\" tabindex=\"-1\"></input>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}

}
