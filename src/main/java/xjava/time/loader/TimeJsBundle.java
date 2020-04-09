package xjava.time.loader;

import javax.annotation.Nonnull;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.TextResource;

public interface TimeJsBundle extends ClientBundle {

	@Nonnull
	@Source("support.js")
	public TextResource support();

	@Nonnull
	@Source("base64-binary.js")
	public TextResource base64binary();

	@Nonnull
	@Source("TZDB.dat")
	public DataResource tzdb();
}
