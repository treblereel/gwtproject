package org.gwtproject.dom.style.shared;

/**
 * Enum for the 'white-space' CSS property.
 */
public enum WhiteSpace implements HasCssName {
  NORMAL {
    @Override
    public String getCssName() {
      return "normal";
    }
  },
  NOWRAP {
    @Override
    public String getCssName() {
      return "nowrap";
    }
  },
  PRE {
    @Override
    public String getCssName() {
      return "pre";
    }
  },
  PRE_LINE {
    @Override
    public String getCssName() {
      return "pre-line";
    }
  },
  PRE_WRAP {
    @Override
    public String getCssName() {
      return "pre-wrap";
    }
  };

  @Override
  public abstract String getCssName();
}
