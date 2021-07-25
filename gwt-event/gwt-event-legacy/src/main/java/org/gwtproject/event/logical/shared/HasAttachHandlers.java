/*
 * Copyright 2008 The GWT Project Authors
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
package org.gwtproject.event.logical.shared;

import org.gwtproject.event.shared.HandlerRegistration;
import org.gwtproject.event.shared.HasHandlers;

/** A widget that implements this interface is a public source of {@link AttachEvent} events. */
public interface HasAttachHandlers extends HasHandlers {
  /**
   * Adds an {@link AttachEvent} handler.
   *
   * @param handler the handler
   * @return the handler registration
   */
  HandlerRegistration addAttachHandler(AttachEvent.Handler handler);

  /**
   * Returns whether or not the receiver is attached to the document's {@code body} element.
   *
   * @return true if attached, false otherwise
   */
  boolean isAttached();
}