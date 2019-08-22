/*
 * Copyright 2012 Google Inc.
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

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.TypeElement;

import org.gwtproject.validation.client.impl.GwtSpecificValidator;
import org.gwtproject.validation.context.AptContext;
import org.gwtproject.validation.rebind.beaninfo.PropertyDescriptor;
import org.gwtproject.validation.rebind.ext.GeneratorContext;
import org.gwtproject.validation.rebind.ext.TreeLogger;
import org.gwtproject.validation.rebind.ext.UnableToCompleteException;
import org.gwtproject.validation.rg.util.SourceWriter;
import org.gwtproject.validation.rg.util.Util;

/**
 * A cache and factory for BeanHelpers. There should be one BeanHelperCache per
 * compilation run.
 * <p>
 * (public for tests)
 */
public class BeanHelperCache { // public for testing

    private final Map<TypeElement, BeanHelper> cache;

    private AptContext context;

    /**
     * Creates a cache. There should be one cache per compiler run.
     * (public for tests.)
     */
    public BeanHelperCache() {
        cache = new HashMap<>();
    }

    /**
     * Clears the cache.
     * (Public for testing.)
     */
    public void clear() {
        cache.clear();
    }

    /**
     * Creates a BeanHelper and writes an interface containing its instance. Also,
     * recursively creates any BeanHelpers on its constrained properties.
     */
    BeanHelper createHelper(TypeElement jType, TreeLogger logger, GeneratorContext context)
            throws UnableToCompleteException {
        this.context = context.getAptContext();
        return doCreateHelper(jType, logger, context);
    }

    List<BeanHelper> getAllBeans() {
        return Util.sortMostSpecificFirst(context, cache.values(), BeanHelper.TO_CLAZZ);
    }

    BeanHelper getBean(TypeElement key) {
        return cache.get(key);
    }

    boolean isClassConstrained(TypeElement clazz) {
        throw new UnsupportedOperationException("isClassConstrained");
    }

    private BeanHelper doCreateHelper(TypeElement beanType, TreeLogger logger, GeneratorContext context)
            throws UnableToCompleteException {
        BeanHelper helper = getBean(beanType);
        if (helper == null) {
            helper = new BeanHelper(context.getAptContext(), beanType);

            cache.put(helper.getJClass(), helper);

            writeInterface(context, logger, helper);

            // now recurse on all Cascaded elements
            System.out.println("skipped all Cascaded elements");
            for (PropertyDescriptor p : helper.getBeanDescriptor().getConstrainedProperties()) {
                // TODO(idol) only bother creating objects for properties that have constrains in the groups
                // specified in @GwtValidation, but not others
                if (p.isCascaded()) {
                    doCreateHelperForProp(p, helper, logger, context);
                }
            }
        }
        return helper;
    }

    /**
     * Creates the appropriate BeanHelper for a property on a bean.
     */
    private void doCreateHelperForProp(PropertyDescriptor p, BeanHelper parent,
                                       TreeLogger logger, GeneratorContext context)
            throws UnableToCompleteException {
        throw new UnsupportedOperationException("doCreateHelperForProp");
/*
    Class<?> elementClass = p.getElementClass();
    if (GwtSpecificValidatorCreator.isIterableOrMap(elementClass)) {
      if (parent.hasField(p)) {
        TypeElement type = parent.getAssociationType(p, true);

        createHelper(type, logger, context);
      }
      if (parent.hasGetter(p)) {
        TypeElement type = parent.getAssociationType(p, false);

        createHelper(type, logger, context);
      }
    } else {
      throw new UnsupportedOperationException("doCreateHelperForProp");

     if (serverSideValidator.getConstraintsForClass(elementClass).isBeanConstrained()) {
        createHelper(elementClass, logger, context);
      }
    }*/
    }

    /**
     * Write an Empty Interface implementing
     * {@link org.gwtproject.validation.client.impl.GwtSpecificValidator} with
     * Generic parameter of the bean type.
     */
    private void writeInterface(GeneratorContext context, TreeLogger logger, BeanHelper bean) throws UnableToCompleteException {
        PrintWriter pw = context.tryCreate(logger, bean.getPackage(),
                                           bean.getValidatorName());
        if (pw != null) {
            TreeLogger interfaceLogger = logger.branch(TreeLogger.TRACE,
                                                       "Creating the interface for " + bean.getFullyQualifiedValidatorName());

            ClassSourceFileComposerFactory factory = new ClassSourceFileComposerFactory(
                    bean.getPackage(), bean.getValidatorName());
            factory.addImplementedInterface(GwtSpecificValidator.class.getCanonicalName()
                                                    + " <" + bean.getTypeCanonicalName() + ">");
            factory.makeInterface();
            SourceWriter sw = factory.createSourceWriter(context, pw);

            // static MyValidator INSTANCE = GWT.create(MyValidator.class);
            sw.print("static ");
            sw.print(bean.getValidatorName());
            sw.print(" INSTANCE = new ");
            sw.print(bean.getValidatorName());
            sw.println("Impl();");

            sw.commit(interfaceLogger);
            pw.close();
        }
    }
}
