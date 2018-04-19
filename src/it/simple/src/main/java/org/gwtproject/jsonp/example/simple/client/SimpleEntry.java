package org.gwtproject.jsonp.example.simple.client;

import static elemental2.dom.DomGlobal.window;
import static org.gwtproject.jsonp.example.simple.client.Callbacks.async;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import org.gwtproject.jsonp.client.JsonpRequest;
import org.gwtproject.jsonp.client.JsonpRequestBuilder;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;

public class SimpleEntry implements EntryPoint {

  private static final Logger LOG = Logger.getLogger("SimpleEntry");

  @Override
  public void onModuleLoad() {
    IntStream.range(0, 3).forEachOrdered(this::sendObjectRequest);

    if ("on".equals(System.getProperty("superdevmode"))) { // echo servlet is available

      // real values
      jsonp().requestBoolean(echo("true"),
          async(Boolean.class).couldSucceed(window.console::log).orFail(this::logError));

      jsonp().requestBoolean(echo("false"),
          async(Boolean.class).couldSucceed(window.console::log).orFail(this::logError));

      jsonp().requestString(echo("'abc'"),
          async(String.class).couldSucceed(window.console::log).orFail(this::logError));

      jsonp().requestInteger(echo("4200"),
          async(Integer.class).couldSucceed(window.console::log).orFail(this::logError));

      jsonp().requestDouble(echo("4.200"),
          async(Double.class).couldSucceed(window.console::log).orFail(this::logError));

      jsonp().requestObject(echo("{key:'value'}"),
          async(JavaScriptObject.class).couldSucceed(window.console::log).orFail(this::logError));

      // nulls
      jsonp().requestBoolean(echo("null"),
          async(Boolean.class).couldSucceed(r -> window.console.log("bool: " + r)).orFail(this::logError));

      jsonp().requestString(echo("null"),
          async(String.class).couldSucceed(r -> window.console.log("string: " + r)).orFail(this::logError));

      jsonp().requestInteger(echo("null"),
          async(Integer.class).couldSucceed(r -> window.console.log("int: " + r)).orFail(this::logError));

      jsonp().requestDouble(echo("null"),
          async(Double.class).couldSucceed(r -> window.console.log("double: " + r)).orFail(this::logError));

      jsonp().requestObject(echo("null"),
          async(JavaScriptObject.class).couldSucceed(r -> window.console.log("object: " + r)).orFail(this::logError));

      jsonp().send(echo("null"),
          async(Void.class).couldSucceed(r -> window.console.log("void: " + r)).orFail(this::logError));

      // timeout
      jsonp().send(echoTimeout(),
          async(Void.class).couldSucceed(r -> LOG.info("should timeout")).orFail(this::logError));

      // error
      JsonpRequestBuilder failure = jsonp();
      failure.setFailureCallbackParam("failureCallback");
      failure.send(echoFailure("FAILURE_CALLBACK_WORKS"),
          async(Void.class).couldSucceed(r -> LOG.info("should fail")).orFail(this::logError));

      // delay
      jsonp().requestString(echoDelayed("'delayed_works'"),
          async(String.class).couldSucceed(window.console::log).orFail(this::logError));

      // predetermined
      // requires defining of following additional callback before request completes:
      // window.__gwt_jsonp__.P12345.callbackList[1] = {onSuccess:
      // function(data){console.log("pred id again: ", data);}}
      String id = "12345";
      JsonpRequestBuilder pred = jsonp();
      pred.setPredeterminedId(id);
      pred.requestString(echoDelayed("'pred123'", 5000),
          async(String.class).couldSucceed(window.console::log).orFail(this::logError));

      // cancel
      JsonpRequest<String> toCancel = jsonp().requestString(echoDelayed("'cancel_does_not_work'"),
          async(String.class).couldSucceed(window.console::log).orFail(this::logError));
      
      toCancel.cancel();
      LOG.info("canceled messages should not show up");
    }
  }

  private JsonpRequestBuilder jsonp() {
    return new JsonpRequestBuilder();
  }

  private void logError(Throwable caught) {
    LOG.log(Level.SEVERE, "Error while making jsonp request: ", caught);
  }

  private void sendObjectRequest(int i) {
    String url = "http://validate.jsontest.com/?json={\"key\":" + i + "}";

    jsonp().requestObject(url, async(JavaScriptObject.class).couldSucceed(r -> {
      window.console.log("i=" + i, r);
    }).orFail(this::logError));
  }

  private static String echo(String value) {
    return GWT.getModuleBaseURL() + "echo?action=SUCCESS&value=" + value;
  }

  private static String echoDelayed(String value) {
    return echoDelayed(value, 500);
  }

  private static String echoDelayed(String value, long delayMs) {
    return GWT.getModuleBaseURL() + "echo?action=SUCCESS&delay=" + delayMs + "&value=" + value;
  }

  private static String echoFailure(String error) {
    return GWT.getModuleBaseURL() + "echo?action=FAILURE&error=" + error;
  }

  private static String echoTimeout() {
    return GWT.getModuleBaseURL() + "echo?action=TIMEOUT";
  }

}
