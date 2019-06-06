package org.gwtproject.text.shared;

import org.gwtproject.safehtml.shared.SafeHtml;
import org.gwtproject.safehtml.shared.SafeHtmlBuilder;
import org.gwtproject.safehtml.shared.SafeHtmlUtils;
import org.gwtproject.text.shared.SafeHtmlRenderer;

public class SimpleSafeHtmlRenderer implements SafeHtmlRenderer<String> {

  private static SimpleSafeHtmlRenderer instance;

  public static SimpleSafeHtmlRenderer getInstance() {
    if (instance == null) {
      instance = new SimpleSafeHtmlRenderer();
    }
    return instance;
  }

  private SimpleSafeHtmlRenderer() {}

  public SafeHtml render(String object) {
    return (object == null) ? SafeHtmlUtils.EMPTY_SAFE_HTML : SafeHtmlUtils.fromString(object);
  }

  public void render(String object, SafeHtmlBuilder appendable) {
    appendable.append(SafeHtmlUtils.fromString(object));
  }
}
