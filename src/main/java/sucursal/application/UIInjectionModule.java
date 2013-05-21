package sucursal.application;

import sucursal.ui.Loader;
import sucursal.ui.MainView;
import sucursal.ui.SimpleDialog;
import sucursal.ui.swing.SwingLoader;
import sucursal.ui.swing.SwingMainView;
import sucursal.ui.swing.SwingSimpleDialog;

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
		bind(MainView.class).to(SwingMainView.class);
		bind(SimpleDialog.class).to(SwingSimpleDialog.class);
	}
}
