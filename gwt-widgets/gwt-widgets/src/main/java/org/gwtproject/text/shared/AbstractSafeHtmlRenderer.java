package org.gwtproject.text.shared;

import org.gwtproject.safehtml.shared.SafeHtml;
import org.gwtproject.safehtml.shared.SafeHtmlBuilder;
import org.gwtproject.safehtml.shared.SafeHtmlUtils;

public abstract class AbstractSafeHtmlRenderer<T> implements SafeHtmlRenderer<T> {

  private static final SafeHtml EMPTY_STRING = SafeHtmlUtils.fromSafeConstant("");

  public void render(T object, SafeHtmlBuilder appendable) {
    appendable.append(render(object));
  }

  protected SafeHtml toSafeHtml(Object obj) {
    return obj == null ? EMPTY_STRING : SafeHtmlUtils.fromString(String.valueOf(obj));
  }
}
