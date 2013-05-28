package sucursal.ui.views;

import sucursal.modelo.caja.Caja;
import sucursal.utilities.Evento;

/**
 * Represents the main view of the application, which allows the user to enable
 * and interact with a {@link Caja}.
 */
public interface MainView {
	/**
	 * Displays the view on screen, allowing the user to interact with it.
	 */
	void displayView();

	/**
	 * Sets up the view to observe the events raised by a given {@link Caja}.
	 */
	void observar(Caja caja);

	/**
	 * Event which can be watched to be notified when the user asks the view to
	 * open the current {@link Caja}.
	 */
	Evento<MainView> getOnAbrirCaja();

	/**
	 * Event which can be watched to be notified when the user asks the view to
	 * close the current {@link Caja}.
	 */
	Evento<MainView> getOnCerrarCaja();

	/**
	 * Event which can be watched to be notified when the user asks the view to
	 * create a new buying session to the current {@link Caja}.
	 */
	Evento<MainView> getOnIniciarCompra();
}
