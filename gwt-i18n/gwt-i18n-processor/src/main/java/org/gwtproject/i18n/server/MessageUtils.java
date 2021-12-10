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
package org.gwtproject.i18n.server;

import static org.gwtproject.i18n.client.LocalizableResource.*;

import javax.lang.model.element.TypeElement;
import org.gwtproject.i18n.client.Constants;
import org.gwtproject.i18n.client.Messages;
import org.gwtproject.i18n.context.AptContext;
import org.gwtproject.i18n.server.keygen.MethodNameKeyGenerator;

/** Utilities for processing GWT i18n messages. */
public class MessageUtils {

  /**
   * An exception signaling {@link #getKeyGenerator(GenerateKeys)} was unable to process the
   * annotation.
   */
  public static class KeyGeneratorException extends Exception {

    /** @param message */
    public KeyGeneratorException(String message) {
      super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public KeyGeneratorException(String message, Throwable cause) {
      super(message, cause);
    }
  }

  public static Class<?>[] SELECTOR_ANOTATIONS =
      new Class<?>[] {Messages.PluralCount.class, Messages.Select.class};

  public static String getConstantsDefaultValue(Message msg) {
    Constants.DefaultStringValue dsv = msg.getAnnotation(Constants.DefaultStringValue.class);
    if (dsv != null) {
      return dsv.value();
    }
    Constants.DefaultIntValue div = msg.getAnnotation(Constants.DefaultIntValue.class);
    if (div != null) {
      return String.valueOf(div.value());
    }
    Constants.DefaultBooleanValue dbv = msg.getAnnotation(Constants.DefaultBooleanValue.class);
    if (dbv != null) {
      return String.valueOf(dbv.value());
    }
    Constants.DefaultDoubleValue ddv = msg.getAnnotation(Constants.DefaultDoubleValue.class);
    if (ddv != null) {
      return String.valueOf(ddv.value());
    }
    Constants.DefaultFloatValue dfv = msg.getAnnotation(Constants.DefaultFloatValue.class);
    if (dfv != null) {
      return String.valueOf(dfv.value());
    }
    Constants.DefaultStringArrayValue dsav =
        msg.getAnnotation(Constants.DefaultStringArrayValue.class);
    if (dsav != null) {
      StringBuilder buf = new StringBuilder();
      boolean needComma = false;
      for (String value : dsav.value()) {
        if (needComma) {
          buf.append(',');
        } else {
          needComma = true;
        }
        buf.append(MessageUtils.quoteComma(value));
      }
      return buf.toString();
    }
    Constants.DefaultStringMapValue dsmv = msg.getAnnotation(Constants.DefaultStringMapValue.class);
    if (dsmv != null) {
      String[] values = dsmv.value();
      StringBuilder buf = new StringBuilder();
      boolean needComma = false;
      for (int i = 0; i < values.length; i += 2) {
        if (needComma) {
          buf.append(',');
        } else {
          needComma = true;
        }
        buf.append(MessageUtils.quoteComma(values[i]));
      }
      return buf.toString();
    }
    return null;
  }

  @SuppressWarnings("deprecation")
  public static KeyGenerator getKeyGenerator(AptContext context, GenerateKeys keyGenAnnot)
      throws KeyGeneratorException {
    if (keyGenAnnot == null) {
      return new MethodNameKeyGenerator();
    }
    String keyGenClassName = keyGenAnnot.value();
    Throwable caught = null;
    try {

      TypeElement keyGenClassNameTypeElement = context.elements.getTypeElement(keyGenClassName);
      TypeElement keyGenerator =
          context.elements.getTypeElement(KeyGenerator.class.getCanonicalName());

      Class<?> clazz = Class.forName(keyGenClassName);

      if (KeyGenerator.class.isAssignableFrom(clazz)) {
        Class<? extends KeyGenerator> kgClass = clazz.asSubclass(KeyGenerator.class);
        return kgClass.newInstance();
      }

      if (org.gwtproject.i18n.rg.rebind.keygen.KeyGenerator.class.isAssignableFrom(clazz)) {
        Class<? extends org.gwtproject.i18n.rg.rebind.keygen.KeyGenerator> kgClass =
            clazz.asSubclass(org.gwtproject.i18n.rg.rebind.keygen.KeyGenerator.class);
        return new KeyGeneratorAdapter(kgClass.newInstance());
      }
      throw new KeyGeneratorException(
          keyGenClassName + " in @GenerateKeys must implement KeyGenerator");
    } catch (ClassNotFoundException e) {
      caught = e;
    } catch (InstantiationException e) {
      caught = e;
    } catch (IllegalAccessException e) {
      caught = e;
    }
    throw new KeyGeneratorException(
        "Unable to process @GenerateKeys('" + keyGenClassName + "'): " + caught.getMessage(),
        caught);
  }

  public static String quoteComma(String value) {
    return value.replace(",", "\\,");
  }
}
