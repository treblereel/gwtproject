package org.gwtproject.dom.style.shared;

/**
 * Enum for the vertical-align property.
 */
public enum VerticalAlign implements HasCssName {
  BASELINE {
    @Override
    public String getCssName() {
      return "baseline";
    }
  },
  SUB {
    @Override
    public String getCssName() {
      return "sub";
    }
  },
  SUPER {
    @Override
    public String getCssName() {
      return "super";
    }
  },
  TOP {
    @Override
    public String getCssName() {
      return "top";
    }
  },
  TEXT_TOP {
    @Override
    public String getCssName() {
      return "text-top";
    }
  },
  MIDDLE {
    @Override
    public String getCssName() {
      return "middle";
    }
  },
  BOTTOM {
    @Override
    public String getCssName() {
      return "bottom";
    }
  },
  TEXT_BOTTOM {
    @Override
    public String getCssName() {
      return "text-bottom";
    }
  };

  @Override
  public abstract String getCssName();
}
