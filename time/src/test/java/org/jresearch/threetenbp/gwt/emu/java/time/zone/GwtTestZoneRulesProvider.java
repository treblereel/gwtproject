package org.jresearch.threetenbp.gwt.emu.java.time.zone;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import org.jresearch.threetenbp.gwt.emu.java.time.ZoneOffset;

public final class GwtTestZoneRulesProvider extends ZoneRulesProvider {

	/**
	 * All the versions that are available.
	 */
	private final Map<String, ZoneRules> rules = new HashMap<>();

	public GwtTestZoneRulesProvider() {
		rules.put("Europe/Paris", ZoneRules.of(ZoneOffset.ofHours(2)));
		rules.put("Europe/London", ZoneRules.of(ZoneOffset.ofHours(2)));
	}

	// -----------------------------------------------------------------------
	@Override
	protected Set<String> provideZoneIds() {
		return new HashSet<>(rules.keySet());
	}

	@Override
	protected ZoneRules provideRules(String zoneId, boolean forCaching) {
		Objects.requireNonNull(zoneId, "zoneId");
		ZoneRules result = rules.get(zoneId);
		if (result == null) {
			throw new ZoneRulesException("Unknown time-zone ID: " + zoneId);
		}
		return result;
	}

	@Override
	protected NavigableMap<String, ZoneRules> provideVersions(String zoneId) {
		TreeMap<String, ZoneRules> map = new TreeMap<>();
		map.put("test", provideRules(zoneId, false));
		return map;
	}

	@Override
	public String toString() {
		return "GWTTest";
	}

}
