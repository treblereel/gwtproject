/*
 * Copyright 2009 Google Inc.
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
package org.gwtproject.jsonp.client;

import static elemental2.dom.DomGlobal.document;

import org.gwtproject.safehtml.shared.annotations.IsTrustedResourceUri;
import org.gwtproject.safehtml.shared.annotations.SuppressIsTrustedResourceUriCastCheck;
import org.gwtproject.timer.client.Timer;
import org.gwtproject.user.client.rpc.AsyncCallback;

import elemental2.core.JsArray;
import elemental2.core.JsBoolean;
import elemental2.core.JsNumber;
import elemental2.dom.Element;
import elemental2.dom.HTMLScriptElement;

/**
 * A JSONP request that is waiting for a response. The request can be canceled.
 *
 * @param <T> the type of the response object.
 */
public class JsonpRequest<T> {

  /**
   * A global JS variable that holds the next index to use.
   */
  /* package */ static final String CALLBACKS_COUNTER_NAME = "__gwt_jsonp_counter__";

  /**
   * A global JS object that contains callbacks of pending requests.
   */
  /* package */ static final String CALLBACKS_NAME = "__gwt_jsonp__";
  private static final JsonpCallbacks CALLBACKS = getOrCreateCallbacks();

  /**
   * Prefix appended to all id's that are determined by the callbacks counter.
   */
  private static final String INCREMENTAL_ID_PREFIX = "I";

  /**
   * Prefix appended to all id's that are passed in by the user. The "P" 
   * suffix must stay in sync with ExternalTextResourceGenerator.java
   */
  private static final String PREDETERMINED_ID_PREFIX = "P";

  /**
   * Returns the next ID to use, incrementing the global counter.
   */
  private static int getAndIncrementCallbackCounter() {
    return CALLBACKS.counter++;
  }

  private static Element getHeadElement() {
    return document.getElementsByTagName("head").getAt(0);
  }

  /**
   * Returns a global object to store callbacks of pending requests, creating
   * it if it doesn't exist.
   */
  
  private static JsonpCallbacks getOrCreateCallbacks() {
    if (!JsonpGlobal.hasCallbacks()) {
      JsonpCallbacks callbacks = new JsonpCallbacks();
      callbacks.counter = 0;
      
      JsonpGlobal.setCallbacks(callbacks);
    }
    
    return JsonpGlobal.getCallbacks();
  }

  private static String getPredeterminedId(String suffix) {
    return PREDETERMINED_ID_PREFIX + suffix;
  }

  private static String nextCallbackId() {
    return INCREMENTAL_ID_PREFIX + getAndIncrementCallbackCounter();
  }

  private final String callbackId;

  private final int timeout;

  private final AsyncCallback<T> callback;

  /**
   * Whether the result is expected to be an integer or not.
   */
  private final boolean expectInteger;

  private final String callbackParam;

  private final String failureCallbackParam;

  private final boolean canHaveMultipleRequestsForSameId;

  /**
   * Timer which keeps track of timeouts.
   */
  private Timer timer;

  /**
   * Create a new JSONP request.
   *
   * @param callback The callback instance to notify when the response comes
   *          back
   * @param timeout Time in ms after which a {@link TimeoutException} will be
   *          thrown
   * @param expectInteger Should be true if T is {@link Integer}, false
   *          otherwise
   * @param callbackParam Name of the url param of the callback function name
   * @param failureCallbackParam Name of the url param containing the
   *          failure callback function name, or null for no failure callback
   */
  JsonpRequest(AsyncCallback<T> callback, int timeout, boolean expectInteger,
      String callbackParam, String failureCallbackParam) {
    callbackId = nextCallbackId();
    this.callback = callback;
    this.timeout = timeout;
    this.expectInteger = expectInteger;
    this.callbackParam = callbackParam;
    this.failureCallbackParam = failureCallbackParam;
    this.canHaveMultipleRequestsForSameId = false;
  }

  /**
   * Create a new JSONP request with a hardcoded id. This could be used to
   * manually control which resources are considered duplicates (by giving them
   * identical ids). Could also be used if the callback name needs to be
   * completely user controlled (since the id is part of the callback name).
   *
   * @param callback The callback instance to notify when the response comes
   *          back
   * @param timeout Time in ms after which a {@link TimeoutException} will be
   *          thrown
   * @param expectInteger Should be true if T is {@link Integer}, false
   *          otherwise
   * @param callbackParam Name of the url param of the callback function name
   * @param failureCallbackParam Name of the url param containing the
   *          failure callback function name, or null for no failure callback
   * @param id unique id for the resource that is being fetched
   */
  JsonpRequest(AsyncCallback<T> callback, int timeout, boolean expectInteger,
      String callbackParam, String failureCallbackParam, String id) {
    callbackId = getPredeterminedId(id);
    this.callback = callback;
    this.timeout = timeout;
    this.expectInteger = expectInteger;
    this.callbackParam = callbackParam;
    this.failureCallbackParam = failureCallbackParam;
    this.canHaveMultipleRequestsForSameId = true;
  }

  /**
   * Cancels a pending request.  Note that if you are using preset ID's, this
   * will not work, since there is no way of knowing if there are other
   * requests pending (or have already returned) for the same data.
   */
  public void cancel() {
    timer.cancel();
    unload();
  }

  public AsyncCallback<T> getCallback() {
    return callback;
  }

  public int getTimeout() {
    return timeout;
  }

  @Override
  public String toString() {
    return "JsonpRequest(id=" + callbackId + ")";
  }

  // @VisibleForTesting
  String getCallbackId() {
    return callbackId;
  }

  /**
   * Sends a request using the JSONP mechanism.
   *
   * @param baseUri To be sent to the server.
   */
  @SuppressIsTrustedResourceUriCastCheck
  void send(@IsTrustedResourceUri final String baseUri) {
    registerCallbacks(CALLBACKS, canHaveMultipleRequestsForSameId);
    StringBuilder uri = new StringBuilder(baseUri);
    uri.append(baseUri.contains("?") ? "&" : "?");
    String prefix = CALLBACKS_NAME + "." + callbackId;

    uri.append(callbackParam).append("=").append(prefix).append(
        ".onSuccess");
    if (failureCallbackParam != null) {
      uri.append("&");
      uri.append(failureCallbackParam).append("=").append(prefix).append(
          ".onFailure");
    }
    HTMLScriptElement script = (HTMLScriptElement) document.createElement("script");
    script.type = "text/javascript";
    script.id = callbackId;
    script.src = uri.toString();
    timer = new Timer() {
      @Override
      public void run() {
        onFailure(new TimeoutException("Timeout while calling " + baseUri));
      }
    };
    timer.schedule(timeout);
    getHeadElement().appendChild(script);
  }
  
  private void onFailure(String message) {
    onFailure(new Exception(message));
  }

  private void onFailure(Throwable ex) {
    timer.cancel();
    try {
      if (callback != null) {
        callback.onFailure(ex);
      }
    } finally {
      unload();
    }
  }

  private void onSuccess(T data) {
    timer.cancel();
    try {
      if (callback != null) {
        callback.onSuccess(data);
      }
    } finally {
      unload();
    }
  }

  /**
   * Registers the callback methods that will be called when the JSONP response
   * comes back. 2 callbacks are created, one to return the value, and one to
   * notify a failure.
   *
   * @param callbacks the global JS object which stores callbacks
   */
  @SuppressWarnings("unchecked")
  private void registerCallbacks(
      JsonpCallbacks callbacks, boolean canHaveMultipleRequestsForId) {
    
    JsonpCallback jsonpCallback = new JsonpCallback();
    jsonpCallback.onSuccess = data -> {
      if (data instanceof JsBoolean) {
        JsBoolean bool = (JsBoolean) data;
        onSuccess((T) Boolean.valueOf(bool.valueOf()));
      } else if (data instanceof JsNumber) {
        JsNumber number = (JsNumber) data;
        if (expectInteger) {
          onSuccess((T) Integer.valueOf((int) number.valueOf()));
        } else {
          onSuccess((T) Double.valueOf(number.valueOf()));
        }
      } else {
        onSuccess((T) data);
      }
    };
    
    if (failureCallbackParam != null) {
      jsonpCallback.onFailure = message -> {
        onFailure((String) message);
      };
    }
    
    if (canHaveMultipleRequestsForId) {
      // In this case, we keep a wrapper, with a list of callbacks.  Since the
      // response for the request is the same each time, we call all of the
      // callbacks as soon as any response comes back.
      JsonpCallback maybeWrapper = CALLBACKS.get(callbackId);
      if (maybeWrapper == null) {
        maybeWrapper = new JsonpCallback();
        maybeWrapper.callbackList = new JsArray<>();
        
        final JsonpCallback wrapper = maybeWrapper; // final for lambdas
        
        wrapper.onSuccess = data -> {
          while (wrapper.callbackList.length > 0) {
            wrapper.callbackList.shift().onSuccess.accept(data);
          }
        };
        
        wrapper.onFailure = message -> {
          while (wrapper.callbackList.length > 0) {
            wrapper.callbackList.shift().onFailure.accept(message);
          }
        };
        
        CALLBACKS.set(callbackId, wrapper);
      }
      
      maybeWrapper.callbackList.push(jsonpCallback);
    } else {
      // In this simple case, just associate the callback directly with the
      // particular id in the callbacks object
      CALLBACKS.set(callbackId, jsonpCallback);
    }
  }

  /**
   * Cleans everything once the response has been received: deletes the script
   * tag and unregisters the callback.
   */
  private void unload() {
    /*
     * Some browsers (IE7) require the script tag to be deleted outside the
     * scope of the script itself. Therefore, we need to defer the delete
     * statement after the callback execution.
     */
    new Timer() { //TODO: maybe switch back to Scheduler when it's ported
      public void run() {
        if (!canHaveMultipleRequestsForSameId) {
          // If there can me multiple requests for a particular ID, then we
          // don't want to unregister the callback since there may be pending
          // requests that have not yet come back and we don't want them to
          // have an undefined callback function.
          unregisterCallbacks(CALLBACKS, callbackId);
        }
        Element script = document.getElementById(callbackId);
        if (script != null) {
          // The script may have already been deleted
          getHeadElement().removeChild(script);
        }
      }
    }.schedule(1);
  }

  private void unregisterCallbacks(JsonpCallbacks callbacks, String callbackId) {
    JsonpCallback cb = callbacks.get(callbackId);

    cb.onSuccess = cb.onFailure = in -> { };
  }
}
