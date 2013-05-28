package sucursal.ui.controllers;

import sucursal.modelo.compras.Compra;
import sucursal.modelo.compras.ItemDescuento;
import sucursal.modelo.compras.ItemProducto;
import sucursal.ui.views.CompraView;
import sucursal.ui.views.SimpleDialog;
import sucursal.utilities.Observador;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Controller which handles the process of setting up a new {@link Compra}.
 */
@Singleton
public class CompraController {
	private Compra compra;

	private final CompraView view;
	private final NuevoProductoController nuevoProductoController;
	private final SimpleDialog simpleDialog;

	private Observador<CompraView> onConfirmarCompra = new Observador<CompraView>() {
		@Override
		public void notificar(CompraView observable) {
			compra.confirmar();

			StringBuffer informe = new StringBuffer();
			informe.append("Confirmando compra de los siguientes itemts:\n");
			for (ItemProducto item : compra.getItems()) {
				informe.append("    ").append("$").append(item.getTotal())
						.append("    ").append(item.getCantidad()).append(" ")
						.append(item.getProducto().getNombre()).append(" ($")
						.append(item.getProducto().getPrecioUnitario())
						.append(" por unidad)\n");
			}
			informe.append("\nSe han aplicado los siguientes descuentos:\n");
			for (ItemDescuento descuento : compra.getDescuentos()) {
				informe.append("    ").append("-$")
						.append(descuento.getValor()).append("    ")
						.append(descuento.getDescripcion()).append("\n");
			}
			informe.append("\n\nEl total es ").append(compra.getTotal());
			simpleDialog.showInfo(informe.toString());
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

	/**
	 * Launches the process of configuring a new {@link Compra}, displaying the
	 * appropriate views and listening for user interaction.
	 */
	public void launch(final Compra compra) {
		this.compra = compra;
		view.observar(compra);
		view.displayView();
	}

}
