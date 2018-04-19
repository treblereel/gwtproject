package org.gwtproject.jsonp.example.simple.client;

import java.util.function.Consumer;

import org.gwtproject.callback.shared.Callback;

import com.google.gwt.core.client.GWT;

public class CallbackBuilder<T, F> {

  private Consumer<T> onSuccess;
  private Consumer<F> onFailure;
  
  public static <T, F> CallbackBuilder<T, F> async(Class<T> resultType, Class<F> errorType) {
    return new CallbackBuilder<T, F>();
  }
  
  //-----------------------------------------------------------------------------------------------
  // async(T.class, F.class).couldSucceed(this::processResult).orFail(e -> { 
  //   /* multi line error handler */ 
  // })
  
  public CallbackBuilder<T, F> couldSucceed(Consumer<T> consumer) {
    onSuccess = consumer;
    return this;
  }
  
  public Callback<T, F> orFail(Consumer<F> consumer) {
    return couldFail(consumer).build();
  }
  
  //-----------------------------------------------------------------------------------------------
  // async(T.class, F.class).couldFail(this::processError).orSucceed(r -> { 
  //   /* multi line result handler */ 
  // })
  
  public CallbackBuilder<T, F> couldFail(Consumer<F> consumer) {
    onFailure = consumer;
    return this;
  }
  
  public Callback<T, F> orSucceed(Consumer<T> consumer) {
    return couldSucceed(consumer).build();
  }
  
  //-----------------------------------------------------------------------------------------------
  // async(T.class, F.class).shouldSucceed(this::processResult) // error delegated to uncaught ex. handler
  
  public Callback<T, F> shouldSucceed(Consumer<T> consumer) {
    return couldSucceed(consumer).orFail(reportingUncaughtException());
  }
  
  //-----------------------------------------------------------------------------------------------
  // Private methods
  
  private Callback<T, F> build() {
    return new CallbackWithConsumers<T, F>(onSuccess, onFailure);
  }
  
  private Consumer<F> reportingUncaughtException() {
    try {
      throw new Exception(); //for stacktrace where callback was created
    } catch (Exception ex) {
      return error -> {
        Exception e = new Exception("Uncaught error: " + error + "\n", ex);
        GWT.reportUncaughtException(e);
      };
    }
  }
}
