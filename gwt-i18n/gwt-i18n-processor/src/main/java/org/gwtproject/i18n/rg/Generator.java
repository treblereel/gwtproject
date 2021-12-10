/*
 * Copyright 2006 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.gwtproject.i18n.rg;

import com.google.common.base.Strings;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.gwtproject.i18n.ext.GeneratorContext;
import org.gwtproject.i18n.ext.TreeLogger;
import org.gwtproject.i18n.ext.UnableToCompleteException;

/**
 * Generates source code for subclasses during deferred binding requests. Subclasses must be
 * thread-safe.
 *
 * <p>Resource reading should be done through the ResourceOracle in the provided GeneratorContext
 * (not via ClassLoader.getResource(), File, or URL) so that Generator Resource dependencies can be
 * detected and used to facilitate fast incremental recompiles.
 */
public abstract class Generator {

  private static final int MAX_SIXTEEN_BIT_NUMBER_STRING_LENGTH = 5;

  /**
   * Escapes string content to be a valid string literal.
   *
   * @return an escaped version of <code>unescaped</code>, suitable for being enclosed in double
   *     quotes in Java source
   */
  public static String escape(String unescaped) {
    int extra = 0;
    for (int in = 0, n = unescaped.length(); in < n; ++in) {
      switch (unescaped.charAt(in)) {
        case '\0':
        case '\n':
        case '\r':
        case '\"':
        case '\\':
          ++extra;
          break;
      }
    }

    if (extra == 0) {
      return unescaped;
    }

    char[] oldChars = unescaped.toCharArray();
    char[] newChars = new char[oldChars.length + extra];
    for (int in = 0, out = 0, n = oldChars.length; in < n; ++in, ++out) {
      char c = oldChars[in];
      switch (c) {
        case '\0':
          newChars[out++] = '\\';
          c = '0';
          break;
        case '\n':
          newChars[out++] = '\\';
          c = 'n';
          break;
        case '\r':
          newChars[out++] = '\\';
          c = 'r';
          break;
        case '\"':
          newChars[out++] = '\\';
          c = '"';
          break;
        case '\\':
          newChars[out++] = '\\';
          c = '\\';
          break;
      }
      newChars[out] = c;
    }

    return String.valueOf(newChars);
  }

  public static String escapeClassName(String unescapedString) {
    char[] unescapedCharacters = unescapedString.toCharArray();
    StringBuilder escapedCharacters = new StringBuilder();
    boolean firstCharacter = true;
    char[] var4 = unescapedCharacters;
    int var5 = unescapedCharacters.length;

    for (int var6 = 0; var6 < var5; ++var6) {
      char unescapedCharacter = var4[var6];
      if (firstCharacter && !Character.isJavaIdentifierStart(unescapedCharacter)) {
        escapeAndAppendCharacter(escapedCharacters, unescapedCharacter);
      } else if (!Character.isJavaIdentifierPart(unescapedCharacter)) {
        escapeAndAppendCharacter(escapedCharacters, unescapedCharacter);
      } else if (unescapedCharacter == '_') {
        escapedCharacters.append("__");
      } else {
        escapedCharacters.append(unescapedCharacter);
      }

      firstCharacter = false;
    }

    return escapedCharacters.toString();
  }

  private static void escapeAndAppendCharacter(
      StringBuilder escapedCharacters, char unescapedCharacter) {
    String numberString = Integer.toString(unescapedCharacter);
    numberString = Strings.padStart(numberString, 5, '0');
    escapedCharacters.append("_" + numberString);
  }

  public abstract String generate(TreeLogger var1, GeneratorContext var2, String var3)
      throws UnableToCompleteException;

  @Inherited
  @Retention(RetentionPolicy.RUNTIME)
  public @interface RunsLocal {

    String ALL = "%ALL%";

    String[] requiresProperties() default {};
  }
}
