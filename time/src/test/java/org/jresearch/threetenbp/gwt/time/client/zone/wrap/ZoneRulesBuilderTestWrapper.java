package org.jresearch.threetenbp.gwt.time.client.zone.wrap;

import org.jresearch.threetenbp.gwt.emu.java.time.DayOfWeek;
import org.jresearch.threetenbp.gwt.emu.java.time.LocalDateTime;
import org.jresearch.threetenbp.gwt.emu.java.time.LocalTime;
import org.jresearch.threetenbp.gwt.emu.java.time.Month;
import org.jresearch.threetenbp.gwt.emu.java.time.ZoneOffset;
import org.jresearch.threetenbp.gwt.emu.java.time.zone.ZoneOffsetTransitionRule.TimeDefinition;
import org.jresearch.threetenbp.gwt.emu.java.time.zone.ZoneRules;
import org.jresearch.threetenbp.gwt.emu.java.time.zone.ZoneRulesBuilders;

public class ZoneRulesBuilderTestWrapper {

	private final Object builder;

	public ZoneRulesBuilderTestWrapper() {
		builder = ZoneRulesBuilders.create();
	}

	public ZoneRules toRules(String string) {
		return ZoneRulesBuilders.toRules(builder, string);
	}

	public ZoneRulesBuilderTestWrapper addWindowForever(ZoneOffset standardOffset) {
		ZoneRulesBuilders.addWindowForever(builder, standardOffset);
		return this;
	}

	public ZoneRulesBuilderTestWrapper addWindow(ZoneOffset standardOffset, LocalDateTime until,
			TimeDefinition untilDefinition) {
		ZoneRulesBuilders.addWindow(builder, standardOffset, until, untilDefinition);
		return this;
	}

	public ZoneRulesBuilderTestWrapper addRuleToWindow(LocalDateTime transitionDateTime, TimeDefinition timeDefinition,
			int savingAmountSecs) {
		ZoneRulesBuilders.addRuleToWindow(builder, transitionDateTime, timeDefinition, savingAmountSecs);
		return this;
	}

	public ZoneRulesBuilderTestWrapper addRuleToWindow(int year, Month month, int dayOfMonthIndicator, LocalTime time,
			boolean timeEndOfDay, TimeDefinition timeDefinition, int savingAmountSecs) {
		ZoneRulesBuilders.addRuleToWindow(builder, year, month, dayOfMonthIndicator, time, timeEndOfDay, timeDefinition,
				savingAmountSecs);
		return this;
	}

	public ZoneRulesBuilderTestWrapper addRuleToWindow(int startYear, int endYear, Month month, int dayOfMonthIndicator,
			DayOfWeek dayOfWeek, LocalTime time, boolean timeEndOfDay, TimeDefinition timeDefinition,
			int savingAmountSecs) {
		ZoneRulesBuilders.addRuleToWindow(builder, startYear, endYear, month, dayOfMonthIndicator, dayOfWeek, time,
				timeEndOfDay, timeDefinition, savingAmountSecs);
		return this;
	}

	public ZoneRulesBuilderTestWrapper setFixedSavingsToWindow(int fixedSavingAmountSecs) {
		ZoneRulesBuilders.setFixedSavingsToWindow(builder, fixedSavingAmountSecs);
		return this;
	}

}
