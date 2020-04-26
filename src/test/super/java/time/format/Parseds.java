package java.time.format;

import java.time.format.DateTimeParseContext.Parsed;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQuery;
import java.time.temporal.ValueRange;

public class Parseds {

	public static ValueRange range(Object parsed, TemporalField field) {
		return ((Parsed)parsed).range(field);
	}

	public static boolean isSupported(Object parsed, TemporalField field) {
		return ((Parsed)parsed).isSupported(field);
	}

	public static int get(Object parsed, TemporalField field) {
		return ((Parsed)parsed).get(field);
	}

	public static long getLong(Object parsed, TemporalField field) {
		return ((Parsed)parsed).getLong(field);
	}

	public static <R> R query(Object parsed, TemporalQuery<R> query) {
		return ((Parsed)parsed).query(query);
	}

}
