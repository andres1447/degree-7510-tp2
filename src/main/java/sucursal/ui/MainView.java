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
	void display();

	void observar(Caja caja);

	Evento<MainView> getOnAbrirCaja();

	Evento<MainView> getOnCerrarCaja();

	Evento<MainView> getOnIniciarCompra();
}
