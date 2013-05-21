package sucursal.ui;

import sucursal.modelo.Caja;
import sucursal.modelo.EventoObservable;

/**
 * Interface representing the main UI
 */
public interface MainView {
	/**
	 * Launches this UI
	 */
	public void display();

	public void observar(Caja caja);

	public EventoObservable<MainView, Boolean> getOnAbrirCaja();

	public EventoObservable<MainView, Boolean> getOnCerrarCaja();
}
