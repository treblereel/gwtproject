/*
 * Copyright © 2018 The GWT Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gwtproject.i18n.processor;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.auto.common.MoreElements;
import com.google.common.collect.SetMultimap;
import com.squareup.javapoet.*;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import org.gwtproject.i18n.shared.Localizable;

public class LocalizableProcessingStep implements BasicAnnotationProcessor.ProcessingStep {
  private final ProcessingEnvironment processingEnv;

  public LocalizableProcessingStep(ProcessingEnvironment processingEnv) {
    this.processingEnv = processingEnv;
  }

  @Override
  public Set<? extends Class<? extends Annotation>> annotations() {
    return Collections.singleton(Localizable.IsLocalizable.class);
  }

  @Override
  public Set<Element> process(
      SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {

    for (Element element : elementsByAnnotation.get(Localizable.IsLocalizable.class)) {
      // build a model
      String packageName =
          processingEnv.getElementUtils().getPackageOf(element).getQualifiedName().toString();

      // collect the possible locales
      // TODO correctly sort this based on the logic ahmad has worked up

      List<String> locales = getLocaleNames(element);
      // TODO remove hack which restricts us to default
      locales = Collections.singletonList("default");

      // implement the interface into a new type for each locale key
      Map<String, ClassName> keyToNameMapping = new HashMap<>();

      keyToNameMapping.put(
          "default", ClassName.get(packageName, element.getSimpleName().toString() + "_default"));

      TypeSpec.Builder defaultImplBuilder =
          TypeSpec.classBuilder(element.getSimpleName().toString() + "_default")
              .addModifiers(Modifier.PUBLIC)
              .addSuperinterface(ClassName.get(element.asType()));

      MoreElements.getLocalAndInheritedMethods(
              (TypeElement) element, processingEnv.getTypeUtils(), processingEnv.getElementUtils())
          .stream()
          //                    .filter(method -> !method.getModifiers().contains(Modifier.STATIC))
          .filter(method -> !method.getModifiers().contains(Modifier.DEFAULT))

          // skip anything on Object
          .filter(
              method ->
                  !Object.class
                      .getName()
                      .equals(ClassName.get(method.getEnclosingElement().asType()).toString()))
          .forEach(
              method -> {
                MethodSpec.Builder impl =
                    MethodSpec.methodBuilder(method.getSimpleName().toString())
                        .addModifiers(Modifier.PUBLIC)
                        .returns(TypeName.get(method.getReturnType()))
                        .addAnnotation(Override.class);
                for (VariableElement parameter : method.getParameters()) {
                  impl.addParameter(
                      TypeName.get(parameter.asType()), parameter.getSimpleName().toString());
                }
                // TODO support other return types, return correct values, etc
                if (method.getReturnType().getKind().isPrimitive()) {
                  if (method.getReturnType().getKind() == TypeKind.BOOLEAN) {
                    impl.addStatement("return false");
                  } else {
                    impl.addStatement("return ($T)0", TypeName.get(method.getReturnType()));
                  }
                } else {

                  impl.addStatement("return $S", method.getSimpleName().toString());
                }

                defaultImplBuilder.addMethod(impl.build());
              });

      try {
        JavaFile.builder(packageName, defaultImplBuilder.build())
            .build()
            .writeTo(processingEnv.getFiler());
      } catch (IOException e) {
        e.printStackTrace();
      }

      // for each method that needs overriding, generate a method which
      // either reads from the default annotation or just returns the
      // method name itself, ignoring all args

      // based on the types created and their locale,
      // create a factor
      TypeSpec factory =
          TypeSpec.classBuilder(element.getSimpleName() + "_Factory")
              .addMethod(
                  MethodSpec.methodBuilder("create")
                      .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                      .returns(ClassName.get(element.asType()))
                      .addStatement("return create(System.getProperty(\"locale\", \"default\"))")
                      .build())
              .addMethod(
                  MethodSpec.methodBuilder("create")
                      .addParameter(String.class, "locale")
                      .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                      .returns(ClassName.get(element.asType()))
                      .addCode(makeSwitchCase(locales, keyToNameMapping))
                      .build())
              .build();
      try {
        JavaFile.builder(packageName, factory).build().writeTo(processingEnv.getFiler());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return Collections.emptySet();
  }

  private List<String> getLocaleNames(Element element) {
    // breadth-first search to find the annotation since you can have diamond inheritance in
    // interfaces
    List<Element> typesToCheck = new ArrayList<>();
    typesToCheck.add(element);
    for (int i = 0; i < typesToCheck.size(); i++) {
      Element check = typesToCheck.get(i);
      Localizable.I18nLocaleSuffuxes annotation =
          check.getAnnotation(Localizable.I18nLocaleSuffuxes.class);
      if (annotation != null) {
        return Arrays.asList(annotation.value());
      }
      TypeElement type = (TypeElement) check;
      typesToCheck.addAll(
          type.getInterfaces().stream()
              .map(m -> processingEnv.getTypeUtils().asElement(m))
              .collect(Collectors.toList()));
    }
    return Collections.emptyList();
  }

  private CodeBlock makeSwitchCase(List<String> locales, Map<String, ClassName> keyToNameMapping) {
    CodeBlock.Builder switchCase = CodeBlock.builder().beginControlFlow("switch (locale)");
    for (String locale : locales) {
      switchCase
          .beginControlFlow("case $S:", locale)
          .addStatement("return new $T()", keyToNameMapping.get(locale))
          .endControlFlow();
    }
    return switchCase.endControlFlow().addStatement("return null").build();
  }
}
