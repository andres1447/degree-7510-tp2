package sucursal.ui.controllers;

import java.util.List;
import java.util.Map.Entry;

import sucursal.modelo.Sucursal;
import sucursal.modelo.caja.Caja;
import sucursal.modelo.compras.Compra;
import sucursal.modelo.exceptions.CajaNoAbiertaException;
import sucursal.modelo.exceptions.CajaYaAbiertaException;
import sucursal.modelo.exceptions.CompraEnProcesoException;
import sucursal.ui.views.MainView;
import sucursal.ui.views.SimpleDialog;
import sucursal.utilities.Observador;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Controller which represents an application session.o
 */
@Singleton
public class MainController {
	private Sucursal sucursal;	
	private final Caja caja;
	private final CompraController compraController;
	private final MainView view;
	private final SimpleDialog simpleDialog;

	private final Observador<MainView> onAbrirCaja = new Observador<MainView>() {
		@Override
		public void notificar(MainView observable) {
			try {
				caja.abrir();
			} catch (CajaYaAbiertaException e) {
				simpleDialog.showError("La caja ya se encuentra abierta.");
			}
		}
	};

	private Observador<MainView> onCerrarCaja = new Observador<MainView>() {
		@Override
		public void notificar(MainView observable) {
			try {
				caja.cerrar();
				List<Entry<String, Integer>> resumen = sucursal.generarResumenVentas();
				StringBuilder mensaje = new StringBuilder();
				for (Entry<String, Integer> entry : resumen) {
					mensaje.append("Producto " + entry.getKey() + " vendio " + entry.getValue() + " unidades\n");
				}
				simpleDialog.showInfo(mensaje.toString());
				
			} catch (CajaNoAbiertaException e) {
				simpleDialog.showError("La caja ya se encuentra cerrada.");
			}
		}
	};

	private Observador<MainView> onIniciarCompra = new Observador<MainView>() {
		@Override
		public void notificar(MainView observable) {
			try {
				Compra compra = caja.iniciarCompra();
				compraController.launch(compra);
			} catch (CompraEnProcesoException e) {
				simpleDialog.showError("Compra en proceso");
			} catch (CajaNoAbiertaException e) {
				simpleDialog.showError("La caja se encuentra cerrada");
			}
		}
	};


	@Inject
	public MainController(final Sucursal sucursal,
			final CompraController compraController, final MainView view,
			final SimpleDialog simpleDialog) {
		this.sucursal = sucursal;
		this.caja = sucursal.habilitarCaja();
		this.compraController = compraController;
		this.simpleDialog = simpleDialog;
		this.view = view;
		this.view.getOnAbrirCaja().registrar(onAbrirCaja);
		this.view.getOnCerrarCaja().registrar(onCerrarCaja);
		this.view.getOnIniciarCompra().registrar(onIniciarCompra);
	}

	/**
	 * Launches the main process of interaction with the application, displaying
	 * the appropriate views and listening for user interaction.
	 */
	public void launch() {
		view.observar(this.caja);
		view.displayView();
	}

}
