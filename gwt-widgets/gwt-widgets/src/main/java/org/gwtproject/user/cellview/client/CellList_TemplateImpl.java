package org.gwtproject.user.cellview.client;

/**
 * This class is generated from org.gwtproject.user.cellview.client.CellList.Template, do not edit manually
 */
public class CellList_TemplateImpl implements org.gwtproject.user.cellview.client.CellList.Template {
  
  /**
   * @Template("<div __idx=\"{0}\" class=\"{1}\" style=\"outline:none;\" >{2}</div>")
   */
  public org.gwtproject.safehtml.shared.SafeHtml div(
        int arg0, 
        java.lang.String arg1, 
        org.gwtproject.safehtml.shared.SafeHtml arg2) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<div __idx=\"");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(String.valueOf(arg0)));
    sb.append("\" class=\"");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg1));
    sb.append("\" style=\"outline:none;\" >");
    sb.append(arg2.asString());
    sb.append("</div>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}

}
