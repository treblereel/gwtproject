package org.gwtproject.jsonp.example.simple.client;

import java.util.function.Consumer;

import org.gwtproject.callback.shared.Callback;

public class CallbackWithConsumers<T, F> implements Callback<T, F> {
  private Consumer<T> onSuccess;
  private Consumer<F> onFailure;

  public CallbackWithConsumers(Consumer<T> onSuccess, Consumer<F> onFailure) {
    assert onFailure != null : "failure must be handled!";
    
    this.onSuccess = onSuccess == null ? t -> { } : onSuccess;
    this.onFailure = onFailure;
  }

  @Override
  public void onFailure(F error) {
    onFailure.accept(error);
  }

  @Override
  public void onSuccess(T result) {
    onSuccess.accept(result);
  }
}
