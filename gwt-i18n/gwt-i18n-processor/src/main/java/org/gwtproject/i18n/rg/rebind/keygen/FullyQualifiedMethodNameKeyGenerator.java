/*
 * Copyright 2008 Google Inc.
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
package org.gwtproject.i18n.rg.rebind.keygen;

import org.gwtproject.i18n.server.Message;

/** Key generator using the fully-qualified method name. */
public class FullyQualifiedMethodNameKeyGenerator implements KeyGenerator {

  public String generateKey(Message msg) {
    return msg.getMessageInterface().getQualifiedName() + "." + msg.getMethodName();
  }

  public String generateKey(String className, String methodName, String text, String meaning) {
    return className + "." + methodName;
  }
}
