package sucursal.ui.swing;

import java.awt.EventQueue;

import sucursal.ui.Loader;
import sucursal.ui.MainController;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * {@link Loader} implementation which loads the Swing application windows
 */
@Singleton
public class SwingLoader implements Loader {
	/**
	 * The main application UI to load
	 */
	private final MainController mainController;

	/**
	 * Creates a new {@link SwingLoader} instance
	 * 
	 * @param mainUI
	 *            the main application UI to load
	 */
	@Inject
	public SwingLoader(final MainController mainController) {
		this.mainController = mainController;
	}

	/**
	 * The runnable task to invoke on application startup which loads the
	 * application UI
	 */
	private final Runnable loadApplicationUI = new Runnable() {
		public void run() {
			try {
				mainController.launch();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	/**
	 * Implements {@link Loader#loadUI()} to use AWT infrastructure to display
	 * the application window on the AWT event thread.
	 */
	@Override
	public void loadUI() {
		EventQueue.invokeLater(loadApplicationUI);
	}

}