package org.gwtproject.dom.style.client;

/**
 * Enum for the font-weight property.
 */
public enum FontWeight implements HasCssName {
  NORMAL {
    @Override
    public String getCssName() {
      return "normal";
    }
  },
  BOLD {
    @Override
    public String getCssName() {
      return "bold";
    }
  },
  BOLDER {
    @Override
    public String getCssName() {
      return "bolder";
    }
  },
  LIGHTER {
    @Override
    public String getCssName() {
      return "lighter";
    }
  };

  @Override
  public abstract String getCssName();
}
