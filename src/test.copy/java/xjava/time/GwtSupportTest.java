package xjava.time;

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
}
