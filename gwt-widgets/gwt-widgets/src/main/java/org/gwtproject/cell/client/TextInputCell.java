/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.gwtproject.cell.client;

import org.gwtproject.dom.client.BrowserEvents;
import org.gwtproject.dom.client.Element;
import org.gwtproject.dom.client.InputElement;
import org.gwtproject.dom.client.NativeEvent;
import org.gwtproject.safehtml.client.SafeHtmlTemplates;
import org.gwtproject.safehtml.shared.SafeHtml;
import org.gwtproject.safehtml.shared.SafeHtmlBuilder;
import org.gwtproject.text.shared.SafeHtmlRenderer;

/** An {@link org.gwtproject.cell.client.AbstractCell} used to render a text input. */
public class TextInputCell
    extends org.gwtproject.cell.client.AbstractInputCell<String, TextInputCell.ViewData> {

  interface Template extends SafeHtmlTemplates {

    TextInputCell.Template INSTANCE = new TextInputCell_TemplateImpl();

    SafeHtml input(String value);
  }

  /** The {@code ViewData} for this cell. */
  public static class ViewData {
    /** The last value that was updated. */
    private String lastValue;

    /** The current value. */
    private String curValue;

    /**
     * Construct a ViewData instance containing a given value.
     *
     * @param value a String value
     */
    public ViewData(String value) {
      this.lastValue = value;
      this.curValue = value;
    }

    /**
     * Return true if the last and current values of this ViewData object are equal to those of the
     * other object.
     */
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof ViewData)) {
        return false;
      }
      ViewData vd = (ViewData) other;
      return equalsOrNull(lastValue, vd.lastValue) && equalsOrNull(curValue, vd.curValue);
    }

    /**
     * Return the current value of the input element.
     *
     * @return the current value String
     * @see #setCurrentValue(String)
     */
    public String getCurrentValue() {
      return curValue;
    }

    /**
     * Return the last value sent to the {@link org.gwtproject.cell.client.ValueUpdater}.
     *
     * @return the last value String
     * @see #setLastValue(String)
     */
    public String getLastValue() {
      return lastValue;
    }

    /** Return a hash code based on the last and current values. */
    @Override
    public int hashCode() {
      return (lastValue + "_*!@HASH_SEPARATOR@!*_" + curValue).hashCode();
    }

    /**
     * Set the current value.
     *
     * @param curValue the current value
     * @see #getCurrentValue()
     */
    protected void setCurrentValue(String curValue) {
      this.curValue = curValue;
    }

    /**
     * Set the last value.
     *
     * @param lastValue the last value
     * @see #getLastValue()
     */
    protected void setLastValue(String lastValue) {
      this.lastValue = lastValue;
    }

    private boolean equalsOrNull(Object a, Object b) {
      return (a != null) ? a.equals(b) : ((b == null) ? true : false);
    }
  }

  /** Constructs a TextInputCell that renders its text without HTML markup. */
  public TextInputCell() {
    super(BrowserEvents.CHANGE, BrowserEvents.KEYUP);
  }

  /**
   * Constructs a TextInputCell that renders its text using the given {@link SafeHtmlRenderer}.
   *
   * @param renderer parameter is ignored
   * @deprecated the value of a text input is never treated as html
   */
  @Deprecated
  public TextInputCell(SafeHtmlRenderer<String> renderer) {
    this();
  }

  @Override
  public void onBrowserEvent(
      Context context,
      Element parent,
      String value,
      NativeEvent event,
      org.gwtproject.cell.client.ValueUpdater<String> valueUpdater) {
    super.onBrowserEvent(context, parent, value, event, valueUpdater);

    // Ignore events that don't target the input.
    InputElement input = getInputElement(parent);
    Element target = event.getEventTarget().cast();
    if (!input.isOrHasChild(target)) {
      return;
    }

    String eventType = event.getType();
    Object key = context.getKey();
    if (BrowserEvents.CHANGE.equals(eventType)) {
      finishEditing(parent, value, key, valueUpdater);
    } else if (BrowserEvents.KEYUP.equals(eventType)) {
      // Record keys as they are typed.
      ViewData vd = getViewData(key);
      if (vd == null) {
        vd = new ViewData(value);
        setViewData(key, vd);
      }
      vd.setCurrentValue(input.getValue());
    }
  }

  @Override
  public void render(Context context, String value, SafeHtmlBuilder sb) {
    // Get the view data.
    Object key = context.getKey();
    ViewData viewData = getViewData(key);
    if (viewData != null && viewData.getCurrentValue().equals(value)) {
      clearViewData(key);
      viewData = null;
    }

    String s = (viewData != null) ? viewData.getCurrentValue() : value;
    if (s != null) {
      sb.append(Template.INSTANCE.input(s));
    } else {
      sb.appendHtmlConstant("<input type=\"text\" tabindex=\"-1\"></input>");
    }
  }

  @Override
  protected void finishEditing(
      Element parent,
      String value,
      Object key,
      org.gwtproject.cell.client.ValueUpdater<String> valueUpdater) {
    String newValue = getInputElement(parent).getValue();

    // Get the view data.
    ViewData vd = getViewData(key);
    if (vd == null) {
      vd = new ViewData(value);
      setViewData(key, vd);
    }
    vd.setCurrentValue(newValue);

    // Fire the value updater if the value has changed.
    if (valueUpdater != null && !vd.getCurrentValue().equals(vd.getLastValue())) {
      vd.setLastValue(newValue);
      valueUpdater.update(newValue);
    }

    // Blur the element.
    super.finishEditing(parent, newValue, key, valueUpdater);
  }

  @Override
  protected InputElement getInputElement(Element parent) {
    return super.getInputElement(parent).<InputElement>cast();
  }
}
