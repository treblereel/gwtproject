/*
 * Copyright © 2021 The GWT Project Authors
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

package org.jresearch.threetenbp.gwt.tzdb.client;

import org.jresearch.threetenbp.gwt.emu.org.jresearch.threetenbp.gwt.time.client.Support;
import org.jresearch.threetenbp.gwt.tzdb.client.zone.GwtTzdbZoneRuleProvider;

public class TzdbEntryPoint {

	//@Override
	public void onModuleLoad() {
		Support.registerGwtZoneRuleProvider(new GwtTzdbZoneRuleProvider());
	}

}
