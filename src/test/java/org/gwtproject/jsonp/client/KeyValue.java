package org.gwtproject.jsonp.client;

import com.google.gwt.core.client.JavaScriptObject;

public class KeyValue extends JavaScriptObject {
  
  protected KeyValue() { }
  
  public final native String getKey() /*-{
    return this.key;
  }-*/;
  
  public final native int getValue() /*-{
    return this.value;
  }-*/;

  public static final native KeyValue newKeyValue(String key, int value) /*-{
    return {
      key: key,
      value: value
    };
  }-*/;

}
