/*
 * Copyright © 2019 The GWT Project Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gwtproject.resources.client;

import com.google.gwt.junit.client.GWTTestCase;

/** @author Dmitrii Tikhomirov <chani.liet@gmail.com> Created by treblereel on 10/19/18. */
public class TextResourceTest extends GWTTestCase {
  private static final String HELLO = "Hello World!";
  String result;

  @Override
  public String getModuleName() {
    return "org.gwtproject.resources.ResourcesTestsModule";
  }

  /**
   * Test fix for problem where large text files caused out of memory errors when run in Development
   * Mode.
   */
  public void testBigTextResource() {
    final Resources r = new TextResourceTest_ResourcesImpl();
    String result = r.bigTextResource().getText();
    int length = result.length();
    assertEquals(12737792, length);
  }

  public void testInline() {
    Resources r = new TextResourceTest_ResourcesImpl();
    assertEquals(HELLO, r.helloWorldRelative().getText());
    assertEquals(HELLO, r.helloWorldAbsolute().getText());
  }

  public void testMeta() {
    Resources r = new TextResourceTest_ResourcesImpl();

    // assertEquals(GWT.getModuleBaseForStaticFiles(), DomGlobal.location.getOrigin());

    assertEquals("helloWorldAbsolute", r.helloWorldAbsolute().getName());
    assertEquals("helloWorldRelative", r.helloWorldRelative().getName());
    assertEquals("helloWorldExternal", r.helloWorldExternal().getName());

    ResourcePrototype[] resources = r.getResources();
    assertEquals(6, resources.length);
  }

  public void testAnotatelessExternalText() {
    Resources r = new TextResourceTest_ResourcesImpl();

    try {
      r.helloWorldExternal()
          .getText(
              new ResourceCallback<TextResource>() {
                @Override
                public void onError(ResourceException e) {}

                @Override
                public void onSuccess(TextResource resource) {
                  result = resource.getText();
                  assertEquals("Hello World!", resource.getText());
                }
              });
    } catch (ResourceException e) {
      throw new Error(e);
    }
  }

  public void testAnotatelessText() {
    Resources r = new TextResourceTest_ResourcesImpl();
    assertEquals(HELLO, r.hello().getText());
  }

  public void testOutsideResourceOracle() {
    Resources r = new TextResourceTest_ResourcesImpl();
    assertTrue(r.helloWorldOutsideResourceOracle().getText().startsWith(HELLO));
  }

  @Resource
  interface Resources extends ClientBundleWithLookup {

    @Source("bigtextresource.txt")
    TextResource bigTextResource();

    @Source("org/gwtproject/resources/client/hello.txt")
    TextResource helloWorldAbsolute();

    @Source("hello.txt")
    ExternalTextResource helloWorldExternal();

    @Source("org/gwtproject/resources/server/outside_resource_oracle.txt")
    TextResource helloWorldOutsideResourceOracle();

    @Source("hello.txt")
    TextResource helloWorldRelative();

    TextResource hello();
  }
}
