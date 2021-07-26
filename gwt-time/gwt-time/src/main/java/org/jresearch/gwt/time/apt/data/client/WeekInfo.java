package org.jresearch.gwt.time.apt.data.client;

import static org.jresearch.gwt.time.apt.data.client.Region.*;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class WeekInfo {
  public static final Map<Integer, EnumSet<Region>> MIN_DAYS = new HashMap<>();

  public static final Map<Integer, EnumSet<Region>> FIRST_DAY = new HashMap<>();

  public static final Integer DEFAULT_FIRST_DAY = Integer.valueOf(1);

  public static final Integer DEFAULT_MIN_DAYS = Integer.valueOf(1);

  static {
    FIRST_DAY.put(Integer.valueOf(5), EnumSet.of(MV));
    FIRST_DAY.put(
        Integer.valueOf(6), EnumSet.of(AE, AF, BH, DJ, DZ, EG, IQ, IR, JO, KW, LY, OM, QA, SD, SY));
    FIRST_DAY.put(
        Integer.valueOf(7),
        EnumSet.of(
            AG, AS, AU, BD, BR, BS, BT, BW, BZ, CA, CN, CO, DM, DO, ET, GT, GU, HK, HN, ID, IL, IN,
            JM, JP, KE, KH, KR, LA, MH, MM, MO, MT, MX, MZ, NI, NP, PA, PE, PH, PK, PR, PT, PY, SA,
            SG, SV, TH, TT, TW, UM, US, VE, VI, WS, YE, ZA, ZW));
    MIN_DAYS.put(
        Integer.valueOf(4),
        EnumSet.of(
            AD, AN, AT, AX, BE, BG, CH, CZ, DE, DK, EE, ES, FI, FJ, FO, FR, GB, GF, GG, GI, GP, GR,
            HU, IE, IM, IS, IT, JE, LI, LT, LU, MC, MQ, NL, NO, PL, PT, RE, RU, SE, SJ, SK, SM,
            VA));
  }
}
