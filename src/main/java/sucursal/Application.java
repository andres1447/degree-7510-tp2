package sucursal;

import java.awt.EventQueue;

import sucursal.modelo.Sucursal;
import sucursal.ui.AppUI;

public class Application {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sucursal sucursal = new Sucursal();
					AppUI window = new AppUI(sucursal);
					window.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
