package org.jresearch.threetenbp.gwt.client.zone;

import java.time.zone.ZoneRulesProvider;
import java.util.function.Consumer;

public interface GwtZoneRuleProvider {

	boolean isInitializing();

	boolean isInitialized();

	boolean isAsyncInitializeSupported();

	ZoneRulesProvider initialize();

	void initiatedAsyncInitialize(Consumer<ZoneRulesProvider> callback);

}
