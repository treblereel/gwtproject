package org.gwtproject.jsonp.example.simple.client;

import java.util.function.Consumer;

import org.gwtproject.callback.shared.AsyncCallback;

public class AsyncCallbackBuilder<T> {

  private Consumer<T> onSuccess;
  private Consumer<Throwable> onFailure;
  
  public static <T> AsyncCallbackBuilder<T> async(Class<T> resultType) {
    return new AsyncCallbackBuilder<T>();
  }
  
  //-----------------------------------------------------------------------------------------------
  // async(T.class).couldSucceed(this::processResult).orFail(e -> { 
  //   /* multi line error handler */ 
  // })
  
  public AsyncCallbackBuilder<T> couldSucceed(Consumer<T> consumer) {
    onSuccess = consumer;
    return this;
  }
  
  public AsyncCallback<T> orFail(Consumer<Throwable> consumer) {
    return couldFail(consumer).build();
  }
  
  //-----------------------------------------------------------------------------------------------
  // async(T.class).couldFail(this::processError).orSucceed(r -> { 
  //   /* multi line result handler */ 
  // })
  
  public AsyncCallbackBuilder<T> couldFail(Consumer<Throwable> consumer) {
    onFailure = consumer;
    return this;
  }
  
  public AsyncCallback<T> orSucceed(Consumer<T> consumer) {
    return couldSucceed(consumer).build();
  }
  
  //-----------------------------------------------------------------------------------------------
  // async(T.class).shouldSucceed(this::processResult) // error delegated to uncaught ex. handler
  
  public AsyncCallback<T> shouldSucceed(Consumer<T> consumer) {
    return couldSucceed(consumer).build();
  }
  
  //-----------------------------------------------------------------------------------------------
  // Private methods
  
  private AsyncCallback<T> build() {
    return new AsyncCallbackWithConsumers<T>(onSuccess, onFailure);
  }
}
