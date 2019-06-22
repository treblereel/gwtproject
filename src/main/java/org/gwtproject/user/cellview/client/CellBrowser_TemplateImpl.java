package org.gwtproject.user.cellview.client;

/**
 * This class is generated from org.gwtproject.user.cellview.client.CellBrowser.Template, do not edit manually
 */
public class CellBrowser_TemplateImpl implements org.gwtproject.user.cellview.client.CellBrowser.Template {
  
  /**
   * @Template("<div __idx=\"{0}\" class=\"{1}\" style=\"{2}position:relative;outline:none;\">{3}<div>{4}</div></div>")
   */
  public org.gwtproject.safehtml.shared.SafeHtml div(
        int arg0, 
        java.lang.String arg1, 
        org.gwtproject.safecss.shared.SafeStyles arg2, 
        org.gwtproject.safehtml.shared.SafeHtml arg3, 
        org.gwtproject.safehtml.shared.SafeHtml arg4) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<div __idx=\"");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(String.valueOf(arg0)));
    sb.append("\" class=\"");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg1));
    sb.append("\" style=\"");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg2.asString()));
    sb.append("position:relative;outline:none;\">");
    sb.append(arg3.asString());
    sb.append("<div>");
    sb.append(arg4.asString());
    sb.append("</div></div>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}


/**
 * @Template("<div __idx=\"{0}\" class=\"{1}\" style=\"{2}position:relative;outline:none;\" tabindex=\"{3}\">{4}<div>{5}</div></div>")
 */
public org.gwtproject.safehtml.shared.SafeHtml divFocusable(
      int arg0, 
      java.lang.String arg1, 
      org.gwtproject.safecss.shared.SafeStyles arg2, 
      int arg3, 
      org.gwtproject.safehtml.shared.SafeHtml arg4, 
      org.gwtproject.safehtml.shared.SafeHtml arg5) {
  StringBuilder sb = new java.lang.StringBuilder();
  sb.append("<div __idx=\"");
  sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(String.valueOf(arg0)));
  sb.append("\" class=\"");
  sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg1));
  sb.append("\" style=\"");
  sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg2.asString()));
  sb.append("position:relative;outline:none;\" tabindex=\"");
  sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(String.valueOf(arg3)));
  sb.append("\">");
  sb.append(arg4.asString());
  sb.append("<div>");
  sb.append(arg5.asString());
  sb.append("</div></div>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}


/**
 * @Template("<div __idx=\"{0}\" class=\"{1}\" style=\"{2}position:relative;outline:none;\" tabindex=\"{3}\" accessKey=\"{4}\">{5}<div>{6}</div></div>")
 */
public org.gwtproject.safehtml.shared.SafeHtml divFocusableWithKey(
      int arg0, 
      java.lang.String arg1, 
      org.gwtproject.safecss.shared.SafeStyles arg2, 
      int arg3, 
      char arg4, 
      org.gwtproject.safehtml.shared.SafeHtml arg5, 
      org.gwtproject.safehtml.shared.SafeHtml arg6) {
  StringBuilder sb = new java.lang.StringBuilder();
  sb.append("<div __idx=\"");
  sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(String.valueOf(arg0)));
  sb.append("\" class=\"");
  sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg1));
  sb.append("\" style=\"");
  sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg2.asString()));
  sb.append("position:relative;outline:none;\" tabindex=\"");
  sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(String.valueOf(arg3)));
  sb.append("\" accessKey=\"");
  sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(String.valueOf(arg4)));
  sb.append("\">");
  sb.append(arg5.asString());
  sb.append("<div>");
  sb.append(arg6.asString());
  sb.append("</div></div>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}


/**
 * @Template("<div style=\"{0}position:absolute;\">{1}</div>")
 */
public org.gwtproject.safehtml.shared.SafeHtml imageWrapper(
      org.gwtproject.safecss.shared.SafeStyles arg0, 
      org.gwtproject.safehtml.shared.SafeHtml arg1) {
  StringBuilder sb = new java.lang.StringBuilder();
  sb.append("<div style=\"");
  sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0.asString()));
  sb.append("position:absolute;\">");
  sb.append(arg1.asString());
  sb.append("</div>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}

}
