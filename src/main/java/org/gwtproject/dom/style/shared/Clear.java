package org.gwtproject.dom.style.shared;

/**
 * Enum for the 'clear' CSS property.
 */
public enum Clear implements HasCssName {
  BOTH {
    @Override
    public String getCssName() {
      return "both";
    }
  },
  LEFT {
    @Override
    public String getCssName() {
      return "left";
    }
  },
  NONE {
    @Override
    public String getCssName() {
      return "none";
    }
  },
  RIGHT {
    @Override
    public String getCssName() {
      return "right";
    }
  };

  @Override
  public abstract String getCssName();
}
