package sucursal.application;

import sucursal.ui.Loader;
import sucursal.ui.MainUI;
import sucursal.ui.swing.SwingLoader;
import sucursal.ui.swing.SwingMainUI;

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
		bind(Loader.class).to(SwingLoader.class);
		bind(MainUI.class).to(SwingMainUI.class);
	}
}
