package org.jresearch.threetenbp.gwt.emu.java.time;

import org.jresearch.threetenbp.gwt.emu.java.time.chrono.TestChronoLocalDate;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.TestChronoLocalDateTime;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.TestChronoZonedDateTime;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.TestHijrahChronology;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.TestIsoChronology;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.TestJapaneseChronology;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.TestMinguoChronology;
import org.jresearch.threetenbp.gwt.emu.java.time.chrono.TestThaiBuddhistChronology;
import org.jresearch.threetenbp.gwt.emu.java.time.format.TestDateTimeFormatter;
import org.jresearch.threetenbp.gwt.emu.java.time.format.TestDateTimeFormatterBuilder;
import org.jresearch.threetenbp.gwt.emu.java.time.format.TestDateTimeTextPrinting;
import org.jresearch.threetenbp.gwt.emu.java.time.format.TestDecimalStyle;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TestChronoField;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TestChronoUnit;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TestIsoFields;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TestJulianFields;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TestMonthDay;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TestTemporalAdjusters;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TestValueRange;
import org.jresearch.threetenbp.gwt.emu.java.time.temporal.TestYearMonth;
import org.jresearch.threetenbp.gwt.emu.java.time.zone.TestFixedZoneRules;
import org.jresearch.threetenbp.gwt.emu.java.time.zone.TestZoneOffsetTransition;
import org.jresearch.threetenbp.gwt.emu.java.time.zone.TestZoneOffsetTransitionRule;
import org.jresearch.threetenbp.gwt.emu.java.time.zone.TestZoneRulesBuilder;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;

public class GwtTreetenBpSuite {

	public static Test suite() {
		GWTTestSuite suite = new GWTTestSuite("Tests");

		// $JUnit-BEGIN$
		suite.addTestSuite(GwtTest.class);
		suite.addTestSuite(GwtSupportTest.class);
		suite.addTestSuite(TestDayOfWeek.class);
		suite.addTestSuite(TestDateTimes_implementation.class);
		suite.addTestSuite(TestDateTimeUtils.class);
		suite.addTestSuite(TestDuration.class);
		suite.addTestSuite(TestInstant.class);
		suite.addTestSuite(TestMonth.class);
		suite.addTestSuite(TestOffsetDateTime_instants.class);
		suite.addTestSuite(TestPeriod.class);
		suite.addTestSuite(TestZoneOffset.class);
		suite.addTestSuite(TestChronoLocalDate.class);
		suite.addTestSuite(TestChronoLocalDateTime.class);
		suite.addTestSuite(TestChronoZonedDateTime.class);
		suite.addTestSuite(TestHijrahChronology.class);
		suite.addTestSuite(TestIsoChronology.class);
		suite.addTestSuite(TestJapaneseChronology.class);
		suite.addTestSuite(TestMinguoChronology.class);
		suite.addTestSuite(TestThaiBuddhistChronology.class);
		suite.addTestSuite(TestChronoField.class);
		suite.addTestSuite(TestChronoUnit.class);
		suite.addTestSuite(TestIsoFields.class);
		suite.addTestSuite(TestJulianFields.class);
		suite.addTestSuite(TestMonthDay.class);
		suite.addTestSuite(TestTemporalAdjusters.class);
		suite.addTestSuite(TestValueRange.class);
		suite.addTestSuite(TestYearMonth.class);
		suite.addTestSuite(TestFixedZoneRules.class);
		suite.addTestSuite(TestZoneOffsetTransition.class);
		suite.addTestSuite(TestZoneOffsetTransitionRule.class);
		suite.addTestSuite(TestZoneRulesBuilder.class);
		suite.addTestSuite(TestDateTimeFormatter.class);
		suite.addTestSuite(TestDateTimeFormatterBuilder.class);
		suite.addTestSuite(TestDateTimeTextPrinting.class);
		suite.addTestSuite(TestDecimalStyle.class);
		// $JUnit-END$

		return suite;
	}
}
