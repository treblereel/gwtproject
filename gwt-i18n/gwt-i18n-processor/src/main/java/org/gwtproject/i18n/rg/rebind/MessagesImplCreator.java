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
package org.gwtproject.i18n.rg.rebind;

import static org.gwtproject.i18n.rg.rebind.AbstractResource.ResourceList;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import org.gwtproject.i18n.ext.GeneratorContext;
import org.gwtproject.i18n.ext.TreeLogger;
import org.gwtproject.i18n.ext.UnableToCompleteException;
import org.gwtproject.i18n.rg.util.SourceWriter;
import org.gwtproject.i18n.server.Type;
import org.gwtproject.i18n.shared.GwtLocale;
import org.gwtproject.safehtml.shared.SafeHtml;

/**
 * Creates the class implementation for a given resource bundle using the standard <code>
 * AbstractGeneratorClassCreator</code>.
 */
public class MessagesImplCreator extends AbstractLocalizableImplCreator {

  /**
   * Constructor for <code>MessagesImplCreator</code>.
   *
   * @param logger logger to print errors
   * @param writer <code>Writer</code> to print to
   * @param localizableClass Class/Interface to conform to
   * @param resourceList resource bundle used to generate the class
   * @throws UnableToCompleteException
   */
  public MessagesImplCreator(
      TreeLogger logger,
      GeneratorContext context,
      SourceWriter writer,
      TypeElement localizableClass,
      ResourceList resourceList) {
    super(logger, context.getAptContext(), writer, localizableClass, resourceList, false);
    MessagesMethodCreator creator = new MessagesMethodCreator(this, context, writer);
    register(Type.STRING, creator);
    register(new Type(SafeHtml.class.getCanonicalName(), false), creator);
  }

  /**
   * Checks that the method has the right structure to implement <code>Messages</code>.
   *
   * @param method
   * @throws UnableToCompleteException
   */
  private void checkMessagesMethod(TreeLogger logger, ExecutableElement method)
      throws UnableToCompleteException {
    if (!method.getReturnType().toString().equals("java.lang.String")
        && !method.getReturnType().toString().equals(MessagesMethodCreator.SAFE_HTML_FQCN)) {
      throw error(
          logger,
          "All methods in interfaces extending Messages must have a return type "
              + "of String or "
              + MessagesMethodCreator.SAFE_HTML_FQCN
              + ".");
    }
  }

  /**
   * Create the method body associated with the given method. Arguments are arg0...argN.
   *
   * @param m method to emit
   * @throws UnableToCompleteException
   */
  @Override
  protected void emitMethodBody(TreeLogger logger, ExecutableElement m, GwtLocale locale)
      throws UnableToCompleteException {
    checkMessagesMethod(logger, m);
    delegateToCreator(logger, m, locale);
  }
}
