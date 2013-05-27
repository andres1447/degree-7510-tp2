package sucursal.ui;

import sucursal.exceptions.CajaNoAbiertaException;
import sucursal.exceptions.CajaYaAbiertaException;
import sucursal.exceptions.CompraEnProcesoException;
import sucursal.modelo.Caja;
import sucursal.modelo.Sucursal;
import sucursal.utilities.Observador;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class MainController {
	private final Caja caja;
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
			} catch (CajaNoAbiertaException e) {
				simpleDialog.showError("La caja ya se encuentra cerrada.");
			}
		}
	};

	private Observador<MainView> onIniciarCompra = new Observador<MainView>() {
		@Override
		public void notificar(MainView observable) {
			try {
				caja.iniciarCompra();
			} catch (CompraEnProcesoException e) {
				simpleDialog.showError("Compra en proceso");
			} catch (CajaNoAbiertaException e) {
				simpleDialog.showError("La caja se encuentra cerrada");
			}
		}
	};

	@Inject
	public MainController(final Sucursal sucursal, final MainView view,
			final SimpleDialog simpleDialog) {
		this.caja = sucursal.habilitarCaja();
		this.simpleDialog = simpleDialog;
		this.view = view;
		this.view.getOnAbrirCaja().registrar(onAbrirCaja);
		this.view.getOnCerrarCaja().registrar(onCerrarCaja);
		this.view.getOnIniciarCompra().registrar(onIniciarCompra);
	}

	public void launch() {
		view.observar(this.caja);
		view.display();
	}

}
