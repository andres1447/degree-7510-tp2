package sucursal.ui.swing;

import java.awt.EventQueue;

import sucursal.modelo.Sucursal;
import sucursal.ui.Loader;

import com.google.inject.Singleton;

/**
 * {@link Loader} implementation which loads the Swing application windows
 */
@Singleton
public class SwingLoader implements Loader {
	/**
	 * The runnable task to invoke on application startup which loads the
	 * application UI
	 */
	private final Runnable loadApplicationUI = new Runnable() {
		public void run() {
			try {
				Sucursal sucursal = new Sucursal();
				AppUI window = new AppUI(sucursal);
				window.setVisible(true);
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
