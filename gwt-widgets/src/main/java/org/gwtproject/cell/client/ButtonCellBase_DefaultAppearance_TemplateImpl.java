package org.gwtproject.cell.client;

/**
 * This class is generated from org.gwtproject.cell.client.ButtonCellBase.DefaultAppearance.Template, do not edit manually
 */
public class ButtonCellBase_DefaultAppearance_TemplateImpl implements org.gwtproject.cell.client.ButtonCellBase.DefaultAppearance.Template {

    /**
     * @Template("<div class=\"{0}\" style=\"{1}position:relative;zoom:0;\">{2}{3}</div>")
     */
    public org.gwtproject.safehtml.shared.SafeHtml iconContentLayout(
            java.lang.String arg0,
            org.gwtproject.safecss.shared.SafeStyles arg1,
            org.gwtproject.safehtml.shared.SafeHtml arg2,
            org.gwtproject.safehtml.shared.SafeHtml arg3) {
        StringBuilder sb = new java.lang.StringBuilder();
        sb.append("<div class=\"");
        sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
        sb.append("\" style=\"");
        sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg1.asString()));
        sb.append("position:relative;zoom:0;\">");
        sb.append(arg2.asString());
        sb.append(arg3.asString());
        sb.append("</div>");
        return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
    }

    /**
     * @Template("<div style=\"{0}position:absolute;top:50%;line-height:0px;\">{1}</div>")
     */
    public org.gwtproject.safehtml.shared.SafeHtml iconWrapper(
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
}
