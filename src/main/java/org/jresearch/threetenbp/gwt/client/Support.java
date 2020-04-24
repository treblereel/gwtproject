package org.jresearch.threetenbp.gwt.client;

import static jsinterop.annotations.JsPackage.GLOBAL;

import java.nio.ByteBuffer;
import java.time.zone.TzdbZoneRulesProvider;
import java.time.zone.ZoneRulesProvider;

import javax.annotation.Nonnull;

import org.gwtproject.typedarrays.shared.Uint8Array;
import org.gwtproject.xhr.client.ReadyStateChangeHandler;
import org.gwtproject.xhr.client.XMLHttpRequest;
import org.gwtproject.xhr.client.XMLHttpRequest.ResponseType;
import org.jresearch.threetenbp.gwt.client.loader.TimeJsBundle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;

import elemental2.core.ArrayBuffer;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;

@JsType(isNative = true, namespace = GLOBAL, name = "support")
public class Support {

	@JsOverlay
	private static boolean initialized = false;

	@JsOverlay
	public static void init() {
		if (!isInitialized()) {
			final TimeJsBundle bundle = GWT.create(TimeJsBundle.class);
			ScriptInjector.fromString(bundle.support().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
			ScriptInjector.fromString(bundle.base64binary().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
			XMLHttpRequest request = XMLHttpRequest.create();
	        request.open("GET", bundle.tzdb().getSafeUri().asString());
	        request.setResponseType(ResponseType.ArrayBuffer);
	        request.setOnReadyStateChange(new ReadyStateChangeHandler() {
	            @Override
	            public void onReadyStateChange(XMLHttpRequest xhr) {
	                if (xhr.getReadyState() == XMLHttpRequest.DONE) {
	                    if (xhr.getStatus() == 200) {
	                        ArrayBuffer buffer = Js.cast(xhr.getResponseArrayBuffer());
	                        ByteBuffer data = ByteBuffer.wrapArrayBuffer(buffer);
	                        TzdbZoneRulesProvider provider = new TzdbZoneRulesProvider(data);
	                        ZoneRulesProvider.registerProvider(provider);
	                    } else {
	                        System.out.println("response status: " + xhr.getStatus() + " " + xhr.getStatusText());
	                    }
	                }
	            }
	        });
	        request.send();
			initialized = true;
		}
	}

	@JsOverlay
	public static boolean isInitialized() {
		return initialized;
	}

	@Nonnull
	@JsMethod(namespace = JsPackage.GLOBAL)
	public static native float getTimestamp();

	@Nonnull
	@JsMethod(namespace = JsPackage.GLOBAL)
	public static native String getTimezone();

	@Nonnull
	@JsMethod(namespace = JsPackage.GLOBAL)
	public static native void sleep(int milliseconds);

	@Nonnull
	@JsMethod(namespace = "Base64Binary")
	public static native ArrayBuffer decodeArrayBuffer(String base64);

	@Nonnull
	@JsMethod(namespace = "Base64Binary")
	public static native Uint8Array decode(String base64);

}
