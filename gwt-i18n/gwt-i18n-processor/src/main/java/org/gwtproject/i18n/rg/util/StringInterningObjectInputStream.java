/*
 * Copyright 2014 Google Inc.
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

package org.gwtproject.i18n.rg.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/** An ObjectInputStream that interns all deserialized strings. */
public class StringInterningObjectInputStream extends ObjectInputStream {

  public static final int MAX_INTERNED_STRING_SIZE = 2048;

  public StringInterningObjectInputStream(InputStream inputStream) throws IOException {
    super(inputStream);
    enableResolveObject(true);
  }

  @Override
  protected Object resolveObject(Object obj) throws IOException {
    Object resolvedObject = super.resolveObject(obj);
    if (resolvedObject instanceof String
        && ((String) resolvedObject).length() < MAX_INTERNED_STRING_SIZE) {
      return StringInterner.get().intern((String) resolvedObject);
    }
    return resolvedObject;
  }
}
