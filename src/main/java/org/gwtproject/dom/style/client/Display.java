package org.gwtproject.dom.style.client;

/**
 * Enum for the display property.
 */
public enum Display implements HasCssName {
  NONE {
    @Override
    public String getCssName() {
      return "none";
    }
  },
  BLOCK {
    @Override
    public String getCssName() {
      return "block";
    }
  },
  INLINE {
    @Override
    public String getCssName() {
      return "inline";
    }
  },
  INLINE_BLOCK {
    @Override
    public String getCssName() {
      return "inline-block";
    }
  },
  INLINE_TABLE {
    @Override
    public String getCssName() {
      return "inline-table";
    }
  },
  LIST_ITEM {
    @Override
    public String getCssName() {
      return "list-item";
    }
  },
  RUN_IN {
    @Override
    public String getCssName() {
      return "run-in";
    }
  },
  TABLE {
    @Override
    public String getCssName() {
      return "table";
    }
  },
  TABLE_CAPTION {
    @Override
    public String getCssName() {
      return "table-caption";
    }
  },
  TABLE_COLUMN_GROUP {
    @Override
    public String getCssName() {
      return "table-column-group";
    }
  },
  TABLE_HEADER_GROUP {
    @Override
    public String getCssName() {
      return "table-header-group";
    }
  },
  TABLE_FOOTER_GROUP {
    @Override
    public String getCssName() {
      return "table-footer-group";
    }
  },
  TABLE_ROW_GROUP {
    @Override
    public String getCssName() {
      return "table-row-group";
    }
  },
  TABLE_CELL {
    @Override
    public String getCssName() {
      return "table-cell";
    }
  },
  TABLE_COLUMN {
    @Override
    public String getCssName() {
      return "table-column";
    }
  },
  TABLE_ROW {
    @Override
    public String getCssName() {
      return "table-row";
    }
  },
  INITIAL {
    @Override
    public String getCssName() {
      return "initial";
    }
  },
  FLEX {
    @Override
    public String getCssName() {
      return "flex";
    }
  },
  INLINE_FLEX {
    @Override
    public String getCssName() {
      return "inline-flex";
    }
  };

  @Override
  public abstract String getCssName();
}
