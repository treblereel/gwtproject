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
package org.gwtproject.jsonp.client;

import com.google.j2cl.junit.apt.J2clTestInput;
import elemental2.promise.Promise;
import org.gwtproject.timer.client.Timer;
import org.gwtproject.user.client.rpc.AsyncCallback;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

// import static org.gwtproject.jsonp.client.KeyValue.newKeyValue;



/**
 * Tests for {@link JsonpRequest}.
 */
@J2clTestInput(JsonpRequestTest.class)
public class JsonpRequestTest {
  
  /**
   * The maximum amount of time to wait for a response in milliseconds.
   */
  private static final int RESPONSE_DELAY = 2500;
  /**
   * The ID of the current test.
   */
  protected static     int currentTestId;
  
  private JsonpRequestBuilder jsonp;
  private AsyncCallback<?>    asyncCallback;
  private Timer               timer;
  
  @Before
  public void setUp() {
    jsonp = new JsonpRequestBuilder();
    jsonp.setTimeout(RESPONSE_DELAY + 500);
  }
  
  @After
  public void gwtTearDown() {
    ++currentTestId;
  }
  
  @Test(timeout = 5000)
  public Promise<Boolean> testBooleanFalse() {
    return new Promise<>(
        (resolve, reject) -> {
          runAsyncTask(
              () ->
                  jsonp.requestBoolean(
                      echo("false"),
                      new AssertSuccessCallback<Boolean>(Boolean.FALSE,
                                                         resolve,
                                                         reject)));
        });
  }
  
  private void runAsyncTask(Task task) {
    timer =
        new Timer() {
          
          @Override
          public void run() {
            task.run();
          }
        };
    timer.schedule(500);
  }
  
  //  @Test(timeout = 5000)
  //  public Promise<Void> testVoid() {
  //    return new Promise<>((resolve, reject) -> {
  //      timer =
  //          new Timer() {
  //
  //            @Override
  //            public void run() {
  //              asyncCallback = new AsyncCallback<Void>() {
  //
  //                @Override
  //                public void onFailure(Throwable throwable) {
  //                  fail();
  //                  timer.cancel();
  //                  resolve.onInvoke((Void) null);
  //                }
  //
  //                @Override
  //                public void onSuccess(Void o) {
  //
  //                }
  //              }
  //            }
  //          }
  //
  //
  //      timer.scheduleRepeating(500);
  //
  //
  //      runAsyncTask( jsonp.send(echo(null),
  //                               new AssertSuccessCallback<Void>(null)););
  //    });
  //
  //    delayTestFinish(RESPONSE_DELAY);
  //    jsonp.send(echo(null), );
  //  }
  
  //  /** Checks that an error is received. */
  //  private class AssertFailureCallback<T> implements AsyncCallback<T> {
  //    private String expectedMessage;
  //    private int id;
  //
  //    public AssertFailureCallback(String expectedMessage) {
  //      id = currentTestId;
  //      this.expectedMessage = expectedMessage;
  //    }
  //
  //    @Override
  //    public void onFailure(Throwable throwable) {
  //      if (id == currentTestId) {
  //        assertEquals(expectedMessage, throwable.getMessage());
  //        finishTest();
  //      }
  //    }
  //
  //    @Override
  //    public void onSuccess(T value) {
  //      if (id == currentTestId) {
  //        fail();
  //      }
  //    }
  //  }
  //
  //  private class AssertCancelCallback<T> implements AsyncCallback<T> {
  //    private int id;
  //
  //    public AssertCancelCallback() {
  //      id = currentTestId;
  //    }
  //
  //    @Override
  //    public void onFailure(Throwable throwable) {
  //      if (id == currentTestId) {
  //        fail();
  //      }
  //    }
  //
  //    @Override
  //    public void onSuccess(T value) {
  //      if (id == currentTestId) {
  //        fail();
  //      }
  //    }
  //  }
  //
  //  private class AssertObjectSuccessCallback extends AssertSuccessCallback<KeyValue> {
  //    private AssertObjectSuccessCallback(KeyValue expectedValue) {
  //      super(expectedValue, null);
  //    }
  //
  //    @Override
  //    protected void assertEqualObjects(KeyValue expected, KeyValue actual) {
  //      assertEquals(expected.getKey(), actual.getKey());
  //      assertEquals(expected.getValue(), actual.getValue());
  //    }
  //  }
  
  private String echo(String value) {
    return getPathToServer() + "echoServlet?action=SUCCESS&value=" + value;
  }
  
  private String getPathToServer() {
    return "http://localhost:9999/";
  }
  
  //  @Test
  //  public void testTimeout() {
  //    delayTestFinish(jsonp.getTimeout() + 1000);
  //    jsonp.requestString(echoTimeout(), new AssertTimeoutExceptionCallback<String>());
  //  }
  @Test(timeout = 11000)
  public Promise<Void> testTimeout() {
    return new Promise<>(((resolve, reject) -> {
      Timer timer = new Timer() {
        
        @Override
        public void run() {
          jsonp.requestString(echoTimeout(),
                              new AssertTimeoutExceptionCallback<>(this));
        }
      };
      timer.schedule(jsonp.getTimeout());
    }));
  }
  
  //  private String echoDelayed(String value) {
  //    return echoDelayed(value, 500);
  //  }
  //
  //  private String echoDelayed(String value, long delayMs) {
  //    return getPathToServer() + "echoServlet?action=SUCCESS&delay=" + delayMs + "&value=" +
  // value;
  //  }
  //
  //  private String echoFailure(String error) {
  //    return getPathToServer() + "echoServlet?action=FAILURE&error=" + error;
  //  }
  
  private String echoTimeout() {
    return getPathToServer() + "echoServlet?action=TIMEOUT";
  }
  
  //  @Override
  //  public String getModuleName() {
  //    return "org.gwtproject.jsonp.JsonpTest";
  //  }
  //
  //  public void testBooleanTrue() {
  //    delayTestFinish(RESPONSE_DELAY);
  //    jsonp.requestBoolean(echo("true"), new AssertSuccessCallback<Boolean>(Boolean.TRUE));
  //  }
  //
  //  /**
  //   * Fails in devmode with HtmlUnit, JS "null" exception.
  //   *
  //   * <p>Call occurs through postponedActions in HtmlUnit that execute synchronously. Should be
  //   * async. Need to file HtmlUnitBug.
  //   */
  //  @DoNotRunWith(Platform.HtmlUnitBug)
  //  public void testCancel() {
  //    delayTestFinish(2000);
  //    // setup a server request that will delay for 500ms
  //    JsonpRequest<String> req =
  //        jsonp.requestString(echoDelayed("'A'", 500), new AssertCancelCallback<String>());
  //    // cancel it before it comes back
  //    req.cancel();
  //    // wait 1s to make sure we don't get a callback
  //    new Timer() {
  //      @Override
  //      public void run() {
  //        finishTest();
  //      }
  //    }.schedule(1500);
  //  }
  //
  //  @Test
  //  public void testDelay() {
  //    delayTestFinish(RESPONSE_DELAY);
  //    JsonpRequest<String> req =
  //        jsonp.requestString(echoDelayed("'A'"), new AssertSuccessCallback<String>("A"));
  //  }
  //
  //  @Test
  //  public void testDouble() {
  //    delayTestFinish(RESPONSE_DELAY);
  //    jsonp.requestDouble(echo("123.456"), new AssertSuccessCallback<Double>(123.456));
  //  }
  //
  //  @Test
  //  public void testFailureCallback() {
  //    delayTestFinish(RESPONSE_DELAY);
  //    jsonp.setFailureCallbackParam("failureCallback");
  //    jsonp.requestString(echoFailure("ERROR"), new AssertFailureCallback<String>("ERROR"));
  //  }
  //
  //  @Test
  //  public void testIds() {
  //    delayTestFinish(RESPONSE_DELAY);
  //    JsonpRequest<String> reqA =
  //        jsonp.requestString(echo("'A'"), new AssertSuccessCallback<String>("A"));
  //    JsonpRequest<String> reqB =
  //        jsonp.requestString(echo("'B'"), new AssertSuccessCallback<String>("B"));
  //    // WARNING: knows the current structure of IDs
  //    int idA = Integer.parseInt(reqA.getCallbackId().substring(1));
  //    int idB = Integer.parseInt(reqB.getCallbackId().substring(1));
  //    assertEquals("Unexpected ID sequence", idA + 1, idB);
  //  }
  //
  //  @Test
  //  public void testInteger() {
  //    delayTestFinish(RESPONSE_DELAY);
  //    jsonp.requestInteger(echo("123"), new AssertSuccessCallback<Integer>(123));
  //  }
  //
  //  /**
  //   * Tests that if no failure callback is defined, the servlet receives well only a 'callback'
  //   * parameter, and sends back the error to it.
  //   */
  //  @Test
  //  public void testNoFailureCallback() {
  //    delayTestFinish(RESPONSE_DELAY);
  //    jsonp.setFailureCallbackParam(null);
  //    jsonp.requestString(echoFailure("ERROR"), new AssertSuccessCallback<String>("ERROR"));
  //  }
  //
  //  @Test
  //  public void testNullBoolean() {
  //    delayTestFinish(RESPONSE_DELAY);
  //    jsonp.requestBoolean(echo("null"), new AssertSuccessCallback<Boolean>(null));
  //  }
  //
  //  @Test
  //  public void testNullDouble() {
  //    delayTestFinish(RESPONSE_DELAY);
  //    jsonp.requestDouble(echo("null"), new AssertSuccessCallback<Double>(null));
  //  }
  //
  //  @Test
  //  public void testNullInteger() {
  //    delayTestFinish(RESPONSE_DELAY);
  //    jsonp.requestInteger(echo("null"), new AssertSuccessCallback<Integer>(null));
  //  }
  //
  //  @Test
  //  public void testNullString() {
  //    delayTestFinish(RESPONSE_DELAY);
  //    jsonp.requestString(echo("null"), new AssertSuccessCallback<String>(null));
  //  }
  //
  //  @Test
  //  public void testOverlapped() {
  //    delayTestFinish(RESPONSE_DELAY);
  //    Counter counter = new Counter(3);
  //    JsonpRequest<String> reqA =
  //        jsonp.requestString(
  //            echoDelayed("'A'", 750), new AssertSuccessCallback<String>("A", counter));
  //    JsonpRequest<String> reqB =
  //        jsonp.requestString(
  //            echoDelayed("'B'", 500), new AssertSuccessCallback<String>("B", counter));
  //    JsonpRequest<String> reqC =
  //        jsonp.requestString(echo("'C'"), new AssertSuccessCallback<String>("C", counter));
  //  }
  //
  //  @Test
  //  public void testPredeterminedIds() {
  //    delayTestFinish(RESPONSE_DELAY);
  //    String PREDETERMINED = "pred";
  //    jsonp.setPredeterminedId(PREDETERMINED);
  //    JsonpRequest<String> reqA =
  //        jsonp.requestString(echo("'A'"), new AssertSuccessCallback<String>("A"));
  //    String idA = reqA.getCallbackId().substring(1);
  //    assertEquals("Unexpected ID sequence", PREDETERMINED, idA);
  //    jsonp.setPredeterminedId(null);
  //  }
  //
  //  @Test
  //  public void testString() {
  //    delayTestFinish(RESPONSE_DELAY);
  //    jsonp.requestString(echo("'Hello'"), new AssertSuccessCallback<String>("Hello"));
  //  }
  //
  //  @Test
  //  public void testObject() {
  //    delayTestFinish(RESPONSE_DELAY);
  //
  //    // given
  //    String key = "abc";
  //    int value = 42;
  //
  //    // when
  //    //    jsonp.requestObject(
  //    //        echo("{key: '" + key + "',value:" + value + "}"),
  //    //        // then
  //    ////        new AssertObjectSuccessCallback(Js.uncheckedCast(newKeyValue(key, value))));
  //    //        new AssertObjectSuccessCallback(newKeyValue(key, value)));
  //    jsonp.requestObject(
  //        echo("{key: '" + key + "',value:" + value + "}"),
  //        // then
  //        //        new AssertObjectSuccessCallback(Js.uncheckedCast(newKeyValue(key, value))));
  //        new AssertObjectSuccessCallback(new KeyValue(key, value)));
  //  }
  
  @FunctionalInterface
  private interface Task {
    
    void run();
    
  }
  


  /**
   * Checks that a timeout happens.
   */
  private class AssertTimeoutExceptionCallback<T>
      implements AsyncCallback<T> {
    
    private Timer timer;
    
    public AssertTimeoutExceptionCallback(Timer timer) {
      this.timer = timer;
    }
    
    @Override
    public void onFailure(Throwable throwable) {
      assertTrue(throwable instanceof TimeoutException);
      timer.cancel();
    }
    
    @Override
    public void onSuccess(T value) {
      fail();
    }
    
  }
  
  
  
  /**
   * A class that counts results and calls finishTest when the right number have been received.
   */
  private class Counter {
    
    private int count;
    
    public Counter(int count) {
      this.count = count;
    }
    
    public void count() {
      if (--count < 0) {
        fail("Too many results");
      }
      if (count == 0) {
        timer.cancel();
      }
    }
    
  }
  
  
  
  /**
   * Checks that the received value is as expected.
   */
  private class AssertSuccessCallback<T>
      implements AsyncCallback<T> {
    
    private final T                                                      expectedValue;
    private final int                                                    id;
    private final Counter                                                counter;
    private       Promise.PromiseExecutorCallbackFn.ResolveCallbackFn<T> resolve;
    private       Promise.PromiseExecutorCallbackFn.RejectCallbackFn     reject;
    
    protected AssertSuccessCallback(
        T expectedValue,
        Promise.PromiseExecutorCallbackFn.ResolveCallbackFn<T> resolve,
        Promise.PromiseExecutorCallbackFn.RejectCallbackFn reject) {
      this(expectedValue,
           null,
           resolve,
           reject);
    }
    
    private AssertSuccessCallback(
        T expectedValue,
        Counter counter,
        Promise.PromiseExecutorCallbackFn.ResolveCallbackFn<T> resolve,
        Promise.PromiseExecutorCallbackFn.RejectCallbackFn reject) {
      this.id            = currentTestId;
      this.counter       = counter;
      this.expectedValue = expectedValue;
      this.reject        = reject;
      this.resolve       = resolve;
    }
    
    @Override
    public void onFailure(Throwable throwable) {
      if (id == currentTestId) {
        fail();
      }
      resolve.onInvoke((T) null);
    }
    
    @Override
    public void onSuccess(T value) {
      if (id == currentTestId) {
        assertEqualObjects(expectedValue,
                           value);
        if (counter != null) {
          counter.count();
          ;
        } else {
          resolve.onInvoke((T) null);
        }
      }
    }
    
    protected void assertEqualObjects(T expected,
                                      T actual) {
      assertEquals(expectedValue,
                   actual);
    }
    
  }
  
}
