package org.jresearch.threetenbp.gwt.emu.java.time.zone;

import org.jresearch.threetenbp.gwt.emu.java.time.LocalDateTime;
import org.jresearch.threetenbp.gwt.emu.java.time.ZoneOffset;

public class ZoneOffsetTransitions {

	private ZoneOffsetTransitions() {
		// prevent instantiation
	}

	public static ZoneOffsetTransition create(LocalDateTime transition, ZoneOffset offsetBefore, ZoneOffset offsetAfter) {
		return new ZoneOffsetTransition(transition, offsetBefore, offsetAfter);
	}

}
