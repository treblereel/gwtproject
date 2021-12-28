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

import com.google.auto.common.MoreElements;
import com.google.auto.common.MoreTypes;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import org.gwtproject.i18n.context.AptContext;
import org.gwtproject.i18n.ext.GeneratorContext;
import org.gwtproject.i18n.ext.TreeLogger;
import org.gwtproject.i18n.rg.util.MoreTypeUtils;
import org.gwtproject.i18n.rg.util.StringKey;
import org.gwtproject.i18n.shared.GwtLocale;
import org.gwtproject.i18n.shared.GwtLocaleFactory;

/** Creates resources. */
public abstract class ResourceFactory {

  /** A key based on TypeElement and GwtLocale. */
  static class ClassLocale extends StringKey {
    public ClassLocale(TypeElement clazz, GwtLocale locale) {
      super(clazz.getQualifiedName().toString() + "/" + locale.toString());
    }
  }

  /**
   * Separator between class name and locale in resource files. Should not appear in valid
   * localizable class names.
   */
  public static final char LOCALE_SEPARATOR = '_';

  private static List<ResourceFactory> loaders = new ArrayList<ResourceFactory>();

  /**
   * Since multiple generators share the ResourceFactory, we tie the ResourceFactoryContext cache to
   * the GeneratorContext.
   */
  private static final WeakHashMap<GeneratorContext, ResourceFactoryContext>
      resourceFactoryCtxHolder = new WeakHashMap<>();

  static {
    loaders.add(new LocalizedPropertiesResource.Factory());
  }

  public static synchronized ResourceList getBundle(
      TreeLogger logger,
      GeneratorContext context,
      TypeElement topClass,
      GwtLocale bundleLocale,
      boolean isConstants) {
    List<GwtLocale> locales = bundleLocale.getCompleteSearchList();
    List<TypeElement> classes = new ArrayList<>();
    Set<TypeElement> seenClasses = new HashSet<>();
    Map<ClassLocale, AnnotationsResource> annotations = new HashMap<>();
    GwtLocaleFactory factory = LocaleUtils.getLocaleFactory();
    GwtLocale defaultLocale = factory.getDefault();

    walkInheritanceTree(
        logger,
        context.getAptContext(),
        topClass,
        factory,
        defaultLocale,
        classes,
        annotations,
        seenClasses,
        isConstants);
    // TODO(jat): handle explicit subinterface with other locales -- ie:
    // public interface Foo_es_MX extends Foo { ... }
    ResourceList allResources = new ResourceList();
    ResourceFactoryContext localizableCtx = getResourceFactoryContext(context);
    for (GwtLocale locale : locales) {
      for (TypeElement clazz : classes) {
        ClassLocale key = new ClassLocale(clazz, locale);
        ResourceList resources;
        resources = localizableCtx.getResourceList(key);
        if (resources == null) {
          resources = new ResourceList();
          addFileResources(logger, context, clazz, locale, resources);
          AnnotationsResource annotationsResource = annotations.get(key);
          if (annotationsResource != null) {
            resources.add(annotationsResource);
          }
          localizableCtx.putResourceList(key, resources);
        }
        allResources.addAll(resources);
      }
    }
    String className = topClass.getQualifiedName().toString();
    TreeLogger branch =
        logger.branch(
            TreeLogger.SPAM, "Resource search order for " + className + ", locale " + bundleLocale);
    if (logger.isLoggable(TreeLogger.SPAM)) {
      for (AbstractResource resource : allResources) {
        branch.log(TreeLogger.SPAM, resource.toString());
      }
    }
    return allResources;
  }

  public static String getResourceName(TypeElement targetClass) {
    String name =
        targetClass
            .getQualifiedName()
            .toString()
            .replaceFirst(MoreElements.getPackage(targetClass).toString(), "")
            .replaceFirst("\\.", "");
    if (targetClass.getEnclosingElement().getKind().isClass()
        || targetClass.getEnclosingElement().getKind().isInterface()) {
      name = name.replace('.', '$');
    }
    return name;
  }

  private static void addFileResources(
      TreeLogger logger,
      GeneratorContext context,
      TypeElement clazz,
      GwtLocale locale,
      ResourceList resources) {
    // TODO: handle classes in the default package?
    String targetPath =
        MoreElements.getPackage(clazz).getQualifiedName().toString() + '.' + getResourceName(clazz);
    String localizedPath = targetPath;
    if (!locale.isDefault()) {
      localizedPath = targetPath + LOCALE_SEPARATOR + locale.getAsString();
    }
    // Check for file-based resources.
    String partialPath = localizedPath.replace('.', '/');
    for (int i = 0; i < loaders.size(); i++) {
      ResourceFactory element = loaders.get(i);
      String ext = "." + element.getExt();
      String path = partialPath + ext;
      URL resource = context.getResourcesOracle().findResource(path);
      if (resource == null && partialPath.contains("$")) {
        // Also look for A_B for inner classes, as $ in path names
        // can cause issues for some build tools.
        path = partialPath.replace('$', '_') + ext;
        resource = context.getResourcesOracle().findResource(path);
      }
      if (resource != null) {
        InputStream resourceStream = null;
        try {
          resourceStream = resource.openStream();
        } catch (IOException ex) {
          logger.log(TreeLogger.ERROR, "Error opening resource: " + path);
          throw new RuntimeException(ex);
        }
        AbstractResource found = element.load(resourceStream, locale);
        found.setPath(path);
        resources.add(found);
      }
    }
  }

  private static synchronized ResourceFactoryContext getResourceFactoryContext(
      GeneratorContext context) {
    ResourceFactoryContext resourceFactoryCtx = resourceFactoryCtxHolder.get(context);
    if (resourceFactoryCtx == null) {
      resourceFactoryCtx = new ResourceFactoryContext();
      resourceFactoryCtxHolder.put(context, resourceFactoryCtx);
    }
    return resourceFactoryCtx;
  }

  private static void walkInheritanceTree(
      TreeLogger logger,
      AptContext context,
      TypeElement clazz,
      GwtLocaleFactory factory,
      GwtLocale defaultLocale,
      List<TypeElement> classes,
      Map<ClassLocale, AnnotationsResource> annotations,
      Set<TypeElement> seenClasses,
      boolean isConstants) {
    if (seenClasses.contains(clazz)) {
      return;
    }
    seenClasses.add(clazz);
    classes.add(clazz);
    AnnotationsResource resource;
    try {
      resource = new AnnotationsResource(logger, context, clazz, defaultLocale, isConstants);
      if (resource.notEmpty()) {
        resource.setPath(clazz.getQualifiedName().toString());
        ClassLocale key = new ClassLocale(clazz, defaultLocale);
        annotations.put(key, resource);
        String defLocaleValue = null;

        // If the class has an embedded locale in it, use that for the default
        String className = clazz.getSimpleName().toString();
        int underscore = className.indexOf('_');
        if (underscore >= 0) {
          defLocaleValue = className.substring(underscore + 1);
        }
        // If there is an annotation declaring the default locale, use that
        DefaultLocale defLocaleAnnot =
            getClassAnnotation(context.types, clazz, DefaultLocale.class);
        if (defLocaleAnnot != null) {
          defLocaleValue = defLocaleAnnot.value();
        }
        GwtLocale defLocale = LocaleUtils.getLocaleFactory().fromString(defLocaleValue);
        if (!defLocale.isDefault()) {
          key = new ClassLocale(clazz, defLocale);
          annotations.put(key, resource);
        }
      }
    } catch (AnnotationsResource.AnnotationsError e) {
      logger.log(TreeLogger.ERROR, e.getMessage(), e);
    }

    for (TypeMirror supertype : context.types.directSupertypes(clazz.asType())) {
      DeclaredType declared =
          (DeclaredType) supertype; // you should of course check this is possible first
      Element supertypeElement = declared.asElement();
    }

    if (clazz != null) {
      if (MoreTypeUtils.getSuperType(context.types, clazz) != null) {
        walkInheritanceTree(
            logger,
            context,
            MoreTypeUtils.getSuperType(context.types, clazz),
            factory,
            defaultLocale,
            classes,
            annotations,
            seenClasses,
            isConstants);
      }

      for (TypeMirror intf : clazz.getInterfaces()) {
        walkInheritanceTree(
            logger,
            context,
            MoreTypes.asTypeElement(intf),
            factory,
            defaultLocale,
            classes,
            annotations,
            seenClasses,
            isConstants);
      }
    }
  }

  abstract String getExt();

  abstract AbstractResource load(InputStream m, GwtLocale locale);
}
