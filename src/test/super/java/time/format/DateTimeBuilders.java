package java.time.format;

import java.time.format.DateTimeFormatterBuilder.FractionPrinterParser;
import java.time.format.DateTimeFormatterBuilder.OffsetIdPrinterParser;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;

import org.jresearch.threetenbp.gwt.client.format.wrap.DateTimeParseContextTestWrapper;
import org.jresearch.threetenbp.gwt.client.format.wrap.DateTimePrintContextTestWrapper;

public class DateTimeBuilders {

	public static TemporalAccessor create(TemporalField field, long value) {
		return new DateTimeBuilder(field, value);
	}

}
