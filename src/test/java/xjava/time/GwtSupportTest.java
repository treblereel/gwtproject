package xjava.time;

import org.gwtproject.typedarrays.shared.Uint8Array;
import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;


public class GwtSupportTest extends GWTTestCase {

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
	public void testGetTimestamp() {
		final float timestamp = Support.getTimestamp();
		assertTrue(timestamp > 0);
	}

	@Test
	public void testGetTimezove() {
		final String timezone = Support.getTimezone();
		assertNotNull(timezone);
	}

	@Test
	public void testDecodeArrayBuffer() {
		Uint8Array data = Support.decode("VGhlIHF1");
		assertNotNull(data);
		assertEquals(6, data.length());
		assertEquals(0x54, data.get(0));
		assertEquals(0x68, data.get(1));
		assertEquals(0x65, data.get(2));
		assertEquals(0x20, data.get(3));
		assertEquals(0x71, data.get(4));
		assertEquals(0x75, data.get(5));
	}

}
