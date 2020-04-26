package org.jresearch.threetenbp.gwt.client.format.wrap;

import java.time.chrono.ChronoLocalDate;
import java.time.format.CharLiteralPrinterParsers;
import java.time.format.ReducedPrinterParsers;
import java.time.temporal.TemporalField;

public class ReducedPrinterParserTestWrapper implements PrinterParserTestWrapper {

    private final Object parser;

	public ReducedPrinterParserTestWrapper(TemporalField field, int width, int maxWidth, int baseValue, ChronoLocalDate baseDate) {
		parser = ReducedPrinterParsers.create( field,  width,  maxWidth,  baseValue,  baseDate);
	}

	@Override
	public boolean print(DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return CharLiteralPrinterParsers.print(parser, context, buf);
	}

	@Override
	public int parse(DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return CharLiteralPrinterParsers.parse(parser, context, text, position);
	}

	@Override
	public String toString() {
		return parser.toString();
	}

	@Override
	public Object getParser() {
		return parser;
	}

}
