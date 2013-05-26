package sucursal.ui;

import sucursal.exceptions.CajaNoInicializadaException;
import sucursal.exceptions.CajaYaAbiertaException;
import sucursal.modelo.Caja;
import sucursal.modelo.EventoObservable.Observador;
import sucursal.modelo.Sucursal;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class MainController {
	private final Caja caja;
	private final MainView view;
	private final SimpleDialog simpleDialog;

	private final Observador<MainView, Boolean> onAbrirCaja = new Observador<MainView, Boolean>() {
		@Override
		public void notificar(MainView observable, Boolean data) {
			try {
				caja.abrirCaja();
			} catch (CajaYaAbiertaException e) {
				simpleDialog.showError("La caja ya se encuentra abierta.");
			}
		}
	};

	private Observador<MainView, Boolean> onCerrarCaja = new Observador<MainView, Boolean>() {
		@Override
		public void notificar(MainView observable, Boolean data) {
			try {
				caja.cerrarCaja();
			} catch (CajaNoInicializadaException e) {
				simpleDialog.showError("La caja ya se encuentra cerrada.");
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
	}

	public void launch() {
		view.observar(this.caja);
		view.display();
	}

}
