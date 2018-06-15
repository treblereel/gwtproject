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

package org.gwtproject.geolocation.client;

import com.google.gwt.core.client.JavaScriptObject;

import java.util.logging.Logger;

import elemental2.core.JsObject;
import elemental2.dom.DomGlobal;
import elemental2.dom.GeolocationPosition;
import elemental2.dom.GeolocationPositionError;
import elemental2.dom.GeolocationPositionOptions;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;

import static elemental2.dom.DomGlobal.window;

/**
 * Implements the HTML5 Geolocation interface.
 * 
 * <p>
 * You can obtain a user's position by first calling
 * <code>Geolocation.getIfSupported()</code>
 * </p>
 * 
 * <p>
 * Once you have a <code>Geolocation</code>, you can request the user's current
 * position by calling {@link #getCurrentPosition(Callback)} or
 * {@link #watchPosition(Callback)}.
 * </p>
 * 
 * <p>
 * The first time an application requests the user's position, the browser will
 * prompt the user for permission. If the user grants permission, the browser
 * will locate the user and report it back to your application. If the user
 * declines permission, the callback's {@link Callback#onFailure(Object)} method
 * will be called with a {@link PositionError} with its code set to
 * {@link PositionError#PERMISSION_DENIED}.
 * </p>
 * 
 * <p>
 * <span style="color:red;">Experimental API: This API is still under
 * development and is subject to change.</span>
 * 
 * <p>
 * This may not be supported on all browsers.
 * </p>
 * 
 * @see <a href="http://www.w3.org/TR/geolocation-API/">W3C Geolocation API</a>
 * @see <a href="http://diveintohtml5.info/geolocation.html">Dive Into HTML5 -
 *      Geolocation</a>
 */
public class Geolocation {
  private static final Logger LOGGER = Logger.getLogger(Geolocation.class.getName());
  private static GeolocationSupportDetector detector;
  private static Geolocation impl;

  /**
   * Detector for browser support for Geolocation.
   */
  private static class GeolocationSupportDetector {

    private static boolean detectSupport(){
      return window.navigator.geolocation!=null;
    }

    private boolean supported = detectSupport();

    public boolean isSupported() {
      return supported;
    }
  }

  /**
   * Additional options for receiving the user's location.
   */
  @JsType(isNative = true, namespace = JsPackage.GLOBAL)
  public static class PositionOptions {

    /**
     * Sets whether or not the application will request a more accurate position
     * from the browser.
     *
     * <p>
     * If the browser supports this option, the user will be prompted to grant
     * permission to this application, even if permission to get the user's
     * (less accurate) position has already been granted.</p>
     *
     * <p>
     * Requesting high accuracy may be slower, or not supported at all,
     * depending on the browser.
     * </p>
     *
     * <p>
     * By default this is <code>false</code>
     * </p>
     */
    @JsProperty
    public native PositionOptions setHighAccuracyEnabled(boolean enabled);

    /**
     * Allows the browser to return a position immediately with a cached
     * position. The maximum age is then the oldest acceptable cached
     * position. If no acceptable cached position is found, the browser will
     * locate the user and cache and return the position.
     *
     * <p>
     * By default this is 0, which means that the position cache will not be
     * used.
     * </p>
     */
    @JsProperty
    public native PositionOptions setMaximumAge(int maximumAge);

    /**
     * Sets the amount of time (in milliseconds) that the application is willing
     * to wait before getting the user's position. If a request for position
     * takes more than this amount of time, an error will result.
     *
     * <p>
     * By default this is -1, which means there is no application-specified
     * timeout.
     * </p>
     */
    @JsProperty
    public native PositionOptions setTimeout(int timeout);
  }

  /**
   * Returns a {@link Geolocation} if the browser supports this feature, and
   * <code>null</code> otherwise.
   */
  public static Geolocation getIfSupported() {
    if (!isSupported()) {
      return null;
    } else {
      LOGGER.fine("geolocation supported");
      if (impl == null) {
        impl = new Geolocation();
      }
      return impl;
    }
  }

  /**
   * Returns <code>true</code> if the browser supports geolocation.
   */
  public static boolean isSupported() {
    if (detector == null) {
      detector = new GeolocationSupportDetector();
    }
    return detector.isSupported();
  }

  private static void handleFailure(Callback<Position, PositionError> callback, int code,
      String msg) {
    callback.onFailure(new PositionError(code, msg));
  }

  private static void handleSuccess(Callback<Position, PositionError> callback, Position pos) {
    callback.onSuccess(pos);
  }

  /**
   * Should be instantiated by {@link #getIfSupported()}.
   */
  protected Geolocation() {
  }

  /**
   * Stops watching the user's position.
   *
   * @param watchId the ID of a position watch as returned by a previous call to
   *        {@link #watchPosition(Callback)}.
   */
  public void clearWatch(int watchId) {
    window.navigator.geolocation.clearWatch(watchId);
  };

  /**
   * Calls the callback with the user's current position.
   */
  public void getCurrentPosition(Callback<Position, PositionError> callback) {
    getCurrentPosition(callback, null);
  }

  /**
   * Calls the callback with the user's current position, with additional
   * options.
   */
  public void getCurrentPosition(Callback<Position, PositionError> callback,
      PositionOptions options) {
    GeolocationPositionOptions geolocationPositionOptions = Js.uncheckedCast(options);
    if(isSupported()) {
      window.navigator.geolocation.getCurrentPosition(p0 -> {
        Position result = Js.uncheckedCast(p0);
        handleSuccess(callback, result);
        return null;
      }, p0 -> {
        handleFailure(callback, (int) p0.getCode(), p0.getMessage());
        return null;
      }, geolocationPositionOptions);
    }
  }

  /**
   * Repeatedly calls the given callback with the user's position, as it
   * changes.
   *
   * <p>
   * The frequency of these updates is entirely up to the browser. There is no
   * guarantee that updates will be received at any set interval, but are
   * instead designed to be sent when the user's position changes. This method
   * should be used instead of polling the user's current position.
   * </p>
   *
   * @return the ID of this watch, which can be passed to
   *         {@link #clearWatch(int)} to stop watching the user's position.
   */
  public int watchPosition(Callback<Position, PositionError> callback) {
    return watchPosition(callback, null);
  }

  /**
   * Repeatedly calls the given callback with the user's position, as it
   * changes, with additional options.
   * 
   * <p>
   * The frequency of these updates is entirely up to the browser. There is no
   * guarantee that updates will be received at any set interval, but are
   * instead designed to be sent when the user's position changes. This method
   * should be used instead of polling the user's current position.
   * </p>
   * 
   * <p>
   * If the browser does not support geolocation, this method will do nothing,
   * and will return -1.
   * </p>
   * 
   * @return the ID of this watch, which can be passed to
   *         {@link #clearWatch(int)} to stop watching the user's position.
   */
  public native int watchPosition(Callback<Position, PositionError> callback,
      PositionOptions options) /*-{
    var opt = @com.google.gwt.geolocation.client.Geolocation::toJso(*)(options);

    var success = $entry(function(pos) {
      @com.google.gwt.geolocation.client.Geolocation::handleSuccess(*)(callback, pos);
    });

    var failure = $entry(function(err) {
      @com.google.gwt.geolocation.client.Geolocation::handleFailure(*)
      (callback, err.code, err.message);
    });

    var id = -1;
    if (@com.google.gwt.geolocation.client.Geolocation::isSupported()) {
      id = $wnd.navigator.geolocation.watchPosition(success, failure, opt);
    }
    return id;
  }-*/;
}
