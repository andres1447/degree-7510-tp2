package sucursal.ui.views;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;
import sucursal.utilities.Evento;

/**
 * Represents a view which allows the user to set up a new {@link Compra}.
 */
public interface CompraView {
	/**
	 * Sets up the view to observe the events raised by the given {@link Compra}
	 * instance.
	 */
	void observar(final Compra compra);

	/**
	 * Displays the view on screen, allowing the user to interact with it.
	 */
	void displayView();

	/**
	 * Removes the view from screen, so that the user may no longer interact
	 * with it.
	 */
	void hideView();

	/**
	 * Event which can be watched to be notified when the user asks the view to
	 * confirm the associated {@link Compra}.
	 */
	Evento<CompraView> getOnConfirmarCompra();

	/**
	 * Event which can be watched to be notified when the user asks the view to
	 * cancel the associated {@link Compra}.
	 */
	Evento<CompraView> getOnCancelarCompra();

	/**
	 * Event which can be watched to be notified when the user asks the view to
	 * add a new {@link ItemProducto} to the associated {@link Compra}.
	 */
	Evento<CompraView> getOnAgregarProducto();

	/**
	 * Event which can be watched to be notified when the user asks the view to
	 * undo the last addition of an {@link ItemProducto} to the associated
	 * {@link Compra}.
	 */
	Evento<CompraView> getOnDeshacer();
}
