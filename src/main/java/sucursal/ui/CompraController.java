package sucursal.ui;

import sucursal.modelo.Compra;
import sucursal.utilities.Observador;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CompraController {
	private Compra compra;

	private final CompraView view;
	private final NuevoProductoController nuevoProductoController;
	private final SimpleDialog simpleDialog;

	private Observador<CompraView> onConfirmarCompra = new Observador<CompraView>() {
		@Override
		public void notificar(CompraView observable) {
			// TODO: Implementar confirmacion de compra
			simpleDialog.showInfo("Compra confirmada");
			compra.confirmar();
			view.hideView();
		}
	};

	private Observador<CompraView> onCancelarCompra = new Observador<CompraView>() {
		@Override
		public void notificar(CompraView observable) {
			simpleDialog.showInfo("Compra cancelada");
			compra.cancelar();
			view.hideView();
		}
	};

	private Observador<CompraView> onAgregarProducto = new Observador<CompraView>() {
		@Override
		public void notificar(CompraView observable) {
			nuevoProductoController.launch(compra);
		}
	};

	private Observador<CompraView> onDeshacer = new Observador<CompraView>() {
		@Override
		public void notificar(CompraView observable) {
			compra.quitarUltimoItemAgregado();
		}
	};

	@Inject
	public CompraController(final CompraView view,
			final NuevoProductoController nuevoProductoController,
			final SimpleDialog simpleDialog) {
		this.view = view;
		this.nuevoProductoController = nuevoProductoController;
		this.simpleDialog = simpleDialog;
		this.view.getOnConfirmarCompra().registrar(onConfirmarCompra);
		this.view.getOnCancelarCompra().registrar(onCancelarCompra);
		this.view.getOnAgregarProducto().registrar(onAgregarProducto);
		this.view.getOnDeshacer().registrar(onDeshacer);
	}

	public void launch(final Compra compra) {
		this.compra = compra;
		view.observar(compra);
		view.displayView();
	}

}
