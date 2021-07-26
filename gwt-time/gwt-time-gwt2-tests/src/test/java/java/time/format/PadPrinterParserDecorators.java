package java.time.format;

import org.jresearch.threetenbp.gwt.time.client.format.wrap.DateTimeParseContextTestWrapper;
import org.jresearch.threetenbp.gwt.time.client.format.wrap.DateTimePrintContextTestWrapper;

// JDK version, see for real code in /src/test/super
public class PadPrinterParserDecorators {

  public static Object create(Object printerParser, int padWidth, char padChar) {
    return null;
  }

  public static boolean print(
      Object parser, DateTimePrintContextTestWrapper context, StringBuilder buf) {
    return false;
  }

  public static int parse(
      Object parser, DateTimeParseContextTestWrapper context, CharSequence text, int position) {
    return 0;
  }
}
