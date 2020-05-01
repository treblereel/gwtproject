package org.jresearch.threetenbp.gwt.client;

import static jsinterop.annotations.JsPackage.GLOBAL;

import java.nio.ByteBuffer;
import java.time.zone.Providers;
import java.time.zone.ZoneRulesProvider;

import javax.annotation.Nonnull;

import org.gwtproject.nio.TypedArrayHelper;
import org.gwtproject.typedarrays.shared.Uint8Array;
import org.jresearch.threetenbp.gwt.client.loader.TimeJsBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;

import elemental2.core.ArrayBuffer;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = GLOBAL, name = "support")
public class Support {

	@JsOverlay
	private static final Logger LOGGER = LoggerFactory.getLogger(Support.class);
	@JsOverlay
	private static final TimeJsBundle bundle = GWT.create(TimeJsBundle.class);

	@JsOverlay
	private static boolean commonInitialized = false;
	@JsOverlay
	private static boolean tzTnitialized = false;

	static {
		init();
	}

	@JsOverlay
	public static void init() {
		if (!isCommonInitialized()) {
			LOGGER.trace("common initialization");
			ScriptInjector.fromString(bundle.support().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
			ScriptInjector.fromString(bundle.base64binary().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
			commonInitialized = true;
		}
	}

	@JsOverlay
	public static void initTzData() {
		if (!isTzInitialized()) {
			LOGGER.trace("tz initialization");
			String tzData = bundle.tzdbEncoded().getText();
			ArrayBuffer buffer = Support.decodeArrayBuffer(tzData);
			ByteBuffer data = TypedArrayHelper.wrap(buffer);
			ZoneRulesProvider provider = Providers.of(data);
			ZoneRulesProvider.registerProvider(provider);
			tzTnitialized = true;
		}
	}

	@JsOverlay
	public static boolean isCommonInitialized() {
		return commonInitialized;
	}

	@JsOverlay
	public static boolean isTzInitialized() {
		return tzTnitialized;
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
	@JsMethod(namespace = JsPackage.GLOBAL)
	public static native String displayTimeZone(boolean daylight, String timeZone, String style, String locale);

	@Nonnull
	@JsMethod(namespace = "Base64Binary")
	public static native ArrayBuffer decodeArrayBuffer(String base64);

	@Nonnull
	@JsMethod(namespace = "Base64Binary")
	public static native Uint8Array decode(String base64);

}
