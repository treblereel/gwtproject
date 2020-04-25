package java.time.zone;

import java.nio.ByteBuffer;

public class Providers {

	private Providers() {
		// prevent instantiation
	}

	public static ZoneRulesProvider of(ByteBuffer data) {
		return new TzdbZoneRulesProvider(data);
	}

}
