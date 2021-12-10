/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.gwtproject.i18n.shared;

public class StringUtils {
  public static char[] HEX_CHARS =
      new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

  public StringUtils() {}

  public static String javaScriptString(String value) {
    char[] chars = value.toCharArray();
    int n = chars.length;
    int quoteCount = 0;
    int aposCount = 0;

    for (int i = 0; i < n; ++i) {
      switch (chars[i]) {
        case '"':
          ++quoteCount;
          break;
        case '\'':
          ++aposCount;
      }
    }

    StringBuilder result = new StringBuilder(value.length() + 16);
    int quoteChar = quoteCount < aposCount ? 34 : 39;
    result.append((char) quoteChar);

    for (int i = 0; i < n; ++i) {
      char c = chars[i];
      if (' ' <= c && c <= '~' && c != quoteChar && c != '\\') {
        result.append(c);
      } else {
        int escape = -1;
        switch (c) {
          case '\b':
            escape = 98;
            break;
          case '\t':
            escape = 116;
            break;
          case '\n':
            escape = 110;
            break;
          case '\f':
            escape = 102;
            break;
          case '\r':
            escape = 114;
            break;
          case '"':
            escape = 34;
            break;
          case '\'':
            escape = 39;
            break;
          case '\\':
            escape = 92;
        }

        if (escape >= 0) {
          result.append('\\');
          result.append((char) escape);
        } else {
          byte hexSize;
          if (c < 256) {
            result.append("\\x");
            hexSize = 2;
          } else {
            result.append("\\u");
            hexSize = 4;
          }

          for (int shift = (hexSize - 1) * 4; shift >= 0; shift -= 4) {
            int digit = 15 & c >> shift;
            result.append(HEX_CHARS[digit]);
          }
        }
      }
    }

    result.append((char) quoteChar);
    escapeClosingTags(result);
    String resultString = result.toString();
    return resultString;
  }

  public static String toHexString(byte[] bytes) {
    char[] hexString = new char[2 * bytes.length];
    int j = 0;

    for (int i = 0; i < bytes.length; ++i) {
      hexString[j++] = HEX_CHARS[(bytes[i] & 240) >> 4];
      hexString[j++] = HEX_CHARS[bytes[i] & 15];
    }

    return new String(hexString);
  }

  private static void escapeClosingTags(StringBuilder str) {
    if (str != null) {
      int index = 0;

      while ((index = str.indexOf("</", index)) != -1) {
        str.insert(index + 1, '\\');
      }
    }
  }
}
