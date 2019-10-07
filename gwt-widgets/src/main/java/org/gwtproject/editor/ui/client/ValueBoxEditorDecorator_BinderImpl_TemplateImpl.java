package org.gwtproject.editor.ui.client;

/**
 * This class is generated from org.gwtproject.editor.ui.client.ValueBoxEditorDecorator_BinderImpl.Template, do not edit manually
 */
public class ValueBoxEditorDecorator_BinderImpl_TemplateImpl implements org.gwtproject.editor.ui.client.ValueBoxEditorDecorator_BinderImpl.Template {
  
  /**
   * @Template("<div class='{0}' id='{1}'></div> <span id='{2}'></span>")
   */
  public org.gwtproject.safehtml.shared.SafeHtml html1(
        java.lang.String arg0, 
        java.lang.String arg1, 
        java.lang.String arg2) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<div class='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
    sb.append("' id='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg1));
    sb.append("'></div> <span id='");
    sb.append(org.gwtproject.safehtml.shared.SafeHtmlUtils.htmlEscape(arg2));
    sb.append("'></span>");
return new org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}

}
