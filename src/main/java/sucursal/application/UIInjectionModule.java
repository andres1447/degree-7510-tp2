package sucursal.application;

import sucursal.ui.UILoader;
import sucursal.ui.swing.SwingUILoader;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * {@link Injector} {@link Module} in charge of defining bindings for UI-related
 * interfaces.
 */
public class UIInjectionModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(UILoader.class).to(SwingUILoader.class);
	}
}
