/*
 * Copyright (c) 2007-present, Stephen Colebourne & Michael Nascimento Santos
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * Neither the name of JSR-310 nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.jresearch.threetenbp.gwt.time.client.format;

import java.time.DateTimeException;
import java.time.LocalDate;
import org.jresearch.threetenbp.gwt.time.client.format.wrap.CharLiteralPrinterParserTestWrapper;
import org.jresearch.threetenbp.gwt.time.client.format.wrap.PadPrinterParserDecoratorTestWrapper;
import org.jresearch.threetenbp.gwt.time.client.format.wrap.StringLiteralPrinterParserTestWrapper;
import org.junit.Test;

/** Test PadPrinterDecorator. */
// @Test
public class TestPadPrinterDecorator extends AbstractTestPrinterParser {

  // -----------------------------------------------------------------------
  public void test_print_emptyCalendrical() throws Exception {
    PadPrinterParserDecoratorTestWrapper pp =
        new PadPrinterParserDecoratorTestWrapper(
            new CharLiteralPrinterParserTestWrapper('Z'), 3, '-');
    pp.print(printEmptyContext, buf);
    assertEquals(buf.toString(), "--Z");
  }

  public void test_print_fullDateTime() throws Exception {
    printContext.setDateTime(LocalDate.of(2008, 12, 3));
    PadPrinterParserDecoratorTestWrapper pp =
        new PadPrinterParserDecoratorTestWrapper(
            new CharLiteralPrinterParserTestWrapper('Z'), 3, '-');
    pp.print(printContext, buf);
    assertEquals(buf.toString(), "--Z");
  }

  public void test_print_append() throws Exception {
    buf.append("EXISTING");
    PadPrinterParserDecoratorTestWrapper pp =
        new PadPrinterParserDecoratorTestWrapper(
            new CharLiteralPrinterParserTestWrapper('Z'), 3, '-');
    pp.print(printEmptyContext, buf);
    assertEquals(buf.toString(), "EXISTING--Z");
  }

  // -----------------------------------------------------------------------
  public void test_print_noPadRequiredSingle() throws Exception {
    PadPrinterParserDecoratorTestWrapper pp =
        new PadPrinterParserDecoratorTestWrapper(
            new CharLiteralPrinterParserTestWrapper('Z'), 1, '-');
    pp.print(printEmptyContext, buf);
    assertEquals(buf.toString(), "Z");
  }

  public void test_print_padRequiredSingle() throws Exception {
    PadPrinterParserDecoratorTestWrapper pp =
        new PadPrinterParserDecoratorTestWrapper(
            new CharLiteralPrinterParserTestWrapper('Z'), 5, '-');
    pp.print(printEmptyContext, buf);
    assertEquals(buf.toString(), "----Z");
  }

  public void test_print_noPadRequiredMultiple() throws Exception {
    PadPrinterParserDecoratorTestWrapper pp =
        new PadPrinterParserDecoratorTestWrapper(
            new StringLiteralPrinterParserTestWrapper("WXYZ"), 4, '-');
    pp.print(printEmptyContext, buf);
    assertEquals(buf.toString(), "WXYZ");
  }

  public void test_print_padRequiredMultiple() throws Exception {
    PadPrinterParserDecoratorTestWrapper pp =
        new PadPrinterParserDecoratorTestWrapper(
            new StringLiteralPrinterParserTestWrapper("WXYZ"), 5, '-');
    pp.print(printEmptyContext, buf);
    assertEquals(buf.toString(), "-WXYZ");
  }

  @Test(expected = DateTimeException.class)
  public void test_print_overPad() throws Exception {
    try {
      PadPrinterParserDecoratorTestWrapper pp =
          new PadPrinterParserDecoratorTestWrapper(
              new StringLiteralPrinterParserTestWrapper("WXYZ"), 3, '-');
      pp.print(printEmptyContext, buf);
      fail("Missing exception");
    } catch (DateTimeException e) {
      // expected
    }
  }

  // -----------------------------------------------------------------------
  public void test_toString1() throws Exception {
    CharLiteralPrinterParserTestWrapper wrapped = new CharLiteralPrinterParserTestWrapper('Y');
    PadPrinterParserDecoratorTestWrapper pp =
        new PadPrinterParserDecoratorTestWrapper(wrapped, 5, ' ');
    assertEquals(pp.toString(), "Pad('Y',5)");
  }

  public void test_toString2() throws Exception {
    CharLiteralPrinterParserTestWrapper wrapped = new CharLiteralPrinterParserTestWrapper('Y');
    PadPrinterParserDecoratorTestWrapper pp =
        new PadPrinterParserDecoratorTestWrapper(wrapped, 5, '-');
    assertEquals(pp.toString(), "Pad('Y',5,'-')");
  }
}
