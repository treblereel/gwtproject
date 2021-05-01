/*
 * Copyright 2011 Google Inc.
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
package org.gwtproject.user.client.ui;

import elemental2.dom.DomGlobal;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsFunction;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;
import org.gwtproject.core.client.JavaScriptObject;
import org.gwtproject.dom.builder.shared.HtmlBuilderFactory;
import org.gwtproject.dom.builder.shared.HtmlElementBuilder;
import org.gwtproject.dom.client.Element;
import org.gwtproject.user.client.DOM;

/**
 * EXPERIMENTAL and subject to change. Do not use this in production code.
 *
 * <p>A simple {@link Element} implementation (<strong>not</strong> an actual dom object) that can
 * serve as stand in to be used by {@link IsRenderable} widgets before they are fully built. For
 * example, it can accumulate simple set*() values to be used when the widget is actually ready to
 * render. Thus, most {@link IsRenderable} widget code can be written without taking into account
 * whether or not the widget has yet been rendered.
 *
 * <p>{@link DOM#appendChild} is aware of PotentialElement, and calls its resolve() method. This
 * triggers a call to {@link UIObject#resolvePotentialElement()}, which widgets can customize to get
 * a real {@link Element} in place at the last moment.
 *
 * <p>TODO(rdcastro): Cover all unsupported methods with helpful error messages.
 */
public class PotentialElement extends Element {

  static {
    declareShim();
  }

  public static PotentialElement as(Element e) {
    assert isPotential(e);
    return (PotentialElement) e;
  }

  /**
   * Builds a new PotentialElement with the tag name set to "div".
   *
   * @see #build(UIObject,String)
   */
  public static PotentialElement build(UIObject o) {
    return build(o, "div");
  }

  /**
   * Builds a new PotentialElement. This element keeps track of the {@link UIObject} so that it can
   * call {@link UIObject#resolvePotentialElement} to get a real element when that is needed.
   */
  public static PotentialElement build(UIObject o, String tagName) {
    GwtPotentialElementShim shim = new GwtPotentialElementShim();
    shim.tagName = tagName;
    shim.__gwt_resolve = () -> buildResolveCallback(o);
    return Js.uncheckedCast(Element.as(shim));
  }

  /**
   * Creates an {@link HtmlElementBuilder} instance inheriting all attributes set for the given
   * PotentialElement.
   *
   * @param potentialElement assumed to be a PotentialElement, used as basis for the builder
   * @return a propertly configured {@link HtmlElementBuilder} instance
   */
  public static HtmlElementBuilder createBuilderFor(Element potentialElement) {
    PotentialElement el = PotentialElement.as(potentialElement);
    HtmlElementBuilder builder = HtmlBuilderFactory.get().trustedCreate(el.getTagName());
    el.mergeInto(builder);
    return builder;
  }

  /**
   * Tests whether a given {@link JavaScriptObject} represents a PotentialElement.
   *
   * @param o the {@link JavaScriptObject} to be tested
   * @return true if the given object is a PotentialElement instance
   */
  public static boolean isPotential(JavaScriptObject o) {
    return DOM.isPotential(o);
  }

  /**
   * If given a PotentialElement, returns the real Element to be built from it. Otherwise returns
   * the given Element itself.
   *
   * <p>Note that a PotentialElement can only be resolved once. Making repeated calls to this method
   * with the same PotentialElement is an error.
   */
  public static Element resolve(Element maybePotential) {
    return maybePotential.<PotentialElement>cast().resolve();
  }

  // TODO MISSED         this.__gwt_resolve =
  // @com.google.gwt.user.client.ui.PotentialElement::cannotResolveTwice();
  private static JavaScriptObject buildResolveCallback(UIObject resolver) {
    Fn func =
        () -> {
          // this.__gwt_resolve =
          // @com.google.gwt.user.client.ui.PotentialElement::cannotResolveTwice();
          return resolver.resolvePotentialElement();
        };
    return Js.uncheckedCast(func);
  }

  private static final void cannotResolveTwice() {
    throw new Error("A PotentialElement cannot be resolved twice.");
  }

  // TODO DO WE NEED IT ?
  private static final void declareShim() {
    Js.asPropertyMap(DomGlobal.window)
        .set("GwtPotentialElementShim", new GwtPotentialElementShim());
  }

  @JsConstructor
  protected PotentialElement() {}

  final Element setResolver(UIObject resolver) {
    ((JsPropertyMap) this).set("__gwt_resolve", buildResolveCallback(resolver));
    return this;
  }

  /**
   * Copy only the fields that have actually changed from the values in the shim prototype. Do this
   * by severing the __proto__ link, allowing us to iterate only on the fields set in this specific
   * instance.
   */
  private void mergeInto(HtmlElementBuilder builder) {
    JsPropertyMap _this = ((JsPropertyMap) this);

    String savedProto = _this.get("__proto__").toString();
    String tagName = _this.get("tagName").toString();
    String gwtResolve = _this.get("__gwt_resolve").toString();
    String className = _this.get("className").toString();
    try {
      _this.set("__proto__", null);
      _this.set("tagName", null);
      _this.set("__gwt_resolve", null);

      // className needs special treatment because the actual HTML attribute is
      // called "class" and not "className".
      if (_this.get("className") != null) {
        builder.className(_this.get("className").toString());
        _this.set("className", null);
      }
      // Iterate over all attributes, and copy them to the ElementBuilder.
      // TODO(rdcastro): Deal with the "style" attribute.
      _this.forEach(
          fn -> {
            if (_this.has(fn)) {
              Object obj = _this.get(fn);
              if (obj instanceof String) {
                builder.attribute(fn, _this.get(fn).toString());
              } else if (obj instanceof Number) {
                builder.attribute(fn, Integer.valueOf(_this.get(fn).toString()));
              }
            }
          });
    } finally {
      _this.set("__proto__", savedProto);
      if (className != null) {
        _this.set("className", className);
      }
      _this.set("__gwt_resolve", gwtResolve);
      _this.set("tagName", tagName);
    }
  }

  /**
   * Calls the <code>__gwt_resolve</code> method on the underlying JavaScript object if it exists.
   * On objects created via {@link #build}, this method is a call to the {@link
   * UIObject#resolvePotentialElement} method on the associated UIObject.
   */
  private Element resolve() {
    return ((JsPropertyMap) this).has("__gwt_resolve")
        ? ((Fn) ((JsPropertyMap) this).get("__gwt_resolve")).onInvoke()
        : this;
  }

  @FunctionalInterface
  @JsFunction
  interface Fn {
    Element onInvoke();
  }
}
