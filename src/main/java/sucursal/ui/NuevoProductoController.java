package sucursal.ui;

import sucursal.exceptions.ProductoInexistenteException;
import sucursal.modelo.Compra;
import sucursal.modelo.LineProducto;
import sucursal.modelo.Producto;
import sucursal.ui.NuevoProductoView.NuevoProductoParams;
import sucursal.utilities.Observador;
import sucursal.utilities.ObservadorParametrizado;

import com.google.inject.Inject;
import com.google.inject.Singleton;

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
				compra.agregarItem(new LineProducto(producto, cantidad));
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

	public void launch(final Compra compra) {
		this.compra = compra;
		view.displayView();
	}
}
