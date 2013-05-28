package sucursal.ui.views;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;
import sucursal.modelo.productos.Producto;
import sucursal.utilities.Evento;
import sucursal.utilities.EventoParametrizado;

/**
 * Represents a view which allows the user to configure a new
 * {@link ItemProducto} in a {@link Compra}.
 */
public interface NuevoProductoView {
	/**
	 * Event parameter class for the {@link NuevoProductoView#getOnAceptar()}
	 * event.
	 */
	public static class NuevoProductoParams {
		private final String codigo;
		private final String cantidad;

		public NuevoProductoParams(String codigo, String cantidad) {
			this.codigo = codigo;
			this.cantidad = cantidad;
		}

		/**
		 * The code which was entered by the user to identify the
		 * {@link Producto} for which an {@link ItemProducto} needs to be added
		 * to the {@link Compra}.
		 */
		public String getCodigo() {
			return codigo;
		}

		/**
		 * The amount which was entered by the user to specify the amount of
		 * units of {@link Producto} of the {@link ItemProducto} to be added to
		 * the {@link Compra}.
		 * 
		 * @return
		 */
		public String getCantidad() {
			return cantidad;
		}
	}

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
	 * accept the configured {@link ItemProducto}.
	 */
	EventoParametrizado<NuevoProductoView, NuevoProductoParams> getOnAceptar();

	/**
	 * Event which can be watched to be notified when the user asks the view to
	 * cancel the configured {@link ItemProducto}.
	 */
	Evento<NuevoProductoView> getOnCancelar();
}
