package sucursal.modelo.ofertas.predicados;

import java.util.Date;

import com.google.common.base.Predicate;

/**
 * Predicate function which returns true if the {@link Date} is in a given
 * range, and false otherwise.
 */
public class PredicadoFechaEnRango implements Predicate<Date> {
	public static PredicadoFechaEnRango para(final Date desde, final Date hasta) {
		return new PredicadoFechaEnRango(desde, hasta);
	}

	private final Date desde;
	private final Date hasta;

	private PredicadoFechaEnRango(final Date desde, final Date hasta) {
		this.desde = desde;
		this.hasta = hasta;
	}

	@Override
	public boolean apply(final Date fechaCreacion) {
		return fechaCreacion.after(desde) && fechaCreacion.before(hasta);
	}
}
