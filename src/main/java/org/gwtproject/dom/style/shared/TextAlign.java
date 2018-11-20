package org.gwtproject.dom.style.shared;

/**
 * Enum for the text-align property.
 */
public enum TextAlign implements HasCssName {
  CENTER {
    @Override
    public String getCssName() {
      return "center";
    }
  },
  JUSTIFY {
    @Override
    public String getCssName() {
      return "justify";
    }
  },
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
  };

  @Override
  public abstract String getCssName();
}
