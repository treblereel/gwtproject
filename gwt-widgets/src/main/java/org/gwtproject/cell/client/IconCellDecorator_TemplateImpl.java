package org.gwtproject.cell.client;

/**
 * This class is generated from org.gwtproject.cell.client.IconCellDecorator.Template, do not edit manually
 */
public class IconCellDecorator_TemplateImpl implements org.gwtproject.cell.client.IconCellDecorator.Template {
  
  /**
   * @Template("<div style=\"{0}position:relative;zoom:1;\">{1}<div>{2}</div></div>")
   */
  public org.gwtproject.safehtml.shared.SafeHtml outerDiv(
        org.gwtproject.safecss.shared.SafeStyles arg0, 
        org.gwtproject.safehtml.shared.SafeHtml arg1, 
        org.gwtproject.safehtml.shared.SafeHtml arg2) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<div style=\"");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0.asString()));
    sb.append("position:relative;zoom:1;\">");
    sb.append(arg1.asString());
    sb.append("<div>");
    sb.append(arg2.asString());
    sb.append("</div></div>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}


/**
 * @Template("<div style=\"{0}position:absolute;bottom:0px;line-height:0px;\">{1}</div>")
 */
public org.gwtproject.safehtml.shared.SafeHtml imageWrapperBottom(
      org.gwtproject.safecss.shared.SafeStyles arg0, 
      org.gwtproject.safehtml.shared.SafeHtml arg1) {
  StringBuilder sb = new java.lang.StringBuilder();
  sb.append("<div style=\"");
  sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0.asString()));
  sb.append("position:absolute;bottom:0px;line-height:0px;\">");
  sb.append(arg1.asString());
  sb.append("</div>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}


/**
 * @Template("<div style=\"{0}position:absolute;top:50%;line-height:0px;\">{1}</div>")
 */
public org.gwtproject.safehtml.shared.SafeHtml imageWrapperMiddle(
      org.gwtproject.safecss.shared.SafeStyles arg0, 
      org.gwtproject.safehtml.shared.SafeHtml arg1) {
  StringBuilder sb = new java.lang.StringBuilder();
  sb.append("<div style=\"");
  sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0.asString()));
  sb.append("position:absolute;top:50%;line-height:0px;\">");
  sb.append(arg1.asString());
  sb.append("</div>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}


/**
 * @Template("<div style=\"{0}position:absolute;top:0px;line-height:0px;\">{1}</div>")
 */
public org.gwtproject.safehtml.shared.SafeHtml imageWrapperTop(
      org.gwtproject.safecss.shared.SafeStyles arg0, 
      org.gwtproject.safehtml.shared.SafeHtml arg1) {
  StringBuilder sb = new java.lang.StringBuilder();
  sb.append("<div style=\"");
  sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0.asString()));
  sb.append("position:absolute;top:0px;line-height:0px;\">");
  sb.append(arg1.asString());
  sb.append("</div>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}

}
