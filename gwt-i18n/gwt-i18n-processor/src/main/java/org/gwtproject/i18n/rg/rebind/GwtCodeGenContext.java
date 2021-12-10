/*
 * Copyright 2011 Google Inc.
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
package org.gwtproject.i18n.rg.rebind;

import java.io.PrintWriter;
import org.gwtproject.i18n.ext.GeneratorContext;
import org.gwtproject.i18n.ext.TreeLogger;
import org.gwtproject.i18n.ext.UnableToCompleteException;
import org.gwtproject.i18n.rg.Generator;
import org.gwtproject.i18n.server.AbortablePrintWriter;
import org.gwtproject.i18n.server.JavaSourceWriterBuilder;

/**
 * {@link CodeGenContext} implementation for use within a GWT {@link Generator}.
 *
 * <p>Experimental API - subject to change.
 */
public class GwtCodeGenContext implements CodeGenContext {

  private final TreeLogger logger;
  private final GeneratorContext ctx;

  /**
   * @param logger
   * @param ctx
   */
  public GwtCodeGenContext(TreeLogger logger, GeneratorContext ctx) {
    this.logger = logger;
    this.ctx = ctx;
  }

  public JavaSourceWriterBuilder addClass(String pkgName, String className) {
    try {
      return addClass(null, pkgName, className);
    } catch (UnableToCompleteException e) {
      throw new Error(e);
    }
  }

  public JavaSourceWriterBuilder addClass(String superPath, String pkgName, String className)
      throws UnableToCompleteException {
    String superPkg = superPath == null ? pkgName : superPath + "." + pkgName;
    final PrintWriter pw = ctx.tryCreate(logger, superPkg, className);
    if (pw == null) {
      return null;
    }
    return new JavaSourceWriterBuilder(
        new AbortablePrintWriter(pw) {
          @Override
          protected void onClose(boolean aborted) {
            if (!aborted) {
              ctx.commit(logger, pw);
            }
          }
        },
        pkgName,
        className);
  }

  public void error(String msg) {
    logger.log(TreeLogger.ERROR, msg);
  }

  public void error(String msg, Throwable cause) {
    logger.log(TreeLogger.ERROR, msg, cause);
  }

  public void error(Throwable cause) {
    logger.log(TreeLogger.ERROR, cause.getMessage(), cause);
  }

  public void warn(String msg) {
    logger.log(TreeLogger.WARN, msg);
  }

  public void warn(String msg, Throwable cause) {
    logger.log(TreeLogger.WARN, msg, cause);
  }

  public void warn(Throwable cause) {
    logger.log(TreeLogger.WARN, cause.getMessage(), cause);
  }
}
