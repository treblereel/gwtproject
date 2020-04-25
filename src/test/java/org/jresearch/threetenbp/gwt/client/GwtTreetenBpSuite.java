package org.jresearch.threetenbp.gwt.client;

import org.jresearch.threetenbp.gwt.client.chrono.TestChronoLocalDate;
import org.jresearch.threetenbp.gwt.client.chrono.TestChronoLocalDateTime;
import org.jresearch.threetenbp.gwt.client.chrono.TestChronoZonedDateTime;
import org.jresearch.threetenbp.gwt.client.chrono.TestHijrahChronology;
import org.jresearch.threetenbp.gwt.client.chrono.TestIsoChronology;
import org.jresearch.threetenbp.gwt.client.chrono.TestJapaneseChronology;
import org.jresearch.threetenbp.gwt.client.chrono.TestMinguoChronology;
import org.jresearch.threetenbp.gwt.client.chrono.TestThaiBuddhistChronology;
import org.jresearch.threetenbp.gwt.client.temporal.TestChronoField;
import org.jresearch.threetenbp.gwt.client.temporal.TestChronoUnit;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;

public class GwtTreetenBpSuite {

	public static Test suite() {
		GWTTestSuite suite = new GWTTestSuite("Tests");

		// $JUnit-BEGIN$
		suite.addTestSuite(GwtTest.class);
		suite.addTestSuite(GwtSupportTest.class);
		suite.addTestSuite(TestClock.class);
		suite.addTestSuite(TestClock_Fixed.class);
		suite.addTestSuite(TestClock_Offset.class);
		suite.addTestSuite(TestClock_System.class);
		suite.addTestSuite(TestClock_Tick.class);
		suite.addTestSuite(TestDayOfWeek.class);
		suite.addTestSuite(TestLocalDate.class);
		suite.addTestSuite(TestLocalTime.class);
		suite.addTestSuite(TestDateTimes_implementation.class);
		suite.addTestSuite(TestDateTimeUtils.class);
		suite.addTestSuite(TestDuration.class);
		suite.addTestSuite(TestInstant.class);
		suite.addTestSuite(TestMonth.class);
		suite.addTestSuite(TestLocalDateTime.class);
		suite.addTestSuite(TestOffsetDateTime_instants.class);
		suite.addTestSuite(TestOffsetDateTime.class);
		suite.addTestSuite(TestOffsetTime.class);
		suite.addTestSuite(TestPeriod.class);
		suite.addTestSuite(TestZonedDateTime.class);
		suite.addTestSuite(TestZoneId.class);
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
//		suite.addTestSuite(Test.class);
//		suite.addTestSuite(Test.class);
//		suite.addTestSuite(Test.class);
//		suite.addTestSuite(Test.class);
//		suite.addTestSuite(Test.class);
//		suite.addTestSuite(Test.class);
//		suite.addTestSuite(Test.class);
//		suite.addTestSuite(Test.class);
//		suite.addTestSuite(Test.class);
		// $JUnit-END$

		return suite;
	}
}
