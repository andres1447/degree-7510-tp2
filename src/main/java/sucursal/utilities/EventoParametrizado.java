package sucursal.utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic utility class to manage event propagation. Represents an event which
 * can be observed. The event is parametrized, meaning that when it is raised,
 * additional data will be attached to the event.
 * 
 * @param <T>
 *            The type of the class owning the observable event
 * @param <D>
 *            The type of the data associated to the event
 */
public class EventoParametrizado<T, D> {
	private final List<ObservadorParametrizado<T, D>> observadores = new ArrayList<>();
	private final T observable;

	public EventoParametrizado(final T observable) {
		this.observable = observable;
	}

	/**
	 * Registers an observer to be notified when the event is raised
	 */
	public void registrar(final ObservadorParametrizado<T, D> observer) {
		this.observadores.add(observer);
	}

	/**
	 * Unregisters an observer to no longer be notified when the event is raised
	 */
	public void desregistrar(final ObservadorParametrizado<T, D> observer) {
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
	 * Raises the event, notifying all registered observers, and including the
	 * given data in the notification.
	 */
	public void notificar(final D data) {
		for (ObservadorParametrizado<T, D> observer : observadores) {
			observer.notificar(observable, data);
		}
	}
}
