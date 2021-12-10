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

import com.google.auto.common.MoreElements;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import org.gwtproject.i18n.context.AptContext;
import org.gwtproject.i18n.rg.rebind.AbstractResource.ResourceList;
import org.gwtproject.i18n.rg.util.MoreTypeUtils;
import org.gwtproject.i18n.server.AbstractMessageInterface;
import org.gwtproject.i18n.server.Message;
import org.gwtproject.i18n.server.MessageInterface;
import org.gwtproject.i18n.server.MessageProcessingException;
import org.gwtproject.i18n.shared.GwtLocaleFactory;

/** Implementation of the {@link MessageInterface} API on top of TypeOracle. */
public class TypeOracleMessageInterface extends AbstractMessageInterface {

  private final TypeElement type;
  private final ResourceList resources;
  private final AptContext context;

  public TypeOracleMessageInterface(
      AptContext context, GwtLocaleFactory factory, TypeElement type, ResourceList resources) {
    super(factory);
    this.type = type;
    this.context = context;
    this.resources = resources;
  }

  @Override
  public <A extends Annotation> A getAnnotation(Class<A> annotClass) {
    return MoreTypeUtils.getAnnotation(context.types, type, annotClass);
  }

  @Override
  public String getClassName() {
    return type.getSimpleName().toString();
  }

  @Override
  public Iterable<Message> getMessages() throws MessageProcessingException {
    List<Message> messages = new ArrayList<>();
    type.getEnclosedElements().stream()
        .filter(elm -> elm.getKind().equals(ElementKind.METHOD))
        .map(elm -> MoreElements.asExecutable(elm))
        .forEach(
            method -> {
              messages.add(new TypeOracleMessage(context, factory, this, method, resources));
            });
    Collections.sort(messages);
    return Collections.unmodifiableList(messages);
  }

  @Override
  public String getPackageName() {
    return MoreElements.getPackage(type).getQualifiedName().toString();
  }

  @Override
  public String getQualifiedName() {
    return type.getQualifiedName().toString();
  }

  @Override
  public boolean isAnnotationPresent(Class<? extends Annotation> annotClass) {
    return MoreTypeUtils.isAnnotationPresent(context.types, type, annotClass);
  }
}
