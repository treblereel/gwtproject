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

import static org.gwtproject.i18n.rg.rebind.AbstractResource.*;

import com.google.common.collect.Lists;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import org.gwtproject.i18n.ext.GeneratorContext;
import org.gwtproject.i18n.ext.TreeLogger;
import org.gwtproject.i18n.ext.UnableToCompleteException;
import org.gwtproject.i18n.rg.util.SourceWriter;
import org.gwtproject.i18n.server.Type;
import org.gwtproject.i18n.shared.GwtLocale;

class ConstantsWithLookupImplCreator extends ConstantsImplCreator {

  /**
   * Used partition size if no one is specified.
   *
   * <p>Used in constructor without a partition size.
   */
  private static final int DEFAULT_PARTITIONS_SIZE = 500;

  final ExecutableElement[] allInterfaceMethods;

  private final Map<String, LookupMethodCreator> namesToMethodCreators = new HashMap<>();

  private final Map<ExecutableElement, List<List<ExecutableElement>>> neededPartitionLookups =
      new HashMap<>();

  private final int partitionsSize;

  private GeneratorContext context;

  /**
   * Constructor for <code>ConstantsWithLookupImplCreator</code>. The default partition size of
   * {@value #DEFAULT_PARTITIONS_SIZE} is used.
   *
   * @param logger logger to print errors
   * @param writer <code>Writer</code> to print to
   * @param localizableClass class/interface to conform to
   * @param resourceList resource bundle used to generate the class
   * @throws UnableToCompleteException
   * @see LookupMethodCreator#DEFAULT_PARTITIONS_SIZE
   */
  ConstantsWithLookupImplCreator(
      TreeLogger logger,
      SourceWriter writer,
      TypeElement localizableClass,
      ResourceList resourceList,
      GeneratorContext context)
      throws UnableToCompleteException {
    this(logger, writer, localizableClass, resourceList, context, DEFAULT_PARTITIONS_SIZE);
  }

  /**
   * Constructor for <code>ConstantsWithLookupImplCreator</code>.
   *
   * @param logger logger to print errors
   * @param writer <code>Writer</code> to print to
   * @param localizableClass class/interface to conform to
   * @param resourceList resource bundle used to generate the class
   * @throws UnableToCompleteException
   */
  ConstantsWithLookupImplCreator(
      TreeLogger logger,
      SourceWriter writer,
      TypeElement localizableClass,
      ResourceList resourceList,
      GeneratorContext context,
      int partitionsSize)
      throws UnableToCompleteException {
    super(logger, context.getAptContext(), writer, localizableClass, resourceList);
    this.partitionsSize = partitionsSize;
    this.context = context;
    // Boolean
    LookupMethodCreator booleanMethod =
        new LookupMethodCreator(this, Type.BOOLEAN) {
          @Override
          public void printReturnTarget() {
            println("return target.booleanValue();");
          }

          @Override
          public String returnTemplate() {
            return "boolean answer = {0}();\n"
                + "cache.put(\"{0}\",new Boolean(answer));\n"
                + "return answer;";
          }
        };
    namesToMethodCreators.put("getBoolean", booleanMethod);

    // Double
    LookupMethodCreator doubleMethod =
        new LookupMethodCreator(this, Type.DOUBLE) {
          @Override
          public void printReturnTarget() {
            println("return target.doubleValue();");
          }

          @Override
          public String returnTemplate() {
            return "double answer = {0}();\n"
                + "cache.put(\"{0}\",new Double(answer));\n"
                + "return answer;";
          }
        };
    namesToMethodCreators.put("getDouble", doubleMethod);

    // Int
    LookupMethodCreator intMethod =
        new LookupMethodCreator(this, Type.INT) {
          @Override
          public void printReturnTarget() {
            println("return target.intValue();");
          }

          @Override
          public String returnTemplate() {
            return "int answer = {0}();\n"
                + "cache.put(\"{0}\",new Integer(answer));\n"
                + "return answer;";
          }
        };

    namesToMethodCreators.put("getInt", intMethod);

    // Float
    LookupMethodCreator floatMethod =
        new LookupMethodCreator(this, Type.FLOAT) {
          @Override
          public String returnTemplate() {
            String val =
                "float answer = {0}();\n"
                    + "cache.put(\"{0}\", new Float(answer));\n"
                    + "return answer;";
            return val;
          }

          @Override
          protected void printReturnTarget() {
            println("return target.floatValue();");
          }
        };
    namesToMethodCreators.put("getFloat", floatMethod);

    // Map - use erased type for matching
    namesToMethodCreators.put(
        "getMap",
        new LookupMethodCreator(this, Type.STRING_MAP) {
          @Override
          public String getReturnTypeName() {
            return ConstantsMapMethodCreator.GENERIC_STRING_MAP_TYPE;
          }
        });

    // String
    LookupMethodCreator stringMethod =
        new LookupMethodCreator(this, Type.STRING) {
          @Override
          public String returnTemplate() {
            return "String answer = {0}();\n" + "cache.put(\"{0}\",answer);\n" + "return answer;";
          }
        };
    namesToMethodCreators.put("getString", stringMethod);

    namesToMethodCreators.put(
        "getStringArray",
        new LookupMethodCreator(
            this, new Type.ArrayType(String[].class.getCanonicalName(), Type.STRING)));

    setNeedCache(true);
    allInterfaceMethods = getAllInterfaceMethods(localizableClass);
  }

  @Override
  protected void classEpilog() {
    createNeededPartitionLookups();
    super.classEpilog();
  }

  /** Create the method body associated with the given method. Arguments are arg0...argN. */
  @Override
  protected void emitMethodBody(TreeLogger logger, ExecutableElement method, GwtLocale locale)
      throws UnableToCompleteException {
    checkMethod(logger, method);
    if (method.getParameters().size() == 1) {
      String name = method.getSimpleName().toString();
      LookupMethodCreator c = getLookupMethodCreator(name);
      if (c != null) {
        createMethodWithPartitionCheckFor(c, method);
        return;
      }
    }
    // fall through
    super.emitMethodBody(logger, method, locale);
  }

  void addNeededPartitionLookups(
      ExecutableElement targetMethod,
      List<List<ExecutableElement>> methodToCreatePartitionLookups) {
    neededPartitionLookups.put(targetMethod, methodToCreatePartitionLookups);
  }

  void createMethodWithPartitionCheckFor(
      LookupMethodCreator methodCreator, ExecutableElement targetMethod) {
    List<List<ExecutableElement>> methodPartitions =
        findMethodsToCreateWithPartitionSize(targetMethod, methodCreator.getReturnType());

    String nextPartitionMethod = null;
    final List<List<ExecutableElement>> methodToCreatePartitionLookups;
    final List<ExecutableElement> methodsToCreate;
    if (methodPartitions.size() > 1) {
      nextPartitionMethod = createPartitionMethodName(targetMethod, 0);
      methodsToCreate = methodPartitions.get(0);
      methodToCreatePartitionLookups = methodPartitions.subList(1, methodPartitions.size());
    } else {
      methodsToCreate =
          methodPartitions.isEmpty() ? Collections.emptyList() : methodPartitions.get(0);
      methodToCreatePartitionLookups = Collections.emptyList();
    }
    addNeededPartitionLookups(targetMethod, methodToCreatePartitionLookups);
    methodCreator.createCacheLookupFor();
    methodCreator.createMethodFor(targetMethod, methodsToCreate, nextPartitionMethod);
  }

  String createPartitionMethodName(ExecutableElement targetMethod, int partitionIndex) {
    final String templatePartitionMethodName = "{0}FromPartition{1}";
    return MessageFormat.format(
        templatePartitionMethodName,
        new Object[] {targetMethod.getSimpleName().toString(), partitionIndex});
  }

  List<ExecutableElement> findAllMethodsToCreate(
      ExecutableElement targetMethod, Type methodReturnType) {
    ExecutableElement[] allMethods = allInterfaceMethods;
    // TypeMirror erasedType = methodReturnType;
    List<ExecutableElement> methodsToCreate = new ArrayList<>();
    for (ExecutableElement methodToCheck : allMethods) {
      if (methodToCheck.getReturnType().toString().equals(methodReturnType.getSourceName())
          && methodToCheck != targetMethod) {
        methodsToCreate.add(methodToCheck);
      }
    }
    return methodsToCreate;
  }

  private boolean compareMethods(ExecutableElement method1, ExecutableElement method2) {
    if (method1.getSimpleName().equals(method2.getSimpleName())) {
      return true;
    }
    return false;
  }

  List<List<ExecutableElement>> findMethodsToCreateWithPartitionSize(
      ExecutableElement targetMethod, Type methodReturnType) {
    List<ExecutableElement> allMethodsToCreate =
        findAllMethodsToCreate(targetMethod, methodReturnType);
    return Lists.partition(allMethodsToCreate, partitionsSize);
  }

  LookupMethodCreator getLookupMethodCreator(String name) {
    return namesToMethodCreators.get(name);
  }

  /** Visible for testing only. */
  Map<ExecutableElement, List<List<ExecutableElement>>> getNeededPartitionLookups() {
    return neededPartitionLookups;
  }

  /**
   * Checks that the method has the right structure to implement <code>Constant</code>.
   *
   * @param method method to check
   */
  private void checkMethod(TreeLogger logger, ExecutableElement method)
      throws UnableToCompleteException {
    if (getLookupMethodCreator(method.getSimpleName().toString()) != null) {
      List<? extends VariableElement> params = method.getParameters();
      // user may have specified a method named getInt/etc with no parameters
      // this isn't a conflict, so treat them like any other Constant methods
      if (params.size() == 0) {
        checkConstantMethod(logger, method);
      } else {
        if (params.size() != 1 || !params.get(0).asType().toString().equals("java.lang.String")) {
          throw error(logger, method + " must have a single String argument.");
        }
        checkReturnType(logger, method);
      }
    } else {
      checkConstantMethod(logger, method);
    }
  }

  private void createNeededPartitionLookups() {
    for (Entry<ExecutableElement, List<List<ExecutableElement>>> neededPartitionLookup :
        neededPartitionLookups.entrySet()) {
      ExecutableElement targetMethod = neededPartitionLookup.getKey();
      LookupMethodCreator lookupMethodCreator =
          getLookupMethodCreator(targetMethod.getSimpleName().toString());
      List<List<ExecutableElement>> methodForPartitionLookups = neededPartitionLookup.getValue();
      int partitionStartIndex = 0;
      Iterator<List<ExecutableElement>> neededPartitionIterator =
          methodForPartitionLookups.iterator();
      while (neededPartitionIterator.hasNext()) {
        String currentPartitionLookupMethodName =
            createPartitionMethodName(targetMethod, partitionStartIndex++);
        List<ExecutableElement> methodsToCreate = neededPartitionIterator.next();
        String nextPartitionMethod = null;
        if (neededPartitionIterator.hasNext()) {
          nextPartitionMethod = createPartitionMethodName(targetMethod, partitionStartIndex);
        }
        lookupMethodCreator.createPartitionLookup(
            currentPartitionLookupMethodName, targetMethod, methodsToCreate, nextPartitionMethod);
      }
    }
  }
}
