package org.gwtproject.dom.style.shared;

/**
 * Enum for the 'text-overflow' CSS3 property.
 */
public enum TextOverflow implements HasCssName {
  CLIP {
    @Override
    public String getCssName() {
      return "clip";
    }
  },
  ELLIPSIS {
    @Override
    public String getCssName() {
      return "ellipsis";
    }
  };

  @Override
  public abstract String getCssName();
}
