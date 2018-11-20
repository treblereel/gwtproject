package org.gwtproject.dom.style.shared;

/**
 * Enum for the 'text-decoration' CSS property.
 */
public enum TextDecoration implements HasCssName {
  BLINK {
    @Override
    public String getCssName() {
      return "blink";
    }
  },
  LINE_THROUGH {
    @Override
    public String getCssName() {
      return "line-through";
    }
  },
  NONE {
    @Override
    public String getCssName() {
      return "none";
    }
  },
  OVERLINE {
    @Override
    public String getCssName() {
      return "overline";
    }
  },
  UNDERLINE {
    @Override
    public String getCssName() {
      return "underline";
    }
  };

  @Override
  public abstract String getCssName();
}
