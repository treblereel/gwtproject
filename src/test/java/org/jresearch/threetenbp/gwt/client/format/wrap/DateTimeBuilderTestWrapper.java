package org.jresearch.threetenbp.gwt.client.format.wrap;

import java.time.format.DateTimeBuilders;
import java.time.format.OffsetIdPrinterParsers;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;

public class DateTimeBuilderTestWrapper implements TemporalAccessor {

    private final TemporalAccessor builder;

	public DateTimeBuilderTestWrapper(TemporalField field, long value) {
		builder = DateTimeBuilders.create(field, value);
	}

	@Override
	public boolean isSupported(TemporalField field) {
		return builder.isSupported(field);
	}

	@Override
	public long getLong(TemporalField field) {
		return builder.getLong(field);
	}

}
