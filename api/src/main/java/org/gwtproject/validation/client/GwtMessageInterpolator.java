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

import java.util.Locale;

import org.gwtproject.i18n.shared.GwtLocale;

/**
 * Simple GWT {@link javax.validation.MessageInterpolator}.
 */
public final class GwtMessageInterpolator extends BaseMessageInterpolator {
    // This class only has the parts the need to overridden for GWT

    /**
     * Creates a {@link javax.validation.MessageInterpolator MessageInterpolator}
     * MessageInterpolator that uses the default
     * {@link UserValidationMessagesResolver}.
     */
    public GwtMessageInterpolator() {
        this(new ProviderValidationMessageResolverImpl());
    }

    /**
     * Creates a {@link javax.validation.MessageInterpolator MessageInterpolator}
     * using the supplied{@link UserValidationMessagesResolver}.
     * @param userValidationMessagesResolver
     */
    public GwtMessageInterpolator(
            UserValidationMessagesResolver userValidationMessagesResolver) {
        super(userValidationMessagesResolver);
    }
}
