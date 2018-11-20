package org.gwtproject.dom.style.shared;

/**
 * Enum for the visibility property.
 */
public enum Visibility implements HasCssName {
  VISIBLE {
    @Override
    public String getCssName() {
      return "visible";
    }
  },
  HIDDEN {
    @Override
    public String getCssName() {
      return "hidden";
    }
  };

  @Override
  public abstract String getCssName();
}
