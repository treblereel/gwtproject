/*
 * Copyright © 2018 The GWT Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gwtproject.i18n.datetimeformat;

import static org.gwtproject.i18n.shared.CustomDateTimeFormat.Pattern;

import org.gwtproject.i18n.shared.DateTimeFormat;
import org.gwtproject.i18n.shared.SupportedLocales;
import org.gwtproject.i18n.shared.annotations.IsCustomDateTimeFormat;

/** Class for getting customized date/time formats. */
@IsCustomDateTimeFormat
@SupportedLocales({"en", "de"})
public interface MyFormats {

  /** Returns a pattern for abbreviated year, month, and date. */
  @Pattern("yMMMd")
  DateTimeFormat yearMonthDayAbbrev();

  /** Returns a pattern for full year, month, and date. */
  @Pattern("yyyyMMMMd")
  DateTimeFormat yearMonthDayFull();

  /** Returns a pattern for full year, month, and date. */
  @Pattern("MMMM d, yyyy")
  DateTimeFormat yearMonthDayFull2();
}
