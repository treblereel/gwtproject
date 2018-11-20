package org.gwtproject.dom.style.shared;

/**
 * Enum for the 'text-transform' CSS property.
 */
public enum TextTransform implements HasCssName {
  CAPITALIZE {
    @Override
    public String getCssName() {
      return "capitalize";
    }
  },
  NONE {
    @Override
    public String getCssName() {
      return "none";
    }
  },
  LOWERCASE {
    @Override
    public String getCssName() {
      return "lowercase";
    }
  },
  UPPERCASE {
    @Override
    public String getCssName() {
      return "uppercase";
    }
  };

  @Override
  public abstract String getCssName();
}
