package org.jresearch.gwt.time.apt.base;

public enum Chrono {
  ISO("gregorian", "ISO"),
  THAI_BUDDHIST("buddhist", "ThaiBuddhist"),
  HIJRAH_UMALQURA("islamic-umalqura", "Hijrah-umalqura"),
  JAPANESE("japanese", "Japanese"),
  MINGUO("roc", "Minguo"),
  ;

  private final String cldrCelendar;
  private final String javaTime;

  Chrono(String cldrCelendar, String javaTime) {
    this.cldrCelendar = cldrCelendar;
    this.javaTime = javaTime;
  }

  public String getCldrCelendar() {
    return cldrCelendar;
  }

  public String getJavaTime() {
    return javaTime;
  }
}
