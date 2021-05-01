/*
 * Copyright © 2019 The GWT Project Authors
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
package org.gwtproject.text.client;

import java.util.Date;
import org.gwtproject.i18n.shared.DateTimeFormat;
import org.gwtproject.i18n.shared.TimeZone;
import org.gwtproject.text.shared.AbstractRenderer;

public class DateTimeFormatRenderer extends AbstractRenderer<Date> {
  private final DateTimeFormat format;
  private final TimeZone timeZone;

  public DateTimeFormatRenderer() {
    this(DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT));
  }

  public DateTimeFormatRenderer(DateTimeFormat format) {
    this(format, (TimeZone) null);
  }

  public DateTimeFormatRenderer(DateTimeFormat format, TimeZone timeZone) {
    assert format != null;

    this.format = format;
    this.timeZone = timeZone;
  }

  public String render(Date object) {
    return object == null ? "" : this.format.format(object, this.timeZone);
  }
}
