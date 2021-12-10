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

import static org.gwtproject.i18n.client.LocalizableResource.*;
import static org.gwtproject.i18n.rg.rebind.AbstractResource.*;
import static org.gwtproject.i18n.rg.rebind.AnnotationUtil.getClassAnnotation;
import static org.gwtproject.i18n.rg.rebind.AnnotationsResource.*;
import static org.gwtproject.i18n.server.MessageCatalogFactory.*;

import com.google.auto.common.MoreElements;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.MissingResourceException;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import org.gwtproject.i18n.client.LocalizableResource.Key;
import org.gwtproject.i18n.context.AptContext;
import org.gwtproject.i18n.ext.GeneratorContext;
import org.gwtproject.i18n.ext.SelectionProperty;
import org.gwtproject.i18n.ext.TreeLogger;
import org.gwtproject.i18n.ext.UnableToCompleteException;
import org.gwtproject.i18n.rg.rebind.format.MessageCatalogFormat;
import org.gwtproject.i18n.rg.util.SourceWriter;
import org.gwtproject.i18n.rg.util.Util;
import org.gwtproject.i18n.server.KeyGenerator;
import org.gwtproject.i18n.server.MessageCatalogFactory;
import org.gwtproject.i18n.server.MessageInterface;
import org.gwtproject.i18n.server.MessageProcessingException;
import org.gwtproject.i18n.shared.GwtLocale;
import org.gwtproject.i18n.shared.GwtLocaleFactory;

/**
 * Represents generic functionality needed for <code>Constants</code> and <code>Messages</code>
 * classes.
 */
abstract class AbstractLocalizableImplCreator extends AbstractGeneratorClassCreator {

  public static class MessageCatalogContextImpl implements Context {

    private final GeneratorContext context;
    private final TreeLogger logger;

    public MessageCatalogContextImpl(GeneratorContext context, TreeLogger logger) {
      this.context = context;
      this.logger = logger;
    }

    public OutputStream createBinaryFile(String catalogName) {
      try {
        final OutputStream ostr = context.tryCreateResource(logger, catalogName);
        if (ostr != null) {
          // wrap the stream so we can commit the resource on close
          return new OutputStream() {

            @Override
            public void close() throws IOException {
              try {
                context.commitResource(logger, ostr);
              } catch (UnableToCompleteException e) {
                // error already logged, anything more to do?
                throw new NullPointerException(); // TODO
              }
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
              ostr.write(b, off, len);
            }

            @Override
            public void write(int b) throws IOException {
              ostr.write(b);
            }
          };
        }
      } catch (UnableToCompleteException e) {
        // error already logged, anything more to do?
        throw new NullPointerException(e.getMessage());
      }
      return null;
    }

    public PrintWriter createTextFile(String catalogName, String charSet) {
      OutputStream outStr = createBinaryFile(catalogName);
      if (outStr != null) {
        try {
          return new PrintWriter(
              new BufferedWriter(new OutputStreamWriter(outStr, "UTF-8")), false);
        } catch (UnsupportedEncodingException e) {
          error("UTF-8 not supported", e);
        }
      }
      return null;
    }

    public void error(String msg) {
      logger.log(TreeLogger.ERROR, msg);
    }

    public void error(String msg, Throwable cause) {
      logger.log(TreeLogger.ERROR, msg, cause);
    }

    public GwtLocaleFactory getLocaleFactory() {
      return LocaleUtils.getLocaleFactory();
    }

    public void warning(String msg) {
      logger.log(TreeLogger.WARN, msg);
    }

    public void warning(String msg, Throwable cause) {
      logger.log(TreeLogger.WARN, msg, cause);
    }
  }

  static String generateConstantOrMessageClass(
      TreeLogger logger, GeneratorContext context, GwtLocale locale, TypeElement targetClass)
      throws UnableToCompleteException {
    boolean seenError = false;
    TypeElement constantsClass =
        context.getAptContext().elements.getTypeElement(LocalizableGenerator.CONSTANTS_NAME);
    TypeElement constantsWithLookupClass =
        context
            .getAptContext()
            .elements
            .getTypeElement(LocalizableGenerator.CONSTANTS_WITH_LOOKUP_NAME);
    TypeElement messagesClass =
        context.getAptContext().elements.getTypeElement(LocalizableGenerator.MESSAGES_NAME);

    String name = targetClass.getSimpleName().toString();
    String packageName = MoreElements.getPackage(targetClass).getQualifiedName().toString();

    // Make sure the interface being rebound extends either Constants or
    // Messages.
    boolean assignableToConstants =
        context.getAptContext().types.isAssignable(targetClass.asType(), constantsClass.asType());
    boolean assignableToMessages =
        context.getAptContext().types.isAssignable(targetClass.asType(), messagesClass.asType());
    if (!assignableToConstants && !assignableToMessages) {
      // Let the implementation generator handle this interface.
      return null;
    }

    // Make sure that they don't try to extend both Messages and Constants.
    if (assignableToConstants && assignableToMessages) {
      throw error(logger, name + " cannot extend both Constants and Messages");
    }

    // Make sure that the type being rebound is in fact an interface.
    if (!targetClass.getKind().isInterface()) {
      throw error(logger, name + " must be an interface");
    }

    ResourceList resourceList;
    try {
      resourceList =
          ResourceFactory.getBundle(logger, context, targetClass, locale, assignableToConstants);
    } catch (MissingResourceException e) {
      throw error(
          logger,
          "Localization failed; there must be at least one resource accessible through"
              + " the classpath in package '"
              + packageName
              + "' whose base name is '"
              + ResourceFactory.getResourceName(targetClass)
              + "'");
    } catch (IllegalArgumentException e) {
      // A bad key can generate an illegal argument exception.
      throw error(logger, e.getMessage());
    }

    // generated implementations for interface X will be named X_, X_en,
    // X_en_CA, etc.
    GwtLocale generatedLocale = locale;
    String localeSuffix = String.valueOf(ResourceFactory.LOCALE_SEPARATOR);
    localeSuffix += generatedLocale.getAsString();
    // Use _ rather than "." in class name, cannot use $
    String simpleName = Util.getJavaClassName(targetClass);
    String className = simpleName.replaceAll("\\.", "") + localeSuffix;
    PrintWriter pw = context.tryCreate(logger, packageName, className);
    if (pw != null) {
      ClassSourceFileComposerFactory factory =
          new ClassSourceFileComposerFactory(packageName, className);
      factory.addImplementedInterface(targetClass.getQualifiedName().toString());
      SourceWriter writer = factory.createSourceWriter(context, pw);
      // Now that we have all the information set up, process the class
      AbstractLocalizableImplCreator creator;
      if (context
          .getAptContext()
          .types
          .isAssignable(targetClass.asType(), constantsWithLookupClass.asType())) {
        creator =
            new ConstantsWithLookupImplCreator(logger, writer, targetClass, resourceList, context);

      } else if (context
          .getAptContext()
          .types
          .isAssignable(targetClass.asType(), constantsClass.asType())) {
        creator =
            new ConstantsImplCreator(
                logger, context.getAptContext(), writer, targetClass, resourceList);
      } else {
        creator = new MessagesImplCreator(logger, context, writer, targetClass, resourceList);
      }
      creator.emitClass(logger, generatedLocale);
    }
    // Generate a translatable output file if requested.
    // TODO remove as def annotation
    Generate generate =
        getClassAnnotation(context.getAptContext().types, targetClass, Generate.class);
    if (generate != null) {
      String path = generate.fileName();
      if (Generate.DEFAULT.equals(path)) {
        path =
            MoreElements.getPackage(targetClass).toString()
                + "."
                + targetClass.getSimpleName().toString().replace('.', '_');
      } else if (path.endsWith(File.pathSeparator)) {
        path = path + targetClass.getSimpleName().toString().replace('.', '_');
      }
      String[] genLocales = generate.locales();
      boolean found = false;
      if (genLocales.length != 0) {
        // verify the current locale is in the list
        for (String genLocale : genLocales) {
          if (GwtLocale.DEFAULT_LOCALE.equals(genLocale)) {
            // Locale "default" gets special handling because of property
            // fallbacks; "default" might be mapped to any real locale.
            try {
              SelectionProperty localeProp =
                  context.getPropertyOracle().getSelectionProperty(logger, "locale");
              String defaultLocale = localeProp.getFallbackValue();
              if (defaultLocale.length() > 0) {
                genLocale = defaultLocale;
              }
            } catch (UnableToCompleteException e) {
              throw error(logger, "Could not get 'locale' property");
            }
          }
          if (genLocale.equals(locale.toString())) {
            found = true;
            break;
          }
        }
      } else {
        // Since they want all locales, this is guaranteed to be one of them.
        found = true;
      }
      if (found) {
        for (String genClassName : generate.format()) {
          MessageCatalogFormat msgWriter = null;
          MessageCatalogFactory msgCatFactory = null;
          try {
            // TODO(jat): if GWT is ever modified to take a classpath for user
            // code as an option, we would need to use the user classloader here
            Class<?> clazz =
                Class.forName(genClassName, false, MessageCatalogFormat.class.getClassLoader());
            if (MessageCatalogFormat.class.isAssignableFrom(clazz)) {
              Class<? extends MessageCatalogFormat> msgFormatClass =
                  clazz.asSubclass(MessageCatalogFormat.class);
              msgWriter = msgFormatClass.newInstance();
            } else if (MessageCatalogFactory.class.isAssignableFrom(clazz)) {
              Class<? extends MessageCatalogFactory> msgFactoryClass =
                  clazz.asSubclass(MessageCatalogFactory.class);
              msgCatFactory = msgFactoryClass.newInstance();
            } else {
              logger.log(
                  TreeLogger.ERROR,
                  "Class specified in @Generate must "
                      + "either be a subtype of MessageCatalogFormat or "
                      + "MessageCatalogFactory");
              seenError = true;
              continue;
            }
          } catch (InstantiationException e) {
            logger.log(TreeLogger.ERROR, "Error instantiating @Generate class " + genClassName, e);
            seenError = true;
            continue;
          } catch (IllegalAccessException e) {
            logger.log(TreeLogger.ERROR, "@Generate class " + genClassName + " illegal access", e);
            seenError = true;
            continue;
          } catch (ClassNotFoundException e) {
            logger.log(TreeLogger.ERROR, "@Generate class " + genClassName + " not found");
            seenError = true;
            continue;
          }
          // Make generator-specific changes to a temporary copy of the path.
          String genPath = path;
          if (genLocales.length != 1) {
            // If the user explicitly specified only one locale, do not add the
            // locale.
            genPath += '_' + locale.toString();
          }
          if (msgCatFactory != null) {
            seenError |=
                generateToMsgCatFactory(
                    logger,
                    context,
                    locale,
                    targetClass,
                    seenError,
                    resourceList,
                    msgCatFactory,
                    genPath);
          }
        }
      }
    }
    if (seenError) {
      // If one of our generators had a fatal error, don't complete normally.
      throw new UnableToCompleteException();
    }
    return packageName + "." + className;
  }

  /**
   * Write translation source files to a {@link MessageCatalogFactory}.
   *
   * @param logger
   * @param context
   * @param locale
   * @param targetClass
   * @param seenError
   * @param resourceList
   * @param msgCatFactory
   * @param genPath
   * @return true if an error occurred (already logged)
   */
  private static boolean generateToMsgCatFactory(
      TreeLogger logger,
      GeneratorContext context,
      GwtLocale locale,
      TypeElement targetClass,
      boolean seenError,
      ResourceList resourceList,
      MessageCatalogFactory msgCatFactory,
      String genPath) {
    // TODO(jat): maintain MessageCatalogWriter instances across
    // generator runs so they can save state.  One problem is knowing
    // when the last generator has been run.
    Writer catWriter = null;
    try {
      String catalogName = genPath + msgCatFactory.getExtension();
      Context ctx = new MessageCatalogContextImpl(context, logger);
      MessageInterface msgIntf =
          new TypeOracleMessageInterface(
              context.getAptContext(), LocaleUtils.getLocaleFactory(), targetClass, resourceList);
      catWriter = msgCatFactory.getWriter(ctx, catalogName);
      if (catWriter == null) {
        if (logger.isLoggable(TreeLogger.TRACE)) {
          logger.log(TreeLogger.TRACE, "Already generated " + catalogName);
        }
        return false;
      }
      msgIntf.accept(catWriter.visitClass());
    } catch (MessageProcessingException e) {
      logger.log(TreeLogger.ERROR, e.getMessage(), e);
      seenError = true;
    } finally {
      if (catWriter != null) {
        try {
          catWriter.close();
        } catch (IOException e) {
          logger.log(TreeLogger.ERROR, "IO error closing catalog writer", e);
          seenError = true;
        }
      }
    }
    return seenError;
  }

  /** Generator to use to create keys for messages. */
  private KeyGenerator keyGenerator;

  /** The Dictionary/value bindings used to determine message contents. */
  private ResourceList resourceList;

  private AptContext context;

  /** True if the class being generated uses Constants-style annotations/quoting. */
  private boolean isConstants;

  /**
   * Constructor for <code>AbstractLocalizableImplCreator</code>.
   *
   * @param writer writer
   * @param targetClass current target
   * @param resourceList backing resource
   */
  public AbstractLocalizableImplCreator(
      TreeLogger logger,
      AptContext context,
      SourceWriter writer,
      TypeElement targetClass,
      ResourceList resourceList,
      boolean isConstants) {
    super(writer, targetClass);
    this.resourceList = resourceList;
    this.isConstants = isConstants;
    this.context = context;
    try {
      keyGenerator = getKeyGenerator(context, targetClass);
    } catch (AnnotationsError e) {
      logger.log(
          TreeLogger.WARN,
          "Error getting key generator for " + targetClass.getQualifiedName().toString(),
          e);
    }
  }

  /**
   * Gets the resource associated with this class.
   *
   * @return the resource
   */
  public ResourceList getResourceBundle() {
    return resourceList;
  }

  @Override
  protected String branchMessage() {
    return "Processing " + this.getTarget();
  }

  /**
   * Find the creator associated with the given method, and delegate the creation of the method body
   * to it.
   *
   * @param logger TreeLogger instance for logging
   * @param method method to be generated
   * @param locale locale to generate
   * @throws UnableToCompleteException
   */
  protected void delegateToCreator(TreeLogger logger, ExecutableElement method, GwtLocale locale)
      throws UnableToCompleteException {
    AbstractMethodCreator methodCreator = getMethodCreator(logger, method);
    String key = getKey(logger, method);
    if (key == null) {
      logger.log(
          TreeLogger.ERROR,
          "Unable to get or compute key for method "
              + method.getSimpleName().toString()
              + " in "
              + method.getEnclosingElement(),
          null);
      throw new UnableToCompleteException();
    }
    methodCreator.createMethodFor(logger, method, key, resourceList, locale);
  }

  /**
   * Returns a resource key given a method name.
   *
   * @param logger TreeLogger instance for logging
   * @param method method to get key for
   * @return the key to use for resource lookups or null if unable to get or compute the key
   */
  protected String getKey(TreeLogger logger, ExecutableElement method) {
    Key key = method.getAnnotation(Key.class);
    if (key != null) {
      return key.value();
    }
    return AnnotationsResource.getKey(logger, context, keyGenerator, method, isConstants);
  }
}
