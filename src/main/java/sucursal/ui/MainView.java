package sucursal.ui;

import sucursal.modelo.Caja;
import sucursal.utilities.Evento;

/**
 * Interface representing the main UI
 */
public interface MainView {
	/**
	 * Launches this UI
	 */
	public void display();

	public void observar(Caja caja);

	public Evento<MainView> getOnAbrirCaja();

	public Evento<MainView> getOnCerrarCaja();
}
