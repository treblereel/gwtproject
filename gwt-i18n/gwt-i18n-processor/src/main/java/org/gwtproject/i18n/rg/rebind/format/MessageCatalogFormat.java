/*
 * Copyright 2008 Google Inc.
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
package org.gwtproject.i18n.rg.rebind.format;

import static org.gwtproject.i18n.rg.rebind.AbstractResource.*;

import java.io.PrintWriter;
import javax.lang.model.element.TypeElement;
import org.gwtproject.i18n.ext.TreeLogger;
import org.gwtproject.i18n.ext.UnableToCompleteException;

/**
 * Interface for writing various message catalog formats.
 *
 * <p><hr><b>WARNING:</b> this API is expected to change as we develop additional message catalog
 * formats. In particular, this interface will be extended to support reading message catalogs and
 * further changes may be required. <hr>
 *
 * <p>Implementations of this interface are executed at compile time and therefore must not contain
 * any JSNI code.
 */
public interface MessageCatalogFormat {

  /**
   * Write a message catalog file.
   *
   * @param logger TreeLogger for logging errors/etc
   * @param locale locale of this output file
   * @param resourceList the contents to write
   * @param out the PrintWriter to generate output on
   * @param messageInterface the interface to create (so additional annotations may be accessed)
   * @throws UnableToCompleteException if a fatal error prevents generating the output file. In this
   *     case, the implementation must have already logged an appropriate ERROR message to the
   *     logger.
   */
  void write(
      TreeLogger logger,
      String locale,
      ResourceList resourceList,
      PrintWriter out,
      TypeElement messageInterface)
      throws UnableToCompleteException;

  /** Returns the extension to use for this file type, including the dot. */
  String getExtension();
}
