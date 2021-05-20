package org.jresearch.threetenbp.gwt.time.client;

import org.jresearch.threetenbp.gwt.emu.org.jresearch.threetenbp.gwt.time.client.Support;

import com.google.gwt.core.client.EntryPoint;

public class TimeEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {
		Support.init();
	}

}
