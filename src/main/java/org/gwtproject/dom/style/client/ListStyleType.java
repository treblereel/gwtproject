package org.gwtproject.dom.style.client;

/**
 * Enum for the list-style-type property.
 */
public enum ListStyleType implements HasCssName {
  NONE {
    @Override
    public String getCssName() {
      return "none";
    }
  },
  DISC {
    @Override
    public String getCssName() {
      return "disc";
    }
  },
  CIRCLE {
    @Override
    public String getCssName() {
      return "circle";
    }
  },
  SQUARE {
    @Override
    public String getCssName() {
      return "square";
    }
  },
  DECIMAL {
    @Override
    public String getCssName() {
      return "decimal";
    }
  },
  LOWER_ALPHA {
    @Override
    public String getCssName() {
      return "lower-alpha";
    }
  },
  UPPER_ALPHA {
    @Override
    public String getCssName() {
      return "upper-alpha";
    }
  },
  LOWER_ROMAN {
    @Override
    public String getCssName() {
      return "lower-roman";
    }
  },
  UPPER_ROMAN {
    @Override
    public String getCssName() {
      return "upper-roman";
    }
  };

  @Override
  public abstract String getCssName();
}
