package org.gwtproject.user.cellview.client;

/**
 * This class is generated from org.gwtproject.user.cellview.client.CellTree.Template, do not edit manually
 */
public class CellTree_TemplateImpl implements org.gwtproject.user.cellview.client.CellTree.Template {
  
  /**
   * @Template("<div class=\"{0}\" style=\"{1}position:absolute;\">{2}</div>")
   */
  public org.gwtproject.safehtml.shared.SafeHtml imageWrapper(
        java.lang.String arg0, 
        org.gwtproject.safecss.shared.SafeStyles arg1, 
        org.gwtproject.safehtml.shared.SafeHtml arg2) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<div class=\"");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
    sb.append("\" style=\"");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg1.asString()));
    sb.append("position:absolute;\">");
    sb.append(arg2.asString());
    sb.append("</div>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}

}
