package sucursal.modelo.eventos;

/**
 * Represents an observer which can observe an observable event, being notified
 * when the event is raised
 * 
 * @see Evento
 */
public interface Observador<T> {
	/**
	 * Notifies this observer that the event has been raised
	 * 
	 * @param observable
	 *            the observable which raised the event
	 * @param data
	 *            specific event data associated to the observable event
	 */
	void notificar(final T observable);
}
