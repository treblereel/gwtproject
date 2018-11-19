package org.gwtproject.dom.style.client;

/**
 * Enum for the position property.
 */
public enum Position implements HasCssName {
  STATIC {
    @Override
    public String getCssName() {
      return "static";
    }
  },
  RELATIVE {
    @Override
    public String getCssName() {
      return "relative";
    }
  },
  ABSOLUTE {
    @Override
    public String getCssName() {
      return "absolute";
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