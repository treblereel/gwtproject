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

import static org.gwtproject.i18n.client.LocalizableResource.*;
import static org.gwtproject.i18n.server.MessageFormatUtils.*;

import com.google.auto.common.MoreElements;
import com.google.auto.common.MoreTypes;
import java.lang.annotation.Annotation;
import java.util.List;
import javax.lang.model.element.ExecutableElement;
import org.gwtproject.i18n.client.Constants;
import org.gwtproject.i18n.client.Messages;
import org.gwtproject.i18n.context.AptContext;
import org.gwtproject.i18n.rg.util.MoreTypeUtils;
import org.gwtproject.i18n.server.KeyGenerator;
import org.gwtproject.i18n.server.Message;
import org.gwtproject.i18n.server.MessageInterface;
import org.gwtproject.i18n.server.MessageProcessingException;
import org.gwtproject.i18n.server.MessageTranslation;
import org.gwtproject.i18n.server.MessageUtils;
import org.gwtproject.i18n.server.MessageVisitor;
import org.gwtproject.i18n.server.Parameter;
import org.gwtproject.i18n.server.Type;
import org.gwtproject.i18n.shared.GwtLocale;

/**
 * Implementation of {@link Message} only suitable for use by key generators. See the restrictions
 * in {@link KeyGenerator#generateKey(Message)}.
 */
class KeyGenMessage implements Message {

  private ExecutableElement method;

  private AptContext context;

  /** @param method */
  public KeyGenMessage(AptContext context, ExecutableElement method) {
    this.method = method;
    this.context = context;
  }

  public void accept(MessageVisitor v) throws MessageProcessingException {
    throw new MessageProcessingException("unsupported");
  }

  public void accept(MessageVisitor v, GwtLocale locale) throws MessageProcessingException {
    throw new MessageProcessingException("unsupported");
  }

  public int compareTo(Message o) {
    return 0;
  }

  public Iterable<AlternateFormMapping> getAllMessageForms() {
    return null;
  }

  public <A extends Annotation> A getAnnotation(Class<A> annotClass) {
    A annot = method.getAnnotation(annotClass);
    if (annot != null) {
      return annot;
    }
    return MoreTypeUtils.getAnnotation(
        context.types, MoreElements.asType(method.getEnclosingElement()), annotClass);
  }

  public String getDefaultMessage() {
    if (isAnnotationPresent(Messages.DefaultMessage.class)) {
      Messages.DefaultMessage annot = getAnnotation(Messages.DefaultMessage.class);
      return annot.value();
    } else if (isAnnotationPresent(Constants.DefaultStringMapValue.class)) {
      Constants.DefaultStringMapValue annot = getAnnotation(Constants.DefaultStringMapValue.class);
      String[] keyValues = annot.value();
      StringBuilder buf = new StringBuilder();
      boolean needComma = false;
      for (int i = 0; i < keyValues.length; i += 2) {
        if (needComma) {
          buf.append(',');
        } else {
          needComma = true;
        }
        buf.append(MessageUtils.quoteComma(keyValues[i]));
      }
      return buf.toString();
    } else {
      return MessageUtils.getConstantsDefaultValue(this);
    }
  }

  public String getDescription() {
    Description annot = getAnnotation(Description.class);
    return annot != null ? annot.value() : null;
  }

  public String getKey() {
    return null;
  }

  public GwtLocale getMatchedLocale() {
    return null;
  }

  public String getMeaning() {
    Meaning meaningAnnot = getAnnotation(Meaning.class);
    return meaningAnnot != null ? meaningAnnot.value() : null;
  }

  public MessageInterface getMessageInterface() {
    return new KeyGenMessageInterface(
        MoreTypes.asTypeElement(method.getEnclosingElement().asType()));
  }

  public MessageStyle getMessageStyle() {
    return isAnnotationPresent(Messages.DefaultMessage.class)
        ? MessageStyle.MESSAGE_FORMAT
        : MessageStyle.PLAIN;
  }

  public String getMethodName() {
    return method.getSimpleName().toString();
  }

  public List<Parameter> getParameters() {
    return null;
  }

  public Type getReturnType() {
    return null;
  }

  public int[] getSelectorParameterIndices() {
    return null;
  }

  public MessageTranslation getTranslation(GwtLocale locale) {
    return null;
  }

  public boolean isAnnotationPresent(Class<? extends Annotation> annotClass) {
    return getAnnotation(annotClass) != null;
  }

  public boolean isVarArgs() {
    return false;
  }
}
