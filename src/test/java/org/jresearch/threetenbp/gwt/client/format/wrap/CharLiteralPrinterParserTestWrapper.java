package org.jresearch.threetenbp.gwt.client.format.wrap;

import java.time.format.CharLiteralPrinterParsers;

public class CharLiteralPrinterParserTestWrapper {

    private final Object parser;

	public CharLiteralPrinterParserTestWrapper(char literal) {
		parser = CharLiteralPrinterParsers.create(literal);
	}

	public boolean print(DateTimePrintContextTestWrapper context, StringBuilder buf) {
		return CharLiteralPrinterParsers.print(parser, context, buf);
	}

	public int parse(DateTimeParseContextTestWrapper context, CharSequence text, int position) {
		return CharLiteralPrinterParsers.parse(parser, context, text, position);
	}
	
	@Override
	public String toString() {
		return parser.toString();
	}

}
