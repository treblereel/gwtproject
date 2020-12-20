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
package org.gwtproject.geolocation.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.junit.Test;

/** Tests for {@link Geolocation}. */
@J2clTestInput(GeolocationTest.class)
public class GeolocationTest {

  protected Geolocation geolocation;

  {
    geolocation = Geolocation.getIfSupported();
  }

  @Test
  public void testNullIfUnsupported() {
    if (!Geolocation.isSupported()) {
      assertNull(geolocation);
    } else {
      assertNotNull(geolocation);
    }
  }
}
