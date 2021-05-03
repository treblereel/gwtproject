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
package org.gwtproject.validation.client;

import javax.validation.Validator;

import org.gwtproject.validation.client.constraints.PatternValidatorGwtTest.Flagged;
import org.gwtproject.validation.client.impl.AbstractGwtValidator;

/**
 * Factory to create the Validator specified by {@link GwtValidator}.
 * <p>
 * GWT.create instances of this class
 */
public class TestValidatorFactory {

    /**
     * Validator Interface annotated with the list of classes to validate on the
     * client.
     */
    @GwtValidation(value = {Flagged.class})
    public interface GwtValidator extends Validator {

    }
}
