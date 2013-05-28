package sucursal.ui.swing;

import java.awt.EventQueue;

import sucursal.ui.Loader;
import sucursal.ui.controllers.MainController;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * {@link Loader} implementation which loads the Swing application windows
 */
@Singleton
public class SwingLoader implements Loader {
	private final MainController mainController;

	@Inject
	public SwingLoader(final MainController mainController) {
		this.mainController = mainController;
	}

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
