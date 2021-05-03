/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat, Inc. and/or its affiliates, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gwtproject.validation.apt;

import java.util.Set;
import javax.lang.model.element.TypeElement;
import org.gwtproject.validation.context.AptContext;
import org.gwtproject.validation.ext.Generator;
import org.gwtproject.validation.ext.StandardGeneratorContext;
import org.gwtproject.validation.rebind.ValidatorGenerator;
import org.gwtproject.validation.rebind.ext.TreeLogger;
import org.gwtproject.validation.rebind.ext.UnableToCompleteException;

/** @author Dmitrii Tikhomirov Created by treblereel 11/11/18 */
public class ClientBundleClassBuilder {

  private final TreeLogger logger;
  private final AptContext context;

  private final Set<TypeElement> elements;

  public ClientBundleClassBuilder(
      TreeLogger logger, AptContext context, Set<TypeElement> elements) {
    this.logger = logger;
    this.context = context;
    this.elements = elements;
  }

  public void process() throws UnableToCompleteException {
    Generator generator = new ValidatorGenerator();

    StandardGeneratorContext standardGeneratorContext = new StandardGeneratorContext(context);
    for (TypeElement type : elements) {
      generator.generate(logger, standardGeneratorContext, type);
    }
  }
}
