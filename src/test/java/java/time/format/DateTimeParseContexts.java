package java.time.format;

import java.time.chrono.Chronology;
import java.time.temporal.TemporalField;
import java.util.Locale;

import org.jresearch.threetenbp.gwt.client.format.wrap.ParsedTestWrapper;

//Do nothing in JDK version
public class DateTimeParseContexts {

    public static Object create(Locale locale, DecimalStyle symbols, Chronology chronology) {
		return null;
	}

	public static ParsedTestWrapper toParsed(Object context) {
		return null;
	}

	public static void setCaseSensitive(Object context, boolean caseSensitive) {
    }

	public static Long getParsed(Object context, TemporalField field) {
		return null;
	}

}
