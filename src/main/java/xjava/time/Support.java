package xjava.time;

import static jsinterop.annotations.JsPackage.GLOBAL;

import javax.annotation.Nonnull;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;

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
		if (!initialized) {
			final TimeJsBundle bundle = GWT.create(TimeJsBundle.class);
			ScriptInjector.fromString(bundle.support().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
			initialized = true;
		}
	}

	@Nonnull
	@JsMethod(namespace = JsPackage.GLOBAL)
	public static native float getTimestamp();

	@Nonnull
	@JsMethod(namespace = JsPackage.GLOBAL)
	public static native String getTimezone();

}
