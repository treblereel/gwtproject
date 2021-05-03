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
package org.gwtproject.validation.rebind.beaninfo;

import java.util.Set;

/**
 * Describes a validated element (class, field or property).
 *
 * @author Emmanuel Bernard
 * @author Hardy Ferentschik
 * @author Dmitrii Tikhomirov Created by treblereel 8/20/19
 */
public interface ElementDescriptor<T> {

  /**
   * Return <code>true</code> if at least one constraint declaration is present for this element in
   * the class hierarchy, <code>false</code> otherwise.
   */
  boolean hasConstraints();

  /** @return Statically defined returned type. */
  T getElementClass();

  String getFullyQualifiedFieldName();

  /**
   * Return all constraint descriptors for this element in the class hierarchy or an empty <code>Set
   * </code> if none are present.
   *
   * @return <code>Set</code> of constraint descriptors for this element
   */
  Set<ConstraintDescriptor> getConstraintDescriptors();
}
