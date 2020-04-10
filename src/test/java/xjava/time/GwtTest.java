package xjava.time;

import java.nio.ByteBuffer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.junit.client.GWTTestCase;

import elemental2.core.ArrayBuffer;
import xjava.time.zone.TzdbZoneRulesProvider;


public class GwtTest extends GWTTestCase {

    private static Logger LOGGER = LoggerFactory.getLogger("com.google.gwt.junit.client.GWTTestCase");

	/**
	 * Must refer to a valid module that sources this class.
	 */
	@Override
	public String getModuleName() {
		return "xjava.module";
	}

	@Override
	protected void gwtSetUp() throws Exception {
		Support.init();
		super.gwtSetUp();
	}

	@Test
	public void testTzdbProvider() {
		ArrayBuffer buffer = Support.decodeArrayBuffer(TzData.TZ_DATA);
        ByteBuffer data = ByteBuffer.wrapArrayBuffer(buffer);
		TzdbZoneRulesProvider provider = new TzdbZoneRulesProvider(data);

		assertNotNull(provider);
	}

	@Test
	public void testLogging() {
		String param = "test";
		LOGGER.trace("trace message: {}", param);
		LOGGER.debug("debug message: {}", param);
		LOGGER.error("error message: {}", param);
		LOGGER.info("info message: {}", param);
		LOGGER.warn("warn message: {}", param);
	}
}
