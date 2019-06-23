package org.gwtproject.user.cellview.client;

/**
 * This class is generated from org.gwtproject.user.cellview.client.CellTreeNodeView.Template, do not edit manually
 */
public class CellTreeNodeView_TemplateImpl implements org.gwtproject.user.cellview.client.CellTreeNodeView.Template {
  
  /**
   * @Template("<div style=\"{0}position:relative;\" class=\"{1}\">{2}<div class=\"{3}\">{4}</div></div>")
   */
  public org.gwtproject.safehtml.shared.SafeHtml innerDiv(
        org.gwtproject.safecss.shared.SafeStyles arg0, 
        java.lang.String arg1, 
        org.gwtproject.safehtml.shared.SafeHtml arg2, 
        java.lang.String arg3, 
        org.gwtproject.safehtml.shared.SafeHtml arg4) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<div style=\"");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0.asString()));
    sb.append("position:relative;\" class=\"");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg1));
    sb.append("\">");
    sb.append(arg2.asString());
    sb.append("<div class=\"");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg3));
    sb.append("\">");
    sb.append(arg4.asString());
    sb.append("</div></div>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}


/**
 * @Template("<div aria-selected=\"{3}\"><div style=\"{0}\" class=\"{1}\">{2}</div></div>")
 */
public org.gwtproject.safehtml.shared.SafeHtml outerDiv(
      org.gwtproject.safecss.shared.SafeStyles arg0, 
      java.lang.String arg1, 
      org.gwtproject.safehtml.shared.SafeHtml arg2, 
      java.lang.String arg3) {
  StringBuilder sb = new java.lang.StringBuilder();
  sb.append("<div aria-selected=\"");
  sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg3));
  sb.append("\"><div style=\"");
  sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0.asString()));
  sb.append("\" class=\"");
  sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg1));
  sb.append("\">");
  sb.append(arg2.asString());
  sb.append("</div></div>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}

}
