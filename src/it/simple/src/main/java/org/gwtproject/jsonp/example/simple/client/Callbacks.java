package org.gwtproject.jsonp.example.simple.client;

public class Callbacks {
  
  public static <T> AsyncCallbackBuilder<T> async(Class<T> resultType) {
    return AsyncCallbackBuilder.async(resultType);
  }
}
