package org.gwtproject.dom.style.client;

/**
 * Enum for the font-style property.
 */
public enum FontStyle implements HasCssName {
  NORMAL {
    @Override
    public String getCssName() {
      return "normal";
    }
  },
  ITALIC {
    @Override
    public String getCssName() {
      return "italic";
    }
  },
  OBLIQUE {
    @Override
    public String getCssName() {
      return "oblique";
    }
  };

  @Override
  public abstract String getCssName();
}
