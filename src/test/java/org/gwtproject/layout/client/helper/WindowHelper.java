package org.gwtproject.layout.client.helper;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true,
        name = "window",
        namespace = JsPackage.GLOBAL)
public class WindowHelper {

  public static native void moveTo(int x,
                                   int y);

  public static native void resizeTo(int width,
                                     int height);

  public static native void resizeBy(int width,
                                     int height);
}
