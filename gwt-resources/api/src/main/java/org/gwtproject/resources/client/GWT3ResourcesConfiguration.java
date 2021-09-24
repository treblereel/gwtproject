/*
 * Copyright 2021
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

package org.gwtproject.resources.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** @author Dmitrii Tikhomirov Created by treblereel 9/23/21 */
@Documented
@Inherited
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface GWT3ResourcesConfiguration {

  ClientBundle clientBundle() default @ClientBundle();

  CssResource cssResource() default @CssResource();

  @interface ClientBundle {

    String cacheLocation() default "";

    String cacheUrl() default "";

    boolean enableInlining() default true;

    boolean enableRenaming() default true;
  }

  @interface CssResource {

    String[] allowedFunctions() default {};

    String[] allowedAtRules() default {"-moz-document", "supports"};

    boolean gssDefaultInUiBinder() default false;

    boolean mergeEnabled() default true;

    boolean enableGss() default false;

    String[] reservedClassPrefixes() default {"gwt-"};

    String obfuscationPrefix() default "default";

    String style() default "obf";

    String conversionMode() default "off";
  }
}
