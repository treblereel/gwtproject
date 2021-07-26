package org.jresearch.threetenbp.gwt.time.client.format.wrap;

public interface PrinterParserTestWrapper {

  public Object getParser();

  public boolean print(DateTimePrintContextTestWrapper context, StringBuilder buf);

  public int parse(DateTimeParseContextTestWrapper context, CharSequence text, int position);
}
