package java.time.chrono;

import java.time.LocalDate;

public class JapaneseEras {

	private JapaneseEras() {
		// prevent instantiation
	}

	public static LocalDate endDate(JapaneseEra era) {
		return era.endDate();
	}

}
