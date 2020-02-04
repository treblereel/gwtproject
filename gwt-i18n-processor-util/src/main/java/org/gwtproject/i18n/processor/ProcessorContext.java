/*
 * Copyright © 2018 The GWT Authors
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
package org.gwtproject.i18n.processor;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public class ProcessorContext {

  private final Messager messager;
  private final Filer filer;
  private final Types types;
  private final Elements elements;
  private final ProcessingEnvironment processingEnv;

  public ProcessorContext(ProcessingEnvironment processingEnv) {
    this.messager = processingEnv.getMessager();
    this.filer = processingEnv.getFiler();
    this.types = processingEnv.getTypeUtils();
    this.elements = processingEnv.getElementUtils();
    this.processingEnv = processingEnv;
  }

  public Messager getMessager() {
    return messager;
  }

  public Filer getFiler() {
    return filer;
  }

  public Types getTypes() {
    return types;
  }

  public Elements getElements() {
    return elements;
  }

  public Map<String, String> getOptions() {
    return new HashMap<>(processingEnv.getOptions());
  }
}
