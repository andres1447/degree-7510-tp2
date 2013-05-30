package sucursal.ui.controllers;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemProducto;
import sucursal.modelo.exceptions.ProductoInexistenteException;
import sucursal.modelo.productos.Producto;
import sucursal.ui.views.NuevoProductoView;
import sucursal.ui.views.NuevoProductoView.NuevoProductoParams;
import sucursal.ui.views.SimpleDialog;
import sucursal.utilities.Observador;
import sucursal.utilities.ObservadorParametrizado;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * controller which represents the process of creating a new
 * {@link ItemProducto} for a {@link Compra}.
 */
@Singleton
public class NuevoProductoController {
	private Compra compra;
	private final NuevoProductoView view;
	private final SimpleDialog simpleDialog;

	private ObservadorParametrizado<NuevoProductoView, NuevoProductoParams> onAceptar = new ObservadorParametrizado<NuevoProductoView, NuevoProductoParams>() {
		@Override
		public void notificar(NuevoProductoView observable,
				NuevoProductoParams data) {
			try {
				Producto producto = compra.getListadoProductos()
						.buscarPorCodigo(data.getCodigo());
				int cantidad = Integer.parseInt(data.getCantidad());
				compra.agregarItem(producto, cantidad);
				view.hideView();
			} catch (NumberFormatException e) {
				simpleDialog
						.showError("La cantidad no es un valor numerico valido");
			} catch (ProductoInexistenteException e) {
				simpleDialog
						.showError("No existe ningun producto con ese codigo");
			}

		}
	};

	private Observador<NuevoProductoView> onCancelar = new Observador<NuevoProductoView>() {
		@Override
		public void notificar(NuevoProductoView observable) {
			view.hideView();
		}
	};

	@Inject
	public NuevoProductoController(final NuevoProductoView view,
			final SimpleDialog simpleDialog) {
		this.view = view;
		this.simpleDialog = simpleDialog;
		this.view.getOnAceptar().registrar(onAceptar);
		this.view.getOnCancelar().registrar(onCancelar);
	}

	/**
	 * Launches the process of adding a new {@link ItemProducto} to a
	 * {@link Compra}, displaying the appropriate view and listening for user
	 * interaction.
	 */
	public void launch(final Compra compra) {
		this.compra = compra;
		view.displayView();
	}
}
