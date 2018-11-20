package org.gwtproject.dom.style.shared;

/**
 * Enum for the float property.
 */
public enum Float implements HasCssName {
  LEFT {
    @Override
    public String getCssName() {
      return "left";
    }
  },
  RIGHT {
    @Override
    public String getCssName() {
      return "right";
    }
  },
  NONE {
    @Override
    public String getCssName() {
      return "none";
    }
  };

  @Override
  public abstract String getCssName();
}
