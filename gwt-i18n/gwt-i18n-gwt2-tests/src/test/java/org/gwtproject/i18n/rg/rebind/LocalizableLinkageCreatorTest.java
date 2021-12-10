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
package org.gwtproject.i18n.rg.rebind;

import com.google.gwt.dev.javac.testing.impl.MockJavaResource;
import junit.framework.TestCase;
import org.junit.Ignore;

/** Check that locale aliases are handled properly for Localizable and related processing. */
@Ignore
public class LocalizableLinkageCreatorTest extends TestCase {

  private static final MockJavaResource LOCALIZABLE =
      new MockJavaResource("org.gwtproject.i18n.client.Localizable") {
        @Override
        public CharSequence getContent() {
          StringBuffer code = new StringBuffer();
          code.append("package org.gwtproject.i18n.client;\n");
          code.append("public interface Localizable { }\n");
          return code;
        }
      };

  private static final MockJavaResource TEST =
      new MockJavaResource("foo.Test") {
        @Override
        public CharSequence getContent() {
          StringBuffer code = new StringBuffer();
          code.append("package foo;\n");
          code.append("import org.gwtproject.i18n.client.Localizable;\n");
          code.append("public class Test implements Localizable { }\n");
          return code;
        }
      };

  private static final MockJavaResource TEST_HE =
      new MockJavaResource("foo.Test_he") {
        @Override
        public CharSequence getContent() {
          StringBuffer code = new StringBuffer();
          code.append("package foo;\n");
          code.append("public class Test_he extends Test { }\n");
          return code;
        }
      };

  private static final MockJavaResource TEST_IW =
      new MockJavaResource("foo.Test_iw") {
        @Override
        public CharSequence getContent() {
          StringBuffer code = new StringBuffer();
          code.append("package foo;\n");
          code.append("public class Test_iw extends Test { }\n");
          return code;
        }
      };
}
