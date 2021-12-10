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
import javax.lang.model.element.TypeElement;
import org.gwtproject.i18n.server.KeyGenerator;
import org.gwtproject.i18n.server.MessageInterface;
import org.gwtproject.i18n.server.MessageInterfaceVisitor;
import org.gwtproject.i18n.server.MessageProcessingException;
import org.gwtproject.i18n.shared.GwtLocale;

/**
 * Simple {@link MessageInterface} implementation only suitable for use with {@link KeyGenerator
 * KeyGenerator}.
 */
class KeyGenMessageInterface implements MessageInterface {

  private TypeElement type;

  /** @param type */
  public KeyGenMessageInterface(TypeElement type) {
    this.type = type;
  }

  public void accept(MessageInterfaceVisitor cv) throws MessageProcessingException {
    throw new MessageProcessingException("unsupported");
  }

  public void accept(MessageInterfaceVisitor cv, GwtLocale locale)
      throws MessageProcessingException {
    throw new MessageProcessingException("unsupported");
  }

  public <A extends Annotation> A getAnnotation(Class<A> annotClass) {
    throw new UnsupportedOperationException();
  }

  public String getClassName() {
    return type.getSimpleName().toString();
  }

  public String getPackageName() {
    return MoreElements.getPackage(type).getQualifiedName().toString();
  }

  public String getQualifiedName() {
    return type.getQualifiedName().toString();
  }

  public boolean isAnnotationPresent(Class<? extends Annotation> annotClass) {
    throw new UnsupportedOperationException();
  }
}
