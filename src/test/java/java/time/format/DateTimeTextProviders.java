package java.time.format;

import java.time.format.DateTimeFormatterBuilder.CharLiteralPrinterParser;
import java.time.format.DateTimeFormatterBuilder.DateTimePrinterParser;

import org.jresearch.threetenbp.gwt.client.format.wrap.DateTimeParseContextTestWrapper;
import org.jresearch.threetenbp.gwt.client.format.wrap.DateTimePrintContextTestWrapper;

public class DateTimeTextProviders {

	public static DateTimeTextProvider create() {
		return DateTimeTextProvider.getInstance();
	}

}
