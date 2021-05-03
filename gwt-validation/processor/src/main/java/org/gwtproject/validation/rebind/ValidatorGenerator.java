/*
 * Copyright 2010 Google Inc.
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
package org.gwtproject.validation.rebind;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.validation.Validator;

import com.google.auto.common.MoreElements;
import com.google.auto.common.MoreTypes;
import org.gwtproject.validation.client.GwtValidation;
import org.gwtproject.validation.client.impl.GwtSpecificValidator;
import org.gwtproject.validation.context.AptContext;
import org.gwtproject.validation.ext.Generator;
import org.gwtproject.validation.rebind.ext.GeneratorContext;
import org.gwtproject.validation.rebind.ext.TreeLogger;
import org.gwtproject.validation.rebind.ext.UnableToCompleteException;
import org.gwtproject.validation.rg.util.Util;

/**
 * Generates subclasses of {@link Validator} and {@link GwtSpecificValidator}. The generic
 * validator only handles the classes listed in the
 * {@link org.gwtproject.validation.client.GwtValidation} annotation. See
 * {@link org.gwtproject.validation.client.GwtValidation} for usage.
 */
public final class ValidatorGenerator extends Generator {

    private final BeanHelperCache cache;

    private List<TypeElement> groups;

    private Set<TypeElement> values = new HashSet<>();

    // called by the compiler via reflection
    public ValidatorGenerator() {
        this.cache = new BeanHelperCache();
    }

    @Override
    public String generate(TreeLogger logger, GeneratorContext context,
                           TypeElement type) throws UnableToCompleteException {
        TypeElement genericType = context.getAptContext().elements.getTypeElement(Validator.class.getCanonicalName());
        if (context.getAptContext().types.isAssignable(type.asType(), genericType.asType())) {
            return generateGwtSpecificValidator(logger, context, type);
        } else {
            logger.log(TreeLogger.ERROR,
                       "type is not a ValidatorGenerator'" + type + "'",
                       null);
            throw new UnableToCompleteException();
        }
    }

    private String generateGwtSpecificValidator(TreeLogger logger, GeneratorContext context,
                                                TypeElement validatorType) throws UnableToCompleteException {
        GwtValidation gwtValidation = validatorType.getAnnotation(GwtValidation.class);
        if (gwtValidation == null) {
            logger.log(TreeLogger.ERROR, validatorType + " must be annotated with "
                    + GwtValidation.class.getCanonicalName(), null);
            throw new UnableToCompleteException();
        }

        Set<TypeElement> beans = Util.getValues(gwtValidation);
        values.addAll(beans);

        for (TypeElement bean : beans) {
            checkInheritance(bean.getSuperclass(), context.getAptContext());
        }

        if (values.isEmpty()) {
            logger.log(TreeLogger.ERROR,
                       "The @" + GwtValidation.class.getSimpleName() + "  of " + validatorType
                               + "must specify at least one bean type to validate.", null);
            throw new UnableToCompleteException();
        }

        groups = Util.getGroups(gwtValidation);

        if (groups.isEmpty()) {
            logger.log(TreeLogger.ERROR,
                       "The @" + GwtValidation.class.getSimpleName() + "  of " + validatorType
                               + "must specify at least one validation group.", null);
            throw new UnableToCompleteException();
        }

        TreeLogger validatorLogger = logger.branch(TreeLogger.DEBUG,
                                                   "Generating Validator for  '" + validatorType.getQualifiedName()
                                                           + "'", null);

        for (TypeElement value : values) {
            BeanHelper beanHelper = cache.createHelper(value, logger, context);
            if (beanHelper == null) {
                logger.log(TreeLogger.ERROR, "Unable to create BeanHelper for " + value
                        + " " + Validator.class.getSimpleName()
                        + ".", null);
                throw new UnableToCompleteException();
            }
            generateGwtSpecificValidator(value, beanHelper, validatorLogger, context, cache, groups);
        }
        return generateGenericValidator(logger, context, validatorType);
    }

    private void checkInheritance(TypeMirror bean, AptContext context) {
        TypeElement object = context.elements.getTypeElement(Object.class.getCanonicalName());
        if (!context.types.isSameType(object.asType(), bean)) {
            checkInheritance(MoreTypes.asTypeElement(bean).getSuperclass(), context);
            if (!values.contains(MoreTypes.asTypeElement(bean))) {
                Set<VariableElement> fields = MoreTypes.asTypeElement(bean).getEnclosedElements()
                        .stream()
                        .filter(elm -> elm.getKind().equals(ElementKind.FIELD))
                        .map(MoreElements::asVariable).collect(Collectors.toSet());
                loop:
                for (VariableElement field : fields) {
                    for (AnnotationMirror annotationMirror : field.getAnnotationMirrors()) {
                        if (context.isSupported(annotationMirror.getAnnotationType().asElement().toString())) {
                            values.add(MoreTypes.asTypeElement(bean));
                            break loop;
                        }
                    }
                }
            }
        }
    }

    private String generateGwtSpecificValidator(TypeElement type,
                                                BeanHelper beanHelper, TreeLogger logger,
                                                GeneratorContext context, BeanHelperCache cache,
                                                List<TypeElement> groups) throws UnableToCompleteException {
        AbstractCreator creator = new GwtSpecificValidatorCreator(type, beanHelper, logger, context, cache, groups);
        return creator.create();
    }

    private String generateGenericValidator(TreeLogger logger, GeneratorContext context,
                                            TypeElement validatorType) throws UnableToCompleteException {
        TreeLogger validatorLogger = logger.branch(TreeLogger.DEBUG,
                                                   "Generating Validator for  '" + validatorType.getQualifiedName()
                                                           + "'", null);
        AbstractCreator creator = new ValidatorCreator(validatorType,
                                                       values,
                                                       groups,
                                                       validatorLogger,
                                                       context, cache);
        return creator.create();
    }
}
