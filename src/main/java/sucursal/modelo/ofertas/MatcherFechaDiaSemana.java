package sucursal.modelo.ofertas;

import java.util.Date;

public class MatcherFechaDiaSemana implements MatcherFecha {
	@Override
	public boolean matcheaCon(final Date fechaCreacion) {
		// TODO: Obtener el dia de la semana para comparar
		return false;
	}
}
