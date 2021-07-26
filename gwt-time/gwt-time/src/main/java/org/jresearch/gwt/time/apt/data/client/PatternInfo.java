package org.jresearch.gwt.time.apt.data.client;

import static org.jresearch.gwt.time.apt.base.Chrono.*;
import static org.jresearch.gwt.time.apt.data.client.ImmutablePatternCoordinates.*;

import java.util.HashMap;
import java.util.Map;
import org.jresearch.threetenbp.gwt.time.client.locale.LocaleWrapper;

public class PatternInfo {
  public static final Map<String, PatternCoordinates[]> DATE_FULL_PATTERNS = new HashMap<>();

  public static final Map<String, PatternCoordinates[]> DATE_LONG_PATTERNS = new HashMap<>();

  public static final Map<String, PatternCoordinates[]> DATE_MEDIUM_PATTERNS = new HashMap<>();

  public static final Map<String, PatternCoordinates[]> DATE_SHORT_PATTERNS = new HashMap<>();

  public static final Map<String, PatternCoordinates[]> DATE_TIME_FULL_PATTERNS = new HashMap<>();

  public static final Map<String, PatternCoordinates[]> DATE_TIME_LONG_PATTERNS = new HashMap<>();

  public static final Map<String, PatternCoordinates[]> DATE_TIME_MEDIUM_PATTERNS = new HashMap<>();

  public static final Map<String, PatternCoordinates[]> DATE_TIME_SHORT_PATTERNS = new HashMap<>();

  public static final Map<String, PatternCoordinates[]> TIME_FULL_PATTERNS = new HashMap<>();

  public static final Map<String, PatternCoordinates[]> TIME_LONG_PATTERNS = new HashMap<>();

  public static final Map<String, PatternCoordinates[]> TIME_MEDIUM_PATTERNS = new HashMap<>();

  public static final Map<String, PatternCoordinates[]> TIME_SHORT_PATTERNS = new HashMap<>();

  static {
    DATE_FULL_PATTERNS.put(
        "EEEE d MMMM y G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("br")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("br")),
          of(MINGUO, LocaleWrapper.forLanguageTag("br")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fr")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("fr")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fy")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("fy")),
          of(MINGUO, LocaleWrapper.forLanguageTag("fy")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ga")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("it")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("nl")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("nl")),
          of(MINGUO, LocaleWrapper.forLanguageTag("nl")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("sv")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d 'ta'’ MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("mt")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE d MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("af-NA")),
          of(ISO, LocaleWrapper.forLanguageTag("agq")),
          of(ISO, LocaleWrapper.forLanguageTag("bas")),
          of(ISO, LocaleWrapper.forLanguageTag("bm")),
          of(ISO, LocaleWrapper.forLanguageTag("br")),
          of(ISO, LocaleWrapper.forLanguageTag("dje")),
          of(ISO, LocaleWrapper.forLanguageTag("dua")),
          of(ISO, LocaleWrapper.forLanguageTag("dyo")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IE")),
          of(ISO, LocaleWrapper.forLanguageTag("ewo")),
          of(ISO, LocaleWrapper.forLanguageTag("fa")),
          of(ISO, LocaleWrapper.forLanguageTag("ff")),
          of(ISO, LocaleWrapper.forLanguageTag("fr")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-CA")),
          of(ISO, LocaleWrapper.forLanguageTag("fy")),
          of(ISO, LocaleWrapper.forLanguageTag("ga")),
          of(ISO, LocaleWrapper.forLanguageTag("it")),
          of(ISO, LocaleWrapper.forLanguageTag("kab")),
          of(ISO, LocaleWrapper.forLanguageTag("khq")),
          of(ISO, LocaleWrapper.forLanguageTag("km")),
          of(ISO, LocaleWrapper.forLanguageTag("kok")),
          of(ISO, LocaleWrapper.forLanguageTag("ksf")),
          of(ISO, LocaleWrapper.forLanguageTag("kw")),
          of(ISO, LocaleWrapper.forLanguageTag("ln")),
          of(ISO, LocaleWrapper.forLanguageTag("lu")),
          of(ISO, LocaleWrapper.forLanguageTag("mfe")),
          of(ISO, LocaleWrapper.forLanguageTag("mg")),
          of(ISO, LocaleWrapper.forLanguageTag("mua")),
          of(ISO, LocaleWrapper.forLanguageTag("nl")),
          of(ISO, LocaleWrapper.forLanguageTag("nmg")),
          of(ISO, LocaleWrapper.forLanguageTag("nus")),
          of(ISO, LocaleWrapper.forLanguageTag("rn")),
          of(ISO, LocaleWrapper.forLanguageTag("se-FI")),
          of(ISO, LocaleWrapper.forLanguageTag("ses")),
          of(ISO, LocaleWrapper.forLanguageTag("sg")),
          of(ISO, LocaleWrapper.forLanguageTag("shi")),
          of(ISO, LocaleWrapper.forLanguageTag("shi-Latn")),
          of(ISO, LocaleWrapper.forLanguageTag("sv")),
          of(ISO, LocaleWrapper.forLanguageTag("to")),
          of(ISO, LocaleWrapper.forLanguageTag("twq")),
          of(ISO, LocaleWrapper.forLanguageTag("yav")),
          of(ISO, LocaleWrapper.forLanguageTag("zgh")),
        });
    DATE_FULL_PATTERNS.put(
        "y MMMMའི་ཚེས་d, EEEE",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("bo")),
        });
    DATE_FULL_PATTERNS.put(
        "dd MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ms-BN")),
        });
    DATE_FULL_PATTERNS.put(
        "Gy年M月d日 EEEE",
        new PatternCoordinates[] {
          of(MINGUO, LocaleWrapper.forLanguageTag("yue")),
          of(MINGUO, LocaleWrapper.forLanguageTag("zh-Hant")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, སྤྱི་ལོ་y MMMM ཚེས་dd",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("dz")),
        });
    DATE_FULL_PATTERNS.put(
        "d MMMM y, EEEE",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("az")),
          of(ISO, LocaleWrapper.forLanguageTag("az-Cyrl")),
        });
    DATE_FULL_PATTERNS.put(
        "d MMMM y EEEE",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("tk")), of(ISO, LocaleWrapper.forLanguageTag("tr")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, dd MMMM y G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ca")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("id")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("id")),
          of(MINGUO, LocaleWrapper.forLanguageTag("id")),
        });
    DATE_FULL_PATTERNS.put(
        "y年M月d日EEEE",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ja")),
          of(ISO, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(ISO, LocaleWrapper.forLanguageTag("zh")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant-HK")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEEที่ d MMMM ปีGที่ y",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("th")),
          of(MINGUO, LocaleWrapper.forLanguageTag("th")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE d MMMM, y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ha")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, dd MMMM, y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ka")),
          of(ISO, LocaleWrapper.forLanguageTag("uz-Cyrl")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d'mh' MMMM y G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("gd")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("gd")),
          of(MINGUO, LocaleWrapper.forLanguageTag("gd")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE d MMMM 'de' y G",
        new PatternCoordinates[] {
          of(MINGUO, LocaleWrapper.forLanguageTag("ca")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE، d MMMM، y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ur")),
        });
    DATE_FULL_PATTERNS.put(
        "y թ. MMMM d, EEEE",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("hy")),
        });
    DATE_FULL_PATTERNS.put(
        "Gy年M月d日EEEE",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("ja")),
          of(MINGUO, LocaleWrapper.forLanguageTag("ja")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("yue")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("yue")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(MINGUO, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("zh")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("zh")),
          of(MINGUO, LocaleWrapper.forLanguageTag("zh")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("zh-Hant")),
        });
    DATE_FULL_PATTERNS.put(
        "y年M月d日 EEEE",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("yue")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant")),
        });
    DATE_FULL_PATTERNS.put(
        "cccc, MMMM d. y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("smn")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE d. MMMM y G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("cs")),
          of(MINGUO, LocaleWrapper.forLanguageTag("cs")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("da")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("gsw")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("no")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("no")),
          of(MINGUO, LocaleWrapper.forLanguageTag("no")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, dd. MMMM y.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("bs-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("sr")),
          of(ISO, LocaleWrapper.forLanguageTag("sr-Latn")),
        });
    DATE_FULL_PATTERNS.put(
        "y 'ж'. d MMMM, EEEE",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("kk")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, MMMM dd 'lia', G y",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("ee")),
          of(MINGUO, LocaleWrapper.forLanguageTag("ee")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d. MMMM y.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("bs")), of(ISO, LocaleWrapper.forLanguageTag("hr")),
        });
    DATE_FULL_PATTERNS.put(
        "y, MMMM d, EEEE",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ml")),
        });
    DATE_FULL_PATTERNS.put(
        "y 'оны' MMMM'ын' d, EEEE 'гараг'",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("mn")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, MMMM dd, y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("so")),
        });
    DATE_FULL_PATTERNS.put(
        "cccc d. MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("fi")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE 'le' d 'de' MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ia")),
        });
    DATE_FULL_PATTERNS.put(
        "G y. MMMM d., EEEE",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("hu")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE dd MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("af")),
          of(ISO, LocaleWrapper.forLanguageTag("gv")),
          of(ISO, LocaleWrapper.forLanguageTag("kkj")),
          of(ISO, LocaleWrapper.forLanguageTag("kl")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d. MMMM y. G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("hr")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE፣ dd MMMM መዓልቲ y G",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ti")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d MMMM y 'р'.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("uk")),
        });
    DATE_FULL_PATTERNS.put(
        "y MMMM d, EEEE",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("am")),
          of(ISO, LocaleWrapper.forLanguageTag("ckb")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm")),
          of(ISO, LocaleWrapper.forLanguageTag("ii")),
          of(ISO, LocaleWrapper.forLanguageTag("lrc")),
          of(ISO, LocaleWrapper.forLanguageTag("ne")),
          of(ISO, LocaleWrapper.forLanguageTag("")),
          of(ISO, LocaleWrapper.forLanguageTag("rw")),
          of(ISO, LocaleWrapper.forLanguageTag("sd")),
          of(ISO, LocaleWrapper.forLanguageTag("se")),
          of(ISO, LocaleWrapper.forLanguageTag("si")),
          of(ISO, LocaleWrapper.forLanguageTag("sn")),
          of(ISO, LocaleWrapper.forLanguageTag("xh")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d-MMMM, y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("uz")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d-'a' 'de' MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("eo")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE د y د MMMM d",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ps")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, MMMM d 'lia' y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ee")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, dd MMMM y 'г'. G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("mk")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, dd MMMM 'de' y G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ast")),
          of(MINGUO, LocaleWrapper.forLanguageTag("ast")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE، d MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ar")),
        });
    DATE_FULL_PATTERNS.put(
        "y('e')'ko' MMMM'ren' d('a'), EEEE",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("eu")),
        });
    DATE_FULL_PATTERNS.put(
        "d, MMMM y, EEEE",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("te")),
        });
    DATE_FULL_PATTERNS.put(
        "G y MMMM d, EEEE",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("")),
          of(HIJRAH_UMALQURA, LocaleWrapper.forLanguageTag("")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("")),
          of(MINGUO, LocaleWrapper.forLanguageTag("")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d 'de' MMMM 'de' y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("es")),
          of(ISO, LocaleWrapper.forLanguageTag("es-419")),
          of(ISO, LocaleWrapper.forLanguageTag("es-MX")),
          of(ISO, LocaleWrapper.forLanguageTag("es-US")),
          of(ISO, LocaleWrapper.forLanguageTag("gl")),
          of(ISO, LocaleWrapper.forLanguageTag("pt")),
          of(ISO, LocaleWrapper.forLanguageTag("pt-PT")),
          of(ISO, LocaleWrapper.forLanguageTag("seh")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, 'dä' d. MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ksh")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, dטן MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("yi")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, y. 'gada' d. MMMM",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("lv")),
        });
    DATE_FULL_PATTERNS.put(
        "y d-MMMM، EEEE",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ug")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d MMMM y 'г'.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("be")),
          of(ISO, LocaleWrapper.forLanguageTag("bg")),
          of(ISO, LocaleWrapper.forLanguageTag("ru")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d MMMM, y 'аз'",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("os")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d MMMM, y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("el")),
        });
    DATE_FULL_PATTERNS.put(
        "y-'ж'., d-MMMM, EEEE",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ky")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE , 'lyɛ'̌ʼ d 'na' MMMM, y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("nnh")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d 'di' MMMM 'di' y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("kea")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d MMMM 'de' y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ast")), of(ISO, LocaleWrapper.forLanguageTag("ca")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, y MMMM dd",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ak")),
          of(ISO, LocaleWrapper.forLanguageTag("jgo")),
          of(ISO, LocaleWrapper.forLanguageTag("mgo")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, 'ngày' dd MMMM 'năm' y G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("vi")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("vi")),
          of(MINGUO, LocaleWrapper.forLanguageTag("vi")),
        });
    DATE_FULL_PATTERNS.put(
        "cccc d. MMMM y G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fi")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("fi")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d MMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("yo")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d. MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("de")),
          of(ISO, LocaleWrapper.forLanguageTag("dsb")),
          of(ISO, LocaleWrapper.forLanguageTag("et")),
          of(ISO, LocaleWrapper.forLanguageTag("fo")),
          of(ISO, LocaleWrapper.forLanguageTag("gsw")),
          of(ISO, LocaleWrapper.forLanguageTag("hsb")),
          of(ISO, LocaleWrapper.forLanguageTag("is")),
          of(ISO, LocaleWrapper.forLanguageTag("lb")),
          of(ISO, LocaleWrapper.forLanguageTag("wae")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d, MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("doi")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE ທີ d MMMM G y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("lo")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, MMMM d، y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("ug")),
        });
    DATE_FULL_PATTERNS.put(
        "y년 M월 d일 EEEE",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ko")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, 'de' d. MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("nds")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d'mh' MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("gd")),
        });
    DATE_FULL_PATTERNS.put(
        "y 'm'. MMMM d 'd'., EEEE",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("lt")),
        });
    DATE_FULL_PATTERNS.put(
        "d MMMM, y 'ел', EEEE",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("tt")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE d. MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("cs")),
          of(ISO, LocaleWrapper.forLanguageTag("nn")),
          of(ISO, LocaleWrapper.forLanguageTag("no")),
          of(ISO, LocaleWrapper.forLanguageTag("sk")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("asa")),
          of(ISO, LocaleWrapper.forLanguageTag("bem")),
          of(ISO, LocaleWrapper.forLanguageTag("bez")),
          of(ISO, LocaleWrapper.forLanguageTag("cgg")),
          of(ISO, LocaleWrapper.forLanguageTag("cy")),
          of(ISO, LocaleWrapper.forLanguageTag("dav")),
          of(ISO, LocaleWrapper.forLanguageTag("ebu")),
          of(ISO, LocaleWrapper.forLanguageTag("el")),
          of(ISO, LocaleWrapper.forLanguageTag("en-001")),
          of(ISO, LocaleWrapper.forLanguageTag("en-AE")),
          of(ISO, LocaleWrapper.forLanguageTag("en-AU")),
          of(ISO, LocaleWrapper.forLanguageTag("en-GB")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-CH")),
          of(ISO, LocaleWrapper.forLanguageTag("guz")),
          of(ISO, LocaleWrapper.forLanguageTag("haw")),
          of(ISO, LocaleWrapper.forLanguageTag("hi")),
          of(ISO, LocaleWrapper.forLanguageTag("ig")),
          of(ISO, LocaleWrapper.forLanguageTag("it-CH")),
          of(ISO, LocaleWrapper.forLanguageTag("jmc")),
          of(ISO, LocaleWrapper.forLanguageTag("jv")),
          of(ISO, LocaleWrapper.forLanguageTag("kam")),
          of(ISO, LocaleWrapper.forLanguageTag("kde")),
          of(ISO, LocaleWrapper.forLanguageTag("ki")),
          of(ISO, LocaleWrapper.forLanguageTag("kln")),
          of(ISO, LocaleWrapper.forLanguageTag("ksb")),
          of(ISO, LocaleWrapper.forLanguageTag("lag")),
          of(ISO, LocaleWrapper.forLanguageTag("lg")),
          of(ISO, LocaleWrapper.forLanguageTag("luo")),
          of(ISO, LocaleWrapper.forLanguageTag("luy")),
          of(ISO, LocaleWrapper.forLanguageTag("mai")),
          of(ISO, LocaleWrapper.forLanguageTag("mas")),
          of(ISO, LocaleWrapper.forLanguageTag("mer")),
          of(ISO, LocaleWrapper.forLanguageTag("mgh")),
          of(ISO, LocaleWrapper.forLanguageTag("mi")),
          of(ISO, LocaleWrapper.forLanguageTag("mk")),
          of(ISO, LocaleWrapper.forLanguageTag("ms")),
          of(ISO, LocaleWrapper.forLanguageTag("naq")),
          of(ISO, LocaleWrapper.forLanguageTag("nd")),
          of(ISO, LocaleWrapper.forLanguageTag("nyn")),
          of(ISO, LocaleWrapper.forLanguageTag("pa")),
          of(ISO, LocaleWrapper.forLanguageTag("pcm")),
          of(ISO, LocaleWrapper.forLanguageTag("pl")),
          of(ISO, LocaleWrapper.forLanguageTag("ro")),
          of(ISO, LocaleWrapper.forLanguageTag("rof")),
          of(ISO, LocaleWrapper.forLanguageTag("rwk")),
          of(ISO, LocaleWrapper.forLanguageTag("sa")),
          of(ISO, LocaleWrapper.forLanguageTag("saq")),
          of(ISO, LocaleWrapper.forLanguageTag("sat")),
          of(ISO, LocaleWrapper.forLanguageTag("sbp")),
          of(ISO, LocaleWrapper.forLanguageTag("sd-Deva")),
          of(ISO, LocaleWrapper.forLanguageTag("sq")),
          of(ISO, LocaleWrapper.forLanguageTag("su")),
          of(ISO, LocaleWrapper.forLanguageTag("sw")),
          of(ISO, LocaleWrapper.forLanguageTag("teo")),
          of(ISO, LocaleWrapper.forLanguageTag("tzm")),
          of(ISO, LocaleWrapper.forLanguageTag("vai")),
          of(ISO, LocaleWrapper.forLanguageTag("vai-Latn")),
          of(ISO, LocaleWrapper.forLanguageTag("vun")),
          of(ISO, LocaleWrapper.forLanguageTag("xog")),
        });
    DATE_FULL_PATTERNS.put(
        "GGGGy年M月d日EEEE",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ja")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE dd 'de' MMMM 'de' y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("es-HN")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, MMMM d, y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("brx")),
          of(ISO, LocaleWrapper.forLanguageTag("ceb")),
          of(ISO, LocaleWrapper.forLanguageTag("chr")),
          of(ISO, LocaleWrapper.forLanguageTag("en")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CA")),
          of(ISO, LocaleWrapper.forLanguageTag("fil")),
          of(ISO, LocaleWrapper.forLanguageTag("kn")),
          of(ISO, LocaleWrapper.forLanguageTag("ks")),
          of(ISO, LocaleWrapper.forLanguageTag("lkt")),
          of(ISO, LocaleWrapper.forLanguageTag("om")),
          of(ISO, LocaleWrapper.forLanguageTag("or")),
          of(ISO, LocaleWrapper.forLanguageTag("zu")),
        });
    DATE_FULL_PATTERNS.put(
        "y၊ MMMM d၊ EEEE",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("my")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEEที่ d MMMM G y",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("th")),
          of(ISO, LocaleWrapper.forLanguageTag("th")),
        });
    DATE_FULL_PATTERNS.put(
        "d MMMM y G EEEE",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("tr")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d MMMM, y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("as")),
          of(ISO, LocaleWrapper.forLanguageTag("bn")),
          of(ISO, LocaleWrapper.forLanguageTag("ccp")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IN")),
          of(ISO, LocaleWrapper.forLanguageTag("gu")),
          of(ISO, LocaleWrapper.forLanguageTag("mr")),
          of(ISO, LocaleWrapper.forLanguageTag("qu")),
          of(ISO, LocaleWrapper.forLanguageTag("ta")),
          of(ISO, LocaleWrapper.forLanguageTag("vi")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, 'ils' d MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("rm")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d בMMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("he")),
        });
    DATE_FULL_PATTERNS.put(
        "y 'сыл' MMMM d 'күнэ', EEEE",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("sah")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, dd MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("en-BW")),
          of(ISO, LocaleWrapper.forLanguageTag("en-BZ")),
          of(ISO, LocaleWrapper.forLanguageTag("en-ZA")),
          of(ISO, LocaleWrapper.forLanguageTag("en-ZW")),
          of(ISO, LocaleWrapper.forLanguageTag("id")),
          of(ISO, LocaleWrapper.forLanguageTag("ms-ID")),
          of(ISO, LocaleWrapper.forLanguageTag("pa-Arab")),
          of(ISO, LocaleWrapper.forLanguageTag("tg")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE፣ d MMMM y G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("am")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE d 'di' MMMM 'dal' y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("fur")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, MMMM d, y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("bs-Cyrl")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("en")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fil")),
          of(MINGUO, LocaleWrapper.forLanguageTag("fil")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("sr")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("sr-Latn")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d. MMMM y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("cs")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("de")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("is")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("is")),
          of(MINGUO, LocaleWrapper.forLanguageTag("is")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ksh")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("lb")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, dd. MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("sl")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d MMM, y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("wo")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d MMMM y G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("be")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("el")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("ms")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("pl")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ro")),
        });
    DATE_FULL_PATTERNS.put(
        "y نچی ییل d نچی MMMM EEEE کونی",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("uz-Arab")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE 'den' d. MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("da")),
        });
    DATE_FULL_PATTERNS.put(
        "MMMM d, y, EEEE",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("mni")),
        });
    DATE_FULL_PATTERNS.put(
        "EEEE, d 'de' MMMM 'de' y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("es")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("pt")),
        });
    DATE_FULL_PATTERNS.put(
        "y. MMMM d., EEEE",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("hu")),
        });
    DATE_LONG_PATTERNS.put(
        "'Ngày' dd 'tháng' M 'năm' y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("vi")),
        });
    DATE_LONG_PATTERNS.put(
        "d MMMM y 'г'.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("be")),
          of(ISO, LocaleWrapper.forLanguageTag("bg")),
          of(ISO, LocaleWrapper.forLanguageTag("ru")),
        });
    DATE_LONG_PATTERNS.put(
        "d نچی MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("uz-Arab")),
        });
    DATE_LONG_PATTERNS.put(
        "d MMMM, y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("el")),
        });
    DATE_LONG_PATTERNS.put(
        "y. 'gada' d. MMMM",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("lv")),
        });
    DATE_LONG_PATTERNS.put(
        "dd MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("af")),
          of(ISO, LocaleWrapper.forLanguageTag("en-BW")),
          of(ISO, LocaleWrapper.forLanguageTag("en-BZ")),
          of(ISO, LocaleWrapper.forLanguageTag("en-MT")),
          of(ISO, LocaleWrapper.forLanguageTag("en-ZA")),
          of(ISO, LocaleWrapper.forLanguageTag("en-ZW")),
          of(ISO, LocaleWrapper.forLanguageTag("gv")),
          of(ISO, LocaleWrapper.forLanguageTag("kl")),
          of(ISO, LocaleWrapper.forLanguageTag("om")),
          of(ISO, LocaleWrapper.forLanguageTag("so")),
          of(ISO, LocaleWrapper.forLanguageTag("tg")),
          of(ISO, LocaleWrapper.forLanguageTag("ti")),
        });
    DATE_LONG_PATTERNS.put(
        "d-MMMM, y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("uz")),
        });
    DATE_LONG_PATTERNS.put(
        "d, MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("doi")),
        });
    DATE_LONG_PATTERNS.put(
        "d. MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("cs")),
          of(ISO, LocaleWrapper.forLanguageTag("da")),
          of(ISO, LocaleWrapper.forLanguageTag("de")),
          of(ISO, LocaleWrapper.forLanguageTag("dsb")),
          of(ISO, LocaleWrapper.forLanguageTag("et")),
          of(ISO, LocaleWrapper.forLanguageTag("fi")),
          of(ISO, LocaleWrapper.forLanguageTag("fo")),
          of(ISO, LocaleWrapper.forLanguageTag("gsw")),
          of(ISO, LocaleWrapper.forLanguageTag("hsb")),
          of(ISO, LocaleWrapper.forLanguageTag("is")),
          of(ISO, LocaleWrapper.forLanguageTag("ksh")),
          of(ISO, LocaleWrapper.forLanguageTag("lb")),
          of(ISO, LocaleWrapper.forLanguageTag("nds")),
          of(ISO, LocaleWrapper.forLanguageTag("nn")),
          of(ISO, LocaleWrapper.forLanguageTag("no")),
          of(ISO, LocaleWrapper.forLanguageTag("sk")),
          of(ISO, LocaleWrapper.forLanguageTag("wae")),
        });
    DATE_LONG_PATTERNS.put(
        "y年M月d日",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ja")),
          of(ISO, LocaleWrapper.forLanguageTag("yue")),
          of(ISO, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(ISO, LocaleWrapper.forLanguageTag("zh")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant-HK")),
        });
    DATE_LONG_PATTERNS.put(
        "d 'ta'’ MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("mt")),
        });
    DATE_LONG_PATTERNS.put(
        "d 'de' MMMM 'de' y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("es")),
          of(ISO, LocaleWrapper.forLanguageTag("es-419")),
          of(ISO, LocaleWrapper.forLanguageTag("es-MX")),
          of(ISO, LocaleWrapper.forLanguageTag("es-US")),
          of(ISO, LocaleWrapper.forLanguageTag("gl")),
          of(ISO, LocaleWrapper.forLanguageTag("pt")),
          of(ISO, LocaleWrapper.forLanguageTag("pt-PT")),
          of(ISO, LocaleWrapper.forLanguageTag("seh")),
        });
    DATE_LONG_PATTERNS.put(
        "d MMMM، y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ur")),
        });
    DATE_LONG_PATTERNS.put(
        "d 'de' MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ia")),
        });
    DATE_LONG_PATTERNS.put(
        "y၊ d MMMM",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("my")),
        });
    DATE_LONG_PATTERNS.put(
        "dd MMMM, y թ.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("hy")),
        });
    DATE_LONG_PATTERNS.put(
        "d MMMM 'de' y G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ast")),
          of(MINGUO, LocaleWrapper.forLanguageTag("ast")),
          of(MINGUO, LocaleWrapper.forLanguageTag("ca")),
        });
    DATE_LONG_PATTERNS.put(
        "dd. MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("sl")),
        });
    DATE_LONG_PATTERNS.put(
        "d 'de' MMMM 'de' y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("es")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("pt")),
        });
    DATE_LONG_PATTERNS.put(
        "د y د MMMM d",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ps")),
        });
    DATE_LONG_PATTERNS.put(
        "y-MMMM-dd",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("eo")),
        });
    DATE_LONG_PATTERNS.put(
        "d בMMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("he")),
        });
    DATE_LONG_PATTERNS.put(
        "MMMM d، y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("ug")),
        });
    DATE_LONG_PATTERNS.put(
        "G y MMMM d",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("")),
          of(HIJRAH_UMALQURA, LocaleWrapper.forLanguageTag("")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("")),
          of(MINGUO, LocaleWrapper.forLanguageTag("")),
        });
    DATE_LONG_PATTERNS.put(
        "y 'оны' MMMM'ын' d",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("mn")),
        });
    DATE_LONG_PATTERNS.put(
        "dd MMMM y 'г'. G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("mk")),
        });
    DATE_LONG_PATTERNS.put(
        "d MMMM y 'р'.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("uk")),
        });
    DATE_LONG_PATTERNS.put(
        "d MMMM, y 'ел'",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("tt")),
        });
    DATE_LONG_PATTERNS.put(
        "སྤྱི་ལོ་y MMMMའི་ཚེས་d",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("bo")),
        });
    DATE_LONG_PATTERNS.put(
        "dی MMMMی y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ckb")),
        });
    DATE_LONG_PATTERNS.put(
        "GGGGy年M月d日",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ja")),
        });
    DATE_LONG_PATTERNS.put(
        "dd. MMMM y.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("bs-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("sr")),
          of(ISO, LocaleWrapper.forLanguageTag("sr-Latn")),
        });
    DATE_LONG_PATTERNS.put(
        "MMMM d 'lia' y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ee")),
        });
    DATE_LONG_PATTERNS.put(
        "Gy年M月d日",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("ja")),
          of(MINGUO, LocaleWrapper.forLanguageTag("ja")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("yue")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("yue")),
          of(MINGUO, LocaleWrapper.forLanguageTag("yue")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(MINGUO, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("zh")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("zh")),
          of(MINGUO, LocaleWrapper.forLanguageTag("zh")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(MINGUO, LocaleWrapper.forLanguageTag("zh-Hant")),
        });
    DATE_LONG_PATTERNS.put(
        "སྤྱི་ལོ་y MMMM ཚེས་ dd",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("dz")),
        });
    DATE_LONG_PATTERNS.put(
        "dטן MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("yi")),
        });
    DATE_LONG_PATTERNS.put(
        "y 'm'. MMMM d 'd'.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("lt")),
        });
    DATE_LONG_PATTERNS.put(
        "y-'ж'., d-MMMM",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ky")),
        });
    DATE_LONG_PATTERNS.put(
        "MMMM d 'lia', G y",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("ee")),
          of(MINGUO, LocaleWrapper.forLanguageTag("ee")),
        });
    DATE_LONG_PATTERNS.put(
        "G y. MMMM d.",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("hu")),
        });
    DATE_LONG_PATTERNS.put(
        "d-MMMM، y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ug")),
        });
    DATE_LONG_PATTERNS.put(
        "d. MMMM y G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("cs")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("cs")),
          of(MINGUO, LocaleWrapper.forLanguageTag("cs")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("da")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("de")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fi")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("fi")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("gsw")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("is")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("is")),
          of(MINGUO, LocaleWrapper.forLanguageTag("is")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("lb")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("no")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("no")),
          of(MINGUO, LocaleWrapper.forLanguageTag("no")),
        });
    DATE_LONG_PATTERNS.put(
        "d MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("af-NA")),
          of(ISO, LocaleWrapper.forLanguageTag("agq")),
          of(ISO, LocaleWrapper.forLanguageTag("am")),
          of(ISO, LocaleWrapper.forLanguageTag("ar")),
          of(ISO, LocaleWrapper.forLanguageTag("asa")),
          of(ISO, LocaleWrapper.forLanguageTag("az")),
          of(ISO, LocaleWrapper.forLanguageTag("az-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("bas")),
          of(ISO, LocaleWrapper.forLanguageTag("bem")),
          of(ISO, LocaleWrapper.forLanguageTag("bez")),
          of(ISO, LocaleWrapper.forLanguageTag("bm")),
          of(ISO, LocaleWrapper.forLanguageTag("br")),
          of(ISO, LocaleWrapper.forLanguageTag("cgg")),
          of(ISO, LocaleWrapper.forLanguageTag("cy")),
          of(ISO, LocaleWrapper.forLanguageTag("dav")),
          of(ISO, LocaleWrapper.forLanguageTag("dje")),
          of(ISO, LocaleWrapper.forLanguageTag("dua")),
          of(ISO, LocaleWrapper.forLanguageTag("dyo")),
          of(ISO, LocaleWrapper.forLanguageTag("ebu")),
          of(ISO, LocaleWrapper.forLanguageTag("el")),
          of(ISO, LocaleWrapper.forLanguageTag("en-001")),
          of(ISO, LocaleWrapper.forLanguageTag("en-AE")),
          of(ISO, LocaleWrapper.forLanguageTag("en-AU")),
          of(ISO, LocaleWrapper.forLanguageTag("en-GB")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IN")),
          of(ISO, LocaleWrapper.forLanguageTag("ewo")),
          of(ISO, LocaleWrapper.forLanguageTag("fa")),
          of(ISO, LocaleWrapper.forLanguageTag("ff")),
          of(ISO, LocaleWrapper.forLanguageTag("fr")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-CA")),
          of(ISO, LocaleWrapper.forLanguageTag("fy")),
          of(ISO, LocaleWrapper.forLanguageTag("ga")),
          of(ISO, LocaleWrapper.forLanguageTag("guz")),
          of(ISO, LocaleWrapper.forLanguageTag("haw")),
          of(ISO, LocaleWrapper.forLanguageTag("hi")),
          of(ISO, LocaleWrapper.forLanguageTag("id")),
          of(ISO, LocaleWrapper.forLanguageTag("ig")),
          of(ISO, LocaleWrapper.forLanguageTag("it")),
          of(ISO, LocaleWrapper.forLanguageTag("jmc")),
          of(ISO, LocaleWrapper.forLanguageTag("jv")),
          of(ISO, LocaleWrapper.forLanguageTag("kab")),
          of(ISO, LocaleWrapper.forLanguageTag("kam")),
          of(ISO, LocaleWrapper.forLanguageTag("kde")),
          of(ISO, LocaleWrapper.forLanguageTag("khq")),
          of(ISO, LocaleWrapper.forLanguageTag("ki")),
          of(ISO, LocaleWrapper.forLanguageTag("kkj")),
          of(ISO, LocaleWrapper.forLanguageTag("kln")),
          of(ISO, LocaleWrapper.forLanguageTag("km")),
          of(ISO, LocaleWrapper.forLanguageTag("kok")),
          of(ISO, LocaleWrapper.forLanguageTag("ksb")),
          of(ISO, LocaleWrapper.forLanguageTag("ksf")),
          of(ISO, LocaleWrapper.forLanguageTag("kw")),
          of(ISO, LocaleWrapper.forLanguageTag("lag")),
          of(ISO, LocaleWrapper.forLanguageTag("lg")),
          of(ISO, LocaleWrapper.forLanguageTag("ln")),
          of(ISO, LocaleWrapper.forLanguageTag("lo")),
          of(ISO, LocaleWrapper.forLanguageTag("lu")),
          of(ISO, LocaleWrapper.forLanguageTag("luo")),
          of(ISO, LocaleWrapper.forLanguageTag("luy")),
          of(ISO, LocaleWrapper.forLanguageTag("mai")),
          of(ISO, LocaleWrapper.forLanguageTag("mas")),
          of(ISO, LocaleWrapper.forLanguageTag("mer")),
          of(ISO, LocaleWrapper.forLanguageTag("mfe")),
          of(ISO, LocaleWrapper.forLanguageTag("mg")),
          of(ISO, LocaleWrapper.forLanguageTag("mgh")),
          of(ISO, LocaleWrapper.forLanguageTag("mi")),
          of(ISO, LocaleWrapper.forLanguageTag("mk")),
          of(ISO, LocaleWrapper.forLanguageTag("ms")),
          of(ISO, LocaleWrapper.forLanguageTag("mua")),
          of(ISO, LocaleWrapper.forLanguageTag("naq")),
          of(ISO, LocaleWrapper.forLanguageTag("nd")),
          of(ISO, LocaleWrapper.forLanguageTag("nl")),
          of(ISO, LocaleWrapper.forLanguageTag("nmg")),
          of(ISO, LocaleWrapper.forLanguageTag("nus")),
          of(ISO, LocaleWrapper.forLanguageTag("nyn")),
          of(ISO, LocaleWrapper.forLanguageTag("pa")),
          of(ISO, LocaleWrapper.forLanguageTag("pa-Arab")),
          of(ISO, LocaleWrapper.forLanguageTag("pcm")),
          of(ISO, LocaleWrapper.forLanguageTag("pl")),
          of(ISO, LocaleWrapper.forLanguageTag("qu")),
          of(ISO, LocaleWrapper.forLanguageTag("rm")),
          of(ISO, LocaleWrapper.forLanguageTag("rn")),
          of(ISO, LocaleWrapper.forLanguageTag("ro")),
          of(ISO, LocaleWrapper.forLanguageTag("rof")),
          of(ISO, LocaleWrapper.forLanguageTag("rwk")),
          of(ISO, LocaleWrapper.forLanguageTag("sa")),
          of(ISO, LocaleWrapper.forLanguageTag("saq")),
          of(ISO, LocaleWrapper.forLanguageTag("sat")),
          of(ISO, LocaleWrapper.forLanguageTag("sbp")),
          of(ISO, LocaleWrapper.forLanguageTag("sd-Deva")),
          of(ISO, LocaleWrapper.forLanguageTag("se-FI")),
          of(ISO, LocaleWrapper.forLanguageTag("ses")),
          of(ISO, LocaleWrapper.forLanguageTag("sg")),
          of(ISO, LocaleWrapper.forLanguageTag("shi")),
          of(ISO, LocaleWrapper.forLanguageTag("shi-Latn")),
          of(ISO, LocaleWrapper.forLanguageTag("sq")),
          of(ISO, LocaleWrapper.forLanguageTag("su")),
          of(ISO, LocaleWrapper.forLanguageTag("sv")),
          of(ISO, LocaleWrapper.forLanguageTag("sw")),
          of(ISO, LocaleWrapper.forLanguageTag("teo")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("th")),
          of(ISO, LocaleWrapper.forLanguageTag("tk")),
          of(ISO, LocaleWrapper.forLanguageTag("to")),
          of(ISO, LocaleWrapper.forLanguageTag("tr")),
          of(ISO, LocaleWrapper.forLanguageTag("twq")),
          of(ISO, LocaleWrapper.forLanguageTag("tzm")),
          of(ISO, LocaleWrapper.forLanguageTag("vai")),
          of(ISO, LocaleWrapper.forLanguageTag("vai-Latn")),
          of(ISO, LocaleWrapper.forLanguageTag("vun")),
          of(ISO, LocaleWrapper.forLanguageTag("xog")),
          of(ISO, LocaleWrapper.forLanguageTag("yav")),
          of(ISO, LocaleWrapper.forLanguageTag("zgh")),
        });
    DATE_LONG_PATTERNS.put(
        "d MMMM y G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("am")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("be")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("br")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("br")),
          of(MINGUO, LocaleWrapper.forLanguageTag("br")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ca")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("el")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fr")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("fr")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fy")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("fy")),
          of(MINGUO, LocaleWrapper.forLanguageTag("fy")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ga")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("id")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("id")),
          of(MINGUO, LocaleWrapper.forLanguageTag("id")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("ms")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("nl")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("nl")),
          of(MINGUO, LocaleWrapper.forLanguageTag("nl")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("pl")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ro")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("sv")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("tr")),
        });
    DATE_LONG_PATTERNS.put(
        "d MMMM G y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("th")),
        });
    DATE_LONG_PATTERNS.put(
        "d. MMMM y. G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("hr")),
        });
    DATE_LONG_PATTERNS.put(
        "dd MMMM y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("it")),
        });
    DATE_LONG_PATTERNS.put(
        "d MMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("yo")),
        });
    DATE_LONG_PATTERNS.put(
        "dd 'de' MMMM 'de' y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("es-HN")),
        });
    DATE_LONG_PATTERNS.put(
        "MMMM d, y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("brx")),
          of(ISO, LocaleWrapper.forLanguageTag("ceb")),
          of(ISO, LocaleWrapper.forLanguageTag("chr")),
          of(ISO, LocaleWrapper.forLanguageTag("en")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CA")),
          of(ISO, LocaleWrapper.forLanguageTag("fil")),
          of(ISO, LocaleWrapper.forLanguageTag("kn")),
          of(ISO, LocaleWrapper.forLanguageTag("ks")),
          of(ISO, LocaleWrapper.forLanguageTag("lkt")),
          of(ISO, LocaleWrapper.forLanguageTag("mni")),
          of(ISO, LocaleWrapper.forLanguageTag("or")),
          of(ISO, LocaleWrapper.forLanguageTag("zu")),
        });
    DATE_LONG_PATTERNS.put(
        "y('e')'ko' MMMM'ren' d('a')",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("eu")),
        });
    DATE_LONG_PATTERNS.put(
        "MMMM d. y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("smn")),
        });
    DATE_LONG_PATTERNS.put(
        "y, MMMM d",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ml")), of(ISO, LocaleWrapper.forLanguageTag("sah")),
        });
    DATE_LONG_PATTERNS.put(
        "d MMMM 'de' y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ast")), of(ISO, LocaleWrapper.forLanguageTag("ca")),
        });
    DATE_LONG_PATTERNS.put(
        "y 'ж'. d MMMM",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("kk")),
        });
    DATE_LONG_PATTERNS.put(
        "'lyɛ'̌ʼ d 'na' MMMM, y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("nnh")),
        });
    DATE_LONG_PATTERNS.put(
        "d MMMM, y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("as")),
          of(ISO, LocaleWrapper.forLanguageTag("bn")),
          of(ISO, LocaleWrapper.forLanguageTag("ccp")),
          of(ISO, LocaleWrapper.forLanguageTag("gu")),
          of(ISO, LocaleWrapper.forLanguageTag("ha")),
          of(ISO, LocaleWrapper.forLanguageTag("ka")),
          of(ISO, LocaleWrapper.forLanguageTag("mr")),
          of(ISO, LocaleWrapper.forLanguageTag("ta")),
          of(ISO, LocaleWrapper.forLanguageTag("te")),
          of(ISO, LocaleWrapper.forLanguageTag("uz-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("vi")),
          of(ISO, LocaleWrapper.forLanguageTag("wo")),
        });
    DATE_LONG_PATTERNS.put(
        "y MMMM d",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ak")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm")),
          of(ISO, LocaleWrapper.forLanguageTag("ii")),
          of(ISO, LocaleWrapper.forLanguageTag("jgo")),
          of(ISO, LocaleWrapper.forLanguageTag("lrc")),
          of(ISO, LocaleWrapper.forLanguageTag("mgo")),
          of(ISO, LocaleWrapper.forLanguageTag("ne")),
          of(ISO, LocaleWrapper.forLanguageTag("")),
          of(ISO, LocaleWrapper.forLanguageTag("rw")),
          of(ISO, LocaleWrapper.forLanguageTag("sd")),
          of(ISO, LocaleWrapper.forLanguageTag("se")),
          of(ISO, LocaleWrapper.forLanguageTag("si")),
          of(ISO, LocaleWrapper.forLanguageTag("sn")),
          of(ISO, LocaleWrapper.forLanguageTag("xh")),
        });
    DATE_LONG_PATTERNS.put(
        "MMMM d, y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("bs-Cyrl")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("en")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fil")),
          of(MINGUO, LocaleWrapper.forLanguageTag("fil")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("sr")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("sr-Latn")),
        });
    DATE_LONG_PATTERNS.put(
        "y. MMMM d.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("hu")),
        });
    DATE_LONG_PATTERNS.put(
        "d MMMM, y 'аз'",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("os")),
        });
    DATE_LONG_PATTERNS.put(
        "d 'di' MMMM 'di' y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("kea")),
        });
    DATE_LONG_PATTERNS.put(
        "y년 M월 d일",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ko")),
        });
    DATE_LONG_PATTERNS.put(
        "d. MMMM y.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("bs")), of(ISO, LocaleWrapper.forLanguageTag("hr")),
        });
    DATE_LONG_PATTERNS.put(
        "d 'di' MMMM 'dal' y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("fur")),
        });
    DATE_LONG_PATTERNS.put(
        "d'mh' MMMM y G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("gd")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("gd")),
          of(MINGUO, LocaleWrapper.forLanguageTag("gd")),
        });
    DATE_LONG_PATTERNS.put(
        "d'mh' MMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("gd")),
        });
    DATE_LONG_PATTERNS.put(
        "d MMMM ปีG y",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("th")),
          of(MINGUO, LocaleWrapper.forLanguageTag("th")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d. MMM y.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("bs")), of(ISO, LocaleWrapper.forLanguageTag("hr")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d MMM, y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("el")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d MMM 'de' y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("es-BO")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d. MMM y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("da")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("gsw")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("no")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("no")),
          of(MINGUO, LocaleWrapper.forLanguageTag("no")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "y MMM d",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ak")),
          of(ISO, LocaleWrapper.forLanguageTag("ckb")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm")),
          of(ISO, LocaleWrapper.forLanguageTag("ii")),
          of(ISO, LocaleWrapper.forLanguageTag("jgo")),
          of(ISO, LocaleWrapper.forLanguageTag("lrc")),
          of(ISO, LocaleWrapper.forLanguageTag("mg")),
          of(ISO, LocaleWrapper.forLanguageTag("mgo")),
          of(ISO, LocaleWrapper.forLanguageTag("ne")),
          of(ISO, LocaleWrapper.forLanguageTag("ps")),
          of(ISO, LocaleWrapper.forLanguageTag("")),
          of(ISO, LocaleWrapper.forLanguageTag("rw")),
          of(ISO, LocaleWrapper.forLanguageTag("sd")),
          of(ISO, LocaleWrapper.forLanguageTag("se")),
          of(ISO, LocaleWrapper.forLanguageTag("si")),
          of(ISO, LocaleWrapper.forLanguageTag("sn")),
          of(ISO, LocaleWrapper.forLanguageTag("xh")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "y. M. d.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ko")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "dd MMM y 'аз'",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("os")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "y-MM-dd",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("lt")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "dd.MM.y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("de")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("lb")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ro")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "MMM dd, y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("gv")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "y 'оны' MMM'ын' d",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("mn")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "dd-MM-y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("as")),
          of(ISO, LocaleWrapper.forLanguageTag("es-CL")),
          of(ISO, LocaleWrapper.forLanguageTag("rm")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d.MM.y 'г'.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("bg")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "y. MMM d.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("hu")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "y年M月d日",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("yue")),
          of(ISO, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(ISO, LocaleWrapper.forLanguageTag("zh")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant-HK")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "MMM d, y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("bs-Cyrl")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("en")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fil")),
          of(MINGUO, LocaleWrapper.forLanguageTag("fil")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("sr")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("sr-Latn")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "dd.MM.y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("de")),
          of(ISO, LocaleWrapper.forLanguageTag("fo")),
          of(ISO, LocaleWrapper.forLanguageTag("gsw")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d.M.y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("dsb")),
          of(ISO, LocaleWrapper.forLanguageTag("fi")),
          of(ISO, LocaleWrapper.forLanguageTag("hsb")),
          of(ISO, LocaleWrapper.forLanguageTag("mk")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d 'de' MMM 'de' y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("gl")),
          of(ISO, LocaleWrapper.forLanguageTag("pt")),
          of(ISO, LocaleWrapper.forLanguageTag("seh")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "dטן MMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("yi")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "G y MMM d",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("")),
          of(HIJRAH_UMALQURA, LocaleWrapper.forLanguageTag("")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("")),
          of(MINGUO, LocaleWrapper.forLanguageTag("")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d. MMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("da")),
          of(ISO, LocaleWrapper.forLanguageTag("et")),
          of(ISO, LocaleWrapper.forLanguageTag("is")),
          of(ISO, LocaleWrapper.forLanguageTag("lb")),
          of(ISO, LocaleWrapper.forLanguageTag("nds")),
          of(ISO, LocaleWrapper.forLanguageTag("nn")),
          of(ISO, LocaleWrapper.forLanguageTag("no")),
          of(ISO, LocaleWrapper.forLanguageTag("sl")),
          of(ISO, LocaleWrapper.forLanguageTag("wae")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d. M. y G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("cs")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("cs")),
          of(MINGUO, LocaleWrapper.forLanguageTag("cs")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d MMM y 'г'.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ru")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "MMM d، y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("ug")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d.MM.y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("be")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "dd MMM, y թ.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("hy")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d-MMM-y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("kok")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "y 'ж'. dd MMM",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("kk")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "MMM d 'lia', G y",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("ee")),
          of(MINGUO, LocaleWrapper.forLanguageTag("ee")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d בMMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("he")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "dd.MM.y.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("bs-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("sr")),
          of(ISO, LocaleWrapper.forLanguageTag("sr-Latn")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "dd/MM/y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("fur")),
          of(ISO, LocaleWrapper.forLanguageTag("pt-PT")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d MM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("yo")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "Gy年M月d日",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("ja")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("yue")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("yue")),
          of(MINGUO, LocaleWrapper.forLanguageTag("yue")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(MINGUO, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("zh")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("zh")),
          of(MINGUO, LocaleWrapper.forLanguageTag("zh")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(MINGUO, LocaleWrapper.forLanguageTag("zh-Hant")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "dd-MM-y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("vi")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "dd-MMM-y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("en-BZ")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IN")),
          of(ISO, LocaleWrapper.forLanguageTag("en-PK")),
          of(ISO, LocaleWrapper.forLanguageTag("om")),
          of(ISO, LocaleWrapper.forLanguageTag("so")),
          of(ISO, LocaleWrapper.forLanguageTag("ti")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "dd MMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("af")),
          of(ISO, LocaleWrapper.forLanguageTag("en-BE")),
          of(ISO, LocaleWrapper.forLanguageTag("en-BW")),
          of(ISO, LocaleWrapper.forLanguageTag("en-MT")),
          of(ISO, LocaleWrapper.forLanguageTag("en-ZA")),
          of(ISO, LocaleWrapper.forLanguageTag("kl")),
          of(ISO, LocaleWrapper.forLanguageTag("mt")),
          of(ISO, LocaleWrapper.forLanguageTag("tg")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "Gy/MM/dd",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ja")),
          of(MINGUO, LocaleWrapper.forLanguageTag("ja")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d/MM/y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("en-NZ")),
          of(ISO, LocaleWrapper.forLanguageTag("es-CO")),
          of(ISO, LocaleWrapper.forLanguageTag("es-GT")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d MMM، y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ur")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "y('e')'ko' MMM d('a')",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("eu")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "dd MMM y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("it")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "y-'ж'., d-MMM",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ky")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "dd/MM/y G",
        new PatternCoordinates[] {
          of(MINGUO, LocaleWrapper.forLanguageTag("ca")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("es")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("ms")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("pt")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "MM/dd/y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("es-PA")),
          of(ISO, LocaleWrapper.forLanguageTag("es-PR")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d MMM, y 'ел'",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("tt")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "y-MMM-dd",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("eo")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "y, MMM d",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ml")), of(ISO, LocaleWrapper.forLanguageTag("sah")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "y ལོའི་MMMཚེས་d",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("bo")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "G y.MM.dd.",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("hu")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "y/MM/dd",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ja")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d MMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("af-NA")),
          of(ISO, LocaleWrapper.forLanguageTag("am")),
          of(ISO, LocaleWrapper.forLanguageTag("asa")),
          of(ISO, LocaleWrapper.forLanguageTag("ast")),
          of(ISO, LocaleWrapper.forLanguageTag("az")),
          of(ISO, LocaleWrapper.forLanguageTag("az-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("bem")),
          of(ISO, LocaleWrapper.forLanguageTag("bez")),
          of(ISO, LocaleWrapper.forLanguageTag("br")),
          of(ISO, LocaleWrapper.forLanguageTag("ca")),
          of(ISO, LocaleWrapper.forLanguageTag("cgg")),
          of(ISO, LocaleWrapper.forLanguageTag("cy")),
          of(ISO, LocaleWrapper.forLanguageTag("dav")),
          of(ISO, LocaleWrapper.forLanguageTag("dua")),
          of(ISO, LocaleWrapper.forLanguageTag("dyo")),
          of(ISO, LocaleWrapper.forLanguageTag("ebu")),
          of(ISO, LocaleWrapper.forLanguageTag("el")),
          of(ISO, LocaleWrapper.forLanguageTag("en-001")),
          of(ISO, LocaleWrapper.forLanguageTag("en-AE")),
          of(ISO, LocaleWrapper.forLanguageTag("en-AU")),
          of(ISO, LocaleWrapper.forLanguageTag("en-GB")),
          of(ISO, LocaleWrapper.forLanguageTag("es")),
          of(ISO, LocaleWrapper.forLanguageTag("es-419")),
          of(ISO, LocaleWrapper.forLanguageTag("es-MX")),
          of(ISO, LocaleWrapper.forLanguageTag("es-US")),
          of(ISO, LocaleWrapper.forLanguageTag("ewo")),
          of(ISO, LocaleWrapper.forLanguageTag("fa")),
          of(ISO, LocaleWrapper.forLanguageTag("fr")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-CA")),
          of(ISO, LocaleWrapper.forLanguageTag("fy")),
          of(ISO, LocaleWrapper.forLanguageTag("ga")),
          of(ISO, LocaleWrapper.forLanguageTag("gd")),
          of(ISO, LocaleWrapper.forLanguageTag("guz")),
          of(ISO, LocaleWrapper.forLanguageTag("haw")),
          of(ISO, LocaleWrapper.forLanguageTag("hi")),
          of(ISO, LocaleWrapper.forLanguageTag("ia")),
          of(ISO, LocaleWrapper.forLanguageTag("id")),
          of(ISO, LocaleWrapper.forLanguageTag("ig")),
          of(ISO, LocaleWrapper.forLanguageTag("it")),
          of(ISO, LocaleWrapper.forLanguageTag("jmc")),
          of(ISO, LocaleWrapper.forLanguageTag("jv")),
          of(ISO, LocaleWrapper.forLanguageTag("kam")),
          of(ISO, LocaleWrapper.forLanguageTag("kde")),
          of(ISO, LocaleWrapper.forLanguageTag("kea")),
          of(ISO, LocaleWrapper.forLanguageTag("ki")),
          of(ISO, LocaleWrapper.forLanguageTag("kkj")),
          of(ISO, LocaleWrapper.forLanguageTag("kln")),
          of(ISO, LocaleWrapper.forLanguageTag("km")),
          of(ISO, LocaleWrapper.forLanguageTag("ksb")),
          of(ISO, LocaleWrapper.forLanguageTag("ksf")),
          of(ISO, LocaleWrapper.forLanguageTag("kw")),
          of(ISO, LocaleWrapper.forLanguageTag("lag")),
          of(ISO, LocaleWrapper.forLanguageTag("lg")),
          of(ISO, LocaleWrapper.forLanguageTag("ln")),
          of(ISO, LocaleWrapper.forLanguageTag("lo")),
          of(ISO, LocaleWrapper.forLanguageTag("lu")),
          of(ISO, LocaleWrapper.forLanguageTag("luo")),
          of(ISO, LocaleWrapper.forLanguageTag("luy")),
          of(ISO, LocaleWrapper.forLanguageTag("mai")),
          of(ISO, LocaleWrapper.forLanguageTag("mas")),
          of(ISO, LocaleWrapper.forLanguageTag("mer")),
          of(ISO, LocaleWrapper.forLanguageTag("mgh")),
          of(ISO, LocaleWrapper.forLanguageTag("mi")),
          of(ISO, LocaleWrapper.forLanguageTag("ms")),
          of(ISO, LocaleWrapper.forLanguageTag("mua")),
          of(ISO, LocaleWrapper.forLanguageTag("naq")),
          of(ISO, LocaleWrapper.forLanguageTag("nd")),
          of(ISO, LocaleWrapper.forLanguageTag("nl")),
          of(ISO, LocaleWrapper.forLanguageTag("nmg")),
          of(ISO, LocaleWrapper.forLanguageTag("nus")),
          of(ISO, LocaleWrapper.forLanguageTag("nyn")),
          of(ISO, LocaleWrapper.forLanguageTag("pa")),
          of(ISO, LocaleWrapper.forLanguageTag("pa-Arab")),
          of(ISO, LocaleWrapper.forLanguageTag("pcm")),
          of(ISO, LocaleWrapper.forLanguageTag("pl")),
          of(ISO, LocaleWrapper.forLanguageTag("qu")),
          of(ISO, LocaleWrapper.forLanguageTag("rn")),
          of(ISO, LocaleWrapper.forLanguageTag("ro")),
          of(ISO, LocaleWrapper.forLanguageTag("rof")),
          of(ISO, LocaleWrapper.forLanguageTag("rwk")),
          of(ISO, LocaleWrapper.forLanguageTag("sa")),
          of(ISO, LocaleWrapper.forLanguageTag("saq")),
          of(ISO, LocaleWrapper.forLanguageTag("sat")),
          of(ISO, LocaleWrapper.forLanguageTag("sbp")),
          of(ISO, LocaleWrapper.forLanguageTag("sd-Deva")),
          of(ISO, LocaleWrapper.forLanguageTag("se-FI")),
          of(ISO, LocaleWrapper.forLanguageTag("sq")),
          of(ISO, LocaleWrapper.forLanguageTag("su")),
          of(ISO, LocaleWrapper.forLanguageTag("sv")),
          of(ISO, LocaleWrapper.forLanguageTag("sw")),
          of(ISO, LocaleWrapper.forLanguageTag("teo")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("th")),
          of(ISO, LocaleWrapper.forLanguageTag("th")),
          of(ISO, LocaleWrapper.forLanguageTag("tk")),
          of(ISO, LocaleWrapper.forLanguageTag("to")),
          of(ISO, LocaleWrapper.forLanguageTag("tr")),
          of(ISO, LocaleWrapper.forLanguageTag("twq")),
          of(ISO, LocaleWrapper.forLanguageTag("tzm")),
          of(ISO, LocaleWrapper.forLanguageTag("uz-Arab")),
          of(ISO, LocaleWrapper.forLanguageTag("vai")),
          of(ISO, LocaleWrapper.forLanguageTag("vai-Latn")),
          of(ISO, LocaleWrapper.forLanguageTag("vun")),
          of(ISO, LocaleWrapper.forLanguageTag("xog")),
          of(ISO, LocaleWrapper.forLanguageTag("yav")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d MMM y G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("am")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ast")),
          of(MINGUO, LocaleWrapper.forLanguageTag("ast")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("be")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("br")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("br")),
          of(MINGUO, LocaleWrapper.forLanguageTag("br")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ca")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("el")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fr")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("fr")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fy")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("fy")),
          of(MINGUO, LocaleWrapper.forLanguageTag("fy")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ga")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("id")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("id")),
          of(MINGUO, LocaleWrapper.forLanguageTag("id")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("nl")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("nl")),
          of(MINGUO, LocaleWrapper.forLanguageTag("nl")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("pl")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("sv")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("tr")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d MMM G y",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("th")),
          of(MINGUO, LocaleWrapper.forLanguageTag("th")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "MMM d, y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("brx")),
          of(ISO, LocaleWrapper.forLanguageTag("ceb")),
          of(ISO, LocaleWrapper.forLanguageTag("chr")),
          of(ISO, LocaleWrapper.forLanguageTag("en")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CA")),
          of(ISO, LocaleWrapper.forLanguageTag("fil")),
          of(ISO, LocaleWrapper.forLanguageTag("kn")),
          of(ISO, LocaleWrapper.forLanguageTag("ks")),
          of(ISO, LocaleWrapper.forLanguageTag("lkt")),
          of(ISO, LocaleWrapper.forLanguageTag("mni")),
          of(ISO, LocaleWrapper.forLanguageTag("or")),
          of(ISO, LocaleWrapper.forLanguageTag("zu")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d. M. y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("cs")), of(ISO, LocaleWrapper.forLanguageTag("sk")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "dd.M.y G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("mk")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "MMM d. y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("smn")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "སྤྱི་ལོ་y ཟླ་MMM ཚེས་dd",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("dz")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d-MMM، y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ug")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "y၊ MMM d",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("my")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d, MMM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("doi")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "MMM d 'lia', y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ee")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d. MMM. y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ksh")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d-MMM, y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("uz")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d.M.y G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fi")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("fi")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("is")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("is")),
          of(MINGUO, LocaleWrapper.forLanguageTag("is")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d MMM. y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ka")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d MMM, y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("agq")),
          of(ISO, LocaleWrapper.forLanguageTag("bas")),
          of(ISO, LocaleWrapper.forLanguageTag("bm")),
          of(ISO, LocaleWrapper.forLanguageTag("bn")),
          of(ISO, LocaleWrapper.forLanguageTag("ccp")),
          of(ISO, LocaleWrapper.forLanguageTag("dje")),
          of(ISO, LocaleWrapper.forLanguageTag("ff")),
          of(ISO, LocaleWrapper.forLanguageTag("gu")),
          of(ISO, LocaleWrapper.forLanguageTag("ha")),
          of(ISO, LocaleWrapper.forLanguageTag("kab")),
          of(ISO, LocaleWrapper.forLanguageTag("khq")),
          of(ISO, LocaleWrapper.forLanguageTag("mfe")),
          of(ISO, LocaleWrapper.forLanguageTag("mr")),
          of(ISO, LocaleWrapper.forLanguageTag("nnh")),
          of(ISO, LocaleWrapper.forLanguageTag("ses")),
          of(ISO, LocaleWrapper.forLanguageTag("sg")),
          of(ISO, LocaleWrapper.forLanguageTag("shi")),
          of(ISO, LocaleWrapper.forLanguageTag("shi-Latn")),
          of(ISO, LocaleWrapper.forLanguageTag("ta")),
          of(ISO, LocaleWrapper.forLanguageTag("te")),
          of(ISO, LocaleWrapper.forLanguageTag("uz-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("vi")),
          of(ISO, LocaleWrapper.forLanguageTag("wo")),
          of(ISO, LocaleWrapper.forLanguageTag("zgh")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "dd MMM,y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("en-ZW")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "dd‏/MM‏/y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ar")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "y. 'gada' d. MMM",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("lv")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d. M. y. G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("hr")),
        });
    DATE_MEDIUM_PATTERNS.put(
        "d MMM y 'р'.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("uk")),
        });
    DATE_SHORT_PATTERNS.put(
        "dd.MM.yy",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("az")),
          of(ISO, LocaleWrapper.forLanguageTag("az-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("cs")),
          of(ISO, LocaleWrapper.forLanguageTag("de")),
          of(ISO, LocaleWrapper.forLanguageTag("et")),
          of(ISO, LocaleWrapper.forLanguageTag("fo")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-CH")),
          of(ISO, LocaleWrapper.forLanguageTag("gsw")),
          of(ISO, LocaleWrapper.forLanguageTag("hy")),
          of(ISO, LocaleWrapper.forLanguageTag("it-CH")),
          of(ISO, LocaleWrapper.forLanguageTag("ka")),
          of(ISO, LocaleWrapper.forLanguageTag("kk")),
          of(ISO, LocaleWrapper.forLanguageTag("lb")),
          of(ISO, LocaleWrapper.forLanguageTag("lv")),
          of(ISO, LocaleWrapper.forLanguageTag("os")),
          of(ISO, LocaleWrapper.forLanguageTag("uk")),
        });
    DATE_SHORT_PATTERNS.put(
        "M/d/yy",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("brx")),
          of(ISO, LocaleWrapper.forLanguageTag("ceb")),
          of(ISO, LocaleWrapper.forLanguageTag("chr")),
          of(ISO, LocaleWrapper.forLanguageTag("ee")),
          of(ISO, LocaleWrapper.forLanguageTag("en")),
          of(ISO, LocaleWrapper.forLanguageTag("fil")),
          of(ISO, LocaleWrapper.forLanguageTag("ks")),
          of(ISO, LocaleWrapper.forLanguageTag("lkt")),
          of(ISO, LocaleWrapper.forLanguageTag("or")),
          of(ISO, LocaleWrapper.forLanguageTag("zu")),
        });
    DATE_SHORT_PATTERNS.put(
        "y-MM-dd",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("af")),
          of(ISO, LocaleWrapper.forLanguageTag("bo")),
          of(ISO, LocaleWrapper.forLanguageTag("ckb")),
          of(ISO, LocaleWrapper.forLanguageTag("dz")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CA")),
          of(ISO, LocaleWrapper.forLanguageTag("en-SE")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-CA")),
          of(ISO, LocaleWrapper.forLanguageTag("ii")),
          of(ISO, LocaleWrapper.forLanguageTag("jgo")),
          of(ISO, LocaleWrapper.forLanguageTag("kl")),
          of(ISO, LocaleWrapper.forLanguageTag("lrc")),
          of(ISO, LocaleWrapper.forLanguageTag("lt")),
          of(ISO, LocaleWrapper.forLanguageTag("mg")),
          of(ISO, LocaleWrapper.forLanguageTag("mgo")),
          of(ISO, LocaleWrapper.forLanguageTag("")),
          of(ISO, LocaleWrapper.forLanguageTag("rw")),
          of(ISO, LocaleWrapper.forLanguageTag("sd")),
          of(ISO, LocaleWrapper.forLanguageTag("se")),
          of(ISO, LocaleWrapper.forLanguageTag("si")),
          of(ISO, LocaleWrapper.forLanguageTag("sn")),
          of(ISO, LocaleWrapper.forLanguageTag("sv")),
          of(ISO, LocaleWrapper.forLanguageTag("ug")),
          of(ISO, LocaleWrapper.forLanguageTag("xh")),
        });
    DATE_SHORT_PATTERNS.put(
        "d-M-y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("as")),
        });
    DATE_SHORT_PATTERNS.put(
        "d/MM/yy",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("en-NZ")),
          of(ISO, LocaleWrapper.forLanguageTag("es-CO")),
          of(ISO, LocaleWrapper.forLanguageTag("es-GT")),
          of(ISO, LocaleWrapper.forLanguageTag("es-PE")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-BE")),
          of(ISO, LocaleWrapper.forLanguageTag("ms")),
        });
    DATE_SHORT_PATTERNS.put(
        "dd.MM.y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("pl")),
        });
    DATE_SHORT_PATTERNS.put(
        "yy/M/d",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("eu")),
          of(ISO, LocaleWrapper.forLanguageTag("ne")),
          of(ISO, LocaleWrapper.forLanguageTag("sah")),
        });
    DATE_SHORT_PATTERNS.put(
        "yy-MM-dd",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("eo")),
        });
    DATE_SHORT_PATTERNS.put(
        "dd-MM-y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ia")),
          of(ISO, LocaleWrapper.forLanguageTag("jv")),
          of(ISO, LocaleWrapper.forLanguageTag("mi")),
          of(ISO, LocaleWrapper.forLanguageTag("nl")),
          of(ISO, LocaleWrapper.forLanguageTag("wo")),
        });
    DATE_SHORT_PATTERNS.put(
        "GGGGG y.MM.dd.",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("hu")),
        });
    DATE_SHORT_PATTERNS.put(
        "dd.MM.y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("da")),
          of(ISO, LocaleWrapper.forLanguageTag("nn")),
          of(ISO, LocaleWrapper.forLanguageTag("no")),
          of(ISO, LocaleWrapper.forLanguageTag("pl")),
          of(ISO, LocaleWrapper.forLanguageTag("ro")),
          of(ISO, LocaleWrapper.forLanguageTag("ru")),
          of(ISO, LocaleWrapper.forLanguageTag("se-FI")),
          of(ISO, LocaleWrapper.forLanguageTag("tk")),
          of(ISO, LocaleWrapper.forLanguageTag("tt")),
        });
    DATE_SHORT_PATTERNS.put(
        "d.M.y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("fi")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("gsw")),
          of(ISO, LocaleWrapper.forLanguageTag("he")),
          of(ISO, LocaleWrapper.forLanguageTag("is")),
          of(ISO, LocaleWrapper.forLanguageTag("smn")),
        });
    DATE_SHORT_PATTERNS.put(
        "d/M/y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("agq")),
          of(ISO, LocaleWrapper.forLanguageTag("bas")),
          of(ISO, LocaleWrapper.forLanguageTag("bm")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("da")),
          of(ISO, LocaleWrapper.forLanguageTag("dje")),
          of(ISO, LocaleWrapper.forLanguageTag("dua")),
          of(ISO, LocaleWrapper.forLanguageTag("dyo")),
          of(ISO, LocaleWrapper.forLanguageTag("en-HK")),
          of(ISO, LocaleWrapper.forLanguageTag("en-ZW")),
          of(ISO, LocaleWrapper.forLanguageTag("es-US")),
          of(ISO, LocaleWrapper.forLanguageTag("ewo")),
          of(ISO, LocaleWrapper.forLanguageTag("ff")),
          of(ISO, LocaleWrapper.forLanguageTag("kab")),
          of(ISO, LocaleWrapper.forLanguageTag("khq")),
          of(ISO, LocaleWrapper.forLanguageTag("ksf")),
          of(ISO, LocaleWrapper.forLanguageTag("ln")),
          of(ISO, LocaleWrapper.forLanguageTag("lo")),
          of(ISO, LocaleWrapper.forLanguageTag("lu")),
          of(ISO, LocaleWrapper.forLanguageTag("mfe")),
          of(ISO, LocaleWrapper.forLanguageTag("mua")),
          of(ISO, LocaleWrapper.forLanguageTag("nmg")),
          of(ISO, LocaleWrapper.forLanguageTag("rn")),
          of(ISO, LocaleWrapper.forLanguageTag("seh")),
          of(ISO, LocaleWrapper.forLanguageTag("ses")),
          of(ISO, LocaleWrapper.forLanguageTag("sg")),
          of(ISO, LocaleWrapper.forLanguageTag("shi")),
          of(ISO, LocaleWrapper.forLanguageTag("shi-Latn")),
          of(ISO, LocaleWrapper.forLanguageTag("twq")),
          of(ISO, LocaleWrapper.forLanguageTag("yav")),
          of(ISO, LocaleWrapper.forLanguageTag("yo")),
          of(ISO, LocaleWrapper.forLanguageTag("zgh")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant-HK")),
        });
    DATE_SHORT_PATTERNS.put(
        "d.MM.yy 'г'.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("bg")),
        });
    DATE_SHORT_PATTERNS.put(
        "dd-MM-yy GGGGG",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fy")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("fy")),
          of(MINGUO, LocaleWrapper.forLanguageTag("fy")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("nl")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("nl")),
          of(MINGUO, LocaleWrapper.forLanguageTag("nl")),
        });
    DATE_SHORT_PATTERNS.put(
        "d.M.yy.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("bs-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("sr")),
          of(ISO, LocaleWrapper.forLanguageTag("sr-Latn")),
        });
    DATE_SHORT_PATTERNS.put(
        "Gy-MM-dd",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("zh")),
        });
    DATE_SHORT_PATTERNS.put(
        "yy. M. d.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ko")),
        });
    DATE_SHORT_PATTERNS.put(
        "d.MM.y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("tr")),
        });
    DATE_SHORT_PATTERNS.put(
        "dd/MM/y GGGGG",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("am")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ca")),
          of(MINGUO, LocaleWrapper.forLanguageTag("ca")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fr")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("fr")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ga")),
        });
    DATE_SHORT_PATTERNS.put(
        "d-M-yy",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("kok")),
        });
    DATE_SHORT_PATTERNS.put(
        "dd/MM/y GGGG",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("br")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("br")),
          of(MINGUO, LocaleWrapper.forLanguageTag("br")),
        });
    DATE_SHORT_PATTERNS.put(
        "Gyy/M/d",
        new PatternCoordinates[] {
          of(MINGUO, LocaleWrapper.forLanguageTag("yue-Hans")),
        });
    DATE_SHORT_PATTERNS.put(
        "d/M/yy",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ast")),
          of(ISO, LocaleWrapper.forLanguageTag("bn")),
          of(ISO, LocaleWrapper.forLanguageTag("ca")),
          of(ISO, LocaleWrapper.forLanguageTag("ccp")),
          of(ISO, LocaleWrapper.forLanguageTag("doi")),
          of(ISO, LocaleWrapper.forLanguageTag("el")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("el")),
          of(ISO, LocaleWrapper.forLanguageTag("en-AU")),
          of(ISO, LocaleWrapper.forLanguageTag("en-JM")),
          of(ISO, LocaleWrapper.forLanguageTag("en-SG")),
          of(ISO, LocaleWrapper.forLanguageTag("es")),
          of(ISO, LocaleWrapper.forLanguageTag("es-419")),
          of(ISO, LocaleWrapper.forLanguageTag("gu")),
          of(ISO, LocaleWrapper.forLanguageTag("ha")),
          of(ISO, LocaleWrapper.forLanguageTag("haw")),
          of(ISO, LocaleWrapper.forLanguageTag("hi")),
          of(ISO, LocaleWrapper.forLanguageTag("ig")),
          of(ISO, LocaleWrapper.forLanguageTag("km")),
          of(ISO, LocaleWrapper.forLanguageTag("kn")),
          of(ISO, LocaleWrapper.forLanguageTag("ky")),
          of(ISO, LocaleWrapper.forLanguageTag("mai")),
          of(ISO, LocaleWrapper.forLanguageTag("ml")),
          of(ISO, LocaleWrapper.forLanguageTag("mni")),
          of(ISO, LocaleWrapper.forLanguageTag("mr")),
          of(ISO, LocaleWrapper.forLanguageTag("pa")),
          of(ISO, LocaleWrapper.forLanguageTag("sa")),
          of(ISO, LocaleWrapper.forLanguageTag("sat")),
          of(ISO, LocaleWrapper.forLanguageTag("sd-Deva")),
          of(ISO, LocaleWrapper.forLanguageTag("su")),
          of(ISO, LocaleWrapper.forLanguageTag("ta")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("th")),
          of(ISO, LocaleWrapper.forLanguageTag("th")),
          of(ISO, LocaleWrapper.forLanguageTag("to")),
          of(ISO, LocaleWrapper.forLanguageTag("ur")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hans-HK")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hans-MO")),
        });
    DATE_SHORT_PATTERNS.put(
        "y/M/d",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("fa")),
          of(ISO, LocaleWrapper.forLanguageTag("ps")),
          of(ISO, LocaleWrapper.forLanguageTag("uz-Arab")),
          of(ISO, LocaleWrapper.forLanguageTag("yue")),
          of(ISO, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(ISO, LocaleWrapper.forLanguageTag("zh")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant")),
        });
    DATE_SHORT_PATTERNS.put(
        "y. MM. dd.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("hu")),
        });
    DATE_SHORT_PATTERNS.put(
        "dd/MM/y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("am")),
          of(ISO, LocaleWrapper.forLanguageTag("asa")),
          of(ISO, LocaleWrapper.forLanguageTag("bem")),
          of(ISO, LocaleWrapper.forLanguageTag("bez")),
          of(ISO, LocaleWrapper.forLanguageTag("br")),
          of(ISO, LocaleWrapper.forLanguageTag("cgg")),
          of(ISO, LocaleWrapper.forLanguageTag("dav")),
          of(ISO, LocaleWrapper.forLanguageTag("ebu")),
          of(ISO, LocaleWrapper.forLanguageTag("en-001")),
          of(ISO, LocaleWrapper.forLanguageTag("en-AE")),
          of(ISO, LocaleWrapper.forLanguageTag("en-GB")),
          of(ISO, LocaleWrapper.forLanguageTag("fr")),
          of(ISO, LocaleWrapper.forLanguageTag("ga")),
          of(ISO, LocaleWrapper.forLanguageTag("gd")),
          of(ISO, LocaleWrapper.forLanguageTag("guz")),
          of(ISO, LocaleWrapper.forLanguageTag("jmc")),
          of(ISO, LocaleWrapper.forLanguageTag("kam")),
          of(ISO, LocaleWrapper.forLanguageTag("kde")),
          of(ISO, LocaleWrapper.forLanguageTag("kea")),
          of(ISO, LocaleWrapper.forLanguageTag("ki")),
          of(ISO, LocaleWrapper.forLanguageTag("kln")),
          of(ISO, LocaleWrapper.forLanguageTag("ksb")),
          of(ISO, LocaleWrapper.forLanguageTag("kw")),
          of(ISO, LocaleWrapper.forLanguageTag("lag")),
          of(ISO, LocaleWrapper.forLanguageTag("lg")),
          of(ISO, LocaleWrapper.forLanguageTag("luo")),
          of(ISO, LocaleWrapper.forLanguageTag("luy")),
          of(ISO, LocaleWrapper.forLanguageTag("mas")),
          of(ISO, LocaleWrapper.forLanguageTag("mer")),
          of(ISO, LocaleWrapper.forLanguageTag("mgh")),
          of(ISO, LocaleWrapper.forLanguageTag("mt")),
          of(ISO, LocaleWrapper.forLanguageTag("naq")),
          of(ISO, LocaleWrapper.forLanguageTag("nd")),
          of(ISO, LocaleWrapper.forLanguageTag("nyn")),
          of(ISO, LocaleWrapper.forLanguageTag("pa-Arab")),
          of(ISO, LocaleWrapper.forLanguageTag("pcm")),
          of(ISO, LocaleWrapper.forLanguageTag("pt")),
          of(ISO, LocaleWrapper.forLanguageTag("qu")),
          of(ISO, LocaleWrapper.forLanguageTag("rof")),
          of(ISO, LocaleWrapper.forLanguageTag("rwk")),
          of(ISO, LocaleWrapper.forLanguageTag("saq")),
          of(ISO, LocaleWrapper.forLanguageTag("sbp")),
          of(ISO, LocaleWrapper.forLanguageTag("sw")),
          of(ISO, LocaleWrapper.forLanguageTag("teo")),
          of(ISO, LocaleWrapper.forLanguageTag("tzm")),
          of(ISO, LocaleWrapper.forLanguageTag("vai")),
          of(ISO, LocaleWrapper.forLanguageTag("vai-Latn")),
          of(ISO, LocaleWrapper.forLanguageTag("vi")),
          of(ISO, LocaleWrapper.forLanguageTag("vun")),
          of(ISO, LocaleWrapper.forLanguageTag("xog")),
        });
    DATE_SHORT_PATTERNS.put(
        "d.M y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("no")),
        });
    DATE_SHORT_PATTERNS.put(
        "d. MM. yy",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("sl")),
        });
    DATE_SHORT_PATTERNS.put(
        "dd. MM. y.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("hr")),
        });
    DATE_SHORT_PATTERNS.put(
        "d/M/yy G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("th")),
        });
    DATE_SHORT_PATTERNS.put(
        "d. M. y. GGGGG",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("hr")),
        });
    DATE_SHORT_PATTERNS.put(
        "d‏/M‏/y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ar")),
        });
    DATE_SHORT_PATTERNS.put(
        "MM/dd/yy",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("es-PA")),
          of(ISO, LocaleWrapper.forLanguageTag("es-PR")),
        });
    DATE_SHORT_PATTERNS.put(
        "d. M. y.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("bs")),
        });
    DATE_SHORT_PATTERNS.put(
        "Gy/M/d",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("yue")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("yue")),
          of(MINGUO, LocaleWrapper.forLanguageTag("yue")),
          of(MINGUO, LocaleWrapper.forLanguageTag("zh")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(MINGUO, LocaleWrapper.forLanguageTag("zh-Hant")),
        });
    DATE_SHORT_PATTERNS.put(
        "Gyy-MM-dd",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("yue-Hans")),
        });
    DATE_SHORT_PATTERNS.put(
        "dd/MM/yy GGGGG",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("es")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("it")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("pt")),
        });
    DATE_SHORT_PATTERNS.put(
        "d/M/y G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("pt-PT")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("pt-PT")),
          of(MINGUO, LocaleWrapper.forLanguageTag("pt-PT")),
          of(MINGUO, LocaleWrapper.forLanguageTag("th")),
        });
    DATE_SHORT_PATTERNS.put(
        "dd.MM.y GGGGG",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ro")),
        });
    DATE_SHORT_PATTERNS.put(
        "d/M/y GGGGG",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("el")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("id")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("id")),
          of(MINGUO, LocaleWrapper.forLanguageTag("id")),
        });
    DATE_SHORT_PATTERNS.put(
        "Gy/MM/dd",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ja")),
          of(MINGUO, LocaleWrapper.forLanguageTag("ja")),
        });
    DATE_SHORT_PATTERNS.put(
        "d/MM/y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("nl-BE")),
          of(ISO, LocaleWrapper.forLanguageTag("nus")),
        });
    DATE_SHORT_PATTERNS.put(
        "Gd/M/yy",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("zh-Hans-HK")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("zh-Hans-HK")),
          of(MINGUO, LocaleWrapper.forLanguageTag("zh-Hans-HK")),
          of(MINGUO, LocaleWrapper.forLanguageTag("zh-Hans-MO")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("zh-Hans-SG")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("zh-Hans-SG")),
          of(MINGUO, LocaleWrapper.forLanguageTag("zh-Hans-SG")),
        });
    DATE_SHORT_PATTERNS.put(
        "dd/MM/y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("vi")),
        });
    DATE_SHORT_PATTERNS.put(
        "Gy-M-d",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("zh")),
        });
    DATE_SHORT_PATTERNS.put(
        "d.MM.y G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("tr")),
        });
    DATE_SHORT_PATTERNS.put(
        "dd/MM/yy",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("cy")),
          of(ISO, LocaleWrapper.forLanguageTag("en-BE")),
          of(ISO, LocaleWrapper.forLanguageTag("en-BW")),
          of(ISO, LocaleWrapper.forLanguageTag("en-BZ")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IN")),
          of(ISO, LocaleWrapper.forLanguageTag("es-MX")),
          of(ISO, LocaleWrapper.forLanguageTag("fur")),
          of(ISO, LocaleWrapper.forLanguageTag("gl")),
          of(ISO, LocaleWrapper.forLanguageTag("gv")),
          of(ISO, LocaleWrapper.forLanguageTag("id")),
          of(ISO, LocaleWrapper.forLanguageTag("it")),
          of(ISO, LocaleWrapper.forLanguageTag("ms-ID")),
          of(ISO, LocaleWrapper.forLanguageTag("nnh")),
          of(ISO, LocaleWrapper.forLanguageTag("om")),
          of(ISO, LocaleWrapper.forLanguageTag("pt-PT")),
          of(ISO, LocaleWrapper.forLanguageTag("so")),
          of(ISO, LocaleWrapper.forLanguageTag("tg")),
          of(ISO, LocaleWrapper.forLanguageTag("ti")),
          of(ISO, LocaleWrapper.forLanguageTag("uz")),
          of(ISO, LocaleWrapper.forLanguageTag("uz-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("yi")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hans-SG")),
        });
    DATE_SHORT_PATTERNS.put(
        "y-MM-dd G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("sv")),
        });
    DATE_SHORT_PATTERNS.put(
        "y/MM/dd",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("en-ZA")),
          of(ISO, LocaleWrapper.forLanguageTag("ja")),
        });
    DATE_SHORT_PATTERNS.put(
        "d/MM/y GGGGG",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("ms")),
        });
    DATE_SHORT_PATTERNS.put(
        "d. M. y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ksh")), of(ISO, LocaleWrapper.forLanguageTag("sk")),
        });
    DATE_SHORT_PATTERNS.put(
        "GGGGG y-MM-dd",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("")),
          of(HIJRAH_UMALQURA, LocaleWrapper.forLanguageTag("")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("")),
          of(MINGUO, LocaleWrapper.forLanguageTag("")),
        });
    DATE_SHORT_PATTERNS.put(
        "dd/MM y",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("kkj")),
        });
    DATE_SHORT_PATTERNS.put(
        "M/d/yy G",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("bs-Cyrl")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("sr")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("sr-Latn")),
        });
    DATE_SHORT_PATTERNS.put(
        "y.MM.dd",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("mn")),
        });
    DATE_SHORT_PATTERNS.put(
        "dd.MM.yy GGGGG",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("cs")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("cs")),
          of(MINGUO, LocaleWrapper.forLanguageTag("cs")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("de")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("lb")),
        });
    DATE_SHORT_PATTERNS.put(
        "d.MM.yy",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("be")), of(ISO, LocaleWrapper.forLanguageTag("nds")),
        });
    DATE_SHORT_PATTERNS.put(
        "dd-MM-GGGGG yy",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("ee")),
        });
    DATE_SHORT_PATTERNS.put(
        "GGGGGy/M/d",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("ja")),
        });
    DATE_SHORT_PATTERNS.put(
        "dd-MM-yy",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("es-CL")),
          of(ISO, LocaleWrapper.forLanguageTag("fy")),
          of(ISO, LocaleWrapper.forLanguageTag("my")),
          of(ISO, LocaleWrapper.forLanguageTag("rm")),
          of(ISO, LocaleWrapper.forLanguageTag("te")),
        });
    DATE_SHORT_PATTERNS.put(
        "d. M. yy.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("hr-BA")),
        });
    DATE_SHORT_PATTERNS.put(
        "yy/MM/dd",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ak")),
        });
    DATE_SHORT_PATTERNS.put(
        "dd-MM-GGGGG y",
        new PatternCoordinates[] {
          of(MINGUO, LocaleWrapper.forLanguageTag("ee")),
        });
    DATE_SHORT_PATTERNS.put(
        "d.M.y G",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("no")),
          of(MINGUO, LocaleWrapper.forLanguageTag("no")),
        });
    DATE_SHORT_PATTERNS.put(
        "d/M/yy GGGGG",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ast")),
          of(MINGUO, LocaleWrapper.forLanguageTag("ast")),
        });
    DATE_SHORT_PATTERNS.put(
        "d.M.yy",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("be")),
          of(ISO, LocaleWrapper.forLanguageTag("dsb")),
          of(ISO, LocaleWrapper.forLanguageTag("hsb")),
          of(ISO, LocaleWrapper.forLanguageTag("mk")),
          of(ISO, LocaleWrapper.forLanguageTag("sq")),
        });
    DATE_SHORT_PATTERNS.put(
        "M/d/y GGGGG",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("en")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fil")),
          of(MINGUO, LocaleWrapper.forLanguageTag("fil")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("ug")),
        });
    DATE_SHORT_PATTERNS.put(
        "dd.M.y GGGGG",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("mk")),
        });
    DATE_SHORT_PATTERNS.put(
        "d.M.y GGGGG",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fi")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("fi")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("is")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("is")),
          of(MINGUO, LocaleWrapper.forLanguageTag("is")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'da' {0}",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("br")),
          of(ISO, LocaleWrapper.forLanguageTag("br")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("br")),
          of(MINGUO, LocaleWrapper.forLanguageTag("br")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'о' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("uk")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'na' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ig")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1}, 'a' 'les' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ca")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'um' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("de")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} के {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("mai")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'às' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("pt-PT")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'klo' {0}",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fi")),
          of(ISO, LocaleWrapper.forLanguageTag("fi")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{0} 'do' {1}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("gl")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'у' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("be")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'à' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("fr")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-CA")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'om' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("fy")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("nl")),
          of(ISO, LocaleWrapper.forLanguageTag("nl")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("nl")),
          of(MINGUO, LocaleWrapper.forLanguageTag("nl")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("af")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("am")),
          of(ISO, LocaleWrapper.forLanguageTag("am")),
          of(ISO, LocaleWrapper.forLanguageTag("as")),
          of(ISO, LocaleWrapper.forLanguageTag("az")),
          of(ISO, LocaleWrapper.forLanguageTag("bn")),
          of(ISO, LocaleWrapper.forLanguageTag("bs-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("ccp")),
          of(ISO, LocaleWrapper.forLanguageTag("ckb")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("cs")),
          of(ISO, LocaleWrapper.forLanguageTag("cs")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("cs")),
          of(MINGUO, LocaleWrapper.forLanguageTag("cs")),
          of(ISO, LocaleWrapper.forLanguageTag("dsb")),
          of(ISO, LocaleWrapper.forLanguageTag("dz")),
          of(ISO, LocaleWrapper.forLanguageTag("et")),
          of(ISO, LocaleWrapper.forLanguageTag("eu")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("fr")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("fy")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ga")),
          of(ISO, LocaleWrapper.forLanguageTag("ga")),
          of(ISO, LocaleWrapper.forLanguageTag("gd")),
          of(ISO, LocaleWrapper.forLanguageTag("ha")),
          of(ISO, LocaleWrapper.forLanguageTag("haw")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("hr")),
          of(ISO, LocaleWrapper.forLanguageTag("hsb")),
          of(ISO, LocaleWrapper.forLanguageTag("hu")),
          of(ISO, LocaleWrapper.forLanguageTag("id")),
          of(ISO, LocaleWrapper.forLanguageTag("ii")),
          of(ISO, LocaleWrapper.forLanguageTag("it")),
          of(ISO, LocaleWrapper.forLanguageTag("ja")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("ja")),
          of(ISO, LocaleWrapper.forLanguageTag("jgo")),
          of(ISO, LocaleWrapper.forLanguageTag("jv")),
          of(ISO, LocaleWrapper.forLanguageTag("kkj")),
          of(ISO, LocaleWrapper.forLanguageTag("kl")),
          of(ISO, LocaleWrapper.forLanguageTag("kn")),
          of(ISO, LocaleWrapper.forLanguageTag("ko")),
          of(ISO, LocaleWrapper.forLanguageTag("kok")),
          of(ISO, LocaleWrapper.forLanguageTag("ks")),
          of(ISO, LocaleWrapper.forLanguageTag("ky")),
          of(ISO, LocaleWrapper.forLanguageTag("lb")),
          of(ISO, LocaleWrapper.forLanguageTag("lrc")),
          of(ISO, LocaleWrapper.forLanguageTag("lt")),
          of(ISO, LocaleWrapper.forLanguageTag("lv")),
          of(ISO, LocaleWrapper.forLanguageTag("mg")),
          of(ISO, LocaleWrapper.forLanguageTag("mgo")),
          of(ISO, LocaleWrapper.forLanguageTag("mi")),
          of(ISO, LocaleWrapper.forLanguageTag("ml")),
          of(ISO, LocaleWrapper.forLanguageTag("mn")),
          of(ISO, LocaleWrapper.forLanguageTag("ms")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("ms")),
          of(ISO, LocaleWrapper.forLanguageTag("mt")),
          of(ISO, LocaleWrapper.forLanguageTag("my")),
          of(ISO, LocaleWrapper.forLanguageTag("ne")),
          of(ISO, LocaleWrapper.forLanguageTag("nn")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("no")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("no")),
          of(MINGUO, LocaleWrapper.forLanguageTag("no")),
          of(ISO, LocaleWrapper.forLanguageTag("om")),
          of(ISO, LocaleWrapper.forLanguageTag("pa")),
          of(ISO, LocaleWrapper.forLanguageTag("pl")),
          of(ISO, LocaleWrapper.forLanguageTag("ps")),
          of(ISO, LocaleWrapper.forLanguageTag("pt")),
          of(ISO, LocaleWrapper.forLanguageTag("qu")),
          of(ISO, LocaleWrapper.forLanguageTag("rm")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("")),
          of(ISO, LocaleWrapper.forLanguageTag("")),
          of(HIJRAH_UMALQURA, LocaleWrapper.forLanguageTag("")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("")),
          of(MINGUO, LocaleWrapper.forLanguageTag("")),
          of(ISO, LocaleWrapper.forLanguageTag("rw")),
          of(ISO, LocaleWrapper.forLanguageTag("sah")),
          of(ISO, LocaleWrapper.forLanguageTag("sat")),
          of(ISO, LocaleWrapper.forLanguageTag("sd")),
          of(ISO, LocaleWrapper.forLanguageTag("se")),
          of(ISO, LocaleWrapper.forLanguageTag("si")),
          of(ISO, LocaleWrapper.forLanguageTag("sl")),
          of(ISO, LocaleWrapper.forLanguageTag("sn")),
          of(ISO, LocaleWrapper.forLanguageTag("so")),
          of(ISO, LocaleWrapper.forLanguageTag("sr")),
          of(ISO, LocaleWrapper.forLanguageTag("sr-Latn")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("sv")),
          of(ISO, LocaleWrapper.forLanguageTag("sv")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("sv")),
          of(ISO, LocaleWrapper.forLanguageTag("sw")),
          of(ISO, LocaleWrapper.forLanguageTag("tg")),
          of(ISO, LocaleWrapper.forLanguageTag("th")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("th")),
          of(ISO, LocaleWrapper.forLanguageTag("ti")),
          of(ISO, LocaleWrapper.forLanguageTag("tk")),
          of(ISO, LocaleWrapper.forLanguageTag("tr")),
          of(ISO, LocaleWrapper.forLanguageTag("ug")),
          of(ISO, LocaleWrapper.forLanguageTag("ur")),
          of(ISO, LocaleWrapper.forLanguageTag("uz-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("xh")),
          of(ISO, LocaleWrapper.forLanguageTag("yi")),
          of(ISO, LocaleWrapper.forLanguageTag("yo")),
          of(ISO, LocaleWrapper.forLanguageTag("yue")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("yue")),
          of(ISO, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(ISO, LocaleWrapper.forLanguageTag("zgh")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("zh")),
          of(ISO, LocaleWrapper.forLanguageTag("zh")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("zh")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant-HK")),
          of(ISO, LocaleWrapper.forLanguageTag("zu")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1}، ساعت {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("fa")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} בשעה {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("he")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'pukul' {0}",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("id")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'tme' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("smn")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1},{0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("nnh")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'at' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("en")),
          of(ISO, LocaleWrapper.forLanguageTag("en-AU")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CA")),
          of(ISO, LocaleWrapper.forLanguageTag("en-GB")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IN")),
          of(ISO, LocaleWrapper.forLanguageTag("sd-Deva")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'la' {0}",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ro")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'në' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("sq")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} ’அன்று’ {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ta")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} গী {0} দা",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("mni")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1}, {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("bg")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ca")),
          of(ISO, LocaleWrapper.forLanguageTag("es")),
          of(ISO, LocaleWrapper.forLanguageTag("es-MX")),
          of(ISO, LocaleWrapper.forLanguageTag("es-US")),
          of(ISO, LocaleWrapper.forLanguageTag("hy")),
          of(ISO, LocaleWrapper.forLanguageTag("ka")),
          of(ISO, LocaleWrapper.forLanguageTag("kea")),
          of(ISO, LocaleWrapper.forLanguageTag("kk")),
          of(ISO, LocaleWrapper.forLanguageTag("lo")),
          of(ISO, LocaleWrapper.forLanguageTag("nds")),
          of(ISO, LocaleWrapper.forLanguageTag("os")),
          of(ISO, LocaleWrapper.forLanguageTag("ro")),
          of(ISO, LocaleWrapper.forLanguageTag("ru")),
          of(ISO, LocaleWrapper.forLanguageTag("sk")),
          of(ISO, LocaleWrapper.forLanguageTag("to")),
          of(ISO, LocaleWrapper.forLanguageTag("tt")),
          of(ISO, LocaleWrapper.forLanguageTag("uz")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'kl'. {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("da")),
          of(ISO, LocaleWrapper.forLanguageTag("fo")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("is")),
          of(ISO, LocaleWrapper.forLanguageTag("is")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("is")),
          of(MINGUO, LocaleWrapper.forLanguageTag("is")),
          of(ISO, LocaleWrapper.forLanguageTag("no")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} को {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("hi")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} {0}కి",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("te")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} នៅ​ម៉ោង {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("km")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'jam' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("su")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1}{0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(MINGUO, LocaleWrapper.forLanguageTag("zh-Hant")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'sa' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ceb")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} तदा {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("sa")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} એ {0} વાગ્યે",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("gu")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} - {0}",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("el")),
          of(ISO, LocaleWrapper.forLanguageTag("el")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'u' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("bs")), of(ISO, LocaleWrapper.forLanguageTag("hr")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{0} ਵਿਖੇ {1}",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("pa")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} ᎤᎾᎢ {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("chr")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} في {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ar")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'fọ' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("pcm")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} गी {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("doi")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'aig' {0}",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("gd")),
          of(MINGUO, LocaleWrapper.forLanguageTag("gd")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} रोजी {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("mr")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{0} ଠାରେ {1}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("or")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'a' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ia")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1}, 'во' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("mk")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'a' 'les' {0}",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ast")),
          of(ISO, LocaleWrapper.forLanguageTag("ast")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("ast")),
          of(MINGUO, LocaleWrapper.forLanguageTag("ast")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{0} {1}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ee")), of(ISO, LocaleWrapper.forLanguageTag("vi")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'ɣef' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("kab")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'nang' {0}",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fil")),
          of(ISO, LocaleWrapper.forLanguageTag("fil")),
          of(MINGUO, LocaleWrapper.forLanguageTag("fil")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'am' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("cy")),
        });
    DATE_TIME_FULL_PATTERNS.put(
        "{1} 'ci' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("wo")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'da' {0}",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("br")),
          of(ISO, LocaleWrapper.forLanguageTag("br")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("br")),
          of(MINGUO, LocaleWrapper.forLanguageTag("br")),
          of(ISO, LocaleWrapper.forLanguageTag("ha")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'о' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("uk")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'na' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ig")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1}, 'a' 'les' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ca")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'um' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("de")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} के {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("mai")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'às' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("pt-PT")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'klo' {0}",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fi")),
          of(ISO, LocaleWrapper.forLanguageTag("fi")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{0} 'do' {1}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("gl")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'у' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("be")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'à' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("fr")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-CA")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'om' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("fy")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("nl")),
          of(ISO, LocaleWrapper.forLanguageTag("nl")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("nl")),
          of(MINGUO, LocaleWrapper.forLanguageTag("nl")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("af")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("am")),
          of(ISO, LocaleWrapper.forLanguageTag("am")),
          of(ISO, LocaleWrapper.forLanguageTag("as")),
          of(ISO, LocaleWrapper.forLanguageTag("az")),
          of(ISO, LocaleWrapper.forLanguageTag("bn")),
          of(ISO, LocaleWrapper.forLanguageTag("bs-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("ccp")),
          of(ISO, LocaleWrapper.forLanguageTag("ckb")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("cs")),
          of(ISO, LocaleWrapper.forLanguageTag("cs")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("cs")),
          of(MINGUO, LocaleWrapper.forLanguageTag("cs")),
          of(ISO, LocaleWrapper.forLanguageTag("dsb")),
          of(ISO, LocaleWrapper.forLanguageTag("dz")),
          of(ISO, LocaleWrapper.forLanguageTag("et")),
          of(ISO, LocaleWrapper.forLanguageTag("eu")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("fr")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("fy")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ga")),
          of(ISO, LocaleWrapper.forLanguageTag("ga")),
          of(ISO, LocaleWrapper.forLanguageTag("gd")),
          of(ISO, LocaleWrapper.forLanguageTag("haw")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("hr")),
          of(ISO, LocaleWrapper.forLanguageTag("hsb")),
          of(ISO, LocaleWrapper.forLanguageTag("hu")),
          of(ISO, LocaleWrapper.forLanguageTag("id")),
          of(ISO, LocaleWrapper.forLanguageTag("ii")),
          of(ISO, LocaleWrapper.forLanguageTag("it")),
          of(ISO, LocaleWrapper.forLanguageTag("ja")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("ja")),
          of(ISO, LocaleWrapper.forLanguageTag("jgo")),
          of(ISO, LocaleWrapper.forLanguageTag("jv")),
          of(ISO, LocaleWrapper.forLanguageTag("kkj")),
          of(ISO, LocaleWrapper.forLanguageTag("kl")),
          of(ISO, LocaleWrapper.forLanguageTag("kn")),
          of(ISO, LocaleWrapper.forLanguageTag("ko")),
          of(ISO, LocaleWrapper.forLanguageTag("kok")),
          of(ISO, LocaleWrapper.forLanguageTag("ks")),
          of(ISO, LocaleWrapper.forLanguageTag("ky")),
          of(ISO, LocaleWrapper.forLanguageTag("lb")),
          of(ISO, LocaleWrapper.forLanguageTag("lrc")),
          of(ISO, LocaleWrapper.forLanguageTag("lt")),
          of(ISO, LocaleWrapper.forLanguageTag("lv")),
          of(ISO, LocaleWrapper.forLanguageTag("mg")),
          of(ISO, LocaleWrapper.forLanguageTag("mgo")),
          of(ISO, LocaleWrapper.forLanguageTag("mi")),
          of(ISO, LocaleWrapper.forLanguageTag("ml")),
          of(ISO, LocaleWrapper.forLanguageTag("mn")),
          of(ISO, LocaleWrapper.forLanguageTag("ms")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("ms")),
          of(ISO, LocaleWrapper.forLanguageTag("mt")),
          of(ISO, LocaleWrapper.forLanguageTag("my")),
          of(ISO, LocaleWrapper.forLanguageTag("ne")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("no")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("no")),
          of(MINGUO, LocaleWrapper.forLanguageTag("no")),
          of(ISO, LocaleWrapper.forLanguageTag("om")),
          of(ISO, LocaleWrapper.forLanguageTag("pa")),
          of(ISO, LocaleWrapper.forLanguageTag("pl")),
          of(ISO, LocaleWrapper.forLanguageTag("ps")),
          of(ISO, LocaleWrapper.forLanguageTag("pt")),
          of(ISO, LocaleWrapper.forLanguageTag("rm")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("")),
          of(ISO, LocaleWrapper.forLanguageTag("")),
          of(HIJRAH_UMALQURA, LocaleWrapper.forLanguageTag("")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("")),
          of(MINGUO, LocaleWrapper.forLanguageTag("")),
          of(ISO, LocaleWrapper.forLanguageTag("rw")),
          of(ISO, LocaleWrapper.forLanguageTag("sah")),
          of(ISO, LocaleWrapper.forLanguageTag("sat")),
          of(ISO, LocaleWrapper.forLanguageTag("sd")),
          of(ISO, LocaleWrapper.forLanguageTag("sd-Deva")),
          of(ISO, LocaleWrapper.forLanguageTag("se")),
          of(ISO, LocaleWrapper.forLanguageTag("si")),
          of(ISO, LocaleWrapper.forLanguageTag("sl")),
          of(ISO, LocaleWrapper.forLanguageTag("sn")),
          of(ISO, LocaleWrapper.forLanguageTag("so")),
          of(ISO, LocaleWrapper.forLanguageTag("sr")),
          of(ISO, LocaleWrapper.forLanguageTag("sr-Latn")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("sv")),
          of(ISO, LocaleWrapper.forLanguageTag("sv")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("sv")),
          of(ISO, LocaleWrapper.forLanguageTag("sw")),
          of(ISO, LocaleWrapper.forLanguageTag("tg")),
          of(ISO, LocaleWrapper.forLanguageTag("th")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("th")),
          of(ISO, LocaleWrapper.forLanguageTag("ti")),
          of(ISO, LocaleWrapper.forLanguageTag("tk")),
          of(ISO, LocaleWrapper.forLanguageTag("tr")),
          of(ISO, LocaleWrapper.forLanguageTag("ug")),
          of(ISO, LocaleWrapper.forLanguageTag("ur")),
          of(ISO, LocaleWrapper.forLanguageTag("uz-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("xh")),
          of(ISO, LocaleWrapper.forLanguageTag("yi")),
          of(ISO, LocaleWrapper.forLanguageTag("yo")),
          of(ISO, LocaleWrapper.forLanguageTag("yue")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("yue")),
          of(ISO, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(ISO, LocaleWrapper.forLanguageTag("zgh")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("zh")),
          of(ISO, LocaleWrapper.forLanguageTag("zh")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("zh")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(MINGUO, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant-HK")),
          of(ISO, LocaleWrapper.forLanguageTag("zu")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1}، ساعت {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("fa")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} בשעה {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("he")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'pukul' {0}",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("id")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'tme' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("smn")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'at' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("en")),
          of(ISO, LocaleWrapper.forLanguageTag("en-AU")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CA")),
          of(ISO, LocaleWrapper.forLanguageTag("en-GB")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IN")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'la' {0}",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ro")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'në' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("sq")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} ’அன்று’ {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ta")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} গী {0} দা",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("mni")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1}, {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("bg")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ca")),
          of(ISO, LocaleWrapper.forLanguageTag("es")),
          of(ISO, LocaleWrapper.forLanguageTag("es-MX")),
          of(ISO, LocaleWrapper.forLanguageTag("es-US")),
          of(ISO, LocaleWrapper.forLanguageTag("hy")),
          of(ISO, LocaleWrapper.forLanguageTag("ka")),
          of(ISO, LocaleWrapper.forLanguageTag("kea")),
          of(ISO, LocaleWrapper.forLanguageTag("kk")),
          of(ISO, LocaleWrapper.forLanguageTag("lo")),
          of(ISO, LocaleWrapper.forLanguageTag("nds")),
          of(ISO, LocaleWrapper.forLanguageTag("nnh")),
          of(ISO, LocaleWrapper.forLanguageTag("os")),
          of(ISO, LocaleWrapper.forLanguageTag("ro")),
          of(ISO, LocaleWrapper.forLanguageTag("ru")),
          of(ISO, LocaleWrapper.forLanguageTag("sk")),
          of(ISO, LocaleWrapper.forLanguageTag("to")),
          of(ISO, LocaleWrapper.forLanguageTag("tt")),
          of(ISO, LocaleWrapper.forLanguageTag("uz")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'kl'. {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("da")),
          of(ISO, LocaleWrapper.forLanguageTag("fo")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("is")),
          of(ISO, LocaleWrapper.forLanguageTag("is")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("is")),
          of(MINGUO, LocaleWrapper.forLanguageTag("is")),
          of(ISO, LocaleWrapper.forLanguageTag("nn")),
          of(ISO, LocaleWrapper.forLanguageTag("no")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} को {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("hi")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} {0}కి",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("te")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} នៅ​ម៉ោង {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("km")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'jam' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("su")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'sa' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ceb")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} तदा {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("sa")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} એ {0} વાગ્યે",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("gu")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} - {0}",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("el")),
          of(ISO, LocaleWrapper.forLanguageTag("el")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'u' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("bs")), of(ISO, LocaleWrapper.forLanguageTag("hr")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{0} ਵਿਖੇ {1}",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("pa")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} ᎤᎾᎢ {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("chr")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} في {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ar")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'fọ' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("pcm")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} गी {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("doi")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'aig' {0}",
        new PatternCoordinates[] {
          of(JAPANESE, LocaleWrapper.forLanguageTag("gd")),
          of(MINGUO, LocaleWrapper.forLanguageTag("gd")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} रोजी {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("mr")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{0} ଠାରେ {1}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("or")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'a' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ia")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1}, 'во' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("mk")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'a' 'les' {0}",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ast")),
          of(ISO, LocaleWrapper.forLanguageTag("ast")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("ast")),
          of(MINGUO, LocaleWrapper.forLanguageTag("ast")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{0} {1}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ee")),
          of(ISO, LocaleWrapper.forLanguageTag("qu")),
          of(ISO, LocaleWrapper.forLanguageTag("vi")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'ɣef' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("kab")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'nang' {0}",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fil")),
          of(ISO, LocaleWrapper.forLanguageTag("fil")),
          of(MINGUO, LocaleWrapper.forLanguageTag("fil")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'am' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("cy")),
        });
    DATE_TIME_LONG_PATTERNS.put(
        "{1} 'ci' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("wo")),
        });
    DATE_TIME_MEDIUM_PATTERNS.put(
        "{1}, {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ar")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ast")),
          of(ISO, LocaleWrapper.forLanguageTag("ast")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("ast")),
          of(MINGUO, LocaleWrapper.forLanguageTag("ast")),
          of(ISO, LocaleWrapper.forLanguageTag("be")),
          of(ISO, LocaleWrapper.forLanguageTag("bg")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("br")),
          of(ISO, LocaleWrapper.forLanguageTag("br")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("br")),
          of(MINGUO, LocaleWrapper.forLanguageTag("br")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ca")),
          of(ISO, LocaleWrapper.forLanguageTag("ca")),
          of(ISO, LocaleWrapper.forLanguageTag("ceb")),
          of(ISO, LocaleWrapper.forLanguageTag("chr")),
          of(ISO, LocaleWrapper.forLanguageTag("de")),
          of(ISO, LocaleWrapper.forLanguageTag("doi")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("el")),
          of(ISO, LocaleWrapper.forLanguageTag("el")),
          of(ISO, LocaleWrapper.forLanguageTag("en")),
          of(ISO, LocaleWrapper.forLanguageTag("en-AU")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CA")),
          of(ISO, LocaleWrapper.forLanguageTag("en-GB")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IN")),
          of(ISO, LocaleWrapper.forLanguageTag("es-CO")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fil")),
          of(ISO, LocaleWrapper.forLanguageTag("fil")),
          of(MINGUO, LocaleWrapper.forLanguageTag("fil")),
          of(ISO, LocaleWrapper.forLanguageTag("fo")),
          of(ISO, LocaleWrapper.forLanguageTag("fr")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-CA")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-ML")),
          of(ISO, LocaleWrapper.forLanguageTag("ha")),
          of(ISO, LocaleWrapper.forLanguageTag("he")),
          of(ISO, LocaleWrapper.forLanguageTag("hi")),
          of(ISO, LocaleWrapper.forLanguageTag("hy")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("id")),
          of(ISO, LocaleWrapper.forLanguageTag("ig")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("is")),
          of(ISO, LocaleWrapper.forLanguageTag("is")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("is")),
          of(MINGUO, LocaleWrapper.forLanguageTag("is")),
          of(ISO, LocaleWrapper.forLanguageTag("it")),
          of(ISO, LocaleWrapper.forLanguageTag("jv")),
          of(ISO, LocaleWrapper.forLanguageTag("ka")),
          of(ISO, LocaleWrapper.forLanguageTag("kab")),
          of(ISO, LocaleWrapper.forLanguageTag("kea")),
          of(ISO, LocaleWrapper.forLanguageTag("kk")),
          of(ISO, LocaleWrapper.forLanguageTag("km")),
          of(ISO, LocaleWrapper.forLanguageTag("lo")),
          of(ISO, LocaleWrapper.forLanguageTag("mai")),
          of(ISO, LocaleWrapper.forLanguageTag("mni")),
          of(ISO, LocaleWrapper.forLanguageTag("mr")),
          of(ISO, LocaleWrapper.forLanguageTag("ms")),
          of(ISO, LocaleWrapper.forLanguageTag("nds")),
          of(ISO, LocaleWrapper.forLanguageTag("ne")),
          of(ISO, LocaleWrapper.forLanguageTag("nn")),
          of(ISO, LocaleWrapper.forLanguageTag("no")),
          of(ISO, LocaleWrapper.forLanguageTag("or")),
          of(ISO, LocaleWrapper.forLanguageTag("os")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("pa")),
          of(ISO, LocaleWrapper.forLanguageTag("pa")),
          of(ISO, LocaleWrapper.forLanguageTag("pl")),
          of(ISO, LocaleWrapper.forLanguageTag("pt-PT")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ro")),
          of(ISO, LocaleWrapper.forLanguageTag("ro")),
          of(ISO, LocaleWrapper.forLanguageTag("ru")),
          of(ISO, LocaleWrapper.forLanguageTag("sa")),
          of(ISO, LocaleWrapper.forLanguageTag("sd-Deva")),
          of(ISO, LocaleWrapper.forLanguageTag("sk")),
          of(ISO, LocaleWrapper.forLanguageTag("sq")),
          of(ISO, LocaleWrapper.forLanguageTag("su")),
          of(ISO, LocaleWrapper.forLanguageTag("ta")),
          of(ISO, LocaleWrapper.forLanguageTag("to")),
          of(ISO, LocaleWrapper.forLanguageTag("tt")),
          of(ISO, LocaleWrapper.forLanguageTag("uk")),
          of(ISO, LocaleWrapper.forLanguageTag("uz")),
          of(ISO, LocaleWrapper.forLanguageTag("yi")),
        });
    DATE_TIME_MEDIUM_PATTERNS.put(
        "{1} {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("af")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("am")),
          of(ISO, LocaleWrapper.forLanguageTag("am")),
          of(ISO, LocaleWrapper.forLanguageTag("as")),
          of(ISO, LocaleWrapper.forLanguageTag("az")),
          of(ISO, LocaleWrapper.forLanguageTag("bn")),
          of(ISO, LocaleWrapper.forLanguageTag("bs")),
          of(ISO, LocaleWrapper.forLanguageTag("bs-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("ccp")),
          of(ISO, LocaleWrapper.forLanguageTag("ckb")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("cs")),
          of(ISO, LocaleWrapper.forLanguageTag("cs")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("cs")),
          of(MINGUO, LocaleWrapper.forLanguageTag("cs")),
          of(ISO, LocaleWrapper.forLanguageTag("cy")),
          of(ISO, LocaleWrapper.forLanguageTag("da")),
          of(ISO, LocaleWrapper.forLanguageTag("dsb")),
          of(ISO, LocaleWrapper.forLanguageTag("dz")),
          of(ISO, LocaleWrapper.forLanguageTag("es")),
          of(ISO, LocaleWrapper.forLanguageTag("es-419")),
          of(ISO, LocaleWrapper.forLanguageTag("es-MX")),
          of(ISO, LocaleWrapper.forLanguageTag("es-US")),
          of(ISO, LocaleWrapper.forLanguageTag("et")),
          of(ISO, LocaleWrapper.forLanguageTag("eu")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("fr")),
          of(ISO, LocaleWrapper.forLanguageTag("fy")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("fy")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ga")),
          of(ISO, LocaleWrapper.forLanguageTag("ga")),
          of(ISO, LocaleWrapper.forLanguageTag("gd")),
          of(MINGUO, LocaleWrapper.forLanguageTag("gd")),
          of(ISO, LocaleWrapper.forLanguageTag("gu")),
          of(ISO, LocaleWrapper.forLanguageTag("haw")),
          of(ISO, LocaleWrapper.forLanguageTag("hr")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("hr")),
          of(ISO, LocaleWrapper.forLanguageTag("hsb")),
          of(ISO, LocaleWrapper.forLanguageTag("hu")),
          of(ISO, LocaleWrapper.forLanguageTag("ia")),
          of(ISO, LocaleWrapper.forLanguageTag("id")),
          of(ISO, LocaleWrapper.forLanguageTag("ii")),
          of(ISO, LocaleWrapper.forLanguageTag("ja")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("ja")),
          of(ISO, LocaleWrapper.forLanguageTag("jgo")),
          of(ISO, LocaleWrapper.forLanguageTag("kkj")),
          of(ISO, LocaleWrapper.forLanguageTag("kl")),
          of(ISO, LocaleWrapper.forLanguageTag("kn")),
          of(ISO, LocaleWrapper.forLanguageTag("ko")),
          of(ISO, LocaleWrapper.forLanguageTag("kok")),
          of(ISO, LocaleWrapper.forLanguageTag("ks")),
          of(ISO, LocaleWrapper.forLanguageTag("ky")),
          of(ISO, LocaleWrapper.forLanguageTag("lb")),
          of(ISO, LocaleWrapper.forLanguageTag("lrc")),
          of(ISO, LocaleWrapper.forLanguageTag("lt")),
          of(ISO, LocaleWrapper.forLanguageTag("lv")),
          of(ISO, LocaleWrapper.forLanguageTag("mg")),
          of(ISO, LocaleWrapper.forLanguageTag("mgo")),
          of(ISO, LocaleWrapper.forLanguageTag("mi")),
          of(ISO, LocaleWrapper.forLanguageTag("ml")),
          of(ISO, LocaleWrapper.forLanguageTag("mn")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("ms")),
          of(ISO, LocaleWrapper.forLanguageTag("mt")),
          of(ISO, LocaleWrapper.forLanguageTag("my")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("nl")),
          of(ISO, LocaleWrapper.forLanguageTag("nl")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("nl")),
          of(MINGUO, LocaleWrapper.forLanguageTag("nl")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("no")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("no")),
          of(MINGUO, LocaleWrapper.forLanguageTag("no")),
          of(ISO, LocaleWrapper.forLanguageTag("om")),
          of(ISO, LocaleWrapper.forLanguageTag("pcm")),
          of(ISO, LocaleWrapper.forLanguageTag("ps")),
          of(ISO, LocaleWrapper.forLanguageTag("pt")),
          of(ISO, LocaleWrapper.forLanguageTag("qu")),
          of(ISO, LocaleWrapper.forLanguageTag("rm")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("")),
          of(ISO, LocaleWrapper.forLanguageTag("")),
          of(HIJRAH_UMALQURA, LocaleWrapper.forLanguageTag("")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("")),
          of(MINGUO, LocaleWrapper.forLanguageTag("")),
          of(ISO, LocaleWrapper.forLanguageTag("rw")),
          of(ISO, LocaleWrapper.forLanguageTag("sah")),
          of(ISO, LocaleWrapper.forLanguageTag("sat")),
          of(ISO, LocaleWrapper.forLanguageTag("sd")),
          of(ISO, LocaleWrapper.forLanguageTag("se")),
          of(ISO, LocaleWrapper.forLanguageTag("si")),
          of(ISO, LocaleWrapper.forLanguageTag("sl")),
          of(ISO, LocaleWrapper.forLanguageTag("sn")),
          of(ISO, LocaleWrapper.forLanguageTag("so")),
          of(ISO, LocaleWrapper.forLanguageTag("sr")),
          of(ISO, LocaleWrapper.forLanguageTag("sr-Latn")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("sv")),
          of(ISO, LocaleWrapper.forLanguageTag("sv")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("sv")),
          of(ISO, LocaleWrapper.forLanguageTag("sw")),
          of(ISO, LocaleWrapper.forLanguageTag("te")),
          of(ISO, LocaleWrapper.forLanguageTag("tg")),
          of(ISO, LocaleWrapper.forLanguageTag("th")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("th")),
          of(ISO, LocaleWrapper.forLanguageTag("ti")),
          of(ISO, LocaleWrapper.forLanguageTag("tk")),
          of(ISO, LocaleWrapper.forLanguageTag("tr")),
          of(ISO, LocaleWrapper.forLanguageTag("ur")),
          of(ISO, LocaleWrapper.forLanguageTag("uz-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("xh")),
          of(ISO, LocaleWrapper.forLanguageTag("yo")),
          of(ISO, LocaleWrapper.forLanguageTag("yue")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("yue")),
          of(ISO, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(ISO, LocaleWrapper.forLanguageTag("zgh")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("zh")),
          of(ISO, LocaleWrapper.forLanguageTag("zh")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("zh")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(MINGUO, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant-HK")),
          of(ISO, LocaleWrapper.forLanguageTag("zu")),
        });
    DATE_TIME_MEDIUM_PATTERNS.put(
        "{1} 'tme' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("smn")),
        });
    DATE_TIME_MEDIUM_PATTERNS.put(
        "{0}, {1}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("gl")), of(ISO, LocaleWrapper.forLanguageTag("vi")),
        });
    DATE_TIME_MEDIUM_PATTERNS.put(
        "{1}, 'во' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("mk")),
        });
    DATE_TIME_MEDIUM_PATTERNS.put(
        "{1}،‏ {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("fa")),
        });
    DATE_TIME_MEDIUM_PATTERNS.put(
        "{1} 'klo' {0}",
        new PatternCoordinates[] {
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fi")),
          of(ISO, LocaleWrapper.forLanguageTag("fi")),
        });
    DATE_TIME_MEDIUM_PATTERNS.put(
        "{0} {1}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ee")),
        });
    DATE_TIME_MEDIUM_PATTERNS.put(
        "{1} - {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("wo")),
        });
    DATE_TIME_MEDIUM_PATTERNS.put(
        "{1}، {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ug")),
        });
    DATE_TIME_SHORT_PATTERNS.put(
        "{1}, {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ar")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ast")),
          of(ISO, LocaleWrapper.forLanguageTag("be")),
          of(ISO, LocaleWrapper.forLanguageTag("bg")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ca")),
          of(ISO, LocaleWrapper.forLanguageTag("ceb")),
          of(ISO, LocaleWrapper.forLanguageTag("chr")),
          of(ISO, LocaleWrapper.forLanguageTag("de")),
          of(ISO, LocaleWrapper.forLanguageTag("doi")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("el")),
          of(ISO, LocaleWrapper.forLanguageTag("el")),
          of(ISO, LocaleWrapper.forLanguageTag("en")),
          of(ISO, LocaleWrapper.forLanguageTag("en-AU")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CA")),
          of(ISO, LocaleWrapper.forLanguageTag("en-GB")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IN")),
          of(ISO, LocaleWrapper.forLanguageTag("es-CO")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fil")),
          of(ISO, LocaleWrapper.forLanguageTag("fil")),
          of(MINGUO, LocaleWrapper.forLanguageTag("fil")),
          of(ISO, LocaleWrapper.forLanguageTag("fo")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-ML")),
          of(ISO, LocaleWrapper.forLanguageTag("ha")),
          of(ISO, LocaleWrapper.forLanguageTag("he")),
          of(ISO, LocaleWrapper.forLanguageTag("hi")),
          of(ISO, LocaleWrapper.forLanguageTag("hy")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("id")),
          of(ISO, LocaleWrapper.forLanguageTag("ig")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("is")),
          of(ISO, LocaleWrapper.forLanguageTag("is")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("is")),
          of(MINGUO, LocaleWrapper.forLanguageTag("is")),
          of(ISO, LocaleWrapper.forLanguageTag("it")),
          of(ISO, LocaleWrapper.forLanguageTag("jv")),
          of(ISO, LocaleWrapper.forLanguageTag("ka")),
          of(ISO, LocaleWrapper.forLanguageTag("kab")),
          of(ISO, LocaleWrapper.forLanguageTag("kea")),
          of(ISO, LocaleWrapper.forLanguageTag("kk")),
          of(ISO, LocaleWrapper.forLanguageTag("km")),
          of(ISO, LocaleWrapper.forLanguageTag("lo")),
          of(ISO, LocaleWrapper.forLanguageTag("mai")),
          of(ISO, LocaleWrapper.forLanguageTag("mni")),
          of(ISO, LocaleWrapper.forLanguageTag("mr")),
          of(ISO, LocaleWrapper.forLanguageTag("ms")),
          of(ISO, LocaleWrapper.forLanguageTag("nds")),
          of(ISO, LocaleWrapper.forLanguageTag("ne")),
          of(ISO, LocaleWrapper.forLanguageTag("nn")),
          of(ISO, LocaleWrapper.forLanguageTag("no")),
          of(ISO, LocaleWrapper.forLanguageTag("or")),
          of(ISO, LocaleWrapper.forLanguageTag("os")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("pa")),
          of(ISO, LocaleWrapper.forLanguageTag("pa")),
          of(ISO, LocaleWrapper.forLanguageTag("pl")),
          of(ISO, LocaleWrapper.forLanguageTag("pt-PT")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ro")),
          of(ISO, LocaleWrapper.forLanguageTag("ro")),
          of(ISO, LocaleWrapper.forLanguageTag("ru")),
          of(ISO, LocaleWrapper.forLanguageTag("sa")),
          of(ISO, LocaleWrapper.forLanguageTag("sd-Deva")),
          of(ISO, LocaleWrapper.forLanguageTag("sq")),
          of(ISO, LocaleWrapper.forLanguageTag("su")),
          of(ISO, LocaleWrapper.forLanguageTag("ta")),
          of(ISO, LocaleWrapper.forLanguageTag("tt")),
          of(ISO, LocaleWrapper.forLanguageTag("uk")),
          of(ISO, LocaleWrapper.forLanguageTag("uz")),
        });
    DATE_TIME_SHORT_PATTERNS.put(
        "{1} {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("af")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("am")),
          of(ISO, LocaleWrapper.forLanguageTag("am")),
          of(ISO, LocaleWrapper.forLanguageTag("as")),
          of(ISO, LocaleWrapper.forLanguageTag("ast")),
          of(MINGUO, LocaleWrapper.forLanguageTag("ast")),
          of(ISO, LocaleWrapper.forLanguageTag("az")),
          of(ISO, LocaleWrapper.forLanguageTag("bn")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("br")),
          of(ISO, LocaleWrapper.forLanguageTag("br")),
          of(ISO, LocaleWrapper.forLanguageTag("bs")),
          of(ISO, LocaleWrapper.forLanguageTag("bs-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("ca")),
          of(ISO, LocaleWrapper.forLanguageTag("ccp")),
          of(ISO, LocaleWrapper.forLanguageTag("ckb")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("cs")),
          of(ISO, LocaleWrapper.forLanguageTag("cs")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("cs")),
          of(MINGUO, LocaleWrapper.forLanguageTag("cs")),
          of(ISO, LocaleWrapper.forLanguageTag("cy")),
          of(ISO, LocaleWrapper.forLanguageTag("da")),
          of(ISO, LocaleWrapper.forLanguageTag("dsb")),
          of(ISO, LocaleWrapper.forLanguageTag("dz")),
          of(ISO, LocaleWrapper.forLanguageTag("es")),
          of(ISO, LocaleWrapper.forLanguageTag("es-419")),
          of(ISO, LocaleWrapper.forLanguageTag("es-MX")),
          of(ISO, LocaleWrapper.forLanguageTag("es-US")),
          of(ISO, LocaleWrapper.forLanguageTag("et")),
          of(ISO, LocaleWrapper.forLanguageTag("eu")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("fi")),
          of(ISO, LocaleWrapper.forLanguageTag("fi")),
          of(ISO, LocaleWrapper.forLanguageTag("fr")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("fr")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-CA")),
          of(ISO, LocaleWrapper.forLanguageTag("fy")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("fy")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("ga")),
          of(ISO, LocaleWrapper.forLanguageTag("ga")),
          of(ISO, LocaleWrapper.forLanguageTag("gd")),
          of(MINGUO, LocaleWrapper.forLanguageTag("gd")),
          of(ISO, LocaleWrapper.forLanguageTag("gu")),
          of(ISO, LocaleWrapper.forLanguageTag("haw")),
          of(ISO, LocaleWrapper.forLanguageTag("hr")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("hr")),
          of(ISO, LocaleWrapper.forLanguageTag("hsb")),
          of(ISO, LocaleWrapper.forLanguageTag("hu")),
          of(ISO, LocaleWrapper.forLanguageTag("ia")),
          of(ISO, LocaleWrapper.forLanguageTag("id")),
          of(ISO, LocaleWrapper.forLanguageTag("ii")),
          of(ISO, LocaleWrapper.forLanguageTag("ja")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("ja")),
          of(ISO, LocaleWrapper.forLanguageTag("jgo")),
          of(ISO, LocaleWrapper.forLanguageTag("kkj")),
          of(ISO, LocaleWrapper.forLanguageTag("kl")),
          of(ISO, LocaleWrapper.forLanguageTag("kn")),
          of(ISO, LocaleWrapper.forLanguageTag("ko")),
          of(ISO, LocaleWrapper.forLanguageTag("kok")),
          of(ISO, LocaleWrapper.forLanguageTag("ks")),
          of(ISO, LocaleWrapper.forLanguageTag("ky")),
          of(ISO, LocaleWrapper.forLanguageTag("lb")),
          of(ISO, LocaleWrapper.forLanguageTag("lrc")),
          of(ISO, LocaleWrapper.forLanguageTag("lt")),
          of(ISO, LocaleWrapper.forLanguageTag("lv")),
          of(ISO, LocaleWrapper.forLanguageTag("mg")),
          of(ISO, LocaleWrapper.forLanguageTag("mgo")),
          of(ISO, LocaleWrapper.forLanguageTag("mi")),
          of(ISO, LocaleWrapper.forLanguageTag("ml")),
          of(ISO, LocaleWrapper.forLanguageTag("mn")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("ms")),
          of(ISO, LocaleWrapper.forLanguageTag("mt")),
          of(ISO, LocaleWrapper.forLanguageTag("my")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("nl")),
          of(ISO, LocaleWrapper.forLanguageTag("nl")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("nl")),
          of(MINGUO, LocaleWrapper.forLanguageTag("nl")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("no")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("no")),
          of(MINGUO, LocaleWrapper.forLanguageTag("no")),
          of(ISO, LocaleWrapper.forLanguageTag("om")),
          of(ISO, LocaleWrapper.forLanguageTag("pcm")),
          of(ISO, LocaleWrapper.forLanguageTag("ps")),
          of(ISO, LocaleWrapper.forLanguageTag("pt")),
          of(ISO, LocaleWrapper.forLanguageTag("qu")),
          of(ISO, LocaleWrapper.forLanguageTag("rm")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("")),
          of(ISO, LocaleWrapper.forLanguageTag("")),
          of(HIJRAH_UMALQURA, LocaleWrapper.forLanguageTag("")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("")),
          of(MINGUO, LocaleWrapper.forLanguageTag("")),
          of(ISO, LocaleWrapper.forLanguageTag("rw")),
          of(ISO, LocaleWrapper.forLanguageTag("sah")),
          of(ISO, LocaleWrapper.forLanguageTag("sat")),
          of(ISO, LocaleWrapper.forLanguageTag("sd")),
          of(ISO, LocaleWrapper.forLanguageTag("se")),
          of(ISO, LocaleWrapper.forLanguageTag("si")),
          of(ISO, LocaleWrapper.forLanguageTag("sk")),
          of(ISO, LocaleWrapper.forLanguageTag("sl")),
          of(ISO, LocaleWrapper.forLanguageTag("smn")),
          of(ISO, LocaleWrapper.forLanguageTag("sn")),
          of(ISO, LocaleWrapper.forLanguageTag("so")),
          of(ISO, LocaleWrapper.forLanguageTag("sr")),
          of(ISO, LocaleWrapper.forLanguageTag("sr-Latn")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("sv")),
          of(ISO, LocaleWrapper.forLanguageTag("sv")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("sv")),
          of(ISO, LocaleWrapper.forLanguageTag("sw")),
          of(ISO, LocaleWrapper.forLanguageTag("te")),
          of(ISO, LocaleWrapper.forLanguageTag("tg")),
          of(ISO, LocaleWrapper.forLanguageTag("th")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("th")),
          of(ISO, LocaleWrapper.forLanguageTag("ti")),
          of(ISO, LocaleWrapper.forLanguageTag("tk")),
          of(ISO, LocaleWrapper.forLanguageTag("to")),
          of(ISO, LocaleWrapper.forLanguageTag("tr")),
          of(ISO, LocaleWrapper.forLanguageTag("ur")),
          of(ISO, LocaleWrapper.forLanguageTag("uz-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("xh")),
          of(ISO, LocaleWrapper.forLanguageTag("yi")),
          of(ISO, LocaleWrapper.forLanguageTag("yo")),
          of(ISO, LocaleWrapper.forLanguageTag("yue")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("yue")),
          of(ISO, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(ISO, LocaleWrapper.forLanguageTag("zgh")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("zh")),
          of(ISO, LocaleWrapper.forLanguageTag("zh")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("zh")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(MINGUO, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant-HK")),
          of(ISO, LocaleWrapper.forLanguageTag("zu")),
        });
    DATE_TIME_SHORT_PATTERNS.put(
        "{0}, {1}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("gl")), of(ISO, LocaleWrapper.forLanguageTag("vi")),
        });
    DATE_TIME_SHORT_PATTERNS.put(
        "{1}, 'во' {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("mk")),
        });
    DATE_TIME_SHORT_PATTERNS.put(
        "{1}،‏ {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("fa")),
        });
    DATE_TIME_SHORT_PATTERNS.put(
        "{0} {1}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ee")),
        });
    DATE_TIME_SHORT_PATTERNS.put(
        "{1} - {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("wo")),
        });
    DATE_TIME_SHORT_PATTERNS.put(
        "{1}، {0}",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ug")),
        });
    TIME_FULL_PATTERNS.put(
        "H:mm:ss zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ar-IL")),
          of(ISO, LocaleWrapper.forLanguageTag("cs")),
          of(ISO, LocaleWrapper.forLanguageTag("dsb")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IL")),
          of(ISO, LocaleWrapper.forLanguageTag("he")),
          of(ISO, LocaleWrapper.forLanguageTag("hsb")),
          of(ISO, LocaleWrapper.forLanguageTag("hu")),
          of(ISO, LocaleWrapper.forLanguageTag("sk")),
          of(ISO, LocaleWrapper.forLanguageTag("tt")),
        });
    TIME_FULL_PATTERNS.put(
        "ah:mm:ss [zzzz]",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("yue")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant-HK")),
        });
    TIME_FULL_PATTERNS.put(
        "H ໂມງ m ນາທີ ss ວິນາທີ zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("lo")),
        });
    TIME_FULL_PATTERNS.put(
        "zzzz HH:mm:ss",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("my")),
        });
    TIME_FULL_PATTERNS.put(
        "'kl'. HH:mm:ss zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("nn")),
        });
    TIME_FULL_PATTERNS.put(
        "h:mm:ss a zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("af-NA")),
          of(ISO, LocaleWrapper.forLanguageTag("ak")),
          of(ISO, LocaleWrapper.forLanguageTag("am")),
          of(ISO, LocaleWrapper.forLanguageTag("ar")),
          of(ISO, LocaleWrapper.forLanguageTag("bem")),
          of(ISO, LocaleWrapper.forLanguageTag("bn")),
          of(ISO, LocaleWrapper.forLanguageTag("bo")),
          of(ISO, LocaleWrapper.forLanguageTag("brx")),
          of(ISO, LocaleWrapper.forLanguageTag("ccp")),
          of(ISO, LocaleWrapper.forLanguageTag("ceb")),
          of(ISO, LocaleWrapper.forLanguageTag("chr")),
          of(ISO, LocaleWrapper.forLanguageTag("ckb")),
          of(ISO, LocaleWrapper.forLanguageTag("doi")),
          of(ISO, LocaleWrapper.forLanguageTag("el")),
          of(ISO, LocaleWrapper.forLanguageTag("en")),
          of(ISO, LocaleWrapper.forLanguageTag("en-AU")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CA")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IN")),
          of(ISO, LocaleWrapper.forLanguageTag("es-CO")),
          of(ISO, LocaleWrapper.forLanguageTag("es-DO")),
          of(ISO, LocaleWrapper.forLanguageTag("es-PA")),
          of(ISO, LocaleWrapper.forLanguageTag("es-PH")),
          of(ISO, LocaleWrapper.forLanguageTag("es-PR")),
          of(ISO, LocaleWrapper.forLanguageTag("es-US")),
          of(ISO, LocaleWrapper.forLanguageTag("es-VE")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm-GH")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm-GM")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm-LR")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm-MR")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm-SL")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Latn-GH")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Latn-GM")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Latn-LR")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Latn-MR")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Latn-SL")),
          of(ISO, LocaleWrapper.forLanguageTag("fil")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-DJ")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-DZ")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-MR")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-SY")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-TD")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-TN")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-VU")),
          of(ISO, LocaleWrapper.forLanguageTag("ha-GH")),
          of(ISO, LocaleWrapper.forLanguageTag("haw")),
          of(ISO, LocaleWrapper.forLanguageTag("hi")),
          of(ISO, LocaleWrapper.forLanguageTag("ii")),
          of(ISO, LocaleWrapper.forLanguageTag("kab")),
          of(ISO, LocaleWrapper.forLanguageTag("km")),
          of(ISO, LocaleWrapper.forLanguageTag("kok")),
          of(ISO, LocaleWrapper.forLanguageTag("ks")),
          of(ISO, LocaleWrapper.forLanguageTag("lkt")),
          of(ISO, LocaleWrapper.forLanguageTag("lrc-IQ")),
          of(ISO, LocaleWrapper.forLanguageTag("mai")),
          of(ISO, LocaleWrapper.forLanguageTag("mi")),
          of(ISO, LocaleWrapper.forLanguageTag("ml")),
          of(ISO, LocaleWrapper.forLanguageTag("mni")),
          of(ISO, LocaleWrapper.forLanguageTag("mr")),
          of(ISO, LocaleWrapper.forLanguageTag("ms")),
          of(ISO, LocaleWrapper.forLanguageTag("naq")),
          of(ISO, LocaleWrapper.forLanguageTag("ne-IN")),
          of(ISO, LocaleWrapper.forLanguageTag("om")),
          of(ISO, LocaleWrapper.forLanguageTag("or")),
          of(ISO, LocaleWrapper.forLanguageTag("pa")),
          of(ISO, LocaleWrapper.forLanguageTag("pa-Arab")),
          of(ISO, LocaleWrapper.forLanguageTag("ps-PK")),
          of(ISO, LocaleWrapper.forLanguageTag("pt-MO")),
          of(ISO, LocaleWrapper.forLanguageTag("sa")),
          of(ISO, LocaleWrapper.forLanguageTag("sat")),
          of(ISO, LocaleWrapper.forLanguageTag("sd")),
          of(ISO, LocaleWrapper.forLanguageTag("so")),
          of(ISO, LocaleWrapper.forLanguageTag("te")),
          of(ISO, LocaleWrapper.forLanguageTag("ti")),
          of(ISO, LocaleWrapper.forLanguageTag("to")),
          of(ISO, LocaleWrapper.forLanguageTag("tr-CY")),
          of(ISO, LocaleWrapper.forLanguageTag("ug")),
          of(ISO, LocaleWrapper.forLanguageTag("ur")),
          of(ISO, LocaleWrapper.forLanguageTag("vai")),
          of(ISO, LocaleWrapper.forLanguageTag("vai-Latn")),
        });
    TIME_FULL_PATTERNS.put(
        "H.mm.ss zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("en-FI")),
          of(ISO, LocaleWrapper.forLanguageTag("fi")),
          of(ISO, LocaleWrapper.forLanguageTag("smn")),
          of(ISO, LocaleWrapper.forLanguageTag("su")),
        });
    TIME_FULL_PATTERNS.put(
        "H 'h' mm 'min' ss 's' zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("fr-BE")),
        });
    TIME_FULL_PATTERNS.put(
        "H นาฬิกา mm นาที ss วินาที zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("th")),
        });
    TIME_FULL_PATTERNS.put(
        "HH.mm.ss zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("da")),
          of(ISO, LocaleWrapper.forLanguageTag("en-DK")),
          of(ISO, LocaleWrapper.forLanguageTag("id")),
          of(ISO, LocaleWrapper.forLanguageTag("kl")),
          of(ISO, LocaleWrapper.forLanguageTag("ms-ID")),
          of(ISO, LocaleWrapper.forLanguageTag("si")),
        });
    TIME_FULL_PATTERNS.put(
        "'Klock' H.mm:ss (zzzz)",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("nds")),
        });
    TIME_FULL_PATTERNS.put(
        "H:mm:ss (zzzz)",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ca")),
          of(ISO, LocaleWrapper.forLanguageTag("es")),
          of(ISO, LocaleWrapper.forLanguageTag("fa")),
          of(ISO, LocaleWrapper.forLanguageTag("ps")),
          of(ISO, LocaleWrapper.forLanguageTag("uz")),
          of(ISO, LocaleWrapper.forLanguageTag("uz-Arab")),
        });
    TIME_FULL_PATTERNS.put(
        "h:mm:ss a, zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("sq")),
        });
    TIME_FULL_PATTERNS.put(
        "HH:mm:ss zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("af")),
          of(ISO, LocaleWrapper.forLanguageTag("agq")),
          of(ISO, LocaleWrapper.forLanguageTag("ar-KM")),
          of(ISO, LocaleWrapper.forLanguageTag("ar-MA")),
          of(ISO, LocaleWrapper.forLanguageTag("asa")),
          of(ISO, LocaleWrapper.forLanguageTag("ast")),
          of(ISO, LocaleWrapper.forLanguageTag("az")),
          of(ISO, LocaleWrapper.forLanguageTag("az-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("bas")),
          of(ISO, LocaleWrapper.forLanguageTag("bez")),
          of(ISO, LocaleWrapper.forLanguageTag("bm")),
          of(ISO, LocaleWrapper.forLanguageTag("br")),
          of(ISO, LocaleWrapper.forLanguageTag("bs")),
          of(ISO, LocaleWrapper.forLanguageTag("bs-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("cgg")),
          of(ISO, LocaleWrapper.forLanguageTag("ckb-IR")),
          of(ISO, LocaleWrapper.forLanguageTag("cy")),
          of(ISO, LocaleWrapper.forLanguageTag("dav")),
          of(ISO, LocaleWrapper.forLanguageTag("de")),
          of(ISO, LocaleWrapper.forLanguageTag("dje")),
          of(ISO, LocaleWrapper.forLanguageTag("dua")),
          of(ISO, LocaleWrapper.forLanguageTag("dyo")),
          of(ISO, LocaleWrapper.forLanguageTag("ebu")),
          of(ISO, LocaleWrapper.forLanguageTag("ee-TG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-150")),
          of(ISO, LocaleWrapper.forLanguageTag("en-AI")),
          of(ISO, LocaleWrapper.forLanguageTag("en-BI")),
          of(ISO, LocaleWrapper.forLanguageTag("en-BW")),
          of(ISO, LocaleWrapper.forLanguageTag("en-BZ")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CC")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CK")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CM")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CX")),
          of(ISO, LocaleWrapper.forLanguageTag("en-DG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-FK")),
          of(ISO, LocaleWrapper.forLanguageTag("en-GB")),
          of(ISO, LocaleWrapper.forLanguageTag("en-GG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-GI")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IE")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IM")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IO")),
          of(ISO, LocaleWrapper.forLanguageTag("en-JE")),
          of(ISO, LocaleWrapper.forLanguageTag("en-KE")),
          of(ISO, LocaleWrapper.forLanguageTag("en-MG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-MS")),
          of(ISO, LocaleWrapper.forLanguageTag("en-MT")),
          of(ISO, LocaleWrapper.forLanguageTag("en-MU")),
          of(ISO, LocaleWrapper.forLanguageTag("en-NF")),
          of(ISO, LocaleWrapper.forLanguageTag("en-NG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-NR")),
          of(ISO, LocaleWrapper.forLanguageTag("en-NU")),
          of(ISO, LocaleWrapper.forLanguageTag("en-PN")),
          of(ISO, LocaleWrapper.forLanguageTag("en-RW")),
          of(ISO, LocaleWrapper.forLanguageTag("en-SC")),
          of(ISO, LocaleWrapper.forLanguageTag("en-SH")),
          of(ISO, LocaleWrapper.forLanguageTag("en-SX")),
          of(ISO, LocaleWrapper.forLanguageTag("en-TK")),
          of(ISO, LocaleWrapper.forLanguageTag("en-TV")),
          of(ISO, LocaleWrapper.forLanguageTag("en-TZ")),
          of(ISO, LocaleWrapper.forLanguageTag("en-UG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-ZA")),
          of(ISO, LocaleWrapper.forLanguageTag("en-ZW")),
          of(ISO, LocaleWrapper.forLanguageTag("es-419")),
          of(ISO, LocaleWrapper.forLanguageTag("es-MX")),
          of(ISO, LocaleWrapper.forLanguageTag("et")),
          of(ISO, LocaleWrapper.forLanguageTag("ewo")),
          of(ISO, LocaleWrapper.forLanguageTag("ff")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm")),
          of(ISO, LocaleWrapper.forLanguageTag("fo")),
          of(ISO, LocaleWrapper.forLanguageTag("fr")),
          of(ISO, LocaleWrapper.forLanguageTag("fur")),
          of(ISO, LocaleWrapper.forLanguageTag("fy")),
          of(ISO, LocaleWrapper.forLanguageTag("ga")),
          of(ISO, LocaleWrapper.forLanguageTag("gd")),
          of(ISO, LocaleWrapper.forLanguageTag("gl")),
          of(ISO, LocaleWrapper.forLanguageTag("gsw")),
          of(ISO, LocaleWrapper.forLanguageTag("guz")),
          of(ISO, LocaleWrapper.forLanguageTag("gv")),
          of(ISO, LocaleWrapper.forLanguageTag("ha")),
          of(ISO, LocaleWrapper.forLanguageTag("hy")),
          of(ISO, LocaleWrapper.forLanguageTag("ia")),
          of(ISO, LocaleWrapper.forLanguageTag("ig")),
          of(ISO, LocaleWrapper.forLanguageTag("is")),
          of(ISO, LocaleWrapper.forLanguageTag("it")),
          of(ISO, LocaleWrapper.forLanguageTag("jgo")),
          of(ISO, LocaleWrapper.forLanguageTag("jmc")),
          of(ISO, LocaleWrapper.forLanguageTag("jv")),
          of(ISO, LocaleWrapper.forLanguageTag("ka")),
          of(ISO, LocaleWrapper.forLanguageTag("kam")),
          of(ISO, LocaleWrapper.forLanguageTag("kde")),
          of(ISO, LocaleWrapper.forLanguageTag("kea")),
          of(ISO, LocaleWrapper.forLanguageTag("khq")),
          of(ISO, LocaleWrapper.forLanguageTag("ki")),
          of(ISO, LocaleWrapper.forLanguageTag("kk")),
          of(ISO, LocaleWrapper.forLanguageTag("kln")),
          of(ISO, LocaleWrapper.forLanguageTag("ksb")),
          of(ISO, LocaleWrapper.forLanguageTag("ksf")),
          of(ISO, LocaleWrapper.forLanguageTag("ksh")),
          of(ISO, LocaleWrapper.forLanguageTag("kw")),
          of(ISO, LocaleWrapper.forLanguageTag("ky")),
          of(ISO, LocaleWrapper.forLanguageTag("lag")),
          of(ISO, LocaleWrapper.forLanguageTag("lb")),
          of(ISO, LocaleWrapper.forLanguageTag("lg")),
          of(ISO, LocaleWrapper.forLanguageTag("ln")),
          of(ISO, LocaleWrapper.forLanguageTag("lrc")),
          of(ISO, LocaleWrapper.forLanguageTag("lt")),
          of(ISO, LocaleWrapper.forLanguageTag("lu")),
          of(ISO, LocaleWrapper.forLanguageTag("luo")),
          of(ISO, LocaleWrapper.forLanguageTag("luy")),
          of(ISO, LocaleWrapper.forLanguageTag("lv")),
          of(ISO, LocaleWrapper.forLanguageTag("mas")),
          of(ISO, LocaleWrapper.forLanguageTag("mer")),
          of(ISO, LocaleWrapper.forLanguageTag("mfe")),
          of(ISO, LocaleWrapper.forLanguageTag("mg")),
          of(ISO, LocaleWrapper.forLanguageTag("mgh")),
          of(ISO, LocaleWrapper.forLanguageTag("mgo")),
          of(ISO, LocaleWrapper.forLanguageTag("mk")),
          of(ISO, LocaleWrapper.forLanguageTag("mt")),
          of(ISO, LocaleWrapper.forLanguageTag("mua")),
          of(ISO, LocaleWrapper.forLanguageTag("nd")),
          of(ISO, LocaleWrapper.forLanguageTag("ne")),
          of(ISO, LocaleWrapper.forLanguageTag("nl")),
          of(ISO, LocaleWrapper.forLanguageTag("nmg")),
          of(ISO, LocaleWrapper.forLanguageTag("no")),
          of(ISO, LocaleWrapper.forLanguageTag("nyn")),
          of(ISO, LocaleWrapper.forLanguageTag("om-KE")),
          of(ISO, LocaleWrapper.forLanguageTag("os")),
          of(ISO, LocaleWrapper.forLanguageTag("pcm")),
          of(ISO, LocaleWrapper.forLanguageTag("pl")),
          of(ISO, LocaleWrapper.forLanguageTag("pt")),
          of(ISO, LocaleWrapper.forLanguageTag("pt-PT")),
          of(ISO, LocaleWrapper.forLanguageTag("qu")),
          of(ISO, LocaleWrapper.forLanguageTag("rm")),
          of(ISO, LocaleWrapper.forLanguageTag("rn")),
          of(ISO, LocaleWrapper.forLanguageTag("ro")),
          of(ISO, LocaleWrapper.forLanguageTag("rof")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("")),
          of(ISO, LocaleWrapper.forLanguageTag("")),
          of(HIJRAH_UMALQURA, LocaleWrapper.forLanguageTag("")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("")),
          of(MINGUO, LocaleWrapper.forLanguageTag("")),
          of(ISO, LocaleWrapper.forLanguageTag("ru")),
          of(ISO, LocaleWrapper.forLanguageTag("rw")),
          of(ISO, LocaleWrapper.forLanguageTag("rwk")),
          of(ISO, LocaleWrapper.forLanguageTag("sah")),
          of(ISO, LocaleWrapper.forLanguageTag("saq")),
          of(ISO, LocaleWrapper.forLanguageTag("sbp")),
          of(ISO, LocaleWrapper.forLanguageTag("se")),
          of(ISO, LocaleWrapper.forLanguageTag("seh")),
          of(ISO, LocaleWrapper.forLanguageTag("ses")),
          of(ISO, LocaleWrapper.forLanguageTag("sg")),
          of(ISO, LocaleWrapper.forLanguageTag("sl")),
          of(ISO, LocaleWrapper.forLanguageTag("sn")),
          of(ISO, LocaleWrapper.forLanguageTag("so-KE")),
          of(ISO, LocaleWrapper.forLanguageTag("sq-MK")),
          of(ISO, LocaleWrapper.forLanguageTag("sq-XK")),
          of(ISO, LocaleWrapper.forLanguageTag("sr")),
          of(ISO, LocaleWrapper.forLanguageTag("sr-Latn")),
          of(ISO, LocaleWrapper.forLanguageTag("sv")),
          of(ISO, LocaleWrapper.forLanguageTag("sw")),
          of(ISO, LocaleWrapper.forLanguageTag("ta-LK")),
          of(ISO, LocaleWrapper.forLanguageTag("teo")),
          of(ISO, LocaleWrapper.forLanguageTag("tg")),
          of(ISO, LocaleWrapper.forLanguageTag("tk")),
          of(ISO, LocaleWrapper.forLanguageTag("tr")),
          of(ISO, LocaleWrapper.forLanguageTag("twq")),
          of(ISO, LocaleWrapper.forLanguageTag("uk")),
          of(ISO, LocaleWrapper.forLanguageTag("vi")),
          of(ISO, LocaleWrapper.forLanguageTag("vun")),
          of(ISO, LocaleWrapper.forLanguageTag("wo")),
          of(ISO, LocaleWrapper.forLanguageTag("xh")),
          of(ISO, LocaleWrapper.forLanguageTag("xog")),
          of(ISO, LocaleWrapper.forLanguageTag("yav")),
          of(ISO, LocaleWrapper.forLanguageTag("yi")),
          of(ISO, LocaleWrapper.forLanguageTag("yo")),
          of(ISO, LocaleWrapper.forLanguageTag("zgh")),
          of(ISO, LocaleWrapper.forLanguageTag("zu")),
        });
    TIME_FULL_PATTERNS.put(
        "H-'a' 'horo' 'kaj' m:ss zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("eo")),
        });
    TIME_FULL_PATTERNS.put(
        "HH.mm:ss 'h' zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("fr-CH")),
        });
    TIME_FULL_PATTERNS.put(
        "a h시 m분 s초 zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ko")),
        });
    TIME_FULL_PATTERNS.put(
        "a h.mm.ss zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("as")),
        });
    TIME_FULL_PATTERNS.put(
        "ཆུ་ཚོད་ h སྐར་མ་ mm:ss a zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("dz")),
        });
    TIME_FULL_PATTERNS.put(
        "HH:mm:ss (zzzz)",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("eu")),
          of(ISO, LocaleWrapper.forLanguageTag("hr")),
          of(ISO, LocaleWrapper.forLanguageTag("mn")),
          of(ISO, LocaleWrapper.forLanguageTag("uz-Cyrl")),
        });
    TIME_FULL_PATTERNS.put(
        "a 'ga' h:mm:ss zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ee")),
        });
    TIME_FULL_PATTERNS.put(
        "zzzz ah:mm:ss",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(ISO, LocaleWrapper.forLanguageTag("zh")),
        });
    TIME_FULL_PATTERNS.put(
        "a h:mm:ss zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("sd-Deva")),
          of(ISO, LocaleWrapper.forLanguageTag("ta")),
        });
    TIME_FULL_PATTERNS.put(
        "HH:mm:ss, zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("be")),
        });
    TIME_FULL_PATTERNS.put(
        "H:mm:ss 'ч'. zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("bg")),
        });
    TIME_FULL_PATTERNS.put(
        "hh:mm:ss a zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("gu")), of(ISO, LocaleWrapper.forLanguageTag("kn")),
        });
    TIME_FULL_PATTERNS.put(
        "zzzz h:mm:ss a",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("nus")),
        });
    TIME_FULL_PATTERNS.put(
        "HH 'h' mm 'min' ss 's' zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("fr-CA")),
        });
    TIME_FULL_PATTERNS.put(
        "H時mm分ss秒 zzzz",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ja")),
        });
    TIME_LONG_PATTERNS.put(
        "HH:mm:ss z",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("af")),
          of(ISO, LocaleWrapper.forLanguageTag("agq")),
          of(ISO, LocaleWrapper.forLanguageTag("ar-KM")),
          of(ISO, LocaleWrapper.forLanguageTag("ar-MA")),
          of(ISO, LocaleWrapper.forLanguageTag("asa")),
          of(ISO, LocaleWrapper.forLanguageTag("ast")),
          of(ISO, LocaleWrapper.forLanguageTag("az")),
          of(ISO, LocaleWrapper.forLanguageTag("az-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("bas")),
          of(ISO, LocaleWrapper.forLanguageTag("be")),
          of(ISO, LocaleWrapper.forLanguageTag("bez")),
          of(ISO, LocaleWrapper.forLanguageTag("bm")),
          of(ISO, LocaleWrapper.forLanguageTag("br")),
          of(ISO, LocaleWrapper.forLanguageTag("bs")),
          of(ISO, LocaleWrapper.forLanguageTag("bs-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("cgg")),
          of(ISO, LocaleWrapper.forLanguageTag("ckb-IR")),
          of(ISO, LocaleWrapper.forLanguageTag("cy")),
          of(ISO, LocaleWrapper.forLanguageTag("dav")),
          of(ISO, LocaleWrapper.forLanguageTag("de")),
          of(ISO, LocaleWrapper.forLanguageTag("dje")),
          of(ISO, LocaleWrapper.forLanguageTag("dua")),
          of(ISO, LocaleWrapper.forLanguageTag("dyo")),
          of(ISO, LocaleWrapper.forLanguageTag("ebu")),
          of(ISO, LocaleWrapper.forLanguageTag("ee-TG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-150")),
          of(ISO, LocaleWrapper.forLanguageTag("en-AI")),
          of(ISO, LocaleWrapper.forLanguageTag("en-BI")),
          of(ISO, LocaleWrapper.forLanguageTag("en-BW")),
          of(ISO, LocaleWrapper.forLanguageTag("en-BZ")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CC")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CK")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CM")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CX")),
          of(ISO, LocaleWrapper.forLanguageTag("en-DG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-FK")),
          of(ISO, LocaleWrapper.forLanguageTag("en-GB")),
          of(ISO, LocaleWrapper.forLanguageTag("en-GG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-GI")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IE")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IM")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IO")),
          of(ISO, LocaleWrapper.forLanguageTag("en-JE")),
          of(ISO, LocaleWrapper.forLanguageTag("en-KE")),
          of(ISO, LocaleWrapper.forLanguageTag("en-MG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-MS")),
          of(ISO, LocaleWrapper.forLanguageTag("en-MT")),
          of(ISO, LocaleWrapper.forLanguageTag("en-MU")),
          of(ISO, LocaleWrapper.forLanguageTag("en-NF")),
          of(ISO, LocaleWrapper.forLanguageTag("en-NG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-NR")),
          of(ISO, LocaleWrapper.forLanguageTag("en-NU")),
          of(ISO, LocaleWrapper.forLanguageTag("en-PN")),
          of(ISO, LocaleWrapper.forLanguageTag("en-RW")),
          of(ISO, LocaleWrapper.forLanguageTag("en-SC")),
          of(ISO, LocaleWrapper.forLanguageTag("en-SH")),
          of(ISO, LocaleWrapper.forLanguageTag("en-SX")),
          of(ISO, LocaleWrapper.forLanguageTag("en-TK")),
          of(ISO, LocaleWrapper.forLanguageTag("en-TV")),
          of(ISO, LocaleWrapper.forLanguageTag("en-TZ")),
          of(ISO, LocaleWrapper.forLanguageTag("en-UG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-ZA")),
          of(ISO, LocaleWrapper.forLanguageTag("en-ZW")),
          of(ISO, LocaleWrapper.forLanguageTag("eo")),
          of(ISO, LocaleWrapper.forLanguageTag("es-419")),
          of(ISO, LocaleWrapper.forLanguageTag("es-MX")),
          of(ISO, LocaleWrapper.forLanguageTag("et")),
          of(ISO, LocaleWrapper.forLanguageTag("ewo")),
          of(ISO, LocaleWrapper.forLanguageTag("ff")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm")),
          of(ISO, LocaleWrapper.forLanguageTag("fo")),
          of(ISO, LocaleWrapper.forLanguageTag("fr")),
          of(ISO, LocaleWrapper.forLanguageTag("fur")),
          of(ISO, LocaleWrapper.forLanguageTag("fy")),
          of(ISO, LocaleWrapper.forLanguageTag("ga")),
          of(ISO, LocaleWrapper.forLanguageTag("gd")),
          of(ISO, LocaleWrapper.forLanguageTag("gl")),
          of(ISO, LocaleWrapper.forLanguageTag("gsw")),
          of(ISO, LocaleWrapper.forLanguageTag("guz")),
          of(ISO, LocaleWrapper.forLanguageTag("gv")),
          of(ISO, LocaleWrapper.forLanguageTag("ha")),
          of(ISO, LocaleWrapper.forLanguageTag("hr")),
          of(ISO, LocaleWrapper.forLanguageTag("hy")),
          of(ISO, LocaleWrapper.forLanguageTag("ia")),
          of(ISO, LocaleWrapper.forLanguageTag("ig")),
          of(ISO, LocaleWrapper.forLanguageTag("is")),
          of(ISO, LocaleWrapper.forLanguageTag("it")),
          of(ISO, LocaleWrapper.forLanguageTag("jgo")),
          of(ISO, LocaleWrapper.forLanguageTag("jmc")),
          of(ISO, LocaleWrapper.forLanguageTag("jv")),
          of(ISO, LocaleWrapper.forLanguageTag("ka")),
          of(ISO, LocaleWrapper.forLanguageTag("kam")),
          of(ISO, LocaleWrapper.forLanguageTag("kde")),
          of(ISO, LocaleWrapper.forLanguageTag("kea")),
          of(ISO, LocaleWrapper.forLanguageTag("khq")),
          of(ISO, LocaleWrapper.forLanguageTag("ki")),
          of(ISO, LocaleWrapper.forLanguageTag("kk")),
          of(ISO, LocaleWrapper.forLanguageTag("kln")),
          of(ISO, LocaleWrapper.forLanguageTag("ksb")),
          of(ISO, LocaleWrapper.forLanguageTag("ksf")),
          of(ISO, LocaleWrapper.forLanguageTag("ksh")),
          of(ISO, LocaleWrapper.forLanguageTag("kw")),
          of(ISO, LocaleWrapper.forLanguageTag("ky")),
          of(ISO, LocaleWrapper.forLanguageTag("lag")),
          of(ISO, LocaleWrapper.forLanguageTag("lb")),
          of(ISO, LocaleWrapper.forLanguageTag("lg")),
          of(ISO, LocaleWrapper.forLanguageTag("ln")),
          of(ISO, LocaleWrapper.forLanguageTag("lrc")),
          of(ISO, LocaleWrapper.forLanguageTag("lt")),
          of(ISO, LocaleWrapper.forLanguageTag("lu")),
          of(ISO, LocaleWrapper.forLanguageTag("luo")),
          of(ISO, LocaleWrapper.forLanguageTag("luy")),
          of(ISO, LocaleWrapper.forLanguageTag("lv")),
          of(ISO, LocaleWrapper.forLanguageTag("mas")),
          of(ISO, LocaleWrapper.forLanguageTag("mer")),
          of(ISO, LocaleWrapper.forLanguageTag("mfe")),
          of(ISO, LocaleWrapper.forLanguageTag("mg")),
          of(ISO, LocaleWrapper.forLanguageTag("mgh")),
          of(ISO, LocaleWrapper.forLanguageTag("mgo")),
          of(ISO, LocaleWrapper.forLanguageTag("mk")),
          of(ISO, LocaleWrapper.forLanguageTag("mt")),
          of(ISO, LocaleWrapper.forLanguageTag("mua")),
          of(ISO, LocaleWrapper.forLanguageTag("nd")),
          of(ISO, LocaleWrapper.forLanguageTag("ne")),
          of(ISO, LocaleWrapper.forLanguageTag("nl")),
          of(ISO, LocaleWrapper.forLanguageTag("nmg")),
          of(ISO, LocaleWrapper.forLanguageTag("nn")),
          of(ISO, LocaleWrapper.forLanguageTag("no")),
          of(ISO, LocaleWrapper.forLanguageTag("nyn")),
          of(ISO, LocaleWrapper.forLanguageTag("om-KE")),
          of(ISO, LocaleWrapper.forLanguageTag("os")),
          of(ISO, LocaleWrapper.forLanguageTag("pl")),
          of(ISO, LocaleWrapper.forLanguageTag("pt")),
          of(ISO, LocaleWrapper.forLanguageTag("pt-PT")),
          of(ISO, LocaleWrapper.forLanguageTag("qu")),
          of(ISO, LocaleWrapper.forLanguageTag("rm")),
          of(ISO, LocaleWrapper.forLanguageTag("rn")),
          of(ISO, LocaleWrapper.forLanguageTag("ro")),
          of(ISO, LocaleWrapper.forLanguageTag("rof")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("")),
          of(ISO, LocaleWrapper.forLanguageTag("")),
          of(HIJRAH_UMALQURA, LocaleWrapper.forLanguageTag("")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("")),
          of(MINGUO, LocaleWrapper.forLanguageTag("")),
          of(ISO, LocaleWrapper.forLanguageTag("ru")),
          of(ISO, LocaleWrapper.forLanguageTag("rw")),
          of(ISO, LocaleWrapper.forLanguageTag("rwk")),
          of(ISO, LocaleWrapper.forLanguageTag("sah")),
          of(ISO, LocaleWrapper.forLanguageTag("saq")),
          of(ISO, LocaleWrapper.forLanguageTag("sbp")),
          of(ISO, LocaleWrapper.forLanguageTag("se")),
          of(ISO, LocaleWrapper.forLanguageTag("seh")),
          of(ISO, LocaleWrapper.forLanguageTag("ses")),
          of(ISO, LocaleWrapper.forLanguageTag("sg")),
          of(ISO, LocaleWrapper.forLanguageTag("sl")),
          of(ISO, LocaleWrapper.forLanguageTag("sn")),
          of(ISO, LocaleWrapper.forLanguageTag("so-KE")),
          of(ISO, LocaleWrapper.forLanguageTag("sq-MK")),
          of(ISO, LocaleWrapper.forLanguageTag("sq-XK")),
          of(ISO, LocaleWrapper.forLanguageTag("sr")),
          of(ISO, LocaleWrapper.forLanguageTag("sr-Latn")),
          of(ISO, LocaleWrapper.forLanguageTag("sv")),
          of(ISO, LocaleWrapper.forLanguageTag("sw")),
          of(ISO, LocaleWrapper.forLanguageTag("ta-LK")),
          of(ISO, LocaleWrapper.forLanguageTag("teo")),
          of(ISO, LocaleWrapper.forLanguageTag("tg")),
          of(ISO, LocaleWrapper.forLanguageTag("tk")),
          of(ISO, LocaleWrapper.forLanguageTag("tr")),
          of(ISO, LocaleWrapper.forLanguageTag("twq")),
          of(ISO, LocaleWrapper.forLanguageTag("uk")),
          of(ISO, LocaleWrapper.forLanguageTag("vi")),
          of(ISO, LocaleWrapper.forLanguageTag("vun")),
          of(ISO, LocaleWrapper.forLanguageTag("wo")),
          of(ISO, LocaleWrapper.forLanguageTag("xh")),
          of(ISO, LocaleWrapper.forLanguageTag("xog")),
          of(ISO, LocaleWrapper.forLanguageTag("yav")),
          of(ISO, LocaleWrapper.forLanguageTag("yi")),
          of(ISO, LocaleWrapper.forLanguageTag("zgh")),
          of(ISO, LocaleWrapper.forLanguageTag("zu")),
        });
    TIME_LONG_PATTERNS.put(
        "H ໂມງ m ນາທີ ss ວິນາທີ z",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("lo")),
        });
    TIME_LONG_PATTERNS.put(
        "'Klock' H.mm:ss (z)",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("nds")),
        });
    TIME_LONG_PATTERNS.put(
        "z HH:mm:ss",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("my")),
        });
    TIME_LONG_PATTERNS.put(
        "z h:mm:ss a",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("nus")),
        });
    TIME_LONG_PATTERNS.put(
        "HH:mm:ss (z)",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("eu")),
          of(ISO, LocaleWrapper.forLanguageTag("mn")),
          of(ISO, LocaleWrapper.forLanguageTag("uz-Cyrl")),
        });
    TIME_LONG_PATTERNS.put(
        "HH 'h' mm 'min' ss 's' z",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("fr-CA")),
        });
    TIME_LONG_PATTERNS.put(
        "a 'ga' h:mm:ss z",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ee")),
        });
    TIME_LONG_PATTERNS.put(
        "ah:mm:ss [z]",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("yue")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant-HK")),
        });
    TIME_LONG_PATTERNS.put(
        "H:mm:ss z",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ar-IL")),
          of(ISO, LocaleWrapper.forLanguageTag("ca")),
          of(ISO, LocaleWrapper.forLanguageTag("cs")),
          of(ISO, LocaleWrapper.forLanguageTag("dsb")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IL")),
          of(ISO, LocaleWrapper.forLanguageTag("es")),
          of(ISO, LocaleWrapper.forLanguageTag("he")),
          of(ISO, LocaleWrapper.forLanguageTag("hsb")),
          of(ISO, LocaleWrapper.forLanguageTag("hu")),
          of(ISO, LocaleWrapper.forLanguageTag("ja")),
          of(ISO, LocaleWrapper.forLanguageTag("pcm")),
          of(ISO, LocaleWrapper.forLanguageTag("sk")),
          of(ISO, LocaleWrapper.forLanguageTag("tt")),
          of(ISO, LocaleWrapper.forLanguageTag("yo")),
        });
    TIME_LONG_PATTERNS.put(
        "H:mm:ss 'ч'. z",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("bg")),
        });
    TIME_LONG_PATTERNS.put(
        "a h:mm:ss z",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("sd-Deva")),
          of(ISO, LocaleWrapper.forLanguageTag("ta")),
        });
    TIME_LONG_PATTERNS.put(
        "H นาฬิกา mm นาที ss วินาที z",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("th")),
        });
    TIME_LONG_PATTERNS.put(
        "a h시 m분 s초 z",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ko")),
        });
    TIME_LONG_PATTERNS.put(
        "HH.mm.ss z",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("da")),
          of(ISO, LocaleWrapper.forLanguageTag("en-DK")),
          of(ISO, LocaleWrapper.forLanguageTag("id")),
          of(ISO, LocaleWrapper.forLanguageTag("kl")),
          of(ISO, LocaleWrapper.forLanguageTag("ms-ID")),
          of(ISO, LocaleWrapper.forLanguageTag("si")),
        });
    TIME_LONG_PATTERNS.put(
        "H.mm.ss z",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("en-FI")),
          of(ISO, LocaleWrapper.forLanguageTag("fi")),
          of(ISO, LocaleWrapper.forLanguageTag("smn")),
          of(ISO, LocaleWrapper.forLanguageTag("su")),
        });
    TIME_LONG_PATTERNS.put(
        "h:mm:ss a z",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("af-NA")),
          of(ISO, LocaleWrapper.forLanguageTag("ak")),
          of(ISO, LocaleWrapper.forLanguageTag("am")),
          of(ISO, LocaleWrapper.forLanguageTag("ar")),
          of(ISO, LocaleWrapper.forLanguageTag("bem")),
          of(ISO, LocaleWrapper.forLanguageTag("bn")),
          of(ISO, LocaleWrapper.forLanguageTag("bo")),
          of(ISO, LocaleWrapper.forLanguageTag("brx")),
          of(ISO, LocaleWrapper.forLanguageTag("ccp")),
          of(ISO, LocaleWrapper.forLanguageTag("ceb")),
          of(ISO, LocaleWrapper.forLanguageTag("chr")),
          of(ISO, LocaleWrapper.forLanguageTag("ckb")),
          of(ISO, LocaleWrapper.forLanguageTag("doi")),
          of(ISO, LocaleWrapper.forLanguageTag("el")),
          of(ISO, LocaleWrapper.forLanguageTag("en")),
          of(ISO, LocaleWrapper.forLanguageTag("en-AU")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CA")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IN")),
          of(ISO, LocaleWrapper.forLanguageTag("es-CO")),
          of(ISO, LocaleWrapper.forLanguageTag("es-DO")),
          of(ISO, LocaleWrapper.forLanguageTag("es-PA")),
          of(ISO, LocaleWrapper.forLanguageTag("es-PH")),
          of(ISO, LocaleWrapper.forLanguageTag("es-PR")),
          of(ISO, LocaleWrapper.forLanguageTag("es-US")),
          of(ISO, LocaleWrapper.forLanguageTag("es-VE")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm-GH")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm-GM")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm-LR")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm-MR")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm-SL")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Latn-GH")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Latn-GM")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Latn-LR")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Latn-MR")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Latn-SL")),
          of(ISO, LocaleWrapper.forLanguageTag("fil")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-DJ")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-DZ")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-MR")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-SY")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-TD")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-TN")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-VU")),
          of(ISO, LocaleWrapper.forLanguageTag("ha-GH")),
          of(ISO, LocaleWrapper.forLanguageTag("haw")),
          of(ISO, LocaleWrapper.forLanguageTag("hi")),
          of(ISO, LocaleWrapper.forLanguageTag("ii")),
          of(ISO, LocaleWrapper.forLanguageTag("kab")),
          of(ISO, LocaleWrapper.forLanguageTag("km")),
          of(ISO, LocaleWrapper.forLanguageTag("kok")),
          of(ISO, LocaleWrapper.forLanguageTag("ks")),
          of(ISO, LocaleWrapper.forLanguageTag("lkt")),
          of(ISO, LocaleWrapper.forLanguageTag("lrc-IQ")),
          of(ISO, LocaleWrapper.forLanguageTag("mai")),
          of(ISO, LocaleWrapper.forLanguageTag("mi")),
          of(ISO, LocaleWrapper.forLanguageTag("ml")),
          of(ISO, LocaleWrapper.forLanguageTag("mni")),
          of(ISO, LocaleWrapper.forLanguageTag("mr")),
          of(ISO, LocaleWrapper.forLanguageTag("ms")),
          of(ISO, LocaleWrapper.forLanguageTag("naq")),
          of(ISO, LocaleWrapper.forLanguageTag("ne-IN")),
          of(ISO, LocaleWrapper.forLanguageTag("om")),
          of(ISO, LocaleWrapper.forLanguageTag("or")),
          of(ISO, LocaleWrapper.forLanguageTag("pa")),
          of(ISO, LocaleWrapper.forLanguageTag("pa-Arab")),
          of(ISO, LocaleWrapper.forLanguageTag("ps-PK")),
          of(ISO, LocaleWrapper.forLanguageTag("pt-MO")),
          of(ISO, LocaleWrapper.forLanguageTag("sa")),
          of(ISO, LocaleWrapper.forLanguageTag("sat")),
          of(ISO, LocaleWrapper.forLanguageTag("sd")),
          of(ISO, LocaleWrapper.forLanguageTag("so")),
          of(ISO, LocaleWrapper.forLanguageTag("te")),
          of(ISO, LocaleWrapper.forLanguageTag("ti")),
          of(ISO, LocaleWrapper.forLanguageTag("to")),
          of(ISO, LocaleWrapper.forLanguageTag("tr-CY")),
          of(ISO, LocaleWrapper.forLanguageTag("ug")),
          of(ISO, LocaleWrapper.forLanguageTag("ur")),
          of(ISO, LocaleWrapper.forLanguageTag("vai")),
          of(ISO, LocaleWrapper.forLanguageTag("vai-Latn")),
        });
    TIME_LONG_PATTERNS.put(
        "ཆུ་ཚོད་ h སྐར་མ་ mm:ss a z",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("dz")),
        });
    TIME_LONG_PATTERNS.put(
        "H:mm:ss (z)",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("fa")),
          of(ISO, LocaleWrapper.forLanguageTag("ps")),
          of(ISO, LocaleWrapper.forLanguageTag("uz")),
          of(ISO, LocaleWrapper.forLanguageTag("uz-Arab")),
        });
    TIME_LONG_PATTERNS.put(
        "h:mm:ss a, z",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("sq")),
        });
    TIME_LONG_PATTERNS.put(
        "a h.mm.ss z",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("as")),
        });
    TIME_LONG_PATTERNS.put(
        "hh:mm:ss a z",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("gu")), of(ISO, LocaleWrapper.forLanguageTag("kn")),
        });
    TIME_LONG_PATTERNS.put(
        "z ah:mm:ss",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(ISO, LocaleWrapper.forLanguageTag("zh")),
        });
    TIME_MEDIUM_PATTERNS.put(
        "HH:mm:ss",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("af")),
          of(ISO, LocaleWrapper.forLanguageTag("agq")),
          of(ISO, LocaleWrapper.forLanguageTag("ar-KM")),
          of(ISO, LocaleWrapper.forLanguageTag("ar-MA")),
          of(ISO, LocaleWrapper.forLanguageTag("asa")),
          of(ISO, LocaleWrapper.forLanguageTag("ast")),
          of(ISO, LocaleWrapper.forLanguageTag("az")),
          of(ISO, LocaleWrapper.forLanguageTag("az-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("bas")),
          of(ISO, LocaleWrapper.forLanguageTag("be")),
          of(ISO, LocaleWrapper.forLanguageTag("bez")),
          of(ISO, LocaleWrapper.forLanguageTag("bm")),
          of(ISO, LocaleWrapper.forLanguageTag("br")),
          of(ISO, LocaleWrapper.forLanguageTag("bs")),
          of(ISO, LocaleWrapper.forLanguageTag("bs-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("cgg")),
          of(ISO, LocaleWrapper.forLanguageTag("ckb-IR")),
          of(ISO, LocaleWrapper.forLanguageTag("cy")),
          of(ISO, LocaleWrapper.forLanguageTag("dav")),
          of(ISO, LocaleWrapper.forLanguageTag("de")),
          of(ISO, LocaleWrapper.forLanguageTag("dje")),
          of(ISO, LocaleWrapper.forLanguageTag("dua")),
          of(ISO, LocaleWrapper.forLanguageTag("dyo")),
          of(ISO, LocaleWrapper.forLanguageTag("ebu")),
          of(ISO, LocaleWrapper.forLanguageTag("ee-TG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-150")),
          of(ISO, LocaleWrapper.forLanguageTag("en-AI")),
          of(ISO, LocaleWrapper.forLanguageTag("en-BI")),
          of(ISO, LocaleWrapper.forLanguageTag("en-BW")),
          of(ISO, LocaleWrapper.forLanguageTag("en-BZ")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CC")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CK")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CM")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CX")),
          of(ISO, LocaleWrapper.forLanguageTag("en-DG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-FK")),
          of(ISO, LocaleWrapper.forLanguageTag("en-GB")),
          of(ISO, LocaleWrapper.forLanguageTag("en-GG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-GI")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IE")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IM")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IO")),
          of(ISO, LocaleWrapper.forLanguageTag("en-JE")),
          of(ISO, LocaleWrapper.forLanguageTag("en-KE")),
          of(ISO, LocaleWrapper.forLanguageTag("en-MG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-MS")),
          of(ISO, LocaleWrapper.forLanguageTag("en-MT")),
          of(ISO, LocaleWrapper.forLanguageTag("en-MU")),
          of(ISO, LocaleWrapper.forLanguageTag("en-NF")),
          of(ISO, LocaleWrapper.forLanguageTag("en-NG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-NR")),
          of(ISO, LocaleWrapper.forLanguageTag("en-NU")),
          of(ISO, LocaleWrapper.forLanguageTag("en-PN")),
          of(ISO, LocaleWrapper.forLanguageTag("en-RW")),
          of(ISO, LocaleWrapper.forLanguageTag("en-SC")),
          of(ISO, LocaleWrapper.forLanguageTag("en-SH")),
          of(ISO, LocaleWrapper.forLanguageTag("en-SX")),
          of(ISO, LocaleWrapper.forLanguageTag("en-TK")),
          of(ISO, LocaleWrapper.forLanguageTag("en-TV")),
          of(ISO, LocaleWrapper.forLanguageTag("en-TZ")),
          of(ISO, LocaleWrapper.forLanguageTag("en-UG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-ZA")),
          of(ISO, LocaleWrapper.forLanguageTag("en-ZW")),
          of(ISO, LocaleWrapper.forLanguageTag("eo")),
          of(ISO, LocaleWrapper.forLanguageTag("es-419")),
          of(ISO, LocaleWrapper.forLanguageTag("es-MX")),
          of(ISO, LocaleWrapper.forLanguageTag("et")),
          of(ISO, LocaleWrapper.forLanguageTag("eu")),
          of(ISO, LocaleWrapper.forLanguageTag("ewo")),
          of(ISO, LocaleWrapper.forLanguageTag("ff")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm")),
          of(ISO, LocaleWrapper.forLanguageTag("fo")),
          of(ISO, LocaleWrapper.forLanguageTag("fr")),
          of(ISO, LocaleWrapper.forLanguageTag("fur")),
          of(ISO, LocaleWrapper.forLanguageTag("fy")),
          of(ISO, LocaleWrapper.forLanguageTag("ga")),
          of(ISO, LocaleWrapper.forLanguageTag("gd")),
          of(ISO, LocaleWrapper.forLanguageTag("gl")),
          of(ISO, LocaleWrapper.forLanguageTag("gsw")),
          of(ISO, LocaleWrapper.forLanguageTag("guz")),
          of(ISO, LocaleWrapper.forLanguageTag("gv")),
          of(ISO, LocaleWrapper.forLanguageTag("ha")),
          of(ISO, LocaleWrapper.forLanguageTag("hr")),
          of(ISO, LocaleWrapper.forLanguageTag("hy")),
          of(ISO, LocaleWrapper.forLanguageTag("ia")),
          of(ISO, LocaleWrapper.forLanguageTag("ig")),
          of(ISO, LocaleWrapper.forLanguageTag("is")),
          of(ISO, LocaleWrapper.forLanguageTag("it")),
          of(ISO, LocaleWrapper.forLanguageTag("jgo")),
          of(ISO, LocaleWrapper.forLanguageTag("jmc")),
          of(ISO, LocaleWrapper.forLanguageTag("jv")),
          of(ISO, LocaleWrapper.forLanguageTag("ka")),
          of(ISO, LocaleWrapper.forLanguageTag("kam")),
          of(ISO, LocaleWrapper.forLanguageTag("kde")),
          of(ISO, LocaleWrapper.forLanguageTag("kea")),
          of(ISO, LocaleWrapper.forLanguageTag("khq")),
          of(ISO, LocaleWrapper.forLanguageTag("ki")),
          of(ISO, LocaleWrapper.forLanguageTag("kk")),
          of(ISO, LocaleWrapper.forLanguageTag("kkj")),
          of(ISO, LocaleWrapper.forLanguageTag("kln")),
          of(ISO, LocaleWrapper.forLanguageTag("ksb")),
          of(ISO, LocaleWrapper.forLanguageTag("ksf")),
          of(ISO, LocaleWrapper.forLanguageTag("ksh")),
          of(ISO, LocaleWrapper.forLanguageTag("kw")),
          of(ISO, LocaleWrapper.forLanguageTag("ky")),
          of(ISO, LocaleWrapper.forLanguageTag("lag")),
          of(ISO, LocaleWrapper.forLanguageTag("lb")),
          of(ISO, LocaleWrapper.forLanguageTag("lg")),
          of(ISO, LocaleWrapper.forLanguageTag("ln")),
          of(ISO, LocaleWrapper.forLanguageTag("lrc")),
          of(ISO, LocaleWrapper.forLanguageTag("lt")),
          of(ISO, LocaleWrapper.forLanguageTag("lu")),
          of(ISO, LocaleWrapper.forLanguageTag("luo")),
          of(ISO, LocaleWrapper.forLanguageTag("luy")),
          of(ISO, LocaleWrapper.forLanguageTag("lv")),
          of(ISO, LocaleWrapper.forLanguageTag("mas")),
          of(ISO, LocaleWrapper.forLanguageTag("mer")),
          of(ISO, LocaleWrapper.forLanguageTag("mfe")),
          of(ISO, LocaleWrapper.forLanguageTag("mg")),
          of(ISO, LocaleWrapper.forLanguageTag("mgh")),
          of(ISO, LocaleWrapper.forLanguageTag("mgo")),
          of(ISO, LocaleWrapper.forLanguageTag("mk")),
          of(ISO, LocaleWrapper.forLanguageTag("mn")),
          of(ISO, LocaleWrapper.forLanguageTag("mt")),
          of(ISO, LocaleWrapper.forLanguageTag("mua")),
          of(ISO, LocaleWrapper.forLanguageTag("nd")),
          of(ISO, LocaleWrapper.forLanguageTag("ne")),
          of(ISO, LocaleWrapper.forLanguageTag("nl")),
          of(ISO, LocaleWrapper.forLanguageTag("nmg")),
          of(ISO, LocaleWrapper.forLanguageTag("nn")),
          of(ISO, LocaleWrapper.forLanguageTag("no")),
          of(ISO, LocaleWrapper.forLanguageTag("nyn")),
          of(ISO, LocaleWrapper.forLanguageTag("om-KE")),
          of(ISO, LocaleWrapper.forLanguageTag("os")),
          of(ISO, LocaleWrapper.forLanguageTag("pcm")),
          of(ISO, LocaleWrapper.forLanguageTag("pl")),
          of(ISO, LocaleWrapper.forLanguageTag("pt")),
          of(ISO, LocaleWrapper.forLanguageTag("pt-PT")),
          of(ISO, LocaleWrapper.forLanguageTag("qu")),
          of(ISO, LocaleWrapper.forLanguageTag("rm")),
          of(ISO, LocaleWrapper.forLanguageTag("rn")),
          of(ISO, LocaleWrapper.forLanguageTag("ro")),
          of(ISO, LocaleWrapper.forLanguageTag("rof")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("")),
          of(ISO, LocaleWrapper.forLanguageTag("")),
          of(HIJRAH_UMALQURA, LocaleWrapper.forLanguageTag("")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("")),
          of(MINGUO, LocaleWrapper.forLanguageTag("")),
          of(ISO, LocaleWrapper.forLanguageTag("ru")),
          of(ISO, LocaleWrapper.forLanguageTag("rw")),
          of(ISO, LocaleWrapper.forLanguageTag("rwk")),
          of(ISO, LocaleWrapper.forLanguageTag("sah")),
          of(ISO, LocaleWrapper.forLanguageTag("saq")),
          of(ISO, LocaleWrapper.forLanguageTag("sbp")),
          of(ISO, LocaleWrapper.forLanguageTag("se")),
          of(ISO, LocaleWrapper.forLanguageTag("seh")),
          of(ISO, LocaleWrapper.forLanguageTag("ses")),
          of(ISO, LocaleWrapper.forLanguageTag("sg")),
          of(ISO, LocaleWrapper.forLanguageTag("sl")),
          of(ISO, LocaleWrapper.forLanguageTag("sn")),
          of(ISO, LocaleWrapper.forLanguageTag("so-KE")),
          of(ISO, LocaleWrapper.forLanguageTag("sq-MK")),
          of(ISO, LocaleWrapper.forLanguageTag("sq-XK")),
          of(ISO, LocaleWrapper.forLanguageTag("sr")),
          of(ISO, LocaleWrapper.forLanguageTag("sr-Latn")),
          of(ISO, LocaleWrapper.forLanguageTag("sv")),
          of(ISO, LocaleWrapper.forLanguageTag("sw")),
          of(ISO, LocaleWrapper.forLanguageTag("ta-LK")),
          of(ISO, LocaleWrapper.forLanguageTag("teo")),
          of(ISO, LocaleWrapper.forLanguageTag("tg")),
          of(ISO, LocaleWrapper.forLanguageTag("th")),
          of(ISO, LocaleWrapper.forLanguageTag("tk")),
          of(ISO, LocaleWrapper.forLanguageTag("tr")),
          of(ISO, LocaleWrapper.forLanguageTag("twq")),
          of(ISO, LocaleWrapper.forLanguageTag("uk")),
          of(ISO, LocaleWrapper.forLanguageTag("uz")),
          of(ISO, LocaleWrapper.forLanguageTag("uz-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("vi")),
          of(ISO, LocaleWrapper.forLanguageTag("vun")),
          of(ISO, LocaleWrapper.forLanguageTag("wo")),
          of(ISO, LocaleWrapper.forLanguageTag("xh")),
          of(ISO, LocaleWrapper.forLanguageTag("xog")),
          of(ISO, LocaleWrapper.forLanguageTag("yav")),
          of(ISO, LocaleWrapper.forLanguageTag("yi")),
          of(ISO, LocaleWrapper.forLanguageTag("zgh")),
          of(ISO, LocaleWrapper.forLanguageTag("zu")),
        });
    TIME_MEDIUM_PATTERNS.put(
        "ah:mm:ss",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("yue")),
          of(ISO, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(ISO, LocaleWrapper.forLanguageTag("zh")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant-HK")),
        });
    TIME_MEDIUM_PATTERNS.put(
        "B HH:mm:ss",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("my")),
        });
    TIME_MEDIUM_PATTERNS.put(
        "H:mm:ss",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ar-IL")),
          of(ISO, LocaleWrapper.forLanguageTag("ca")),
          of(ISO, LocaleWrapper.forLanguageTag("cs")),
          of(ISO, LocaleWrapper.forLanguageTag("dsb")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IL")),
          of(ISO, LocaleWrapper.forLanguageTag("es")),
          of(ISO, LocaleWrapper.forLanguageTag("fa")),
          of(ISO, LocaleWrapper.forLanguageTag("he")),
          of(ISO, LocaleWrapper.forLanguageTag("hsb")),
          of(ISO, LocaleWrapper.forLanguageTag("hu")),
          of(ISO, LocaleWrapper.forLanguageTag("ja")),
          of(ISO, LocaleWrapper.forLanguageTag("lo")),
          of(ISO, LocaleWrapper.forLanguageTag("ps")),
          of(ISO, LocaleWrapper.forLanguageTag("sk")),
          of(ISO, LocaleWrapper.forLanguageTag("tt")),
          of(ISO, LocaleWrapper.forLanguageTag("uz-Arab")),
        });
    TIME_MEDIUM_PATTERNS.put(
        "a h.mm.ss",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("as")),
        });
    TIME_MEDIUM_PATTERNS.put(
        "HH.mm.ss",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("da")),
          of(ISO, LocaleWrapper.forLanguageTag("en-DK")),
          of(ISO, LocaleWrapper.forLanguageTag("id")),
          of(ISO, LocaleWrapper.forLanguageTag("kl")),
          of(ISO, LocaleWrapper.forLanguageTag("ms-ID")),
          of(ISO, LocaleWrapper.forLanguageTag("si")),
        });
    TIME_MEDIUM_PATTERNS.put(
        "H:m:s",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("yo")),
        });
    TIME_MEDIUM_PATTERNS.put(
        "h:mm:ss a",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("af-NA")),
          of(ISO, LocaleWrapper.forLanguageTag("ak")),
          of(ISO, LocaleWrapper.forLanguageTag("am")),
          of(ISO, LocaleWrapper.forLanguageTag("ar")),
          of(ISO, LocaleWrapper.forLanguageTag("bem")),
          of(ISO, LocaleWrapper.forLanguageTag("bn")),
          of(ISO, LocaleWrapper.forLanguageTag("bo")),
          of(ISO, LocaleWrapper.forLanguageTag("brx")),
          of(ISO, LocaleWrapper.forLanguageTag("ccp")),
          of(ISO, LocaleWrapper.forLanguageTag("ceb")),
          of(ISO, LocaleWrapper.forLanguageTag("chr")),
          of(ISO, LocaleWrapper.forLanguageTag("ckb")),
          of(ISO, LocaleWrapper.forLanguageTag("doi")),
          of(ISO, LocaleWrapper.forLanguageTag("el")),
          of(ISO, LocaleWrapper.forLanguageTag("en")),
          of(ISO, LocaleWrapper.forLanguageTag("en-AU")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CA")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IN")),
          of(ISO, LocaleWrapper.forLanguageTag("es-CO")),
          of(ISO, LocaleWrapper.forLanguageTag("es-DO")),
          of(ISO, LocaleWrapper.forLanguageTag("es-PA")),
          of(ISO, LocaleWrapper.forLanguageTag("es-PH")),
          of(ISO, LocaleWrapper.forLanguageTag("es-PR")),
          of(ISO, LocaleWrapper.forLanguageTag("es-US")),
          of(ISO, LocaleWrapper.forLanguageTag("es-VE")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm-GH")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm-GM")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm-LR")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm-MR")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm-SL")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Latn-GH")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Latn-GM")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Latn-LR")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Latn-MR")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Latn-SL")),
          of(ISO, LocaleWrapper.forLanguageTag("fil")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-DJ")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-DZ")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-MR")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-SY")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-TD")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-TN")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-VU")),
          of(ISO, LocaleWrapper.forLanguageTag("ha-GH")),
          of(ISO, LocaleWrapper.forLanguageTag("haw")),
          of(ISO, LocaleWrapper.forLanguageTag("hi")),
          of(ISO, LocaleWrapper.forLanguageTag("ii")),
          of(ISO, LocaleWrapper.forLanguageTag("kab")),
          of(ISO, LocaleWrapper.forLanguageTag("km")),
          of(ISO, LocaleWrapper.forLanguageTag("kok")),
          of(ISO, LocaleWrapper.forLanguageTag("ks")),
          of(ISO, LocaleWrapper.forLanguageTag("lkt")),
          of(ISO, LocaleWrapper.forLanguageTag("lrc-IQ")),
          of(ISO, LocaleWrapper.forLanguageTag("mai")),
          of(ISO, LocaleWrapper.forLanguageTag("mi")),
          of(ISO, LocaleWrapper.forLanguageTag("ml")),
          of(ISO, LocaleWrapper.forLanguageTag("mni")),
          of(ISO, LocaleWrapper.forLanguageTag("mr")),
          of(ISO, LocaleWrapper.forLanguageTag("ms")),
          of(ISO, LocaleWrapper.forLanguageTag("naq")),
          of(ISO, LocaleWrapper.forLanguageTag("ne-IN")),
          of(ISO, LocaleWrapper.forLanguageTag("nus")),
          of(ISO, LocaleWrapper.forLanguageTag("om")),
          of(ISO, LocaleWrapper.forLanguageTag("or")),
          of(ISO, LocaleWrapper.forLanguageTag("pa")),
          of(ISO, LocaleWrapper.forLanguageTag("pa-Arab")),
          of(ISO, LocaleWrapper.forLanguageTag("ps-PK")),
          of(ISO, LocaleWrapper.forLanguageTag("pt-MO")),
          of(ISO, LocaleWrapper.forLanguageTag("sa")),
          of(ISO, LocaleWrapper.forLanguageTag("sat")),
          of(ISO, LocaleWrapper.forLanguageTag("sd")),
          of(ISO, LocaleWrapper.forLanguageTag("so")),
          of(ISO, LocaleWrapper.forLanguageTag("sq")),
          of(ISO, LocaleWrapper.forLanguageTag("te")),
          of(ISO, LocaleWrapper.forLanguageTag("ti")),
          of(ISO, LocaleWrapper.forLanguageTag("to")),
          of(ISO, LocaleWrapper.forLanguageTag("tr-CY")),
          of(ISO, LocaleWrapper.forLanguageTag("ug")),
          of(ISO, LocaleWrapper.forLanguageTag("ur")),
          of(ISO, LocaleWrapper.forLanguageTag("vai")),
          of(ISO, LocaleWrapper.forLanguageTag("vai-Latn")),
        });
    TIME_MEDIUM_PATTERNS.put(
        "a h:mm:ss",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ko")),
          of(ISO, LocaleWrapper.forLanguageTag("sd-Deva")),
          of(ISO, LocaleWrapper.forLanguageTag("ta")),
        });
    TIME_MEDIUM_PATTERNS.put(
        "'Klock' H.mm:ss",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("nds")),
        });
    TIME_MEDIUM_PATTERNS.put(
        "hh:mm:ss a",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("gu")), of(ISO, LocaleWrapper.forLanguageTag("kn")),
        });
    TIME_MEDIUM_PATTERNS.put(
        "a 'ga' h:mm:ss",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ee")),
        });
    TIME_MEDIUM_PATTERNS.put(
        "H.mm.ss",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("en-FI")),
          of(ISO, LocaleWrapper.forLanguageTag("fi")),
          of(ISO, LocaleWrapper.forLanguageTag("smn")),
          of(ISO, LocaleWrapper.forLanguageTag("su")),
        });
    TIME_MEDIUM_PATTERNS.put(
        "H:mm:ss 'ч'.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("bg")),
        });
    TIME_MEDIUM_PATTERNS.put(
        "ཆུ་ཚོད་h:mm:ss a",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("dz")),
        });
    TIME_MEDIUM_PATTERNS.put(
        "HH 'h' mm 'min' ss 's'",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("fr-CA")),
        });
    TIME_SHORT_PATTERNS.put(
        "HH:mm",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("af")),
          of(ISO, LocaleWrapper.forLanguageTag("agq")),
          of(ISO, LocaleWrapper.forLanguageTag("ar-KM")),
          of(ISO, LocaleWrapper.forLanguageTag("ar-MA")),
          of(ISO, LocaleWrapper.forLanguageTag("asa")),
          of(ISO, LocaleWrapper.forLanguageTag("ast")),
          of(ISO, LocaleWrapper.forLanguageTag("az")),
          of(ISO, LocaleWrapper.forLanguageTag("az-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("bas")),
          of(ISO, LocaleWrapper.forLanguageTag("be")),
          of(ISO, LocaleWrapper.forLanguageTag("bez")),
          of(ISO, LocaleWrapper.forLanguageTag("bm")),
          of(ISO, LocaleWrapper.forLanguageTag("br")),
          of(ISO, LocaleWrapper.forLanguageTag("bs")),
          of(ISO, LocaleWrapper.forLanguageTag("bs-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("cgg")),
          of(ISO, LocaleWrapper.forLanguageTag("ckb-IR")),
          of(ISO, LocaleWrapper.forLanguageTag("cy")),
          of(ISO, LocaleWrapper.forLanguageTag("dav")),
          of(ISO, LocaleWrapper.forLanguageTag("de")),
          of(ISO, LocaleWrapper.forLanguageTag("dje")),
          of(ISO, LocaleWrapper.forLanguageTag("dua")),
          of(ISO, LocaleWrapper.forLanguageTag("dyo")),
          of(ISO, LocaleWrapper.forLanguageTag("ebu")),
          of(ISO, LocaleWrapper.forLanguageTag("ee-TG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-150")),
          of(ISO, LocaleWrapper.forLanguageTag("en-AI")),
          of(ISO, LocaleWrapper.forLanguageTag("en-BI")),
          of(ISO, LocaleWrapper.forLanguageTag("en-BW")),
          of(ISO, LocaleWrapper.forLanguageTag("en-BZ")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CC")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CK")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CM")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CX")),
          of(ISO, LocaleWrapper.forLanguageTag("en-DG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-FK")),
          of(ISO, LocaleWrapper.forLanguageTag("en-GB")),
          of(ISO, LocaleWrapper.forLanguageTag("en-GG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-GI")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IE")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IM")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IO")),
          of(ISO, LocaleWrapper.forLanguageTag("en-JE")),
          of(ISO, LocaleWrapper.forLanguageTag("en-KE")),
          of(ISO, LocaleWrapper.forLanguageTag("en-MG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-MS")),
          of(ISO, LocaleWrapper.forLanguageTag("en-MT")),
          of(ISO, LocaleWrapper.forLanguageTag("en-MU")),
          of(ISO, LocaleWrapper.forLanguageTag("en-NF")),
          of(ISO, LocaleWrapper.forLanguageTag("en-NG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-NR")),
          of(ISO, LocaleWrapper.forLanguageTag("en-NU")),
          of(ISO, LocaleWrapper.forLanguageTag("en-PN")),
          of(ISO, LocaleWrapper.forLanguageTag("en-RW")),
          of(ISO, LocaleWrapper.forLanguageTag("en-SC")),
          of(ISO, LocaleWrapper.forLanguageTag("en-SH")),
          of(ISO, LocaleWrapper.forLanguageTag("en-SX")),
          of(ISO, LocaleWrapper.forLanguageTag("en-TK")),
          of(ISO, LocaleWrapper.forLanguageTag("en-TV")),
          of(ISO, LocaleWrapper.forLanguageTag("en-TZ")),
          of(ISO, LocaleWrapper.forLanguageTag("en-UG")),
          of(ISO, LocaleWrapper.forLanguageTag("en-ZA")),
          of(ISO, LocaleWrapper.forLanguageTag("en-ZW")),
          of(ISO, LocaleWrapper.forLanguageTag("eo")),
          of(ISO, LocaleWrapper.forLanguageTag("es-419")),
          of(ISO, LocaleWrapper.forLanguageTag("es-MX")),
          of(ISO, LocaleWrapper.forLanguageTag("et")),
          of(ISO, LocaleWrapper.forLanguageTag("eu")),
          of(ISO, LocaleWrapper.forLanguageTag("ewo")),
          of(ISO, LocaleWrapper.forLanguageTag("ff")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm")),
          of(ISO, LocaleWrapper.forLanguageTag("fo")),
          of(ISO, LocaleWrapper.forLanguageTag("fr")),
          of(ISO, LocaleWrapper.forLanguageTag("fur")),
          of(ISO, LocaleWrapper.forLanguageTag("fy")),
          of(ISO, LocaleWrapper.forLanguageTag("ga")),
          of(ISO, LocaleWrapper.forLanguageTag("gd")),
          of(ISO, LocaleWrapper.forLanguageTag("gl")),
          of(ISO, LocaleWrapper.forLanguageTag("gsw")),
          of(ISO, LocaleWrapper.forLanguageTag("guz")),
          of(ISO, LocaleWrapper.forLanguageTag("gv")),
          of(ISO, LocaleWrapper.forLanguageTag("ha")),
          of(ISO, LocaleWrapper.forLanguageTag("hr")),
          of(ISO, LocaleWrapper.forLanguageTag("hy")),
          of(ISO, LocaleWrapper.forLanguageTag("ia")),
          of(ISO, LocaleWrapper.forLanguageTag("ig")),
          of(ISO, LocaleWrapper.forLanguageTag("is")),
          of(ISO, LocaleWrapper.forLanguageTag("it")),
          of(ISO, LocaleWrapper.forLanguageTag("jgo")),
          of(ISO, LocaleWrapper.forLanguageTag("jmc")),
          of(ISO, LocaleWrapper.forLanguageTag("jv")),
          of(ISO, LocaleWrapper.forLanguageTag("ka")),
          of(ISO, LocaleWrapper.forLanguageTag("kam")),
          of(ISO, LocaleWrapper.forLanguageTag("kde")),
          of(ISO, LocaleWrapper.forLanguageTag("kea")),
          of(ISO, LocaleWrapper.forLanguageTag("khq")),
          of(ISO, LocaleWrapper.forLanguageTag("ki")),
          of(ISO, LocaleWrapper.forLanguageTag("kk")),
          of(ISO, LocaleWrapper.forLanguageTag("kkj")),
          of(ISO, LocaleWrapper.forLanguageTag("kln")),
          of(ISO, LocaleWrapper.forLanguageTag("ksb")),
          of(ISO, LocaleWrapper.forLanguageTag("ksf")),
          of(ISO, LocaleWrapper.forLanguageTag("ksh")),
          of(ISO, LocaleWrapper.forLanguageTag("kw")),
          of(ISO, LocaleWrapper.forLanguageTag("ky")),
          of(ISO, LocaleWrapper.forLanguageTag("lag")),
          of(ISO, LocaleWrapper.forLanguageTag("lb")),
          of(ISO, LocaleWrapper.forLanguageTag("lg")),
          of(ISO, LocaleWrapper.forLanguageTag("ln")),
          of(ISO, LocaleWrapper.forLanguageTag("lrc")),
          of(ISO, LocaleWrapper.forLanguageTag("lt")),
          of(ISO, LocaleWrapper.forLanguageTag("lu")),
          of(ISO, LocaleWrapper.forLanguageTag("luo")),
          of(ISO, LocaleWrapper.forLanguageTag("luy")),
          of(ISO, LocaleWrapper.forLanguageTag("lv")),
          of(ISO, LocaleWrapper.forLanguageTag("mas")),
          of(ISO, LocaleWrapper.forLanguageTag("mer")),
          of(ISO, LocaleWrapper.forLanguageTag("mfe")),
          of(ISO, LocaleWrapper.forLanguageTag("mg")),
          of(ISO, LocaleWrapper.forLanguageTag("mgh")),
          of(ISO, LocaleWrapper.forLanguageTag("mgo")),
          of(ISO, LocaleWrapper.forLanguageTag("mk")),
          of(ISO, LocaleWrapper.forLanguageTag("mn")),
          of(ISO, LocaleWrapper.forLanguageTag("mt")),
          of(ISO, LocaleWrapper.forLanguageTag("mua")),
          of(ISO, LocaleWrapper.forLanguageTag("nd")),
          of(ISO, LocaleWrapper.forLanguageTag("ne")),
          of(ISO, LocaleWrapper.forLanguageTag("nl")),
          of(ISO, LocaleWrapper.forLanguageTag("nmg")),
          of(ISO, LocaleWrapper.forLanguageTag("nn")),
          of(ISO, LocaleWrapper.forLanguageTag("no")),
          of(ISO, LocaleWrapper.forLanguageTag("nyn")),
          of(ISO, LocaleWrapper.forLanguageTag("om-KE")),
          of(ISO, LocaleWrapper.forLanguageTag("os")),
          of(ISO, LocaleWrapper.forLanguageTag("pcm")),
          of(ISO, LocaleWrapper.forLanguageTag("pl")),
          of(ISO, LocaleWrapper.forLanguageTag("pt")),
          of(ISO, LocaleWrapper.forLanguageTag("pt-PT")),
          of(ISO, LocaleWrapper.forLanguageTag("qu")),
          of(ISO, LocaleWrapper.forLanguageTag("rm")),
          of(ISO, LocaleWrapper.forLanguageTag("rn")),
          of(ISO, LocaleWrapper.forLanguageTag("ro")),
          of(ISO, LocaleWrapper.forLanguageTag("rof")),
          of(THAI_BUDDHIST, LocaleWrapper.forLanguageTag("")),
          of(ISO, LocaleWrapper.forLanguageTag("")),
          of(HIJRAH_UMALQURA, LocaleWrapper.forLanguageTag("")),
          of(JAPANESE, LocaleWrapper.forLanguageTag("")),
          of(MINGUO, LocaleWrapper.forLanguageTag("")),
          of(ISO, LocaleWrapper.forLanguageTag("ru")),
          of(ISO, LocaleWrapper.forLanguageTag("rw")),
          of(ISO, LocaleWrapper.forLanguageTag("rwk")),
          of(ISO, LocaleWrapper.forLanguageTag("sah")),
          of(ISO, LocaleWrapper.forLanguageTag("saq")),
          of(ISO, LocaleWrapper.forLanguageTag("sbp")),
          of(ISO, LocaleWrapper.forLanguageTag("se")),
          of(ISO, LocaleWrapper.forLanguageTag("seh")),
          of(ISO, LocaleWrapper.forLanguageTag("ses")),
          of(ISO, LocaleWrapper.forLanguageTag("sg")),
          of(ISO, LocaleWrapper.forLanguageTag("sl")),
          of(ISO, LocaleWrapper.forLanguageTag("sn")),
          of(ISO, LocaleWrapper.forLanguageTag("so-KE")),
          of(ISO, LocaleWrapper.forLanguageTag("sq-MK")),
          of(ISO, LocaleWrapper.forLanguageTag("sq-XK")),
          of(ISO, LocaleWrapper.forLanguageTag("sr")),
          of(ISO, LocaleWrapper.forLanguageTag("sr-Latn")),
          of(ISO, LocaleWrapper.forLanguageTag("sv")),
          of(ISO, LocaleWrapper.forLanguageTag("sw")),
          of(ISO, LocaleWrapper.forLanguageTag("ta-LK")),
          of(ISO, LocaleWrapper.forLanguageTag("teo")),
          of(ISO, LocaleWrapper.forLanguageTag("tg")),
          of(ISO, LocaleWrapper.forLanguageTag("th")),
          of(ISO, LocaleWrapper.forLanguageTag("tk")),
          of(ISO, LocaleWrapper.forLanguageTag("tr")),
          of(ISO, LocaleWrapper.forLanguageTag("twq")),
          of(ISO, LocaleWrapper.forLanguageTag("uk")),
          of(ISO, LocaleWrapper.forLanguageTag("uz")),
          of(ISO, LocaleWrapper.forLanguageTag("uz-Cyrl")),
          of(ISO, LocaleWrapper.forLanguageTag("vi")),
          of(ISO, LocaleWrapper.forLanguageTag("vun")),
          of(ISO, LocaleWrapper.forLanguageTag("wo")),
          of(ISO, LocaleWrapper.forLanguageTag("xh")),
          of(ISO, LocaleWrapper.forLanguageTag("xog")),
          of(ISO, LocaleWrapper.forLanguageTag("yav")),
          of(ISO, LocaleWrapper.forLanguageTag("yi")),
          of(ISO, LocaleWrapper.forLanguageTag("zgh")),
          of(ISO, LocaleWrapper.forLanguageTag("zu")),
        });
    TIME_SHORT_PATTERNS.put(
        "'Kl'. H.mm",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("nds")),
        });
    TIME_SHORT_PATTERNS.put(
        "H.mm",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("en-FI")),
          of(ISO, LocaleWrapper.forLanguageTag("fi")),
          of(ISO, LocaleWrapper.forLanguageTag("smn")),
          of(ISO, LocaleWrapper.forLanguageTag("su")),
        });
    TIME_SHORT_PATTERNS.put(
        "a h:mm",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ko")),
          of(ISO, LocaleWrapper.forLanguageTag("sd-Deva")),
          of(ISO, LocaleWrapper.forLanguageTag("ta")),
        });
    TIME_SHORT_PATTERNS.put(
        "HH 'h' mm",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("fr-CA")),
        });
    TIME_SHORT_PATTERNS.put(
        "h:mm a",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("af-NA")),
          of(ISO, LocaleWrapper.forLanguageTag("ak")),
          of(ISO, LocaleWrapper.forLanguageTag("am")),
          of(ISO, LocaleWrapper.forLanguageTag("ar")),
          of(ISO, LocaleWrapper.forLanguageTag("bem")),
          of(ISO, LocaleWrapper.forLanguageTag("bn")),
          of(ISO, LocaleWrapper.forLanguageTag("bo")),
          of(ISO, LocaleWrapper.forLanguageTag("brx")),
          of(ISO, LocaleWrapper.forLanguageTag("ccp")),
          of(ISO, LocaleWrapper.forLanguageTag("ceb")),
          of(ISO, LocaleWrapper.forLanguageTag("chr")),
          of(ISO, LocaleWrapper.forLanguageTag("ckb")),
          of(ISO, LocaleWrapper.forLanguageTag("doi")),
          of(ISO, LocaleWrapper.forLanguageTag("el")),
          of(ISO, LocaleWrapper.forLanguageTag("en")),
          of(ISO, LocaleWrapper.forLanguageTag("en-AU")),
          of(ISO, LocaleWrapper.forLanguageTag("en-CA")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IN")),
          of(ISO, LocaleWrapper.forLanguageTag("es-CO")),
          of(ISO, LocaleWrapper.forLanguageTag("es-DO")),
          of(ISO, LocaleWrapper.forLanguageTag("es-PA")),
          of(ISO, LocaleWrapper.forLanguageTag("es-PH")),
          of(ISO, LocaleWrapper.forLanguageTag("es-PR")),
          of(ISO, LocaleWrapper.forLanguageTag("es-US")),
          of(ISO, LocaleWrapper.forLanguageTag("es-VE")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm-GH")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm-GM")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm-LR")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm-MR")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Adlm-SL")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Latn-GH")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Latn-GM")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Latn-LR")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Latn-MR")),
          of(ISO, LocaleWrapper.forLanguageTag("ff-Latn-SL")),
          of(ISO, LocaleWrapper.forLanguageTag("fil")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-DJ")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-DZ")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-MR")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-SY")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-TD")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-TN")),
          of(ISO, LocaleWrapper.forLanguageTag("fr-VU")),
          of(ISO, LocaleWrapper.forLanguageTag("ha-GH")),
          of(ISO, LocaleWrapper.forLanguageTag("haw")),
          of(ISO, LocaleWrapper.forLanguageTag("hi")),
          of(ISO, LocaleWrapper.forLanguageTag("ii")),
          of(ISO, LocaleWrapper.forLanguageTag("kab")),
          of(ISO, LocaleWrapper.forLanguageTag("km")),
          of(ISO, LocaleWrapper.forLanguageTag("kok")),
          of(ISO, LocaleWrapper.forLanguageTag("ks")),
          of(ISO, LocaleWrapper.forLanguageTag("lkt")),
          of(ISO, LocaleWrapper.forLanguageTag("lrc-IQ")),
          of(ISO, LocaleWrapper.forLanguageTag("mai")),
          of(ISO, LocaleWrapper.forLanguageTag("mi")),
          of(ISO, LocaleWrapper.forLanguageTag("ml")),
          of(ISO, LocaleWrapper.forLanguageTag("mni")),
          of(ISO, LocaleWrapper.forLanguageTag("mr")),
          of(ISO, LocaleWrapper.forLanguageTag("ms")),
          of(ISO, LocaleWrapper.forLanguageTag("naq")),
          of(ISO, LocaleWrapper.forLanguageTag("ne-IN")),
          of(ISO, LocaleWrapper.forLanguageTag("nus")),
          of(ISO, LocaleWrapper.forLanguageTag("om")),
          of(ISO, LocaleWrapper.forLanguageTag("or")),
          of(ISO, LocaleWrapper.forLanguageTag("pa")),
          of(ISO, LocaleWrapper.forLanguageTag("pa-Arab")),
          of(ISO, LocaleWrapper.forLanguageTag("ps-PK")),
          of(ISO, LocaleWrapper.forLanguageTag("pt-MO")),
          of(ISO, LocaleWrapper.forLanguageTag("sa")),
          of(ISO, LocaleWrapper.forLanguageTag("sat")),
          of(ISO, LocaleWrapper.forLanguageTag("sd")),
          of(ISO, LocaleWrapper.forLanguageTag("so")),
          of(ISO, LocaleWrapper.forLanguageTag("sq")),
          of(ISO, LocaleWrapper.forLanguageTag("te")),
          of(ISO, LocaleWrapper.forLanguageTag("ti")),
          of(ISO, LocaleWrapper.forLanguageTag("to")),
          of(ISO, LocaleWrapper.forLanguageTag("tr-CY")),
          of(ISO, LocaleWrapper.forLanguageTag("ug")),
          of(ISO, LocaleWrapper.forLanguageTag("ur")),
          of(ISO, LocaleWrapper.forLanguageTag("vai")),
          of(ISO, LocaleWrapper.forLanguageTag("vai-Latn")),
        });
    TIME_SHORT_PATTERNS.put(
        "H:mm 'hodź'.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("hsb")),
        });
    TIME_SHORT_PATTERNS.put(
        "H:mm",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ar-IL")),
          of(ISO, LocaleWrapper.forLanguageTag("ca")),
          of(ISO, LocaleWrapper.forLanguageTag("cs")),
          of(ISO, LocaleWrapper.forLanguageTag("dsb")),
          of(ISO, LocaleWrapper.forLanguageTag("en-IL")),
          of(ISO, LocaleWrapper.forLanguageTag("es")),
          of(ISO, LocaleWrapper.forLanguageTag("fa")),
          of(ISO, LocaleWrapper.forLanguageTag("he")),
          of(ISO, LocaleWrapper.forLanguageTag("hu")),
          of(ISO, LocaleWrapper.forLanguageTag("ja")),
          of(ISO, LocaleWrapper.forLanguageTag("lo")),
          of(ISO, LocaleWrapper.forLanguageTag("ps")),
          of(ISO, LocaleWrapper.forLanguageTag("sk")),
          of(ISO, LocaleWrapper.forLanguageTag("tt")),
          of(ISO, LocaleWrapper.forLanguageTag("uz-Arab")),
        });
    TIME_SHORT_PATTERNS.put(
        "B H:mm",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("my")),
        });
    TIME_SHORT_PATTERNS.put(
        "ah:mm",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("yue")),
          of(ISO, LocaleWrapper.forLanguageTag("yue-Hans")),
          of(ISO, LocaleWrapper.forLanguageTag("zh")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant")),
          of(ISO, LocaleWrapper.forLanguageTag("zh-Hant-HK")),
        });
    TIME_SHORT_PATTERNS.put(
        "a 'ga' h:mm",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("ee")),
        });
    TIME_SHORT_PATTERNS.put(
        "HH.mm",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("da")),
          of(ISO, LocaleWrapper.forLanguageTag("en-DK")),
          of(ISO, LocaleWrapper.forLanguageTag("id")),
          of(ISO, LocaleWrapper.forLanguageTag("kl")),
          of(ISO, LocaleWrapper.forLanguageTag("ms-ID")),
          of(ISO, LocaleWrapper.forLanguageTag("si")),
        });
    TIME_SHORT_PATTERNS.put(
        "ཆུ་ཚོད་ h སྐར་མ་ mm a",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("dz")),
        });
    TIME_SHORT_PATTERNS.put(
        "H:mm 'ч'.",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("bg")),
        });
    TIME_SHORT_PATTERNS.put(
        "H:m",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("yo")),
        });
    TIME_SHORT_PATTERNS.put(
        "a h.mm",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("as")),
        });
    TIME_SHORT_PATTERNS.put(
        "hh:mm a",
        new PatternCoordinates[] {
          of(ISO, LocaleWrapper.forLanguageTag("gu")), of(ISO, LocaleWrapper.forLanguageTag("kn")),
        });
  }
}
