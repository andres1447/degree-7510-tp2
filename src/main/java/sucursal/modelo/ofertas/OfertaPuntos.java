package sucursal.modelo.ofertas;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import sucursal.modelo.compras.Compra;

public class OfertaPuntos implements IOferta {
	private final Predicate<Compra> condicion;
	private final Function<Compra, Integer> descuento;

	public OfertaPuntos(final Predicate<Compra> condicion,
			final Function<Compra, Integer> descuento) {
		this.condicion = condicion;
		this.descuento = descuento;
	}

	/**
	 * If the associated conditions are met, applies the discount to a buying
	 * session.
	 */
	public void aplicarSiCorresponde(final Compra compra) {
		if (condicion.apply(compra)) {
			Integer valorDescuento = descuento.apply(compra);
			if (valorDescuento != null) {
				compra.getCliente().agregarPuntos(valorDescuento);
			}
		}
	}
}
