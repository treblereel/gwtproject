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

package org.gwtproject.i18n.rg.rebind;

/** @author Dmitrii Tikhomirov Created by treblereel 6/30/19 */
public enum Visibility {
  Public,
  Private {
    public boolean matches(Visibility visibility) {
      switch (visibility) {
        case LegacyDeploy:
        case Private:
          return true;
        default:
          return false;
      }
    }
  },
  Deploy {
    public boolean matches(Visibility visibility) {
      switch (visibility) {
        case LegacyDeploy:
        case Deploy:
          return true;
        default:
          return false;
      }
    }
  },
  Source,
  LegacyDeploy {
    public boolean matches(Visibility visibility) {
      switch (visibility) {
        case LegacyDeploy:
        case Private:
        case Deploy:
          return true;
        default:
          return false;
      }
    }
  };

  private Visibility() {}

  public boolean matches(Visibility visibility) {
    return this == visibility;
  }
}
