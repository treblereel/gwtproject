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

import static org.gwtproject.i18n.client.Messages.AlternateMessage;
import static org.gwtproject.i18n.client.Messages.Offset;
import static org.gwtproject.i18n.client.Messages.Optional;
import static org.gwtproject.i18n.client.Messages.PluralCount;
import static org.gwtproject.i18n.client.Messages.PluralText;
import static org.gwtproject.i18n.client.Messages.Select;
import static org.gwtproject.i18n.client.PluralRule.PluralForm;
import static org.gwtproject.i18n.rg.rebind.AbstractResource.MissingResourceException;
import static org.gwtproject.i18n.rg.rebind.AbstractResource.ResourceEntry;
import static org.gwtproject.i18n.rg.rebind.AbstractResource.ResourceList;

import com.google.auto.common.MoreElements;
import com.google.auto.common.MoreTypes;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import org.apache.tapestry.util.text.LocalizedPropertiesLoader;
import org.gwtproject.i18n.client.DateTimeFormat;
import org.gwtproject.i18n.client.NumberFormat;
import org.gwtproject.i18n.client.PluralRule;
import org.gwtproject.i18n.client.impl.plurals.DefaultRule;
import org.gwtproject.i18n.context.AptContext;
import org.gwtproject.i18n.ext.GeneratorContext;
import org.gwtproject.i18n.ext.ResourceOracle;
import org.gwtproject.i18n.ext.TreeLogger;
import org.gwtproject.i18n.ext.UnableToCompleteException;
import org.gwtproject.i18n.rg.rebind.MessageFormatParser.ArgumentChunk;
import org.gwtproject.i18n.rg.rebind.MessageFormatParser.DefaultTemplateChunkVisitor;
import org.gwtproject.i18n.rg.rebind.MessageFormatParser.StaticArgChunk;
import org.gwtproject.i18n.rg.rebind.MessageFormatParser.StringChunk;
import org.gwtproject.i18n.rg.rebind.MessageFormatParser.TemplateChunk;
import org.gwtproject.i18n.rg.resource.impl.ResourceOracleImpl;
import org.gwtproject.i18n.rg.util.MoreTypeUtils;
import org.gwtproject.i18n.rg.util.SourceWriter;
import org.gwtproject.i18n.server.StringGenerator;
import org.gwtproject.i18n.shared.AlternateMessageSelector;
import org.gwtproject.i18n.shared.GwtLocale;
import org.gwtproject.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml;
import org.gwtproject.safehtml.shared.SafeHtml;
import org.gwtproject.safehtml.shared.SafeHtmlBuilder;

/** Creator for methods of the Messages interface. */
@SuppressWarnings("deprecation") // for @PluralText
public class MessagesMethodCreator extends AbstractMethodCreator {

  /** Fully-qualified class name of the SafeHtml interface. */
  public static final String SAFE_HTML_FQCN = SafeHtml.class.getCanonicalName();
  /** Fully-qualified class name of the SafeHtmlBuilder class. */
  public static final String SAFE_HTML_BUILDER_FQCN = SafeHtmlBuilder.class.getCanonicalName();
  /** Class names, in a refactor-friendly manner. */
  private static final String dtFormatClassName = DateTimeFormat.class.getCanonicalName();

  private static final String numFormatClassName = NumberFormat.class.getCanonicalName();
  private final Map<GwtLocale, Map<String, String>> listPatternCache;
  private final GeneratorContext context;
  /** Map of supported formats. */
  private Map<String, ValueFormatter> formatters = new HashMap<>();

  private ResourceOracle oracle;
  private SourceWriter writer;

  /** Constructor for <code>MessagesMethodCreator</code>. */
  public MessagesMethodCreator(
      AbstractGeneratorClassCreator classCreator, GeneratorContext context, SourceWriter writer) {
    super(classCreator);
    listPatternCache = new HashMap<>();
    this.context = context;
    this.writer = writer;

    oracle = new ResourceOracleImpl(context.getAptContext());

    formatters.put("date", new DateFormatter());
    formatters.put("number", new NumberFormatter(context.getAptContext()));
    formatters.put("time", new TimeFormatter());
    formatters.put("localdatetime", new LocalDateTimeFormatter());
  }

  /**
   * Creates an instance of a locale-specific plural rule implementation.
   *
   * <p>Note that this uses TypeOracle's ability to find all subclasses of the supplied parent
   * class, then uses reflection to actually load the class. This works because PluralRule instances
   * are required to be translatable, since part of them is executed at runtime and part at compile
   * time.
   *
   * @param logger TreeLogger instance
   * @param ruleClass PluralRule implementation to localize
   * @param locale current locale we are compiling for
   * @return an instance of a PluralRule implementation. If an appropriate implementation of the
   *     requested class cannot be found, an instance of DefaultRule is used instead as a default of
   *     last resort.
   * @throws UnableToCompleteException if findDerivedClasses fails
   *     <p>TODO: consider impact of possibly having multiple TypeOracles
   */
  private static PluralRule createLocalizedPluralRule(
      TreeLogger logger, GeneratorContext context, TypeElement ruleClass, GwtLocale locale)
      throws UnableToCompleteException {
    String baseName = ruleClass.getQualifiedName().toString();
    TypeElement ruleTypeElement = context.getAptContext().elements.getTypeElement(baseName);
    for (GwtLocale search : locale.getCompleteSearchList()) {
      String lookup =
          ruleTypeElement.getQualifiedName().toString()
              + (search.toString().equals("default") ? "" : "_" + search.toString());
      TypeElement localizedType = context.getAptContext().elements.getTypeElement(lookup);
      if (localizedType != null) {
        try {
          Class<?> testClass =
              Class.forName(
                  localizedType.getQualifiedName().toString(),
                  false,
                  PluralRule.class.getClassLoader());
          if (PluralRule.class.isAssignableFrom(testClass)) {
            return (PluralRule) testClass.newInstance();
          }
        } catch (ClassCastException e) {
          // ignore classes of the wrong type
        } catch (ClassNotFoundException e) {
          // ignore missing classes
        } catch (InstantiationException e) {
          // skip classes we can't instantiate
        } catch (IllegalAccessException e) {
          // ignore inaccessible classes
        }
      }
    }
    // default of last resort
    return new DefaultRule();
  }

  private static TypeElement getPluralCountValue(PluralCount annotation) {
    try {
      annotation.value();
    } catch (MirroredTypeException mte) {
      return MoreTypes.asTypeElement(mte.getTypeMirror());
    }
    return null;
  }

  /**
   * Append an argument to the output without doing any formatting.
   *
   * @param buf
   * @param argExpr Java source for expression to produce argument value
   * @param argType type of the argument being appended
   */
  private void appendUnformattedArg(StringGenerator buf, String argExpr, TypeMirror argType) {
    boolean isSafeHtmlTyped = SAFE_HTML_FQCN.equals(argType.toString());
    boolean isPrimitiveType = argType.getKind().isPrimitive();
    boolean needsConversionToString = !("java.lang.String".equals(argType.toString()));
    buf.appendExpression(argExpr, isSafeHtmlTyped, isPrimitiveType, needsConversionToString);
  }

  @Override
  public void createMethodFor(
      TreeLogger logger,
      ExecutableElement m,
      String key,
      ResourceList resourceList,
      GwtLocale locale)
      throws UnableToCompleteException {
    ResourceEntry resourceEntry = resourceList.getEntry(key);
    if (resourceEntry == null) {
      throw new MissingResourceException(key, resourceList);
    }
    List<? extends VariableElement> params = m.getParameters();
    boolean seenPluralCount = false;
    boolean seenSelect = false;

    int numParams = params.size();
    int lastPluralArgNumber = -1;
    List<AlternateFormSelector> selectors = new ArrayList<>();
    // See if any parameter is tagged as a PluralCount or Select parameter.
    for (int i = 0; i < numParams; ++i) {
      PluralCount pluralCount = params.get(i).getAnnotation(PluralCount.class);
      Select select = params.get(i).getAnnotation(Select.class);
      if (pluralCount != null && select != null) {
        throw error(
            logger,
            params.get(i).getSimpleName().toString()
                + " cannot be both @PluralCount"
                + " and @Select");
      }
      AlternateFormSelector selector = null;
      if (select != null) {
        selector = new GenericSelector(logger, m, i, params);
        seenSelect = true;
      } else if (pluralCount != null) {
        PluralFormSelector pluralSelector =
            new PluralFormSelector(logger, context, m, i, params, locale);
        selector = pluralSelector;
        if (!seenPluralCount) {
          // TODO(jat): what if we have different plural rules on the different
          // forms?
          resourceList.setPluralForms(key, pluralSelector.getPluralForms());
        }
        seenPluralCount = true;
        lastPluralArgNumber = i;
      }
      if (selector != null) {
        selectors.add(selector);
      }
    }

    boolean[] seenFlags = new boolean[numParams];
    final Parameters paramsAccessor = new ParametersImpl(params, seenFlags);
    boolean isSafeHtml = m.getReturnType().toString().equals(SAFE_HTML_FQCN);

    if (!isSafeHtml) {
      // Check that no argument is SafeHtml
      for (VariableElement param : params) {
        if (param.asType().toString().equals(SAFE_HTML_FQCN)) {
          throw error(
              logger, "Message methods with SafeHtml arguments can only have SafeHtml return type");
        }
      }
    }

    String template = resourceEntry.getForm(null);
    if (template == null) {
      logger.log(
          TreeLogger.ERROR,
          "No default form for method "
              + m.getSimpleName().toString()
              + "' in "
              + m.getEnclosingElement().getSimpleName().toString()
              + " for locale "
              + locale,
          null);
      throw new UnableToCompleteException();
    }

    // Generate code to format any lists
    // TODO(jat): handle messages with different list formats in alternate forms
    try {
      for (TemplateChunk chunk : MessageFormatParser.parse(template)) {
        if (chunk instanceof ArgumentChunk) {
          ArgumentChunk argChunk = (ArgumentChunk) chunk;
          if (argChunk.isList()) {
            ListAccessor listAccessor = null;
            int listArgNum = argChunk.getArgumentNumber();
            TypeMirror listType = params.get(listArgNum).asType();
            TypeElement classType = null;
            if (!listType.getKind().isPrimitive()
                && !listType.getKind().equals(TypeKind.ARRAY)
                && MoreTypes.asElement(listType).getKind().isInterface()) {
              classType = MoreTypes.asTypeElement(listType);
            }
            TypeMirror elemType = null;
            if (classType != null) {
              if ("java.util.List"
                  .equals(context.getAptContext().types.erasure(classType.asType()).toString())) {
                listAccessor = new ListAccessorList(listArgNum);
              } else {
                logger.log(
                    TreeLogger.ERROR,
                    "Parameters formatted as lists "
                        + "must be declared as java.util.List or arrays in "
                        + m.getEnclosingElement()
                        + "."
                        + m.getSimpleName());
                throw new UnableToCompleteException();
              }
              if (!classType.getTypeParameters().isEmpty()) {
                elemType = ((DeclaredType) params.get(0).asType()).getTypeArguments().get(0);
              } else {
                elemType = classType.asType();
              }
            } else {
              if (listType.getKind().equals(TypeKind.ARRAY)) {
                elemType = MoreTypes.asArray(listType).getComponentType();
                listAccessor = new ListAccessorArray(listArgNum);
              }
            }
            generateListFormattingCode(
                logger, locale, argChunk, elemType, isSafeHtml, listAccessor, paramsAccessor);
          }
        }
      }
    } catch (ParseException pe) {
      throw error(logger, "Error parsing '" + template + "'", pe);
    }

    if (!seenPluralCount
        && !seenSelect
        && (m.getAnnotation(AlternateMessage.class) != null
            || m.getAnnotation(PluralText.class) != null)) {
      logger.log(
          TreeLogger.WARN,
          "Unused @AlternateMessage or @PluralText on "
              + m.getEnclosingElement().getSimpleName()
              + "."
              + m.getSimpleName().toString()
              + "; did you intend to mark a @Select or @PluralCount parameter?",
          null);
    }
    Collection<String> resourceForms = resourceEntry.getForms();
    if (seenPluralCount || seenSelect) {
      paramsAccessor.enablePluralOffsets();
      writer.println(m.getReturnType().toString() + " returnVal = null;");
      for (AlternateFormSelector selector : selectors) {
        selector.generatePrepCode(writer);
      }

      // sort forms so that all exact-value forms come first
      String[] forms = resourceForms.toArray(new String[resourceForms.size()]);
      Arrays.sort(forms, new ExactValueComparator());

      generateMessageSelectors(
          logger,
          m,
          locale,
          resourceEntry,
          selectors,
          paramsAccessor,
          isSafeHtml,
          forms,
          lastPluralArgNumber);
      for (AlternateFormSelector selector : selectors) {
        selector.issueWarnings(logger, m, locale);
      }
      writer.println("if (returnVal != null) {");
      writer.indent();
      writer.println("return returnVal;");
      writer.outdent();
      writer.println("}");
    }
    writer.print("return ");
    generateString(
        logger, locale, template, paramsAccessor, writer, isSafeHtml, lastPluralArgNumber);
    writer.println(";");

    // Generate an error if any required parameter was not used somewhere.
    for (int i = 0; i < numParams; ++i) {
      if (!seenFlags[i]) {
        Optional optional = params.get(i).getAnnotation(Optional.class);
        Select select = params.get(i).getAnnotation(Select.class);
        if (optional == null && select == null) {
          throw error(logger, "Required argument " + i + " not present: " + template);
        }
      }
    }
  }

  private void formatArg(
      TreeLogger logger,
      GwtLocale locale,
      StringGenerator buf,
      ArgumentChunk argChunk,
      String argExpr,
      TypeMirror paramType,
      Parameters params)
      throws UnableToCompleteException {
    String format = argChunk.getFormat();
    if (format != null) {
      String subformat = argChunk.getSubFormat();
      ValueFormatter formatter = formatters.get(format);
      if (formatter != null) {
        if (formatter.format(
            logger, locale, buf, argChunk.getFormatArgs(), subformat, argExpr, paramType, params)) {
          throw new UnableToCompleteException();
        }
        return;
      }
    }
    // no format specified or unknown format
    // have to ensure that the result is stringified if necessary
    appendUnformattedArg(buf, argExpr, paramType);
  }

  /**
   * Generate code for one list pattern.
   *
   * @param logger logger to use for error/warning messages
   * @param locale locale we are generating code for
   * @param listArg the {n,list,...} argument in the original format pattern
   * @param val0 the expression defining the {0} argument in the list pattern
   * @param val1 the expression defining the {1} argument in the list pattern
   * @param elemType the element type of the list/array being rendered as a list * @param isSafeHtml
   *     true if the resulting string is SafeHtml
   * @param listPattern the list pattern to generate code for, ie "{0}, {1}"
   * @param formatSecond true if the {1} parameter needs to be formatted
   * @param params parameters passed to the Messages method call
   * @return a constructed string containing the code to implement the given list pattern
   * @throws UnableToCompleteException
   */
  private CharSequence formatListPattern(
      final TreeLogger logger,
      final GwtLocale locale,
      final ArgumentChunk listArg,
      final String val0,
      final String val1,
      final TypeMirror elemType,
      final boolean isSafeHtml,
      String listPattern,
      final boolean formatSecond,
      final Parameters params)
      throws UnableToCompleteException {
    final StringBuilder buf = new StringBuilder();
    final StringGenerator gen = StringGenerator.create(buf, isSafeHtml);
    try {
      List<TemplateChunk> chunks = MessageFormatParser.parse(listPattern);
      for (TemplateChunk chunk : chunks) {

        chunk.accept(
            new DefaultTemplateChunkVisitor() {
              @Override
              public void visit(ArgumentChunk argChunk) throws UnableToCompleteException {
                // The {0} argument in the list pattern always needs formatting,
                // but the {1} argument is the part of the list already rendered
                // (in either String of SafeHtml form) unless formatSecond is true.
                if (argChunk.getArgumentNumber() == 0 || formatSecond) {
                  formatArg(
                      logger,
                      locale,
                      gen,
                      listArg,
                      argChunk.getArgumentNumber() == 0 ? val0 : val1,
                      elemType,
                      params);
                } else {
                  gen.appendExpression(val1, isSafeHtml, false, false);
                }
              }

              @Override
              public void visit(StringChunk stringChunk) {
                gen.appendStringLiteral(stringChunk.getString());
              }
            });
      }
    } catch (ParseException e) {
      logger.log(
          TreeLogger.ERROR,
          "Internal error: can't parse list pattern '" + listPattern + "' for locale " + locale,
          e);
      throw new UnableToCompleteException();
    }
    gen.completeString();
    return buf;
  }

  /**
   * Generates code to format a list in a format pattern.
   *
   * @param logger logger to use for error/warning messages
   * @param locale locale we are generating code for
   * @param listArg the {n,list,...} argument in the original format pattern
   * @param elemType the element type of the list/array being rendered as a list
   * @param isSafeHtml true if the resulting string is SafeHtml
   * @param listAccessor a way to access elements of the list type supplied by the user
   * @param params parameters passed to the Messages method call
   * @throws UnableToCompleteException
   */
  private void generateListFormattingCode(
      TreeLogger logger,
      GwtLocale locale,
      ArgumentChunk listArg,
      TypeMirror elemType,
      boolean isSafeHtml,
      ListAccessor listAccessor,
      Parameters params)
      throws UnableToCompleteException {
    Map<String, String> listPatternParts = getListPatternParts(logger, locale);
    int listArgNum = listArg.getArgumentNumber();
    writer.println("int arg" + listArgNum + "_size = " + listAccessor.getSize() + ";");
    if (isSafeHtml) {
      writer.println(
          SafeHtml.class.getCanonicalName()
              + " arg"
              + listArgNum
              + "_list = new "
              + OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml.class.getCanonicalName()
              + "(\"\");");
    } else {
      writer.println("String arg" + listArgNum + "_list = \"\";");
    }
    writer.println("switch (arg" + listArgNum + "_size) {");
    writer.indent();
    // TODO(jat): add support for special-cases besides 2 if CLDR ever adds them
    String pairPattern = listPatternParts.get("2");
    if (pairPattern != null) {
      writer.println("case 2:");
      writer.indent();
      writer.println(
          "  arg"
              + listArgNum
              + "_list = "
              + formatListPattern(
                  logger,
                  locale,
                  listArg,
                  listAccessor.getElement("0"),
                  listAccessor.getElement("1"),
                  elemType,
                  isSafeHtml,
                  pairPattern,
                  true,
                  params)
              + ";");
      writer.println("break;");
      writer.outdent();
    }
    writer.println("default:");
    writer.indent();
    writer.println("int i = arg" + listArgNum + "_size;");
    writer.println("if (i > 0) {");
    writer.indent();
    StringBuilder outbuf = new StringBuilder();
    StringGenerator buf = StringGenerator.create(outbuf, isSafeHtml);
    formatArg(logger, locale, buf, listArg, listAccessor.getElement("--i"), elemType, params);
    buf.completeString();
    writer.println("arg" + listArgNum + "_list = " + outbuf + ";");
    writer.outdent();
    writer.println("}");
    writer.println("if (i > 0) {");
    writer.indent();
    writer.println(
        "arg"
            + listArgNum
            + "_list = "
            + formatListPattern(
                logger,
                locale,
                listArg,
                listAccessor.getElement("--i"),
                "arg" + listArgNum + "_list",
                elemType,
                isSafeHtml,
                listPatternParts.get("end"),
                false,
                params)
            + ";");
    writer.outdent();
    writer.println("}");
    writer.println("while (i > 1) {");
    writer.indent();
    writer.println(
        "arg"
            + listArgNum
            + "_list = "
            + formatListPattern(
                logger,
                locale,
                listArg,
                listAccessor.getElement("--i"),
                "arg" + listArgNum + "_list",
                elemType,
                isSafeHtml,
                listPatternParts.get("middle"),
                false,
                params)
            + ";");
    writer.outdent();
    writer.println("}");
    writer.println("if (i > 0) {");
    writer.indent();
    writer.println(
        "arg"
            + listArgNum
            + "_list = "
            + formatListPattern(
                logger,
                locale,
                listArg,
                listAccessor.getElement("--i"),
                "arg" + listArgNum + "_list",
                elemType,
                isSafeHtml,
                listPatternParts.get("start"),
                false,
                params)
            + ";");
    writer.outdent();
    writer.println("}");
    writer.println("break;");
    writer.outdent();
    writer.outdent();
    writer.println("}");
  }

  /**
   * @param logger
   * @param m
   * @param locale
   * @param resourceEntry
   * @param selectors
   * @param paramsAccessor
   * @param isSafeHtml
   * @param forms
   * @param lastPluralArgNumber index of most recent plural argument, used for processing
   *     inner-plural arguments ({#})
   * @throws UnableToCompleteException
   */
  private void generateMessageSelectors(
      TreeLogger logger,
      ExecutableElement m,
      GwtLocale locale,
      ResourceEntry resourceEntry,
      List<AlternateFormSelector> selectors,
      Parameters paramsAccessor,
      boolean isSafeHtml,
      String[] forms,
      int lastPluralArgNumber)
      throws UnableToCompleteException {
    int numSelectors = selectors.size();
    String[] lastForm = new String[numSelectors];
    for (String form : forms) {
      String[] splitForms = form.split("\\|");
      if (splitForms.length != numSelectors) {
        throw error(
            logger,
            "Incorrect number of selector forms for "
                + m.getSimpleName().toString()
                + " - '"
                + form
                + "'");
      }
      boolean allOther = true;
      for (String splitForm : splitForms) {
        if (splitForm.startsWith("=")) {
          allOther = false;
        } else if (!AlternateMessageSelector.OTHER_FORM_NAME.equals(splitForm)) {
          allOther = false;
        }
      }
      if (allOther) {
        // don't process the all-other case, that is the default return value
        logger.log(
            TreeLogger.WARN,
            "Ignoring supplied alternate form with all"
                + " 'other' values, @DefaultMessage will be used");
        continue;
      }

      // find where the changes are
      int firstDifferent = 0;
      while (firstDifferent < numSelectors
          && splitForms[firstDifferent].equals(lastForm[firstDifferent])) {
        firstDifferent++;
      }

      // close nested selects deeper than where the change was
      for (int i = numSelectors; i-- > firstDifferent; ) {
        if (lastForm[i] != null) {
          selectors.get(i).generateSelectMatchEnd(writer, lastForm[i]);
          if (i > firstDifferent) {
            selectors.get(i).generateSelectEnd(writer);
          }
        }
      }

      // open all the nested selects from here
      for (int i = firstDifferent; i < numSelectors; ++i) {
        if (i > firstDifferent || lastForm[i] == null) {
          selectors.get(i).generateSelectStart(writer, splitForms[i].startsWith("="));
        }
        selectors.get(i).generateSelectMatchStart(writer, logger, splitForms[i]);
        lastForm[i] = splitForms[i];
      }
      writer.print("returnVal = ");
      generateString(
          logger,
          locale,
          resourceEntry.getForm(form),
          paramsAccessor,
          writer,
          isSafeHtml,
          lastPluralArgNumber);
      writer.println(";");
    }
    for (int i = numSelectors; i-- > 0; ) {
      if (lastForm[i] != null) {
        selectors.get(i).generateSelectMatchEnd(writer, lastForm[i]);
        selectors.get(i).generateSelectEnd(writer);
      }
    }
  }

  /**
   * Generate a Java string for a given MessageFormat string.
   *
   * @param logger
   * @param template
   * @param paramsAccessor
   * @param writer
   * @param lastPluralArgNumber index of most recent plural argument, used for processing
   *     inner-plural arguments ({#})
   * @throws UnableToCompleteException
   */
  private void generateString(
      final TreeLogger logger,
      final GwtLocale locale,
      final String template,
      final Parameters paramsAccessor,
      SourceWriter writer,
      final boolean isSafeHtml,
      final int lastPluralArgNumber)
      throws UnableToCompleteException {
    StringBuilder outputBuf = new StringBuilder();
    final StringGenerator buf = StringGenerator.create(outputBuf, isSafeHtml);
    final int n = paramsAccessor.getCount();
    try {
      for (TemplateChunk chunk : MessageFormatParser.parse(template)) {
        chunk.accept(
            new DefaultTemplateChunkVisitor() {
              @Override
              public void visit(ArgumentChunk argChunk) throws UnableToCompleteException {
                int argNumber = argChunk.getArgumentNumber();
                if (argNumber >= n) {
                  throw error(
                      logger, "Argument " + argNumber + " beyond range of arguments: " + template);
                }
                if (argNumber < 0) {
                  if (lastPluralArgNumber < 0) {
                    throw error(
                        logger, "Inner-plural notation {#} used outside in non-plural message");
                  }
                  argNumber = lastPluralArgNumber;
                }
                VariableElement param = paramsAccessor.getParameter(argNumber);
                String arg = "arg" + argNumber;
                if (argChunk.isList()) {
                  buf.appendExpression(arg + "_list", isSafeHtml, false, false);
                } else {
                  formatArg(
                      logger,
                      locale,
                      buf,
                      argChunk,
                      paramsAccessor.getParameterExpression(argNumber),
                      param.asType(),
                      paramsAccessor);
                }
              }

              @Override
              public void visit(StaticArgChunk staticArgChunk) {
                buf.appendStringLiteral(staticArgChunk.getReplacement());
              }

              @Override
              public void visit(StringChunk stringChunk) {
                buf.appendStringLiteral(stringChunk.getString());
              }
            });
      }
    } catch (ParseException e) {
      throw error(logger, e);
    }
    buf.completeString();
    writer.print(outputBuf.toString());
  }

  private Map<String, String> getListPatternParts(TreeLogger logger, GwtLocale locale) {
    Map<String, String> map = listPatternCache.get(locale);
    if (map == null) {

      String baseName = "org/gwtproject/i18n/shared/cldr/impl/ListPatterns_";
      // MessagesMethodCreator.class.getPackage().getName().replace('.', '/')
      //    + "/cldr/ListPatterns_";

      for (GwtLocale search : locale.getCompleteSearchList()) {
        String propFile = baseName + search.getAsString() + ".bak";

        URL url = oracle.findResource(propFile);
        if (url != null) {
          try (InputStream stream = url.openStream()) {
            LocalizedPropertiesLoader loader = new LocalizedPropertiesLoader(stream, "UTF-8");
            map = new HashMap<>();
            loader.load(map);
            break;
          } catch (IOException e) {
            logger.log(TreeLogger.WARN, "Ignoring error reading file " + propFile, e);
          }
        }
      }
      listPatternCache.put(locale, map);
    }
    return map;
  }

  /** Interface used to abstract away differences between accessing an array and a list. */
  private interface ListAccessor {

    String getElement(String element);

    String getSize();
  }

  /** An interface for accessing parameters, giving the ability to record accesses. */
  private interface Parameters {

    /** Allow generated code to take advantage of plural offsets (see {@link Offset}). */
    void enablePluralOffsets();

    /**
     * Return the count of parameters.
     *
     * @return the count of parameters
     */
    int getCount();

    /**
     * Returns the given parameter.
     *
     * @param i index of the parameter to return, 0 .. getCount() - 1
     * @return parameter or null if i is out of range
     */
    VariableElement getParameter(int i);

    /**
     * Returns the given parameter.
     *
     * @param name the name of the parameter to return
     * @return parameter or null if the named parameter doesn't exist
     */
    VariableElement getParameter(String name);

    /**
     * Return an expression to get the value of the requested parameter. Note that for arrays or
     * lists this will return an expression giving the count of items in the array or list.
     *
     * @param i index of the parameter, 0 .. getCount() - 1
     * @return the source of code to access the parameter value
     */
    String getParameterExpression(int i);

    /**
     * Find the index of a parameter by name.
     *
     * @param name
     * @return index of requested parameter or -1 if not found
     */
    int getParameterIndex(String name);
  }

  private interface ValueFormatter {

    /**
     * Creates code to format a value according to a format string.
     *
     * @param logger
     * @param locale current locale
     * @param out StringBuilder to append to
     * @param formatArgs format-specific arguments
     * @param subformat the remainder of the format string
     * @param argName the name of the argument to use in the generated code
     * @param argType the type of the argument
     * @param params argument list or null
     * @return true if a fatal error occurred (which will already be logged)
     */
    boolean format(
        TreeLogger logger,
        GwtLocale locale,
        StringGenerator out,
        Map<String, String> formatArgs,
        String subformat,
        String argName,
        TypeMirror argType,
        Parameters params);
  }

  private abstract static class AlternateFormSelector {

    protected final int argNumber;
    protected final Element argType;

    public AlternateFormSelector(
        TreeLogger logger, int argNumber, List<? extends VariableElement> params) {
      this.argNumber = argNumber;
      this.argType = params.get(argNumber);
    }

    public abstract void generatePrepCode(SourceWriter out);

    public abstract void generateSelectEnd(SourceWriter out);

    public abstract void generateSelectMatchEnd(SourceWriter out, String value);

    /**
     * @param out
     * @param logger
     * @param value
     * @throws UnableToCompleteException
     */
    public abstract void generateSelectMatchStart(SourceWriter out, TreeLogger logger, String value)
        throws UnableToCompleteException;

    public abstract void generateSelectStart(SourceWriter out, boolean exactMatches);

    public abstract void issueWarnings(TreeLogger logger, ExecutableElement m, GwtLocale locale);
  }

  /** Implements {x,date...} references in MessageFormat. */
  private static class DateFormatter implements ValueFormatter {

    public boolean format(
        TreeLogger logger,
        GwtLocale locale,
        StringGenerator out,
        Map<String, String> formatArgs,
        String subformat,
        String argName,
        TypeMirror argType,
        Parameters params) {
      if (!"java.util.Date".equals(argType.toString())) {
        logger.log(TreeLogger.ERROR, "Only java.util.Date acceptable for date format");
        return true;
      }
      String tzParam = "";
      String tzArg = formatArgs.get("tz");
      if (tzArg != null) {
        if (tzArg.startsWith("$")) {
          int paramNum = params.getParameterIndex(tzArg.substring(1));
          if (paramNum < 0) {
            logger.log(TreeLogger.ERROR, "Unable to resolve tz argument " + tzArg);
            return true;
          } else if (!"org.gwtproject.i18n.client.TimeZone"
              .equals(params.getParameter(paramNum).asType().toString())) {
            logger.log(TreeLogger.ERROR, "Currency code parameter must be TimeZone");
            return true;
          } else {
            tzParam = ", arg" + paramNum;
          }
        } else {
          tzParam = ", org.gwtproject.i18n.client.TimeZone.createTimeZone(" + tzArg + ")";
        }
      }
      if (subformat == null || "medium".equals(subformat)) {
        out.appendStringValuedExpression(
            dtFormatClassName + ".getMediumDateFormat()" + ".format(" + argName + tzParam + ")");
      } else if ("full".equals(subformat)) {
        out.appendStringValuedExpression(
            dtFormatClassName + ".getFullDateFormat().format(" + argName + tzParam + ")");
      } else if ("long".equals(subformat)) {
        out.appendStringValuedExpression(
            dtFormatClassName + ".getLongDateFormat().format(" + argName + tzParam + ")");
      } else if ("short".equals(subformat)) {
        out.appendStringValuedExpression(
            dtFormatClassName + ".getShortDateFormat()" + ".format(" + argName + tzParam + ")");
      } else {
        logger.log(TreeLogger.WARN, "Use localdatetime format instead");
        out.appendStringValuedExpression(
            dtFormatClassName
                + ".getFormat("
                + wrap(subformat)
                + ").format("
                + argName
                + tzParam
                + ")");
      }
      return false;
    }
  }

  /**
   * Comparator that ensures all exact value matches (=N) strings come before all non-exact matches.
   */
  private static class ExactValueComparator implements Comparator<String> {

    private static int compareOne(String a, String b) {
      boolean aExact = a.startsWith("=");
      boolean bExact = a.startsWith("=");
      if (aExact != bExact) {
        return aExact ? -1 : 1;
      }
      if (aExact) {
        return a.substring(1).compareTo(b.substring(1));
      } else {
        return a.compareTo(b);
      }
    }

    public int compare(String a, String b) {
      String[] aSplit = a.split("\\|");
      String[] bSplit = b.split("\\|");
      int c = 0;
      for (int i = 0; c == 0 && i < aSplit.length && i < bSplit.length; ++i) {
        c = compareOne(aSplit[i], bSplit[i]);
      }
      if (c == 0 && aSplit.length != bSplit.length) {
        c = aSplit.length < bSplit.length ? -1 : 1;
      }
      return c;
    }
  }

  /** An {@link AlternateFormSelector} used with {@link Select}. */
  private static class GenericSelector extends AlternateFormSelector {

    private final TypeElement enumType;
    private final boolean isBoolean;
    private final boolean isString;
    private final boolean needsIf;
    private boolean startedIfChain;

    /**
     * @param logger
     * @param m
     * @param i
     * @param params
     * @throws UnableToCompleteException
     */
    public GenericSelector(
        TreeLogger logger, ExecutableElement m, int i, List<? extends VariableElement> params)
        throws UnableToCompleteException {
      super(logger, i, params);

      TypeElement classType = null;
      TypeElement tempEnumType = null;
      boolean tempIsBoolean = false;
      boolean tempIsString = false;
      boolean tempNeedsIf = false;

      if (!argType.getKind().equals(ElementKind.PARAMETER)) {
        throw new NullPointerException(argType.toString());
      }

      TypeMirror type = MoreElements.asVariable(argType).asType();

      if (type.getKind().isPrimitive()) {
        if (type.getKind().equals(TypeKind.DOUBLE) || type.getKind().equals(TypeKind.FLOAT)) {
          throw error(
              logger,
              m.getSimpleName()
                  + "in "
                  + m.getEnclosingElement()
                  + " : @Select arguments may only be"
                  + " integral primitives, boolean, enums, or String");
        }
        tempIsBoolean = type.getKind().equals(TypeKind.BOOLEAN);
        tempNeedsIf = tempIsBoolean || type.getKind().equals(TypeKind.LONG);
      } else if (type.getKind().equals(TypeKind.DECLARED)
          || MoreTypes.asElement(type).getKind().equals(ElementKind.ENUM)) {
        if (MoreTypes.asElement(type).getKind().equals(ElementKind.ENUM)) {
          tempEnumType = MoreTypes.asTypeElement(type);
        }
        tempIsString = "java.lang.String".equals(type.toString());
        if (tempEnumType == null && !tempIsString) {
          throw error(
              logger,
              m.getSimpleName()
                  + "in "
                  + m.getEnclosingElement()
                  + " : @Select arguments may only be"
                  + " integral primitives, boolean, enums, or String");
        }
      } else {
        throw error(
            logger,
            m.getSimpleName()
                + "in "
                + m.getEnclosingElement()
                + " 2: @Select arguments may only be"
                + " integral primitives, boolean, enums, or String");
      }

      tempNeedsIf |= tempIsString;
      enumType = tempEnumType;
      isBoolean = tempIsBoolean;
      isString = tempIsString;
      needsIf = tempNeedsIf;
    }

    @Override
    public void generatePrepCode(SourceWriter out) {
      if (enumType != null) {
        out.println("int arg" + argNumber + "_ordinal = -1;");
        out.println("if (arg" + argNumber + " != null) {");
        out.indent();
        out.println("arg" + argNumber + "_ordinal = arg" + argNumber + ".ordinal();");
        out.outdent();
        out.println("}");
      }
    }

    @Override
    public void generateSelectEnd(SourceWriter out) {
      if (!startedIfChain) {
        out.outdent();
      }
      out.println("}");
    }

    @Override
    public void generateSelectMatchEnd(SourceWriter out, String value) {
      if (!startedIfChain) {
        out.println("break;");
      }
      out.outdent();
    }

    @Override
    public void generateSelectMatchStart(SourceWriter out, TreeLogger logger, String value)
        throws UnableToCompleteException {
      if (needsIf) {
        if (startedIfChain) {
          out.print("} else ");
        } else {
          startedIfChain = true;
        }
        if (AlternateMessageSelector.OTHER_FORM_NAME.equals(value)) {
          out.println("{  // other");
        } else {
          if (isString) {
            value = value.replace("\"", "\\\"");
            out.println("if (\"" + value + "\".equals(arg" + argNumber + ")) {");
          } else if (isBoolean) {
            boolean isTrue = Boolean.parseBoolean(value);
            out.println("if (" + (isTrue ? "" : "!") + "arg" + argNumber + ") {");
          } else {
            long longVal;
            try {
              longVal = Long.parseLong(value);
            } catch (NumberFormatException e) {
              throw error(logger, "'" + value + "' is not a valid long value", e);
            }
            out.println("if (" + longVal + " == arg" + argNumber + ") {");
          }
        }
      } else {
        if (AlternateMessageSelector.OTHER_FORM_NAME.equals(value)) {
          out.println("default:  // other");
        } else if (enumType != null) {
          final String lookup = value;

          Element field =
              enumType.getEnclosedElements().stream()
                  .filter(elm -> elm.getKind().equals(ElementKind.ENUM_CONSTANT))
                  .filter(elm -> elm.getSimpleName().toString().equals(lookup))
                  .findFirst()
                  .orElseThrow(
                      () ->
                          error(
                              logger,
                              "'"
                                  + lookup
                                  + "' is not a valid value of "
                                  + enumType.getQualifiedName().toString()
                                  + " or 'other'"));
          out.println(
              "case "
                  + MoreTypeUtils.getEnumConstantOrdinal(enumType, field.toString())
                  + ":  // "
                  + value);
        } else {
          int intVal;
          try {
            intVal = Integer.parseInt(value);
          } catch (NumberFormatException e) {
            throw error(logger, "'" + value + "' is not a valid integral value", e);
          }
          out.println("case " + intVal + ":");
        }
      }
      out.indent();
    }

    @Override
    public void generateSelectStart(SourceWriter out, boolean exactMatches) {
      // ignore exactMatches, so "=VALUE" is the same as "VALUE"
      if (needsIf) {
        startedIfChain = false;
        return;
      }
      String suffix = "";
      if (enumType != null) {
        suffix = "_ordinal";
      }
      out.println("switch (arg" + argNumber + suffix + ") {");
      out.indent();
    }

    @Override
    public void issueWarnings(TreeLogger logger, ExecutableElement m, GwtLocale locale) {
      // nothing to warn about
    }
  }

  /** Implementation of ListAccessor for an array. */
  private static class ListAccessorArray implements ListAccessor {

    private final int listArgNum;

    public ListAccessorArray(int listArgNum) {
      this.listArgNum = listArgNum;
    }

    public String getElement(String element) {
      return "arg" + listArgNum + "[" + element + "]";
    }

    public String getSize() {
      return "arg" + listArgNum + ".length";
    }
  }

  /** Implementation of ListAccessor for a List. */
  private static class ListAccessorList implements ListAccessor {

    private final int listArgNum;

    public ListAccessorList(int listArgNum) {
      this.listArgNum = listArgNum;
    }

    public String getElement(String element) {
      return "arg" + listArgNum + ".get(" + element + ")";
    }

    public String getSize() {
      return "arg" + listArgNum + ".size()";
    }
  }

  /** Implements {x,localdatetime,skeleton} references in MessageFormat. */
  private static class LocalDateTimeFormatter implements ValueFormatter {

    private static final String PREDEF = "predef:";

    public boolean format(
        TreeLogger logger,
        GwtLocale locale,
        StringGenerator out,
        Map<String, String> formatArgs,
        String subformat,
        String argName,
        TypeMirror argType,
        Parameters params) {
      if (!"java.util.Date".equals(argType.toString())) {
        logger.log(TreeLogger.ERROR, "Only java.util.Date acceptable for localdatetime format");
        return true;
      }
      if (subformat == null || subformat.length() == 0) {
        logger.log(TreeLogger.ERROR, "localdatetime format requires a skeleton pattern");
        return true;
      }
      String tzParam = "";
      String tzArg = formatArgs.get("tz");
      if (tzArg != null) {
        if (tzArg.startsWith("$")) {
          int paramNum = params.getParameterIndex(tzArg.substring(1));
          if (paramNum < 0) {
            logger.log(TreeLogger.ERROR, "Unable to resolve tz argument " + tzArg);
            return true;
          } else if (!"org.gwtproject.i18n.client.TimeZone"
              .equals(params.getParameter(paramNum).asType().toString())) {
            logger.log(TreeLogger.ERROR, "tz parameter must be of type TimeZone");
            return true;
          } else {
            tzParam = ", arg" + paramNum;
          }
        } else {
          tzParam = ", org.gwtproject.i18n.client.TimeZone.createTimeZone(" + tzArg + ")";
        }
      }
      if (subformat.startsWith(PREDEF)) {
        // TODO(jat): error checking/logging
        DateTimeFormat.PredefinedFormat predef;
        try {
          predef = DateTimeFormat.PredefinedFormat.valueOf(subformat.substring(PREDEF.length()));
        } catch (IllegalArgumentException e) {
          logger.log(TreeLogger.ERROR, "Unrecognized predefined format '" + subformat + "'");
          return true;
        }
        out.appendStringValuedExpression(
            dtFormatClassName
                + ".getFormat("
                + DateTimeFormat.PredefinedFormat.class.getCanonicalName()
                + "."
                + predef.toString()
                + ").format("
                + argName
                + tzParam
                + ")");
        return false;
      }
      DateTimePatternGenerator dtpg = new DateTimePatternGenerator(locale);
      try {
        String pattern = dtpg.getBestPattern(subformat);
        if (pattern == null) {
          logger.log(
              TreeLogger.ERROR, "Invalid localdatetime skeleton pattern \"" + subformat + "\"");
          return true;
        }
        out.appendStringValuedExpression(
            dtFormatClassName
                + ".getFormat("
                + wrap(pattern)
                + ").format("
                + argName
                + tzParam
                + ")");
      } catch (IllegalArgumentException e) {
        logger.log(TreeLogger.ERROR, "Unable to parse '" + subformat + ": " + e.getMessage());
        return true;
      }
      return false;
    }
  }

  /** Implements {x,number...} references in MessageFormat. */
  private static class NumberFormatter implements ValueFormatter {

    private AptContext context;

    NumberFormatter(AptContext context) {
      this.context = context;
    }

    public boolean format(
        TreeLogger logger,
        GwtLocale locale,
        StringGenerator out,
        Map<String, String> formatArgs,
        String subformat,
        String argName,
        TypeMirror argType,
        Parameters params) {
      if (argType.getKind().isPrimitive()) {
        if (argType.getKind().equals(TypeKind.BOOLEAN) || argType.getKind().equals(TypeKind.VOID)) {
          logger.log(TreeLogger.ERROR, "Illegal argument type for number format");
          return true;
        }
      } else {

        if (!MoreTypes.isType(argType)) {
          logger.log(TreeLogger.ERROR, "Unexpected argument type for number format");
          return true;
        }

        TypeElement numberType = context.elements.getTypeElement("java.lang.Number");
        if (!context.types.isAssignable(
            MoreTypes.asTypeElement(argType).asType(), numberType.asType())) {
          logger.log(TreeLogger.ERROR, "Only Number subclasses may be formatted as a number");
          return true;
        }
      }
      String curCodeParam = "";
      String curCode = formatArgs.get("curcode");

      if (curCode != null) {
        if (curCode.startsWith("$")) {
          int paramNum = params.getParameterIndex(curCode.substring(1));
          if (paramNum < 0) {
            logger.log(TreeLogger.ERROR, "Unable to resolve curcode argument " + curCode);
            return true;
          } else if (!"java.lang.String"
              .equals(params.getParameter(paramNum).asType().toString())) {
            logger.log(TreeLogger.ERROR, "Currency code parameter must be String");
            return true;
          } else {
            curCodeParam = "arg" + paramNum;
          }
        } else {
          curCodeParam = '"' + curCode + '"';
        }
      }
      if (subformat == null) {
        out.appendStringValuedExpression(
            numFormatClassName + ".getDecimalFormat().format(" + argName + ")");
      } else if ("integer".equals(subformat)) {
        out.appendStringValuedExpression(
            numFormatClassName
                + ".getDecimalFormat().overrideFractionDigits(0).format("
                + argName
                + ")");
      } else if ("currency".equals(subformat)) {
        out.appendStringValuedExpression(
            numFormatClassName
                + ".getCurrencyFormat("
                + curCodeParam
                + ").format("
                + argName
                + ")");
      } else if ("percent".equals(subformat)) {
        out.appendStringValuedExpression(
            numFormatClassName + ".getPercentFormat().format(" + argName + ")");
      } else {
        if (curCodeParam.length() > 0) {
          curCodeParam = ", " + curCodeParam;
        }
        out.appendStringValuedExpression(
            numFormatClassName
                + ".getFormat("
                + wrap(subformat)
                + curCodeParam
                + ").format("
                + argName
                + ")");
      }
      return false;
    }
  }

  private static class ParametersImpl implements Parameters {

    private List<? extends VariableElement> params;
    private boolean[] seenFlag;
    private int[] offset;
    private boolean[] isList;
    private boolean[] isArray;
    private boolean enablePluralOffsets;

    public ParametersImpl(List<? extends VariableElement> params, boolean[] seenFlag) {
      this.params = params;
      this.seenFlag = seenFlag;
      int n = params.size();
      offset = new int[n];
      isList = new boolean[n];
      isArray = new boolean[n];
      for (int i = 0; i < n; ++i) {
        Offset offsetAnnot = params.get(i).getAnnotation(Offset.class);
        if (offsetAnnot != null) {
          offset[i] = offsetAnnot.value();
        }

        TypeMirror type = params.get(i).asType();
        if (type.getKind().equals(TypeKind.ARRAY)) {
          isArray[i] = true;
        } else if (!type.getKind().isPrimitive()
            && MoreTypes.asElement(type).getKind().isInterface()) {
          if ("java.util.List"
              .equals(MoreTypes.asTypeElement(type).getQualifiedName().toString())) {
            isList[i] = true;
          }
        }
      }
    }

    public void enablePluralOffsets() {
      enablePluralOffsets = true;
    }

    public int getCount() {
      return params.size();
    }

    public VariableElement getParameter(int i) {
      if (i < 0 || i >= params.size()) {
        return null;
      }
      seenFlag[i] = true;
      return params.get(i);
    }

    public VariableElement getParameter(String name) {
      return getParameter(getParameterIndex(name));
    }

    public String getParameterExpression(int i) {
      if (i < 0 || i >= params.size()) {
        return null;
      }
      String argName = "arg" + i;
      seenFlag[i] = true;
      if (enablePluralOffsets && offset[i] != 0) {
        return argName + "_count";
      }
      if (isArray[i]) {
        return argName + ".length";
      }
      if (isList[i]) {
        return argName + ".size()";
      }
      return argName;
    }

    public int getParameterIndex(String name) {
      for (int i = 0; i < params.size(); ++i) {
        if (params.get(i).getSimpleName().toString().equals(name)) {
          return i;
        }
      }
      return -1;
    }
  }

  /** An {@link AlternateFormSelector} used with {@link PluralCount}. */
  private static class PluralFormSelector extends AlternateFormSelector {

    protected final String countSuffix;
    protected final String listSuffix;
    protected final Set<String> missingPluralForms;
    protected final int pluralOffset;
    protected final PluralRule pluralRule;
    private boolean hasExactMatches;
    private boolean inExactMatches;

    // used to generate unique case values for bogus plural forms
    private int bogusCaseValue = 1000;

    public PluralFormSelector(
        TreeLogger logger,
        GeneratorContext context,
        ExecutableElement method,
        int argNumber,
        List<? extends VariableElement> params,
        GwtLocale locale)
        throws UnableToCompleteException {
      super(logger, argNumber, params);
      PluralCount pluralCount = params.get(argNumber).getAnnotation(PluralCount.class);

      TypeElement ruleClass = getPluralCountValue(pluralCount);
      if (ruleClass.getQualifiedName().toString().equals(PluralRule.class.getCanonicalName())) {
        ruleClass =
            context.getAptContext().elements.getTypeElement(DefaultRule.class.getCanonicalName());
      }

      pluralRule =
          createLocalizedPluralRule(
              logger, context,
              ruleClass, locale);
      missingPluralForms = new HashSet<>();
      for (PluralForm form : pluralRule.pluralForms()) {
        if (form.getWarnIfMissing()
            && !AlternateMessageSelector.OTHER_FORM_NAME.equals(form.getName())) {
          missingPluralForms.add(form.getName());
        }
      }

      Offset offsetAnnot = params.get(argNumber).getAnnotation(Offset.class);
      int offset = 0;
      if (offsetAnnot != null) {
        offset = offsetAnnot.value();
      }
      this.pluralOffset = offset;
      boolean isArray = false;
      boolean isList = false;

      Element classType = null;
      if (!argType.asType().getKind().isPrimitive()
          && !argType.asType().getKind().equals(TypeKind.ARRAY)
          && MoreTypes.asTypeElement(argType.asType()).getKind().isInterface()) {
        if ("java.util.List"
            .equals(context.getAptContext().types.erasure(argType.asType()).toString())) {
          isList = true;
        } else {
          classType = null;
        }
      }

      if (argType.asType().getKind().equals(TypeKind.ARRAY)) {
        isArray = true;
      }
      if (!isList
          && !isArray
          && !argType.asType().getKind().equals(TypeKind.INT)
          && !argType.asType().getKind().equals(TypeKind.SHORT)) {
        throw error(
            logger,
            method.getSimpleName()
                + " in "
                + method.getEnclosingElement()
                + ": PluralCount parameter must be int, short, array, or List");
      }
      String tempListSuffix = "";
      if (isList) {
        tempListSuffix = ".size()";
      } else if (isArray) {
        tempListSuffix = ".length";
      }
      String tempCountSuffix = tempListSuffix;
      if (isList || isArray || offset != 0) {
        tempCountSuffix = "_count";
      }
      listSuffix = tempListSuffix;
      countSuffix = tempCountSuffix;
    }

    @Override
    public void generatePrepCode(SourceWriter out) {
      // save a value with the count value, applying an offset if present
      if (countSuffix.length() > 0) {
        out.print("int arg" + argNumber + countSuffix + " = arg" + argNumber + listSuffix);
        if (pluralOffset != 0) {
          out.print(" - " + pluralOffset);
        }
        out.println(";");
      }
      // save the selected plural form
      // TODO(jat): cache instances of the same plural rule?
      out.println(
          "int arg"
              + argNumber
              + "_form = new "
              + pluralRule.getClass().getCanonicalName()
              + "().select(arg"
              + argNumber
              + countSuffix
              + ");");
    }

    @Override
    public void generateSelectEnd(SourceWriter out) {
      if (hasExactMatches && !inExactMatches) {
        // undo extra nesting level
        out.outdent();
        out.println("}");
        out.println("break;");
        out.outdent();
      }
      out.outdent();
      out.println("}");
    }

    @Override
    public void generateSelectMatchEnd(SourceWriter out, String value) {
      out.println("break;");
      out.outdent();
    }

    @Override
    public void generateSelectMatchStart(SourceWriter out, TreeLogger logger, String value)
        throws UnableToCompleteException {
      missingPluralForms.remove(value);
      if (value.startsWith("=")) {
        try {
          long val = Long.parseLong(value.substring(1));
          out.println("case " + val + ":  // " + value);
        } catch (NumberFormatException e) {
          throw error(logger, "Exact match value '" + value + "' must be integral", e);
        }
        out.indent();
        return;
      }

      if (inExactMatches) {
        /*
         * If this is the first non-exact value, create a nested select that
         * chooses the message based on the plural form only if no exact values
         * matched.
         */
        inExactMatches = false;
        out.println("default: // non-exact matches");
        out.indent();
        out.println("switch (arg" + argNumber + "_form) {");
        out.indent();
      }
      if (AlternateMessageSelector.OTHER_FORM_NAME.equals(value)) {
        out.println("default: // other");
        out.indent();
        return;
      }

      PluralForm[] pluralForms = pluralRule.pluralForms();
      for (int i = 0; i < pluralForms.length; ++i) {
        if (pluralForms[i].getName().equals(value)) {
          out.println("case " + i + ":  // " + value);
          out.indent();
          return;
        }
      }
      logger.log(
          TreeLogger.WARN,
          "Plural form '"
              + value
              + "' unknown in "
              + pluralRule.getClass().getCanonicalName()
              + ": ignoring");
      // TODO(jat): perhaps return a failure instead, and let the called skip
      // the nested selector code?  It gets complicated really quick though.
      out.println("case " + (bogusCaseValue++) + ": // unknown plural form '" + value + "'");
      out.indent();
    }

    @Override
    public void generateSelectStart(SourceWriter out, boolean hasExactMatches) {
      this.hasExactMatches = hasExactMatches;
      inExactMatches = hasExactMatches;
      String suffix = hasExactMatches ? listSuffix : "_form";
      out.println("switch (arg" + argNumber + suffix + ") {");
      out.indent();
    }

    public PluralForm[] getPluralForms() {
      return pluralRule.pluralForms();
    }

    @Override
    public void issueWarnings(TreeLogger logger, ExecutableElement m, GwtLocale locale) {
      if (!missingPluralForms.isEmpty()) {
        // TODO(jat): avoid giving warnings for values that are not necessary
        // due to exact value matches.  For example, in English there is no need
        // for ONE if the =1 value was given, and it may be important to have
        // the =1 value across all locales.
        logger.log(
            TreeLogger.WARN,
            "In locale '" + locale + "', required plural forms are missing: " + missingPluralForms);
      }
    }
  }

  /** Implements {x,time...} references in MessageFormat. */
  private static class TimeFormatter implements ValueFormatter {

    public boolean format(
        TreeLogger logger,
        GwtLocale locale,
        StringGenerator out,
        Map<String, String> formatArgs,
        String subformat,
        String argName,
        TypeMirror argType,
        Parameters params) {
      if (!"java.util.Date".equals(argType.toString())) {
        logger.log(TreeLogger.ERROR, "Only java.util.Date acceptable for date format");
        return true;
      }
      String tzParam = "";
      String tzArg = formatArgs.get("tz");
      if (tzArg != null) {
        if (tzArg.startsWith("$")) {
          int paramNum = params.getParameterIndex(tzArg.substring(1));
          if (paramNum < 0) {
            logger.log(TreeLogger.ERROR, "Unable to resolve tz argument " + tzArg);
            return true;
          } else if (!"org.gwtproject.i18n.client.TimeZone"
              .equals(params.getParameter(paramNum).asType().toString())) {
            logger.log(TreeLogger.ERROR, "Currency code parameter must be TimeZone");
            return true;
          } else {
            tzParam = ", arg" + paramNum;
          }
        } else {
          tzParam = ", org.gwtproject.i18n.client.TimeZone.createTimeZone(" + tzArg + ")";
        }
      }
      if (subformat == null || "medium".equals(subformat)) {
        out.appendStringValuedExpression(
            dtFormatClassName + ".getMediumTimeFormat().format(" + argName + tzParam + ")");
      } else if ("full".equals(subformat)) {
        out.appendStringValuedExpression(
            dtFormatClassName + ".getFullTimeFormat().format(" + argName + tzParam + ")");
      } else if ("long".equals(subformat)) {
        out.appendStringValuedExpression(
            dtFormatClassName + ".getLongTimeFormat().format(" + argName + tzParam + ")");
      } else if ("short".equals(subformat)) {
        out.appendStringValuedExpression(
            dtFormatClassName + ".getShortTimeFormat().format(" + argName + tzParam + ")");
      } else {
        logger.log(TreeLogger.WARN, "Use localdatetime format instead");
        out.appendStringValuedExpression(
            dtFormatClassName
                + ".getFormat("
                + wrap(subformat)
                + ").format("
                + argName
                + tzParam
                + ")");
      }
      return false;
    }
  }
}
