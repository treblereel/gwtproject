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

package org.gwtproject.i18n.context;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/** @author Dmitrii Tikhomirov <chani@me.com> Created by treblereel on 10/26/18. */
public class AptContext {

  public final Messager messager;
  public final Filer filer;
  public final Elements elements;
  public final Types types;
  public final RoundEnvironment roundEnvironment;
  public final ProcessingEnvironment processingEnv;

  public AptContext(
      final ProcessingEnvironment processingEnv, final RoundEnvironment roundEnvironment) {
    this.filer = processingEnv.getFiler();
    this.messager = processingEnv.getMessager();
    this.elements = processingEnv.getElementUtils();
    this.types = processingEnv.getTypeUtils();
    this.roundEnvironment = roundEnvironment;
    this.processingEnv = processingEnv;
  }
}
