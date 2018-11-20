package org.gwtproject.dom.style.shared;

/**
 * Enum for the outline-style property.
 */
public enum OutlineStyle implements HasCssName {
  NONE {
    @Override
    public String getCssName() {
      return "none";
    }
  },
  DASHED {
    @Override
    public String getCssName() {
      return "dashed";
    }
  },
  DOTTED {
    @Override
    public String getCssName() {
      return "dotted";
    }
  },
  DOUBLE {
    @Override
    public String getCssName() {
      return "double";
    }
  },
  GROOVE {
    @Override
    public String getCssName() {
      return "groove";
    }
  },
  INSET {
    @Override
    public String getCssName() {
      return "inset";
    }
  },
  OUTSET {
    @Override
    public String getCssName() {
      return "outset";
    }
  },
  RIDGE {
    @Override
    public String getCssName() {
      return "ridge";
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
