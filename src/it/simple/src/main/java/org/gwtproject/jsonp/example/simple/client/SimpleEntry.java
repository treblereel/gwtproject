package org.gwtproject.jsonp.example.simple.client;

import static elemental2.dom.DomGlobal.window;
import static org.gwtproject.jsonp.example.simple.client.Callbacks.async;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import org.gwtproject.callback.shared.Callback;
import org.gwtproject.jsonp.client.JsonpRequest;
import org.gwtproject.jsonp.client.JsonpRequestBuilder;
import org.gwtproject.timer.client.Timer;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;

public class SimpleEntry implements EntryPoint {

  private static final Logger LOG = Logger.getLogger("SimpleEntry");
  private static final String URL = "http://validate.jsontest.com/";

  @Override
  public void onModuleLoad() {
    IntStream.range(0, 10).forEachOrdered(this::sendRequest);
    testCallbackBuilder(); // should be visible in stacktrace
  }

  private void testCallbackBuilder() {
    Callback<String, Integer> callback = async(String.class, Integer.class).shouldSucceed(LOG::info);
    
    new Timer() {
      @Override
      public void run() {
        callback.onFailure(404); // sample error
      }
    }.schedule(1000);
  }

  private void logError(Throwable caught) {
    LOG.log(Level.SEVERE, "Error while making jsonp request: ", caught);
  }

  private void sendRequest(int i) {
    String jsonParam = "?json={\"key\":" + i + "}";

    JsonpRequestBuilder b = new JsonpRequestBuilder();
    b.setTimeout(1000);
    JsonpRequest<JavaScriptObject> jsonp = b.requestObject(URL + jsonParam,
        async(JavaScriptObject.class).couldSucceed(r -> {
          window.console.log("i=" + i, r);
        }).orFail(this::logError));

    // Possible to cancel if needed
    // jsonp.cancel();
  }

}
