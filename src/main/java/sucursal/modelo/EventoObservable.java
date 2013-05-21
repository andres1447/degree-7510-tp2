package sucursal.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic utility class to manage event propagation. Represents an event which
 * can be observed
 * 
 * @param <T>
 *            The type of the class owning the observable event
 * @param <D>
 *            The type of the data associated to the event
 */
public class EventoObservable<T, D> {
	/**
	 * Represents an observer which can observe an observable event, being
	 * notified when the event is raised
	 * 
	 * @see EventoObservable
	 */
	public static interface Observador<T, D> {
		/**
		 * Notifies this observer that the event has been raised
		 * 
		 * @param observable
		 *            the observable which raised the event
		 * @param data
		 *            specific event data associated to the observable event
		 */
		void notificar(final T observable, final D data);
	}

	/**
	 * The list of registered observers
	 */
	private final List<Observador<T, D>> observadores = new ArrayList<>();

	/**
	 * The instance owning the observable event
	 */
	private final T observable;

	/**
	 * Creates a new {@link EventoObservable} instance
	 * 
	 * @param observable
	 *            the instance owning the observable event
	 */
	public EventoObservable(final T observable) {
		this.observable = observable;
	}

	/**
	 * Registers an observer to be notified when the event is raised
	 */
	public void registrar(final Observador<T, D> observer) {
		this.observadores.add(observer);
	}

	/**
	 * Unregisters an observer to no longer be notified when the event is raised
	 */
	public void desregistrar(final Observador<T, D> observer) {
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
	public void notificar(final D data) {
		for (Observador<T, D> observer : observadores) {
			observer.notificar(observable, data);
		}
	}
}
