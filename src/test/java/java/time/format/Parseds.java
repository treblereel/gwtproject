package java.time.format;

import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQuery;
import java.time.temporal.ValueRange;

public class Parseds {

	public static ValueRange range(Object parsed, TemporalField field) {
		return null;
	}

	public static boolean isSupported(Object parsed, TemporalField field) {
		return false;
	}

	public static int get(Object parsed, TemporalField field) {
		return 0;
	}

	public static long getLong(Object parsed, TemporalField field) {
		return 0;
	}

	public static <R> R query(Object parsed, TemporalQuery<R> query) {
		return null;
	}

}
