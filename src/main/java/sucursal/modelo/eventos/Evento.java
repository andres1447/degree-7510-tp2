package sucursal.modelo.eventos;

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
	/**
	 * The list of registered observers
	 */
	private final List<Observador<T>> observadores = new ArrayList<>();

	/**
	 * The instance owning the observable event
	 */
	private final T observable;

	/**
	 * Creates a new {@link Evento} instance
	 * 
	 * @param observable
	 *            the instance owning the observable event
	 */
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
	 * 
	 * @param data
	 *            the event data to send to all observers
	 */
	public void notificar() {
		for (Observador<T> observer : observadores) {
			observer.notificar(observable);
		}
	}
}
