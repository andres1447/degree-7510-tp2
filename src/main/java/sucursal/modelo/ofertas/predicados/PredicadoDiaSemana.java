package sucursal.modelo.ofertas.predicados;

import java.util.Calendar;
import java.util.Date;

import com.google.common.base.Predicate;

/**
 * Predicate function which returns true if the {@link Date} has a given week
 * day, and false otherwise.
 */
public class PredicadoDiaSemana implements Predicate<Date> {
	private final int diaObjetivo;

	public PredicadoDiaSemana(int diaObjetivo) {
		this.diaObjetivo = diaObjetivo;
	}

	@Override
	public boolean apply(final Date fechaCreacion) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaCreacion);
		int diaFecha = calendar.get(Calendar.DAY_OF_WEEK);
		return diaFecha == diaObjetivo;
	}
}
