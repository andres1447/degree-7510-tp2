package sucursal.modelo.ofertas;

import sucursal.modelo.compras.Compra;

public class CondicionGlobalFechaCreacion implements CondicionGlobal {
	private final MatcherFecha matcherFecha;

	public CondicionGlobalFechaCreacion(MatcherFecha matcherFecha) {
		this.matcherFecha = matcherFecha;
	}

	@Override
	public boolean aplicaEn(final Compra compra) {
		return matcherFecha.matcheaCon(compra.getFechaCreacion());
	}
}
