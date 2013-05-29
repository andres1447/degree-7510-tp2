package sucursal.modelo.ofertas;

import java.util.Date;

public class MatcherFechaEnRango implements MatcherFecha {
	private final Date desde;
	private final Date hasta;

	public MatcherFechaEnRango(final Date desde, final Date hasta) {
		this.desde = desde;
		this.hasta = hasta;
	}

	@Override
	public boolean matcheaCon(final Date fechaCreacion) {
		return fechaCreacion.after(desde) && fechaCreacion.before(hasta);
	}
}
