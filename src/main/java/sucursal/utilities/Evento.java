package sucursal.utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic utility class to manage event propagation. Represents an event which
 * can be observed.
 * 
 * @param <T>
 *            The type of the class owning the observable event
 */
public class Evento<T> {
	private final List<Observador<T>> observadores = new ArrayList<>();
	private final T observable;

	public Evento(final T observable) {
		this.observable = observable;
	}

	/**
	 * Registers an observer to be notified when the event is raised
	 */
	public void registrar(final Observador<T> observer) {
		this.observadores.add(observer);
	}

	/**
	 * Unregisters an observer to no longer be notified when the event is raised
	 */
	public void desregistrar(final Observador<T> observer) {
		this.observadores.remove(observer);
	}

	/**
	 * Unregisters all observers so that no previously registered observer is
	 * notified when the event is raised
	 */
	public void limpiarObservadores() {
		this.observadores.clear();
	}

	/**
	 * Raises the event, notifying all registered observers
	 */
	public void notificar() {
		for (Observador<T> observer : observadores) {
			observer.notificar(observable);
		}
	}
}
