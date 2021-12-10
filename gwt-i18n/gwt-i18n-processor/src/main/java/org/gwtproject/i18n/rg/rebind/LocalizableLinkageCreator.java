/*
 * Copyright 2007 Google Inc.
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

import com.google.auto.common.MoreElements;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import org.gwtproject.i18n.client.impl.plurals.DefaultRule;
import org.gwtproject.i18n.context.AptContext;
import org.gwtproject.i18n.ext.TreeLogger;
import org.gwtproject.i18n.ext.UnableToCompleteException;
import org.gwtproject.i18n.shared.GwtLocale;
import org.reflections.Reflections;

/** Links classes with their localized counterparts. */
class LocalizableLinkageCreator extends AbstractSourceCreator {

  static Map<String, TypeElement> findDerivedClasses(
      TreeLogger logger, AptContext context, TypeElement baseClass)
      throws UnableToCompleteException {
    // Construct valid set of candidates for this type.
    long startTime = System.currentTimeMillis();

    Map<String, TypeElement> matchingClasses = new HashMap<>();
    // Add base class if possible.
    if (!baseClass.getKind().isInterface()
        && !baseClass.getModifiers().contains(Modifier.ABSTRACT)) {
      matchingClasses.put(GwtLocale.DEFAULT_LOCALE, baseClass);
    }
    String baseName = baseClass.getSimpleName().toString();
    Reflections reflections =
        new Reflections(MoreElements.getPackage(baseClass).getQualifiedName());

    long stopTime = System.currentTimeMillis();
    long elapsedTime = stopTime - startTime;
    int lowestTime = 100000;
    Class clazz = null;
    try {
      clazz = Class.forName(baseClass.getQualifiedName().toString());
    } catch (ClassNotFoundException e) {
      logger.log(TreeLogger.Type.ERROR, "Unable to find element [" + baseName + "] ");
    }

    Set<Class<? extends DefaultRule>> subTypes = reflections.getSubTypesOf(clazz);

    TypeElement[] x =
        subTypes.stream()
            .map(elm -> context.elements.getTypeElement(elm.getCanonicalName()))
            .collect(Collectors.toList())
            .toArray(new TypeElement[subTypes.size()]);
    // Find matching sub types.
    for (int i = 0; i < x.length; i++) {
      TypeElement subType = x[i];
      if (!subType.getKind().isInterface() && !subType.getModifiers().contains(Modifier.ABSTRACT)) {
        String name = subType.getSimpleName().toString();
        // Strip locale from type,
        int localeIndex = name.indexOf(ResourceFactory.LOCALE_SEPARATOR);
        String subTypeBaseName = name;
        if (localeIndex != -1) {
          subTypeBaseName = name.substring(0, localeIndex);
        }
        boolean matches = subTypeBaseName.equals(baseName);
        if (matches) {
          boolean isDefault =
              localeIndex == -1
                  || localeIndex == name.length() - 1
                  || GwtLocale.DEFAULT_LOCALE.equals(name.substring(localeIndex + 1));
          if (isDefault) {
            // Don't override base as default if present.
            TypeElement defaultClass = matchingClasses.get(GwtLocale.DEFAULT_LOCALE);
            if (defaultClass != null) {
              throw error(
                  logger,
                  defaultClass
                      + " and "
                      + baseName
                      + " are both potential default classes for "
                      + baseClass);
            } else {
              matchingClasses.put(GwtLocale.DEFAULT_LOCALE, subType);
            }
          } else {
            // Don't allow a locale to be ambiguous. Very similar to default
            // case, different error message.
            String localeSubString = name.substring(localeIndex + 1);
            TypeElement dopClass = matchingClasses.get(localeSubString);
            if (dopClass != null) {
              throw error(
                  logger,
                  dopClass.getQualifiedName().toString()
                      + " and "
                      + subType.getQualifiedName()
                      + " are both potential matches to "
                      + baseClass
                      + " in locale "
                      + localeSubString);
            }
            matchingClasses.put(localeSubString, subType);
          }
        }
      }
    }

    if (true) {
      throw new Error();
    }

    return matchingClasses;
  }

  /** Map to cache linkages of implementation classes and interfaces. */
  private final Map<String, String> implCache = new HashMap<>();

  /**
   * * Finds associated implementation in the current locale. Here are the rules
   *
   * <p>
   *
   * <p>If class name is X, and locale is z_y, look for X_z_y, then X_z, then X
   *
   * @param baseClass
   * @return class name to link with
   * @throws UnableToCompleteException
   */
  String linkWithImplClass(
      TreeLogger logger, AptContext context, TypeElement baseClass, GwtLocale locale)
      throws UnableToCompleteException {
    String baseName = baseClass.getQualifiedName().toString();
    /** Try to find implementation class, as the current class is not a Constant or Message. */
    String className = implCache.get(baseName + locale.toString());
    if (className != null) {
      return className;
    }

    if (baseClass.getSimpleName().toString().indexOf(ResourceFactory.LOCALE_SEPARATOR) != -1) {
      throw error(
          logger,
          "Cannot have a "
              + ResourceFactory.LOCALE_SEPARATOR
              + " in the base localizable class "
              + baseClass);
    }

    for (GwtLocale search : locale.getCompleteSearchList()) {
      String lookup =
          baseClass.getQualifiedName().toString()
              + ((search.toString().equals("default")) ? "" : "_" + search.toString());
      TypeElement element = context.elements.getTypeElement(lookup);
      if (element != null) {
        implCache.put(baseName + locale.toString(), lookup);
        return lookup;
      }
    }

    // No classes matched.
    throw error(
        logger, "Cannot find a class to bind to argument type " + baseClass.getQualifiedName());
  }
}
