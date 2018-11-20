package org.gwtproject.dom.style.shared;

/**
 * Enum for the border-style property.
 */
public enum BorderStyle implements HasCssName {
  NONE {
    @Override
    public String getCssName() {
      return "none";
    }
  },
  DOTTED {
    @Override
    public String getCssName() {
      return "dotted";
    }
  },
  DASHED {
    @Override
    public String getCssName() {
      return "dashed";
    }
  },
  HIDDEN {
    @Override
    public String getCssName() {
      return "hidden";
    }
  },
  SOLID {
    @Override
    public String getCssName() {
      return "solid";
    }
  };

  @Override
  public abstract String getCssName();
}

