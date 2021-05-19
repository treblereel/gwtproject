package org.jresearch.threetenbp.gwt.emu.java.time.chrono;

import org.jresearch.threetenbp.gwt.emu.java.time.LocalDate;

public class JapaneseEras {

	private JapaneseEras() {
		// prevent instantiation
	}

	public static LocalDate endDate(JapaneseEra era) {
		return era.endDate();
	}

	public static JapaneseEra registerEra(LocalDate since, String name) {
		return JapaneseEra.registerEra(since, name);
	}
}
