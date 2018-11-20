package org.gwtproject.dom.style.shared;

/**
 * CSS length units.
 */
public enum Unit {
  PX {
    @Override
    public String getType() {
      return "px";
    }
  },
  PCT {
    @Override
    public String getType() {
      return "%";
    }
  },
  EM {
    @Override
    public String getType() {
      return "em";
    }
  },
  EX {
    @Override
    public String getType() {
      return "ex";
    }
  },
  PT {
    @Override
    public String getType() {
      return "pt";
    }
  },
  PC {
    @Override
    public String getType() {
      return "pc";
    }
  },
  IN {
    @Override
    public String getType() {
      return "in";
    }
  },
  CM {
    @Override
    public String getType() {
      return "cm";
    }
  },
  MM {
    @Override
    public String getType() {
      return "mm";
    }
  };

  public abstract String getType();
}
