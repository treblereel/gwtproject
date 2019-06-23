package org.gwtproject.cell.client;

/**
 * This class is generated from org.gwtproject.cell.client.ImageLoadingCell.Template, do not edit manually
 */
public class ImageLoadingCell_TemplateImpl implements org.gwtproject.cell.client.ImageLoadingCell.Template {
  
  /**
   * @Template("<div style='height:0px;width:0px;overflow:hidden;'>{0}</div>")
   */
  public org.gwtproject.safehtml.shared.SafeHtml image(
        org.gwtproject.safehtml.shared.SafeHtml arg0) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<div style='height:0px;width:0px;overflow:hidden;'>");
    sb.append(arg0.asString());
    sb.append("</div>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}


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


/**
 * @Template("<div>{0}</div>")
 */
public org.gwtproject.safehtml.shared.SafeHtml loading(
      org.gwtproject.safehtml.shared.SafeHtml arg0) {
  StringBuilder sb = new java.lang.StringBuilder();
  sb.append("<div>");
  sb.append(arg0.asString());
  sb.append("</div>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}

}
