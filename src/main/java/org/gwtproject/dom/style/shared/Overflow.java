package org.gwtproject.dom.style.shared;

/**
 * Enum for the overflow property.
 */
public enum Overflow implements HasCssName {
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
  },
  SCROLL {
    @Override
    public String getCssName() {
      return "scroll";
    }
  },
  AUTO {
    @Override
    public String getCssName() {
      return "auto";
    }
  };

  @Override
  public abstract String getCssName();
}