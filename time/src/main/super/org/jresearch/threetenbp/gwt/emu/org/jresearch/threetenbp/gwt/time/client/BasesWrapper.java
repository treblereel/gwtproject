package org.jresearch.threetenbp.gwt.emu.org.jresearch.threetenbp.gwt.time.client;

import java.time.chrono.Chronology;
import java.util.Optional;

import org.jresearch.gwt.time.apt.base.Bases;
import org.jresearch.gwt.time.apt.base.Chrono;
import org.jresearch.gwt.tool.emu.apt.annotation.Wrap;

@Wrap("org.jresearch.threetenbp.gwt.emu")
public class BasesWrapper {

	public static Optional<Chrono> ofJavaTime(org.jresearch.threetenbp.gwt.emu.java.time.chrono.Chronology chronology) {
		return Bases.ofJavaTime(cast(chronology));
	}

	private static Chronology cast(Object chronology) {
		return (Chronology) chronology;
	}

}
