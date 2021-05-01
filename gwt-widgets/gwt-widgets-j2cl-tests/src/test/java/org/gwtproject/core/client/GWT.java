/*
 * Copyright © 2019 The GWT Project Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.gwtproject.core.client;

import elemental2.dom.DomGlobal;
import jsinterop.annotations.JsMethod;

public final class GWT {

  public interface UncaughtExceptionHandler {
    void onUncaughtException(Throwable e);
  }

  public GWT() {
  }


  public static void setUncaughtExceptionHandler(UncaughtExceptionHandler handler) {

  }

  /** @deprecated */
  @Deprecated
  public static <T> T create(Class<?> clazz) {
    return null;
  }

  /** @deprecated */
  @Deprecated
  public static boolean isProdMode() {
    return !"on".equals(System.getProperty("superdevmode"));
  }

  /** @deprecated */
  @Deprecated
  public static String getModuleName() {
    return null;
  }

  /** @deprecated */
  @Deprecated
  public static String getModuleBaseURL() {
    return DomGlobal.window.location.href;
  }

  /** @deprecated */
  @Deprecated
  public static void log(String msg) {

  }

  /** @deprecated */
  @Deprecated
  public static void log(String msg, Exception e) {

  }

  /** @deprecated */
  @Deprecated
  public static void reportUncaughtException(Throwable e) {
    DomGlobal.setTimeout((ignore) -> {
      throw_(e);
    }, 0.0D, new Object[0]);
  }

  @JsMethod(
      namespace = "<window>",
      name = "throw"
  )
  private static native void throw_(Object var0);

  public static boolean isScript() {
    return true;
  }
}
