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
package org.gwtproject.i18n.datetimeformat.processor;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SetMultimap;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import org.gwtproject.ext.PropertyOracle;
import org.gwtproject.ext.PropertyOracleImpl;
import org.gwtproject.i18n.processor.*;
import org.gwtproject.i18n.shared.CustomDateTimeFormat;
import org.gwtproject.i18n.shared.DateTimeFormat;
import org.gwtproject.i18n.shared.GwtLocale;
import org.gwtproject.i18n.shared.SupportedLocales;
import org.gwtproject.i18n.shared.annotations.IsCustomDateTimeFormat;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;

import static java.util.Objects.nonNull;

/**
 * Created by colin on 7/17/16.
 */
public class CustomDateTimeFormatProcessingStep extends AbstractProcessingStep {


    public CustomDateTimeFormatProcessingStep(ProcessingEnvironment processingEnv) {
        super(processingEnv);
    }

    public static class Builder extends StepBuilder<CustomDateTimeFormatProcessingStep> {

        public CustomDateTimeFormatProcessingStep build() {
            return new CustomDateTimeFormatProcessingStep(processingEnv);
        }
    }

    @Override
    public Set<? extends Class<? extends Annotation>> annotations() {
        return Collections.singleton(IsCustomDateTimeFormat.class);
    }

    @Override
    public Set<Element> process(
            SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {

        for (Element element : elementsByAnnotation.get(IsCustomDateTimeFormat.class)) {
            try {
                generateCustomDateTimeFormats((TypeElement) element);
            } catch (IOException e) {
                ExceptionUtil.messageStackTrace(messager, e);
            }
        }

        return ImmutableSet.of();
    }

    private void generateCustomDateTimeFormats(TypeElement element)
            throws IOException {

        TypeElement dateTimeFormat = elements.getTypeElement(DateTimeFormat.class.getName());
        // TODO(jat): runtime locales support
        List<String> supportedLocales = getSupportedLocales(element);

        supportedLocales.forEach(locale -> {
            DateTimePatternGenerator dtpg = getDateTimePatternGenerator(locale);
            String className = element.getSimpleName().toString().replace('.', '_') + "_"
                    + locale;

            TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(className)
                    .addModifiers(Modifier.PUBLIC)
                    .addSuperinterface(TypeName.get(element.asType()));

            element.getEnclosedElements()
                    .stream()
                    .filter(e -> ElementKind.METHOD.equals(e.getKind()))
                    .map(e -> (ExecutableElement) e)
                    .forEach(method -> {
                        TypeMirror returnType = method.getReturnType();
                        if (!types.isSameType(returnType, dateTimeFormat.asType())) {
                            messager.printMessage(Diagnostic.Kind.ERROR, element.getQualifiedName() + "." + method.getSimpleName().toString()
                                    + " must return DateTimeFormat");
                        }
                        String pattern;
                        CustomDateTimeFormat.Pattern annotation = method.getAnnotation(CustomDateTimeFormat.Pattern.class);
                        if (annotation == null) {
                            messager.printMessage(Diagnostic.Kind.ERROR, element.getQualifiedName() + "." + method.getSimpleName()
                                    + " must have an @Pattern annotation");
                        }
                        pattern = annotation.value();
                        pattern = dtpg.getBestPattern(pattern);

                        typeBuilder.addMethod(MethodSpec
                                .methodBuilder(method.getSimpleName().toString())
                                .addAnnotation(Override.class)
                                .addModifiers(Modifier.PUBLIC)
                                .returns(TypeName.get(method.getReturnType()))
                                .addStatement("return $T.getFormat(\"$L\")", TypeName.get(method.getReturnType()), pattern)
                                .build());
                    });

            JavaFile driverFile = JavaFile.builder(elements.getPackageOf(element).getQualifiedName().toString(), typeBuilder.build()).build();

            try {
                driverFile.writeTo(filer);
            } catch (IOException e) {
                ExceptionUtil.messageStackTrace(messager, e);
            }
        } );



    }

    private List<String> getSupportedLocales(TypeElement element) {
        SupportedLocales supportedLocales = element.getAnnotation(SupportedLocales.class);
        if (nonNull(supportedLocales) && supportedLocales.value().length > 0) {
            return Arrays.asList(supportedLocales.value());
        }

        ProcessorContext context = new ProcessorContext(processingEnv);
        // Get the current locale and interface type.
        PropertyOracle propertyOracle = new PropertyOracleImpl();
        LocaleUtils localeUtils = LocaleUtils.getInstance(messager, propertyOracle, context);
        GwtLocale gwtLocale = localeUtils.getCompileLocale();

        return Collections.singletonList(gwtLocale.getAsString());
    }

    protected DateTimePatternGenerator getDateTimePatternGenerator(String localeName) {
        return new DateTimePatternGenerator(localeName);
    }

}
