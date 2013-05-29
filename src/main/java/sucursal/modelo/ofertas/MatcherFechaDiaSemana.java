package sucursal.modelo.ofertas;

import java.util.Calendar;
import java.util.Date;

public class MatcherFechaDiaSemana implements MatcherFecha {
	private final int diaObjetivo;
	
	public MatcherFechaDiaSemana(int diaObjetivo) {
		this.diaObjetivo = diaObjetivo;
	}

	@Override
	public boolean matcheaCon(final Date fechaCreacion) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaCreacion);
		int diaFecha = calendar.get(Calendar.DAY_OF_WEEK);
		return diaFecha == diaObjetivo;
	}
}
