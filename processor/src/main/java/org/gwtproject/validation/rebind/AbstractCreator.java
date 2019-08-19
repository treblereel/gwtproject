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

import java.io.PrintWriter;

import javax.lang.model.element.TypeElement;

import org.gwtproject.validation.rebind.ext.GeneratorContext;
import org.gwtproject.validation.rebind.ext.TreeLogger;
import org.gwtproject.validation.rebind.ext.UnableToCompleteException;
import org.gwtproject.validation.rg.util.SourceWriter;

/**
 * Abstract Class for Creating source files.
 * <p>
 * This class is not thread safe.
 */
public abstract class AbstractCreator extends AbstractSourceCreator {

    final GeneratorContext context;

    final TreeLogger logger;

    final BeanHelperCache cache;

    final String packageName;

    final String simpleName;

    AbstractCreator(GeneratorContext context, TreeLogger logger,
                    String packageName, String clazzName, BeanHelperCache cache) {
        this.context = context;
        this.logger = branch(logger, "Creating " + packageName + "." + clazzName);
        this.cache = cache;

        this.packageName = packageName;
        this.simpleName = clazzName.replace('.', '_') + "Impl";
    }

    public final String create() throws UnableToCompleteException {
        SourceWriter sourceWriter = getSourceWriter(logger, context);
        if (sourceWriter != null) {
            writeClassBody(sourceWriter);
            sourceWriter.commit(logger);
        }
        return getQualifiedName();
    }

    protected void addImports(ClassSourceFileComposerFactory composerFactory,
                              Class<?>... imports) {
        for (Class<?> imp : imports) {
            composerFactory.addImport(imp.getCanonicalName());
        }
    }

    protected abstract void compose(ClassSourceFileComposerFactory composerFactory);

    protected BeanHelper createBeanHelper(TypeElement jType)
            throws UnableToCompleteException {
        return cache.createHelper(jType, logger, context);
    }

    protected abstract void writeClassBody(SourceWriter sourceWriter)
            throws UnableToCompleteException;

    protected String getQualifiedName() {
        return (packageName == "" ? "" : packageName + ".") + simpleName;
    }

    private SourceWriter getSourceWriter(TreeLogger logger, GeneratorContext ctx) throws UnableToCompleteException {
        PrintWriter printWriter = ctx.tryCreate(logger, packageName, simpleName);
        if (printWriter == null) {
            return null;
        }

        ClassSourceFileComposerFactory composerFactory = new ClassSourceFileComposerFactory(
                packageName, simpleName);
        compose(composerFactory);
        return composerFactory.createSourceWriter(ctx, printWriter);
    }
}