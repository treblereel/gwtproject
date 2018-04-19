package org.gwtproject.jsonp.example.simple.client;

public class Callbacks {
  
  public static <T, F> CallbackBuilder<T, F> async(Class<T> resultType, Class<F> errorType) {
    return CallbackBuilder.async(resultType, errorType);
  }
  
  public static <T> AsyncCallbackBuilder<T> async(Class<T> resultType) {
    return AsyncCallbackBuilder.async(resultType);
  }
}
