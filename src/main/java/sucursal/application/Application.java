package sucursal.application;

import sucursal.ui.Loader;

import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * Main application class, in charge of bootstraping the application and loading
 * the main application UI.
 */
public class Application {
	/**
	 * The {@link Loader}, in charge of loading the main application UI.
	 */
	private final Loader uiLoader;

	/**
	 * Creates a new {@link Application} instance
	 * 
	 * @param uiLoader
	 *            the {@link Loader}, in charge of loading the main
	 *            application UI.
	 */
	@Inject
	public Application(final Loader uiLoader) {
		this.uiLoader = uiLoader;
	}

	/**
	 * Bootstraps the application and runs it
	 */
	public void run() {
		uiLoader.loadUI();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Injector injector = Configuration.bootstrapInjector();
		Application application = injector.getInstance(Application.class);
		application.run();
	}
}
