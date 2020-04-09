package xjava.time;

import java.nio.ByteBuffer;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

import elemental2.core.ArrayBuffer;
import xjava.time.zone.TzdbZoneRulesProvider;


public class GwtTest extends GWTTestCase {

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
	public void test() {
		ArrayBuffer buffer = Support.decodeArrayBuffer(TzData.TZ_DATA);
        ByteBuffer data = ByteBuffer.wrapArrayBuffer(buffer);
		TzdbZoneRulesProvider provider = new TzdbZoneRulesProvider(data);

		assertNotNull(provider);
	}

}
