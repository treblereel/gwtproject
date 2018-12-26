package org.gwtproject.i18n.datetimeformat;

import org.gwtproject.i18n.shared.DateTimeFormat;
import org.gwtproject.i18n.shared.SupportedLocales;
import org.gwtproject.i18n.shared.annotations.IsCustomDateTimeFormat;

import static org.gwtproject.i18n.shared.CustomDateTimeFormat.Pattern;

/**
 * Class for getting customized date/time formats.
 */
@IsCustomDateTimeFormat
@SupportedLocales({"en", "de"})
public interface MyFormats {

  /**
   * Returns a pattern for abbreviated year, month, and date.
   */
  @Pattern("yMMMd")
  DateTimeFormat yearMonthDayAbbrev();

  /**
   * Returns a pattern for full year, month, and date.
   */
  @Pattern("yyyyMMMMd")
  DateTimeFormat yearMonthDayFull();

  /**
   * Returns a pattern for full year, month, and date.
   */
  @Pattern("MMMM d, yyyy")
  DateTimeFormat yearMonthDayFull2();
}
