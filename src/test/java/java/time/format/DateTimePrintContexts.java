package java.time.format;

import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class DateTimePrintContexts {

	public static Object create(TemporalAccessor temporal, Locale locale, DecimalStyle symbols) {
		return new DateTimePrintContext(temporal, locale, symbols);
	}

}
