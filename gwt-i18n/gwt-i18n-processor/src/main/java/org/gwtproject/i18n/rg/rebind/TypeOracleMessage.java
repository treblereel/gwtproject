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

import com.google.auto.common.MoreTypes;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import org.gwtproject.i18n.context.AptContext;
import org.gwtproject.i18n.rg.rebind.AbstractResource.ResourceEntry;
import org.gwtproject.i18n.rg.rebind.AbstractResource.ResourceList;
import org.gwtproject.i18n.rg.util.MoreTypeUtils;
import org.gwtproject.i18n.server.AbstractMessage;
import org.gwtproject.i18n.server.AbstractParameter;
import org.gwtproject.i18n.server.Message;
import org.gwtproject.i18n.server.MessageInterface;
import org.gwtproject.i18n.server.MessageTranslation;
import org.gwtproject.i18n.server.Parameter;
import org.gwtproject.i18n.server.Type;
import org.gwtproject.i18n.shared.GwtLocale;
import org.gwtproject.i18n.shared.GwtLocaleFactory;
import org.gwtproject.safehtml.shared.SafeHtml;

/** An implementation of the {@link Message} API on top of type oracle. */
public class TypeOracleMessage extends AbstractMessage {

  /** An implementation of {@link Parameter} on top of type oracle. */
  public class TypeOracleParameter extends AbstractParameter {

    private final VariableElement param;

    public TypeOracleParameter(int index, VariableElement param) {
      super(getLocaleFactory(), index, mapJTypeToType(param.asType()));
      this.param = param;
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotClass) {
      return param.getAnnotation(annotClass);
    }

    @Override
    public String getName() {
      return param.getSimpleName().toString();
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotClass) {
      return param.getAnnotation(annotClass) != null;
    }
  }

  /** A single translated message obtained from an {@link AbstractResource}. */
  private class ResourceMessageTranslation implements MessageTranslation {

    private final ResourceEntry resourceEntry;
    private final GwtLocale matchedLocale;

    public ResourceMessageTranslation(ResourceEntry resourceEntry, GwtLocale matchedLocale) {
      this.resourceEntry = resourceEntry;
      this.matchedLocale = matchedLocale;
    }

    public Iterable<AlternateFormMapping> getAllMessageForms() {
      List<AlternateFormMapping> mapping = new ArrayList<AlternateFormMapping>();
      // add the default form
      if (!isStringMap()) {
        mapping.add(new AlternateFormMapping(defaultForms(), getDefaultMessage()));
      }

      // add supplied forms
      int numSelectors = getSelectorParameterIndices().length;
      for (String joinedForms : resourceEntry.getForms()) {
        addMapping(mapping, numSelectors, joinedForms, resourceEntry.getForm(joinedForms));
      }

      // sort into lexicographic order and return
      Collections.sort(mapping);
      return mapping;
    }

    public String getDefaultMessage() {
      return resourceEntry.getForm(null);
    }

    public GwtLocale getMatchedLocale() {
      return matchedLocale;
    }

    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder();
      buf.append("ResourceMT: ").append(resourceEntry);
      return buf.toString();
    }
  }

  private final ExecutableElement method;

  private List<Parameter> parameters;

  private final ResourceList resources;

  private final AptContext context;

  public TypeOracleMessage(
      AptContext context,
      GwtLocaleFactory localeFactory,
      MessageInterface msgIntf,
      ExecutableElement method,
      ResourceList resources) {
    super(context, localeFactory, msgIntf);
    this.method = method;
    this.resources = resources;
    this.context = context;
    init();
  }

  @Override
  public <A extends Annotation> A getAnnotation(Class<A> annotClass) {
    A annot = method.getAnnotation(annotClass);
    if (annot != null) {
      return annot;
    }
    return MoreTypeUtils.getAnnotation(
        context.types, MoreTypes.asTypeElement(method.getEnclosingElement().asType()), annotClass);
  }

  @Override
  public String getMethodName() {
    return method.getSimpleName().toString();
  }

  @Override
  public List<Parameter> getParameters() {
    if (parameters == null) {
      ensureParameters();
    }
    return parameters;
  }

  @Override
  public Type getReturnType() {
    TypeMirror returnType = method.getReturnType();
    return mapJTypeToType(returnType);
  }

  @Override
  public MessageTranslation getTranslation(GwtLocale locale) {
    /*
     * TODO(jat): Note that we don't actually follow the contract here, since
     * the ResourceList we were supplied with has already been filtered
     * according to the locale we should be called with, for the limited use
     * case today of generating translation output files. This will have to be
     * updated when this is used for generating code as well.
     */
    ResourceEntry entry = null;
    if (resources != null) {
      entry = resources.getEntry(getKey());
    }
    if (entry != null) {
      return new ResourceMessageTranslation(entry, resources.findLeastDerivedLocale(null, locale));
    }
    return this;
  }

  @Override
  public boolean isAnnotationPresent(Class<? extends Annotation> annotClass) {
    return method.getAnnotation(annotClass) != null;
  }

  public boolean isVarArgs() {
    return method.isVarArgs();
  }

  protected Type mapJTypeToType(TypeMirror type) {
    if (type.getKind().isPrimitive()) {
      if (type.getKind().equals(TypeKind.BOOLEAN)) {
        return Type.BOOLEAN;
      }
      if (type.getKind().equals(TypeKind.BYTE)) {
        return Type.BYTE;
      }
      if (type.getKind().equals(TypeKind.CHAR)) {
        return Type.CHAR;
      }
      if (type.getKind().equals(TypeKind.DOUBLE)) {
        return Type.DOUBLE;
      }
      if (type.getKind().equals(TypeKind.FLOAT)) {
        return Type.FLOAT;
      }
      if (type.getKind().equals(TypeKind.INT)) {
        return Type.INT;
      }
      if (type.getKind().equals(TypeKind.LONG)) {
        return Type.LONG;
      }
      if (type.getKind().equals(TypeKind.SHORT)) {
        return Type.SHORT;
      }
      throw new RuntimeException("Unexpected primitive type " + type);
    }
    if (type.getKind().equals(TypeKind.ARRAY)) {
      ArrayType componentType = MoreTypes.asArray(type);
      return new Type.ArrayType(
          componentType.getComponentType() + "[]",
          mapJTypeToType(componentType.getComponentType()));
    }
    String qualSourceName = type.toString();

    if (MoreTypes.asElement(type).getKind().equals(ElementKind.ENUM)) {
      List<Element> list =
          MoreTypes.asElement(type).getEnclosedElements().stream()
              .filter(elm -> elm.getKind().equals(ElementKind.ENUM_CONSTANT))
              .collect(Collectors.toList());

      Element[] enumConstants = list.toArray(new Element[list.size()]);
      int n = enumConstants.length;
      String[] names = new String[n];
      for (int i = 0; i < n; ++i) {
        names[i] = enumConstants[i].getSimpleName().toString();
      }
      return new Type.EnumType(qualSourceName, names);
    }
    String stringQualifiedName = String.class.getCanonicalName();
    if (stringQualifiedName.equals(qualSourceName)) {
      return Type.STRING;
    }
    if (SafeHtml.class.getCanonicalName().equals(qualSourceName)) {
      return Type.SAFEHTML;
    }
    TypeElement date = context.elements.getTypeElement(Date.class.getCanonicalName());
    ElementKind kind = MoreTypes.asElement(type).getKind();
    if (date != null
        && (kind.isClass() || kind.isInterface())
        && context.types.isAssignable(type, date.asType())) {
      return Type.DATE;
    }
    String listQualifiedName = List.class.getCanonicalName();
    TypeElement list = context.elements.getTypeElement(listQualifiedName);
    if (!type.getKind().equals(TypeKind.TYPEVAR)) {
      List<? extends TypeMirror> parameterizedType = ((DeclaredType) type).getTypeArguments();
      Element classType = MoreTypes.asElement(type);

      if (list != null
          && classType != null
          && context.types.isAssignable(classType.asType(), list.asType())) {
        if (parameterizedType.isEmpty()) {
          // raw List usage
          return new Type.ListType(listQualifiedName + "<Object>", Type.OBJECT);
        }
        TypeMirror componentType = parameterizedType.get(0);
        return new Type.ListType(
            listQualifiedName + "<" + componentType.toString() + ">",
            mapJTypeToType(componentType));
      }
      String mapQualifiedName = Map.class.getCanonicalName();
      TypeElement map = context.elements.getTypeElement(mapQualifiedName);
      if (map != null
          && classType != null
          && context.types.isAssignable(classType.asType(), map.asType())
          && parameterizedType != null) {
        if (parameterizedType.size() == 2
            && stringQualifiedName.equals(parameterizedType.get(0).toString())
            && stringQualifiedName.equals(parameterizedType.get(1).toString())) {
          return Type.STRING_MAP;
        }
      }
    }
    return new Type(qualSourceName, false);
  }

  private void ensureParameters() {
    parameters = new ArrayList<>();
    int i = 0;
    for (VariableElement parameter : method.getParameters()) {
      parameters.add(new TypeOracleParameter(i++, parameter));
    }
  }
}
