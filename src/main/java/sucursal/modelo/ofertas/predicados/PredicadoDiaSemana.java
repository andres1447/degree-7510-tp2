package sucursal.modelo.ofertas.predicados;

import java.util.Calendar;
import java.util.Date;

import com.google.common.base.Predicate;

public class PredicadoDiaSemana implements Predicate<Date> {
	public static PredicadoDiaSemana instance(final int diaObjetivo) {
		return new PredicadoDiaSemana(diaObjetivo);
	}
	
	private final int diaObjetivo;

	private PredicadoDiaSemana(int diaObjetivo) {
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
