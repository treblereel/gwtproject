package xjava.time;

import static jsinterop.annotations.JsPackage.GLOBAL;

import javax.annotation.Nonnull;

import org.gwtproject.typedarrays.shared.Uint8Array;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;

import elemental2.core.ArrayBuffer;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import xjava.time.loader.TimeJsBundle;

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
//			System.out.println(bundle.77tzdb().getSafeUri().asString());
//			XMLHttpRequest request = XMLHttpRequest.create();
//	        request.open("GET", bundle.tzdb().getSafeUri().asString());
//	        request.setResponseType(ResponseType.ArrayBuffer);
//	        request.setOnReadyStateChange(new ReadyStateChangeHandler() {
//
//	            @Override
//	            public void onReadyStateChange(XMLHttpRequest xhr) {
//	                if (xhr.getReadyState() == XMLHttpRequest.DONE) {
//	                    if (xhr.getStatus() == 200) {
//
//	                        ArrayBuffer buffer = xhr.getResponseArrayBuffer();
//	                        ByteBuffer data = ByteBuffer.wrapArrayBuffer(Js.cast(buffer));
//	                        TzdbZoneRulesProvider provider = new TzdbZoneRulesProvider(data);
//	                        ZoneRulesProvider.registerProvider(provider);
////							Int8Array array = TypedArrays.createInt8Array(buffer);
////	                        System.out.println("got " + array.length() + " bytes: ");
////	                        for (int i = 0; i < array.length(); i++) {
////	                            System.out.println(array.get(i));
////	                        }
//	            			initialized = true;
//	                    } else {
//	                        System.out.println("response status: " + xhr.getStatus() + " " + xhr.getStatusText());
//	                    }
//	                }
//	            }
//	        });
//	        request.send();

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
	@JsMethod(namespace = "Base64Binary")
	public static native ArrayBuffer decodeArrayBuffer(String base64);

	@Nonnull
	@JsMethod(namespace = "Base64Binary")
	public static native Uint8Array decode(String base64);

}
