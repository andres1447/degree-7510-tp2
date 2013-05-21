package sucursal.application;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Utility class which configures the {@link Injector} instance to use through
 * the application.
 */
class InjectorConfiguration {
	/**
	 * Configures a new {@link Injector} instance to use through the application
	 */
	public static Injector bootstrapInjector() {
		return Guice.createInjector(new UIInjectionModule());
	}
}
