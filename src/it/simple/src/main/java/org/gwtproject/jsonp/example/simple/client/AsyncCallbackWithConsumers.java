package org.gwtproject.jsonp.example.simple.client;

import java.util.function.Consumer;

import org.gwtproject.user.client.rpc.AsyncCallback;

import com.google.gwt.core.client.GWT;

public class AsyncCallbackWithConsumers<T> implements AsyncCallback<T> {
  private Consumer<T> onSuccess;
  private Consumer<Throwable> onFailure;

  public AsyncCallbackWithConsumers(Consumer<T> onSuccess, Consumer<Throwable> onFailure) {
    this.onSuccess = onSuccess == null ? t -> { } : onSuccess;
    this.onFailure = onFailure == null ? GWT::reportUncaughtException : onFailure;
  }

  @Override
  public void onFailure(Throwable caught) {
    onFailure.accept(caught);
  }

  @Override
  public void onSuccess(T result) {
    onSuccess.accept(result);
  }
}
