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

package org.jresearch.threetenbp.gwt.tzdb.client.zone;

import java.nio.ByteBuffer;
import java.time.zone.ZoneRulesProvider;

import elemental2.dom.DomGlobal;
import org.gwtproject.core.client.ScriptInjector;
import org.gwtproject.nio.TypedArrayHelper;
import org.jresearch.threetenbp.gwt.time.client.zone.GwtZoneRuleProvider;
import org.jresearch.threetenbp.gwt.tzdb.client.TzdbJs;
import org.jresearch.threetenbp.gwt.tzdb.client.loader.TzdbJsBundle;
import org.jresearch.threetenbp.gwt.tzdb.client.loader.TzdbJsBundleImpl;

import elemental2.core.ArrayBuffer;

public class GwtTzdbZoneRuleProvider implements GwtZoneRuleProvider {

	private static final TzdbJsBundle bundle = new TzdbJsBundleImpl();

	private static boolean initialized = false;

	@Override
	public boolean isInitialized() {
		return initialized;
	}

	@Override
	public void initialize() {
		DomGlobal.console.debug("TZDB sync initialization called");
		if (!initialized) {
			DomGlobal.console.debug("TZDB sync initialization started");
			ScriptInjector.fromString(bundle.base64binary().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
			String tzData = bundle.tzdbEncoded().getText();
			ArrayBuffer buffer = TzdbJs.decodeArrayBuffer(tzData);
			ByteBuffer data = TypedArrayHelper.wrap(buffer);
			ZoneRulesProvider provider = new TzdbZoneRulesProvider(data);
			if (!initialized) {
				ZoneRulesProvider.registerProvider(provider);
			}
			DomGlobal.console.debug("TZDB sync initialization finished");
			initialized = true;
		}
	}

	@Override
	public String getProviderId() {
		return TzdbZoneRulesProvider.class.getName();
	}

}
