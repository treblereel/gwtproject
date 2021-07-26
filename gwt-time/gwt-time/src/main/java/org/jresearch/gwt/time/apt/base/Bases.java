package org.jresearch.gwt.time.apt.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class Bases {

  private static final Map<String, Chrono> CLDR_INDEX = new HashMap<>(Chrono.values().length);
  private static final Map<String, Chrono> JAVA_TIME_INDEX = new HashMap<>(Chrono.values().length);

  static {
    Stream.of(Chrono.values()).forEach(c -> CLDR_INDEX.put(c.getCldrCelendar().toUpperCase(), c));
    Stream.of(Chrono.values()).forEach(c -> JAVA_TIME_INDEX.put(c.getJavaTime().toUpperCase(), c));
  }

  public static Optional<Chrono> ofCldr(String cldrCalendar) {
    return Optional.of(cldrCalendar).map(String::toUpperCase).map(CLDR_INDEX::get);
  }

  public static Optional<Chrono> ofJavaTime(String chronologyId) {
    return Optional.of(chronologyId).map(String::toUpperCase).map(JAVA_TIME_INDEX::get);
  }
}
