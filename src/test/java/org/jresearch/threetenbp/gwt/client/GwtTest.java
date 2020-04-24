package org.jresearch.threetenbp.gwt.client;

import java.nio.ByteBuffer;
import java.time.ZoneId;
import java.time.zone.Providers;
import java.time.zone.ZoneRulesException;
import java.time.zone.ZoneRulesProvider;

import org.gwtproject.nio.TypedArrayHelper;
import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Timer;

import elemental2.core.ArrayBuffer;

public class GwtTest extends GWTTestCase {

//    private static Logger LOGGER = LoggerFactory.getLogger("com.google.gwt.junit.client.GWTTestCase");

	/**
	 * Must refer to a valid module that sources this class.
	 */
	@Override
	public String getModuleName() {
		return "org.jresearch.threetenbp.gwt.module";
	}

	@Override
	protected void gwtSetUp() throws Exception {
		Support.init();
		super.gwtSetUp();
	}

	@Test
	public void testTzdbProviderInit() {
		try {
			ZoneId.of("Europe/Paris");
		} catch (ZoneRulesException e) {
			// expected
		}
		Timer timer = new Timer() {
			public void run() {
				try {
					ZoneId.of("Europe/Paris");
				} catch (ZoneRulesException e) {
					schedule(250);
				}
				finishTest();
			}
		};
		delayTestFinish(30 * 1000);
		timer.schedule(250);
	}

	@Test
	public void testTzdbProvider() {
		ArrayBuffer buffer = Support.decodeArrayBuffer(TzData.TZ_DATA);
		ByteBuffer data = TypedArrayHelper.wrap(buffer);
		ZoneRulesProvider provider = Providers.of(data);

		assertNotNull(provider);
	}

//	@Test
//	public void testLogging() {
//		String param = "test";
//		LOGGER.trace("trace message: {}", param);
//		LOGGER.debug("debug message: {}", param);
//		LOGGER.error("error message: {}", param);
//		LOGGER.info("info message: {}", param);
//		LOGGER.warn("warn message: {}", param);
//	}
}
