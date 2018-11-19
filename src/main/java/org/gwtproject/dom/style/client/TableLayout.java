package org.gwtproject.dom.style.client;

/**
 * Enum for the table-layout property.
 */
public enum TableLayout implements HasCssName {
  AUTO {
    @Override
    public String getCssName() {
      return "auto";
    }
  },
  FIXED {
    @Override
    public String getCssName() {
      return "fixed";
    }
  };

  @Override
  public abstract String getCssName();
}
