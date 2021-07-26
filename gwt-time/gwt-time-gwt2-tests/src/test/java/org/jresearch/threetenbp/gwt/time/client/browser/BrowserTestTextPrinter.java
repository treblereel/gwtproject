package org.jresearch.threetenbp.gwt.time.client.browser;

import org.jresearch.threetenbp.gwt.time.client.format.TestTextPrinter;

public class BrowserTestTextPrinter extends TestTextPrinter {

  public void test_print_french_long() throws Exception {
    super.disable_test_print_french_long();
  }

  public void test_print_french_short() throws Exception {
    super.disable_test_print_french_short();
  }
}
